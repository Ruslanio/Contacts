package com.example.ruslan.contacts.listOfContacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ruslan.contacts.supportClasses.DataBase;
import com.example.ruslan.contacts.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan on 17.10.2016.
 */

public class ContactAdapter extends RecyclerView.Adapter {

    private OnContactClickListener onContactClickListener;
    private int pageNumber;
    private ContactAdapter tempAdapter;

    public ContactAdapter(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, null));
            case 1:
                return new FavoriteContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_contact, null));
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, null);
        return new ContactViewHolder(view);
    }


    @Override
    public int getItemViewType(int position) {
        return pageNumber;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ContactViewHolder) {
            ((ContactViewHolder) holder).bind(position);

            TextView tvName = ((ContactViewHolder) holder).mContactName;
            tvName.setText(DataBase.contacts.get(position).getName());

            ImageButton btnAddFavorite = ((ContactViewHolder) holder).mAddFavorite;
            btnAddFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addFavoriteContact(DataBase.contacts.get(position));
                }
            });

        }
        if (holder instanceof FavoriteContactViewHolder) {
            TextView tvName = ((FavoriteContactViewHolder) holder).mFavoriteContactName;
            ((FavoriteContactViewHolder) holder).bind(position);

            if (DataBase.favoriteContacts.size() > 0) {
                tvName.setText(DataBase.favoriteContacts.get(position).getName());
            }
        }
    }

    @Override
    public int getItemCount() {
        switch (pageNumber){
            case 0:
                return DataBase.contacts.size();
            case 1:
                return DataBase.favoriteContacts.size();
        }
        return DataBase.contacts.size();
    }

    public void setOnContactClickListener(OnContactClickListener onContactClickListener) {
        this.onContactClickListener = onContactClickListener;
    }

    public void deleteItem(int position) {
        List<Contact> tempContacts = new ArrayList<>();
        switch (pageNumber){
            case 0:
                tempContacts = DataBase.contacts;
                break;

            case 1:
                tempContacts = DataBase.favoriteContacts;
                break;

        }
        tempContacts.remove(position);
        notifyItemRemoved(position);
    }

    public void addFavoriteContact(Contact contact) {
        DataBase.favoriteContacts.add(contact);
//
//        String fav = "" + DataBase.favoriteContacts.size();
//        String con = "" + DataBase.contacts.size();
//        Log.d("myTag","fav "+ fav);
//        Log.d("myTag","con " + con);

    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView mContactName;
        private ImageButton mAddFavorite;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mContactName = (TextView) itemView.findViewById(R.id.tv_contact_name);
            mAddFavorite = (ImageButton) itemView.findViewById(R.id.btn_add_to_favorite);
        }

        public void bind(final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onContactClickListener.onClickListener(position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onContactClickListener.onLongClickListener(position);
                }
            });
        }

    }

    class FavoriteContactViewHolder extends RecyclerView.ViewHolder {
        private TextView mFavoriteContactName;

        public FavoriteContactViewHolder(View itemView) {
            super(itemView);
            mFavoriteContactName = (TextView) itemView.findViewById(R.id.tv_favorite_contact_name);
        }
        public void bind(final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onContactClickListener.onClickListener(position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onContactClickListener.onLongClickListener(position);
                }
            });
        }
    }
}

