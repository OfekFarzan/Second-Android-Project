package co.il.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    ImageView editedImage;
    EditText etMissionName, etDescription;
    Button saveButton, cancelButton;
    Switch checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editedImage = (ImageView) findViewById(R.id.editedImg);
        etMissionName = (EditText) findViewById(R.id.etMissionName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        saveButton = (Button) findViewById(R.id.btnSave);
        cancelButton = (Button) findViewById(R.id.btnCancel);
        checked = (Switch) findViewById(R.id.sChecked);

        checked.setOnCheckedChangeListener(this);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {

            String missionName = intent.getExtras().getString("MissionName");
            String description = intent.getExtras().getString("Description");
            boolean missionDone = intent.getExtras().getBoolean("MissionDone");
            etMissionName.setText(missionName);
            etDescription.setText(description);
            checked.setChecked(missionDone);
            if (checked.isChecked()) {
                editedImage.setImageResource(R.drawable.ic_baseline_radio_button_checked_24);
            } else
                editedImage.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == saveButton) {
            if (etMissionName.getText().toString().length() > 0 && etDescription.getText().toString().length() > 0) {
                Intent intent = new Intent();
                intent.putExtra("MissionName", etMissionName.getText().toString());
                intent.putExtra("Description", etDescription.getText().toString());
                intent.putExtra("MissionDone", checked.isChecked());
                setResult(RESULT_OK, intent);
                finish();
            } else
                Toast.makeText(this, "please fill all fields", Toast.LENGTH_LONG).show();
        } else if (cancelButton == view) {
            setResult(RESULT_CANCELED, null);
            finish();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            editedImage.setImageResource(R.drawable.ic_baseline_radio_button_checked_24);
        } else
            editedImage.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24);
    }
}