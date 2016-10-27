package com.example.ruslan.contacts.supportClasses;

import com.example.ruslan.contacts.listOfContacts.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan on 27.10.2016.
 */

public class DataBase {
    private static String[] names = {"Диас", "Матвей", "Aртём", "Янис", "Максим", "Дмитрий", "Тимофей", "Даниил", "Роман", "Арсений",
            "Егор", "Кирилл", "Марк", "Никита", "Андрей", "Иван", "Алексей", "Богдан", "Илья", "Ярослав", "Тимур", "Михаил",
            "Владислав", "Александр", "Сергей", "Глеб", "Демид", "Денис", "Руслан", "Павел", "Савелий", "Замир", "Елисей", "Аскар",
            "Константин", "Вадим", "Евгений", "Дами", "Владимир", "Игорь", "Семён", "Захар", "Марсель", "Георгий", "Давид", "Антон",
            "Вячеслав", "Артур", "Мадияр", "Степан", "Олег", "Родион", "Назар", "Станислав", "Николай", "Мирослав", "Валерий", "Савва",
            "Марат", "Виктор", "Фёдор", "Святослав", "Добрыня", "Милан", "Виталий", "Юрий", "Ленар", "Ростислав", "Яромир"};
    private static String[] gavno = {"sdf","gsd","awef","afew","fawe"};

    public static List<Contact> contacts;
    public static List<Contact> favoriteContacts;

    public DataBase() {
        setUpContacts();
    }

    public static void setUpContacts() {
        favoriteContacts = new ArrayList<Contact>();
        int i = 0;
        for (String name : gavno) {
            if (i % 2 == 0)
                favoriteContacts.add(new Contact(name));
        }

        contacts = new ArrayList<Contact>();

        for (String name : names) {
            contacts.add(new Contact(name));
        }
        return;
    }
}
