package com.example.fapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Home page of the FAPApp. Has buttons that link to other activities
public class EnterTheFap extends Activity {

	Button hensonMapBtn;
	Button triviaBtn;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterthefap);

		hensonMapBtn = (Button) findViewById(R.id.mapbtn);
		triviaBtn = (Button) findViewById(R.id.trivia);

		hensonMapBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						HensonMapView.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});

		triviaBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), Trivia.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});
	}
}
