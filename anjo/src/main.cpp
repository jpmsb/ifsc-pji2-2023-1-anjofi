#include <Arduino.h>
#include "LDR.h"
#include "AC.h"
#include <PubSubClient.h>
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

//Criacao da instancia DHT, em funcao do pino do sensor e do tipo do DHT
LDR ldr(15);
AC *ac;

void setup() {
  //Abrimos uma comunicacao serial para imprimir dados no Monitor Serial
  Serial.begin(115200);
  
  //Inicializamos nosso sensor DHT11
  // dht.begin();
      
  //Informamos que o pino do botao atuara como entrada
  pinMode(pinoBotao, INPUT);
  pinMode(pinoLED, OUTPUT);

  pinMode(pinoLDR, INPUT);

  delay(1000);
  ldr.measureBaseValue();

  if (digitalRead(pinoBotao) == LOW) pressionado = true;
  ac = new AC(pressionado);

  // WiFi.scanNetworks will return the number of networks found
  int n = WiFi.scanNetworks();
  Serial.println("Procutando redes");
  if (n == 0) {
      Serial.println("sem redes");
  } else {
    Serial.print(n);
    Serial.println(" encontrou redes");
    for (int i = 0; i < n; ++i) {
      // Print SSID and RSSI for each network found
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

  Serial.println("Conectando na rede WiFi");
  WiFi.mode(WIFI_STA);
  WiFi.begin("iPhone de JPMSB", "abcdefgh");

  while (WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(1000);
  }
  
  Serial.println(WiFi.localIP());
}

//AC ac(pressionado);

void loop() {
  //Assimilamos a variavel estadoBotao a leitura digital do pino do botao    
  estadoBotao = digitalRead(pinoBotao);

  delay(1000);
  //Criamos duas variaveis locais para armazenar a temperatura e a umidade lidas
  ac->getValues();
  float temperatura = ac->getCurrentTemperature();
  // float umidade = dht.readHumidity();
      
  //Mostramos no Monitor Serial os valores de temperatura e umidade
  Serial.print("Temperatura: ");
  Serial.print(temperatura);

  if (ac->getAlreadyOn()) Serial.println(";   AC iniciou ligado.");
  else Serial.println(";   AC iniciou desligado.");

  // Serial.print(" *C - Umidade: ");
  // Serial.print(umidade);

  // Por enquanto o LDR não será utilizado por conflito com o WiFi
  // Serial.print("Luminosidade atual: ");
      
  // Serial.print(ldr.getCurrent());
  // Serial.print(";   Luminosidade base: ");
  // Serial.print(ldr.getBaseValue());

  // Serial.print(";   Luminosidade status: ");

  // if (ldr.getStatus()) {
  //     Serial.println("Ligado");

  // } else Serial.println("Desligado");

  Serial.println("");

}
