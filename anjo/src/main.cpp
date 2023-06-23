#include <Arduino.h>
#include "LDR.h"
#include "AC.h"
#include <string.h>
#include "AnjofiMqtt.h"
#include "AnjofiWifi.h"

//Declaracao das variaveis dos pinos dos sensores e botao
const int pinoDHT = 12;
const int pinoBotao = 4;
const int pinoLDR = 15;
const int pinoLED = 13;

//Declaracao das variaveis que receberao a leitura do botao e sensor de luz
int estadoBotao = 0;
int valorLDR = 0;
bool pressionado = false;

// Criando os objetos do sensor de luz e ar condicionado
LDR ldr(15);
AC *ac;

//Declaracao do servidor e da sua porta
char brokerServer[] = BROKER_SERVER;
int brokerPort = BROKER_PORT;
char brokerUser[] = BROKER_USER;
char brokerPass[] = BROKER_PASS;
char ID[] = DEVICE_ID;
char SSID[] = WIFI_SSID;
char PASS[] = WIFI_PASS;
char jsonMessage[128];
char lightStatus[6];
int lightBaseValue;
int lightCurrentValue;
float temperature;
float humidity;
char acStatus[6];

AnjofiMqtt mqtt(brokerServer, brokerPort, brokerUser, brokerPass);
AnjofiWifi wifi(SSID, PASS);

// Tópico
char topic[16] = "AnJoFi/xxxxxxxx";

void setup() {
  //Abrimos uma comunicacao serial para imprimir dados no Monitor Serial
  Serial.begin(115200);
  
  //Informamos que o pino do botao atuara como entrada
  pinMode(pinoBotao, INPUT);
  pinMode(pinoLED, OUTPUT);

  pinMode(pinoLDR, INPUT);

  delay(1000);
  ldr.measureBaseValue();

  if (digitalRead(pinoBotao) == LOW) pressionado = true;
  ac = new AC(pressionado);

  memcpy(topic+7, ID, 8);
  Serial.println(topic);

}

void loop() {
  // Getting AC parameters
  ac->getValues();
  temperature = ac->getCurrentTemperature();
  humidity = ac->getCurrentHumidity();

  if (ac->getStatus()) strcpy(acStatus, "true");
  else strcpy(acStatus, "false");
      
  // Exibindo a temperatura
  Serial.print("Temperatura: ");
  Serial.print(temperature);

  if (ac->getAlreadyOn()) Serial.println(";   AC iniciou ligado.");
  else Serial.println(";   AC iniciou desligado.");

  // ------------------------------------------------------------- //
  lightBaseValue = ldr.getBaseValue();
  lightCurrentValue = ldr.getCurrent();

  Serial.print("Luminosidade atual: ");
   
  Serial.print(lightCurrentValue);
  Serial.print(";   Luminosidade base: ");
  Serial.print(lightBaseValue);

  Serial.print(";   Luminosidade status: ");

  if (ldr.getStatus()) {
      Serial.println("Ligado");
      strcpy(lightStatus, "true");

  } else {
      Serial.println("Desligado");
      strcpy(lightStatus, "false");
  }
  // ------------------------------------------------------------- //

  sprintf(jsonMessage, "{temperature:%f, humidity:%f, acStatus:%s, lightStatus:%s, lightCurrentValue:%d, lightBaseValue:%d}", temperature, humidity, acStatus, lightStatus, lightCurrentValue, lightBaseValue);

  Serial.println("");
 
  // Conecting to WiFi
  Serial.print("Conectando na rede WiFi");
  wifi.connect();

  while (! wifi.status()){
      Serial.print(".");
      delay(1000);
  }

  Serial.println("");
  Serial.println(WiFi.localIP());

  delay(2000);

  // Connecting to MQTT server
  if (! mqtt.connect()) {
      Serial.print("Erro ao conectar-se no broker! Erro = ");
      Serial.println(mqtt.connectError());
  }

  // Sending data to MQTT server
  mqtt.sendMessage(topic, jsonMessage);

  delay(1000);
  wifi.disconnect();
  delay(2000);
}
