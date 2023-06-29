# Frontend

Este repositório contém um projeto que integra parte do frontend desenvolvido em HTML, CSS e JS na estrutura do backend, utilizando a API com Spring Boot. Para acessar o repositório do backend, onde a parte do frontend está localizada dentro da pasta "resources", clique no seguinte link:

[Repositório do frontend dentro do Backend](https://github.com/ifsc-arliones/ifsc-pji2-2023-1-anjofi/tree/c00d47717027cb76f6661a2d4e63f52cffa9ae9e/backend/src/main/resources/static)

## Funcionalidades

O código apresenta 5 páginas HTML com as seguintes funcionalidades:

### Páginas Abertas
- `index.html`: página de início, que fornece uma breve introdução e objetivos da empresa.
- `cadastro.html`: página onde o usuário pode realizar o cadastro na plataforma.
- `login.html`: página onde o usuário pode fazer login na plataforma.

### Páginas com Acesso Restrito
- `home_usuario.html`: página que exibe dados atualizados a cada 2 segundos.
- `serial.html`: página onde o cliente pode cadastrar seu dispositivo em sua conta.

Dessa forma, o projeto possui essas páginas com diferentes funcionalidades, tanto para usuários não autenticados quanto para aqueles com acesso restrito.

### Segue alguns componentes em JavaScript, responsáveis por adicionar interatividade e dinamismo à página:

- `acesso.js`: responsável por fazer o post para o cadastro e login.
- `att.js`: responsável por fazer o get de atualização a cada 2 segundos dos valores para o usuário.
- `cadastro_serial.js`: responsável por fazer o post para cadastro do serial.
- `script.js`: responsável por fazer a interação do mascote AnJoFi com o usuário.
- `verificar_autenticacao.js`: responsável por verificar o token para permitir que apenas logins cadastrados possam visualizar os dados e cadastrar dispositivos.

### Segue alguns componentes em CSS, responsáveis estilo e o layout da página web:

- `home_usuario.css`: responsável pelo estilo da página de dados para o usuário.
- `home.css`: responsável pelo estilo da página de início.
- `serial.css`: responsável pelo estilo da página de cadastro de serial.
- `style.css`: responsável pelo estilo da página de login e cadastro.







