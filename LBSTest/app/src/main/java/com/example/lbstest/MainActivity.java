package com.example.lbstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //创建一个LocationClient实例
    public LocationClient mLocationClient;

    private TextView positionText;

    private MapView mMapView;

    //创建一个地图控制器，为显示所处位置做准备
    private BaiduMap mBaiduMap;
    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //接收一个全局context
        mLocationClient = new LocationClient(getApplicationContext());
        //注册一个监听器，当获取到位置信息的时候，回调该定位监听器
        mLocationClient.registerLocationListener(new MyLocationListener());
        //初始化地图
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        mMapView = findViewById(R.id.bmapView);

        //获取BaiduMap实例
        mBaiduMap = mMapView.getMap();

        //获得权限，使光标显示在地图上
        mBaiduMap.setMyLocationEnabled(true);

        positionText = findViewById(R.id.position_text_view);
        //创建一个空集合，将需要动态申请的权限，没授权的依次添加进来
        List<String> permissionList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        //判断集合是否为空，不为空则转换为数组，一次性申请权限
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        } else {
            requestLocation();
        }

    }

    private void requestLocation() {
        initLocation();
        //结果回调到监听器
        mLocationClient.start();
    }

    //实时更新定位
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //设定每5秒更新一次定位数据
        option.setScanSpan(1000);

        //设置为百度地图坐标体系，默认gcj02出来的位置误差太大
//        WGS-84：是国际标准，GPS坐标（Google Earth使用、或者GPS模块）
//        GCJ-02：中国坐标偏移标准，Google Map、高德、腾讯使用
//        BD-09：百度坐标偏移标准，Baidu Map使用
        option.setCoorType("bd09ll");

        //设定强制使用GPS定位
        //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);

        //获取当前位置的详细信息
        option.setIsNeedAddress(true);

        mLocationClient.setLocOption(option);
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
    protected void onDestroy() {
        super.onDestroy();
        //在活动被摧毁时一定要调用该方法停止定位，否则程序会在后台持续定位，消耗手机电量
        mLocationClient.stop();
        mMapView.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    //用于获取当前位置，并更新到地图上
    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(update);
            //缩放级别3~19之间，越高越精确
            update = MapStatusUpdateFactory.zoomTo(18f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }

        //设置显示我在地图上的光标信息
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        mBaiduMap.setMyLocationData(locationData);

    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            //用于判断是不是第一次定位
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }

            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度: ").append(bdLocation.getLatitude()).append("\n");
            currentPosition.append("经度: ").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("国家: ").append(bdLocation.getCountry()).append("\n");
            currentPosition.append("省: ").append(bdLocation.getProvince()).append("\n");
            currentPosition.append("市: ").append(bdLocation.getCity()).append("\n");
            currentPosition.append("区: ").append(bdLocation.getDistrict()).append("\n");
            currentPosition.append("街道: ").append(bdLocation.getStreet()).append("\n");
            currentPosition.append("定位方式: ");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络");
            }

            positionText.setText(currentPosition);

        }
    }
}