package com.github.listsapp.view.lookupbook;

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
import com.github.listsapp.util.api.GBook;
import com.github.listsapp.util.api.GBookList;
import com.github.listsapp.view.gbook_details.GBookDetailsViewModel;

import java.util.List;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

public class GoogleBooksAdapter extends RecyclerView.Adapter<GoogleBooksAdapter.ViewHolder> {

    private GBookList gBookList;
    GoogleBooksAdapter.OnListItemClickListener listItemClickListener;
    private Context context;
    public GoogleBooksAdapter(Context context, GoogleBooksAdapter.OnListItemClickListener listItemClickListener)
    {
        this.context = context;
        gBookList = new GBookList();
        this.listItemClickListener = listItemClickListener;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gbooklist_item, parent
                , false);

        return new GoogleBooksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<GBook> items = gBookList.getItems();

        GBook gBook = items.get(position);
        holder.title.setText(gBook.getVolumeInfo().getTitle());
        if(gBook.getVolumeInfo().getAuthors() != null && gBook.getVolumeInfo().getAuthors().size() > 0)
        holder.author.setText(gBook.getVolumeInfo().getAuthors().get(0));
        if(gBook.getVolumeInfo().getCategories() != null && gBook.getVolumeInfo().getCategories().size() > 0)
        holder.category.setText(gBook.getVolumeInfo().getCategories().get(0));
        holder.price.setText(getRating(gBook.getVolumeInfo().getAverageRating()));
        if(gBook.getVolumeInfo().getImageLinks() != null && gBook.getVolumeInfo().getImageLinks().getSmallThumbnail() != null)
        Glide.with(context).load(gBook.getVolumeInfo().getImageLinks().getSmallThumbnail()).placeholder(R.drawable.coverplaceholder).into(holder.thumbnail);
    }

    private String getRating(double averageRating) {
        StringBuilder sb = new StringBuilder(String.valueOf(averageRating));
        if (averageRating > 1) {
            sb.append(" stars");
        } else {
            sb.append(" star");
        }

        return sb.toString();
    }

    @Override
    public int getItemCount() {
        return gBookList.getItems().size();
    }

    public void updateGBookList(GBookList gBookList) {
        this.gBookList = gBookList;
        notifyDataSetChanged();
    }


    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView category;
        TextView price;
        ImageView thumbnail;

        ViewHolder(View viewitem)
        {
            super(viewitem);
            title = itemView.findViewById(R.id.gbook_title);
            author = itemView.findViewById(R.id.gbook_author);
            category =  itemView.findViewById(R.id.gbook_category);
            price = itemView.findViewById(R.id.gbook_price);
            thumbnail = itemView.findViewById(R.id.gbook_thumbnail);

            viewitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItemClickListener.onListItemClick(getAdapterPosition());
                    GBook gBook = gBookList.getItems().get(getAdapterPosition());
                    GBookDetailsViewModel.setChosenGBook(gBook);

                }
            });

        }
    }


    @GlideModule
    public class GlideApp extends AppGlideModule {

    }

}
