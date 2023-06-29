const formulario = document.querySelector("form");
const Iid = document.querySelector(".id");
const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");

let serverAddress;
if (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1') {
  serverAddress = 'http://localhost:8080';
} 
else {
  serverAddress = 'http://----------:8080';
}

function cadastrar() {
    fetch(serverAddress + "/usuarios", {
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
      else if (res.status === 500) {
      exibirErro("Erro ao cadastrar. Verifique os dados e tente novamente.");
      var erroCadastro = document.getElementById("erro-cadastro");
      erroCadastro.innerHTML = "Erro no servidor. Tente novamente mais tarde.";
      }
      else {
      exibirErro("Erro ao cadastrar. Verifique os dados e tente novamente.");
      var erroCadastro = document.getElementById("erro-cadastro");
      erroCadastro.innerHTML = "Erro ao fazer cadastro. ID indisponivel";
    }
  
    })
    .catch(function (res) {
      console.log(res);
      var erroCadastro = document.getElementById("erro-cadastro");
      erroCadastro.innerHTML = "Erro ao fazer cadastro. ID indisponivel.";
    });
  }
  
  function limpar() { 
    Iid.value = "";
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
  }  

  function fazerLogin() {
    fetch(serverAddress + "/login", {
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
      } 
      else if (res.status === 401) {
        console.log("Credenciais inválidas");
        var erroLogin = document.getElementById("erro-login");
        erroLogin.innerHTML = "Erro de login: Credenciais inválidas.";
      } 
      else {
        console.log("Erro ao fazer login");
        var erroLogin = document.getElementById("erro-login");
        erroLogin.innerHTML = "Erro ao fazer login. Por favor, tente novamente";
      }
    })
    .catch(function (res) {
      console.log(res);
      var erroLogin = document.getElementById("erro-login");
      erroLogin.innerHTML = "Erro ao fazer login. Por favor, tente novamente.";
    });
  };

document.getElementById("criarContaBtn").addEventListener("click", function() {
    window.location.href = "cadastro.html"; 
  });
  
