# AutomationTesting
Projeto utilizado para realizar testes automatizados à uma API RESTFul.

# Visão geral
O projeto é uma aplicação back-end com o objetivo de executar testes automatizados à API RESTFul: https://gorest.co.in/public/v2/todos

## Tecnologias

- [Spring Boot](https://projects.spring.io/spring-boot) é uma ferramenta que simplifica a configuração e execução de aplicações Java stand-alone,  com conceitos de dependências “starters”, auto configuração e servlet container embutidos é proporcionado uma grande produtividade desde o start-up da aplicação até sua ida a produção.

- [JUnit](https://junit.org/junit5/) é um framework que facilita o desenvolvimento e execução de testes unitários em código Java.

- [Rest Assured](https://rest-assured.io/) é uma ferramenta que nos permite realizar testes à APIs Restful de maneira simplificada.


# Setup da aplicação (local)

## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 17
Maven 3.9.6 
```


## Execute os tests
### A classe utilizada para executar os testes é: 
```
DevopsApplicationTests.java
```

Para executar os tests, execute o seguinte comando:
```
mvn clean test
```