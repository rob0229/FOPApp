package com.example.fapapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Trivia extends Activity{
	String question = "";
	Button backToETF;
	Button nextQuestion;
	private TextView questionField;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trivia);
		
		questionField = (TextView)findViewById(R.id.nextquestionlabel);
		
		
		backToETF = (Button) findViewById(R.id.back);
	      backToETF.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					Intent myIntent = new Intent(view.getContext(), EnterTheFap.class);
					startActivityForResult(myIntent, 0);
					finish();
				}});
	      
	      nextQuestion = (Button) findViewById(R.id.nextquestionbtn);
	      nextQuestion.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					
					getNextQuestion();
					
				}});
		
		
	}
	//Querys the server for the next question
	public void getNextQuestion(){
		
		String num = "1";
		String test = "1";
		
		new SigninActivity(questionField).execute(num, test);
	
		
	}

	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

}
