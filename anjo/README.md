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

## PlatformIO
### Instalação

O sistema operacional utilizado para o desenvolvimento do projeto é o [openSUSE Tumbleweed](https://get.opensuse.org/tumbleweed/). Para instalar os pacotes necessários para a programação do módulo ESP32, foi utilizado o seguinte comando:

```
sudo zypper install python310-platformio python310-packaging
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

Para poder fazer uso do sensor de temperatura e umidade DHT11, é necessário adicionar as bibliotecas para o mesmo:

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
lib_deps =
  ; Bibliotecas do sensor DHT11
  adafruit/DHT sensor library@^1.4.4
  adafruit/Adafruit Unified Sensor@^1.1.9
```

Com todas as bibliotecas corretamente adicionadas no arquivo "platformio.ini", caso ainda não estejam presentes para uso, serão obtidas na Internet automaticaticamente quando o projeto for compilado.

### Gravação na placa

Para compilar e gravar o programa no módulo ESP32, basta utilizar o seguinte comando no diretório do projeto:

```
pio run -t upload
```

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