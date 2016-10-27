package com.example.ruslan.contacts.activitys;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruslan.contacts.supportClasses.ContactFragmentPagerAdapter;
import com.example.ruslan.contacts.supportClasses.DataBase;
import com.example.ruslan.contacts.R;

public class MainActivity extends AppCompatActivity {

//    private ViewPager pager;
//    private ContactFragmentPagerAdapter pagerAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Fragment fragment = new ContactListFragment();
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.contact_main_fragment_container,fragment,ContactListFragment.class.getName())
//                .commit();
//    }


    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase.setUpContacts(); //create database of contacts

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ContactFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

    }

}
