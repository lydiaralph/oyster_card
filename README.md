Written by [Lydia Ralph](https://www.linkedin.com/in/lydiaralph/)

Created for HMCTS job application

### Tech stack
- Frontend: ReactJS with Vite template, npm
- Backend: Java (Spring Boot)
- Database: PostgreSQL, within Docker
- Build tool: Gradle
- Integration tests: Cucumber, H2

### Build

``` 
# Fetch Go dependencies and compile
./gradlew build

# Fetch npm dependencies and compile
npm install --prefix api/src/main/client/ 
 
# Start the database
docker compose up -d 

# Start the application
./gradlew bootRun 
```

This will start up the application at http://localhost:8080/

### Integration testing
Not set up properly for running with Gradle. Can be run through IDE. 

## Development

### Frontend

To recompile JSX changes: `npm run watch` in client dir. 

### Awaiting development:

Implemented bus-related functionality.

Display response returned from API on UI.

Tidy up gradle build files. 

Select stations from dropdown list fetched from DB

Styling for Main Content section of UI

Error screen for UI

Secure log in for UI






