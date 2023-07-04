# Anjo

## Hardware utilizado

O _hardware_ utilizado é um _NodeMCU_ com o módulo da ESP32 com as seguintes características:

- Processador: Xtensa® Dual-Core 32-bit LX6
- Memória Flash programável: 4 MB
- Clock máximo: 240 MHz
- Memória RAM: 520 KBytes
- Memória ROM: 448 KBytes
- 25 pinos digitais com PWM de resolução de até 16 bits
- Wireless 802.11 b/g/n - 2.4GHz
- Bluetooth Low Energy padrão 4.2
- Tensão de alimentação externa: 4,5 V a 9 V

A placa de desenvolvimento a qual o _NodeMCU_ foi utilizado conta com os seguintes sensores:

- DHT11 para temperatura e umidade, conectado no pino 12
- Sensor de luminosidade conectado no pino 15
- Botão conectado no pino 4
- Acelerômetro de 3 eixos, conectados nos pinos de interrupção 34 e 35

## PlatformIO
### Instalação

O sistema operacional utilizado para o desenvolvimento do projeto é o [openSUSE Tumbleweed](https://get.opensuse.org/tumbleweed/). Para instalar os pacotes necessários para a programação do módulo ESP32, utiliza-se o seguinte comando:

```
pip install platformio
```

Certifique-se que a variável `$PATH` contenha o caminho:

```
$HOME/.local/bin:$PATH
```

No `bash` basta adicionar o comando abaixo no arquivo `$HOME/.profile`:

```
export PATH=$HOME/.local/bin:$PATH
```

No `fish` basta executar o comabando abaixo:

```
fish_add_path $HOME/.local/bin
```

Terminada a instalação, já é possível utilizar os comandos para criar um projeto, compilá-lo e gravá-lo na placa.

### Criação de um projeto

Para criar um novo projeto, utiliza-se o seguinte comando:

```
pio project init --ide vim --board esp32dev
```

Após a execução do comando acima, no diretório onde o mesmo foi executado, são criados os arquivos do projeto para o Kit de Desenvolvimento com o módulo da ESP32. A estrutura de arquivos inicial fica como mostrado abaixo:

```
├── include
│   └── README
├── lib
│   └── README
├── platformio.ini
├── src
│   └── ...
└── test
    └── README
```

Dentro do diretório "src" é criado um arquivo de texto chamado "main.cpp", que contém o código principal da aplicação. Os demais arquivos de código fonte necessários também ficam nesse diretório.

No arquivo "platformio.ini" há as especificações do projeto. O arquivo não modificado, criado utilizando o comando citado logo acima, possui o seguinte conteúdo:

```
; PlatformIO Project Configuration File
;
;   Build options: build flags, source filter
;   Upload options: custom upload port, speed and extra flags
;   Library options: dependencies, extra library storages
;   Advanced options: extra scripting
;
; Please visit documentation for the other options and examples
; https://docs.platformio.org/page/projectconf.html

[env:esp32dev]
platform = espressif32
board = esp32dev
framework = arduino
```

Para poder fazer uso do sensor DHT11, bem como do cliente MQTT, é preciso adicionar as dependências abaixo:

```
lib_deps = 
  adafruit/DHT sensor library@^1.4.4
  adafruit/Adafruit Unified Sensor@^1.1.9
  knolleary/PubSubClient@^2.8
  arduino-libraries/ArduinoMqttClient@^0.1.7
```

Além disso, de forma a deixar o programa mais configurável, variáveis referentes ao servidor MQTT, conexão WIFI, bem como o identificador do dispositivo e o nome do mesmo, são obtidas a partir de variáveis de ambiente:

```
[env:esp32dev]
platform = espressif32
board = esp32dev
framework = arduino
lib_deps = 
  adafruit/DHT sensor library@^1.4.4
  adafruit/Adafruit Unified Sensor@^1.1.9
  knolleary/PubSubClient@^2.8
  arduino-libraries/ArduinoMqttClient@^0.1.7

build_flags =
  -DBROKER_SERVER='"jpmsb.ddns.net"'
  -DBROKER_PORT=51439
  -DBROKER_USER='"anjofi"'
  -DBROKER_PASS='"pji29006"'
  -DWIFI_SSID='"${sysenv.WIFI_SSID}"'
  -DWIFI_PASS='"${sysenv.WIFI_PASS}"'
  -DDEVICE_ID='"${sysenv.DEVICE_ID}"'
  -DDEVICE_NAME='"${sysenv.DEVICE_NAME}"'

```

Com todas as bibliotecas corretamente adicionadas no arquivo "platformio.ini", caso ainda não estejam presentes para uso, serão obtidas na Internet automaticaticamente quando o projeto for compilado.

### Gravação na placa

Para compilar e gravar o programa no módulo ESP32, basta utilizar o seguinte comando no diretório do projeto:

```
WIFI_SSID="Nome da rede" WIFI_PASS="senha" DEVICE_ID="$(xxd -l 4 -p /dev/random)" DEVICE_NAME="Teste" pio run -t upload
```

O comando `$(xxd -l 4 -p /dev/random)` faz que seja gerada uma sequência de 8 caracteres, com númeroes de 0 a 9 e letras de "a" a "f". Dessa forma, gera-se uma sequência hexadecimal.

### Conexão Serial

#### Monitorar saída

Para monitorar a saída pela conexão serial, basta utilizar o seguinte comando:

```
pio device monitor
```

A velocidade padrão da porta serial é assumida em 9600 bit/s. Para especificar uma velocidade diferente, basta utilizar argumento "--baud velocidade", conforme mostrado abaixo:

```
pio device monitor --baud 115200
```

#### Entrada de dados

Caso queira-se inserir uma certa quantidade dados, basta utilizar o comando citado acima, com o argumento adicional "-f send_on_enter", que enviará os dados apenas quando o usuário teclar "Enter". Porém, o que é digitado não é mostrado ao usuário em tempo real. Para exibir o que se é digitado, basta acrescentar o argumento "--echo". O comando final fica conforme abaixo:

```
pio device monitor --baud 115200 --echo -f send_on_enter
```

Ainda é possível utilizar outros filtros, especificados no argumento "-f". O "colorize" é um interessante, pois adiciona cores diferentes para o que foi digitado e o que foi obtido do programa em execução:

```
pio device monitor --baud 115200 --echo -f send_on_enter -f colorize
```

Outro filtro interessante é o "esp32_exception_decoder", que consegue traduzir alguns códigos de erro do ESP32:

```
pio device monitor --baud 115200 --echo -f send_on_enter -f colorize -f esp32_exception_decoder
```
## Classes C++ criadas

### Classe LDR

Responsável por realizar operações com o sensor de luminosidade, acessível através do conversor analógico para digital. LDR é um resistor e sua resistência diminui conforme a presença de fótons aumenta.

#### Métodos

  - `LDR(int pin = 15);`: é o construtor da classe, e tem como parâmetro o pino on o LDR está conectado. Por padrão, o valor do pino é 15;
  - `void measure();`: coleta o valor do sensor a partir do conversor analógico para digital;
  - `void measureBaseValue();`: coleta o valor base que será utilizado como parâmetro para determinar o que é uma luz estar ligada;

  - `int getCurrent();`: obtém a medida mais recente armazenada;

  - `int getBaseValue();`: obtém o valor medido inicialmente;

  - `bool getStatus();`: retorna verdadeiro caso a luz esteja acesa e falso, caso contrário. A conta é realizada avaliando se o valor mais atual é menor do que o valor base;

#### Atributos

  - `int pin = 0;`: armazena o pino do sensor;
  - `int baseValue = 0;`: armazena o valor base;
  - `int currentValue = 0;`: armazena o valor mais recente;


### Classe TemperatureHumidity

Responsável por realizar as operações com o sensor DHT11.

#### Métodos

- `TemperatureHumidity(int pin = 12);`: Construtor da classe e possui como parâmetro o pino o qual o sensor está conectado. Por padrão, o valor é 12;
- `void measure();`: obtém e registra os valores de umidade e temperatura mais recentes;
- `void measureBaseTemperature();`: registra uma temperatura base;
- `float getCurrentTemperature();`: obtém o valor atualizado de temperatura;
- `float getBaseTemperature();`: retorna a temperatura base medida anteriormente;
- `float getPreviousTemperature();`: retorna a temperatura que foi medida anteriormente;
- `float getCurrentHumidity();`: retorna a umidade atualizada;
- `float getPreviousHumidity();`: retorna a umidade medida anteriormente;

#### Atributos

  - `float baseTemperature = 0;`: armazena o valor de temperatura base;
  - `float previousTemperature = 0;`: armazena o valor anterior de temperatura;
  - `float currentTemperature = 0;`: armazenha o valor atualizado de temperatura;
  - `float previousHumidity = 0;`: armazena o valor passado de temperatura;
  - `float currentHumidity = 0;`: armazenha a umidade atualizada;
  - `DHT dht;`: objeto da classe DHT que será utilizado para obter os dados dos sensores;


### Classe AC

Baseando-se no padrão de variação de temperatura, busca determinar se o ar-condicionado está ligado ou não;

#### Métodos
  - `AC(bool alreadyOn = false, int pin = 12);`: é o construtor da classe que tem como parâmetro se o ar-condicionado já estava ligado ou não quando o dispositivo foi inicializado. Tembém tem como parâmetro o pino o qual o sensor de temperatura está conectado. Esse pino é passado para a classe "TemperatureHumidity";
  - `void getValues();`: obtém os valores de temperatura;
  - `bool getStatus();`: retorna verdadeiro caso seja calculado que o ar-condicionado esteja ligado e falso, caso contrário;
  - `bool getAlreadyOn();`: retorna o estado de verdadeiro ou falso obtido no construtor;
  - `float getCurrentTemperature();`: retorna o valor atualizado de temperatura;
  - `float getPreviousTemperature();`: retorna o valor passado de temperatura;
  - `float getCurrentHumidity();`: retorna o valor atualizado de umidade;

#### Atributos

  - `float threshold = 1;`: limiar para variação de temperatura;
  - `float initialTemperature = 0;`: armazena o valor de temperatura assim que movimento foi detectado;
  - `float currentTemperature;`: armazena o valor atualizado de temperatura;
  - `float previousTemperature;`: armazena o valor passado de temperatura;
  - `float currentHumidity;`: armazena o valor atualizado de umidade;
  - `bool alreadyOn;`: armazena o valor obtido no construtor;
  - `TemperatureHumidity sensor;`: atributo que será utilizado para interagir com a classe controladora do sensor de temperatura e umidade;

### Classe AnjofiMqtt

Controla a conexão do dispositivo com o servidor MQTT.

#### Métodos

  - `AnjofiMqtt(const char* server, int port, const char* user, const char* pass);`: construtor da classe e tem como parâmetro o servidor, porta, usuário e senha para conectar-se ao servidor MQTT;
  - `bool connect();`: retorna verdadeiro se a conexão foi realizada com sucesso e falso, caso contrário;
  - `void sendMessage(char topic[], char message[]);`: envia mensagem para um tópico. Possui como parâmetro o tópico de destino e a mensagem que será publicada;
  - `const int connectError();`: retorna o código numérico de falha de conexão;
  - `const char* getUser();`: retorna o usuário utilizado para autenticação;
  - `const char* getPass();`: retorna a senha utilizada para autenticação;
  - `const char* getServer();`: retorna o endereço do servidor MQTT;

#### Atributos

  - `char* brokerServer;`: armazena o endereço do servidor MQTT;
  - `int brokerPort = 0;`: armazenha a porta do servidor MQTT;
  - `char* brokerUser;`: armazena o usuário para autenticar-se no servidor MQTT;
  - `char* brokerPass;`: armazena a senha para autenticar-se no servidor MQTT;
  - `WiFiClient wifiClient;`: objeto do cliente WIFI que será utilizado como meio para a transmissão dos dados;
  - `MqttClient mqttClient;`: objeto cliente MQTT que será utilizado para conectar-se ao servidor MQTT, bem como publicar as mensagens;

### Classe AnjofiWifi

Controle a conexão entre o dispositivo e a rede sem fio.

#### Métodos

  - `AnjofiWifi(const char* ssid, const char* pass);`: construtor da classe que possui como parâmetros o nome da rede sem fio alvo e a senha da rede sem fio em questão;
  - `void connect();`: ligar o módulo WIFI e conecta-se na rede sem fio informada no construtor;
  - `void disconnect();`: desconecta-se da rede sem fio e desliga o módulo WIFI;
  - `bool status();`: retorna verdadeiro caso a conexão esteja estabelecida e falso, caso contrário;
  - `const char* getSSID();`: retorna o nome da rede sem fio;
  - `const char* getPass();`: retorna a senha da rede sem fio;
  - `const char* localIP();`: retorna o endereço IP obtido via DHCP;

#### Atributos

  - `char* wifiSSID;`: armazena o nome da rede sem fio;
  - `char* wifiPass;`: armazena a senha da rede sem fio;

## Roteiro de funcionamento do programa

1. Preparação do ambiente com a declaração de variáveis para a conexão com a rede WIFI e o servidor MQTT, além da instanciação de objetos para trabalhar com o sensor LDR, temperatura, umidade, rede sem fio e cliente MQTT;

1. Definido o tópico como "AnJoFi/ID do dispositivo", cujo ID contém 8 caracteres;

1. Inicia a porta serial, bem como define os pinos dos sensores de luminosidade e temperatura como entradas;

1. Obtém a o valor de luminosidade inicial;

1. Define o tópico o qual as mensagens serão publicadas, imprimindo o resultado final na saída serial;

1. Obtém os valores atualizados de temperatura, umidade e luminosidade;

1. Imprime os valores lidos na saída serial;

1. Conecta-se na rede sem fio;

1. Conecta-se no servidor MQTT;

1. Publica o JSON contendo o apelido do dispositivo, bem como os valores lidos;

1. Desconecta-se da rede sem fio;

1. Retorna ao passo 6.

## Limitações da plataforma de desenvolvimento

O kit de desenvolvimento utilizado possui a limitação com o uso simultâneo do sensor de luminosidade e o módulo da rede sem fio. Dessa forma, foi preciso alternar entre os usos dos componentes. Por este motivo, a placa não permanece conectada o tempo todo à rede sem fio. No momento da obtenção dos dados atualizados dos sensores, o módulo WIFI é desconectado da rede e desligado.