package com.mironenko.dounews;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;

import java.sql.Connection;

public class InternetConnection {
    public static boolean checkConnection(@NonNull Context context) {
    return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
