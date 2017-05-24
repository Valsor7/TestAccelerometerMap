package com.boost.testaccelerometermap.presentation.view.map;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.testaccelerometermap.MyApplication;
import com.boost.testaccelerometermap.R;
import com.boost.testaccelerometermap.dagger.DaggerMapComponent;
import com.boost.testaccelerometermap.dagger.MapModule;
import com.boost.testaccelerometermap.dagger.UtilsComponent;
import com.boost.testaccelerometermap.presentation.presenter.MapPresenterImpl;

import java.util.List;

import javax.inject.Inject;

public class MapFragment extends Fragment implements MapView {
    private static final String TAG = "MapFragment";

    private OnFragmentInteractionListener mListener;

    @Inject
    MapPresenterImpl mMapPresenter;

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 24.05.17 refactor
        UtilsComponent utilsComponent = ((MyApplication)getActivity().getApplication()).getAppComponent();
        DaggerMapComponent.builder().utilsComponent(utilsComponent).mapModule(new MapModule(getActivity())).build().inject(this);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mMapPresenter.onAttachView(this);
        mMapPresenter.getAllData();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mMapPresenter.onDetachView();
        mListener = null;
    }

    @Override
    public void showAll(List<String> markers) {
        Log.d(TAG, "showAll: " + markers.get(0));
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
