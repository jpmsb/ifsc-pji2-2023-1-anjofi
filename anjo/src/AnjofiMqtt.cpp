#include "AnjofiMqtt.h"

AnjofiMqtt::AnjofiMqtt(const char* server, int port, const char* user, const char* pass) : mqttClient(wifiClient) {
    this->brokerServer = strdup(server);
    this->brokerPort = port;
    this->brokerUser = strdup(user);
    this->brokerPass = strdup(pass);

    mqttClient.setUsernamePassword(user, pass);
}

AnjofiMqtt::~AnjofiMqtt() {
    free(brokerServer);
    free(brokerUser);
    free(brokerPass);
}

bool AnjofiMqtt::connect(){
    return mqttClient.connect(brokerServer, brokerPort);
}

const int AnjofiMqtt::connectError(){
    return mqttClient.connectError();
}

void AnjofiMqtt::sendMessage(char topic[], char message[]){
    mqttClient.poll();
    mqttClient.beginMessage(topic);
    mqttClient.print(message);
    mqttClient.endMessage();
}

const char* AnjofiMqtt::getUser() {
    return brokerUser;
}

const char* AnjofiMqtt::getPass() {
    return brokerPass;
}

const char* AnjofiMqtt::getServer() {
    return brokerServer;
}
