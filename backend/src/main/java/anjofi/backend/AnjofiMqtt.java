package engtelecom.pji;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

public class AnjofiMqtt {
    private final String TOPIC = "AnJoFi/";
    private final String TEMPERATURE_TOPIC = "temperatura";

    private float temperature;
    private boolean acStatus;
    private float currentLightIntensity;
    private float baseLightIntensity;
    private boolean lightStatus;
    private MqttClient client;

    public AnjofiMqtt(String server, int port, String identifier) throws MqttException {
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

    public float getTemperature() throws MqttException {
        client.subscribe(TOPIC + TEMPERATURE_TOPIC);
        client.setCallback(new MqttCallback() {
            public void messageArrived(String topic, MqttMessage message){
                temperature = Float.parseFloat(new String(message.getPayload()));
            }

            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
        return temperature;
    }

    public boolean isAcStatus() {
        return acStatus;
    }

    public float getCurrentLightIntensity() {
        return currentLightIntensity;
    }

    public float getBaseLightIntensity() {
        return baseLightIntensity;
    }

    public boolean isLightStatus() {
        return lightStatus;
    }

    
}
