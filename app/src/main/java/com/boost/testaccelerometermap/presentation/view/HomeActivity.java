package com.boost.testaccelerometermap.presentation.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.boost.testaccelerometermap.Constants;
import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.presentation.view.map.MapFragment;
import com.boost.testaccelerometermap.presentation.view.statistics.DataStatisticFragment;
import com.google.android.gms.maps.GoogleMap;

public class HomeActivity extends AppCompatActivity implements MapFragment.OnFragmentMapCallback, DataStatisticFragment.OnFragmentDataStatisticCallback {
    private static final String TAG = "HomeActivity";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setNewFragment(MapFragment.newInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_history) {
            setNewFragment(DataStatisticFragment.newInstance());
        }
        return true;
    }

    private void setNewFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.fl_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onCallback() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CHECK_LOCATION_SETTINGS && resultCode == Activity.RESULT_OK) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(MapFragment.class.getSimpleName());
            if (fragment != null && fragment instanceof MapFragment) {
                ((MapFragment) fragment).onSettingsAccepted();
            }

        }
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
    }
}
