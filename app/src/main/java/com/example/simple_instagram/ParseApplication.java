package com.example.simple_instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // register parse models with Parse before we call Parse.initialize
        ParseObject.registerSubclass(Post.class);

        // set applicationId, and server server based on the values in the back4app settings.
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ye0SP8WkbGMM9EUhnTAxbNvcm7US2pax9b88KTDj")
                .clientKey("yUCtvm5nlKtMqtor0CNyuxFgopi0310XxLwifICt")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}

//public class ParseApplication extends Application {
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        // Use for troubleshooting -- remove this line for production
//        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
//
//        // Use for monitoring Parse OkHttp traffic
//        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
//        // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.networkInterceptors().add(httpLoggingInterceptor);
//
//        // set applicationId, and server server based on the values in the back4app settings.
//        // any network interceptors must be added with the Configuration Builder given this syntax
//        Parse.initialize(new Parse.Configuration.Builder(this)
//                .applicationId("myAppId") // should correspond to Application Id env variable
//                .clientKey("yourClientKey)  // should correspond to Client key env variable
//                        .server("https://parseapi.back4app.com").build());
//    }
//}