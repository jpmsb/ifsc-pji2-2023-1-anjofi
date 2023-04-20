#include <Arduino.h>

/************************************************
* RoboCore - IoT DevKit - LoRaWAN   
*
* Aprendendo a utilizar o sensor de temperatura e
* umidade do kit, em conjunto com o sensor de 
* luminosidade e botao.
***********************************************/

//Inclusao da bilbioteca do sensor DHT11 
#include "LDR.h"
#include "AC.h"

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

  if (pinoBotao == LOW) pressionado = true;
}

AC ac(pressionado);

void loop() {
  //Assimilamos a variavel estadoBotao a leitura digital do pino do botao    
  estadoBotao = digitalRead(pinoBotao);

  //Se o botao estiver pressionado, entramos nesta rotina condicional
  delay(1000);
  estadoBotao = digitalRead(pinoBotao);
      
  //Criamos duas variaveis locais para armazenar a temperatura e a umidade lidas
  ac.getValues();
  float temperatura = ac.getCurrentTemperature();
  // float umidade = dht.readHumidity();
      
  //Mostramos no Monitor Serial os valores de temperatura e umidade
  Serial.print("Temperatura: ");
  Serial.println(temperatura);
  // Serial.print(" *C - Umidade: ");
  // Serial.print(umidade);
  Serial.print("Luminosidade atual: ");
      
  //Lemos entao o sensor LDR e mapeamos seu valor de 0 a 100 %
  

  //Finalizamos a linha impressa no Monitor Serial com a luminosidade em %
  Serial.println(ldr.getCurrent());
  Serial.print("Luminosidade base: ");
  Serial.println(ldr.getBaseValue());

  Serial.print("Luminosidade status: ");

  if (ldr.getStatus()) {
      Serial.println("Ligado");

  } else Serial.println("Desligado");

}
