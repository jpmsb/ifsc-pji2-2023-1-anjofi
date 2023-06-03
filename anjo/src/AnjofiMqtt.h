#ifndef _ANJOFI_MQTT_H_
#define _ANJOFI_MQTT_H_

#include <cstring>
#include <WiFi.h>
#include <ArduinoMqttClient.h>

class AnjofiMqtt {
    public:
       AnjofiMqtt(const char* server, int port, const char* user, const char* pass);

       ~AnjofiMqtt();

       bool connect();

       void sendMessage(char topic[], char message[]);

       bool getStatus();

       const int connectError();

       const char* getUser();

       const char* getPass();

       const char* getServer();

    private:
       char* brokerServer;
       int brokerPort = 0;
       char* brokerUser;
       char* brokerPass;
       WiFiClient wifiClient;
       MqttClient mqttClient;
}; 

#endif
