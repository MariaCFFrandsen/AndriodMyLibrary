package com.github.listsapp.view.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.listsapp.R;
import com.github.listsapp.model.LibraryBookAdapter;
import com.github.listsapp.viewmodel.LibraryViewModel;
import androidx.appcompat.widget.Toolbar;

public class fragment_library extends Fragment implements LibraryBookAdapter.OnListItemClickListener, AdapterView.OnItemSelectedListener {


    LibraryViewModel viewModel;
    Spinner spinner;
    private String spinnerFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        spinnerFilter = "All";
        //recyclerview setup
        RecyclerView recyclerView = view.findViewById(R.id.library_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        LibraryBookAdapter adapter = new LibraryBookAdapter(this);
        recyclerView.setAdapter(adapter);

        //viewmodel setup
        viewModel = new ViewModelProvider(this).get(LibraryViewModel.class);
        viewModel.getSearchedBooks().observe(getViewLifecycleOwner(), adapter::updateList);

        //toolbar setup
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home Library");
        setHasOptionsMenu(true);


        //spinner setup
        spinner = view.findViewById(R.id.libary_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.book_filters, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchForBook(newText, spinnerFilter);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {

        fragment_bookdetails book = new fragment_bookdetails();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, book);
        transaction.addToBackStack("book");
        transaction.commit();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerFilter = parent.getItemAtPosition(position).toString();
        viewModel.searchForBook("", spinnerFilter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        System.out.println("first");
    }
}