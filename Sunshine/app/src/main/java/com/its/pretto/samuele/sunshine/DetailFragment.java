package com.its.pretto.samuele.sunshine;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * Created by Samuele.Pretto on 28/10/2016.
 */

public class DetailFragment extends Fragment {

    private static final String FORECAST_SHARE_HASHTAG=" #SunshineApp";
    private String mForecastStr;

    public interface IDetailFragment{
        public void writeDetail(String s);
    }

    IDetailFragment listener;

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,container,false);
        Intent intent = getActivity().getIntent();
        if (intent !=null && intent.hasExtra(Intent.EXTRA_TEXT)){
            mForecastStr =intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView) rootView.findViewById(R.id.textView)).setText(mForecastStr);
        }

        return rootView;
    }

    private Intent createSharedForecastIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,mForecastStr+FORECAST_SHARE_HASHTAG);
        return shareIntent;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment,menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if (mShareActionProvider!=null){
            mShareActionProvider.setShareIntent(createSharedForecastIntent());
        }
        else {
            Log.d("debug","Share Action Provider null");
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
