package com.example.ruslan.contacts;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ruslan.contacts.listOfContacts.Contact;
import com.example.ruslan.contacts.listOfContacts.ContactAdapter;
import com.example.ruslan.contacts.listOfContacts.OnContactClickListener;
import com.example.ruslan.contacts.listOfContacts.OnLongContactClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnContactClickListener , OnLongContactClickListener{

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
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpContacts();

        adapter = new ContactAdapter(contacts,getApplicationContext());
        adapter.setOnContactClickListener(this);
        adapter.setOnLongContactClickListener(this);

        contactRecyclerView = (RecyclerView) findViewById(R.id.rv_contact_list);
        contactRecyclerView.setAdapter(adapter);
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if (findViewById(R.id.contact_info_fragment_container) != null){
            orientationLand = true;
        }
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
            Intent intent = new Intent(getApplicationContext(),ContactInfoActivity.class);

            intent.putExtra("name",contacts.get(position).getName());
            intent.putExtra("number",contacts.get(position).getNumber());

            startActivity(intent);
        }
    }

    @Override
    public void onLongClickListener(final int position) {

        Toast.makeText(getApplicationContext(),"Работает",Toast.LENGTH_LONG);

        builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Remove contact");
        builder.setMessage("Are you sure?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contacts.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"contact removed",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    private void setUpContacts(){
        contacts = new ArrayList<Contact>();

        for(String name:names){
            contacts.add(new Contact(name));
        }
        return;
    }

}
