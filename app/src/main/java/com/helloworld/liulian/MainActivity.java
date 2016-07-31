package com.helloworld.liulian;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class MainActivity extends FragmentActivity implements OnClickListener, AMapLocationListener {
    //声明四个Tab的布局文件
    private LinearLayout mTabHome;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    //声明四个Tab的ImageButton
    private ImageButton mHomeImgBtn;
    private ImageButton mFrdImg;
    private ImageButton mAddressImg;
    private ImageButton mSettingImg;

    //声明四个Tab分别对应的Fragment
    private HomeFragment mFragHome;
    private Fragment mFragFrd;
    private Fragment mFragAddress;
    private Fragment mFragSetting;

    public AMapLocationClient mAMapLocationClient = null;   //声明定位客户端类对象
    public AMapLocationClientOption mLocationOption = null; //声明AMapLocationClientOption对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();//初始化控件
        initEvents();//初始化事件
        if(mAMapLocationClient == null) initLocation();//初始化地图定位
        selectTab(R.id.id_tab_home);//默认选中第一个Tab
    }

    private void initEvents() {
        //初始化四个Tab的点击事件
        mTabHome.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
    }

    private void initViews() {
        //初始化四个Tab的布局文件
        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);

        //初始化四个ImageButton
        mHomeImgBtn = (ImageButton) findViewById(R.id.id_tab_home_imgbtn);
        mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);
        mSettingImg = (ImageButton) findViewById(R.id.id_tab_setting_img);
    }

    private void initLocation(){
        // 初始化定位客户端
        mAMapLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mAMapLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mAMapLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mAMapLocationClient.startLocation();
    }

    //处理Tab的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_home:
                selectTab(R.id.id_tab_home);//当点击的是主页的Tab就选中主页的Tab
                break;
            case R.id.id_tab_frd:
                selectTab(R.id.id_tab_frd);
                break;
            case R.id.id_tab_address:
                selectTab(R.id.id_tab_address);
                break;
            case R.id.id_tab_setting:
                selectTab(R.id.id_tab_setting);
                break;
        }
    }

    //进行选中Tab的处理
    private void selectTab(int i) {
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);

        switch (i) {
            case R.id.id_tab_home:
                //如果主页对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFragHome == null) {
                    mFragHome = new HomeFragment();
                    transaction.add(R.id.id_content, mFragHome);
                } else {
                    //如果主页对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFragHome);
                }
                break;
            case R.id.id_tab_frd:
                if (mFragFrd == null) {
                    mFragFrd = new FrdFragment();
                    transaction.add(R.id.id_content, mFragFrd);
                } else {
                    transaction.show(mFragFrd);
                }
                break;
            case R.id.id_tab_address:
                if (mFragAddress == null) {
                    mFragAddress = new AddressFragment();
                    transaction.add(R.id.id_content, mFragAddress);
                } else {
                    transaction.show(mFragAddress);
                }
                break;
            case R.id.id_tab_setting:
                if (mFragSetting == null) {
                    mFragSetting = new SettingFragment();
                    transaction.add(R.id.id_content, mFragSetting);
                } else {
                    transaction.show(mFragSetting);
                }
                break;
        }
        transaction.commit();
    }

    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mFragHome != null) {
            transaction.hide(mFragHome);
        }
        if (mFragFrd != null) {
            transaction.hide(mFragFrd);
        }
        if (mFragAddress != null) {
            transaction.hide(mFragAddress);
        }
        if (mFragSetting != null) {
            transaction.hide(mFragSetting);
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                mFragHome.onLocationChanged(aMapLocation);   //为主页地图定位
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mAMapLocationClient != null)
            mAMapLocationClient.onDestroy();
    }
}
