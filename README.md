## [Java Katka ](https://github.com/mailtoharshgupta/java-kata-1)
Application that implements RESTful API for authors/books and magazines repository.
Goal of the app is to provide REST APIs for basic read and search operations on the  repository.

## Architecture

- **Bootstrap Loader** : this layer loads data from the file in the classpath and persists that data into the inmemory store.
- **Rest Controller** : This layer is responsible for exposing RESTful interface for all the API operations.

- **Service Layer** : This layer is responsible for performing any business logic to the resoucres. A request received from REST Controller layer is transferred to the service layer. Service layer interacts with the Data Access Layer to fetch the required data, applies any business logic applicable to finally send it back to Controller layer.

- **Data Access Layer** : This layer acts as an interface for fetching the requried data for Service Layer. This layer acts as an umbrella for any data source used for persisting the resources. As of now, we have used inmemory database for storing the data. We can always move to any other persistence layer like  MySQL, MongoDB or use a distrbuted cache to fetch the data, only the Data Access Layer will have to make changes, where as all the other layers will remain intact.

## Dependencies

- **Spring Boot** : Version - 1.5.10
- **JDK** : Version - 1.8
- **Swagger** : Version - 2.8.0
- **Lombok** : Version - 1.12.16
- **Apache Maven** : Version - 3.3.3

## Features

### API

- **GET(/api/get/)** : Used to get all the publications.
- **GET(/api/get/sort/title)** : Used to get all the publications sorted by their title.
- **POST(/api/get/author/{email})** : Used to get publications by author's email id
- **PUT(/api/get/isbn/{isbn})** : Used to get the publication identified by ISBN

### Web UI
ADue to shortage of time, I couldn't implement a web UI. But it would simply be calling the services exposed and rendering it on the UI.

### Documentation
The in depth documentation of the APIs can be found on the swagger link(http://localhost:8080/swagger-ui.html)


## Getting started

### Installing
To get the source, clone the git repository.
```sh
git clone https://github.com/mailtoharshgupta/java-kata-1
```
### Building
`mvn clean install`
## Running the app
Run the following command `java -jar target/java-kata-1-1-SNAPSHOT.jar`

## API Usage Quickstart
### Getting a list of all publications:
```sh
Request
curl -v 'http://localhost:8080/api/get/'

Response :
[
    {
        "description": "Starkoch Jamie Oliver war mit seinem VW-Bus in Italien unterwegs -- und hat allerlei kulinarische Souvenirs mitgebracht. Es lohnt sich, einen Blick in sein Gepäck zu werfen...",
        "title": "Genial italienisch",
        "isbn": "1024-5245-8584",
        "authors": [
            "null-lieblich@echocat.org",
            "null-walter@echocat.org",
            "null-rabe@echocat.org"
        ]
    },

    {
        "publishedAt": "12.12.2011",
        "title": "The Wine Connoisseurs",
        "isbn": "2547-8548-2541",
        "authors": [
            "null-walter@echocat.org"
        ]
    }
]
```

### Getting a list of all publications sorted by their title :
```sh
Request
curl -v 'http://localhost:8080/api/get/sort/title'

Response :
[
    {
        "publishedAt": "21.05.2011",
        "title": "Beautiful cooking",
        "isbn": "5454-5587-3210",
        "authors": [
            "null-walter@echocat.org"
        ]
    },
    {
        "publishedAt": "01.05.2012",
        "title": "Cooking for gourmets",
        "isbn": "2365-5632-7854",
        "authors": [
            "null-lieblich@echocat.org",
            "null-walter@echocat.org"
        ]
    }
]
```
### Get a Publication(Magazine/Book) identified by isbn
```sh
Request
curl -v 'http://localhost:8080/api/get/isbn/5454-5587-3210'

Response
{
    "publishedAt": "21.05.2011",
    "title": "Beautiful cooking",
    "isbn": "5454-5587-3210",
    "authors": [
        "null-walter@echocat.org"
    ]
}

```

### Getting a list of all publications by author's email id :
```sh
Request
curl -v 'http://localhost:8080/api/get/author/email/null-walter@echocat.org'

Response :
[
    {
        "publishedAt": "01.05.2012",
        "title": "Cooking for gourmets",
        "isbn": "2365-5632-7854",
        "authors": [
            "null-lieblich@echocat.org",
            "null-walter@echocat.org"
        ]
    },
    {
        "publishedAt": "12.12.2011",
        "title": "The Wine Connoisseurs",
        "isbn": "2547-8548-2541",
        "authors": [
            "null-walter@echocat.org"
        ]
    }
]
```
