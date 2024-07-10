# Movie Manager

## Project under development 🚀

**In the near future, I plan to refactor my project to incorporate the Domain-Driven Design (DDD) approach.**

## Brief description
This project is a **comprehensive movie catalogue application**, designed to manage and analyze a collection of movies 
**stored in a JSON file**. Leveraging the power of Java and the **Spring framework**, the project offers **a wide range 
of functionalities** to sort, filter, and analyze movie data based on various criteria. 

Throughout the development of 
this application, best practices in software engineering were diligently followed. This includes adherence to the **SOLID 
principles**, comprehensive **documentation of methods**, and the **implementation of robust testing strategies** to 
ensure the application's reliability and maintainability.


## Features
- **Sorting movies by customizable criteria** (e.g., title, release year in ascending or descending order),
- **Finding movies by customizable criteria** (e.g. those released between a specific date range),
- **Grouping and counting movies** (for example, we can count how many movies have a specific genre),
- **Generating various types of statistics about movies** (for example: searching for directors' best and worst movies, 
or determining statistics on the movies' duration),
- **Sorting actors within movies**,
- **Checking in which movies specific actors starred**,
- **Finding films that are closest to the specified criterion** (e.g. average rating),
- **Finding films that match a specified criterion** (genre, production year range, etc.),
- **Searching for movies containing provided keywords**,
- **Generating and sharing detailed reports through email and PDFs**.


## Testing
The application includes both **unit and integration tests** to ensure reliability and functionality across all layers. 
Tests are written using **JUnit** and can be run with Maven by executing the _mvn test_ command.


## Documentation
**All methods and functionalities are thoroughly documented** in the code. For further details, please refer to 
the inline comments and documentation provided within each method in all service classes.


## Project Usage
Before running the application, please ensure that:

1. You added a json file with data about movies to the main directory.
2. You added an _application.properties_ file to the resources directory with the following properties:
    - _movies.data.filename_ - the name of the json file storing data about movies,
    - _validation.regex_ - the regex pattern for validating data about each movie,
     - _email.host_ - the host of the email server,
    - _email.port_ - the port of the email server,
    - _email.username_ - the username of the email account,
    - _email.password_ - the password of the email account,
    - _email.from_ - the email address from which the reports will be sent,
    - _tmdb.api.key_ - the API key for The Movie Database (TMDB) API.

To run the application, execute the _main_ method in the _App_ class.
