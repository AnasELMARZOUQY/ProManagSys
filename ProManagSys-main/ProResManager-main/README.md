# ProResManager
A comprehensive web-based project resource management system, powered by Angular and Spring Boot, designed to streamline project planning, resource allocation, and task assignments.


# Getting Started

## 1 Clone the repository
` git clone https://github.com/MaramDjebbi/ProResManager.git `

## 2 Install the dependencies

### For the frontend:
` cd Frontend `

` npm install `


### For the backend:
` cd Backend `

` mvn clean install `


### Running the Project
Start the backend server
` cd backend `

` mvn spring-boot:run `

### Start the frontend application
` cd frontend `

` ng build `

` ng serve `

[ The application will be available at ] ( http://localhost:4200 )


# Features
The application includes the following features:

* A list of projects, resources, sessions, affectatations
* The ability to read, create, edit, and delete all the entities
* The ability to register, login and logout 

a newly created user will have a "Manager" role by default.

There will be a default created user with an "Admin" role 
You can log in with the admin user with the following credentials (username: admin123  password: admin@pass)
