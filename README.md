# Location

## Arquitetura

A arquitetura utilizada nesse projeto se baseia na MVVM + Clean Architecture, fazendo uso dos
padrões Repository e Result. Essa robustez não se faz necessária quando temos um BFF(back for front)
dedicado à aplicação, uma vez que toda a logica de negócio seria abstraída no backend, o que nos dá
a liberdade para retirar a camada de negócio, permanescendo as camadas de data(Repository) e de
apresentação(ViewModel e View).

### Injection

Foi utilizado o dagger como motor de injeção de dependencia, mas em um caso real, numa aplicação tão
pequena não seria necessária, podendo utilizar um montor de injeção próprio ou mais simples de
configurar, como o Koin por exemplo.

### Fotos:

As fotos não foram carregadas por meio de uma api de terceiros pois em tempo hábil toda a
arquitetura já construida seria de certa forma prejudicada, uma vez que os links para as imagens já
deveriam vir com os objetos location e location details recebidos.

### Padrões

**Os padrões utilizados para:**

- Nomes
- Empacotamento
- Comunicação entre camadas
- Testes
- ...

![1172125_android_emoji_mobile_mood_robot_icon (1)](https://github.com/justbiped/uaisplit/assets/13192434/93fa5469-eac2-4f54-8f86-f511257b876b)

Podem ser observados no seguinte
documento: [Padrões de desenvolvimento](https://hackmd.io/@roubertedgar/r1-oUxLu8)

### Modulos

O modulo core não foi adicionado a um modulo lib por se tratar de um projeto simples, mas idealmente
essa estrutura seria extraída, de modo a servir a outros produtos que futuramente poderiam ser
desenvolvidos.

Há também o modulo buildSrc para gerenciamento das dependencias do app, que juntamente com arquivos
gradle kotlin disponibiliza autocomplete além de facilitar a manutenção e criação de tasks de build,
já que esse arquivo utiliza a mesma linguagem do projeto, diminuíndo a carga cognitiva.

### Tests

Apenas testes unitários foram criados, mas a cobertura não está muito alta.
Os testes de espresso (teste de caixa cinza) não foram criados pois demandaria um pouco mais de
tempo de configuração.
Não há testes end to end, nesse caso poderia ser feito uso do Appium ou UIAutomator.

Para rodar os testes unitários, na pasta root do projeto, digite `./gradlew com.hotmart.test.test`
