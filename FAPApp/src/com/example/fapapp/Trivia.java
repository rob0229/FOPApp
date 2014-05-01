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
import android.graphics.Color;
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
	boolean correct = false;

	private TextView questionField;
	public static String answer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trivia);

		questionField = (TextView) findViewById(R.id.nextquestionlabel);
		answerBtnA = (Button) findViewById(R.id.answer1btn);
		answerBtnB = (Button) findViewById(R.id.answer2btn);
		answerBtnC = (Button) findViewById(R.id.answer3btn);
		answerBtnD = (Button) findViewById(R.id.answer4btn);
		nextQuestion = (Button) findViewById(R.id.nextquestionbtn);
		backToETF = (Button) findViewById(R.id.back);
		
		answerBtnA.setEnabled(false);
		answerBtnB.setEnabled(false);
		answerBtnC.setEnabled(false);
		answerBtnD.setEnabled(false);
		
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
				correct = false;
				
				answerBtnA.setBackgroundResource(android.R.drawable.btn_default);
				answerBtnB.setBackgroundResource(android.R.drawable.btn_default);
				answerBtnC.setBackgroundResource(android.R.drawable.btn_default);
				answerBtnD.setBackgroundResource(android.R.drawable.btn_default);
				answerBtnA.setEnabled(true);
				answerBtnB.setEnabled(true);
				answerBtnC.setEnabled(true);
				answerBtnD.setEnabled(true);
				nextQuestion.setEnabled(false);
				
				getNextQuestion();
			}
		});
		
		answerBtnA.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				if(answer.equals("1")){
					correct = true;				
				}
				answerBtnA.setEnabled(false);
				answerBtnB.setEnabled(false);
				answerBtnC.setEnabled(false);
				answerBtnD.setEnabled(false);
				nextQuestion.setEnabled(true);
				displayAnswer(answerBtnA);
				
			}
		});
		
		answerBtnB.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if(answer.equals("2")){
					correct = true;				
				}
				answerBtnA.setEnabled(false);
				answerBtnB.setEnabled(false);
				answerBtnC.setEnabled(false);
				answerBtnD.setEnabled(false);
				nextQuestion.setEnabled(true);
				displayAnswer(answerBtnB);
			}
		});
		
		answerBtnC.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if(answer.equals("3")){
					correct = true;			
				}
				answerBtnA.setEnabled(false);
				answerBtnB.setEnabled(false);
				answerBtnC.setEnabled(false);
				answerBtnD.setEnabled(false);
				nextQuestion.setEnabled(true);
				displayAnswer(answerBtnC);
			}
		});

		answerBtnD.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if(answer.equals("4")){
					correct = true;
				}
				answerBtnA.setEnabled(false);
				answerBtnB.setEnabled(false);
				answerBtnC.setEnabled(false);
				answerBtnD.setEnabled(false);
				nextQuestion.setEnabled(true);
				displayAnswer(answerBtnD);
			}
		});
	}

	// Query the server for the next question
	//We should change this random number to the PHP so it can automatically adjust as we
	//add new questions without having to reinstall the app.
	public void getNextQuestion() {
		int randQuestion = randInt(1, 6);

		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(randQuestion);
		String num = sb.toString();
		new DatabaseFunctions(questionField, answerBtnA, answerBtnB, answerBtnC, answerBtnD).execute(num);
		System.out.println("Answer is = " + answer);

	}
	
	//returns a random integer within min/max inclusive
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public void displayAnswer(Button btn){
		if(correct){
			btn.setBackgroundColor(Color.GREEN);
		}
		else
			btn.setBackgroundColor(Color.RED);
		
	}

}
