package com.github.listsapp.repository.dao;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.util.Book;
import com.github.listsapp.util.callbackinterfaces.CallBack;
import com.github.listsapp.util.callbackinterfaces.CallBackForAddGBook;
import com.github.listsapp.util.callbackinterfaces.CallBack_AddBook;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
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

    private LibraryDAO()
    {
        library = new MutableLiveData<>(new ArrayList<>());
        currentlyReading = new MutableLiveData<>(new ArrayList<>());

        database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference().child("users");
        storageReference = FirebaseStorage.getInstance("gs://homelibrary-c0594.appspot.com").getReference("images");
    }

    public static LibraryDAO getInstance(){
        if(libraryDAO == null)
            libraryDAO = new LibraryDAO();

        return libraryDAO;
    }

    public LiveData<List<Book>> getLibrary(String username)
    {
        List<Book> books = new ArrayList<>();
        if(username != null)
        {
            DatabaseReference refDB = databaseReference.child(username).child(data);

            refDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    books.clear();
                    for (DataSnapshot s: snapshot.getChildren()) {
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

            /*
            refDB.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    library.getValue().add(snapshot.getValue(Book.class));
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Book book = snapshot.getValue(Book.class);
                    List<Book> value = library.getValue();


                    for (Book b: value)
                    {
                        if(b.getTitle().equals(book.getTitle()))
                        {
                            library.getValue().remove(b);
                            library.getValue().add(book);
                        }
                    }



                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Book book = snapshot.getValue(Book.class);
                    for (Book b: library.getValue())
                    {
                        if(b.getTitle().equals(book.getTitle()))
                        {
                            library.getValue().remove(b);
                        }

                    }
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

             */


        }
        return library;
    }

    public void addBook(Book book, String displayName, CallBack_AddBook callBack_addBook)
    {
        //databaseReference.child(displayName).child(data).child(book.getTitle()).setValue(book);
        databaseReference.child(displayName).child(data).child(book.getTitle()).setValue(book).addOnCompleteListener(v -> {
            callBack_addBook.callBack_AddBook();
        });
    }


    public void addBook(Book book, String displayName, CallBackForAddGBook gBook)
    {
        //databaseReference.child(displayName).child(data).child(book.getTitle()).setValue(book);
        databaseReference.child(displayName).child(data).child(book.getTitle()).setValue(book).addOnCompleteListener(v -> {
           gBook.callBack_AddGBook();
        });
    }

    public void removeBook(Book book, String displayName, CallBack callBack)
    {
        databaseReference.child(displayName).child(data).child(book.getTitle()).removeValue().addOnCompleteListener(v -> {
            callBack.makeToast("You have deleted ");
        });
    }

    public void editBook(Book book, String displayName, CallBack callBack)
    {
        databaseReference.child(displayName).child(data).child(book.getTitle()).setValue(book).addOnCompleteListener(v -> {
            callBack.makeToast("You have edited your book!");
        });
        /*StorageReference ref = storageReference.child(displayName).child(book.getName());
        Task<Void> delete = ref.delete();
        delete.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // File deleted successfully
                Log.d("storage", "onSuccess: deleted image");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
                Log.d("storage", "onFailure: did not delete file");
            }
        });

         */
    }

    public void uploadFile(String title, String username, String imagename, Uri imageUri) {
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

        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        });
    }

    public StorageTask getStorageTask() {
        return storageTask;
    }



}
