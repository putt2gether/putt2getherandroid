package com.putt2gether.fragments.mygroups;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.putt2gether.R;
import com.putt2gether.adapter.NoInternetConnectionAdapter;
import com.putt2gether.crop_image.CropImage;
import com.putt2gether.crop_image.InternalStorageContentProvider;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 08/09/2016.
 */
public class MyGroupDetail extends AppCompatActivity implements MyGroupDetailAdapter.EditPlayerAdapterCallback {

    ImageView backBTN;
    private RecyclerView ddRecyclerView;
    private MyGroupDetailAdapter adapter;
    private RecyclerView.LayoutManager ddLayoutManager;
    private RelativeLayout editImageLay;
    private ImageView groupBgImage;

    private Uri mImageCaptureUri;
    private AlertDialog dialog;

    public static final String TEMP_PHOTO_FILE_NAME = "temp_image.jpg";
    public static final int REQUEST_CODE_GALLERY = 0x1;
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;
    private File mFileTemp;

    Bitmap photo;
    String strImage;

    RelativeLayout addGroupMemberBTN;
    TextView group_createdBy;
    EditText groupName;
    RelativeLayout saveBtn;
    RelativeLayout editNameBTN;
    LinearLayout parent;
    ArrayList<GroupDetailBean> list;
    ArrayList<String> removeIDS = new ArrayList<String>();
    String isGroupAdmin;
    View line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygroup_detail_activity);
        parent = (LinearLayout)findViewById(R.id.parent);


        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(this.getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }

        captureImageInitialization();

        line = (View)findViewById(R.id.lll);

        backBTN = (ImageView) findViewById(R.id.back_mygroup_detail);
        groupBgImage = (ImageView) findViewById(R.id.bg_image_mygroup);
        editImageLay = (RelativeLayout) findViewById(R.id.edit_image_lay);
        editImageLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               dialog.show();
            }
        });

        ddRecyclerView = (RecyclerView) findViewById(R.id.members_list_group);
        group_createdBy = (TextView) findViewById(R.id.group_createdby_detail);
        groupName = (EditText) findViewById(R.id.group_name_detail);

        saveBtn = (RelativeLayout) findViewById(R.id.save_group_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEditedGroup();
            }
        });

        editNameBTN = (RelativeLayout) findViewById(R.id.edit_mygroup_detail_name);
        editNameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (groupName.getText().length()>0) {
                    groupName.setSelection(groupName.getText().length());
                }

                groupName.setFocusableInTouchMode(true);
                groupName.setFocusable(true);
                groupName.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(groupName, InputMethodManager.SHOW_FORCED);
            }
        });


        ConnectionDetector connectionDetector = new ConnectionDetector(this);

        ddRecyclerView.setHasFixedSize(true);
        ddLayoutManager = new LinearLayoutManager(this);
        ddRecyclerView.setLayoutManager(ddLayoutManager);

        if (connectionDetector.isConnectingToInternet()) {
            getGroupMemberList();
        } else {
            Toast.makeText(getApplicationContext(), R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addGroupMemberBTN = (RelativeLayout) findViewById(R.id.addmembers_btn);

        addGroupMemberBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddMembersActivity.class);
                in.putExtra("groupId",getIntent().getStringExtra("groupID"));
                startActivity(in);
            }
        });
    }

    @Override
    protected void onResume() {
        ConnectionDetector con = new ConnectionDetector(this);
        if (con.isConnectingToInternet()) {
            getGroupMemberList();
        }else {
            Toast.makeText(getApplicationContext(),"No internet connections",Toast.LENGTH_SHORT).show();
        }
        super.onResume();
    }

    public void getGroupMemberList() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);
        String group_id = getIntent().getStringExtra("groupID");

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("group_id", group_id);
            jsonObject.putOpt("version", "2");

            Log.v("kljijloj", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GROUP_DETAIL_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getMemeberListResponse(response);
                Log.e("Group Detail URL", "Group Detail URL" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "Group Detail URL = " + PUTTAPI.GROUP_DETAIL_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getMemeberListResponse(JSONObject response) {

        list = new ArrayList<GroupDetailBean>();
        removeIDS = new ArrayList<String>();

        if (response != null) {

            parent.setVisibility(View.VISIBLE);

            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    String group_Name = jsonObject.getString("group_name");
                    String create_date = jsonObject.getString("create_data");
                    String groupImage = jsonObject.getString("profile_image");
                    isGroupAdmin = jsonObject.getString("is_group_admin");

                    if (isGroupAdmin.equalsIgnoreCase("1")){
                        addGroupMemberBTN.setVisibility(View.VISIBLE);
                        line.setVisibility(View.VISIBLE);
                    }else {
                        addGroupMemberBTN.setVisibility(View.GONE);
                        line.setVisibility(View.GONE);
                    }

                    if (create_date.length() > 1) {
                        group_createdBy.setText(create_date);
                    }
                    if (group_Name.length() > 0) {
                        groupName.setText(group_Name);
                    }

                    Log.v("rhskthoi",groupImage);


                    if ((groupImage != null && groupImage.length() > 10) && !groupImage.equalsIgnoreCase("http://clients.vfactor.in/puttdemo/api_v2/uploads/group/thumb/noimage.png")) {
                        Picasso.with(this).load(groupImage).into(groupBgImage);
                    }

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        GroupDetailBean listBean = new GroupDetailBean();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        listBean.setDisplay_name(jsonObject1.getString("display_name"));
                        listBean.setPhoto_url(jsonObject1.getString("photo_url"));
                        listBean.setSelf_handicap(jsonObject1.getString("self_handicap"));
                        listBean.setMember_id(jsonObject1.getString("member_id"));
                        listBean.setIs_admin(jsonObject1.getString("is_admin"));

                        list.add(listBean);

                    }
                } else {
                    String msg = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (list.size() > 0) {
            adapter = new MyGroupDetailAdapter(MyGroupDetail.this, list,isGroupAdmin);
            adapter.setCallback(this);
            ddRecyclerView.setAdapter(adapter);
        }else {

            ddRecyclerView.setAdapter(new NoInternetConnectionAdapter("You don't have any member "));

        }

    }


    public void saveEditedGroup() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId", null);
        String group_id = getIntent().getStringExtra("groupID");

        if (photo != null) {
            strImage = getStringImage(photo);
        } else {
            strImage = null;
        }
        String groupNAME = groupName.getText().toString();

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id", user_ID);
            jsonObject.putOpt("group_id", group_id);
            jsonObject.putOpt("group_name", groupNAME);
            jsonObject.putOpt("profile_img", strImage);

            JSONArray jsA = new JSONArray();
            for (int i=0;i<removeIDS.size();i++){
                jsA.put(removeIDS.get(i));
            }

            jsonObject.putOpt("deleted_user_ids",jsA);
            jsonObject.putOpt("version", "2");

            Log.v("editGroupPost", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.GROUP_EDIT_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getsaveResponse(response);
                Log.e("Group Edit URL", "Group Edit URL" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this, "Error in " + "Group EDIT URL = " + PUTTAPI.GROUP_EDIT_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getsaveResponse(JSONObject response) {

        if (response != null) {
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {

                    String msg = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } else {
                    String msg = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    private void captureImageInitialization() {
        final String[] items = new String[]{"Take from camera", "Select from gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyGroupDetail.this, R.layout.select_dialog_row, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(MyGroupDetail.this,R.style.MyDialogTheme);

        builder.setTitle(Html.fromHtml("<font color='#FFFFFF'>SELECT IMAGE</font>"));

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) { // pick from
                // camera
                if (item == 0) {

                    boolean resultCam = Utility.checkPermissionCamera(MyGroupDetail.this);
                    if (resultCam) {
                        takePicture();
                    }

                } else {

                    boolean resultCam = Utility.checkPermissionGallery(MyGroupDetail.this);
                    if (resultCam) {
                        openGallery();
                    }

                }
            }
        });

        dialog = builder.create();
    }


    private void takePicture() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            Uri mImageCaptureUri = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                mImageCaptureUri = Uri.fromFile(mFileTemp);
            } else {
                /*
	        	 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
	        	 */
                mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
            }
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
        } catch (ActivityNotFoundException e) {

            Log.d("TAG", "cannot take picture", e);
        }
    }

    private void openGallery() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
    }

    private void startCropImage() {

        Intent intent = new Intent(getApplicationContext(), CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
        intent.putExtra(CropImage.SCALE, true);

        intent.putExtra(CropImage.ASPECT_X, 3);
        intent.putExtra(CropImage.ASPECT_Y, 3);

        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != this.RESULT_OK) {

            return;
        }

        photo = null;

        switch (requestCode) {

            case REQUEST_CODE_GALLERY:

                try {

                    InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                    copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();

                    boolean resultCam1 = Utility.checkPermissionGallery(this);
                    if (resultCam1) {
                        startCropImage();
                    }

                } catch (Exception e) {

                    Log.e("TAG", "Error while creating temp file", e);
                }

                break;
            case REQUEST_CODE_TAKE_PICTURE:

                boolean resultCam1 = Utility.checkPermissionGallery(this);
                if (resultCam1) {
                    startCropImage();
                }
                break;
            case REQUEST_CODE_CROP_IMAGE:

                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {

                    return;
                }

                photo = BitmapFactory.decodeFile(mFileTemp.getPath());
                groupBgImage.setImageBitmap(photo);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public static void copyStream(InputStream input, OutputStream output)
            throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }



    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }




    @Override
    public void deletePressed(int position) {

      //  Toast.makeText(getApplicationContext(),"Checking"+position,Toast.LENGTH_SHORT).show();
        removeIDS.add(list.get(position).getMember_id());
    }

    public String isGroupAdmin(){
        return isGroupAdmin;
    }
}
