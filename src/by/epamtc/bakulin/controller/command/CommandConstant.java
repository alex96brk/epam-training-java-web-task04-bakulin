package by.epamtc.bakulin.controller.command;

public class CommandConstant {
    public static String USER_CMD = "Enter next cmd request:\n" +
            "User requests:\n" +
            "\tuser_help - to show cmd requests\n" +
            "\tuser_find_all - find all users in the library\n" +
            "\tuser_find_by_id $id - to find user by id\n" +
            "\tuser_find_by_uname $userName - to find user by username\n" +

            "Book requests:\n" +
            "\tbook_find_all - find all books in the library\n" +
            "\tbook_find_by_id $id - to find book by id\n" +
            "\tbook_find_by_author $bookAuthor - to find book by author\n";

    public static String ADMIN_CMD = "Enter next cmd request:\n" +
            "User requests:\n" +
            "\tadmin_help - to show cmd requests\n" +
            "\tuser_find_all - find all users in the library\n" +
            "\tuser_find_by_id $id - to find user by id\n" +
            "\tuser_find_by_uname $userName - to find user by username\n" +

            "Book requests:\n" +
            "\tbook_find_all - find all books in the library\n" +
            "\tbook_find_by_id $id - to find book by id\n" +
            "\tbook_find_by_author $bookAuthor - to find book by author\n" +

            "ADMIN requests:\n" +
            "\tbook_add $bookName $bookAuthor $bookGenre - to add book into the library(ADMIN)\n" +
            "\tbook_update $id $currentBookName $newBookName $bookAuthor $bookGenre - to update book into the library(ADMIN)\n" +
            "\tbook_delete $id - to delete book from the library(ADMIN)\n" +
            "\tuser_add $userName $firstName $lastName $password - to add book into the library(ADMIN))\n" +
            "\tuser_update $id $currentUserName $newUserName $firstName $lastName $password $role - to add book into the library(ADMIN)\n" +
            "\tuser_delete $id - to delete user from the library(ADMIN)\n" +
            "";
    public static final String AUTHORITY_USER = "USER";

    public static final String AUTHORITY_ADMIN = "ADMIN";
}
