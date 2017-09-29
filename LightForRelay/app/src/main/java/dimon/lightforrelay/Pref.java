package dimon.lightforrelay;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimon on 2017/2/27.
 */

public class Pref {

    private Context cx;
    private static Pref instance;

    private Pref(Context cx) {
        this.cx = cx;
    }

    public static Pref getPref(Context cx) {
        if (instance == null) {
            synchronized (Pref.class) {
                if (instance == null) {
                    instance = new Pref(cx.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private SharedPreferences getSharedPreferencesComm() {
        String shareXml = "simple_xml";
        return cx.getSharedPreferences(shareXml, 0);
    }

    public void setFirstIn(boolean flag) {
        SharedPreferences settin = getSharedPreferencesComm();
        SharedPreferences.Editor editor = settin.edit();
        editor.putBoolean("first_in", flag);
        editor.apply();
    }

    public boolean getFirstIn() {
        SharedPreferences settin = getSharedPreferencesComm();
        return settin.getBoolean("first_in", true);

    }

    public void setGHFlag(boolean flag) {
        SharedPreferences settin = getSharedPreferencesComm();
        SharedPreferences.Editor editor = settin.edit();
        editor.putBoolean("gh", flag);
        editor.apply();
    }

    public boolean getGHFlag() {
        SharedPreferences settin = getSharedPreferencesComm();
        return settin.getBoolean("gh", false);
    }

    public void setGMFlag(boolean flag) {
        SharedPreferences settin = getSharedPreferencesComm();
        SharedPreferences.Editor editor = settin.edit();
        editor.putBoolean("gm", flag);
        editor.apply();
    }

    public boolean getGMFlag() {
        SharedPreferences settin = getSharedPreferencesComm();
        return settin.getBoolean("gm", false);
    }

    public void setGLFlag(boolean flag) {
        SharedPreferences settin = getSharedPreferencesComm();
        SharedPreferences.Editor editor = settin.edit();
        editor.putBoolean("gl", flag);
        editor.apply();
    }

    public boolean getGLFlag() {
        SharedPreferences settin = getSharedPreferencesComm();
        return settin.getBoolean("gl", false);
    }

    public void setCCFlag(boolean flag) {
        SharedPreferences settin = getSharedPreferencesComm();
        SharedPreferences.Editor editor = settin.edit();
        editor.putBoolean("cc", flag);
        editor.apply();
    }

    public boolean getCCFlag() {
        SharedPreferences settin = getSharedPreferencesComm();
        return settin.getBoolean("cc", false);
    }

    public void setCHFlag(boolean flag) {
        SharedPreferences settin = getSharedPreferencesComm();
        SharedPreferences.Editor editor = settin.edit();
        editor.putBoolean("ch", flag);
        editor.apply();
    }

    public boolean getCHFlag() {
        SharedPreferences settin = getSharedPreferencesComm();
        return settin.getBoolean("ch", false);
    }

    public void setNameList(List<String> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += ("#" + list.get(i));
        }
        SharedPreferences settin = getSharedPreferencesComm();
        SharedPreferences.Editor editor = settin.edit();
        editor.putString("light", str);
        editor.apply();
    }

    public List<String> getNameList() {
        List<String> list = new ArrayList<>();

        SharedPreferences settin = getSharedPreferencesComm();
        String str = settin.getString("light", "");
        if(TextUtils.isEmpty(str)){
            return list;
        }else{
            String[] li = str.split("#");
            for (int i = 1; i < li.length; i++) {
                list.add(li[i]);
            }
            return list;
        }

    }


}
