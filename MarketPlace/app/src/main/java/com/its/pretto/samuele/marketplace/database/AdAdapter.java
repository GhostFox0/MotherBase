package com.its.pretto.samuele.marketplace.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.its.pretto.samuele.marketplace.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Samuele.Pretto on 15/11/2016.
 */

public class AdAdapter extends BaseAdapter {

    Context mContext;

    ArrayList<Ad> mAds;

    public AdAdapter(Context mContext, ArrayList<Ad> mAds) {
        this.mContext = mContext;
        this.mAds = mAds;
    }

    @Override
    public int getCount() {
        return mAds.size();
    }

    @Override
    public Object getItem(int position) {
        return mAds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mAds.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater vInflanter = LayoutInflater.from(mContext);
            convertView = vInflanter.inflate(R.layout.cell_ad,null);
            ViewHolder vHolder = new ViewHolder();
            vHolder.mTitleView = (TextView) convertView.findViewById(R.id.textViewTitle);
            vHolder.mPrice = (TextView) convertView.findViewById(R.id.textViewPrice);
            vHolder.mCategory = (TextView) convertView.findViewById(R.id.textViewCategory);
            vHolder.mDate = (TextView) convertView.findViewById(R.id.textViewDate);
            vHolder.mPlace = (TextView) convertView.findViewById(R.id.textViewPlace);
            vHolder.mImage = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(vHolder);
        }

        Ad ad = (Ad) getItem(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mTitleView.setText(ad.getTitle());
        viewHolder.mPrice.setText(ad.getPrice());
        viewHolder.mCategory.setText(Integer.toString(ad.getCategorie_id()));
        viewHolder.mDate.setText(ad.getDate());
        viewHolder.mPlace.setText(ad.getComune()+" ("+ad.getProvincia()+")");
        Picasso.with(mContext)
                .load(ad.getImage())
                .placeholder(R.drawable.ic_action_image)
                .error(R.drawable.ic_action_image)
                .into(viewHolder.mImage);
        return convertView;
    }

    class ViewHolder{
        TextView mTitleView;
        TextView mPrice;
        TextView mCategory;
        TextView mDate;
        TextView mPlace;
        ImageView mImage;
    }

}
