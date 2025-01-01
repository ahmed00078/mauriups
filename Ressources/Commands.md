## Maven Commands

```
# Build and run the application
mvn spring-boot:run

# Package the application into a JAR file
mvn package

# Clean the build directory
mvn clean

# Install the JAR in your local Maven repository
mvn install

# Run tests
mvn test

# Generate JavaDoc
mvn javadoc:jar

# Generate source code
mvn source:jar
```

## Spring Boot Commands

```
# Start the application
java -jar target/your-app.jar

# Stop the application (if running as a service)
sudo systemctl stop your-app.service

# Restart the application 
sudo systemctl restart your-app.service

# Check logs
tail -f logs/application.log
```

## React Commands

```
# Create a new React app
npx create-react-app my-app

# Start the React development server
npm start

# Build the React app for production
npm run build

# Test the React app locally
npm test

# Eject from npm scripts to package.json
npm run eject
```

## Common Commands

```
# Navigate to directory
cd path/to/directory

# View project dependencies
mvn dependency:tree

# Update project dependencies
mvn dependency:sources

# Generate API documentation (Springfox)
mvn install

# Run integration tests
mvn test -Dtest=IntegrationTest

# Run specific test class
mvn test -Dtest=TestClass

# Run tests in parallel
mvn test -Dtest.to.run=TestClass1,TestClass2

# Run tests with coverage report
mvn test -Dtest=TestClass -Dsurefire.useFile=false

# Run tests and generate JUnit XML results
mvn test -Dtest=TestClass -Dsurefire.useFile=true

# Run tests and generate Cobertura XML coverage report
mvn test -Dtest=TestClass -Dcobertura.format=xml

# Run tests and generate JaCoCo XML coverage report
mvn test -Dtest=TestClass -Djacoco.itReportOutputDirectory=target/site/jacoco-it
```
