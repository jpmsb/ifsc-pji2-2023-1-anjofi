package anjofi.backend;

import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AnjofiMqtt {
    private String server;
    private int port;
    private String identifier;
    private String lastReceivedData;
    private String lastDevice;
    private MqttClient client;
    private HashMap<String, String[]> receivedMessages;

    public AnjofiMqtt(String server, int port, String identifier) throws MqttException {
        this.server = server;
        this.port = port;
        this.identifier = identifier;
        this.receivedMessages = new HashMap<>();
        
        client = new MqttClient(server + ":" + port, identifier);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(20);
        client.connect(options);

    }

    public AnjofiMqtt(String server, int port, String user, String password, String identifier) throws MqttException {
        client = new MqttClient(server + ":" + port, identifier);
        this.receivedMessages = new HashMap<>();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(20);
        options.setUserName(user);
        options.setPassword(password.toCharArray());
        client.connect(options);
    }

    public void listen(String topic) throws MqttException {
        client.subscribe(topic);
        client.setCallback(new MqttCallback() {
            public void messageArrived(String topic, MqttMessage message){
                lastReceivedData = new String(message.getPayload());
                String[] deviceID = topic.split("/");
                lastDevice = deviceID[1];
                storeNewMessage(lastDevice, lastReceivedData);
            }

            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
    }

    private void storeNewMessage(String deviceID, String message) {
        if (receivedMessages.containsKey(deviceID)) {
            int currentMessageArrayLenght = receivedMessages.get(deviceID).length;
            String[] newMessageArray = new String[currentMessageArrayLenght + 1];

            System.arraycopy(receivedMessages.get(deviceID), 0, newMessageArray, 0,currentMessageArrayLenght);
            newMessageArray[currentMessageArrayLenght] = message;

            receivedMessages.put(deviceID, newMessageArray);
        } else {
            String[] newMessageArray = {message};
            receivedMessages.put(deviceID, newMessageArray);
        }
    }

    public String getLastReceivedData(){
        return lastReceivedData;
    }

    public String getLastReceivedData(String deviceID){
        if (receivedMessages.containsKey(deviceID)) {
            int position = receivedMessages.get(deviceID).length - 1;
            return receivedMessages.get(deviceID)[position];
        } else return "";
    }

    public String getLastDevice(){
        return lastDevice;
    }

    public String getAllDevices(){
        String allDevices = "[";

        for (String device : receivedMessages.keySet()) {
            allDevices += "\"" + device + "\", ";
        }
        allDevices = allDevices.substring(0, allDevices.length() - 2);
        allDevices += "]";
        
        return allDevices;
    }
}
