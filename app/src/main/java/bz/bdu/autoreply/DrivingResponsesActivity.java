package bz.bdu.autoreply;

import android.Manifest;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Brandon White on 5/6/2017.
 * Handles the DrivingResponses activity
 * Features include:
 * - Changing settings for driving responses
 */

public class DrivingResponsesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_responses);

    }

}
