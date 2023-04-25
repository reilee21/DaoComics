package com.example.daocomics;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.daocomics.model.User;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static final String SHARE_PREFERENCES_APP = "share_preferences_app";
    public static final String KEY_USER = "key_user" ;
    public static final String KEY_USER_PR0FILE ="key_user_profile" ;

    public static  String ACCOUNT_RMB_US_LOGIN ="account_rmb_us_login" ;
    public static  String ACCOUNT_RMB_PASS ="account_rmb_pass" ;
    public static String ACCOUNT_RMB_USER_NAME = "account_rmb_user_name";
    public static String ACCOUNT_RMB_USER_PHONENB = "account_rmb_user_phonenb";


    public static Bitmap convertFromAssets(Context context, String nameImage)
    {
        AssetManager assetManager = context.getAssets();
        try{
            InputStream inputStream = assetManager.open("images/"+nameImage);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
