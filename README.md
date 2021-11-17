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
Entity Layer: [`User`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/entity/User.java) [`Role`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/entity/Role.java) [`Book`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/entity/Book.java)
Input-Output Layer: [`IOConnectorTXT`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/io/IOConnectorTXT.java)  
DAO Layer: [`DAO<T>`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/dao/DAO.java) [`TXTUserDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/dao/impl/TXTUserDAO.java) [`TXTBookDAO`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/dao/impl/TXTBookDAO.java) [`TXTDAOFactory`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/dao/factory/TXTDAOFactory.java)  
Service Layer: [`UserService`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/service/impl/UserServiceImpl.java) [`BookService`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/service/impl/BookServiceImpl.java) [`TXTServiceFactory`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/service/factory/TXTServiceFactory.java)  
Controller Layer: [`Controller & Command`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/tree/branch-b/src/by/epamtc/bakulin/controller)  
Property File: [`application.properties`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/resources/application.properties)  
Application Runner: [`Runner`](https://github.com/alex96brk/epam-training-java-web-task04-bakulin/blob/branch-b/src/by/epamtc/bakulin/Runner.java)