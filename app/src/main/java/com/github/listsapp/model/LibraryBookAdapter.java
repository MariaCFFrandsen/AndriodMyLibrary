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
import com.github.listsapp.viewmodel.SelectedBookViewModel;

import java.util.ArrayList;
import java.util.List;

public class LibraryBookAdapter extends RecyclerView.Adapter<LibraryBookAdapter.ViewHolder> {

    private List<Book> bookList = new ArrayList<>();
    LibraryBookAdapter.OnListItemClickListener listItemClickListener;

    public LibraryBookAdapter(OnListItemClickListener listener)
    {
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
        holder.name.setText(bookList.get(position).getTitle());
        holder.id.setText(String.valueOf(bookList.get(position).getId()));
        holder.image.setImageResource(bookList.get(position).getIconId());
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
        TextView name;
        TextView id;
        ImageView image;

        ViewHolder(View viewitem)
        {
            super(viewitem);
            name = itemView.findViewById(R.id.library_title);
            id = itemView.findViewById(R.id.library_id);
            image = itemView.findViewById(R.id.library_bookcover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItemClickListener.onListItemClick(getAdapterPosition());
                    int chosenId = Integer.parseInt(id.getText().toString());
                    SelectedBookViewModel.setChosenBook(chosenId);

                }
            });

        }
    }
}
