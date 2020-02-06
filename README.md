# Personal Manager - API and Front End

## Overview

### API Component
##### Personal Manager API offers endpoints for adding new Persons, Retrieving Stored Persons , Edit/Update exisisting details and Delete details.
---
##### The API endpoints are secured using JWT, Only users who are authorized can access the endpoints and make use of the same.
---
##### To Make use of the API, Users have to access an endpoint, providing credentials. Once the credentials are validated, a token will be sent back to them, which has to be set in headers for all subsequent requests while calling the API Endpoints
##### The token has a validity, and has to be renewed once it expires
---
##### When extednded further, API subscribers can register themselves to access the endpoints
---

### UI Component
##### Personal Manager UI Components provides easy accessible User Interfacet to perform all the above mentieond operations like, Add new Person, List all Person , Edit/Update, Delete
---
##### For easy accesing, the Authentication credentials have been hardcoded, This application can be extended by adding a login screen to retrieve and show data based on different user's authorization levels
---
##### UI Component is build using Angular and Material design,with minimal tweaking the app can be converted into resposive design and can be accessed in various devices like, iPad and Mobile other than conventional browsers
---
##### UI component can also be exteded to provide an admin console to view the registered subscribers, assign various auth levels to them and manage the same.

## Project structure


        Project
	    |
	    ├── API Component
	    ├── UI Component
	    └── Screens                            
	    

---
## TechStack
* SpingBoot
* Maven
* H2 In Memory Database
* Swagger
* Angular
* Node JS
* Material Design
