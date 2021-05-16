package com.github.listsapp.view.main;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.listsapp.R;
import com.github.listsapp.view.login.GBook_details_fragment;
import com.github.listsapp.view.main.adapters.SearchedBooksAdapter;
import com.github.listsapp.viewmodel.SearchViewModel;

public class fragment_lookupbook extends Fragment implements SearchedBooksAdapter.OnListItemClickListener {


    private SearchViewModel searchViewModel;
    private AppCompatButton search;
    private EditText search_box;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lookupbook, container, false);
        search = view.findViewById(R.id.button_search);
        search_box = view.findViewById(R.id.search_box);
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        search.setOnClickListener(v -> {
            String keyword = search_box.getText().toString();
            if(!keyword.equals(""))
            {
                searchViewModel.search(keyword);
            }
            else
            {
                Toast.makeText(getContext(), "Please enter search word", Toast.LENGTH_SHORT).show();
            }

        });

        RecyclerView recyclerView = view.findViewById(R.id.lookup_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        SearchedBooksAdapter adapter = new SearchedBooksAdapter(getContext(), this);
        searchViewModel.getSearchedBooks().observe(getViewLifecycleOwner(), adapter::updateGBookList);

        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        GBook_details_fragment second = new GBook_details_fragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.gbook_layout, second);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}