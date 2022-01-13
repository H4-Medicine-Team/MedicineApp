package com.ds.nofication.Adapters;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ds.nofication.R;

import java.util.HashMap;
import java.util.List;

public class SecondLevelAdapter extends BaseExpandableListAdapter
{
    private final Context mContext;
    private final List<String> medicineInfoTitleList;
    private final HashMap<String, List<String>> medicineDataList;
    public SecondLevelAdapter(Context mContext, List<String> medicineInfoTitleList, HashMap<String, List<String>> medicineDataList) {
        this.mContext = mContext;
        this.medicineInfoTitleList = medicineInfoTitleList;
        this.medicineDataList = medicineDataList;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return this.medicineDataList.get(this.medicineInfoTitleList.get(groupPosition)).get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.medicine_list_info_description, parent, false);
        }
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.expandedMedicineInfoDescription);
        txtListChild.setText(childText);
        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition)
    {
        try {
            return this.medicineDataList.get(this.medicineInfoTitleList.get(groupPosition)).size();
        } catch (Exception e) {
            return 0;
        }
    }
    @Override
    public Object getGroup(int groupPosition)
    {
        return this.medicineInfoTitleList.get(groupPosition);
    }
    @Override
    public int getGroupCount()
    {
        return this.medicineInfoTitleList.size();
    }
    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent)
    {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.medicine_info_list_item, parent, false);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.expandedMedicineInfo);
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
    }
}