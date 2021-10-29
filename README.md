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
Data Storage Layer: [`Storage`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/tree/master/src/resources/storage)  
Entity Layer: [`User`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/model/User.java) [`Role`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/model/Role.java) [`Book`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/model/Book.java)  
Input-Output Layer: [`IOManager`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/io/IOManager.java) [`IOManagerTXT(implements IOManager)`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/io/impl/IOManagerTXT.java)  
DAO Layer: [`UserDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/dao/UserDAO.java) [`TXTUserDAO(implements UserDAO)`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/dao/impl/TXTUserDAO.java) [`BookDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/dao/BookDAO.java) [`TXTBookDAO(implements BookDAO)`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/dao/impl/TXTBookDAO.java)  
Property File: [`application.properties`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/resources/application.properties)  
### Тесты проекта:
[`UserTest`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/test/UserTest.java) [`UserDAOTest`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/test/TXTUserDAOTest.java) [`BookTest`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/test/BookTest.java)  [`BookDAOTest`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/master/src/by/epamtc/bakulin/test/TXTBookDAOTest.java)