package csci310.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Student;
import model.Tutee;
import model.Tutor;

public class SaveSharedPreference {
    static final String PREF_USER= "user";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUser(Context ctx, Student student)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(student);
        editor.putString(PREF_USER, json);
        editor.commit();
    }

    public static Student getUser(Context ctx)
    {
        Gson gson = new Gson();
        String json = getSharedPreferences(ctx).getString(PREF_USER, "");
        if(json.length() == 0)return null;
        else {
            Student obj = gson.fromJson(json, Student.class);
            if(obj.isTutee){
                Tutee tutee = (Tutee)gson.fromJson(json, Tutee.class);
                return tutee;
            }
            else{
                Tutor tutor = (Tutor)gson.fromJson(json, Tutor.class);
                return tutor;
            }
        }
    }
    public static void clearUser(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
