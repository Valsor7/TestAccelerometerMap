package com.boost.testaccelerometermap.presentation.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.boost.testaccelerometermap.Constants;
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
                .beginTransaction().add(R.id.fl_container, MapFragment.newInstance(), MapFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onCallback() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CHECK_LOCATION_SETTINGS && resultCode == Activity.RESULT_OK){
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(MapFragment.class.getSimpleName());
            if (fragment != null && fragment instanceof MapFragment){
                ((MapFragment)fragment).onSettingsAccepted();
            }

        }
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
    }
}
