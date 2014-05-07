package com.example.fapapp;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Trivia extends Activity {
	boolean firstQuestion = true;
	MediaPlayer ding;
	MediaPlayer dong;
	@SuppressWarnings("rawtypes")
	static ArrayList questionHistory;
	String question = "";
	Button backToETF;
	Button nextQuestion;
	Button answerBtnA;
	Button answerBtnB;
	Button answerBtnC;
	Button answerBtnD;
	Button resetBtn;
	TextView rightLabel, wrongLabel;
	boolean correct = false;
	private Context context;
	static int right = 0, wrong = 0, sizeOfDB = 0;;

	private TextView questionField;
	public static String answer;

	// instantiates all the variables and UI interface components on the Trivia
	// page
	@SuppressWarnings("rawtypes")
	public void onCreate(Bundle savedInstanceState) {

		ding = MediaPlayer.create(getApplicationContext(), R.raw.ding);
		dong = MediaPlayer.create(getApplicationContext(), R.raw.dong);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trivia);
		context = this;

		questionHistory = new ArrayList();
		questionField = (TextView) findViewById(R.id.nextquestionlabel);
		answerBtnA = (Button) findViewById(R.id.answer1btn);
		answerBtnB = (Button) findViewById(R.id.answer2btn);
		answerBtnC = (Button) findViewById(R.id.answer3btn);
		answerBtnD = (Button) findViewById(R.id.answer4btn);
		nextQuestion = (Button) findViewById(R.id.nextquestionbtn);
		resetBtn = (Button) findViewById(R.id.resetbtn);
		rightLabel = (TextView) findViewById(R.id.rightLabel);
		wrongLabel = (TextView) findViewById(R.id.wrongLabel);
		backToETF = (Button) findViewById(R.id.back);
		answerBtnA.setEnabled(false);
		answerBtnB.setEnabled(false);
		answerBtnC.setEnabled(false);
		answerBtnD.setEnabled(false);
		rightLabel.setText("Correct Answers: " + right);
		wrongLabel.setText("Incorrect Answers: " + wrong);

		backToETF.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						EnterTheFop.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});

		// resets the question score and questionHistory Array
		resetBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				reset();
			}
		});

		nextQuestion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				answer = "";
				correct = false;
				// Resets the button color
				answerBtnA
						.setBackgroundResource(android.R.drawable.btn_default);
				answerBtnB
						.setBackgroundResource(android.R.drawable.btn_default);
				answerBtnC
						.setBackgroundResource(android.R.drawable.btn_default);
				answerBtnD
						.setBackgroundResource(android.R.drawable.btn_default);
				// enables the answer buttons
				answerBtnA.setEnabled(true);
				answerBtnB.setEnabled(true);
				answerBtnC.setEnabled(true);
				answerBtnD.setEnabled(true);

				// disables the next question button
				nextQuestion.setEnabled(false);
				if (!checkDone()) {
					getNextQuestion();
				} else {
					done(view);
				}
				firstQuestion = false;

			}
		});

		answerBtnA.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				if (answer.equals("1")) {
					ding.start();
					correct = true;
					right++;
				} else {
					dong.start();
					wrong++;
				}
				// disables the answer buttons
				answerBtnA.setEnabled(false);
				answerBtnB.setEnabled(false);
				answerBtnC.setEnabled(false);
				answerBtnD.setEnabled(false);
				// enables the next question button
				nextQuestion.setEnabled(true);
				displayAnswer(answerBtnA);
				rightLabel.setText("Correct Answers: " + right);
				wrongLabel.setText("Incorrect Answers: " + wrong);
			}
		});

		answerBtnB.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (answer.equals("2")) {
					ding.start();
					correct = true;
					right++;
				} else {
					dong.start();
					wrong++;
				}
				// disables the answer buttons
				answerBtnA.setEnabled(false);
				answerBtnB.setEnabled(false);
				answerBtnC.setEnabled(false);
				answerBtnD.setEnabled(false);
				// enables the next question button
				nextQuestion.setEnabled(true);
				displayAnswer(answerBtnB);
				rightLabel.setText("Correct Answers: " + right);
				wrongLabel.setText("Incorrect Answers: " + wrong);
			}
		});

		answerBtnC.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (answer.equals("3")) {
					ding.start();
					correct = true;
					right++;
				} else {
					dong.start();
					wrong++;
				}
				// disables the answer buttons
				answerBtnA.setEnabled(false);
				answerBtnB.setEnabled(false);
				answerBtnC.setEnabled(false);
				answerBtnD.setEnabled(false);
				// enables the answer button
				nextQuestion.setEnabled(true);
				displayAnswer(answerBtnC);
				rightLabel.setText("Correct Answers: " + right);
				wrongLabel.setText("Incorrect Answers: " + wrong);
			}
		});

		answerBtnD.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (answer.equals("4")) {
					ding.start();
					correct = true;
					right++;
				} else {
					dong.start();
					wrong++;
				}
				// disables the answer buttons
				answerBtnA.setEnabled(false);
				answerBtnB.setEnabled(false);
				answerBtnC.setEnabled(false);
				answerBtnD.setEnabled(false);
				// enables the answer button
				nextQuestion.setEnabled(true);
				displayAnswer(answerBtnD);
				rightLabel.setText("Correct Answers: " + right);
				wrongLabel.setText("Incorrect Answers: " + wrong);
			}
		});
	}

	// Query the server for the next question
	// We should change this random number to the PHP so it can automatically
	// adjust as we
	// add new questions without having to reinstall the app.
	public void getNextQuestion() {

		new DatabaseFunctions(context, questionField, answerBtnA, answerBtnB,
				answerBtnC, answerBtnD).execute();

	}

	public boolean checkDone() {
		boolean d = false;
		if ((sizeOfDB == (right + wrong)) && (firstQuestion == false)) {
			d = true;
		}
		return d;
	}

	public void done(final View view) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("You answered all the questions!");

		// set dialog message
		alertDialogBuilder
				.setMessage(
						"You answered " + right + " correct and " + wrong
								+ " incorrect. \nDo you want to try again?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								reset();
								firstQuestion = true;
								dialog.cancel();

							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						Intent myIntent = new Intent(view.getContext(),
								EnterTheFop.class);
						startActivityForResult(myIntent, 0);
						finish();
					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

	// resets the button text, question text and score
	public void reset() {
		right = 0;
		wrong = 0;
		rightLabel.setText("Correct Answers: " + right);
		wrongLabel.setText("Incorrect Answers: " + wrong);
		answerBtnA.setBackgroundResource(android.R.drawable.btn_default);
		answerBtnB.setBackgroundResource(android.R.drawable.btn_default);
		answerBtnC.setBackgroundResource(android.R.drawable.btn_default);
		answerBtnD.setBackgroundResource(android.R.drawable.btn_default);
		// enables the answer buttons
		answerBtnA.setEnabled(false);
		answerBtnB.setEnabled(false);
		answerBtnC.setEnabled(false);
		answerBtnD.setEnabled(false);
		answerBtnA.setText("");
		answerBtnB.setText("");
		answerBtnC.setText("");
		answerBtnD.setText("");

		// disables the next question button
		nextQuestion.setEnabled(true);

		questionField.setText("Click \"Next Question\" button");

		questionHistory.clear();

	}

	// returns a random integer within min/max inclusive
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	// changes the color of the button to green if correct and red if incorrect
	public void displayAnswer(Button btn) {
		if (correct) {
			btn.setBackgroundColor(Color.GREEN);
		} else
			btn.setBackgroundColor(Color.RED);
	}
}
