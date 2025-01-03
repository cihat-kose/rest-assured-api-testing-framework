
# RestAssured API Testing Framework

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![RestAssured](https://img.shields.io/badge/RestAssured-6DB33F?style=for-the-badge&logo=java&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF7300?style=for-the-badge&logo=testng&logoColor=white)
![Hamcrest](https://img.shields.io/badge/Hamcrest-655c83?style=for-the-badge)
![Jackson](https://img.shields.io/badge/Jackson-ff9933?style=for-the-badge)
![JavaFaker](https://img.shields.io/badge/JavaFaker-3b5b3f?style=for-the-badge)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Installation](#installation)
4. [Configuration](#configuration)
5. [Usage](#usage)
6. [Folder Structure](#folder-structure)
7. [Dependencies](#dependencies)
8. [Reporting and Logs](#reporting-and-logs)
9. [Contributing](#contributing)
10. [License](#license)

## Project Overview

The **rest-assured-api-testing-framework** is a Java-based API testing framework designed to test REST APIs with RestAssured, TestNG, and Hamcrest. This framework supports CRUD operations on multiple API endpoints, with comprehensive logging and assertions for validation. It includes support for JSON serialization/deserialization via Jackson and data randomization with JavaFaker.

GitHub Repository: [rest-assured-api-testing-framework](https://github.com/cihat-kose/rest-assured-api-testing-framework.git)

### Tested APIs

1. **GoRest API**: A public API offering endpoints for user and comment management. Allows testing of CRUD operations on users and comments. URL: https://gorest.co.in/public/v2
2. **Campus API**: A simulated API for managing locations like countries. This API is primarily used for testing authentication and resource management scenarios. URL: [https://gorest.co.in/public/v2](https://test.mersys.io/)
3. **Zippopotam.us API**: A public API providing location information based on postal codes, commonly used for validating data retrieval. URL: http://api.zippopotam.us
4. **ReqRes API**: A testing API that supports user-related operations and is used to validate request creation and response handling. URL: https://reqres.in/api

## Features

- **API Testing with RestAssured**: Supports `GET`, `POST`, `PUT`, and `DELETE` requests.
- **POJO Modeling**: Models API responses using POJO classes for deserialization.
- **Behavior-Driven Development**: Uses TestNG annotations for structured and readable test scenarios.
- **Data Randomization**: Utilizes JavaFaker for generating random data, enhancing test variety.
- **Custom Request Specifications**: Leverages `RequestSpecification` and `ResponseSpecification` for reusable configurations.
- **Comprehensive Logging**: Logs detailed information for requests and responses.
- **Dependency Injection**: Provides flexibility with dependencies, managed through Maven.

### Scenarios Covered
- **API Key Authentication**: Tests API key-secured endpoints with headers.
- **CRUD Operations**:
  - **Country Management**: Creates, updates, and deletes countries in the Campus API.
  - **User Management**: Handles user creation, retrieval, updating, and deletion in the GoRest API.
- **Assertions with Hamcrest**: Validates responses with assertions on fields like status codes, body content, and more.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/cihat-kose/rest-assured-api-testing-framework.git
   ```
2. **Import the Project in IntelliJ IDEA**:
   - Open IntelliJ IDEA.
   - Select **File > Open** and choose the project folder.
   - Ensure Maven auto-import is enabled to download dependencies.

3. **Install Plugins** (if required):
   - **TestNG** and **RestAssured** support plugins in IntelliJ IDEA for enhanced IDE integration.

## Configuration

- **TestNG.xml**: Contains configurations for TestNG test execution.
- **pom.xml**: Manages dependencies and configurations for the framework. 

## Usage

### Running Tests

To run tests, use Maven or execute directly from IntelliJ IDEA.

#### Maven
```bash
mvn clean test
```

#### IntelliJ IDEA
- Right-click on `TestNG.xml` and select **Run** to execute all tests.
- Alternatively, right-click any test class or method to run specific tests.

### Key Test Scenarios

1. **Country Test**:
   - Located in `campus._09_CountryTest`.
   - Covers country creation, duplicate check, update, and deletion.

2. **User Management in GoRest**:
   - Located in `goRest._07_GoRestUsersTest`.
   - Tests user creation, retrieval by ID, update, and deletion.

3. **Comments API Test**:
   - Located in `goRest._08_GoRestCommentTest`.
   - Manages comments by creating, retrieving, updating, and deleting comments.

4. **API Key Test**:
   - Located in `apiKeyTest.APIKeyTest`.
   - Demonstrates how to authenticate requests using API keys.

## HTTP Status Codes

This project commonly encounters a variety of HTTP status codes in responses. Below is a summary of some frequently used status codes and their meanings:

![HTTP Status Codes](./images/HTTP_Status_Codes.png)

## Folder Structure

```plaintext
cihat-kose/rest-assured-api-testing-framework/
│
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       ├── java/
│       │   ├── apiKeyTest/           # API Key authentication tests
│       │   │   └── APIKeyTest.java
│       │   ├── campus/               # Campus-related API tests
│       │   │   ├── _09_CountryTest.java
│       │   │   └── Country.java
│       │   ├── goRest/               # GoRest API tests
│       │   │   ├── _07_GoRestUsersTest.java
│       │   │   └── _08_GoRestCommentTest.java
│       │   ├── model/                # POJO models for JSON data
│       │   │   ├── Location.java
│       │   │   ├── Place.java
│       │   │   ├── ToDo.java
│       │   │   └── User.java
│       └── resources/
│           └── TestNG.xml            # TestNG suite configuration
├── target/                           # Build output
├── images/                           # Directory for project images
│   └── HTTP_Status_Codes.png         # HTTP status codes image
├── pom.xml                           # Maven configuration file
└── README.md                         # Project README
```

## Dependencies

Dependencies are managed in `pom.xml`. Key dependencies include:

- **RestAssured**: For making HTTP requests to test APIs.
- **TestNG**: For organizing and managing test execution.
- **Hamcrest**: For enhanced assertions in test validations.
- **Jackson**: For JSON serialization and deserialization.
- **JavaFaker**: For generating random test data.
- **SLF4J (Simple Logging Facade for Java)**: For logging support.

## Reporting and Logs

This framework logs API requests and responses, which can be useful for debugging. Logs include:

- **Request and Response Bodies**: Automatically logged for all test methods.
- **Status Codes and Headers**: Available in logs to confirm correct responses.

Test results and logs are saved in the `target` directory after test execution.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.
