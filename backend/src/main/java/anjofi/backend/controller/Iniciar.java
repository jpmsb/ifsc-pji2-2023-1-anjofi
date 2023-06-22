package anjofi.backend.controller;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import anjofi.backend.AnjofiMqtt;
@RestController
public class Iniciar {
    private AnjofiMqtt mqtt;

    public Iniciar () throws MqttException {
        AnjofiMqtt novo = new AnjofiMqtt("tcp://jpmsb.ddns.net", 51439, "anjofi","pji29006","anjofi" );
        this.mqtt = novo;
        this.mqtt.listen("AnJoFi/#");
    }

    @Override
    @GetMapping("/iniciar")
    public String toString() {
        return mqtt.getLastReceivedData();
    }
}