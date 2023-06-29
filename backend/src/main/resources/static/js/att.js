let serverAddress;
if (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1') {
  serverAddress = 'http://localhost:8080';
} 
else {
  serverAddress = 'http://----------:8080';
}


function atualizarValores() {
  fetch(serverAddress + "/iniciar")
    .then(response => response.json())
    .then(data => {
      const temperaturaElement = document.getElementById('temperatura');
      const umidadeElement = document.getElementById('umidade');
      const acStatusElement = document.getElementById('acStatus');
      const lightStatusElement = document.getElementById('lightStatus');
      const lightCurrentValueElement = document.getElementById('lightCurrentValue');
      const lightBaseValueElement = document.getElementById('lightBaseValue');

      const { temperature, humidity, acStatus, lightStatus, lightCurrentValue, lightBaseValue } = data;

      temperaturaElement.innerText = temperature;
      umidadeElement.innerText = humidity;
      acStatusElement.innerText = acStatus;
      lightStatusElement.innerText = lightStatus;
      lightCurrentValueElement.innerText = lightCurrentValue;
      lightBaseValueElement.innerText = lightBaseValue;
    })
    .catch(error => console.error('Ocorreu um erro:', error));
}

atualizarValores();

setInterval(atualizarValores, 2000);

