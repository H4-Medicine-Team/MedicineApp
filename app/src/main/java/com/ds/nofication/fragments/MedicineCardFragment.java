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
     * this fragment, can add parameters if you wanna recieve data
     * from the activity creating this fragment
     * @return A new instance of fragment MedicineCardFragment.
     */

    public static MedicineCardFragment newInstance() {
        MedicineCardFragment fragment = new MedicineCardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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