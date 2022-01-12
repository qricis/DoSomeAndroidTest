package com.coolweather.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.R;
import com.coolweather.android.gson.Forecast;
import com.coolweather.android.gson.Weather;
import com.coolweather.android.service.AutoUpdateService;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    public DrawerLayout mDrawerLayout;

    public SwipeRefreshLayout mSwipeRefreshLayout;

    private String weatherId;

    private ScrollView weatherLayout;

    private Button weatherTitleButton;

    private TextView weatherTitleCity;

    private TextView weatherTitleUpdateTime;

    private TextView weatherNowDegreeText;

    private TextView weatherNowInfoText;

    private LinearLayout weatherForecastLayout;

    private TextView weatherAQIText;

    private TextView weatherPM25Text;

    private TextView weatherSuggestionComfortText;

    private TextView weatherSuggestionCarWashText;

    private TextView weatherSuggestionSportText;

    private ImageView bingPicImg;

    /**
     * 获取控件实例，并尝试从本地缓存中读取数据
     * 若没有本地缓存，则从intent中获取weather_id，并调用requestWeather方法从服务器请求天气数据
     * 请求数据的时候需要将scrollview进行隐藏，不然会很奇怪
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 将状态栏隐藏掉
         * 调用getWindow().getDecorView()拿到当前的DecorView
         * 调用setSystemUiVisibility()方法来改变系统UI的显示(传入的参数表示活动的布局会显示在状态栏上面)
         * 调用setStatusBarColor()将状态栏颜色设置成透明
         * */
        if (Build.VERSION.SDK_INT >=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        //初始化各控件
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        weatherLayout = findViewById(R.id.weather_layout);
        weatherTitleButton = findViewById(R.id.weather_title_nav_button);
        weatherTitleCity = findViewById(R.id.weather_title_city);
        weatherTitleUpdateTime = findViewById(R.id.weather_title_update_time);
        weatherNowDegreeText = findViewById(R.id.weather_now_degree_text);
        weatherNowInfoText = findViewById(R.id.weather_now_info_text);
        weatherForecastLayout = findViewById(R.id.weather_forecast_layout);
        weatherAQIText = findViewById(R.id.weather_aqi_aqi_text);
        weatherPM25Text = findViewById(R.id.weather_aqi_pm25_text);
        weatherSuggestionComfortText = findViewById(R.id.weather_suggestion_comfort_text);
        weatherSuggestionCarWashText = findViewById(R.id.weather_suggestion_car_wash_text);
        weatherSuggestionSportText = findViewById(R.id.weather_suggestion_sport_text);
        bingPicImg = findViewById(R.id.bing_pic_img);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        SharedPreferences prefs  = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather",null);
        if (weatherString != null) {
            //有缓存时直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            weatherId = weather.mBasic.weatherId;
            showWeatherInfo(weather);
        } else {
            //无缓存时去服务器查询天气
            weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(weatherId);
            }
        });
        String bingPic = prefs.getString("bing_pic",null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImg);
        } else {
            loadBingPic();
        }
    }

    public void weatherTitleNavButton(View view) {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    /**
     * 根据天气id请求城市天气信息
     * */
    public void requestWeather(final String weatherId) {

        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=08f828b5a3ec4905a29156bf5f29136f";

        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }

    /**
     * 处理并展示Weather实体类中的数据
     * */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.mBasic.cityName;
        String updateTime = weather.mBasic.mUpdate.updateTime.split( " ")[1];
        String degree = weather.mNow.temperature + "℃";
        String weatherInfo = weather.mNow.mMore.info;
        weatherTitleCity.setText(cityName);
        weatherTitleUpdateTime.setText(updateTime);
        weatherNowDegreeText.setText(degree);
        weatherNowInfoText.setText(weatherInfo);
        weatherForecastLayout.removeAllViews();
        for (Forecast forecast : weather.mForecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_forecast,weatherForecastLayout,false);
            TextView dateText = view.findViewById(R.id.item_forecast_data_text);
            TextView infoText = view.findViewById(R.id.item_forecast_info_text);
            TextView maxText = view.findViewById(R.id.item_forecast_max_text);
            TextView minText = view.findViewById(R.id.item_forecast_min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.mMore.info);
            maxText.setText(forecast.mTemperature.max);
            minText.setText(forecast.mTemperature.min);
            weatherForecastLayout.addView(view);
        }
        if (weather.mAQI != null) {
            weatherAQIText.setText(weather.mAQI.mAQICity.aqi);
            weatherPM25Text.setText(weather.mAQI.mAQICity.pm25);
        }
        String comfort = "舒适度：" + weather.mSuggestion.mComfort.info;
        String carWash = "洗车指数：" + weather.mSuggestion.mCarWash.info;
        String sport = "运动指数：" + weather.mSuggestion.mSport.info;
        weatherSuggestionComfortText.setText(comfort);
        weatherSuggestionCarWashText.setText(carWash);
        weatherSuggestionSportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
    }

    /**
     * 加载必应每日一图
     * */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic",bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
}