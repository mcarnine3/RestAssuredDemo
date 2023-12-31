# **RestAssuredDemo**
## Description
The purpose of this repository is to demonstrate test automation using the following:
>Gradle (Build Tool)
https://gradle.org/

>TestNG (Testing Engine)
https://testng.org/doc/

>RestAssured (Test Framework)
https://rest-assured.io/

>Groovy (Development Language)
https://groovy-lang.org/

>Allure Reports (Reporting)
https://docs.qameta.io/allure/

## Configuration
To configure environments, modes, baseUrl, API endpoints, and user properties modify the **config.json** file

To Configure Schemas create a **new JSON file** within the **resources/schemas** project directory for each endpoint using the endpoint location for the naming convention of the Schema file.

To Configure the Allure reports you may setup environment variables in the allure.properties file located within the **resources** project directory.

Categories can be created by defining a **JSON file** named **categories.json** and store this file with the **resources** project directory.