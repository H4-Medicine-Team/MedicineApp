package com.ds.nofication.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.ds.nofication.Models.Backend.Dosage;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

public class MedicineListAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<DrugMedication> expandableDrugMedications;
    private HashMap<DrugMedication, List<Dosage>> drugMedicationListHashMap;

    public MedicineListAdapter(Context context, List<DrugMedication> expandableListTitle, HashMap<DrugMedication, List<Dosage>> drugMedicationListHashMap)
    {
        this.context = context;
        this.expandableDrugMedications = expandableListTitle;
        this.drugMedicationListHashMap = drugMedicationListHashMap;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.drugMedicationListHashMap.get(this.expandableDrugMedications.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    //TODO: REMOVE WHEN MOCK IS NO LONGER NEEDED
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Dosage dosageListItem = (Dosage) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.medicine_list_item, null);
        }
        //TODO: REWRITE WHEN MOCK IS NO LONGER NEEDED
        TextView expandedTimeToTakeTextView = (TextView) convertView.findViewById(R.id.expandedMedicineTimeToTake);
        expandedTimeToTakeTextView.setText(dosageListItem.getInterval().getConsumptionTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        
        TextView expandedTimerTextView = (TextView) convertView.findViewById(R.id.expandedMedicineTimer);
        expandedTimerTextView.setText(""+ChronoUnit.MINUTES.between(dosageListItem.getInterval().getConsumptionTime(), LocalDateTime.now()));
        
        TextView expandedMedicineDateTextView = (TextView) convertView.findViewById(R.id.expandedMedicineDate);
        expandedMedicineDateTextView.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.drugMedicationListHashMap.get(this.expandableDrugMedications.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableDrugMedications.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableDrugMedications.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    //TODO: LOOK INTO THIS
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        DrugMedication drugMedication = (DrugMedication) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.medicine_card_list_group, null);
        }
        TextView drugMedicineNameTextView = (TextView) convertView.findViewById(R.id.medicineName);
        drugMedicineNameTextView.setTypeface(null, Typeface.BOLD);
        drugMedicineNameTextView.setText(drugMedication.getDrug().getName());

        //TODO: REMAKE WHEN FUNCTION TO GET NEXT TIME TO TAKE MEDICINE HAS BEEN IMPLEMENTED
        TextView drugMedicineNextTimeToTakeTextView = (TextView) convertView.findViewById(R.id.medicineNextTimeToTake);
        drugMedicineNextTimeToTakeTextView.setTypeface(null, Typeface.BOLD);
        drugMedicineNextTimeToTakeTextView.setText(drugMedication.getNextDosage().getInterval().getConsumptionTime().format(DateTimeFormatter.ofPattern("HH:mm")));

        //TODO: REMAKE WHEN FUNCTION TO GET MEDICINE COUNT HAS BEEN IMPLEMENTED
        TextView drugMedicineCountTextView = (TextView) convertView.findViewById(R.id.medicineCount);
        drugMedicineCountTextView.setTypeface(null, Typeface.BOLD);
        drugMedicineCountTextView.setText("15/20");
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
