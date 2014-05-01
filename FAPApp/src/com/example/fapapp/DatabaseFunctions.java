package com.example.fapapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class DatabaseFunctions extends AsyncTask<String, Void, String> {

	private TextView questionField;
	private TextView answerField;
	String rawString;
	String rawAnswer;
	String question = "";
	Button answerBtnA;
	Button answerBtnB;
	Button answerBtnC;
	Button answerBtnD;
	String btnA = "", btnB = "", btnC = "", btnD = "";

	public DatabaseFunctions(TextView quest, TextView ansF, Button a, Button b,
			Button c, Button d) {
		this.questionField = quest;
		this.answerField = ansF;
		this.answerBtnA = a;
		this.answerBtnB = b;
		this.answerBtnC = c;
		this.answerBtnD = d;
	}

	protected void onPreExecute() {

	}

	@Override
	protected String doInBackground(String... arg0) {

		try {
			String questionNumber = (String) arg0[0];
			System.out.println("QuestionNumber is: " + questionNumber);

			String link = "http://rkclose.com/trivia.php";
			String data = URLEncoder.encode("NUM", "UTF-8") + "="
					+ URLEncoder.encode(questionNumber, "UTF-8");

			URL url = new URL(link);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());

			wr.write(data);
			wr.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			// Read Server Response
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				break;
			}

			System.out.println("****************sb.toString is : "
					+ sb.toString());

			rawString = sb.toString();
			parseResponse();
			;
			return question;
		} catch (Exception e) {
			return new String("**** Exception: " + e.getMessage());
		}

	}

	@Override
	protected void onPostExecute(String result) {
		this.answerField.setText(Trivia.answer);
		this.questionField.setText(question);
		this.answerBtnA.setText(btnA);
		this.answerBtnB.setText(btnB);
		this.answerBtnC.setText(btnC);
		this.answerBtnD.setText(btnD);
	}

	// Parses the rawString returned by query.
	public void parseResponse() {
		char answerChar;
		// Adds characters for question until the '*' is encountered, which
		// signifies the start of the answer
		for (int i = 0; i < rawString.length(); i++) {
			while (rawString.charAt(i) != '#') {
				question += rawString.charAt(i);
				i++;
			}
			i++;
			while (rawString.charAt(i) != '#') {
				btnA += rawString.charAt(i);
				i++;
			}
			i++;
			while (rawString.charAt(i) != '#') {
				btnB += rawString.charAt(i);
				i++;
			}
			i++;
			while (rawString.charAt(i) != '#') {
				btnC += rawString.charAt(i);
				i++;
			}
			i++;
			while (rawString.charAt(i) != '*') {
				btnD += rawString.charAt(i);
				i++;
			}
			
			if (rawString.charAt(i) == '*') {
				// gets the answer and exits for loop
				answerChar = rawString.charAt(i + 1);
				Trivia.answer += answerChar;
				System.out.println("The answer inside DBF line 87 is : "
						+ rawString.charAt(i + 1) + " answer = : "
						+ Trivia.answer);
				break;
			} 
		}
	}

}