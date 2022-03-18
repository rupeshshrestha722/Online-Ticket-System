# Online Ticket System Backend REST API

## Required Softwares
* Java 8
* MySQL Database 5.7

## Configuration
The configuration can be changed in `./src/main/resources/application.properties`(for deployment). The following variables need to be configured properly.

* `spring.datasource.url`: the database connection url
* `spring.datasource.username`: the username for the database
* `spring.datasource.password`: the password for the database

## Initialize

Create the first user by running following command in the database
```sql
insert  into `users`(`id`,`email`,`first_name`,`last_name`,`password`,`username`,`role`)values (2,'rupesh@gmail.com','Rupesh','Shrestha','$2y$10$Ksf4mVvSHraszPdDxhXhJuibNj6Q6v/ou01CH0IZMZl5CtaTmLnvC','rupesh','USER');
```
username: rupesh
password: password


## Local Deployment
* Create a new database
* configure the application see `Configuration` section above
* Run following commands
    ```bash
    mvnw clean spring-boot:run -DskipTests
    ```

* The API will be available at `http://localhost:8080/api`
