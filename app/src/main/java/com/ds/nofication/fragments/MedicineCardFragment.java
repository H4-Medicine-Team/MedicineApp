package com.ds.nofication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ds.nofication.Adapters.MedicineListAdapter;
import com.ds.nofication.Controllers.ReminderApiController;
import com.ds.nofication.DataPump.ExpandableMedicineListDataPump;
import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.Dosage;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.MedicineCard;
import com.ds.nofication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicineCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicineCardFragment extends Fragment implements ReminderListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ReminderApiController reminderController;
    private ExpandableListView expandableListView;
    private MedicineListAdapter medicineListAdapter;
    private List<DrugMedication> expandableDrugMedications;
    private HashMap<DrugMedication, List<Dosage>> drugMedicationListHashMap;

    final String Tag = "MedicineCardFragment";

    public MedicineCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicineCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicineCardFragment newInstance(String param1, String param2) {
        MedicineCardFragment fragment = new MedicineCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        reminderController = new ReminderApiController();
        reminderController.addListener(this);
        reminderController.requestReminders(this.getContext(), "12345678912");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment before returning, helps with getting reference to views on create.
        View view = inflater.inflate(R.layout.fragment_medicine_card, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        return view;
    }
    @Override
    public void update(MedicineCard medicineCard) {
        drugMedicationListHashMap = ExpandableMedicineListDataPump.getData(medicineCard);
        expandableDrugMedications = new ArrayList<DrugMedication>(drugMedicationListHashMap.keySet());
        medicineListAdapter = new MedicineListAdapter(this.getContext(), expandableDrugMedications, drugMedicationListHashMap);
        expandableListView.setAdapter(medicineListAdapter);
    }
    @Override
    public void errorUpdate(String errorMessage) {
        Log.e(Tag, "Error update: " + errorMessage);
    }
}