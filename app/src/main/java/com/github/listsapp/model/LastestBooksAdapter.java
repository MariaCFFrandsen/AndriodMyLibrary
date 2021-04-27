package com.github.listsapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.listsapp.R;
import com.github.listsapp.util.Book;

import java.util.ArrayList;

public class LastestBooksAdapter extends RecyclerView.Adapter<LastestBooksAdapter.ViewHolder> {


    private ArrayList<Book> books;

    public LastestBooksAdapter(ArrayList<Book> books)
    {
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_recyclerview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.title.setText(books.get(position).getName());
        viewHolder.bookcover.setImageResource(books.get(position).getIconId());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView readStatus;
        ImageView bookcover;
        ImageView owned;
        ViewHolder(View viewitem)
        {
            super(viewitem);

            title = itemView.findViewById(R.id.booktitle);
            readStatus = itemView.findViewById(R.id.readstatus);
            bookcover = itemView.findViewById(R.id.bookcover);
            owned = itemView.findViewById(R.id.owned);

        }
    }
}
