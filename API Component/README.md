# Person Manager API

## Overview

Here we have a single springboot application to handle both Perons API and Authntication part.
We can create the components as seperate microservices when we extend the API and build further
    
## Build and Run

Maven has been used to build and pack the artifact

### To run from an IDE 

`Clone/Import the repo and can be run as a Spring boot App`

### To run using Maven:

`mvn clean package install`

`mvn spring-boot:run`
			                    
### To run as a standalone jar from command line : 

`java -jar target/personalmanager-1.0.0-SNAPSHOT.jar`


## API End Points from Swagger

## Swagger Home Screen :
![Swagger Screen](/Screens/swaggerUI.png?raw=true "Swagger")

## Person controller :
![Swagger Screen](/Screens/personcntrl.png?raw=true "Swagger")

## User/Auth controller :
![Swagger Screen](/Screens/authCntrl.png?raw=true "Swagger")

## Model Structure :
![Swagger Screen](/Screens/model.png?raw=true "Swagger")

