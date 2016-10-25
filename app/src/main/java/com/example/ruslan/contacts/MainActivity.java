package com.example.ruslan.contacts;

import android.app.DialogFragment;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.ruslan.contacts.listOfContacts.Contact;
import com.example.ruslan.contacts.listOfContacts.ContactAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity /*implements OnContactClickListener*/{

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new ContactListFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.contact_main_fragment_container,fragment,ContactListFragment.class.getName())
                .commit();

//        setUpContacts();
//
//        adapter = new ContactAdapter(contacts,this);
//        adapter.setOnContactClickListener(this);
//
//        contactRecyclerView = (RecyclerView) findViewById(R.id.rv_contact_list);
//        contactRecyclerView.setAdapter(adapter);
//        contactRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//        if (findViewById(R.id.contact_info_fragment_container) != null){
//            orientationLand = true;
//        }
    }

//    @Override
//    public void onClickListener(int position) {
//        if(orientationLand){
//            Fragment fragment = ContactInfoFragment.newInstance(contacts.get(position).getName(),contacts.get(position).getNumber());
//            getFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.contact_info_fragment_container,fragment,ContactInfoFragment.class.getName())
//                    .commit();
//        }else{
//            Intent intent = new Intent(getApplicationContext(),ContactInfoActivity.class);
//
//            intent.putExtra("name",contacts.get(position).getName());
//            intent.putExtra("number",contacts.get(position).getNumber());
//
//            startActivity(intent);
//        }
//    }
//
//    @Override
//    public boolean onLongClickListener(final int position) {
//        Toast.makeText(getApplicationContext(),"Работает",Toast.LENGTH_LONG).show();
//        dialog = DeleteDialog.newInstance(this,position,adapter);
//        dialog.show(getFragmentManager(),"delete");
//        return true;
//    }
//
//    private void setUpContacts(){
//        contacts = new ArrayList<Contact>();
//
//        for(String name:names){
//            contacts.add(new Contact(name));
//        }
//        return;
//    }
}
