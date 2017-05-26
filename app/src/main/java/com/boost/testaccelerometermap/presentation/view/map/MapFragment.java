package com.boost.testaccelerometermap.presentation.view.map;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import com.boost.testaccelerometermap.presentation.presenter.MapPresenterImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment extends Fragment implements
        GoogleMapView,
        OnMapReadyCallback {
    private static final String TAG = "MapFragment";

    @BindView(R.id.map_view)
    MapView mGoogleMapView;

    @Inject
    MapPresenterImpl mMapPresenter;

    private OnFragmentMapCallback mListener;
    private GoogleMap mGoogleMap;

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
        // TODO: 24.05.17 refactor

        DaggerMapComponent.builder()
                .utilsComponent(MyApplication.getApp().getAppComponent())
                .mapModule(new MapModule()).build()
                .inject(this);

        if (getArguments() != null) {
        }
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
        mMapPresenter.getAllData();
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

    @Override
    public void showAll(List<AccelerometerData> markers) {
        Log.d(TAG, "showAll: " + markers.get(0));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // TODO: 24.05.17 refactor
        mGoogleMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mGoogleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public interface OnFragmentMapCallback {
        void onCallback();
    }
}
