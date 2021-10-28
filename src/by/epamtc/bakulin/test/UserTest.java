package by.epamtc.bakulin.test;

import by.epamtc.bakulin.model.Role;
import by.epamtc.bakulin.model.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    @Test
    public void testUser1() {
        User user1 = new User("user4ik", "Viktor", "Ivanov", "321Pass");
        System.out.println("user1: " + user1);
        User user2 = new User("user4ik", "Viktor", "Ivanov", "32135Psd");
        System.out.println("user2: " + user2);
        User user3 = new User("alexRaven", "Alex", "Raven", "692635Fwf");
        System.out.println("user3: " + user3);

        boolean equals1 = user1.equals(user2);
        boolean equals2 = user2.equals(user1);
        boolean equals3 = user1.equals(user3);
        boolean equals4 = user2.equals(user3);
        boolean equals5 = user2.equals(user2);

        System.out.println("user1 equals user2?: " + equals1);
        System.out.println("user2 equals user1?: " + equals2);
        System.out.println("user1 equals user3?: " + equals3);
        System.out.println("user2 equals user3?: " + equals4);
        System.out.println("user2 equals user2?: " + equals5);
    }

    @Test
    public void testUser2() {
        User user1 = new User("user4ik", "Viktor", "Ivanov", "321Pass");
        User user2 = new User("usirsgs", "Alf", "Bann", "lahg452");
        User user3 = new User("alexRaven", "Alex", "Raven", "lkjla3585");
        User user4 = new User("annPerova", "Ann", "Petrova", "sdgsl9023");
        user4.setUserRole(Role.ADMIN);
        User user5 = new User("valeryProchor", "Valery", "Prochorov", "92lh53");
        User user6 = new User("anderHolod", "Andrey", "Holodny", "sglhs09");
        User user7 = new User("tamaVoron", "Tamara", "Voronova", "plgir354");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user3);
        list.add(user4);
        list.add(user2);
        list.add(user5);
        list.add(user6);
        list.add(user7);
        System.out.println("Current list:");
        for (User user : list) {
            System.out.println(user);
        }
        Collections.sort(list);
        System.out.println("Sorted list:");
        for (User user : list) {
            System.out.println(user);
        }

    }

    @Test
    public void testUser3() {
        User user1 = new User("user4ik", "Viktor", "Ivanov", "321Pass");
        System.out.println(user1.getUserRole());
        System.out.println(Arrays.toString(user1.getUserRole().getAuthorities()));
    }

}
