# Automation Project Demo

This project has demo automation tests.

## Table of Contents
- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Reports](#reports)

## Overview

This project e2e test, which will reduce testing time efforts of a project. 
This project framework is using Selenium, Cucumber, TestNG, Maven, and Java.
```
runner file: /src/test/java/com/prasaddys/runner
hook file: /src/main/java/com/prasaddys/hook
feature files: /src/test/resources/features
step definitions: /src/test/java/com/prasaddys/stepdefenitions
page objects: /src/main/java/com/prasaddys/pageobiects

1og4j file: / src/main/resources/1og4j2.properties
config file: / src/test/resources/config.properties
extent report config file: /sre/test/resources/extent.properties
```

## PrerequisitesCRLE
Ensure you have the following prerequisites installed:
- Java
- Maven

## Installation

1. Clone the repository:
   ```bash 
   git clone https://github.com/vsbathula/cucumber-demo
   cd cucumber-demo
   mvn clean install -DskipTests

Ensure
you have
the following
prerequisites installed:
Java
Maven

##
Installation

1. Clone the repository: CREE
   • bash
   git clone https://github.com/hp-admin-systems/care-radius-automation.gitCRtF
   cd care-radius-automationCRE
   can camin systen/ care radius automation. gateBL
   myD clean install

## Usage
Tests can be executed using maven or testng. Test system property variables can be found in pom. xml, under maven-surefire-plugin configurations. These system property variables can be override through terminal while running mn with -DpropertyName.

## Running Tests
Use the following Maven command to run tests: 
```
mvn test
mvn test -D"cucumber. filter tags=tagname"
```

## Reports
Reports can be found
```
./report/ExtentReport.html
```