package anjofi.backend;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@Component
public class Iniciar {
    private AnjofiMqtt mqtt;
    private boolean conectouAoMQTT = false;
    
    private String server;
    private int port;
    private String user;
    private String password;

    public Iniciar(@Value("${mqtt.server}") String server, @Value("${mqtt.port}") String port, @Value("${mqtt.user}") String user, @Value("${mqtt.password}") String password) throws UnknownHostException, InterruptedException {
        this.server = BackendApplication.mqttServer != null ? BackendApplication.mqttServer : server;

        this.port = BackendApplication.mqttPort != null ? Integer.parseInt(BackendApplication.mqttPort) : Integer.parseInt(port);

        this.user = BackendApplication.mqttUser != null ? BackendApplication.mqttUser : user;

        this.password = BackendApplication.mqttPassword != null ? BackendApplication.mqttPassword : password;
        
        conectar();
    }

    private void conectar() throws UnknownHostException, InterruptedException {
        AnjofiMqtt novo;
        
        while (! this.conectouAoMQTT) {
            try {
                novo = new AnjofiMqtt("tcp://" + server, port, user, password, InetAddress.getLocalHost().getHostName());

                this.mqtt = novo;
                this.mqtt.listen("AnJoFi/#");
                this.conectouAoMQTT = true;
            } catch (MqttException e) {
                System.out.println("\n\nNão foi possível iniciar o cliente MQTT. Veja o erro abaixo:\n");
                e.printStackTrace();
                Thread.sleep(2000);
            }
        }
    }

    @GetMapping("/iniciar/{id}/last")
    public String getLastReceivedData(@PathVariable String id){
        if (id.length() == 8) {
            return mqtt.getLastReceivedData(id);
        } 

        return "";
    }

    @GetMapping("/iniciar")
    public String listAllDevices() {
        return mqtt.getAllDevices();
    }

}