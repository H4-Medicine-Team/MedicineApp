package com.ds.nofication.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ds.nofication.Models.Backend.Drug;
import com.ds.nofication.Models.Backend.MedicineInfo;
import com.ds.nofication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ParentLevelAdapter extends BaseExpandableListAdapter {
    private final Context mContext;
    private final List<String> drugNames;
    private final HashMap<String, ArrayList<String>> mListData_SecondLevel_Map;
    private final HashMap<String, List<String>> mListData_ThirdLevel_Map;
    public ParentLevelAdapter(Context mContext, List<String> drugNames, HashMap<String, ArrayList<String>> drugListHashMap, List<MedicineInfo> thirdLevelInfo) {
        this.mContext = mContext;
        // Init second level data
        mListData_SecondLevel_Map = drugListHashMap;
        this.drugNames = drugNames;
        // THIRD LEVEL
        mListData_ThirdLevel_Map = new HashMap<>();
        for (MedicineInfo medicineInfo : thirdLevelInfo){
            mListData_ThirdLevel_Map.put(medicineInfo.getTitle(), Arrays.asList(medicineInfo.getHtmlData().clone()));
        }

    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        String parentNode = (String) getGroup(groupPosition);
        final ExpandableListView secondLevelExpListView = new CustomExpListView(this.mContext);
        secondLevelExpListView.setAdapter(new SecondLevelAdapter(this.mContext, mListData_SecondLevel_Map.get(parentNode), mListData_ThirdLevel_Map));
        secondLevelExpListView.setGroupIndicator(null);
        return secondLevelExpListView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }
    @Override
    public Object getGroup(int groupPosition) {
        return this.drugNames.get(groupPosition);
    }
    @Override
    public int getGroupCount() {
        return this.drugNames.size();
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.medicine_info_list_group, parent, false);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.medicineName);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    } }
