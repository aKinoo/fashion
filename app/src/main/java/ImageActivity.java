
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.fashion.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ImageActivity extends ActionBarActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Image Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app:///http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Image Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app:///http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
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
        public void onStart() {
            super.onStart();
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
                        URL imageUrl = new URL("http://10.110.130.123/img/module_table_bottom.png");
                        InputStream imageIs;
                        imageIs = imageUrl.openStream();
                        Bitmap bmp = BitmapFactory.decodeStream(imageIs);
//                        return image;
//                        ArrayAdapter<Image> arrayAdapter = new ArrayAdapter<>(
////                                getActivity(), android.R.layout.fashioncal, list
//                                getActivity(), android.R.layout.list
//                        );
//                        // ListView にアダプタをセット
//                        ListView listView = (ListView)getActivity().findViewById(R.id.listView);
//                        listView.setAdapter(arrayAdapter);
//                        }catch (MalformedURLException e){
//                        return null;
                        // ListView 用のアダプタを作成

                        ImageView image = (ImageView) getActivity().findViewById(R.id.firstImage);
                        // 取得した画像をImageViewに設定します。
                        image.setImageBitmap(bmp);
                        return;

                    } catch (IOException e) {
                        return;
                    }
                }

                //                protected void onPostExecute;(Bitmap)result; {
//
//            }}
                // 実行中
                public void progressUpdate(int progress) {
                }

                // キャンセル
                public void cancel() {
                }
            });
            // 処理を実行
            asyncJsonLoader.execute(uri);
        }// エラーメッセージ表示

        private void showLoadError() {
            Toast toast = Toast.makeText(getActivity(), "データを取得できませんでした。", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // POST通信を実行（AsyncTaskによる非同期処理を使わないバージョン）
    private void exec_post() {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost/test.php");


        String responseData = null;

        try {

            HttpResponse response = client.execute(post);
            // 取得
            Log.d("name", String.valueOf(response));
        } catch(IOException e) {
            e.printStackTrace();
        }


}}