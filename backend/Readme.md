# Backend

Este repositório contém o código backend de um aplicativo que lida com operações relacionadas a usuários e autenticação. O código está escrito em Java e utiliza o framework Spring.

## Funcionalidades

O código possui as seguintes funcionalidades:

## Classe `Arduino`

A classe `Arduino` é utilizada para representar um dispositivo Arduino e obter seu status, com os seguintes atributos:

- `temperatura`: uma string que armazena a temperatura do dispositivo.
- `umidade`: uma string que armazena a umidade do dispositivo.
- `estado`: uma string que armazena o estado do dispositivo.
- `lampada`: uma string que armazena o estado da lâmpada do dispositivo.

A classe possui um construtor com os métodos a seguir:

- `statusDispositivo()`: retorna um objeto do tipo `HashMap<String, String>` contendo o status do dispositivo, com as chaves "Temperatura" e "Umidade" e seus respectivos valores.
- `toString()`: retorna uma string que representa o objeto `Arduino`, exibindo os valores dos atributos `temperatura`, `umidade`, `estado` e `lampada`.

## Classe `Dispositivo`

A classe `Dispositivo` é um controlador REST que gerencia com as requisições relacionadas aos dispositivos. Ela possui a seguinte estrutura:

- `@RestController`: uma anotação que marca a classe como um controlador REST, permitindo que ela receba e responda a requisições HTTP.
- `@RequestMapping("/dispositivos")`: uma anotação que especifica o caminho base para as rotas definidas no controlador.

Essa classe é utilizada como um ponto de entrada para gerenciar requisições relacionadas aos dispositivos, como obter informações ou controlar suas funcionalidades.

## Classe `Iniciar`

A classe `Iniciar` é um controlador REST responsável pela comunicação MQTT. Ela estabelece uma conexão MQTT com um servidor remoto e escuta tópicos MQTT. Ao chamar o endpoint `/iniciar` via requisição GET, o último dado recebido via MQTT é retornado como uma resposta.

## Classe `LoginController`

A classe `LoginController` é um controlador REST que lida com as operações de autenticação de usuários. Ela possui um endpoint POST em `/login` que recebe um objeto `Usuario` no corpo da requisição. A senha fornecida é criptografada e comparada com a senha armazenada no ID de usuário correspondente. Se as senhas coincidirem, uma resposta HTTP 200 OK é retornada. Caso contrário, uma resposta HTTP 404 Not Found é retornada.

## Classe `UsuarioController`

A classe `UsuarioController` é um controlador REST que lida com operações de criação, leitura, atualização e exclusão de usuários. Ela possui os seguintes endpoints:

- `GET /usuarios`: Retorna todos os usuários cadastrados em formato JSON.
- `GET /usuarios/{id}`: Retorna um usuário específico com base no ID fornecido em formato JSON.
- `POST /usuarios`: Adiciona um novo usuário com base nos dados fornecidos no corpo da requisição.
- `PUT /usuarios/{id}`: Atualiza os dados de um usuário específico com base no ID fornecido.
- `DELETE /usuarios/{id}`: Exclui um usuário específico com base no ID fornecido.

## Classe `Operacao`

A classe `Operacao` contém os métodos auxiliares para manipulação e persistência de usuários. Ela possui os seguintes métodos:

- `iniciar()`: Carrega os usuários do arquivo para a memória.
- `adicionarUsuario(Usuario usuario)`: Adiciona um novo usuário à lista de usuários, verificando se já existe um usuário com o mesmo ID.
- `exibirUsuarios()`: Retorna todos os usuários cadastrados.
- `salvarUsuariosNoArquivo()`: Salva os usuários no arquivo.
- `validarSenha(String id, String senha)`: Valida a senha fornecida por um usuário com a senha armazenada para o ID correspondente.
- `exibirUsuario(String id)`: Retorna um usuário específico com base no ID fornecido.
- `excluirUsuario(String id)`: Exclui um usuário com base no ID fornecido.
- `atualizarArquivo()`: Atualiza o arquivo de usuários após exclusão de um usuário.
- `criptografar(String texto)`: Criptografa um texto usando o algoritmo SHA-256.

## Classe `Usuario`

A classe `Usuario` representa a entidade de um usuário do sistema, com métodos para criptografar e validar a senha. Os atributos armazenam informações básicas do usuário, como ID, nome, email e senha, sendo a senha criptografada para fins de segurança. Ela possui os seguintes atributos:

- `id`: uma string que representa o identificador único do usuário.
- `nome`: uma string que armazena o nome do usuário.
- `email`: uma string que contém o email do usuário, criptografado utilizando o algoritmo SHA-256.
- `senha`: uma string que armazena a senha do usuário, também criptografada utilizando o algoritmo SHA-256.

A classe possui os seguintes métodos:

- `Usuario()`: construtor.
- `Usuario(String id, String nome, String email, String senha)`: construtor que recebe os parâmetros `id`, `nome`, `email` e `senha` para inicializar os atributos correspondentes.
- `getId()`: método que retorna o ID do usuário.
- `getNome()`: método que retorna o nome do usuário.
- `getEmail()`: método que retorna o email do usuário (descriptografado).
- `getSenha()`: método que retorna a senha do usuário (descriptografada).
- `criptografar(String texto)`: método que recebe uma string `texto` e retorna uma versão criptografada dessa string usando o algoritmo SHA-256.
- `descriptografar(String textoCriptografado)`: método que recebe uma string `textoCriptografado` e retorna a versão descriptografada dessa string.
- `validarSenha(String senha2)`: método que recebe uma string `senha2` e verifica se ela corresponde à senha do usuário. Retorna `true` se a senha for válida e `false` caso contrário.



## Classe `UsuarioExistenteException`

A classe `UsuarioExistenteException` é uma exceção personalizada enviada quando ocorre uma tentativa de adicionar um usuário que já existe. Ela herda da classe `RuntimeException` do Java.

Essa classe possui um construtor que recebe um objeto `Usuario` como parâmetro e utiliza o método `super()` para chamar o construtor da classe pai (`RuntimeException`). O construtor da exceção cria uma mensagem de erro informando que já existe um usuário com as informações fornecidas.


## Classe `UsuarioNaoEncontradoException`

A classe `UsuarioNaoEncontradoException` é uma exceção enviada quando ocorre uma tentativa de obter um usuário inexistente. Ela herda a classe `RuntimeException` do Java.

Essa classe possui um construtor que recebe uma String `id` como parâmetro e utiliza o método `super()` para chamar o construtor da classe pai (`RuntimeException`). O construtor da exceção cria uma mensagem de erro informando que não foi possível encontrar um usuário com a ID fornecida.


## Classe `AnjofiMqtt`

A classe `AnjofiMqtt` é responsável por gerenciar a conexão MQTT e a troca de mensagens entre um cliente MQTT e um servidor MQTT. As principais características e métodos da classe são:

- Atributos:
  - `server`: uma String que representa o endereço do servidor MQTT.
  - `port`: um inteiro que representa a porta do servidor MQTT.
  - `identifier`: uma String que identifica o cliente MQTT.
  - `lastReceivedData`: uma String que armazena a última mensagem recebida pelo cliente MQTT.
  - `lastDevice`: uma String que armazena o último dispositivo associado à mensagem recebida.
  - `topicAndMessage`: uma String que armazena o tópico e a mensagem recebida formatados como "dispositivo:mensagem".
  - `client`: um objeto da classe `MqttClient` que representa o cliente MQTT.

- Construtores:
  - `AnjofiMqtt(String server, int port, String identifier)`: construtor que recebe o endereço do servidor MQTT, a porta e o identificador do cliente MQTT. Ele estabelece uma conexão MQTT com o servidor.
  - `AnjofiMqtt(String server, int port, String user, String password, String identifier)`: construtor que recebe o endereço do servidor MQTT, a porta, o nome de usuário, a senha e o identificador do cliente MQTT. Ele estabelece uma conexão MQTT com o servidor autenticando-se com as credenciais fornecidas.

- Métodos:
  - `listen(String topic)`: configura o cliente MQTT para escutar um determinado tópico. Ele se inscreve no tópico fornecido e define um callback para lidar com as mensagens recebidas.
  - `getLastReceivedData()`: retorna a última mensagem recebida pelo cliente MQTT como uma String.
  - `getLastDevice()`: retorna o último dispositivo associado à mensagem recebida como uma String.
  - `getTopicAndMessage()`: retorna o tópico e a mensagem recebida formatados como "dispositivo:mensagem" como uma String.

A classe `AnjofiMqtt` permite que você estabeleça uma conexão MQTT, escute os tópicos desejados e acesse as últimas mensagens recebidas.

## Classe `BackendApplication`

A classe `BackendApplication` é a classe principal da aplicação Spring Boot. Ela contém o método `main` que é o ponto de entrada para iniciar a aplicação.

Principais características:

- `@SpringBootApplication`: Essa anotação marca a classe como uma classe de aplicação Spring Boot e configura automaticamente a aplicação.

- `main`: o método `main` é o ponto de entrada da aplicação. Ele inicia a aplicação Spring Boot chamando o método estático `run` da classe `SpringApplication`. O método `run` inicia a aplicação e realiza a configuração necessária para executar a aplicação.

- `throws MqttException`: a declaração `throws MqttException` no método `main` indica que o método pode lançar uma exceção do tipo `MqttException`. Essa exceção é lançada quando ocorre algum erro relacionado à biblioteca Eclipse Paho MQTT durante a execução da aplicação.


## Dependências

O código depende das seguintes bibliotecas:

- `org.eclipse.paho.client.mqttv3`: Biblioteca para comunicação MQTT.
- `org.springframework`: Bibliotecas para o desenvolvimento de aplicativos web utilizando o framework Spring.

## Execução

### Pré-requisitos
Ter acesso a um terminal com privilégios de superusuário.

### Passo 1: Atualizar os repositórios
Abra o terminal e execute o seguinte comando:

```
sudo apt update
```

### Passo 2: Instalar o Java JDK 17
Execute o seguinte comando no terminal:

```
sudo apt install openjdk-17-jdk
```

### Passo 3: Verificar a instalação do Java JDK
Após a instalação, verifique se o Java JDK 17 foi instalado corretamente. Digite o seguinte comando no terminal:

```
java -version
```

### Passo 4: Baixar e instalar o Gradle 7.2
Execute os seguintes comandos no terminal:

```
wget https://services.gradle.org/distributions/gradle-7.2-bin.zip
sudo unzip -d /opt/gradle gradle-7.2-bin.zip
```

Esses comandos baixarão o pacote binário do Gradle 7.2 e o extrairão para o diretório `/opt/gradle`.

### Passo 5: Configurar as variáveis de ambiente
Execute o seguinte comando para abrir o arquivo `.bashrc` no editor de texto `nano`:

```
sudo nano ~/.bashrc
```

No final do arquivo, adicione as seguintes linhas:

```
export GRADLE_HOME=/opt/gradle/gradle-7.2
export PATH=$PATH:$GRADLE_HOME/bin
```

Pressione `Ctrl + X`, digite `Y` e pressione Enter para salvar as alterações e sair do editor `nano`.

### Passo 6: Carregar as alterações de ambiente
Execute o seguinte comando no terminal:

```
source ~/.bashrc
```

### Passo 7: Verificar a instalação do Gradle
Digite o seguinte comando no terminal:

```
gradle -v
```

## Compilando o Servidor

Passo 1: Abra o terminal em seu sistema operacional. 

Passo 2: Navegue até o diretório em que deseja clonar o repositório. 

Passo 3: Clone o repositório
No terminal, use o comando `git clone` seguido da URL do repositório que você copiou anteriormente. Por exemplo:

```
git clone https://github.com/ifsc-arliones/ifsc-pji2-2023-1-anjofi.git
```

Execute o comando no terminal. O Git irá baixar todos os arquivos do repositório para o diretório atual.

Passo 4: Verifique o repositório clonado
Após a conclusão da clonagem, você pode verificar o diretório recém-clonado. Use o comando `ls` para listar o conteúdo do diretório:

```
ls
```
Passo 5: Navegue até o diretório ifsc-pji2-2023-1-anjofi/backend e execute os comandos abaixo:

```
gradle build
```

Passo 6: Abra um navegador web e acesse o endereço:

```
http://localhost:8000
```
## Observações


### 
