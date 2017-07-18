package com.boost.testaccelerometermap;


import android.location.Location;

import com.boost.testaccelerometermap.domain.interactors.location.ParseLocationInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.SaveLocationInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.UpdateLocationsInteractor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.presenter.location.MapPresenterImpl;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

@RunWith(JUnit4.class)
public class TestMapPresenter {

    @Mock
    SaveLocationInteractor mSaveLoactionInteractor;

    @Mock
    ParseLocationInteractor mParseLocationInteractor;

    @Mock
    UpdateLocationsInteractor mUpdateLocationsInteractor;

    @Mock
    Location mLocation;

    @Mock
    GoogleMapView mGoogleMapView;

    @Mock
    LocationModel mLocationModel;

    @Mock
    Throwable mThrowable;

    private MapPresenterImpl mMapPresenter;


    @Before
    public void initPresenter(){
        MockitoAnnotations.initMocks(this);
        mMapPresenter = new MapPresenterImpl(
                mSaveLoactionInteractor,
                mParseLocationInteractor,
                mUpdateLocationsInteractor
        );

        mMapPresenter.onAttachView(mGoogleMapView);
    }

    @Test
    public void testCreateLocationRequest(){
        Mockito.when(mUpdateLocationsInteractor.execute(null))
                .thenReturn(Observable.just(mLocation));

        mMapPresenter.createLocationRequest();
        Mockito.verify(mGoogleMapView).onLocationTriggered(mLocation);
    }

    @Test
    public void testCreateLocationRequestError(){
        Mockito.when(mUpdateLocationsInteractor.execute(null))
                .thenReturn(Observable.create(e -> e.onError(mThrowable)));

        mMapPresenter.createLocationRequest();
        Mockito.verify(mGoogleMapView).onError(mThrowable);
    }

    @Test
    public void testSaveLocation(){
        Mockito.when(mSaveLoactionInteractor.execute(mLocationModel)).thenReturn(Observable.just(new Object()));
        mMapPresenter.saveLocation(mLocationModel);
        Mockito.verify(mGoogleMapView).successfullySaved();
    }

    @Test
    public void testSaveLocationError(){
        Mockito.when(mSaveLoactionInteractor.execute(mLocationModel))
                .thenReturn(Observable.create(e -> e.onError(mThrowable)));

        mMapPresenter.saveLocation(mLocationModel);
        Mockito.verify(mGoogleMapView).onError(mThrowable);
    }

    @Test
    public void testParseLocationsModel(){
        List<LocationModel> locationModelList = new ArrayList<>();
        locationModelList.add(mLocationModel);

        List<LatLng> latLngList = new ArrayList<>();
        LatLng latLng = new LatLng(1, 1);
        latLngList.add(latLng);

        Mockito.when(mParseLocationInteractor.execute(locationModelList)).thenReturn(Observable.just(latLngList));
        mMapPresenter.parseLocationsModel(locationModelList);
        Mockito.verify(mGoogleMapView).onLocationParsed(latLngList);
    }

    @Test
    public void testParseLocationError(){
        List<LocationModel> locationModelList = new ArrayList<>();
        locationModelList.add(mLocationModel);

        Mockito.when(mParseLocationInteractor.execute(locationModelList))
                .thenReturn(Observable.create(e -> e.onError(mThrowable)));

        mMapPresenter.parseLocationsModel(locationModelList);
        Mockito.verify(mGoogleMapView).onError(mThrowable);
    }
}
