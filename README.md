# simplebankingapp
A simple Banking App with basic account Functionalities

## Running the application locally

1. USING CLI

Run the below command to run the application from cli

```shell
mvn spring-boot:run
```

2. IntelliJ

* Run simplebankingappApplication.main()



## Running the application using docker
1. Run `maven clean install`. 
2. Execute `docker-compose up`.


##Access h2 db console
1. open this link http://localhost:9001/h2-console
2. Enter jdbc url as `jdbc:h2:mem:demo`
3. username : `sa` 
4. password : `password`
