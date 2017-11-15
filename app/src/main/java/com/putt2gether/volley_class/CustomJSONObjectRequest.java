package com.putt2gether.volley_class;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class CustomJSONObjectRequest extends JsonObjectRequest {

    private static final String TAG = CustomJSONObjectRequest.class.getSimpleName();
    private static final String STATUS_OK = "success";

    public CustomJSONObjectRequest(int method, String url, JSONObject jsonRequest,Response.Listener<JSONObject> listener,Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);

    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        // here you can write a custom retry policy
        return super.getRetryPolicy();
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject responseObject = new JSONObject(jsonString);
            Log.d(TAG, jsonString);

            boolean hasStatus = responseObject.has(STATUS_OK);

//            if (hasStatus) {
//                boolean isSuccess = responseObject.getBoolean(STATUS_OK);
//                if (!isSuccess)  {
//                    return Response.
//                }(new ServerError());
//            }
            return Response.success(responseObject, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    public String getBodyContentType() {
        // Read at http://stackoverflow.com/questions/17646036/exectuing-http-post-returns-html-instead-of-json
        String type = "application/x-www-form-urlencoded";
     //   Log.d(TAG, "getBodyContentType() [" + type + "]");
        return type;
    }

}