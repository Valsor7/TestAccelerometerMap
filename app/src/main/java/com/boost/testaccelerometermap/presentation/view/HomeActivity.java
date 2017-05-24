package com.boost.testaccelerometermap.presentation.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.view.map.MapFragment;
import com.google.android.gms.maps.GoogleMap;

public class HomeActivity extends AppCompatActivity implements MapFragment.OnFragmentMapCallback {
    private static final String TAG = "HomeActivity";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager()
                .beginTransaction().add(R.id.fl_container, MapFragment.newInstance())
                .commit();
    }

    @Override
    public void onCallback() {

    }
}
