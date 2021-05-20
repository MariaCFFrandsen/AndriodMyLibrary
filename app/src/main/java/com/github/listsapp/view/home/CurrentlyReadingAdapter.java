package com.github.listsapp.view.home;

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

import java.util.ArrayList;
import java.util.List;

public class CurrentlyReadingAdapter extends RecyclerView.Adapter<CurrentlyReadingAdapter.ViewHolder> {


    private List<Book> books = new ArrayList<>();
    private Context context;
    private OnListItemClickListener listItemClickListener;

    public CurrentlyReadingAdapter(Context context, OnListItemClickListener listener)
    {
        this.context = context;
        listItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_testview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //populate views in the recyclerview
        //called when getItemCounts > 0
        viewHolder.readStatus.setText(books.get(position).getReadStatus());
        viewHolder.title.setText(books.get(position).getTitle());
        Glide.with(context).load(books.get(position).getImageUrl()).placeholder(R.drawable.coverplaceholder).into(viewHolder.bookcover);
        viewHolder.author.setText(books.get(position).getAuthor());


    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void updateCurrentlyReadingBookList(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    //this interface is used for callback when you click on a view in the recyclerview
    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //inner class for holding references for views in the recyclerview
        TextView title;
        ImageView bookcover;
        TextView readStatus;
        TextView author;

        ViewHolder(View viewitem)
        {
            super(viewitem);
            //references for views
            readStatus = viewitem.findViewById(R.id.test_readStatus);
            title = itemView.findViewById(R.id.test_title);
            bookcover = viewitem.findViewById(R.id.test_bookcover);
            author = viewitem.findViewById(R.id.test_author);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //when you click on the selected item is set to the viewmodel
                    listItemClickListener.onListItemClick(getAdapterPosition());
                    Book book = books.get(getAdapterPosition());
                    CurrentlyReadingViewModel.setChosenGBook(book);

                }
            });

        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
