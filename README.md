"# kenzan-employee-service" 

Clone all repos
Once cloned, run them in the following order

#kenzan-employee-commons
mvn install

#kenzan-eureka-server
mvn clean spring-boot:run

#kenzan-employee-service
mvn clean spring-boot:run

#kenzan-zuul-server
mvn clean spring-boot:run

Download the postman requests: https://www.getpostman.com/collections/f78e965e3d6cb24b7b34
Run the endpoints

#Annotations

- Inside import.sql you can insert any employee
- For the authentication request, is BasicAuth with user="root", and password="root".

