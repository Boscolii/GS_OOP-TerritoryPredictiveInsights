# Territory Predictive Insights

Projeto da Global Solution de POO no recorte de **clima local**. Sensores simulados de chuva, nível do rio e umidade do solo registram leituras, fazem previsões simples e emitem alertas para apoiar a prevenção de alagamentos.

## Como executar

Use Java 21 e execute `./mvnw.cmd spring-boot:run` no Windows. O menu é de console. O banco H2 fica no diretório `data/territorio`, preservando histórico entre execuções. Para rodar os testes: `./mvnw.cmd test`.

## Requisitos atendidos

- Abstração `ComponenteTerritorial`, herança em `EstacaoMonitoramento`, interface `Sensor` com três implementações e estratégias polimórficas de previsão.
- Exceções próprias para dados inválidos e fonte indisponível, com tratamento no menu.
- Entidades JPA `Medicao` e `Alerta`, repositórios Spring Data JPA e histórico persistido.
- Factory (`FabricaPrevisao`) para selecionar estratégias sem `if` espalhado e Singleton (`ConfiguracaoSistema`) para limiares.
- Oito testes JUnit, incluindo `assertThrows` e Mockito para verificar a emissão de alerta sem banco.

## Decisões da equipe

- Foi escolhido o cenário de clima local porque chuva, nível do rio e umidade do solo possibilitam simular risco de alagamento com sensores coerentes.
- Foi utilizado Spring Boot com Spring Data JPA e H2 em arquivo. Assim, o histórico permanece entre execuções e a correção pode ser feita sem instalar um banco externo.
- As previsões foram mantidas simples e explicáveis: média móvel das cinco leituras mais recentes e comparação entre as duas últimas leituras.
- Alertas são gerados somente quando a previsão ultrapassa um limiar configurado. Eles apoiam a decisão humana e não definem ações por conta própria.

## Divisão de tarefas

| Integrante | Responsabilidade |
| --- | --- |
| Joao Henrique | Domínio, abstrações, sensores e exceções. |
| Henrique Boscoli | Persistência JPA, entidades, repositórios e histórico. |
| Tomazzo Canterucci | Previsões, alertas, menu de console, testes e README. |

## Reflexão crítica

Uma previsão não deve ser apresentada como confiável com pouco histórico: uma ou duas medições não descrevem uma tendência. Além disso, cada sensor observa apenas seu ponto; falhas de comunicação, localização e frequência das leituras limitam a representação da região inteira. Por isso, o sistema explica o valor previsto e o limite ultrapassado, mas deixa a decisão com as pessoas responsáveis, que devem considerar o contexto local e os protocolos de emergência.

## Uso de IA

IA foi utilizada para gerar e organizar partes do código e da documentação, além de auxiliar na análise de erros, na configuração do projeto e na execução dos testes.

O resultado foi conferido por meio da compilação, dos testes automatizados e da execução do menu. Os limiares são valores demonstrativos, não critérios oficiais de risco; ficam em memória e retornam aos padrões ao reiniciar. Eles devem ser calibrados por especialistas antes de qualquer uso real.
