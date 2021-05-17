package com.github.listsapp.view.gbook_details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.listsapp.R;
import com.github.listsapp.util.api.GBook;
import com.github.listsapp.view.gbook_details.GBookDetailsViewModel;

public class GBookDetailsFragment extends Fragment {

    private GBookDetailsViewModel viewModel;
    private TextView booktitle;
    private TextView author;
    private TextView categories;
    private TextView averageRating;
    private TextView publishDate;
    private TextView description;
    private ImageView thumbnail;
    private TextView preview;
    private TextView info;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_g_book_details_fragment, container, false);
        author = view.findViewById(R.id.gbook_details_author);
        booktitle = view.findViewById(R.id.gbook_details_booktitle);
        categories = view.findViewById(R.id.gbook_details_categories);
        averageRating = view.findViewById(R.id.gbook_details_averageRating);
        publishDate = view.findViewById(R.id.gbook_details_publishdate);
        description = view.findViewById(R.id.gbook_details_description);
        thumbnail = view.findViewById(R.id.gbook_details_thumbnail);

        preview = view.findViewById(R.id.gbook_details_preview);
        info = view.findViewById(R.id.gbook_detials_info);

        viewModel = new ViewModelProvider(this).get(GBookDetailsViewModel.class);


        GBookDetailsViewModel.getgBookMutableLiveData().observe(getViewLifecycleOwner(), new Observer<GBook>() {
            @Override
            public void onChanged(GBook gBook) {
                if(gBook.getVolumeInfo() != null)
                {
                    booktitle.setText(gBook.getVolumeInfo().getTitle());
                    categories.setText(gBook.getVolumeInfo().getListToString(gBook.getVolumeInfo().getAuthors()));
                    averageRating.setText("Rating: " + gBook.getVolumeInfo().getAverageRating());
                    publishDate.setText(gBook.getVolumeInfo().getPublisher());
                    description.setText(gBook.getVolumeInfo().getDescription());
                    if(getContext() != null && gBook.getVolumeInfo().getImageLinks() != null && gBook.getVolumeInfo().getImageLinks().getThumbnail() != null)
                    Glide.with(getContext()).load(gBook.getVolumeInfo().getImageLinks().getThumbnail()).placeholder(R.drawable.coverplaceholder).into(thumbnail);


                    /*
                    preview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
                            startActivity(i);
                        }
                    });

                     */

                }

            }

        });


        return view;
    }
}