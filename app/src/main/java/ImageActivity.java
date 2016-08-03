
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.fashion.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private final String uri = "http://api.atnd.org/events/?keyword=android&format=json";

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_fashioncalender, container, false);
            return rootView;
        }

        @Override
        public void onStart() {super.onStart();
            AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(new AsyncJsonLoader.AsyncCallback() {
                // 実行前
                public void preExecute() {
                }
                // 実行後
                public void postExecute(JSONObject result) {
                    if (result == null) {
                        showLoadError(); // エラーメッセージを表示
                        return;
                    }
                    try {
                        // 各 ATND イベントのタイトルを配列へ格納
                        ArrayList<Image> list = new ArrayList<>();
                        JSONArray eventArray = result.getJSONArray("fashion");
                        for (int i = 0; i < eventArray.length(); i++) {
                            JSONObject eventObj = eventArray.getJSONObject(i);
                            JSONObject event = eventObj.getJSONObject("fashion");
                            //Log.d("title", event.getString("title"));
                            Image image =  (ImageView)findViewById(R.id.firstImage);
                            list.add(image);
//                            list.add(event.getString("fashion"));
                        }
                        // ListView 用のアダプタを作成
                        ArrayAdapter<Image> arrayAdapter = new ArrayAdapter<>(
//                                getActivity(), android.R.layout.fashioncal, list
                                getActivity(), android.R.layout., list
                        );
                        // ListView にアダプタをセット
                        ListView listView = (ListView)getActivity().findViewById(R.id.listView);
                        listView.setAdapter(arrayAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showLoadError(); // エラーメッセージを表示
                    }
                }
                // 実行中
                public void progressUpdate(int progress) {
                }
                // キャンセル
                public void cancel() {
                }
            });
            // 処理を実行
            asyncJsonLoader.execute(uri);
        } // エラーメッセージ表示
        private void showLoadError() {
            Toast toast = Toast.makeText(getActivity(), "データを取得できませんでした。", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}