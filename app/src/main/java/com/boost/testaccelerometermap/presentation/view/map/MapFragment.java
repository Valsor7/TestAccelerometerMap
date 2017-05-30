package com.boost.testaccelerometermap.presentation.view.map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.dagger.map.DaggerMapComponent;
import com.boost.testaccelerometermap.dagger.map.MapModule;
import com.boost.testaccelerometermap.presentation.model.AccelerometerData;
import com.boost.testaccelerometermap.presentation.model.LatLngLocation;
import com.boost.testaccelerometermap.presentation.model.LocationToLatLngMapper;
import com.boost.testaccelerometermap.presentation.presenter.MapPresenterImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment extends Fragment implements
        GoogleMapView, OnMapReadyCallback {
    private static final String TAG = "MapFragment";
    private static final float STREET_ZOOM = 15;

    @BindView(R.id.map_view)
    MapView mGoogleMapView;

    @Inject
    MapPresenterImpl mMapPresenter;

    private OnFragmentMapCallback mListener;
    private GoogleMap mGoogleMap;
    private List<LatLng> mLatLngList = new ArrayList<>();


    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentMapCallback) {
            mListener = (OnFragmentMapCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMapComponent.builder()
                .utilsComponent(MyApplication.getApp().getAppComponent())
                .mapModule(new MapModule(this)).build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mMapPresenter.onAttachView(this);
//        mMapPresenter.getAllAccelerometerData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap(savedInstanceState);
        mListener.onCallback();
    }

    private void initMap(Bundle savedInstanceState) {
        mGoogleMapView.onCreate(savedInstanceState);
        mGoogleMapView.getMapAsync(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapPresenter.createLocationRequest();
    }

    @Override
    public void showAll(List<AccelerometerData> markers) {
        Log.d(TAG, "showAll: " + markers.get(0));
    }

    @Override
    public void onLocationTriggered(Location location) {
        Log.d(TAG, "onLocationTriggered: " + location);
        if (mGoogleMap != null){
            mLatLngList.add(LocationToLatLngMapper.convertToLatLng(location));
            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.color(Color.RED);
            polylineOptions.addAll(mLatLngList);
            Polyline polyline = mGoogleMap.addPolyline(polylineOptions);
            polyline.setWidth(12);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLngList.get(mLatLngList.size() - 1)));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.moveCamera(CameraUpdateFactory.zoomTo(STREET_ZOOM));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
    }

    @Override
    public void onResume() {
        super.onResume();
        mGoogleMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGoogleMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // TODO: 29.05.17 stop location updates
        mGoogleMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mGoogleMapView.onLowMemory();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMapPresenter.onDetachView();
        mListener = null;
    }

    public void onSettingsAccepted() {
        mMapPresenter.createLocationRequest();
    }

    public interface OnFragmentMapCallback {
        void onCallback();
    }
}
