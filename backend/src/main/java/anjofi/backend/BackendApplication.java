package anjofi.backend;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import anjofi.backend.controller.Iniciar;

// import anjofi.backend.controller.Iniciar;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) throws MqttException {
		SpringApplication.run(BackendApplication.class, args);
	}
}