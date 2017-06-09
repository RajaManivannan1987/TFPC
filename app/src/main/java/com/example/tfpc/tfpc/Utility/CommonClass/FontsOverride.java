package com.example.tfpc.tfpc.Utility.CommonClass;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;


/**
 * Created by Im033 on 5/17/2017.
 */

public class FontsOverride {
    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular=Typeface.createFromAsset(context.getAssets(),fontAssetName);
        replaceFont(staticTypefaceFieldName,regular);
    }

    private static void replaceFont(String staticTypefaceFieldName, Typeface regular) {
        try{
            final Field staticField=Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null,regular);

        }catch (NoSuchFieldException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
