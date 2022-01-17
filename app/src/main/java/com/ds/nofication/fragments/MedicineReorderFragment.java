package com.ds.nofication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.nofication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicineReorderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicineReorderFragment extends Fragment {

    public MedicineReorderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment, can add parameters if you wanna recieve data
     * from the activity creating this fragment
     * @return A new instance of fragment MedicineReorderFragment.
     */
    public static MedicineReorderFragment newInstance() {
        MedicineReorderFragment fragment = new MedicineReorderFragment();
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
        return inflater.inflate(R.layout.fragment_medicine_reorder, container, false);
    }
}