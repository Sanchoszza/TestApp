package com.android.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.android.testapp.R;

public class LoadPhotoFragment extends Fragment {

    public LoadPhotoFragment() {
        // Required empty public constructor
    }

    public static LoadPhotoFragment newInstance(String param1, String param2) {
        LoadPhotoFragment fragment = new LoadPhotoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_load_photo, container, false);
    }
}