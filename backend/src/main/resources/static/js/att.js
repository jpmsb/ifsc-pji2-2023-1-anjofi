
function atualizarValores() {
    fetch("http://localhost:8080/iniciar") 
      .then(response => response.json())
      .then(data => {
        const temperaturaElement = document.getElementById('temperatura');
        const umidadeElement = document.getElementById('umidade');
        const { Temperatura, Umidade } = data;
  
        temperaturaElement.innerText = Temperatura;
        umidadeElement.innerText = Umidade;
      })
      .catch(error => console.error('Ocorreu um erro:', error));
  }
  
  
  atualizarValores();
  
  setInterval(atualizarValores, 20000);
  