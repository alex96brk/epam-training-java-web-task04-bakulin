package by.epamtc.bakulin.model;


import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private Role userRole;
    private String password;

    public User(String userName, String firstName, String lastName, String password) {
        this.userId = (long) (Math.random() * Long.MAX_VALUE);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = Role.USER;
        this.password = password;
    }

    public User() {
        this.userId = (long) (Math.random() * Long.MAX_VALUE);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            result = false;
        }
        User user = (User) o;
        return (userName == user.userName || (userName != null && userName.equals(user.getUserName()))) &&
                (firstName == user.firstName || (firstName != null && firstName.equals(user.getFirstName()))) &&
                (lastName == user.lastName || (lastName != null && lastName.equals(user.getLastName()))) &&
                (userRole == user.userRole || (userRole != null && userRole.equals(user.getUserRole())));
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + (userName == null ? 0 : userName.hashCode());
        hash = 31 * hash + (firstName == null ? 0 : firstName.hashCode());
        hash = 31 * hash + (lastName == null ? 0 : lastName.hashCode());
        hash = 31 * hash + (userRole == null ? 0 : userRole.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "User{" +
                " userId = " + userId +
                ", userName = '" + userName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", userRole = '" + userRole + '\'' +
                ", password = '" + password + '\'' +
                '}';
    }

    @Override
    public int compareTo(User o) {
        int result = this.userName.compareTo(o.getUserName());
        if (result == 0) {
            result = this.lastName.compareTo(o.getLastName());
        }
        if (result == 0) {
            result = this.firstName.compareTo(o.getFirstName());
        }
        return result;
    }
}
