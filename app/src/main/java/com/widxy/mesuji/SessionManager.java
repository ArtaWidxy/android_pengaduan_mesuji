package com.widxy.mesuji;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.widxy.mesuji.model.lihat.LihatData;
import com.widxy.mesuji.model.login.LoginData;

import java.util.HashMap;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String USER_ID = "user_id";
    public static final String NIK = "nik";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ROLES = "roles";

    public static final String TANGGAPAN = "tanggapan";
    public static final String DESCRIPTION = "description";

    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createViewSession(LihatData lihat){
        editor.putString(TANGGAPAN, lihat.getTanggapan());
        editor.putString(DESCRIPTION, lihat.getDescription());
        editor.commit();
    }

    public HashMap<String, String> getLihatDetail(){
        HashMap<String, String> lihat = new HashMap<>();
        lihat.put(TANGGAPAN, sharedPreferences.getString(TANGGAPAN, null));
        lihat.put(DESCRIPTION, sharedPreferences.getString(DESCRIPTION, null));
        return lihat;
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(USER_ID, user.getUser_id());
        editor.putString(NIK, user.getNik());
        editor.putString(NAME, user.getName());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(PHONE, user.getPhone());
        editor.putString(ROLES, user.getRoles());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getString(USER_ID,null));
        user.put(NIK, sharedPreferences.getString(NIK,null));
        user.put(NAME, sharedPreferences.getString(NAME,null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL,null));
        user.put(PHONE, sharedPreferences.getString(PHONE,null));
        user.put(ROLES, sharedPreferences.getString(ROLES,null));
        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
