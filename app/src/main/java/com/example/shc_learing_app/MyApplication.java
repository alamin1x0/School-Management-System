package com.example.shc_learing_app;

import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AudienceNetworkAds.initialize(this);
        FirebaseMessaging.getInstance().subscribeToTopic("all");
    }
}
