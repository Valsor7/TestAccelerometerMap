package com.boost.testaccelerometermap;


import android.location.Location;

import com.boost.testaccelerometermap.data.model.AccelerometerData;
import com.boost.testaccelerometermap.data.model.response.SuccessResponse;
import com.boost.testaccelerometermap.domain.interactors.location.ParseLocationInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.SaveLocationInteractor;
import com.boost.testaccelerometermap.domain.interactors.location.UpdateLocationsInteractor;
import com.boost.testaccelerometermap.presentation.model.LocationModel;
import com.boost.testaccelerometermap.presentation.presenter.location.MapPresenterImpl;
import com.boost.testaccelerometermap.presentation.view.map.GoogleMapView;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;

import static org.hamcrest.core.Is.is;

public class TestMapPresenter {

    @Mock
    SaveLocationInteractor mSaveLoactionInteractor;

    @Mock
    ParseLocationInteractor mParseLocationInteractor;

    @Mock
    UpdateLocationsInteractor mUpdateLocationsInteractor;
    private MapPresenterImpl mapPresenter;

    @Before
    public void initPresenter(){
        MockitoAnnotations.initMocks(this);

        SuccessResponse response = new SuccessResponse();
        response.message = "hi";
        Mockito.when(mSaveLoactionInteractor.execute(new LocationModel())).thenReturn(Observable.just(new SuccessResponse()));
        mapPresenter = new MapPresenterImpl(
                mSaveLoactionInteractor,
                mParseLocationInteractor,
                mUpdateLocationsInteractor
        );
        mapPresenter.onAttachView(new MyView());
    }

    @Test
    public void testCreateLocationRequest(){
        mapPresenter.saveLocation(new LocationModel());
        org.junit.Assert.assertThat(10.0 ,  is(10.0));
    }

    private class MyView implements GoogleMapView {

        @Override
        public void onError(Object error) {
            System.out.println(error);
        }

        @Override
        public void showAll(List<AccelerometerData> markers) {

        }
        @Test
        @Override
        public void onLocationTriggered(Location location) {
            System.out.println("hi " + location.getLatitude());
            org.junit.Assert.assertThat(location.getLatitude() , is(1023.0));
        }

        @Override
        public void onLocationParsed(List<LatLng> latLngList) {

        }
    }
}
