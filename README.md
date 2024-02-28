# Selenium-Spring-Cucumber Project

## Introduction

The project combines **Selenium WebDriver**, **REST-assured**, **Spring Boot**, and **Cucumber** to deliver a complete suite for automated testing. It segregates web browser automation and API testing into two distinct modules to enable detailed Behavior-Driven Development (BDD) tests. Interactive and detailed test reports are generated using **Allure**.

## Project Structure

* **Selenium Module**: Automates web browser interactions and testing.
* **REST-assured Module**: Defines and runs RESTful API tests easily.

## Getting Started

This section will guide you to set up the project in your local environment for development and testing.

### Prerequisites
* Java 8 or above
* Maven
* A Java supporting IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation
1. Clone the repository into your local machine.
2. Import the project into your IDE as a Maven project.
3. Check if all Maven dependencies specified in ``pom.xml`` file are downloaded and ready to use.

### Running Tests
* Navigate to the project directory in your terminal and execute the command: ``mvn clean test``.
* 
## Running Selenium Tests on Different Browsers
* You can configure the project to run Selenium tests on different browsers by running with following property: `mvn clean test -Dbrowser=firefox`
* Supported values are `chrome`, `firefox`.
* 
## Running Selenium Tests on Remote Grid
* To run Selenium tests on a remote grid, you should have docker installed. Run `docker-compose up` to start the grid.
* Run command: `mvn clean test -Dspring.profiles.active=remote -Dselenium.grid.url=http://localhost:4444/wd/hub -Dcucumber.filter.tags="@UITest" -Dthread.count=4 -Dbrowser=firefox`

### Running Tests with Tags
* To filter Cucumber scenarios by tags for selective test execution, use: ``mvn test -Dcucumber.filter.tags="@YourTag"``. Replace ``@YourTag`` with the appropriate tag(s) you wish to run.

### Running Tests on Multiple Threads
* To execute tests in parallel, configure the `maven-surefire-plugin` in your `pom.xml`. An example is:
  <property>
  <name>dataproviderthreadcount</name>
  <value>4</value>
  </property>
* Or, you can use the command line option: `mvn test thread.count=4`.
* Adjust the `threadCount` value based on the number of threads you wish to use.

### Generating Reports
* After executing the tests, generate and view an Allure report by running: `mvn allure:serve`. 
* Make sure you have allure installed.
* Additionally, you can use cluecumber reports using this command: `mvn cluecumber-report:reporting`.

## Directory Structure
* src/main/java - Contains the Spring Boot application and configurations for Selenium WebDriver and REST-assured.
* src/test/java - Contains test runners and step definitions for Cucumber tests, organized by module.
* src/test/resources/features - Contains the Cucumber feature files describing test scenarios, organized by module.

## Contributing
* Refer to `CONTRIBUTING.md` for details on our code of conduct, and the process for submitting pull requests.


## Authors
* Cristian-Robert Iosef

## License
The project is licensed under the MIT License - refer to the `LICENSE.md` file for more details.

## Notes
* The project is a work in progress and will be updated with new features and improvements over time.
