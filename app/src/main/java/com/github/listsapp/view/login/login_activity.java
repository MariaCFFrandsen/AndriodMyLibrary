package com.github.listsapp.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.github.listsapp.R;
import com.github.listsapp.viewmodel.LoginViewModel;

public class login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login_fragment first = new login_fragment();
        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.loginActivityLayout, first).commit();

    }
}