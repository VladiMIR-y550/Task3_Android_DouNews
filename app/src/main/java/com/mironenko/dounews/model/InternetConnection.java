package com.mironenko.dounews.model;

import android.content.Context;
import android.net.ConnectivityManager;

import java.sql.Connection;

public class InternetConnection {
    public static boolean checkConnection(Context context) {
    return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
