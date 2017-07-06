package com.boost.testaccelerometermap.presentation.view.map;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.data.model.Location;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.model.LocationToLatLngMapper;
import com.boost.testaccelerometermap.presentation.presenter.location.MapPresenterImpl;
import com.boost.testaccelerometermap.presentation.utils.TimeUtils;
import com.boost.testaccelerometermap.presentation.view.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.DexterBuilder;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapFragment extends BaseFragment implements
        GoogleMapView, OnMapReadyCallback {
    private static final String TAG = "MapFragment";
    private static final float STREET_ZOOM = 15;

    @BindView(R.id.map_view)
    MapView mGoogleMapView;

    @Inject
    MapPresenterImpl mMapPresenter;

    private OnFragmentMapCallback mListener;
    private GoogleMap mGoogleMap;
    // for optimization
    private List<LatLng> mLatLngList = new ArrayList<>();
    private List<LocationModel> mLocationModels = new ArrayList<>();
    private PolylineOptions mPolylineOptions = new PolylineOptions();

    public static MapFragment newInstance() {
        return newInstance(new ArrayList<>());
    }

    public static MapFragment newInstance(ArrayList<LocationModel> locations){
        if (locations == null){
            locations = new ArrayList<>();
        }
        Bundle args = new Bundle();
        args.putParcelableArrayList(Location.class.getSimpleName(), locations);
        return newInstance(args);
    }

    public static MapFragment newInstance(Bundle data){
        MapFragment fragment = new MapFragment();
        fragment.setArguments(data);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDomainComponent.inject(this);
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap(savedInstanceState);
    }

    private void initMap(Bundle savedInstanceState) {
        mGoogleMapView.onCreate(savedInstanceState);
        mGoogleMapView.getMapAsync(this);
    }




    @Override
    public void onStart() {
        super.onStart();
        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public void checkPermission(String permission) {
        Dexter.withActivity(getActivity()).withPermission(permission)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Log.d(TAG, "onPermissionGranted: ");
                        mMapPresenter.createLocationRequest();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }

    @Override
    public void showAll(List<AccelerometerData> markers) {
        Log.d(TAG, "showAll: " + markers.get(0));
    }

    @Override
    public void onLocationTriggered(android.location.Location location) {
        Log.d(TAG, "onLocationTriggered: " + location);
        if (mGoogleMap != null) {
            if (mLocationModels.isEmpty()) {
                Log.d(TAG, "onLocationTriggered: first time");
//                mLocationModels.add(new LocationModel(location));
            }

//            mMapPresenter.saveLocation(new Location(location));
            mLatLngList.add(LocationToLatLngMapper.convertToLatLng(location));
            if (isSameDay()) {
//                Log.d(TAG, "onLocationTriggered: size " + mLatLngList.size());
                drawTrackLine(mLatLngList);
            }
        }
    }

    @Override
    public void onLocationParsed(List<LatLng> latLngList) {
        drawTrackLine(latLngList);
    }

    private boolean isSameDay() {
        return !mLocationModels.isEmpty() && mLocationModels.get(0).getDayInMillis() == TimeUtils.getResetedDayInMillis();
    }

    private void drawTrackLine(List<LatLng> latLngList){
        mPolylineOptions.color(Color.BLUE);
        mPolylineOptions.addAll(latLngList);
        Polyline polyline = mGoogleMap.addPolyline(mPolylineOptions);
        polyline.setWidth(12);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngList.get(latLngList.size() - 1)));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.moveCamera(CameraUpdateFactory.zoomTo(STREET_ZOOM));
        if (getArguments() != null) {
            showHistory();
        }
    }

    private void showHistory() {
        mLocationModels.clear();
        mLocationModels = getArguments().getParcelableArrayList(Location.class.getSimpleName());
        if (mLocationModels != null && !mLocationModels.isEmpty()) {
            Log.d(TAG, "showHistory: " + mLocationModels.size());
            mMapPresenter.parseLocationsModel(mLocationModels);
        }
    }

    @OnClick(R.id.btn_start_service)
    public void onClickStart(){
        MyApplication.getApp().startService();
    }

    @OnClick(R.id.btn_stop_service)
    public void onStopService(){
        MyApplication.getApp().stopService();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
    public void onStop() {
        super.onStop();
        mMapPresenter.onDetachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        mGoogleMapView.onDestroy();
        mMapPresenter.onDetachView();
        mListener = null;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mGoogleMapView.onLowMemory();
    }

    public void onSettingsAccepted() {
        mMapPresenter.createLocationRequest();
    }

    @Override
    public void onError(Object error) {

    }

    public interface OnFragmentMapCallback {
        void onMapCallback();
    }
}
