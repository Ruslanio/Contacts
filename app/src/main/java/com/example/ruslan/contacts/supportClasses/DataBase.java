package com.example.ruslan.contacts.supportClasses;

import android.content.Context;

import com.example.ruslan.contacts.fragments.ContactListFragment;
import com.example.ruslan.contacts.listOfContacts.Contact;
import com.example.ruslan.contacts.listOfContacts.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan on 27.10.2016.
 */

public class DataBase {
    private String[] names = {"Диас", "Матвей", "Aртём", "Янис", "Максим", "Дмитрий", "Тимофей", "Даниил", "Роман", "Арсений",
            "Егор", "Кирилл", "Марк", "Никита", "Андрей", "Иван", "Алексей", "Богдан", "Илья", "Ярослав", "Тимур", "Михаил",
            "Владислав", "Александр", "Сергей", "Глеб", "Демид", "Денис", "Руслан", "Павел", "Савелий", "Замир", "Елисей", "Аскар",
            "Константин", "Вадим", "Евгений", "Дами", "Владимир", "Игорь", "Семён", "Захар", "Марсель", "Георгий", "Давид", "Антон",
            "Вячеслав", "Артур", "Мадияр", "Степан", "Олег", "Родион", "Назар", "Станислав", "Николай", "Мирослав", "Валерий", "Савва",
            "Марат", "Виктор", "Фёдор", "Святослав", "Добрыня", "Милан", "Виталий", "Юрий", "Ленар", "Ростислав", "Яромир"};

    public List<Contact> contacts;
    public List<Contact> favoriteContacts;
    private static DataBase dataBase;


    private DataBase() {
        setUpContacts();
    }

    public static DataBase getInstance(){
        if(dataBase == null){
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public void setUpContacts() {
        favoriteContacts = new ArrayList<Contact>();

        contacts = new ArrayList<Contact>();

        for (String name : names) {
            contacts.add(new Contact(name));
        }
        return;
    }

    public boolean addFavoriteContact(int position){
        if(favoriteContacts.contains(contacts.get(position))){

            return false;
        } else {
            favoriteContacts.add(contacts.get(position));

            ArrayList<ContactAdapter> adapters = (ArrayList<ContactAdapter>) ContactListFragment.allAdapters;
            for (ContactAdapter adapter : adapters) {
                adapter.notifyDataSetChanged();
            }
            return true;
        }
    }
}
