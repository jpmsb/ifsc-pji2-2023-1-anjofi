const formulario = document.querySelector("form");
const Iids = document.querySelector(".ids");
const port = '8080';

let serverAddress;

serverAddress = 'http://' + window.location.hostname + ':' + port;


function cadastrar_serial() {
    fetch(serverAddress + "/usuarios/serial", {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: "POST",
      body: JSON.stringify({
        ids: Iids.value,
 
      })
    })
    .then(function (res) {
      console.log(res);
      if (res.status === 201) {
        console.log("Cadastro realizado com sucesso");
        window.location.href = "home_usuario.html"; 
    }
    })
    .catch(function (res) {
      console.log(res);
    });
  }
  
 