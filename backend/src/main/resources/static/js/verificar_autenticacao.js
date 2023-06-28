// Função para verificar se o usuário está autenticado antes de permitir o acesso à página "home_usuario.html"
function verificarAutenticacao() {
    // Verifique se o token de autenticação está presente
    var token = getCookie("token");
    if (!token) {
      // Token não encontrado, redirecione para a página de login
      window.location.href = "./pagina_de_login.html";
      return;
    }
  
    // Verifique se o token é válido, fazendo uma solicitação ao backend
    fetch("http://localhost:8080/verificar-autenticacao", {
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
        // Token inválido, redirecione para a página de login
        window.location.href = "./pagina_de_login.html";
      }
    })
    .catch(function (err) {
      console.log(err);
      // Erro ao verificar a autenticação, redirecione para a página de login
      window.location.href = "./pagina_de_login.html";
    });
  };
  
  // Função auxiliar para obter o valor de um cookie
  function getCookie(name) {
    var cookieArr = document.cookie.split(";"); // Adicione o ponto e vírgula aqui
    for (var i = 0; i < cookieArr.length; i++) {
      var cookiePair = cookieArr[i].split("=");
      if (name === cookiePair[0].trim()) {
        return decodeURIComponent(cookiePair[1]);
      }
    }
    return null;
  }
  