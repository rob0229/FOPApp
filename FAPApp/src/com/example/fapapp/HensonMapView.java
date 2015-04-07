package com.example.fapapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;

public class HensonMapView extends Activity {
	Button backToETF;
	Point p;

	private Activity _activity;
	private ArrayList<String> _imagePaths;
	private LayoutInflater inflater;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hensonmap);

		Button btn_show = (Button) findViewById(R.id.show_popup);
		btn_show.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				// Open popup window
				if (p != null)
					showPopup(HensonMapView.this, p);
			}
		});
		
		backToETF = (Button) findViewById(R.id.backtoetf);
		backToETF.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						EnterTheFop.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});
		
	}

	// Get the x and y position after the button is draw on screen
	// (It's important to note that we can't get the position in the onCreate(),
	// because at that stage most probably the view isn't drawn yet, so it will
	// return (0, 0))
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {

		int[] location = new int[2];
		Button button = (Button) findViewById(R.id.show_popup);

		// Get the x, y location and store it in the location[] array
		// location[0] = x, location[1] = y.
		button.getLocationOnScreen(location);

		// Initialize the Point with x, and y positions
		p = new Point();
		p.x = location[0];
		p.y = location[1];
	}

	// The method that displays the popup.
	@SuppressWarnings("deprecation")
	private void showPopup(final Activity context, Point p) {
		int popupWidth = 350;
		int popupHeight = 300;

		// Inflate the popup_layout.xml
		LinearLayout viewGroup = (LinearLayout) context
				.findViewById(R.id.popup);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

		// Creating the PopupWindow
		final PopupWindow popup = new PopupWindow(context);
		popup.setContentView(layout);
		popup.setWidth(popupWidth);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);

		// Some offset to align the popup a bit to the right, and a bit down,
		// relative to button's position.
		int OFFSET_X = 0;
		int OFFSET_Y = 75;

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());

		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y
				+ OFFSET_Y);

		// Getting a reference to Close button, and close the popup when
		// clicked.
		Button close = (Button) layout.findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popup.dismiss();
			}
		});


	}

	public Object instantiateItem(ViewGroup container, int position) {
		TouchImageView touch = new TouchImageView(this);

		inflater = (LayoutInflater) _activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View viewLayout = inflater
				.inflate(R.layout.hensonmap, container, false);

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = BitmapFactory.decodeFile(_imagePaths.get(position),
				options);
		touch.setImageBitmap(bitmap);

		((ViewPager) container).addView(viewLayout);

		return viewLayout;
	}

}