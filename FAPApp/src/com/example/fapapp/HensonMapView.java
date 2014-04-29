package com.example.fapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HensonMapView extends Activity {
	Button backToETF;
	
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.hensonmap);
	      
	      backToETF = (Button) findViewById(R.id.backtoetf);
	      backToETF.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					Intent myIntent = new Intent(view.getContext(), EnterTheFap.class);
					startActivityForResult(myIntent, 0);
					finish();
				}});
		
	}

}
