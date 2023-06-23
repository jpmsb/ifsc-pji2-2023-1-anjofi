package anjofi.backend.controller;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

import anjofi.backend.AnjofiMqtt;

@RestController
public class Iniciar {
    private AnjofiMqtt mqtt;

    public Iniciar () throws MqttException, UnknownHostException {
        AnjofiMqtt novo = new AnjofiMqtt("tcp://jpmsb.ddns.net", 51439, "anjofi","pji29006", InetAddress.getLocalHost().getHostName());
        this.mqtt = novo;
        this.mqtt.listen("AnJoFi/#");
        System.out.println("iniciou mqtt");
    }

    @Override
    @GetMapping("/iniciar")
    public String toString() {
        return mqtt.getLastReceivedData();
    }
}
