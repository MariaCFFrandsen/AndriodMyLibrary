package com.github.listsapp.view.main;

import android.media.Rating;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.github.listsapp.R;

public class fragment_addbook extends Fragment {

    RatingBar ratingBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addbook, container, false);
        ratingBar = view.findViewById(R.id.ratingbaraddbook);
        return view;
    }
}