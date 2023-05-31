const formulario = document.querySelector("form");
const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");


function cadastrar() {
    fetch("http://191.36.15.51:8080/usuarios", {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: "POST",
      body: JSON.stringify({
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
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
  }
  
  formulario.addEventListener('submit', function (event) {
    event.preventDefault();
  
    cadastrar();
    limpar();
  });


function fazerLogin() {
  fetch("http://localhost:8080/usuarios/login", {
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    method: "POST",
    body: JSON.stringify({
      nome: Inome.value,
      senha: Isenha.value
    })
  })
  .then(function (res) {
    if (res.status === 200) {
      console.log("Login bem-sucedido");
      window.location.href = "home_usuario.html"; // Redireciona para outra_pagina.html após o login bem-sucedido

      // Faça o que for necessário após o login bem-sucedido, como redirecionar para outra página
    } else if (res.status === 401) {
      console.log("Credenciais inválidas");
      // Exiba uma mensagem de erro informando que as credenciais são inválidas
    } else {
      console.log("Erro ao fazer login");
      // Exiba uma mensagem de erro genérica
    }
  })
  .catch(function (error) {
    console.log("Erro ao fazer login", error);
    // Exiba uma mensagem de erro informando que ocorreu um erro ao fazer login
  });
};

formulario.addEventListener('submit', function (event) {
  event.preventDefault();

  fazerLogin();
});

document.getElementById("criarContaBtn").addEventListener("click", function() {
    window.location.href = "index.html"; 
  });
  
