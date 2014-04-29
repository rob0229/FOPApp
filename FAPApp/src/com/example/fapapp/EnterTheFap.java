package com.example.fapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterTheFap extends Activity{
	
	Button logOutBtn;
	Button hensonMapBtn;
	Button triviaBtn;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterthefap);
		
		hensonMapBtn = (Button) findViewById(R.id.mapbtn);
		logOutBtn = (Button) findViewById(R.id.logoutbtn);	
		triviaBtn = (Button) findViewById(R.id.trivia);
		
		logOutBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), Login.class);
				startActivityForResult(myIntent, 0);
				finish();
			}});
		
		hensonMapBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), HensonMapView.class);
				startActivityForResult(myIntent, 0);
				finish();
			}});
		
		triviaBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), Trivia.class);
				startActivityForResult(myIntent, 0);
				finish();
			}});
	
	}

}
