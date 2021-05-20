package com.github.listsapp.view.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.github.listsapp.R;
import com.github.listsapp.util.Book;
import com.github.listsapp.view.bookdetails.BookDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class LibraryBookAdapter extends RecyclerView.Adapter<LibraryBookAdapter.ViewHolder> {

    private List<Book> bookList = new ArrayList<>();
    LibraryBookAdapter.OnListItemClickListener listItemClickListener;

    private Context context;

    public LibraryBookAdapter(OnListItemClickListener listener, Context context)
    {
        this.context = context;
        listItemClickListener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_testview, parent
                , false);
        return new LibraryBookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(bookList.get(position).getTitle());
        holder.author.setText(bookList.get(position).getAuthor());
        holder.readStatus.setText(bookList.get(position).getReadStatus());
        if(bookList.get(position).getImageUrl() != null)
        Glide.with(context).load(bookList.get(position).getImageUrl()).into(holder.bookcover);

    }

    public void updateList(List<Book> list)
    {
        bookList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView bookcover;
        TextView readStatus;
        TextView author;

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
                    Book book = bookList.get(getAdapterPosition());
                    BookDetailsViewModel.setChosenBook(book);

                }
            });

        }
    }





}


