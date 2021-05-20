package com.github.listsapp.repository.dao;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.util.Book;
import com.github.listsapp.util.callback.CallBack;
import com.github.listsapp.util.callback.CallBackCheckTitle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    private static LibraryDAO libraryDAO;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private MutableLiveData<List<Book>> library;
    private final String data = "library";
    private StorageReference storageReference;
    private StorageTask storageTask;
    private MutableLiveData<List<Book>> currentlyReading;

    private LibraryDAO() {
        library = new MutableLiveData<>(new ArrayList<>());
        currentlyReading = new MutableLiveData<>(new ArrayList<>());

        database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference().child("users");
        storageReference = FirebaseStorage.getInstance("gs://homelibrary-c0594.appspot.com").getReference("images");
    }

    public static LibraryDAO getInstance() {
        if (libraryDAO == null)
            libraryDAO = new LibraryDAO();

        return libraryDAO;
    }

    public LiveData<List<Book>> getLibrary(String username) {
        List<Book> books = new ArrayList<>();
        if (username != null) {
            DatabaseReference refDB = databaseReference.child(username).child(data);

            refDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    books.clear();
                    for (DataSnapshot s : snapshot.getChildren()) {
                        Book value = s.getValue(Book.class);
                        books.add(value);

                    }
                    library.setValue(books);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("Firebase", "failure on retrieving books");

                }


            });


        }
        return library;
    }

    public void addBook(Book book, String displayName, CallBack callBack_addBook) {
        //adding book under root book.getTitle()
        databaseReference.child(displayName).child(data).child(book.getTitle()).setValue(book).addOnCompleteListener(v -> {
            callBack_addBook.makeToast("You have added " + book.getTitle() + " to your library!");
        });
    }

    public void removeBook(Book book, String displayName, CallBack callBack) {
        //deleting the book from firebase database
        databaseReference.child(displayName).child(data).child(book.getTitle()).removeValue().addOnCompleteListener(v -> {
            callBack.makeToast("You have deleted ");
        });

        //deleting images from firebase storage
        if (book.getImageUrl() != null) {
            StorageReference ref = storageReference.child(displayName + "/" + book.getName());
            Task<Void> delete = ref.delete();
            delete.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    System.out.println("hhehhehehehehehehe");
                    // file deleted successfully
                    Log.d("storage", "onSuccess: deleted image");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // error
                    Log.d("storage", "onFailure: did not delete file");
                }
            });
        }


    }

    public void editBook(Book book, String displayName, CallBack callBack) {
        //overwriting the variables for root book.getTitle()

        databaseReference.child(displayName).child(data).child(book.getTitle()).setValue(book).addOnCompleteListener(v -> {
            callBack.makeToast("You have edited your book!");
        });

    }

    public void uploadFile(String title, String username, String imagename, Uri imageUri) {
        //uploading file to storage using uri
        StorageReference reference = storageReference.child(username + "/" + imagename);
        storageTask = reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        databaseReference.child(username).child(data).child(title).child("name").setValue(imagename);
                        databaseReference.child(username).child(data).child(title).child("imageUrl").setValue(uri.toString());
                    }
                });

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Firebase storage", "failure on retrieving images");
            }

        });
    }

    public StorageTask getStorageTask() {
        return storageTask;
    }


    public void checkTitle(CallBackCheckTitle callBack, String title, String username) {
        //books are stored under their title
        //this method is used to make sure the user does not overwrite a book

        if (username != null) {
            Query query = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("users").child(username).child("library").orderByChild("title").equalTo(title);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    callBack.checkTitle(!(snapshot.getChildrenCount() > 0));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("Firebase", "failure on checking title");

                }


            });


        }
    }


}
