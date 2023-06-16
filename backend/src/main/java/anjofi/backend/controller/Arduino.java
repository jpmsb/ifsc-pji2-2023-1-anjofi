package anjofi.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;

public class Arduino {

    String temperatura;
    String umidade;
    String estado;
    String lampada;

    public Arduino() {
        System.out.println("");

        this.temperatura = temperatura;
        this.umidade = umidade;
        this.estado = estado;
        this.lampada = lampada;
    }

    public HashMap <String, String> statusDispositivo(){
        HashMap aux = new HashMap<>();
        aux.put("Temperatura", this.temperatura);
        aux.put("Umidade", this.umidade);


        return aux;
        
    }
    @Override
    public String toString() {
        return "Arduino [temperatura=" + temperatura + ", umidade=" + umidade + ", estado=" + estado + ", lampada="
                + lampada + "]";
    }

}
