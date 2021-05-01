# rabbitmq-with-spring

- This is a simple project to illustrate how to work with rabbitMQ, Java and Spring.
- There are many ways to work with Exchanges in Rabbit, the approach chosen was Topic exchange.
- MessagingConfig.java is the most import class in this project, feel free to explorer it.


### first thing first you need to install rabbitMQ or run command below if you have docker-compose on your machine. (the file is provided in this repository)
```
docker-compose up -d
```
### RabbitMQ dashboard
> http://localhost:8088/

### to trigger process you need to make a post like bellow
```
curl --location --request POST 'http://localhost:8082/digital-bank/api/create' --header 'Content-Type: application/json' --data-raw '{"name": "edney roldao","accountID": "ancasfdasd"}'
```