package com.putt2gether.fragments.mygroups;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
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
import java.util.Collections;
import java.util.List;

import com.putt2gether.R;
import com.putt2gether.crop_image.CropImage;
import com.putt2gether.crop_image.InternalStorageContentProvider;
import com.putt2gether.custome.ItemTouchHelperAdapter;
import com.putt2gether.custome.SimpleItemTouchHelperCallback;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.network.PUTTAPI;
import com.putt2gether.utils.Utility;
import com.putt2gether.volley_class.AppController;
import com.putt2gether.volley_class.CustomJSONObjectRequest;

/**
 * Created by Ajay on 08/09/2016.
 */
public class CreateGroupActivity extends AppCompatActivity {

    private ImageView backBTN;
    private RecyclerView ddRecyclerView;
    private GroupMemberAdapter adapter;
    private RecyclerView.LayoutManager ddLayoutManager;
    private RelativeLayout imageLayout;

    private ArrayList<String> listSelectedMember;

    private Uri mImageCaptureUri;
    private AlertDialog dialog;
    public static final String TEMP_PHOTO_FILE_NAME = "temp_image.jpg";

    public static final int REQUEST_CODE_GALLERY = 0x1;
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;

    private File mFileTemp;

    Bitmap photo;

    private ImageView groupImage;
    private RelativeLayout saveGROUPBTN;
    private EditText groupNameET;
    String groupNAME ;
    RelativeLayout parent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group_activity);

        parent  = (RelativeLayout)findViewById(R.id.parent);
        setupUI(parent);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(this.getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }

        captureImageInitialization();

        groupNameET = (EditText)findViewById(R.id.input_group_name);
        saveGROUPBTN = (RelativeLayout)findViewById(R.id.savegroup_btn);


        saveGROUPBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGroup();
            }
        });

        groupImage = (ImageView)findViewById(R.id.group_image_upload);

        imageLayout = (RelativeLayout)findViewById(R.id.upload_image_layout);
        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        backBTN = (ImageView)findViewById(R.id.back_create_group);

        ddRecyclerView = (RecyclerView)findViewById(R.id.add_members_list_group);

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

    }

    public void getGroupMemberList(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("searchkey","");
            jsonObject.putOpt("version","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.SUGGESTION_LIST_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getMemeberListResponse(response);
                Log.e("Group members", "GetGroup membersList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this,"Error in "+ "GetGroup membersList URL = " + PUTTAPI.SUGGESTION_LIST_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void getMemeberListResponse(JSONObject response){

        ArrayList<GroupMemberBean> list = new ArrayList<GroupMemberBean>();

        if(response != null){
            try {

                JSONObject jsonObject = response.getJSONObject("output");
                JSONArray jsonArray = jsonObject.getJSONArray("Suggestion List");

                for (int i = 0 ; i < jsonArray.length(); i++){
                    GroupMemberBean listBean = new GroupMemberBean();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    listBean.setUser_id(jsonObject1.getString("user_id"));
                    listBean.setFull_name(jsonObject1.getString("full_name"));
                    listBean.setRegistered_mobile_number(jsonObject1.getString("registered_mobile_number"));
                    listBean.setLast_modified_date(jsonObject1.getString("last_modified_date"));
                    listBean.setCountry(jsonObject1.getString("country"));
                    listBean.setSelf_handicap(jsonObject1.getString("self_handicap"));
                    listBean.setPlayed(jsonObject1.getString("played"));

                    //listBean.setHandicap(jsonObject1.getString("handicap"));
                    listBean.setProfile_image(jsonObject1.getString("profile_image"));
                    listBean.setThumb_image(jsonObject1.getString("thumb_image"));

                    list.add(listBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        adapter = new GroupMemberAdapter(CreateGroupActivity.this,list);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(ddRecyclerView);


        ddRecyclerView.setAdapter(adapter);


    }


    public void createGroup(){


        if (groupNameET.getText().toString()==null || groupNameET.getText().toString().length()==0){
            groupNameET.setError("Please enter the group name");
        }else {
            groupNAME = groupNameET.getText().toString();
        }



        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        JSONObject jsonObject = null;
        SharedPreferences pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String user_ID = pref.getString("userId",null);

        try {
            jsonObject = new JSONObject();
            jsonObject.putOpt("user_id",user_ID);
            jsonObject.putOpt("group_name",groupNAME);
            if (photo!=null) {
                jsonObject.putOpt("profile_img", getStringImage(photo));
            }

            JSONArray jsonArray = new JSONArray();
            for (int i=0 ; i< listSelectedMember.size();i++){
                JSONObject jso = new JSONObject();
                jso.putOpt("member_id",listSelectedMember.get(i));
                jsonArray.put(jso);
            }
            jsonObject.putOpt("member_list",jsonArray);

            jsonObject.putOpt("version","2");


            Log.v("createGroup",jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJSONObjectRequest jsonObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, PUTTAPI.CREATE_GROUP_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                geCreateGroupResponse(response);
                Log.e("Group members", "GetGroup membersList" + response.toString());
                pDialog.cancel();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
            }
        });
        Utility.showLogError(this,"Error in "+ "GetGroup membersList URL = " + PUTTAPI.CREATE_GROUP_URL);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    private void geCreateGroupResponse(JSONObject response){

        if(response != null){

            try {

                JSONObject jsonObject = response.getJSONObject("output");
                String status  = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")){

                    String msg= jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                    finish();

                }else {
                    String msgError = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),msgError,Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }





    private void captureImageInitialization() {
        final String[] items = new String[]{"Take from camera", "Select from gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateGroupActivity.this, R.layout.select_dialog_row, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupActivity.this,R.style.MyDialogTheme);

        builder.setTitle(Html.fromHtml("<font color='#FFFFFF'>SELECT IMAGE</font>"));

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) { // pick from
                // camera
                if (item == 0) {

                    boolean resultCam = Utility.checkPermissionCamera(CreateGroupActivity.this);
                    if (resultCam) {
                        takePicture();
                    }

                } else {

                    boolean resultCam = Utility.checkPermissionGallery(CreateGroupActivity.this);
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

        Intent intent = new Intent(this, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
        intent.putExtra(CropImage.SCALE, true);

        intent.putExtra(CropImage.ASPECT_X, 3);
        intent.putExtra(CropImage.ASPECT_Y, 3);

        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {

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
                groupImage.setImageBitmap(photo);
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



    public class GroupMemberAdapter  extends RecyclerView.Adapter<GroupMemberAdapter.SimpleViewHolder> implements Filterable,ItemTouchHelperAdapter {

        private final Context mContext;
        private List<GroupMemberBean> mData;

        private  ArrayList<GroupMemberBean> listSuggesion;
        int count = 0;

        private SharedPreferences mSharedPreferences;
        private SharedPreferences createSharedPreferences;


        private LayoutInflater mInflater;
        private ItemFilter mFilter = new ItemFilter();
        int top = 0;
        private int noPlayer;

        public void add(GroupMemberBean s,int position) {
            position = position == -1 ? getItemCount()  : position;
            mData.add(position,s);
            notifyItemInserted(position);
        }

        public void remove(int position){
            if (position < getItemCount()  ) {
                mData.remove(position);
                notifyItemRemoved(position);
            }
        }

        @Override
        public Filter getFilter() {
            return mFilter;
        }

        public  class SimpleViewHolder extends RecyclerView.ViewHolder {
            //public final TextView title;

            private TextView name;
            private ImageView image;
            private TextView addIcon;
            private ImageView removeIcon;
            private ImageView starIcon;
            private TextView age;
            private String radioText;

            public SimpleViewHolder(View view) {
                super(view);



                name = (TextView) view.findViewById(R.id.suggestion_row_name);
                image = (ImageView)view.findViewById(R.id.suggestions_row_image);

                addIcon = (TextView) view.findViewById(R.id.suggestion_row_addBTN);

                starIcon = (ImageView)view.findViewById(R.id.suggestion_row_star);
                age = (TextView)view.findViewById(R.id.suggestion_row_age);
            }
        }

        public GroupMemberAdapter(Context context,ArrayList<GroupMemberBean> data) {
            mContext = context;
            if (data != null) {
                mData = data;
                this.listSuggesion = data;
                mInflater = LayoutInflater.from(context);
            }
            else mData = new ArrayList<GroupMemberBean>();
        }



        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.suggestion_list_row, parent, false);

            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleViewHolder holder, final int position) {


            mSharedPreferences = mContext.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
            createSharedPreferences = mContext.getSharedPreferences("craete_event", Context.MODE_PRIVATE);



            String capsName = mData.get(position).getFull_name();
            String caps = capsName.toUpperCase();
            holder.name.setText(caps);
            // holder.budget.setText(list.get(position).getBudget());


            String imageCheck = mData.get(position).getThumb_image();
            int isImageFound =0;
            if (imageCheck!=null) {
                 isImageFound = imageCheck.length();
            }

            if (isImageFound>5) {

                Picasso.with(mContext).load(mData.get(position).getThumb_image()).into(holder.image);
                //new DownloadImageTask(holder.image).execute(mData.get(position).getThumb_image());
            }else {
                holder.image.setImageResource(R.drawable.vishal);
            }
            // holder.image.setImageResource(list.get(position).getImage());

            holder.age.setText(mData.get(position).getSelf_handicap());

            String str = mData.get(position).getPlayed();
            int star = Integer.parseInt(str);
            if (star == 1) {
                holder.starIcon.setVisibility(View.VISIBLE);
            }


            final GroupMemberBean pojo = mData.get(position);

            if(!pojo.isClicked()) {
                holder.addIcon.setText("ADD");

            } else {
                holder.addIcon.setText("REMOVE");

            }


            if(!pojo.isClicked()) {
                holder.addIcon.setText("ADD");

                holder.addIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //   SharedPreferences pref = mContext.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
                        //    SharedPreferences.Editor editor = pref.edit();
                        //   final String radioText = pref.getString("radioText", null);

                        noPlayer = listSuggesion.size()+1;

                        if (top < noPlayer-1) {

                            GroupMemberBean bean = new GroupMemberBean();
                            pojo.setClicked(true);


                            onItemMove(position, top);
                            notifyDataSetChanged();
                            top = top + 1;

                            holder.addIcon.setText("REMOVE");


                            Log.v("Clicked Position", String.valueOf(position));
                        }else {
                            Log.v("Clicked Position", String.valueOf(pojo));

                            final Dialog dialog = new Dialog(mContext);

                            dialog.setCanceledOnTouchOutside(true);
                            Window window = dialog.getWindow();
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


                            WindowManager.LayoutParams wlp = window.getAttributes();

                            wlp.gravity = Gravity.CENTER;
                            wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                            window.setAttributes(wlp);


                            // Include dialog.xml file
                            dialog.setContentView(R.layout.pop_up_moreselection);
                            TextView text = (TextView)dialog.findViewById(R.id.popup_preview);

                            RelativeLayout close = (RelativeLayout)dialog.findViewById(R.id.close_popup);
                            text.setText("You haven't added same no. of players as selected on Create Event. Please add same no. players to continue.");

                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            dialog.show();

                            close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.cancel();
                                }
                            });



                        }

                    }
                });

            } else {
                holder.addIcon.setText("REMOVE");

                holder.addIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        GroupMemberBean bean = new GroupMemberBean();
                        pojo.setClicked(false);

                        //add(bean,2);
                        int in = mData.size() - 1;
                        onItemMove(position,in);
                        notifyDataSetChanged();
                        holder.addIcon.setText("ADD");
                        top = top-1;

                        Log.v("topppp", String.valueOf(top));

                        //  SharedPreferences.Editor editor1 = createSharedPreferences.edit();
                        //  editor1.putString("top", String.valueOf(top));
                        //  editor1.commit();

                        Log.v("Clicked Position", String.valueOf(pojo));

                    }
                });


                listSelectedMember = new ArrayList<String>();

                for (int i = 0 ; i < top ; i++){
                    String id1 = listSuggesion.get(i).getUser_id();
                    Log.v("idddddddd"+i,id1);
                    listSelectedMember.add(id1);

                }

            }

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        private class ItemFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String filterString = constraint.toString().toLowerCase();

                FilterResults results = new FilterResults();

                final List<GroupMemberBean> list = listSuggesion;
                int count = list.size();
                final ArrayList<GroupMemberBean> nlist = new ArrayList<GroupMemberBean>(count);

                String filterableString ;

                for (int i = 0; i < count; i++) {
                    filterableString = ""+list.get(i).getFull_name();
                    if (filterableString.toLowerCase().contains(filterString)) {
                        GroupMemberBean mBookServiceModel = list.get(i);
                        nlist.add(mBookServiceModel);
                    }
                }

                results.values = nlist;
                results.count = nlist.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mData = (ArrayList<GroupMemberBean>) results.values;
                notifyDataSetChanged();
            }
        }

  /*  public void swap(int firstPosition, int secondPosition)
    {
        Collections.swap(listSuggesion, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    } */

        @Override
        public void onItemDismiss(int position) {
            mData.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public void onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mData, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mData, i, i - 1);
                }
            }

            notifyItemMoved(fromPosition, toPosition);
            //notifyDataSetChanged();
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(CreateGroupActivity.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}
