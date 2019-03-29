package com.geosis.messageviewdemo;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;

public class MapActivity extends AppCompatActivity {

    private MapView mMapView = null;
    private AMap aMap=null;
    LatLng markerPosition = new LatLng(39.993308, 116.473258);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        if (aMap == null) {
            aMap=mMapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(markerPosition));
            aMap.moveCamera(CameraUpdateFactory.zoomTo(14f));
        }
        addGrowMarker();
    }

    // 添加带生长效果marker
    private void addGrowMarker() {
        View markerView = LayoutInflater.from(this).inflate(R.layout.icon_marker,mMapView,false);

        MarkerOptions options = new MarkerOptions();
        options.position(markerPosition);
        options.title("地震5.6级");
        options.icon(BitmapDescriptorFactory.fromView(markerView));
        Marker marker = aMap.addMarker(options);

        Animation markerAnimation = new ScaleAnimation(0, 1, 0, 1); //初始化生长效果动画
        markerAnimation.setDuration(500);  //设置动画时间 单位毫秒
        marker.setAnimation(markerAnimation);
        marker.startAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
