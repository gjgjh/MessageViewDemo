package com.geosis.messageviewdemo;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;

public class MapActivity extends AppCompatActivity {

    private MapView mMapView = null;
    private AMap aMap=null;
    private BottomSheetBehavior mBottomSheetBehavior=null;
    private LatLng markerPosition = null;
    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initWidgets(savedInstanceState);
        showMessage();
    }

    // 初始化控件
    private void initWidgets(Bundle savedInstanceState) {
        // 获取地图控件
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        // 获取messgae对象，并在信息窗体显示
        message=(Message)getIntent().getSerializableExtra("message_item");
        markerPosition=message.getM_location();

        // 获取信息窗体控件
        View bottomSheet = findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
    }

    // 可视化消息
    private void showMessage() {
        // 地图界面移动到指定位置
        if (aMap == null) {
            aMap=mMapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(markerPosition));
            aMap.moveCamera(CameraUpdateFactory.zoomTo(14f));
            UiSettings uiSettings=aMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
        }
        addGrowMarker();

        // 信息窗体从底部弹出显示
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                });
            }
        }).start();


        // 根据消息内容做出显示
        TextView textView;
        textView=(TextView)findViewById(R.id.earthquake_time);
        textView.setText(message.getM_time());
        textView=(TextView)findViewById(R.id.AdminRigion);
        textView.setText(message.getM_admin_region());
        textView=(TextView)findViewById(R.id.Location);
        textView.setText("经度"+message.getM_location().longitude+" 纬度"+message.getM_location().latitude);
        textView=(TextView)findViewById(R.id.rank);
        textView.setText(""+message.getM_rank());

        textView=(TextView)findViewById(R.id.description);
        switch (message.getM_description()){
            case 0:
                textView.setText("无震感");
                break;
            case 1:
                textView.setText("仅仅有感");
                break;
            case 2:
                textView.setText("可行走");
                break;
            case 3:
                textView.setText("站立不稳，行走困难");
                break;
            case 4:
                textView.setText("被地震摔倒");
                break;
            default:
                textView.setText("null");
                break;
        }

        textView=(TextView)findViewById(R.id.source);
        switch (message.getM_info_source()){
            case 0:
                textView.setText("听说");
                break;
            case 1:
                textView.setText("亲眼目睹");
                break;
            case 2:
                textView.setText("政府数据");
                break;
            case 3:
                textView.setText("估计");
                break;
            default:
                textView.setText("null");
                break;
        }

        textView=(TextView)findViewById(R.id.remark);
        textView.setText(message.getM_remark());
    }

    // 添加带生长效果marker
    private void addGrowMarker() {
        View markerView = LayoutInflater.from(this).inflate(R.layout.icon_marker,mMapView,false);

        MarkerOptions options = new MarkerOptions();
        options.position(markerPosition);
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
