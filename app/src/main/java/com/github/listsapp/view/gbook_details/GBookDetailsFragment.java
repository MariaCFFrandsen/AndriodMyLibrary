package com.github.listsapp.view.gbook_details;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.listsapp.R;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.api.GBook;
import com.github.listsapp.util.callbackinterfaces.CallBack;
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
    private AppCompatButton add_gbook;
    private GBook gBook;


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
        add_gbook = view.findViewById(R.id.button_addgbook);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home Library");

        viewModel = new ViewModelProvider(this).get(GBookDetailsViewModel.class);


        add_gbook.setOnClickListener( v -> {
                    Book book = Book.CreateBookFromGBook(gBook);
                    String imagename = book.getTitle() + System.currentTimeMillis() + "." + getFileExtension(Uri.parse(gBook.getVolumeInfo().getImageLinks().getThumbnail()));
                    viewModel.addGBookToLibrary(book, gBook.getVolumeInfo().getImageLinks().getThumbnail(), imagename, new CallBack() {
                        @Override
                        public void makeToast(String message) {
                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });
                });

        GBookDetailsViewModel.getgBookMutableLiveData().observe(getViewLifecycleOwner(), new Observer<GBook>() {
            @Override
            public void onChanged(GBook gBook) {
                if(gBook.getVolumeInfo() != null)
                {
                    System.out.println(gBook.getVolumeInfo().getTitle());
                    setGBook(gBook);
                    booktitle.setText(gBook.getVolumeInfo().getTitle());
                    author.setText(gBook.getVolumeInfo().getListToString(gBook.getVolumeInfo().getAuthors()));
                    averageRating.setText("Rating: " + gBook.getVolumeInfo().getAverageRating());
                    publishDate.setText(gBook.getVolumeInfo().getPublisher());
                    categories.setText(gBook.getVolumeInfo().getListToString(gBook.getVolumeInfo().getCategories()));
                    description.setText(gBook.getVolumeInfo().getDescription());
                    if(gBook.getVolumeInfo().getImageLinks() != null && gBook.getVolumeInfo().getImageLinks().getThumbnail() != null)
                    Glide.with(getContext()).load(gBook.getVolumeInfo().getImageLinks().getThumbnail()).placeholder(R.drawable.coverplaceholder).into(thumbnail);
                }

            }

        });


        return view;
    }

    public void setGBook(GBook gBook)
    {
        this.gBook = gBook;
    }


    private String getFileExtension(Uri uri)
    {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(cr.getType(uri));
    }


}