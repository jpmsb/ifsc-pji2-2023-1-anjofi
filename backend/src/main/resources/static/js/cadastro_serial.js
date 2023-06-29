const formulario = document.querySelector("form");
const Iids = document.querySelector(".ids");


let serverAddress;
if (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1') {
  serverAddress = 'http://localhost:8080';
} 
else {
  serverAddress = 'http://----------:8080';
}


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
  
 