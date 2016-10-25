package com.example.ruslan.contacts;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ruslan.contacts.listOfContacts.Contact;
import com.example.ruslan.contacts.listOfContacts.ContactAdapter;
import com.example.ruslan.contacts.listOfContacts.OnContactClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan on 25.10.2016.
 */

public class ContactListFragment extends Fragment implements OnContactClickListener{

    private RecyclerView contactRecyclerView;
    private List<Contact> contacts;
    private ContactAdapter adapter;
    private String[] names = {"Диас","Матвей","Aртём","Янис","Максим","Дмитрий","Тимофей","Даниил","Роман","Арсений",
            "Егор","Кирилл","Марк","Никита","Андрей","Иван","Алексей","Богдан","Илья","Ярослав","Тимур","Михаил",
            "Владислав","Александр","Сергей","Глеб","Демид","Денис","Руслан","Павел","Савелий","Замир","Елисей","Аскар",
            "Константин","Вадим","Евгений","Дами","Владимир","Игорь","Семён","Захар","Марсель","Георгий","Давид","Антон",
            "Вячеслав","Артур","Мадияр","Степан","Олег","Родион","Назар","Станислав","Николай","Мирослав","Валерий","Савва",
            "Марат","Виктор","Фёдор","Святослав", "Добрыня","Милан","Виталий","Юрий","Ленар","Ростислав","Яромир"};
    private boolean orientationLand = false;
    private DialogFragment dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_list_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setUpContacts();

        adapter = new ContactAdapter(contacts,getActivity());
        adapter.setOnContactClickListener(this);

        contactRecyclerView = (RecyclerView) view.findViewById(R.id.rv_contact_list);
        contactRecyclerView.setAdapter(adapter);
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (view.findViewById(R.id.contact_info_fragment_container) != null){
            orientationLand = true;
        }
    }

    private void setUpContacts(){
        contacts = new ArrayList<Contact>();

        for(String name:names){
            contacts.add(new Contact(name));
        }
        return;
    }

    @Override
    public void onClickListener(int position) {
        if(orientationLand){
            Fragment fragment = ContactInfoFragment.newInstance(contacts.get(position).getName(),contacts.get(position).getNumber());
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contact_info_fragment_container,fragment,ContactInfoFragment.class.getName())
                    .commit();
        }else{
            Intent intent = new Intent(getActivity(),ContactInfoActivity.class);

            intent.putExtra("name",contacts.get(position).getName());
            intent.putExtra("number",contacts.get(position).getNumber());

            startActivity(intent);
        }
    }

    @Override
    public boolean onLongClickListener(int position) {
        Toast.makeText(getActivity(),"Работает",Toast.LENGTH_LONG).show();
        dialog = DeleteDialog.newInstance(getActivity(),position,adapter);
        dialog.show(getFragmentManager(),"delete");
        return true;
    }
}
