const port = '8080';
let serverAddress = 'http://' + window.location.hostname + ':' + port;

document.addEventListener("DOMContentLoaded", function() {
  const devices = document.getElementById('devices');

  fetch(serverAddress + "/iniciar")
    .then(response => response.json())
    .then(data => {
      data.forEach(item => {
        const option = document.createElement('option');
        option.value = item;
        option.innerText = item;
        devices.appendChild(option);
      });
    })
    .catch(error => console.error('Ocorreu um erro:', error));

  
});

function atualizarValores() {
  const devices = document.getElementById('devices');
  const selectedOption = devices.value;
  const idInput = selectedOption;

  fetch(serverAddress + "/iniciar/" + idInput + "/last")
    .then(response => response.json())
    .then(data => {
      const nameElement = document.getElementById('nome');
      const temperaturaElement = document.getElementById('temperatura');
      const umidadeElement = document.getElementById('umidade');
      const acStatusElement = document.getElementById('acStatus');
      const lightStatusElement = document.getElementById('lightStatus');
      const lightCurrentValueElement = document.getElementById('lightCurrentValue');
      const lightBaseValueElement = document.getElementById('lightBaseValue');

      const { name, temperature, humidity, acStatus, lightStatus, lightCurrentValue, lightBaseValue } = data;

      nameElement.innerText = name;
      temperaturaElement.innerText = temperature;
      umidadeElement.innerText = humidity;
      acStatusElement.innerText = acStatus;
      lightStatusElement.innerText = lightStatus;
      lightCurrentValueElement.innerText = lightCurrentValue;
      lightBaseValueElement.innerText = lightBaseValue;

      // Verificar se acStatus ou lightStatus são "true" e adicionar classe CSS correspondente
      if (acStatus === "true" ) {
        acStatusElement.classList.add('red-background');
      } else {
        acStatusElement.classList.remove('red-background');
      }
      if (lightStatus === "true") {
        lightStatusElement.classList.add('red-background');
      } else {
        lightStatusElement.classList.remove('red-background');
      }
    })
    .catch(error => console.error('Ocorreu um erro:', error));
}

atualizarValores();
setInterval(atualizarValores, 2000);