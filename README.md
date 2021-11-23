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
Data Storage Layer: [`Storage`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/tree/branch-b/src/resources/storage)  
Entity Layer: [`User`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/entity/User.java) [`Role`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/entity/Role.java) [`Book`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/entity/Book.java)  
Input-Output Functionality: [`IOConnectorTXT`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/dao/io/txt/IOConnectorTXT.java) [`IOConnectorXLSX`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/tree/branch-c/src/by/epamtc/bakulin/dao/io/xlsx)  
DAO Layer: [`DAO<T>`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/dao/DAO.java) [`TXTUserDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/dao/impl/TXTUserDAO.java) [`TXTBookDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/dao/impl/TXTBookDAO.java)  
DAO Factory: [`LibraryDAOFactory`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/dao/factory/LibraryDAOFactory.java) [`TXTDAOFactory`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/dao/factory/impl/TXTDAOFactory.java) [`XLSXDAOFactory`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/dao/factory/impl/XLSXDAOFactory.java)  
Service Layer: [`UserService`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/service/impl/UserServiceImpl.java) [`BookService`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/service/impl/BookServiceImpl.java)  
Service Factory: [`ServiceFactory`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/service/factory/ServiceFactory.java)  
Controller Layer: [`Controller & Command`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/tree/branch-c/src/by/epamtc/bakulin/controller)  
Property File: [`application.properties`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/resources/application.properties)  
Application Runner: [`Runner`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-c/src/by/epamtc/bakulin/ConsoleRunner.java)