package bz.bdu.autoreply;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Brandon White on 5/6/2017.
 * Handles the DrivingResponses activity
 * Features include:
 * - Changing settings for driving responses
 */

public class DrivingResponsesActivity extends AppCompatActivity {

    private final String FILENAME = "DrivingResponses.settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_responses);
        loadSettings(this.findViewById(R.id.dest_eta_switch));
        updateSettings(this.findViewById(R.id.dest_eta_switch));
    }

    // updates "Driving Response" settings stored in internal storage
    public void updateSettings(View view) {
        File settingsFile;
        settingsFile = getDir(FILENAME, MODE_PRIVATE);

        // initialize settings array
        Map<String, String> map = new HashMap<String, String>();

        Switch dest_eta_switch = (Switch) findViewById(R.id.dest_eta_switch);
        Switch location_switch = (Switch) findViewById(R.id.location_switch);
        if(dest_eta_switch.isChecked())
            map.put("DEST_ETA","true");
        else
            map.put("DEST_ETA","false");

        if(location_switch.isChecked())
            map.put("LOCATION","true");
        else
            map.put("LOCATION","false");

        // check if settings file exists; create if not
        if(!settingsFile.exists()) {
            try {
                Log.d("AutoReply", "updateSettings: Creating settings file!");
                settingsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // file operation try block
        try {

            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            /*for(Map.Entry<String, String> entry : map.entrySet()){
                String line = entry.getKey() + "=" + entry.getValue() + System.getProperty("line.separator");
                fos.write(line.getBytes());
            }*/

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            fos.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadSettings(View view) {

        Switch dest_eta_switch = (Switch) findViewById(R.id.dest_eta_switch);
        Switch location_switch = (Switch) findViewById(R.id.location_switch);

        try {
            FileInputStream fis = openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map map = (Map) ois.readObject();

            if(map.get("DEST_ETA").equals("true") )
                dest_eta_switch.setChecked(true);
            else
                dest_eta_switch.setChecked(false);

            if(map.get("LOCATION").equals("true") )
                location_switch.setChecked(true);
            else
                location_switch.setChecked(false);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
