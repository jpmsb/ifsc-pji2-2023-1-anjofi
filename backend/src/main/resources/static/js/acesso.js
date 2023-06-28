const formulario = document.querySelector("form");
const Iid = document.querySelector(".id");
const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");


function cadastrar() {
    fetch("http://localhost:8080/usuarios", {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: "POST",
      body: JSON.stringify({
        id: Iid.value,
        nome: Inome.value,
        email: Iemail.value,
        senha: Isenha.value,
      })
    })
    .then(function (res) {
      console.log(res);
      if (res.status === 201) {
        console.log("Cadastro realizado com sucesso");
        window.location.href = "login.html"; 
    }
    })
    .catch(function (res) {
      console.log(res);
    });
  }
  
  function limpar() { 
    Iid.value = "";
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
  }
  

  function fazerLogin() {
    fetch("http://localhost:8080/login", {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: "POST",
      body: JSON.stringify({
        id: Iid.value,
        senha: Isenha.value
      })
    })
    .then(function (res) {
      console.log(res.status);
      if (res.status === 200) {
        console.log("Login bem-sucedido");
        window.location.href = "./acesso-restrito/home_usuario.html";
      } else if (res.status === 401) {
        console.log("Credenciais inválidas");
        // Selecionar o elemento HTML correspondente ao campo de login
        var erroLogin = document.getElementById("erro-login");
        // Exibir a mensagem de erro no campo
        erroLogin.innerHTML = "Erro de login: Credenciais inválidas.";
      } else {
        console.log("Erro ao fazer login");
        // Selecionar o elemento HTML correspondente ao campo de login
        var erroLogin = document.getElementById("erro-login");
        // Exibir a mensagem de erro no campo
        erroLogin.innerHTML = "Erro ao fazer login. Por favor, tente novamente mais tarde.";
      }
    })
    .catch(function (res) {
      console.log(res);
      // Selecionar o elemento HTML correspondente ao campo de login
      var erroLogin = document.getElementById("erro-login");
      // Exibir a mensagem de erro no campo
      erroLogin.innerHTML = "Erro ao fazer login. Por favor, verifique sua conexão de internet.";
    });
  };
  



document.getElementById("criarContaBtn").addEventListener("click", function() {
    window.location.href = "cadastro.html"; 
  });
  
