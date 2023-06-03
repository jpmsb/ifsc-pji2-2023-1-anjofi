#ifndef _ANJOFI_WIFI_H_
#define _ANJOFI_WIFI_H_

#include <cstring>
#include <WiFi.h>
#include <esp_wifi.h>

class AnjofiWifi {
    public:
       AnjofiWifi(const char* ssid, const char* pass);

       ~AnjofiWifi();

       void connect();

       void disconnect();

       bool status();

       const char* getSSID();

       const char* getPass();

       const char* localIP();

    private:
       char* wifiSSID;
       char* wifiPass;
}; 

#endif
