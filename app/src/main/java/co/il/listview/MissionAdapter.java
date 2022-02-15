package co.il.listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MissionAdapter extends ArrayAdapter<Mission>
{
    Context context;
    List<Mission> missions;

    public MissionAdapter (Context context,int resource, int textViewResourceId, List<Mission> missions)
    {
        super(context,resource,textViewResourceId,missions);

        this.context = context;
        this.missions = missions;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.customized_layout,parent,false);

        TextView tvMissionName = (TextView) view.findViewById(R.id.tvMissionName);
        TextView tvDescription = (TextView)view.findViewById(R.id.tvMissionDescription);
        ImageView ivmissionDone = (ImageView)view.findViewById(R.id.ivMission);
        Mission temp = missions.get(position);

        tvMissionName.setText(temp.getMissionName());
        tvDescription.setText(temp.getDescription());
        if (temp.isDone())
            ivmissionDone.setImageResource(R.drawable.ic_baseline_radio_button_checked_24);
        else
            ivmissionDone.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24);

        return view;
    }


}
