package com.putt2gether.fragments.faq;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.putt2gether.R;
import com.putt2gether.network.ConnectionDetector;
import com.putt2gether.putt.HomeActivity;

/**
 * Created by Abc on 9/8/2016.
 */
public class Faq extends Fragment {

    WebView webView;

    ProgressDialog progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faqs, container, false);

        webView=(WebView)view.findViewById(R.id.faq_web);


        ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());


        if (connectionDetector.isConnectingToInternet()) {

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.getSettings().setAppCacheEnabled(false);
            webView.getSettings().setLoadWithOverviewMode(true);

            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webView.setScrollbarFadingEnabled(false);

            webView.setWebViewClient(new MyWebClient());

            webView.loadUrl("http://putt2gether.com/#faq");

        } else {
            Toast.makeText(getActivity(), R.string.internet_warning, Toast.LENGTH_SHORT).show();
        }



        ((HomeActivity) getActivity()).addtitle("FAQ'S");



        return view;
    }

    public class MyWebClient extends WebViewClient {



        public MyWebClient() {

            progressBar = ProgressDialog.show(getActivity(), "Wait", "Loading...");
            progressBar.setCancelable(true);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if(url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(intent);

                return true;
            }

            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);
            progressBar.dismiss();
        }
    }


}
