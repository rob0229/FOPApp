package com.example.fapapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.ArrayList;

public class HensonMapView extends Activity {
	Button backToETF;
	
    private Activity _activity;
    private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hensonmap);

		backToETF = (Button) findViewById(R.id.backtoetf);
		backToETF.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						EnterTheFap.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});

	}
	
    public Object instantiateItem(ViewGroup container, int position) {
    	TouchImageView touch = new TouchImageView(this);
  
        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.hensonmap, container,
                false);
         
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
        touch.setImageBitmap(bitmap);
  
        ((ViewPager) container).addView(viewLayout);
  
        return viewLayout;
    }

}