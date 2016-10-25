package com.example.ruslan.contacts.listOfContacts;

import android.app.FragmentManager;
import android.content.Context;
import android.app.DialogFragment;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruslan.contacts.DeleteDialog;
import com.example.ruslan.contacts.R;

import java.util.List;

/**
 * Created by Ruslan on 17.10.2016.
 */

public class ContactAdapter extends RecyclerView.Adapter {

    private List<Contact> contacts;
    private Context context;
    private OnContactClickListener onContactClickListener;

    public ContactAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, null);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
      ((ContactViewHolder) holder).bind(position);
//      ((ContactViewHolder) holder).bind(onLongContactClickListener,position);

        TextView tvName = ((ContactViewHolder) holder).mContactName;
        tvName.setText(contacts.get(position).getName());

        ImageButton btnAddFavorite = ((ContactViewHolder) holder).mAddFavorite;
        btnAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setOnContactClickListener(OnContactClickListener onContactClickListener) {
        this.onContactClickListener = onContactClickListener;
    }

    public void deleteItem(int position){
        contacts.remove(position);
        notifyItemRemoved(position);
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView mContactName;
        private ImageButton mAddFavorite;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mContactName = (TextView) itemView.findViewById(R.id.tv_contact_name);
            mAddFavorite = (ImageButton) itemView.findViewById(R.id.btn_add_to_favorite);
        }

        public void bind( final int position) {
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

