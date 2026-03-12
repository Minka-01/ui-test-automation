
## 🧰 Tech stack
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white) ![Selenium](https://img.shields.io/badge/-selenium-%43B02A?style=for-the-badge&logo=selenium&logoColor=white) ![Confluence](https://img.shields.io/badge/confluence-%23172BF4.svg?style=for-the-badge&logo=confluence&logoColor=white) ![Trello](https://img.shields.io/badge/Trello-%23026AA7.svg?style=for-the-badge&logo=Trello&logoColor=white) ![Jira](https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)

---

# 🔍 UI test automation project

This repository contains UI automation tests written in **Java** using **Selenium WebDriver** and **TestNG**.

The tests cover several user flows in the application, including product management, course configuration and editing package contents.

The project uses the **Page Object Model (POM)** pattern to keep test logic separated from page interactions.

---

## 🧩 Tools and libraries

- Java  
- Selenium WebDriver  
- TestNG  
- Maven  
- Page Object Model  
- IntelliJ IDEA  


---

## 🔎 What is tested

The automated tests cover scenarios such as:

- creating a physical product
- editing product configuration
- enabling product visibility
- opening product details
- adding a product to the cart
- managing packages
- changing the order of products in a package
- removing products from packages
- verifying drag & drop interactions

---

## 🗂 Project structure

```
src
 ├─ main
 │   └─ java
 │       └─ config
 │           └─ PropertiesReader.java
 │
 └─ test
     └─ java
         ├─ pages
         │   ├─ AdminBarComponent
         │   ├─ AdminMenuPage
         │   ├─ BasePage
         │   ├─ CartPage
         │   ├─ CoursesEditPage
         │   ├─ CoursesPage
         │   ├─ CoursesPopupPage
         │   ├─ DigitalProductsPage
         │   ├─ LoginPage
         │   ├─ MediaPage
         │   ├─ PackagesEditPage
         │   ├─ PackagesPage
         │   ├─ PaymentHistoryPage
         │   ├─ PhysicalProductsEditPage
         │   ├─ PhysicalProductsPage
         │   ├─ PhysicalProductsPopupPage
         │   ├─ ProductDetailsPage
         │   ├─ ReportsPage
         │   └─ ...
         │
         └─ tests
             ├─ AdminMenuTest
             ├─ CoursesPageTest
             ├─ PackagesPageTest
             ├─ PhysicalProductsPageTest
             └─ TestBase
```

---

## 🏗 Test structure

Tests are organized using the **Page Object Model**.

Each page of the application has its own class that contains the elements and actions related to that page.  
This helps keep tests readable and reduces duplicated code.

Example:

```
AdminMenuPage      → navigation in the admin panel
PackagesPage       → package list
PackagesEditPage   → editing package contents
```

---

## 🧪 Example automated scenario

One of the tests verifies changing the order of products inside a package.

Test flow:

1. Open the **Packages** section in the admin panel  
2. Select a package  
3. Open the **Package Contents** tab  
4. Change the order of two products using drag & drop  
5. Save changes  
6. Verify the order update  
7. Remove a product from the package  
8. Verify the new number of products  

Example fragment from the test:

```java
editPage.dragProduct(0, 1);

editPage.clickSaveButton();

boolean orderChanged = editPage.waitUntilOrderChanges(firstBefore);

Assert.assertTrue(orderChanged, "Order did not change after drag and save");
```

---

## 📋 Test scenarios

Examples of automated scenarios included in the project:

- creating a physical product
- enabling product visibility in the catalog
- opening product details
- adding a product to the cart
- editing package contents
- changing the order of products in a package
- removing a product from a package

---

## 📎 Notes

Running the tests requires access to the target application and valid user credentials.

Example test executions are shown in the attached recordings.

---

## 🧾 Example manual test scenarios

### Change order of products in a package

Steps:

1. Open the **Packages** section in the admin panel  
2. Select a package  
3. Go to the **Package Contents** tab  
4. Drag one product below another  
5. Save changes  

Expected result  
The order of products should be updated.

---

### Create a physical product

Steps:

1. Open the **Physical Products** section  
2. Click **Add Physical Product**  
3. Enter product name and price  
4. Click **Create**

Expected result  
The product appears in the product table.

---

### Add a variant to a course

Steps:

1. Open the **Courses** section  
2. Create a new course  
3. Click **Add Variant**  
4. Enter variant name and price  
5. Save the variant  

Expected result  
The variant is added to the course.
