# Location

## Arquitetura
A arquitetura utilizada nesse projeto se baseia na MVVM + Clean Architecture, fazendo uso dos padrões Repository e Result. Essa robustez não se faz necessária quando temos um BFF(back for front) dedicado à aplicação, uma vez que toda a logica de negócio seria abstraída no backend, o que nos dá a liberdade para retirar a camada de negócio, permanescendo as camadas de data(Repository) e de apresentação(ViewModel e View).

### Injection
Foi utilizado o dagger como motor de injeção de dependencia, mas em um caso real, numa aplicação tão pequena não seria necessária, podendo utilizar um montor de injeção próprio ou mais simples de configurar, como o Koin por exemplo.

### Fotos:
As fotos não foram carregadas por meio de uma api de terceiros pois em tempo hábil toda a arquitetura já construida seria de certa forma prejudicada, uma vez que os links para as imagens já deveriam vir com os objetos location e location details recebidos.

### Padrões
**Os padrões utilizados para:**
- Nomes
- Empacotamento
- Comunicação entre camadas
- Testes
- ...

Podem ser observados no seguinte documento: [Padrões de desenvolvimento](https://hackmd.io/@roubertedgar/r1-oUxLu8)

