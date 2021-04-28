package com.github.listsapp.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.listsapp.view.main.activity_main;
import com.github.listsapp.R;
import com.github.listsapp.util.User;
import com.github.listsapp.viewmodel.LoginViewModel;


public class login_fragment extends Fragment {

    private AppCompatButton button;
    private LoginViewModel loginViewModel;
    private EditText editText_login_username;
    private EditText editText_login_password;
    private AppCompatButton button_login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        View view = inflater.inflate(R.layout.login_fragment, container, false);
        button = view.findViewById(R.id.button_create_user);
        editText_login_username = view.findViewById(R.id.editText_login_username);
        editText_login_password = view.findViewById(R.id.editText_login_password);
        button_login = view.findViewById(R.id.button_login);


        button.setOnClickListener(v -> {
            fragment_createuser second = new fragment_createuser();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.loginActivityLayout, second);
            transaction.addToBackStack("first");
            transaction.commit();
        });

        button_login.setOnClickListener(v -> {
            //TODO: virker ikke endnu
            //TODO: ret gui med textfields
            String username = editText_login_username.toString();
            String password =  editText_login_password.toString();

            User user = new User(editText_login_username.toString(), editText_login_password.toString());
            System.out.println(username + " username");
            System.out.println(password + " password");

            Context context = getActivity().getApplicationContext();
            Class destination = activity_main.class;
            Intent i = new Intent(context, destination);
            startActivity(i);


            /*

            User user = new User(username, password);

            LiveData<Boolean> login = loginViewModel.login(user);
            boolean check = login.getValue();
            System.out.println();
            if(check)
            {
                //Navigate to activity
                Toast.makeText(getContext(), "You have logged in!", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getContext(), "not logged in", Toast.LENGTH_SHORT).show();
                */
        });

        return view;
    }

    @Override
    public void onDestroy() {
        System.out.println("destroyed");
        super.onDestroy();
    }

    @Override
    public void onResume() {
        System.out.println("resumed");
        super.onResume();
    }
}