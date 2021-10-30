# EPAM Java Web Development Training
### Студент: Bakulin Alexey (Бакулин Алексей)
### Задание: Task04
### Навигация по репозиторию:
### Задание:
Авторизовать и аутентифицировать пользователя;  
Зарегистрировать нового пользователя (может только администратор);  
Просмотреть весь каталог;  
Найти книгу по автору + по другому критерию;  
Добавить новую запись;  
Отредактировать запись;
### Структура проекта:
Data Storage Layer: [`Storage`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/tree/branch-a/src/resources/storage)  
Entity Layer: [`User`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/model/User.java) [`Role`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/model/Role.java) [`Book`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/model/Book.java)  
Input-Output Layer: [`IOConnector`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/io/IOConnector.java) [`IOConnectorTXT(implements IOConnector)`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/io/impl/IOConnectorTXT.java)  
DAO Layer: [`DAO<T>`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/dao/DAO.java) [`TXTUserDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/dao/impl/TXTUserDAO.java) [`TXTBookDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/dao/impl/TXTBookDAO.java)  
Property File: [`application.properties`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/resources/application.properties)  
### Тесты проекта:
[`UserTest`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/test/UserTest.java) [`TXTUserDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/test/TXTUserDAOTest.java) [`BookTest`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/test/BookTest.java) [`TXTBookDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-a/src/by/epamtc/bakulin/test/TXTBookDAOTest.java)