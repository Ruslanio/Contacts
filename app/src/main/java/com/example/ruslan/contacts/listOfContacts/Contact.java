package com.example.ruslan.contacts.listOfContacts;

import java.util.Random;

/**
 * Created by Ruslan on 17.10.2016.
 */

public class Contact {

    private String name;
    private String number = "8";
    private Random rnd = new Random();

    public Contact(String name) {
        this.name = name;

        for(int i = 0;i < 10;i++ ){
            number = number + rnd.nextInt(10);
        }
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
