# Recipe App

This is a Spring Boot application that allows users to search for recipes based on various criteria, such as ingredients, cuisine, and difficulty level. Users can also add their own recipes to the database. The application is built using Kotlin with coroutines and follows the Clean Architecture principles.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17
- Gradle 6.x or higher
- H2 Database

### Installing

- Clone the repository

```
git clone https://github.com/Jonajor/recipe.git

cd recipe
```

```
gradle build
```

- Run the application

```
gradle bootRun
```


## Endpoints

- `GET /recipes`: Get a list of all recipes
- `GET /recipes/{id}`: Get a specific recipe by its id
- `POST /recipes`: Add a new recipe to the database
- `PUT /recipes/{id}`: Update an existing recipe by its id
- `DELETE /recipes/{id}`: Delete an existing recipe by its id

## Built With
- Spring Boot
- Kotlin
- Gradle Kotlin DSL
- H2 Database


## Authors

* **Jonathan** - *Initial work* - [GitHub](https://github.com/Jonajor)
