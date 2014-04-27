package com.example.fapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterTheFap extends Activity{
	
	Button logOutBtn;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.passreset);
		logOutBtn = (Button) findViewById(R.id.logoutbtn);
		logOutBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//Intent myIntent = new Intent(view.getContext(), FAPApp.class);
				//startActivityForResult(myIntent, 0);
				//finish();
			}});
		
	
	}

}
