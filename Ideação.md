# Projeto Integrador II - 2023.1

## Ideação

### Brainstorming 4.3.5

**João**
- Usar um sensor de presença para identificar quando não há mais pessoas no ambiente e juntar essa análise para validar se há equipamentos que foram deixados ligados.
- Ter um sistema web que obtém a informação de que o(s) equipamento(s) está(ão) ligado(s) e sinalizar os responsáveis como possibilidade de desligamento.
- Além de monitorar que um equipamento esteja ligado desnecessariamente, permitir o desligamento remoto através de um sistema web.
- Observar o início e término comuns de eventos e gerar alertas, como uma placa luminosa piscante, com um lembrete de desligar equipamentos ligados.

**Filipi**
- Um sistema que fique na lateral da porta onde, se estiver luz acesa ou ar condicionado ligado, gera um aviso luminoso.
- Esse sistema se comunica com um aplicativo onde podemos visualizar o estado da sala, podendo desligar via aplicativo.
- Sensor de presença para verificar se tem movimento na sala e gera aviso.

**Ana**
- Sensor de presença que impeça o fechamento da sala de aula sem que os equipamentos estejam desligados, impedindo a trava da porta, com aviso visual e sonoro.
- Sensor de presença que detecte que não há ninguém na sala de aula e notifique de forma remota que há equipamentos ligados, permitindo o desligamento de forma remota.
- Avisos sonoros e visuais como checklist ao fechar a sala de aula, sem detector de presença. Se ainda assim for fechada, ativar alarme sonoro e visual externo, que pode ser controlado através de app.

**Arliones**
- Sinal luminoso (luz vermelha piscando) se abrir porta da sala e algum equipamento estiver ligado (implica em saber se equipamentos estão ligados).
- Instalação de sensores de presença e de equipamentos ligados em todas as salas e desenvolvimento de aplicação Web onde o vigilante/orientação de turno/gestor possa verificar salas com equipamentos ligados, porém sem usuários.
- Avisar via sistema web ou notificação (e.g., WhatsApp) que alguma sala está possivelmente com equipamentos ligados sem uso.

**Sensores:**
- Presença
- Porta aberta/fechada
- Luzes ligadas
- Ar condicionado ligado
- Projetor ligado
- Computadores ligados
- Servidor
- Serviço IoT - gerenciar dispositivos (e.g., NodeRED)
- Solução sem banco de dados - a aplicação considerará apenas o estado atual dos sensores (configurações em arquivos)
- Aplicação com regras de negócio que identificam situações relevantes do sistema (backend - em Java/Python)
- Servidor Web com frontend do sistema (GUI Web/Mobile)

**Atuação:**
- Acionar avisos luminosos e/ou sonoros
- Desligar equipamentos remotamente