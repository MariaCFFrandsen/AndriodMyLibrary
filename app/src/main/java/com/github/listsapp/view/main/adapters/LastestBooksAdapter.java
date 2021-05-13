package com.github.listsapp.view.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.listsapp.R;
import com.github.listsapp.util.Book;
import com.github.listsapp.viewmodel.SelectedBookViewModel;

import java.util.List;

public class LastestBooksAdapter extends RecyclerView.Adapter<LastestBooksAdapter.ViewHolder> {


    private List<Book> books;
    OnListItemClickListener listItemClickListener;
    String hello;

    public LastestBooksAdapter(List<Book> books, OnListItemClickListener listener)
    {
        this.books = books;
        listItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_testview2, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.readStatus.setText(books.get(position).getReadStatus());
        viewHolder.title.setText(books.get(position).getTitle());
        viewHolder.bookcover.setImageResource(books.get(position).getBookCover());
        viewHolder.author.setText("Christopher Paolini");
        if(books.get(position).isOwned())
        {
           // viewHolder.bookcover.setImageResource(books.get(position).getIconId());
         //   viewHolder.owned.setImageResource(R.drawable.owned);
        }
        //viewHolder.readStatus.setText(books.get(position).getReadStatus());

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        /*TextView title;
        TextView readStatus;
        ImageView bookcover;
        ImageView owned;
         title = itemView.findViewById(R.id.lastest_booktitle);
            readStatus = itemView.findViewById(R.id.lastest_readstatus);
            bookcover = itemView.findViewById(R.id.lastest_bookcover);
            owned = itemView.findViewById(R.id.lastest_owned);

         */
        TextView title;
        ImageView bookcover;
        TextView readStatus;
        TextView author;
       // OnListItemClickListener listItemClickListener;

        ViewHolder(View viewitem)
        {
            super(viewitem);

            readStatus = viewitem.findViewById(R.id.test_readStatus);
            title = itemView.findViewById(R.id.test_title);
            bookcover = viewitem.findViewById(R.id.test_bookcover);
            author = viewitem.findViewById(R.id.test_author);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItemClickListener.onListItemClick(getAdapterPosition());
                    int chosenId = Integer.parseInt("1");
                    SelectedBookViewModel.setChosenBook(chosenId);
                }
            });

        }
    }
}
