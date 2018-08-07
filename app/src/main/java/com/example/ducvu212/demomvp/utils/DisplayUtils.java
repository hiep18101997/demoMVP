package com.example.ducvu212.demomvp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by CuD HniM on 18/08/07.
 */
public class DisplayUtils {

    public static void makeText(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
