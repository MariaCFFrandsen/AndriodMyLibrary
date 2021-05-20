package com.github.listsapp.repository.dao;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.firebase.ui.auth.AuthUI;
import com.github.listsapp.util.callback.CallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserDAO extends LiveData<FirebaseUser> {
    private final FirebaseAuth.AuthStateListener listener = firebaseAuth -> setValue(firebaseAuth.getCurrentUser());
    private static Application app;

    public void setApplication(Application app)
    {
        this.app = app;
    }

    @Override
    protected void onActive() {
        super.onActive();
        FirebaseAuth.getInstance().addAuthStateListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        FirebaseAuth.getInstance().removeAuthStateListener(listener);
    }


    public static void signOut(CallBack callBackForSignOut) {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext()).addOnCompleteListener( v -> {

                    callBackForSignOut.makeToast("You have signed out");

        });

    }


}
