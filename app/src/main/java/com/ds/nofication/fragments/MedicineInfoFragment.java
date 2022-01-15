package com.ds.nofication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.nofication.Adapters.CustomMedicineInfoExpList;
import com.ds.nofication.Adapters.MedicineListAdapter;
import com.ds.nofication.Controllers.MedicineDkApiController;
import com.ds.nofication.Controllers.ReminderApiController;
import com.ds.nofication.DataPump.ExpandableMedicineListDataPump;
import com.ds.nofication.Listeners.MedicineDkListener;
import com.ds.nofication.Listeners.ReminderListener;
import com.ds.nofication.Models.Backend.Drug;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.DrugMedicineInfo;
import com.ds.nofication.Models.Backend.MedicineCard;
import com.ds.nofication.Models.Backend.MedicineDkDTO;
import com.ds.nofication.Models.Backend.MedicineInfo;
import com.ds.nofication.R;

import java.util.ArrayList;


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

    public MedicineInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
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
        LinearLayout parent = new LinearLayout(getContext());
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ArrayList<DrugMedicineInfo> drugMedicineInfos = getMedicineMock();
        for (DrugMedicineInfo drugMedicineInfo: drugMedicineInfos)
        {
            CustomMedicineInfoExpList p = MakeDrugMedicineInfoViewParent(inflater, container, drugMedicineInfo.getDrug().getName());
             parent.addView(p);
             for (MedicineInfo medicineInfo : drugMedicineInfo.getMedicineInfoList()){
                 CustomMedicineInfoExpList t = MakeDrugMedicineInfoViewChild(inflater, container, medicineInfo.getTitle());
                 p.addView(t);
                 t.addView(MakeDrugMedicineInfoViewChildDescription(inflater, container, medicineInfo.getHtmlDataAsString()));
             }
        }

        return parent;
    }

    public ArrayList<DrugMedicineInfo> getMedicineMock(){
        ArrayList<DrugMedicineInfo> drugMedicineInfoArrayList = new ArrayList<>();

        Drug drug = new Drug("Ipren", "123");
        String[] mockData = {"Ipren® anvendes til behandling af svage og moderate smerter, fx hovedpine og migræne, tandpine, muskel- og ledsmerter eller menstruationssmerter. Midlet dæmper desuden de vævsreaktioner (inflammation), som kan ses ved visse gigtsygdomme, eller som kan opstå efter et slag eller en forstuvning. Symptomerne på inflammation kan være hævelse, rødme, ømhed og smerter. Ipren® er et NSAID."};
        MedicineInfo medicineInfo = new MedicineInfo(mockData, "Indeholder");
        String[] mockData2 = {"Forsigtighed hos ældre pga. øget risiko for mavesår. Behovet for behandling bør genovervejes med jævne mellemrum.Forsigtighed ved svær overfølsomhed for mesalazin-præparater (midler mod tarmsygdom) pga. risiko for krydsallergi. NSAID kan skjule symptomer på alvorlige underliggende infektioner (fx lungebetændelse og skoldkopper), fordi midlerne bl.a. nedsætter feber og smerter. Får du NSAID i forbindelse med en infektion, bør du kontakte din læge, hvis symptomerne varer ved eller forværres. Nedsat leverfunktionMå ikke anvendes ved meget dårligt fungerende lever."};
        MedicineInfo medicineInfo2 = new MedicineInfo(mockData2, "Særlige advarsler");
        ArrayList<MedicineInfo> medicineInfoArrayList = new ArrayList<>();
        medicineInfoArrayList.add(medicineInfo);
        medicineInfoArrayList.add(medicineInfo2);

        DrugMedicineInfo drugMedicineInfo = new DrugMedicineInfo(drug,medicineInfoArrayList);
        Drug drug2 = new Drug("Panodil", "1234");
        String[] mockData3 = {"Panodil® anvendes til behandling af svage og moderate smerter, fx hovedpine og migræne, tandpine, muskel- og ledsmerter eller menstruationssmerter. Midlet dæmper desuden de vævsreaktioner (inflammation), som kan ses ved visse gigtsygdomme, eller som kan opstå efter et slag eller en forstuvning. Symptomerne på inflammation kan være hævelse, rødme, ømhed og smerter. Ipren® er et NSAID."};
        MedicineInfo medicineInfo3 = new MedicineInfo(mockData3, "Indeholder");
        String[] mockData4 = {"Forsigtighed hos ældre pga. øget risiko for mavesår. Behovet for behandling bør genovervejes med jævne mellemrum.Forsigtighed ved svær overfølsomhed for mesalazin-præparater (midler mod tarmsygdom) pga. risiko for krydsallergi. NSAID kan skjule symptomer på alvorlige underliggende infektioner (fx lungebetændelse og skoldkopper), fordi midlerne bl.a. nedsætter feber og smerter. Får du NSAID i forbindelse med en infektion, bør du kontakte din læge, hvis symptomerne varer ved eller forværres. Nedsat leverfunktionMå ikke anvendes ved meget dårligt fungerende lever."};
        MedicineInfo medicineInfo4 = new MedicineInfo(mockData4, "Særlige advarsler");
        ArrayList<MedicineInfo> medicineInfoArrayList2 = new ArrayList<>();
        medicineInfoArrayList2.add(medicineInfo3);
        medicineInfoArrayList2.add(medicineInfo4);
        DrugMedicineInfo drugMedicineInfo2 = new DrugMedicineInfo(drug2,medicineInfoArrayList2);

        drugMedicineInfoArrayList.add(drugMedicineInfo);
        drugMedicineInfoArrayList.add(drugMedicineInfo2);

        return drugMedicineInfoArrayList;
    }

    private CustomMedicineInfoExpList MakeDrugMedicineInfoViewParent(LayoutInflater inflater, ViewGroup container, String title){
        CustomMedicineInfoExpList medicineInfoTitleView = (CustomMedicineInfoExpList)inflater.inflate(R.layout.medicine_info_list_group, container, false);

        medicineInfoTitleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        medicineInfoTitleView.setOrientation(LinearLayout.VERTICAL);
        TextView t = (TextView)inflater.inflate(R.layout.medicine_info_list_item, container,false);
        t.setText(title);
        medicineInfoTitleView.setTitle(t);
        return medicineInfoTitleView;
    }
    private CustomMedicineInfoExpList MakeDrugMedicineInfoViewChild(LayoutInflater inflater, ViewGroup container, String title){
        CustomMedicineInfoExpList medicineInfoTitleView = (CustomMedicineInfoExpList)inflater.inflate(R.layout.medicine_info_list_group, container, false);
        medicineInfoTitleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        medicineInfoTitleView.setOrientation(LinearLayout.VERTICAL);
        TextView t = (TextView)inflater.inflate(R.layout.medicine_list_info_description, container,false);
        t.setText(title);
        medicineInfoTitleView.setTitle(t);
        return medicineInfoTitleView;
    }
    private TextView MakeDrugMedicineInfoViewChildDescription(LayoutInflater inflater, ViewGroup container, String title){

        TextView t = (TextView)inflater.inflate(R.layout.medicine_list_info_description_child, container,false);
        t.setText(title);
        return t;
    }
    @Override
    public void update(MedicineDkDTO medicineDkDTO) {

    }
    @Override
    public void update(MedicineCard medicineCard) {
        medicineCard.getDrugMedications();
    }

    @Override
    public void errorUpdate(String errorMessage) {

    }
}