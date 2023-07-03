package anjofi.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {
	@Value("${mqtt.server}")
	public static String mqttServer;

	@Value("${mqtt.port}")
	public static String mqttPort;

	@Value("${mqtt.user}")
	public static String mqttUser;

	@Value("${mqtt.password}")
	public static String mqttPassword;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	public void run(ApplicationArguments args) {
        if (args.containsOption("mqtt.server")) {
            String mqttServerOverridden = args.getOptionValues("mqtt.server").get(0);
            mqttServer = mqttServerOverridden;
        }

		if (args.containsOption("mqtt.port")) {
            String mqttPortOverridden = args.getOptionValues("mqtt.port").get(0);
            mqttPort = mqttPortOverridden;
        }

		if (args.containsOption("mqtt.user")) {
            String mqttUserOverridden = args.getOptionValues("mqtt.user").get(0);
            mqttUser = mqttUserOverridden;
        }

		if (args.containsOption("mqtt.password")) {
            String mqttPasswordOverridden = args.getOptionValues("mqtt.password").get(0);
            mqttPassword = mqttPasswordOverridden;
        }
    }
}