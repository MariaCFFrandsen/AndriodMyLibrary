package com.github.listsapp.view.lookupbook;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
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

import com.github.listsapp.view.gbook_details.GBookDetailsFragment;
import com.github.listsapp.R;

public class GoogleBooksFragment extends Fragment implements GoogleBooksAdapter.OnListItemClickListener {

    private GoogleBooksViewModel searchViewModel;
    private AppCompatButton search;
    private EditText search_box;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lookupbook, container, false);
        search = view.findViewById(R.id.button_search);
        search_box = view.findViewById(R.id.search_box);
        searchViewModel = new ViewModelProvider(this).get(GoogleBooksViewModel.class);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home Library");

        search.setOnClickListener(v -> {
            String keyword = search_box.getText().toString();
            if(!keyword.equals(""))
            {
                searchViewModel.search(keyword);
            }
            else
            {
                Toast.makeText(getContext(), "Please enter search word(s)", Toast.LENGTH_SHORT).show();
            }

        });

        RecyclerView recyclerView = view.findViewById(R.id.lookup_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        GoogleBooksAdapter adapter = new GoogleBooksAdapter(getContext(), this);
        searchViewModel.getSearchedBooks().observe(getViewLifecycleOwner(), adapter::updateGBookList);

        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //method used to switch between fragments in a onclicklistener
        GBookDetailsFragment second = new GBookDetailsFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, second);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}