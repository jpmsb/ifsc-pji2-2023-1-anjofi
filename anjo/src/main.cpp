#include <Arduino.h>
#include "LDR.h"
#include "AC.h"
#include <ArduinoMqttClient.h>
// #include <PubSubClient.h>
#include <WiFi.h>
// #include <esp_wifi.h>

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
// LDR ldr(15);
AC *ac;

// MQTT
WiFiClient wifiClient;
MqttClient mqttClient(wifiClient);

//Declaracao do servidor e da sua porta
const char broker[] = "test.mosquitto.org";
int port = 1883;

// Tópico
char topic[] = "AnJoFi/temperatura";

void setup() {
  //Abrimos uma comunicacao serial para imprimir dados no Monitor Serial
  Serial.begin(115200);
  
  //Informamos que o pino do botao atuara como entrada
  pinMode(pinoBotao, INPUT);
  pinMode(pinoLED, OUTPUT);

  pinMode(pinoLDR, INPUT);

  delay(1000);
  // ldr.measureBaseValue();

  if (digitalRead(pinoBotao) == LOW) pressionado = true;
  ac = new AC(pressionado);

  // Procurando e listando as redes sem fio
  int n = WiFi.scanNetworks();
  Serial.println("Procurando redes");
  if (n == 0) {
      Serial.println("Nenhuma rede encontrada.");
  } else {
    Serial.print("Encontrou ");
    Serial.print(n);
    Serial.print(" redes: ");
    for (int i = 0; i < n; ++i) {
      // Mostrar o SSID e RSSI de cada rede encontrada
      if (i) Serial.print("                   ");
      Serial.print(i + 1);
      Serial.print(": ");
      Serial.print(WiFi.SSID(i));
      Serial.print(" (");
      Serial.print(WiFi.RSSI(i));
      Serial.print(")");
      Serial.println((WiFi.encryptionType(i) == WIFI_AUTH_OPEN)?" ":"*");
      delay(10);
    }
  }

  Serial.print("Conectando na rede WiFi");
  WiFi.mode(WIFI_STA);
  WiFi.begin("STE2023", "IFSCSTE2023");

  while (WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(1000);
  }
 
  Serial.println(""); 
  Serial.println(WiFi.localIP());

  // Configuração inicial do MQTT
  Serial.print("Conectando-se ao broker: ");
  Serial.println(broker);
  
  if (! mqttClient.connect(broker, port)) {
    Serial.print("Erro ao conectar-se no broker! Erro = ");
    Serial.println(mqttClient.connectError());

    while (1);
  }

  Serial.println("Conectou-se ao broker");
}

//AC ac(pressionado);

void loop() {
  mqttClient.poll();

  // Obtendo a temperatura
  ac->getValues();
  float temperatura = ac->getCurrentTemperature();
  // float umidade = dht.readHumidity();
      
  // Exibindo a temperatura
  Serial.print("Temperatura: ");
  Serial.print(temperatura);

  if (ac->getAlreadyOn()) Serial.println(";   AC iniciou ligado.");
  else Serial.println(";   AC iniciou desligado.");

  // ------------------------------------------------------------- //
  // Por enquanto o LDR não será utilizado por conflito com o WiFi
  // Serial.print("Luminosidade atual: ");
      
  // Serial.print(ldr.getCurrent());
  // Serial.print(";   Luminosidade base: ");
  // Serial.print(ldr.getBaseValue());

  // Serial.print(";   Luminosidade status: ");

  // if (ldr.getStatus()) {
  //     Serial.println("Ligado");

  // } else Serial.println("Desligado");
  // ------------------------------------------------------------- //

  Serial.println("");
  
  // Enviando mensagem pelo MQTT
  mqttClient.beginMessage(topic);
  mqttClient.print(temperatura);
  mqttClient.endMessage();

  delay(2000);
}
