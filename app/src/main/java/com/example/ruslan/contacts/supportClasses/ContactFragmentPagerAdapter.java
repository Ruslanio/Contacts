package com.example.ruslan.contacts.supportClasses;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ruslan.contacts.fragments.ContactListFragment;

/**
 * Created by Ruslan on 25.10.2016.
 */

public class ContactFragmentPagerAdapter extends FragmentStatePagerAdapter {
    static final int PAGE_COUNT = 2;

    public ContactFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ContactListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
