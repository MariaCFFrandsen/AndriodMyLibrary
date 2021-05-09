package com.github.listsapp.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.github.listsapp.R;
import com.github.listsapp.view.main.activity_main;
import com.github.listsapp.viewmodel.LoginViewModel;
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

        login_fragment first = new login_fragment();
        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.loginActivityLayout, first).commit();
        FirebaseApp.initializeApp(this);
        viewModel = new LoginViewModel();
        checkIfSignedIn();

        button = findViewById(R.id.externallogins);

        button.setOnClickListener(v -> {
            signIn();
        });

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
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null)
                goToMainActivity();
        });
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
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
    }


}