package anjofi.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anjofi.backend.AnjofiMqtt;


@RestController

@RequestMapping("/iniciar")

public class Iniciar {

    @GetMapping
    public HashMap<String, String> iniciar() throws MqttException{
            
        AnjofiMqtt novo = new AnjofiMqtt("tcp://jpmsb.ddns.net", 51439, "anjofi","pji29006","anjofi" );
        
        novo.listen("AnJoFi/#");
        
        
        String tratar;
        ArrayList<Arduino> lista = new ArrayList<>();
        tratar = novo.getTopicAndMessage();

        tratar = novo.getLastReceivedData();

        Arduino arduino = new Arduino();

        
        String[] substrings = tratar.split(",");

        arduino.temperatura=substrings[0];
        arduino.umidade = substrings[1];
        arduino.estado = substrings[2];
        arduino.lampada= substrings[3];

        lista.add(arduino);


        //novo.getLastDevice();
        Operacao.iniciar();
        return arduino.statusDispositivo();
    }

    @Override
    public String toString() {
        return "Iniciar []";
    }
    
}
