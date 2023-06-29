let serverAddress;
if (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1') {
  serverAddress = 'http://localhost:8080';
}
else {
  serverAddress = 'http://----------:8080';
}

// Não esta validando no backend
// Função para verificar se o usuário está autenticado antes de permitir o acesso à página "home_usuario.html"

function verificarAutenticacao() {
    var token = getCookie("token");
    if (!token) {
      window.location.href = "./login.html";
      return;
    }
  
    fetch(serverAddress + "/verificar-autenticacao", {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      },
      method: "GET"
    })
    .then(function (res) {
      if (res.status === 200) {
        console.log("Usuário autenticado");
      } else {
        console.log("Token inválido");
        window.location.href = "./login.html";
      }
    })
    .catch(function (err) {
      console.log(err);
      window.location.href = "./login.html";
    });
  };
  
  function getCookie(name) {
    var cookieArr = document.cookie.split(";"); 
    for (var i = 0; i < cookieArr.length; i++) {
      var cookiePair = cookieArr[i].split("=");
      if (name === cookiePair[0].trim()) {
        return decodeURIComponent(cookiePair[1]);
      }
    }
    return null;
  }
  