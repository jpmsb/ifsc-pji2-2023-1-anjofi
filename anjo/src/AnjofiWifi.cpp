#include "AnjofiWifi.h"

AnjofiWifi::AnjofiWifi(const char* ssid, const char* pass) {
    this->wifiSSID = strdup(ssid);
    this->wifiPass = strdup(pass);
}

AnjofiWifi::~AnjofiWifi() {
    free(wifiSSID);
    free(wifiPass);
}

void AnjofiWifi::connect() {
    esp_wifi_start();
    WiFi.mode(WIFI_STA);
    WiFi.begin(wifiSSID, wifiPass);
}

void AnjofiWifi::disconnect() {
    WiFi.disconnect();
    esp_wifi_stop();    
}

bool AnjofiWifi::status(){
    return WiFi.status() == WL_CONNECTED;
}

const char* AnjofiWifi::getSSID() {
    return wifiSSID;
}

const char* AnjofiWifi::getPass() {
    return wifiPass;
}

const char* AnjofiWifi::localIP(){
    return WiFi.localIP().toString().c_str();
}
