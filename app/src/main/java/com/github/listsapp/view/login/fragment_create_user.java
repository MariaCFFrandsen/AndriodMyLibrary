package com.github.listsapp.view.login;

import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.listsapp.R;
import com.github.listsapp.util.User;
import com.github.listsapp.viewmodel.LoginViewModel;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class fragment_create_user extends Fragment {
    private AppCompatButton button;
    private LoginViewModel loginViewModel;
    private EditText editText_username;
    private EditText editText_password;
    private EditText editText_passwordagain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.createuser_fragment, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        button = view.findViewById(R.id.button_signup);
        editText_username = view.findViewById(R.id.editText_createusername);
        editText_password = view.findViewById(R.id.editText_createpassword);
        editText_passwordagain = view.findViewById(R.id.editText_createpassword2);



        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();
                String password_again = editText_passwordagain.getText().toString();

                User user = new User(username, password);
                user.setPasswordAgain(password_again);

                Toast.makeText(getContext(), "Not created", Toast.LENGTH_SHORT).show();

                /*if(check)
                {
                    FragmentManager manager = getParentFragmentManager();
                    manager.popBackStack("first",
                            POP_BACK_STACK_INCLUSIVE);
                }
                else
                {
                    Toast.makeText(getContext(), "Not created", Toast.LENGTH_SHORT).show();
                }

                 */


            }
        });

        return view;
    }
}