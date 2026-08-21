This is an Improved version of TestExecution project that I previously created. In this version I applied Page Object Model design pattern and added Parameterization using a excel file to store the testdata.

Below you can check more details about the Project:

# POMTestExecution

Automated Selenium test suite using the Page Object Model (POM) pattern, TestNG and Maven.

This project includes:
- Page Object Model-based page classes under `src/main/java/com/sdet/pages`
- Test classes under `src/test/java/com/sdet/testcases`
- Data-driven tests using an Excel file (`src/test/resources/excel/testdata.xlsx`)
- TestNG runner configuration at `src/test/runner/testng.xml` (parallel tests supported)
- ExtentReports integration for rich HTML test reports

---

## Prerequisites
- Java 11+ installed and JAVA_HOME set
- Maven 3.6+ installed
- Internet access for WebDriverManager to download browser drivers (or preinstalled drivers)
- Browsers installed for tests (Chrome, Firefox as used in the project)

Windows PowerShell (example commands below assume PowerShell and the project path `C:\seleniumTesting\POMTestExecution`).

---

## Quick setup

1. Open a PowerShell terminal.
2. Navigate to the project root:

```powershell
cd C:\seleniumTesting\POMTestExecution
```

3. Build the project and download dependencies:

```powershell
mvn clean compile -DskipTests
```

---

## Run tests

Run the full TestNG test suite (uses `src/test/runner/testng.xml`):

```powershell
mvn test
```

Run a single TestNG test class (example):

```powershell
mvn -Dtest=com.sdet.testcases.searchBox test
```

You can control parallel execution and thread-count in `src/test/runner/testng.xml`.

---

## Configuration and test data
- `src/test/resources/properties/Config.properties`  test site URL, timeouts, and other runtime config
- `src/test/resources/properties/OR.properties`  element locators (keys used by page objects)
- `src/test/resources/excel/testdata.xlsx`  data for data-driven tests

Tests in `src/test/java/com/sdet/testcases` usually call `SetUp(browser)` and obtain a WebDriver instance scoped to the current TestNG thread.

---

## Thread-safety and WebDriver handling

This project uses ThreadLocal to support parallel TestNG execution:
- `MainTest` maintains a `private static ThreadLocal<WebDriver> driverTL` and a `ThreadLocal<WebDriverWait> waitTL`.
- Tests call `SetUp(browser)` which sets the thread-local WebDriver and WebDriverWait.
- Page objects obtain the current thread's driver with `MainPage.getDriver()` (backed by a ThreadLocal in `MainPage`).

Important notes:
- `MainTest.tearDown()` quits the driver and calls `driverTL.remove()` and `waitTL.remove()` to avoid ThreadLocal leaks.

---

## Reports and screenshots

- ExtentReports output is created in `target/surefire-reports` (configured by `ExtentManager` / `ExtentListeners`).
- Screenshots captured on failure are placed in `target/surefire-reports` and referenced by the Extent report.

---

## Common troubleshooting
- If tests cannot launch a browser: ensure browser is installed and WebDriverManager can download drivers (or set local driver path).
- If locators fail, verify keys in `OR.properties` match the values used in page objects (keys end with `_X`, `_C`, or `_ID`).
- If tests interfere with each other in parallel runs, make sure each test calls `SetUp(...)` before interacting with page objects and that no code uses a static WebDriver.


---

## Contributing
1. Fork the repo or work on a branch.
2. Ensure new tests is thread-safe for parallel TestNG execution.
3. Run `mvn test` locally and verify report output in `target/surefire-reports`.

