package com.example.ruslan.contacts.fragments;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ruslan.contacts.supportClasses.DataBase;
import com.example.ruslan.contacts.R;
import com.example.ruslan.contacts.activitys.ContactInfoActivity;
import com.example.ruslan.contacts.listOfContacts.Contact;
import com.example.ruslan.contacts.listOfContacts.ContactAdapter;
import com.example.ruslan.contacts.listOfContacts.OnContactClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan on 25.10.2016.
 */

public class ContactListFragment extends android.support.v4.app.Fragment implements OnContactClickListener{

    private RecyclerView contactRecyclerView;
    List<Contact> tempContacts = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private String[] names = {"Диас","Матвей","Aртём","Янис","Максим","Дмитрий","Тимофей","Даниил","Роман","Арсений",
            "Егор","Кирилл","Марк","Никита","Андрей","Иван","Алексей","Богдан","Илья","Ярослав","Тимур","Михаил",
            "Владислав","Александр","Сергей","Глеб","Демид","Денис","Руслан","Павел","Савелий","Замир","Елисей","Аскар",
            "Константин","Вадим","Евгений","Дами","Владимир","Игорь","Семён","Захар","Марсель","Георгий","Давид","Антон",
            "Вячеслав","Артур","Мадияр","Степан","Олег","Родион","Назар","Станислав","Николай","Мирослав","Валерий","Савва",
            "Марат","Виктор","Фёдор","Святослав", "Добрыня","Милан","Виталий","Юрий","Ленар","Ростислав","Яромир"};
    private DialogFragment dialog;
    private int pageNumber;

    public static ContactListFragment newInstance(int page){
        ContactListFragment fragment = new ContactListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page_number",page);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_list_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageNumber = getArguments().getInt("page_number");
        contactRecyclerView = (RecyclerView) view.findViewById(R.id.rv_contact_list);



        switch(pageNumber){
            //all contacts
            case 0:
                adapter = new ContactAdapter(0);
                ((ContactAdapter) adapter).setOnContactClickListener(this);
                contactRecyclerView.setAdapter(adapter);
                break;
            //favorite contacts
            case 1:
                adapter = new ContactAdapter(1);
                ((ContactAdapter) adapter).setOnContactClickListener(this);
                contactRecyclerView.setAdapter(adapter);
                break;
        }
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onClickListener(int position) {
        switch (pageNumber){ // normal or favorite contact pressed
            case 0:
                tempContacts = DataBase.contacts;
                break;
            case 1:
                tempContacts = DataBase.favoriteContacts;
                break;
        }

        if(isOrientationLand()){
            Fragment fragment = ContactInfoFragment.newInstance(tempContacts.get(position).getName(),tempContacts.get(position).getNumber());
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contact_info_fragment_container,fragment,ContactInfoFragment.class.getName())
                    .commit();
        }else{
            Intent intent = new Intent(getActivity(), ContactInfoActivity.class);

            intent.putExtra("name",tempContacts.get(position).getName());
            intent.putExtra("number",tempContacts.get(position).getNumber());

            startActivity(intent);

        }
    }

    @Override
    public boolean onLongClickListener(int position) {
//        Toast.makeText(getActivity(),"Работает",Toast.LENGTH_LONG).show();
        dialog = DeleteDialogFragment.newInstance(getActivity(),position,(ContactAdapter) adapter);
        dialog.show(getFragmentManager(),"delete");
        return true;
    }

    private boolean isOrientationLand(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return false;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return true;
        return false;
    }

}
