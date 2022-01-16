package com.ds.nofication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.nofication.Adapters.CustomMedicineInfoExpList;
import com.ds.nofication.Controllers.MedicineDkApiController;
import com.ds.nofication.Controllers.ReminderApiController;
import com.ds.nofication.Listeners.MedicineDkListener;
import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.Drug;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.DrugMedicineInfo;
import com.ds.nofication.Models.Backend.MedicineCard;
import com.ds.nofication.Models.Backend.MedicineInfo;
import com.ds.nofication.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicineInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicineInfoFragment extends Fragment implements MedicineDkListener, ReminderListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MedicineDkApiController medicineDkController;
    private ReminderApiController reminderController;
    private HashMap<String, ArrayList<MedicineInfo>> medicineInfoViewData;
    private ArrayList<Drug> drugs;
    LinearLayout parent;
    LayoutInflater _inflater;
    ViewGroup _container;

    public MedicineInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MedicineInfoFragment.
     */
    public static MedicineInfoFragment newInstance() {
        MedicineInfoFragment fragment = new MedicineInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicineInfoViewData = new HashMap<>();
        drugs = new ArrayList<>();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        reminderController = new ReminderApiController();
        reminderController.addListener(this);
        medicineDkController = new MedicineDkApiController();
        medicineDkController.addListener(this);

        reminderController.requestReminders(this.getContext(), "12345678912");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parent = new LinearLayout(getContext());
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        _inflater = inflater;
        _container = container;
        return parent;
    }


    private CustomMedicineInfoExpList MakeDrugMedicineInfoViewParent(LayoutInflater inflater, ViewGroup container, String title) {
        CustomMedicineInfoExpList medicineInfoTitleView = (CustomMedicineInfoExpList) inflater.inflate(R.layout.medicine_info_list_group, container, false);

        medicineInfoTitleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        medicineInfoTitleView.setOrientation(LinearLayout.VERTICAL);
        TextView t = (TextView) inflater.inflate(R.layout.medicine_info_list_item, container, false);
        t.setText(title);
        medicineInfoTitleView.setTitle(t);
        return medicineInfoTitleView;
    }

    private CustomMedicineInfoExpList MakeDrugMedicineInfoViewChild(LayoutInflater inflater, ViewGroup container, String title) {
        CustomMedicineInfoExpList medicineInfoTitleView = (CustomMedicineInfoExpList) inflater.inflate(R.layout.medicine_info_list_group, container, false);
        medicineInfoTitleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        medicineInfoTitleView.setOrientation(LinearLayout.VERTICAL);
        TextView t = (TextView) inflater.inflate(R.layout.medicine_list_info_description, container, false);
        t.setText(title);
        medicineInfoTitleView.setTitle(t);
        return medicineInfoTitleView;
    }

    private TextView MakeDrugMedicineInfoViewChildDescription(LayoutInflater inflater, ViewGroup container, String title) {

        TextView t = (TextView) inflater.inflate(R.layout.medicine_list_info_description_child, container, false);
        t.setText(title);
        return t;
    }

    @Override
    public void update(DrugMedicineInfo drugMedicineInfo) {
        for (Drug drug : drugs) {
            if (drug.getIdentifier().equals(drugMedicineInfo.getDrugId())) {
                medicineInfoViewData.put(drug.getName(), drugMedicineInfo.getMedicineInfoList());

            }
        }
        for (String drugName : medicineInfoViewData.keySet()) {
            CustomMedicineInfoExpList p = MakeDrugMedicineInfoViewParent(_inflater, _container, drugName);
            parent.addView(p);
            for (MedicineInfo medicineInfo : medicineInfoViewData.get(drugName)) {
                CustomMedicineInfoExpList t = MakeDrugMedicineInfoViewChild(_inflater, _container, medicineInfo.getTitle());
                p.addView(t);
                t.addView(MakeDrugMedicineInfoViewChildDescription(_inflater, _container, medicineInfo.getHtmlDataAsString()));
            }
        }
    }

    @Override
    public void update(MedicineCard medicineCard) {
        for (DrugMedication drugMed : medicineCard.getDrugMedications()) {
            drugs.add(drugMed.getDrug());
            Log.e("DrugMed", drugMed.getIdentifier() + " drug" + drugMed.getDrug().getIdentifier());
            medicineDkController.requestGetMedicine(getContext(), drugMed.getDrug().getIdentifier());
        }
    }

    @Override
    public void errorUpdate(String errorMessage) {

    }
}