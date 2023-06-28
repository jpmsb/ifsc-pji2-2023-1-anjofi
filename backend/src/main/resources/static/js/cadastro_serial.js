const formulario = document.querySelector("form");
const Iids = document.querySelector(".ids");


function cadastrar_serial() {
    fetch("http://localhost:8080/usuarios/serial", {
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
  
 