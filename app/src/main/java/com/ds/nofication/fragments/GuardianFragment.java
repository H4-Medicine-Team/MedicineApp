package com.ds.nofication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.nofication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuardianFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuardianFragment extends Fragment {


    public GuardianFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment, can add parameters if you wanna recieve data
     * from the activity creating this fragment
     *
     * @return A new instance of fragment GuardianFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuardianFragment newInstance() {
        GuardianFragment fragment = new GuardianFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guardian, container, false);
    }
}