package com.example.admin.fashion;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 2016/08/03.
 */
public class SettingPrefActivityMain extends AppCompatActivity {
    static public final String PREF_TIME_SETTING = "time_setting";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

//        addPreferencesFromResource(R.xml.setting_pref);

        //PrefFragmentの呼び出し
        getFragmentManager().beginTransaction().replace(
                android.R.id.content, new PrefFragment()).commit();
    }

    //設定画面のPrefFragmentクラス
    public static class PrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_pref_main);
            setSummaryFraction();
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
                setSummaryFraction();
            }
        };

        //FractionのSummaryを設定
        private void setSummaryFraction(){
//            Log.d("main",PREF_COLOR_SETTING[i]);
            ListPreference prefFraction = (ListPreference)findPreference(PREF_TIME_SETTING);
            prefFraction.setSummary(prefFraction.getEntry());
        }
    }
}
