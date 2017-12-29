**Excel Parsing Microservice using Spring Boot and Spring Integration**

This microservice contains one main module :

1. **geographical-microservice** - 
	
	1. This module reads and writes the result to the database.
	2. It runs on port of 9090.
	3. The main class to start the module is GeographicalApplication.java.
	4. Spring Integration is used to parse the file asynchronously.

The other two modules :

**geographical-api** : contains the dtos.

**geographical-model** : contains the entities.
 
**Database** :

The database used is In Memory Database(H2). You can also use PostGreSql 10. Please ensure you have it installed on the system and the relevant database and schema is created. You can find the database and the schema in the application properties spring.datasource.url property.

**Basic Authentication** :

Basic Authentication is supported. The credentials are 

**Username** : user

**Password** : abcd1234

**Base File Path** : 

The base file path property is present in the application.properties of the geographical-microservice module in the resources folder of the test and main.
You can change the file path property accordingly.

**Integration Tests** :

For running the Integration Tests please ensure that the sample geodata.xls file is in the base file path. You can find the sample file in the test/resources folder of the geographical-microservice module.

**Endpoints Test** :

You can test the application REST endpoints through Postman.
Please install Postman and login using the below credentials and find the relevant endpoints in the Excel Microservice :

Username : cheetah198816

Password : abcd1234
