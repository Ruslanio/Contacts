package com.example.ruslan.contacts.listOfContacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruslan.contacts.R;

import java.util.List;

/**
 * Created by Ruslan on 17.10.2016.
 */

public class ContactAdapter extends RecyclerView.Adapter {

    private List<Contact> contacts;
    private Context context;
    private OnContactClickListener onContactClickListener;
    private  OnLongContactClickListener onLongContactClickListener;

    public ContactAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,null);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ContactViewHolder) holder).bind(onContactClickListener,position);
        TextView tvName = ((ContactViewHolder) holder).mContactName;
        tvName.setText(contacts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setOnContactClickListener( OnContactClickListener onContactClickListener){
        this.onContactClickListener = onContactClickListener;
    }


    public void setOnLongContactClickListener(OnLongContactClickListener onLongContactClickListener) {
        this.onLongContactClickListener = onLongContactClickListener;
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder{

        private TextView mContactName;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mContactName = (TextView) itemView.findViewById(R.id.tv_contact_name);
        }

        public void bind(final OnContactClickListener onContactClickListener, final int position){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onContactClickListener.onClickListener(position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongContactClickListener.onLongClickListener(position);
                    return true;
                }
            });
        }
    }
}
