package com.ds.nofication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ds.nofication.Adapters.ParentLevelAdapter;
import com.ds.nofication.Controllers.MedicineDkApiController;
import com.ds.nofication.Controllers.ReminderApiController;
import com.ds.nofication.Listeners.MedicineDkListener;
import com.ds.nofication.Models.Backend.Drug;
import com.ds.nofication.Models.Backend.DrugMedicineInfo;
import com.ds.nofication.Models.Backend.MedicineDkDTO;
import com.ds.nofication.Models.Backend.MedicineInfo;
import com.ds.nofication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicineInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicineInfoFragment extends Fragment implements MedicineDkListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MedicineDkApiController medicineDkController;
    public MedicineInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicineInfoFragment.
     */
    public static MedicineInfoFragment newInstance(String param1, String param2) {
        MedicineInfoFragment fragment = new MedicineInfoFragment();
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
        medicineDkController = new MedicineDkApiController();
        medicineDkController.addListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine_info, container, false);
        ExpandableListView mExpandableListView = (ExpandableListView) view.findViewById(R.id.expandableListViewInfo);
        if (mExpandableListView != null) {
            ParentLevelAdapter parentLevelAdapter = new ParentLevelAdapter(this.getContext(), getDrugNamesMock(),getDrugMedicineInfoMock(), getMedicineMock());
            mExpandableListView.setAdapter(parentLevelAdapter);
        }
        return view;
    }

    public ArrayList<MedicineInfo> getMedicineMock(){
        String[] mockData = {"Ipren® anvendes til behandling af svage og moderate smerter, fx hovedpine og migræne, tandpine, muskel- og ledsmerter eller menstruationssmerter. Midlet dæmper desuden de vævsreaktioner (inflammation), som kan ses ved visse gigtsygdomme, eller som kan opstå efter et slag eller en forstuvning. Symptomerne på inflammation kan være hævelse, rødme, ømhed og smerter. Ipren® er et NSAID."};
        MedicineInfo medicineInfo = new MedicineInfo(mockData, "Indeholder");
        String[] mockData2 = {"Forsigtighed hos ældre pga. øget risiko for mavesår. Behovet for behandling bør genovervejes med jævne mellemrum.Forsigtighed ved svær overfølsomhed for mesalazin-præparater (midler mod tarmsygdom) pga. risiko for krydsallergi. NSAID kan skjule symptomer på alvorlige underliggende infektioner (fx lungebetændelse og skoldkopper), fordi midlerne bl.a. nedsætter feber og smerter. Får du NSAID i forbindelse med en infektion, bør du kontakte din læge, hvis symptomerne varer ved eller forværres. Nedsat leverfunktionMå ikke anvendes ved meget dårligt fungerende lever."};
        MedicineInfo medicineInfo2 = new MedicineInfo(mockData2, "Særlige advarsler");
        ArrayList<MedicineInfo> medicineInfoArrayList = new ArrayList<>();
        medicineInfoArrayList.add(medicineInfo);
        medicineInfoArrayList.add(medicineInfo2);
        return medicineInfoArrayList;
    }
    public List<String> getDrugNamesMock(){
        List<String> drugNames = new ArrayList<>();
        drugNames.add("Ipren");
        drugNames.add("Panodil");
        return drugNames;
    }
    public HashMap<String, ArrayList<String>> getDrugMedicineInfoMock(){
        ArrayList<DrugMedicineInfo> drugMedicineInfoArrayList = new ArrayList<>();
        DrugMedicineInfo drugMedicineInfo = new DrugMedicineInfo(new Drug("Ipren", "13"), getMedicineMock());
        drugMedicineInfoArrayList.add(drugMedicineInfo);
        HashMap<String, ArrayList<String>> returnHashMap = new HashMap<>();
        for (DrugMedicineInfo drugMedicineInfoHash : drugMedicineInfoArrayList){
            returnHashMap.put(drugMedicineInfoHash.getDrug().getName(), drugMedicineInfoHash.getMedicineInfoListTitles());
        }

        return returnHashMap;
    }

    @Override
    public void update(MedicineDkDTO medicineDkDTO) {

    }

    @Override
    public void errorUpdate(String errorMessage) {

    }
}