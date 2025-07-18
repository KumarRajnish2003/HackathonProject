# HackathonProject

# Identify New Bikes - Automation Framework


[Project Link - https://github.com/KumarRajnish2003/HackathonProject.git](https://github.com/KumarRajnish2003/HackathonProject.git)

## Table of Contents

- [Introduction](#introduction)
- [Problem Statement](#problem-statement)
- [Key Automation Scope](#key-automation-scope)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Cloning the Repository](#cloning-the-repository)
  - [Setting up the Environment](#setting-up-the-environment)
  - [Running the Tests](#running-the-tests)
- [Framework Structure](#framework-structure)
- [Configuration](#configuration)
- [Reporting](#reporting)
- [Data-Driven Testing](#data-driven-testing)
- [Page Object Model (POM)](#page-object-model-pom)

## Introduction

This repository hosts a robust and scalable Hybrid Automation Framework built using Selenium WebDriver with Java. It is designed to automate specific web scraping and interaction tasks on automotive classifieds and new vehicle websites, specifically focusing on identifying upcoming bikes and extracting information about used cars. The framework adheres to best practices like the Page Object Model (POM) and supports data-driven testing for enhanced maintainability and reusability.

## Problem Statement

The core objectives of this automation project are derived from the following problem statements:

1.  **Identify Upcoming Bikes:**
    * Display details of upcoming bikes in India, including bike name, price, and expected launch date.
    * Filter results to include only bikes manufactured by **Honda**.
    * Further filter results to include only bikes with a price less than **4 Lacs**.
    * *(Suggested site: zigwheels.com, but adaptable to any legitimate vehicle classifieds site.)*

2.  **Extract Popular Used Car Models:**
    * For used cars in Chennai, extract all the popular models into a list.
    * Display the extracted list of popular models.
    * *(Suggested site: zigwheels.com, but adaptable to any legitimate vehicle classifieds site.)*

3.  **Invalid Login & Error Message Capture (Google Login):**
    * Attempt to log in using Google authentication (if available on the suggested site).
    * Provide invalid account details during the login attempt.
    * Capture and display the error message generated due to invalid credentials.
    * *(Suggested site: zigwheels.com, but adaptable to any legitimate site offering Google login.)*

## Key Automation Scope

The framework addresses the following key automation challenges and functionalities:

* **Handling Windows & Frames:** Efficiently navigating and interacting with multiple browser windows and iframes.
* **Filling Simple Forms:** Automating the input of data into various web forms.
* **Capturing Warning Messages:** Identifying and extracting warning/error messages displayed on the UI.
* **Extracting Menu Items from Frames & Storing in Collections:** Retrieving data from complex UI structures like menu items within frames and storing them for further processing or validation.
* **Navigating Back to Home Page:** Ensuring reliable navigation back to the application's starting point for subsequent test scenarios.

## Features

* **Hybrid Framework:** Blends Keyword-Driven, Data-Driven, and Page Object Model (POM) approaches for comprehensive test coverage.
* **Selenium WebDriver:** Core library for automating browser interactions.
* **TestNG:** Robust testing framework for test execution, parallel testing, and detailed reporting.
* **Page Object Model (POM):** Ensures highly maintainable and readable tests by abstracting UI elements and interactions.
* **Apache POI:** Facilitates data-driven testing by reading test data from external Excel files (`.xlsx`).
* **Maven:** Manages project dependencies, builds, and standardizes the project structure.
* **Comprehensive Reporting:** Generates detailed and user-friendly test execution reports (e.g., TestNG default, ExtentReports if integrated).
* **Configurable Environment:** Easy to switch between different environments or configurations using external property files.
* **Cross-Browser Testing:** Designed to support testing across various browsers (Chrome, Firefox, Edge, etc.).
* **Screenshot on Failure:** Automatically captures screenshots upon test failure for effective debugging.
* **Robust Error Handling:** Includes mechanisms to provide clear and actionable error messages.
* **Logging:** Utilizes a dedicated logging framework (e.g., Log4j2) for detailed execution logs.

## Technologies Used

* **Java:** Programming Language (JDK 8+)
* **Selenium WebDriver:** Automation Library (e.g., `4.33.0`)
* **TestNG:** Testing Framework (e.g., `7.9.0`)
* **Maven:** Build Automation Tool (e.g., `4.0.0`)
* **Apache POI:** For Excel Data Handling (e.g., `5.2.5`)
* **ExtentReports 5.1.2**
* **Log4j2 2.25.0]**

## Prerequisites

Before you can run this project, ensure you have the following installed and configured:

* **Java Development Kit (JDK) 8 or higher:**
    * [Download JDK](https://www.oracle.com/java/technologies/downloads/)
    * Ensure `JAVA_HOME` environment variable is set correctly.
* **Apache Maven:**
    * [Download Maven](https://maven.apache.org/download.cgi)
    * Ensure `M2_HOME` is set and Maven's `bin` directory is added to your system's `PATH` environment variable.
* **Web Browser:** Chrome, Firefox, or Edge.
* **WebDriver Executables:** Download the appropriate WebDriver executable for your chosen browser(s).
    * [ChromeDriver](https://chromedriver.chromium.org/downloads)
    * [GeckoDriver (Firefox)](https://github.com/mozilla/geckodriver/releases)
    * [MSEdgeDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)
    * **Recommendation:** Place these WebDriver executables in a designated `src/main/resources/drivers` folder within your project or ensure their path is correctly configured in `config.properties`.

## Getting Started

Follow these steps to set up and run the automation tests on your local machine.

### Cloning the Repository

```bash
git clone https://github.com/KumarRajnish2003/HackathonProject.git

cd [your-repo-name]
```
## Setting up the Environment

To get started with this project, follow these steps:

### 1. Open in IDE

Import the cloned project into your preferred Integrated Development Environment (IDE) such as **IntelliJ IDEA** or **Eclipse**. Import it as a **Maven project**.

### 2. Maven Dependencies

Your IDE should automatically download all necessary Maven dependencies defined in the `pom.xml` file. If not, manually trigger a Maven build or update by running the following command in your project's root directory:

```bash
mvn clean install
```
## Running the tests

Run All Tests: Navigate to src/test/resources/testSuites/testng.xml. Right-click on the testng.xml file and select "Run 'testng.xml'". This will execute all tests defined within that suite.

Run Individual Tests: You can also right-click on individual test classes (.java files in src/test/java/com/yourpackage/test) or specific @Test methods to run them in isolation.

### Framework Structure

The project follows a well-organized structure to enhance maintainability and scalability:

ZigWhells/  
├── src  
│   ├── test  
│       ├── java   
│       │   ├── pageObjects/                    
│       │   │   ├── BasePage.java              
│       │   │   ├── GoogleAuthenticationPage.java  
│       │   │   ├── HomePage.java  
│       │   │   ├── HondaUpcomingBikePage.java  
│       │   │   ├── UpcomingBikePage.java  
│       │   │   └── UsedCarPage.java  
│       │   ├── testBase/                
│       │   │   └── BaseClass.java            
│       │   ├── testCases/                  
│       │   │   ├── TS_Login.java             
│       │   │   ├── TS_UpcomingHondaBike.java   
│       │   │   └── TS_UsedCar.java            
│       │   └── utilities/                 
│       │       └── ExtentReportManager.java  
│       ├── resources  
│       │   ├── config.properties             
│       │   └── log4j2.xml                    
│
├── JRE System Library [jdk-24]             
├── Maven Dependencies                      
├── logs/                                  
│   └── automation.log  
├── reports/                                  
│   └── Test-Report-2025.07.16.14.49.29.html  
├── screenshots/                             
│   └── ...  
├── pom.xml                                   
├── target   
├── test-output  
├── master.xml    
├── pom.xml   
└── README.md                                 


### Configuration

All framework-level configurations are managed through the src/main/resources/config.properties file. This allows for easy modification of settings without changing code.

Key configurable properties:

browser: chrome, firefox, edge (determines which browser WebDriver to use)

base.url: The base URL of the primary application under test (e.g., https://www.zigwheels.com/)

headless: true or false (enables/disables headless browser execution)

implicit.wait: Global implicit wait timeout in seconds.

explicit.wait: Global explicit wait timeout in seconds.

screenshot.path: Relative path where screenshots on failure will be saved (e.g., ./target/screenshots/).

report.path: Relative path where test reports will be generated (e.g., ./target/reports/).

webdriver.chrome.driver: Absolute or relative path to the ChromeDriver executable (e.g., src/main/resources/drivers/chromedriver.exe).

webdriver.firefox.driver: Absolute or relative path to the GeckoDriver executable.

webdriver.edge.driver: Absolute or relative path to the MSEdgeDriver executable.

### Reporting

The framework is configured to generate comprehensive test reports for clear visibility into test execution results.

Default TestNG Reports: After every test run, TestNG generates basic HTML reports located in target/surefire-reports/ and target/test-output/. The index.html file in target/test-output/ provides a summary of the test suite execution.

### Data-Driven Testing

The framework supports data-driven testing using Apache POI to read test data from Excel files.

Location: Test data files (e.g., LoginData.xlsx) are typically stored in the src/test/resources/testData/ directory.

Implementation: Test methods leverage TestNG's @DataProvider annotation to fetch data rows from specific sheets within the Excel file. This allows for executing the same test logic with multiple sets of input data, for example, multiple invalid login attempts.

### Page Object Model (POM)

The Page Object Model (POM) design pattern is critically implemented to ensure maintainability and readability of the test suite.

Structure: Each distinct web page or significant UI component of the application has a corresponding Java class located in the src/main/java/com/yourpackage/pages directory.

Elements: These page classes contain WebElements representing the UI components on that page (e.g., text fields, buttons, links).

Methods: Page classes encapsulate the interactions (methods) with those elements (e.g., enterUsername(), clickLoginButton(), getErrorMessage()).

Benefits: This separation of UI elements and test logic drastically reduces code duplication, improves readability, and makes tests highly resilient to changes in the application's UI. If a locator changes, only the page object needs to be updated, not every test case that uses that element.
