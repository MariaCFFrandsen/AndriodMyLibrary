package com.github.listsapp.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.github.listsapp.R;
import com.github.listsapp.view.library.LibrarySearchAdapter;
import com.github.listsapp.view.home.activity_main;
import com.google.firebase.FirebaseApp;

import java.util.Arrays;
import java.util.List;

public class login_activity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 42;
    private AppCompatButton button;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        setFragmentLogin();
        FirebaseApp.initializeApp(this);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        checkIfSignedIn();

        button = findViewById(R.id.externallogins);

        button.setOnClickListener(v -> {
            signIn();
        });

    }

    private void setFragmentLogin() {
        login_fragment first = new login_fragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.loginActivityLayout, first).commit();
    }


    public void signIn()
    {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void checkIfSignedIn() {
     setUsernameAndGoMainActivity();
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, activity_main.class));
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void handleSignInRequest(int resultCode) {
        if (resultCode == RESULT_OK)
        {
            setUsernameAndGoMainActivity();
        }
        else
            Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
    }

    private void setUsernameAndGoMainActivity() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                goToMainActivity();
                viewModel.setDisplayName(user.getDisplayName());
                LibrarySearchAdapter.getInstance().getBooks(user.getDisplayName());
            }


        });
    }



}

