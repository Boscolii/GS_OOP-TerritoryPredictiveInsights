# Territory Predictive Insights

Projeto da Global Solution de POO no recorte de **clima local**. Sensores simulados de chuva, nível do rio e umidade do solo registram leituras, fazem previsões simples e emitem alertas para apoiar a prevenção de alagamentos.

## Público beneficiado e decisão apoiada

O projeto foi pensado para comunidades que vivem em áreas vulneráveis a alagamentos e para as equipes que acompanham essas regiões, como associações de bairro, organizações comunitárias e órgãos municipais. Moradores podem ser beneficiados indiretamente por uma identificação mais rápida de condições que mereçam atenção.

O sistema apoia essas equipes na decisão de quando investigar uma situação, consultar outras fontes e avaliar ações preventivas conforme os protocolos locais. As leituras e os alertas são simulados e não substituem informações oficiais nem a avaliação das pessoas responsáveis.

## Como executar

Use Java 21 e execute `./mvnw.cmd spring-boot:run` no Windows. O menu é de console. O banco H2 fica no diretório `data/territorio`, preservando histórico entre execuções. Para rodar os testes: `./mvnw.cmd test`.

## Requisitos atendidos

- Abstração `ComponenteTerritorial`, herança em `EstacaoMonitoramento`, interface `Sensor` com três implementações e estratégias polimórficas de previsão.
- Exceções próprias para dados inválidos e fonte indisponível, com tratamento no menu.
- Entidades JPA `Medicao` e `Alerta`, repositórios Spring Data JPA e histórico persistido.
- Factory (`FabricaPrevisao`) para selecionar estratégias sem `if` espalhado e Singleton (`ConfiguracaoSistema`) para limiares.
- Oito testes JUnit, incluindo `assertThrows` e Mockito para verificar a emissão de alerta sem banco.

## Decisões da equipe

- **Recorte e sensores:** o grupo escolheu clima local e risco de alagamento. A simulação utiliza chuva, nível do rio e umidade do solo porque cada medida contribui com uma perspectiva diferente das condições monitoradas. As faixas e unidades adotadas são simplificações para o exercício.
- **Persistência:** Spring Boot com Spring Data JPA e H2 em arquivo permite salvar medições e alertas e consultá-los após reiniciar, sem exigir a instalação de um servidor de banco separado. Os limiares são configurações em memória e retornam aos padrões quando o programa é encerrado.
- **Previsões:** optou-se por cálculos simples de explicar: média das até cinco leituras mais recentes e extrapolação da diferença entre as duas últimas. O menu informa quando há menos de cinco leituras, pois uma amostra pequena reduz a confiança no resultado.
- **Alertas:** cada tipo de sensor possui seus próprios limiares demonstrativos para atenção, alerta e crítico. A previsão é comparada com esses valores e o registro informa o sensor, o valor previsto e o limite atingido. Os limites não são parâmetros oficiais e não devem ser usados para decisões reais sem avaliação especializada.
- **Organização e testes:** a Factory concentra a seleção da estratégia de previsão, enquanto o Singleton mantém os limiares usados na execução. As exceções personalizadas comunicam dados inválidos e indisponibilidade de fonte. Os testes verificam validações, cálculos e a emissão de alerta com repositório simulado.
- **Interface:** foi escolhido um menu de console com `Scanner`, suficiente para simular leituras, consultar o histórico, gerar previsões e verificar alertas sem criar uma API ou interface gráfica.

## Divisão de tarefas

| Integrante | Responsabilidade |
| --- | --- |
| Joao Henrique | Domínio, abstrações, sensores e exceções. |
| Henrique Boscoli | Persistência JPA, entidades, repositórios e histórico. |
| Tomazzo Canterucci | Previsões, alertas, menu de console, testes e README. |

## Reflexão crítica

Uma previsão não deve ser apresentada como confiável com pouco histórico: uma ou duas medições não descrevem uma tendência. Além disso, cada sensor observa apenas seu ponto; falhas de comunicação, localização e frequência das leituras limitam a representação da região inteira. Por isso, o sistema explica o valor previsto e o limite ultrapassado, mas deixa a decisão com as pessoas responsáveis, que devem considerar o contexto local e os protocolos de emergência.

Com menos de cinco leituras, o menu avisa que a base histórica é pequena. O sistema ainda apresenta o cálculo para fins didáticos, mas esse aviso impede que o número seja confundido com uma previsão confiável. Se não houver nenhuma leitura, ele informa que o histórico está vazio. Também seria necessário verificar a atualidade e o funcionamento dos sensores antes de usar informações em uma decisão real.

Os dados simulados não descrevem diferenças entre ruas, drenagem, topografia, ocupação ou condições de acesso. Um sensor isolado não representa toda a região e uma média pode esconder mudanças rápidas. A comparação das últimas leituras é uma extrapolação simples, que pode resultar em valores fora da faixa física. Chuva é apresentada em milímetros por leitura, nível do rio em metros e umidade em porcentagem; essas unidades e intervalos são convenções da simulação, sem calibração para uma estação real.

O alerta é um sinal para investigação, não uma ordem de evacuação ou prova de que ocorrerá um alagamento. Responsáveis devem combinar as leituras com dados oficiais, observações no território e protocolos locais, avaliando tanto falsos alarmes quanto riscos não detectados. Os limiares usados no exercício são ilustrativos, não substituem critérios técnicos estabelecidos para o local.

## Uso de IA

IA foi utilizada para gerar e organizar partes do código e da documentação, além de auxiliar na análise de erros, na configuração do projeto e na execução dos testes.

O resultado foi conferido por meio da compilação, dos testes automatizados e da execução do menu. Os limiares são valores demonstrativos, não critérios oficiais de risco; ficam em memória e retornam aos padrões ao reiniciar. Eles devem ser calibrados por especialistas antes de qualquer uso real.
