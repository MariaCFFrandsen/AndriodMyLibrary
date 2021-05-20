package com.github.listsapp.view.addbook;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.listsapp.R;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.callback.CallBack;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.callback.CallBackCheckTitle;
import com.github.listsapp.view.library.LibraryViewModel;
import com.google.firebase.storage.StorageTask;

public class AddBookFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 100;
    RatingBar ratingBar;
    private AppCompatButton button_save;
    private EditText editText_title;
    private EditText editText_author;
    private EditText editText_pagecount;
    private Switch switch_owned;
    private EditText editText_readstatus;
    private EditText editText_price;
    private LibraryViewModel libraryViewModel;
    private AppCompatButton button_uploadPictureGallery;
    private AppCompatButton button_uploadPictureCamera;
    private Uri imageUri;
    private ImageView imageView_bookcover;
    private AddBookViewModel addBookViewModel;

    public AddBookFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        libraryViewModel = new ViewModelProvider(this).get(LibraryViewModel.class);
        View view = inflater.inflate(R.layout.fragment_addbook, container, false);
        editText_title = view.findViewById(R.id.addbook_edit_title);
        editText_author = view.findViewById(R.id.addbook_edit_author);
        editText_price = view.findViewById(R.id.addbook_edit_price);
        editText_readstatus = view.findViewById(R.id.addbook_edit_read);
        switch_owned = view.findViewById(R.id.addbook_switch_owned);
        editText_pagecount = view.findViewById(R.id.addbook_edit_pageCount);
        ratingBar = view.findViewById(R.id.ratingbaraddbook);
        imageView_bookcover = view.findViewById(R.id.addbook_imageView);
        button_save = view.findViewById(R.id.button_save);
        button_uploadPictureCamera = view.findViewById(R.id.button_uploadFromCamera);
        button_uploadPictureGallery = view.findViewById(R.id.button_uploadPicture);
        addBookViewModel = new ViewModelProvider(this).get(AddBookViewModel.class);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home Library");

        button_save.setOnClickListener(v -> {
            saveAddedBook();
        });

        button_uploadPictureGallery.setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.setType("image/");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "pick an image"), GALLERY_REQUEST_CODE);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(getContext()).load(imageUri).into(imageView_bookcover);

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile(String title) {
        if (imageUri != null) {
            String imagename = title + System.currentTimeMillis() + "." + getFileExtension(imageUri);
            addBookViewModel.uploadFile(title, imagename, imageUri);
        } else {
            Toast.makeText(getContext(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveAddedBook() {
        if (editText_title.getText() != null) {
        }

        addBookViewModel.checkTitle(new CallBackCheckTitle() {
            @Override
            public void checkTitle(boolean check) {
                if (check && checkReadStatus(editText_readstatus.getText().toString()))
                {
                    saveAddBook();
                    clearFields();
                } else if(!check)
                    Toast.makeText(getContext(), "Enter a different title", Toast.LENGTH_SHORT).show();
                else if(!checkReadStatus(editText_readstatus.getText().toString()))
                    Toast.makeText(getContext(), "Enter read, unread or currently for status", Toast.LENGTH_SHORT).show();

            }
        }, editText_title.getText().toString(), Repository.getUsername());

    }

    public boolean checkReadStatus(String s)
    {
        boolean b =  s.equals("currently") || s.equals("read") || s.equals("unread");
        System.out.println(b);

        return b;
    }


    private void clearFields()
    {

        editText_title.setText("");
        editText_author.setText("");
        editText_pagecount.setText("");
        editText_price.setText("");
        editText_readstatus.setText("");
        switch_owned.setChecked(false);
        imageView_bookcover.setImageResource(R.drawable.coverplaceholder);

    }

    private void saveAddBook()
    {
        Book book = new Book();
        book.setTitle(editText_title.getText().toString());

        if (editText_author.getText() != null)
            book.setAuthor(editText_author.getText().toString());
        if (editText_readstatus.getText() != null) {
            String s = editText_readstatus.getText().toString();
            book.setReadStatus(s);
        }
        book.setOwned(switch_owned.isChecked());
        try {
            book.setPrice(Integer.parseInt(editText_price.getText().toString()));
        } catch (NumberFormatException e) {
            book.setPrice(0);
        }
        try {
            book.setPagecount(Integer.parseInt(editText_pagecount.getText().toString()));
        } catch (NumberFormatException e) {
            book.setPagecount(0);
        }

        book.setRating(ratingBar.getRating());

        StorageTask storageTask = libraryViewModel.getStorageTask();
        if (storageTask != null && storageTask.isInProgress()) {
            Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
        } else {
            addBookViewModel.addBook(book, new CallBack() {
                @Override
                public void makeToast(String message) {
                    Toast.makeText(getContext(), "You have added a book!", Toast.LENGTH_SHORT).show();

                }
            });
            uploadFile(editText_title.getText().toString());

        }
    }
}