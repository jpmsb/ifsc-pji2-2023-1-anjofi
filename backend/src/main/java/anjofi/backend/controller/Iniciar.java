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
    private boolean conectouAoMQTT = false;

    public Iniciar () throws UnknownHostException, InterruptedException {
        conectar();
    }

    public void conectar() throws UnknownHostException, InterruptedException {
        AnjofiMqtt novo;

        while (! this.conectouAoMQTT) {
            try {
                novo = new AnjofiMqtt("tcp://jpmsb.ddns.net", 51439, "anjofi","pji29006", InetAddress.getLocalHost().getHostName());

                this.mqtt = novo;
                this.mqtt.listen("AnJoFi/#");
                this.conectouAoMQTT = true;
                System.out.println("iniciou mqtt");
            } catch (MqttException e) {
                System.out.println("\n\nNão foi possível iniciar o cliente MQTT. Veja o erro abaixo:\n");
                e.printStackTrace();
                Thread.sleep(2000);
            }
        }
    }

    @Override
    @GetMapping("/iniciar")
    public String toString() {
        return mqtt.getLastReceivedData();
    }
}