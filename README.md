# What The Event
Um aplicativo de listagens de eventos e visualização dos detalhes do evento utilizando princípios de Clean Architecture com Jetpack Compose para construção da UI.

## Como o app está estruturado
O aplicativo é divido por 3 diretórios principais:
- Data: Contém o que é relevante para dados, como o acesso a API.
- Domain: Funciona como uma conexão, é uma camada responsável por tratar os dados e fornecer para a camada de apresentação, aqui podemos encontrar usecases que utilizam repositórios para acessar os dados na camada de data.
- Presentation: Contém a representação de UI, utilizando os dados obtidos pelas camadas anteriores.

## Testes
- Foi implementado alguns testes para componentes feito com jetpack compose, viewmodel, repositories, use cases etc.
