package com.example.admin.fashion;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by admin on 2016/08/02.
 */
public class SettingPrefActivity extends AppCompatActivity {
    static public final String[] PREF_COLOR_SETTING = {"color_setting","color_setting2","color_setting3","color_setting4","bcolor_setting1","bcolor_setting2","bcolor_setting3","bcolor_setting4"};


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

//        addPreferencesFromResource(R.xml.setting_pref);

        //PrefFragmentの呼び出し
        getFragmentManager().beginTransaction().replace(
                android.R.id.content, new PrefFragment()).commit();
    }

    //設定画面のPrefFragmentクラス
    public static class PrefFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_pref);
            for(int i = 0; i < 8;i++) {
                //Summaryを設定
//                Log.d("main","test");
                setSummaryFraction(i);
            }

        }

        //設定値が変更された時のリスナーを登録
        @Override
        public void onResume(){
            super.onResume();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.registerOnSharedPreferenceChangeListener(listener);
        }

        //設定値が変更された時のリスナー登録を解除
        @Override
        public void onPause(){
            super.onPause();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.unregisterOnSharedPreferenceChangeListener(listener);
        }

        //設定変更時にSummaryを更新
        private SharedPreferences.OnSharedPreferenceChangeListener listener
                = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                for(int i = 0; i<8;i++) {
                    if (key.equals(PREF_COLOR_SETTING[i])) {
                        setSummaryFraction(i);
                    }

                }
            }
        };

        //FractionのSummaryを設定
        private void setSummaryFraction(int i){
            Log.d("main",PREF_COLOR_SETTING[i]);
            ListPreference prefFraction = (ListPreference)findPreference(PREF_COLOR_SETTING[i]);
            prefFraction.setSummary(prefFraction.getEntry());

        }


    }
}
