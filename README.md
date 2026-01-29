# Дипломный проект. Задание 3: UI тесты Stellar Burgers

## ✅ Исправления по замечаниям ревьювера:

### 1. Локаторы хранятся в полях Page Object
   - Все локаторы объявлены как private final поля в классах Page Object
   - В тестах используются только методы Page Object

### 2. В тестах нет прямого кода работы с WebDriver
   - Весь код работы с элементами вынесен в Page Objects
   - Тесты содержат только бизнес-логику

### 3. URL определены как константы
   - Класс Constants.java содержит все URL
   - Легко изменить базовый URL в одном месте

### 4. Проверка активности разделов конструктора
   - 3 отдельных теста в ConstructorTest.java
   - Каждый тест проверяет свой раздел

### 5. Тесты разделены по классам
   - ConstructorTest.java - 3 теста конструктора
   - RegistrationTest.java - 2 теста регистрации
   - LoginTest.java - 4 теста входа

### 6. Создание/удаление тестовых пользователей через API
   - Класс UserClient.java для работы с API
   - @Before и @After методы для управления тестовыми данными

### 7. Не используется Thread.sleep()
   - Везде используется WebDriverWait
   - Явные ожидания вместо неявных

## 🏗️ Структура проекта
src/test/java/com/stellarburgers/
├── BaseTest.java # Базовый класс тестов
├── Constants.java # Константы URL
├── api/
│ └── UserClient.java # API клиент для работы с пользователями
├── pages/ # Page Objects
│ ├── MainPage.java
│ ├── LoginPage.java
│ ├── RegisterPage.java
│ └── ForgotPasswordPage.java
└── tests/ # Тестовые классы
├── LoginTest.java # 4 теста входа
├── RegistrationTest.java # 2 теста регистрации
└── ConstructorTest.java # 3 теста конструктора

text

## 📊 Тестовое покрытие (9 тестов)
### LoginTest.java (4 теста)
1. Вход через кнопку "Войти в аккаунт" на главной странице
2. Вход через кнопку "Личный кабинет" на главной странице
3. Вход со страницы регистрации
4. Вход со страницы восстановления пароля

### RegistrationTest.java (2 теста)
1. Успешная регистрация с валидным паролем
2. Ошибка регистрации с коротким паролем

### ConstructorTest.java (3 теста)
1. Переключение на раздел "Булки"
2. Переключение на раздел "Соусы"
3. Переключение на раздел "Начинки"

## 🚀 Запуск тестов
```bash
# Компиляция
mvn clean compile

# Запуск всех тестов
mvn test

# Запуск по классам
mvn test -Dtest=ConstructorTest
mvn test -Dtest=RegistrationTest
mvn test -Dtest=LoginTest
🛠️ Технологии
Java 11

Maven

Selenium WebDriver 4

JUnit 4

WebDriverManager

Проект готов к проверке!
