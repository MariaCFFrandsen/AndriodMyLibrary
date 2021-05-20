package com.github.listsapp.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.listsapp.R;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.repository.dao.UserDAO;
import com.github.listsapp.util.callbackinterfaces.CallBack;
import com.github.listsapp.view.library.LibrarySearchAdapter;
import com.github.listsapp.view.login.LoginActivity;
import com.github.listsapp.view.login.LoginViewModel;
import com.github.listsapp.view.main.ActivityMainViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.core.Repo;

public class activity_main extends AppCompatActivity {

    NavController navController;
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationDrawer;
    Toolbar toolbar;
    private ActivityMainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewModel = new ViewModelProvider(this).get(ActivityMainViewModel.class);

        setupNavigation();
        toolbar.setTitle("Home Library");
        checkIfSignedIn();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_yourlibrary,
                R.id.nav_addbook,
                R.id.nav_lookupbook)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationDrawer, navController);

        TextView viewById = navigationDrawer.getHeaderView(0).findViewById(R.id.nav_drawer_username);
        System.out.println(viewModel == null);

        viewById.setText(viewModel.getUsername());

    }


    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void signOut(MenuItem item) {

        viewModel.signOut(new CallBack() {
            @Override
            public void makeToast(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void checkIfSignedIn() {
        LoginViewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
                Toast.makeText(this, message, Toast.LENGTH_SHORT ).show();

            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }



}