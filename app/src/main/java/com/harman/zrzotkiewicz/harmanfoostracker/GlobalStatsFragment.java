package com.harman.zrzotkiewicz.harmanfoostracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class GlobalStatsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_global_stats, container, false);

        WebView webView = (WebView) view.findViewById(R.id.webview_globalStats);
        webView.loadUrl("http://autotest.harman.com/FoosTracker/Stats/index.php");

        return view;
    }
}
