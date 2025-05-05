package com.example.frontend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frontend.model.Dog;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DogAdapter extends BaseAdapter {
    Context context;
    ArrayList<Dog> dogArrayList;
    LayoutInflater layoutInflater;

    public DogAdapter(Context context, ArrayList<Dog> dogArrayList){
        this.context = context;
        this.dogArrayList = dogArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dogArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_row, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.dogProfilePicture);
        TextView dogName = (TextView) convertView.findViewById(R.id.dogName);
        TextView dogAdoptionStatus = (TextView) convertView.findViewById(R.id.dogAdoptionStatus);
        TextView dogAdoptedBy = (TextView) convertView.findViewById(R.id.dogAdoptedBy);

        Picasso.get().load(dogArrayList.get(position).getImageLink()).into(imageView);
        dogName.setText(dogArrayList.get(position).getName());
        dogAdoptionStatus.setText(dogArrayList.get(position).getAdoptionStatus());
        dogAdoptedBy.setText(dogArrayList.get(position).getAdoptedBy());
        return convertView;
    }
}
