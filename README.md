**Author: Amit Kumar Das**

**Test Automation Consultant/Framework Architect**


**License**

This project is intended for learning, demonstration, and portfolio purposes.

## Swagger Petstore REST Assured API Automation Framework (Java 17 + TestNG)

**portfolio-ready** API automation framework built on:
**REST Assured** (HTTP client + assertions)
**TestNG** (suite orchestration)
**Clean framework structure** (core client, config, utilities, tests)

Target API: **Swagger Petstore (OpenAPI demo)** using the **v3** endpoint.

Default base URL:
- `https://petstore3.swagger.io/api/v3`

> Note: Public demos can be flaky (rate limits / resets). Tests are written to be resilient and to clean up test data.

---

## Run

```bash
mvn clean test
```

---

## Reporting (Allure + Extent)

This project generates **two reports** on every run:

### 1) Extent HTML report (zero extra installs)
- Output: `target/extent-report/ExtentReport.html`
- Open the HTML file in your browser after `mvn test`.

### 2) Allure report
- Test results output: `target/allure-results`

If you have Allure installed, you can view the report with:

```bash
mvn allure:serve
```

Or generate the static report into `target/site/allure-maven`:

```bash
mvn allure:report
```

### Override base URL (optional)
```bash
mvn clean test -DbaseUrl=https://petstore3.swagger.io/api/v3
```

---

## Project Structure

```
src/test/java
  └── com.amit.portfolio.petstore
      ├── core
      │   ├── ApiClient.java
      │   └── Config.java
      ├── util
      │   ├── AssertUtil.java
      │   └── TestData.java
      └── tests
          ├── PetCrudTests.java
          └── StoreInventoryTests.java
```

---

## Endpoints Covered (Examples)

- `POST /pet`
- `GET /pet/{petId}`
- `PUT /pet`
- `DELETE /pet/{petId}`
- `GET /pet/findByStatus?status=available` (stable public demo endpoint)


---

## Additional Test Coverage

### Pet API
- CRUD flow (create → read → update → delete → verify 404)
- Negative checks (non-existing pet, invalid bodies)
- Search by status: `GET /pet/findByStatus?status=available`

### Store API
- Order flow (place order → get → delete → verify 404)
- Negative check for invalid order id

### User API
- User CRUD (create → get → update → delete → verify 404)
- Login/Logout smoke (demo behavior can vary)

---

## Debug Logging (Optional)

Enable full request/response logging:
```bash
mvn clean test -DapiLog=true
```


Additional endpoints tested:
- `POST /store/order`
- `GET /store/order/{orderId}`
- `DELETE /store/order/{orderId}`
- `POST /user`
- `GET /user/{username}`
- `PUT /user/{username}`
- `DELETE /user/{username}`
- `GET /user/login`
- `GET /user/logout`
