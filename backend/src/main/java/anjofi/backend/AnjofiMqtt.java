package anjofi.backend;

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
    private String topicAndMessage;
    private MqttClient client;

    public AnjofiMqtt(String server, int port, String identifier) throws MqttException {
        this.server = server;
        this.port = port;
        this.identifier = identifier;
        
        client = new MqttClient(server + ":" + port, identifier);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(20);
        client.connect(options);

    }

    public AnjofiMqtt(String server, int port, String user, String password, String identifier) throws MqttException {
        client = new MqttClient(server + ":" + port, identifier);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(20);
        options.setUserName(user);
        options.setPassword(password.toCharArray());
        client.connect(options);
    }

    public void listen(String topic) throws MqttException {
        while (lastReceivedData == null){
            client.subscribe(topic);
            client.setCallback(new MqttCallback() {
                public void messageArrived(String topic, MqttMessage message){
                    lastReceivedData = new String(message.getPayload());
                    lastDevice = topic;
                    topicAndMessage = topic + ":" + lastReceivedData;
                }

                @Override
                public void connectionLost(Throwable cause) {
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });
        }
    }

    public String getLastReceivedData(){
        return lastReceivedData;
    }

    public String getLastDevice(){
        return lastDevice;
    }

    public String getTopicAndMessage(){
        return topicAndMessage;
    }
}