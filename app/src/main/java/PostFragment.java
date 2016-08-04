//package com.example.admin.fashion;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.media.MediaScannerConnection;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.provider.Settings;
//import android.util.Pair;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.support.v4.app.Fragment;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.support.v4.content.ContextCompat;
//
//
//
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import okhttp3.MediaType;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PostFragment extends Fragment {
//
//    private View v;
//    private static final int REQUEST_CODE_CAMERA = 1; /* カメラを判定するコード */
//    private static final int REQUEST_CODE_GALLERY = 2; /* ギャラリーを判定するコード */
//    private Bitmap bitmap;
//    private byte[] bytes;
//    private Uri bitmapUri;
//    private EditText body;
//    private ImageButton postButton;
//    private ImageButton selectImageButton;
//    private ImageButton imageButton;
//    private ArrayAdapter parentSpinnerAdapter, childSpinnerAdapter;
//    private Spinner parentSpinner, childSpinner;
//    private List<me.myreco.up.CategoryApi.Result> categories;
//    private RequestBody caption;
//    private RequestBody category;
//    private RequestBody img;
//    private String aToken;
//    private ProgressDialog progressDialog;
//    private static final long CLICK_DELAY = 1000;
//    private static long OLD_CLICK_TIME;
//    private FirebaseAnalytics FirebaseAnalytics;
//    private ArrayList<Pair<String, String>> parentList, childList;
//    private List<Child> child;
//    private Map<String, ArrayList> childMap;
//
//    @Override
//    public View onCreateView(
//            LayoutInflater inflater,
//            ViewGroup container,
//            Bundle savedInstanceState) {
//
//        v = inflater.inflate(R.layout.post_fragment, container, false);
//
//        FirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
//        FirebaseAnalytics.logEvent("PostFragment", null);
//
//        return v;
//    }
//
//
//    public void onResume() {
//        super.onResume();
//
//        body = (EditText) v.findViewById(R.id.body);
//        postButton = (ImageButton) v.findViewById(R.id.postButton);
//        selectImageButton = (ImageButton) v.findViewById(R.id.selectImgButton);
//        imageButton = (ImageButton) v.findViewById(R.id.imageButton);
//        parentSpinner = (Spinner) v.findViewById(R.id.parent_spinner);
//        childSpinner = (Spinner) v.findViewById(R.id.child_spinner);
//
//        SavePreference savePreference = new SavePreference();
//        aToken = savePreference.load_aToken(getActivity());
//
//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setIndeterminate(true);
//
//        //テキストエリアからタブにフォーカスが移るのを回避
//        View.OnTouchListener focus_listener = new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.requestFocusFromTouch();
//                return false;
//            }
//        };
//
//        body.setOnTouchListener(focus_listener);
//
//        final CategoryApi categoryApi = ServiceGenerator.createService(CategoryApi.class);
//        Call<Category> call = categoryApi.getCategory();
//
//        call.enqueue(new Callback<Category>() {
//            @Override
//            public void onResponse(Call<Category> call, Response<Category> response) {
//                categories = response.body().getResults();
//                parentList = new ArrayList();
//                child = new ArrayList();
//                childMap = new HashMap();
//                for (int i = 0; i < categories.size(); i++){
//                    if (categories.get(i).getAllowPost()) {
//                        String label = categories.get(i).getLabel();
//                        String id = categories.get(i).getId().toString();
//                        parentList.add(new Pair(id, label));
//                        if (!categories.get(i).getChildren().isEmpty()){
//                            childList = new ArrayList();
//                            child = categories.get(i).getChildren();
//                            for (int j = 0; j < child.size(); j++){
//                                String parent_id = child.get(j).getParent().getId().toString();
//                                String child_label = child.get(j).getLabel();
//                                String child_id = child.get(j).getId().toString();
//                                childList.add(j, new Pair(child_id, child_label));
//                                childMap.put(parent_id, childList);
//                            }
//                        }
//                    }
//                }
//
//                // スピナー用のデータを取得
//                final ArrayList<Pair<String, String>> parentSpinnerItemList = new ArrayList();
//                int arrayLength = parentList.size();
//                parentSpinnerItemList.add(new Pair("0", "カテゴリを選択してください（必須）"));
//                for (int i = 0; i < arrayLength; i++) {
//                    parentSpinnerItemList.add(new Pair(parentList.get(i).first, parentList.get(i).second));
//                }
//
//                // スピナーにアダプターを設定
//                parentSpinnerAdapter = new KeyValuePairAdapter(getActivity(),
//                        R.layout.spinner_item,
//                        parentSpinnerItemList);
//                parentSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//                parentSpinner.setAdapter(parentSpinnerAdapter);
//                parentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        Integer parent_pos = parentSpinner.getSelectedItemPosition();
//                        final ArrayList<Pair<String, String>> childSpinnerItemList = new ArrayList();
//                        if(parent_pos != 0 && !childMap.get(parentSpinnerItemList.get(parent_pos).first.toString()).isEmpty()){
//                            childList = new ArrayList<Pair<String, String>>(childMap.get(parentSpinnerItemList.get(parent_pos).first.toString()));
//                            int arrayLength = childList.size();
//                            childSpinnerItemList.add(new Pair("0", "詳細カテゴリを選択してください（任意）"));
//                            childSpinner.setEnabled(true);
//                            for (int i = 0; i < arrayLength; i++) {
//                                childSpinnerItemList.add(new Pair(childList.get(i).first, childList.get(i).second));
//                            }
//                        }else if(parent_pos == 0){
//                            childSpinner.setEnabled(false);
//                            childSpinnerItemList.add(new Pair("0", "詳細カテゴリを選択してください（任意）"));
//                        }
//                        childSpinnerAdapter = new KeyValuePairAdapter(getActivity(),
//                                R.layout.spinner_item,
//                                childSpinnerItemList);
//                        childSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//                        childSpinner.setAdapter(childSpinnerAdapter);
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<Category> call, Throwable t) {
//
//            }
//        });
//
//        final String[] str_items = {"カメラで撮影", "ギャラリーの選択", "キャンセル"};
//        selectImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("写真をアップロード")
//                        .setItems(str_items, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                switch (which) {
//                                    case 0:
//                                        wakeupCamera(); // カメラ起動
//                                        break;
//                                    case 1:
//                                        wakeupGallery(); // ギャラリー起動
//                                        break;
//                                    default:
//                                        // キャンセルを選んだ場合
//                                        break;
//                                }
//                            }
//                        }).show();
//            }
//        });
//
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("写真をアップロード")
//                        .setItems(str_items, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                switch (which) {
//                                    case 0:
//                                        wakeupCamera(); // カメラ起動
//                                        break;
//                                    case 1:
//                                        wakeupGallery(); // ギャラリー起動
//                                        break;
//                                    default:
//                                        // キャンセルを選んだ場合
//                                        break;
//                                }
//                            }
//                        }).show();
//            }
//        });
//
//        postButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!isClickEvent()) return;
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
//                alert.setMessage("入力内容を投稿してもよろしいですか？")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                progressDialog.setMessage("投稿中...");
//                                progressDialog.show();
//
//                                if(parentSpinner.getSelectedItemPosition() != 0){
//                                    if (bitmap != null) {
//
//                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                                        bytes = baos.toByteArray();
//                                        img = RequestBody.create(MediaType.parse("image/png"), bytes);
//                                        caption = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(body.getText()));
//                                        if (childSpinner.getSelectedItemPosition() == 0) {
//                                            Pair<String, String> category_pair = (Pair<String, String>) parentSpinnerAdapter.getItem((parentSpinner.getSelectedItemPosition()));
//                                            category = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(category_pair.first));
//                                        }else{
//                                            Pair<String, String> category_pair = (Pair<String, String>) childSpinnerAdapter.getItem((childSpinner.getSelectedItemPosition()));
//                                            category = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(category_pair.first));
//                                        }
//                                        PostApi api = ServiceGenerator.createService(PostApi.class, aToken);
//                                        Call<Result> call = api.imgPost(caption, category, img);
//                                        call.enqueue(new Callback<Result>() {
//                                            @Override
//                                            public void onResponse(Call<Result> call, Response<Result> response) {
//                                                if (progressDialog.isShowing()) {
//                                                    progressDialog.dismiss();
//                                                    Toast.makeText(getActivity(), "投稿に成功しました", Toast.LENGTH_LONG).show();
//                                                }
//                                                body.setText("");
//                                                MainActivity activity = (MainActivity) getActivity();
//                                                activity.notifyFragmentChange();
//
//                                                FirebaseAnalytics.logEvent("PostButtonClicked", null);
//
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<Result> call, Throwable t) {
//                                                if (progressDialog.isShowing()) {
//                                                    progressDialog.dismiss();
//                                                }
//                                                Toast.makeText(getActivity(), "投稿に失敗しました", Toast.LENGTH_LONG).show();
//                                            }
//                                        });
//                                    } else {
//                                        if (progressDialog.isShowing()) {
//                                            progressDialog.dismiss();
//                                        }
//                                        new AlertDialog.Builder(getActivity())
//                                                .setTitle("エラー")
//                                                .setMessage("投稿する写真を選択してください")
//                                                .setPositiveButton("OK", null)
//                                                .show();
//                                    }
//                                }else{
//                                    if (progressDialog.isShowing()) {
//                                        progressDialog.dismiss();
//                                    }
//                                    new AlertDialog.Builder(getActivity())
//                                            .setTitle("エラー")
//                                            .setMessage("カテゴリを選択してください")
//                                            .setPositiveButton("OK", null)
//                                            .show();
//                                }
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                if(progressDialog.isShowing()){
//                                    progressDialog.dismiss();
//                                }
//                                //Noボタンが押された時の処理
//                                Toast.makeText(getActivity(), "投稿をキャンセルしました", Toast.LENGTH_LONG).show();
//                            }
//                        })
//                        .show();
//
//            }
//        });
//    }
//
//    protected void wakeupCamera() {
//        if ((ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) &&
//                (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
//            // 許可されている時の処理
//            File mediaStorageDir = new File(
//                    Environment.getExternalStoragePublicDirectory(
//                            Environment.DIRECTORY_PICTURES
//                    ), "myreco_up"
//            );
//            if (!mediaStorageDir.exists() & !mediaStorageDir.mkdir()) {
//                return;
//            }
//            String timeStamp = new SimpleDateFormat("yyyMMddHHmmss").format(new Date());
//            File mediaFile;
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator + timeStamp + ".JPG");
//            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            bitmapUri = Uri.fromFile(mediaFile);
//            i.putExtra(MediaStore.EXTRA_OUTPUT, bitmapUri); // 画像をmediaUriに書き込み
//            startActivityForResult(i, REQUEST_CODE_CAMERA);
//        } else {
//            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) && shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("パーミッションの説明")
//                        .setMessage("写真を撮るにはパーミッションが必要です")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                requestPermissions(new String[]{android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//                            }
//                        })
//                        .show();
//                return;
//            }else{
//                requestPermissions(new String[]{android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//                return;
//            }
//        }
//    }
//
//    protected void wakeupGallery() {
//        Intent i;
//        if (Build.VERSION.SDK_INT < 19) {
//            i  = new Intent(Intent.ACTION_GET_CONTENT);
//            i.setType("image/*"); // 画像のみが表示されるようにフィルターをかける
//        } else {
//            i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//            i.addCategory(Intent.CATEGORY_OPENABLE);
//            i.setType("image/*"); // 画像のみが表示されるようにフィルターをかける
//        }
//        startActivityForResult(i, REQUEST_CODE_GALLERY);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK) {
//            if (bitmap != null)
//                bitmap.recycle(); // 直前のBitmapが読み込まれていたら開放する
//
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 4; // 元の1/4サイズでbitmap取得
//
//            switch (requestCode) {
//                case 1: // カメラの場合
//                    bitmap = BitmapFactory.decodeFile(bitmapUri.getPath(), options);
//                    // 撮影した画像をギャラリーのインデックスに追加されるようにスキャンする。
//                    String[] paths = {bitmapUri.getPath()};
//                    String[] mimeTypes = {"image/*"};
//                    MediaScannerConnection.scanFile(getActivity(), paths, mimeTypes, new MediaScannerConnection.OnScanCompletedListener() {
//                        @Override
//                        public void onScanCompleted(String path, Uri uri) {
//                        }
//                    });
//                    break;
//                case 2: // ギャラリーの場合
//                    try {
//                        InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
//                        bitmap = BitmapFactory.decodeStream(inputStream, null, options);
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
//                    break;
//            }
//            imageButton.setImageDrawable(null);
//            imageButton.setImageBitmap(bitmap);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//
//        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//
//            if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("パーミッション取得エラー")
//                        .setMessage("今後は許可しないが選択されています。アプリ設定＞権限からチェックしてください")
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                openPermission();
//                            }
//                        })
//                        .show();
//            }
//        }else if(grantResults[1] != PackageManager.PERMISSION_GRANTED) {
//            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("パーミッション取得エラー")
//                        .setMessage("今後は許可しないが選択されています。アプリ設定＞権限からチェックしてください")
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                openPermission();
//                            }
//                        })
//                        .show();
//            }
//        }else{
//            File mediaStorageDir = new File(
//                    Environment.getExternalStoragePublicDirectory(
//                            Environment.DIRECTORY_PICTURES
//                    ), "myreco_up"
//            );
//            if (!mediaStorageDir.exists() & !mediaStorageDir.mkdir()) {
//                return;
//            }
//            String timeStamp = new SimpleDateFormat("yyyMMddHHmmss").format(new Date());
//            File mediaFile;
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator + timeStamp + ".JPG");
//            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            bitmapUri = Uri.fromFile(mediaFile);
//            i.putExtra(MediaStore.EXTRA_OUTPUT, bitmapUri); // 画像をmediaUriに書き込み
//            startActivityForResult(i, REQUEST_CODE_CAMERA);
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    //クリックイベントの実行可否
//    public static boolean isClickEvent() {
//        // 現在時間を取得する
//        long time = System.currentTimeMillis();
//
//        // 一定時間経過していなければクリックイベント実行不可
//        if (time - OLD_CLICK_TIME < CLICK_DELAY) {
//            return false;
//        }
//
//        // 一定時間経過したらクリックイベント実行可能
//        OLD_CLICK_TIME = time;
//        return true;
//    }
//
//    private void openPermission() {
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        //Fragmentの場合はgetContext().getPackageName()
//        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
//        intent.setData(uri);
//        getContext().startActivity(intent);
//    }
//
//}
//
//class KeyValuePairAdapter extends ArrayAdapter<Pair<String, String>> {
//
//    public KeyValuePairAdapter(Context context,
//                               int resourceId,
//                               ArrayList<Pair<String, String>> list) {
//        super(context, resourceId, list);
//    }
//
//    // スピナー表示用
//    @Override
//    public View getView(int pos, View convertView, ViewGroup parent) {
//        TextView textView = (TextView) super.getView(pos, convertView, parent);
//        textView.setText(getItem(pos).second);
//        return textView;
//    }
//
//    // スピナードロップダウン表示用
//    @Override
//    public View getDropDownView(int pos, View convertView, ViewGroup parent) {
//        TextView textView = (TextView) super.getDropDownView(pos,
//                convertView,
//                parent);
//        textView.setText(getItem(pos).second);
//        return textView;
//    }
//}