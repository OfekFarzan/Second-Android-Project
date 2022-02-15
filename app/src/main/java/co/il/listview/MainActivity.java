package co.il.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Mission> missionsList;
    MissionAdapter missionAdapter;
    Mission lastSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lvMissions);

        Mission m1 = new Mission("Math homework", "page 69/1-20", false);
        Mission m2 = new Mission("Cyber homework", "task 5", false);
        Mission m3 = new Mission("Physics homework", "pages 104-105/2-5", false);

        missionsList = new ArrayList<Mission>();
        missionsList.add(m1);
        missionsList.add(m2);
        missionsList.add(m3);

        missionAdapter = new MissionAdapter(this, 0, 0, missionsList);

        lv.setAdapter(missionAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastSelected = missionsList.get(position);
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("MissionName", lastSelected.getMissionName());
                intent.putExtra("Description", lastSelected.getDescription());
                intent.putExtra("MissionDone", lastSelected.isDone());
                startActivityForResult(intent, 0);

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Mission").setIcon(R.drawable.ic_baseline_warning_24)
                        .setCancelable(true).setMessage("Would you like to delete this task?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                missionsList.remove(position);
                                missionAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String missionName = data.getExtras().getString("MissionName");
                String description = data.getExtras().getString("Description");
                boolean done = data.getExtras().getBoolean("done");
                lastSelected.setMissionName(missionName);
                lastSelected.setDescription(description);
                lastSelected.setDone(done);
                missionAdapter.notifyDataSetChanged();
                Toast.makeText(this, "data saved", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "user cancel", Toast.LENGTH_LONG).show();
            }

        }
        else if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                String missionName = data.getExtras().getString("MissionName");
                String description = data.getExtras().getString("Description");
                boolean done = data.getExtras().getBoolean("done");
                missionsList.add(new Mission(missionName, description, done));
                missionAdapter.notifyDataSetChanged();
                Toast.makeText(this, "mission added", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "user cancel", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.iAdd:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 1);
        }
        return true;
    }
}