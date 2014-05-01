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

public class Trivia extends Activity {
	String question = "";
	Button backToETF;
	Button nextQuestion;
	Button answerBtnA;
	Button answerBtnB;
	Button answerBtnC;
	Button answerBtnD;

	private TextView questionField, answerField;
	public static String answer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trivia);

		questionField = (TextView) findViewById(R.id.nextquestionlabel);
		answerField = (TextView) findViewById(R.id.answerField);
		answerBtnA = (Button) findViewById(R.id.answer1btn);
		answerBtnB = (Button) findViewById(R.id.answer2btn);
		answerBtnC = (Button) findViewById(R.id.answer3btn);
		answerBtnD = (Button) findViewById(R.id.answer4btn);
		nextQuestion = (Button) findViewById(R.id.nextquestionbtn);
		backToETF = (Button) findViewById(R.id.back);

		backToETF.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						EnterTheFap.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});

		nextQuestion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				answer = "";
				getNextQuestion();
			}
		});
		
		answerBtnA.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
			}
		});
		
		answerBtnB.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
			}
		});
		
		answerBtnC.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
			}
		});

		answerBtnD.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
			}
		});
	}

	// Query the server for the next question
	public void getNextQuestion() {
		int randQuestion = randInt(1, 3);

		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(randQuestion);
		String num = sb.toString();
		new DatabaseFunctions(questionField, answerField, answerBtnA, answerBtnB, answerBtnC, answerBtnD).execute(num);
		System.out.println("Answer is = " + answer);

	}
	
	//returns a random integer within min/max inclusive
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
