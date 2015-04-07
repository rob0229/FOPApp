package com.example.fapapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
//This class retrieves information from a database and displays the information to the user
//in the App.

public class DatabaseFunctions extends AsyncTask<String, Void, String> {

	private Context context;
	private TextView questionField;
	private ProgressDialog pd = null;
	int questionNumber;
	String randQuest;
	String rawString;
	String sizeOfDB;
	String question = "";
	Button answerBtnA;
	Button answerBtnB;
	Button answerBtnC;
	Button answerBtnD;
	String btnA = "", btnB = "", btnC = "", btnD = "";

	public DatabaseFunctions(Context con, TextView quest, Button a, Button b,
			Button c, Button d) {
		this.context = con;
		this.questionField = quest;
		this.answerBtnA = a;
		this.answerBtnB = b;
		this.answerBtnC = c;
		this.answerBtnD = d;
	}

	protected void onPreExecute() {
		// display a loading message icon here
		pd = new ProgressDialog(context, R.style.GettingDataDialog);
		pd.setTitle("Getting next Question...");
		pd.setMessage("Please wait.");
		pd.setCancelable(false);
		pd.setIndeterminate(true);
		pd.show();
	}

	@Override
	protected String doInBackground(String... arg0) {

		try {
			// connect to server then retrieve the number of questions in the
			// database
			String link = "http://rkclose.com/FOP/getDBSize.php";
			URL url = new URL(link);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			// Read Server Response
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				break;
			}

			// converts StringBuffer into a string
			sizeOfDB = sb.toString();

			randQuest = getRandQuestion(sizeOfDB);

			link = "http://rkclose.com/FOP/trivia.php";

			String data = URLEncoder.encode("NUM", "UTF-8") + "="
					+ URLEncoder.encode(randQuest, "UTF-8");

			url = new URL(link);
			conn = url.openConnection();
			conn.setDoOutput(true);

			wr = new OutputStreamWriter(conn.getOutputStream());

			wr.write(data);
			wr.flush();
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			sb = new StringBuilder();
			line = null;
			// Read Server Response
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				break;
			}

			rawString = sb.toString();

			parseResponse();

			return question;
		} catch (Exception e) {
			return new String("**** Exception: " + e.getMessage());
		}
	}

	@Override
	protected void onPostExecute(String result) {
		pd.dismiss();
		this.questionField.setText(question);
		this.answerBtnA.setText(btnA);
		this.answerBtnB.setText(btnB);
		this.answerBtnC.setText(btnC);
		this.answerBtnD.setText(btnD);
	}

	public String getRandQuestion(String size) {
		// converts string to int
		int sizeOfDB = Integer.parseInt(size);
		Trivia.setsizeOfDB(sizeOfDB);
		String str = "";
		// generates a random int from 1 to size of database
		int questNum = randInt(1, sizeOfDB);

		// checks to see if the random number generated has been generated
		// before. If not, it will add it to the Trivia.arrayList and return the
		// number
		// If all the questions have been asked, it will clear the arraylist and
		// correct/incorrect
		// labels and start over repeating the questions
		do {
			// Erase question history to start over
			if (Trivia.questionHistory.size() == sizeOfDB) {
				Trivia.questionHistory.clear();
				Trivia.right = 0;
				Trivia.wrong = 0;
			}
			// If the number has not been added yet, add it and return the
			// string
			else if (!Trivia.questionHistory.contains(questNum)) {
				System.out.println("****222");
				StringBuilder sb = new StringBuilder();
				sb.append("");
				sb.append(questNum);
				str = sb.toString();
				Trivia.questionHistory.add(questNum);
				return str;
			}

			// Gets a new random int between 1 and the size of the database
			else {
				System.out.println("****333");

				questNum = randInt(1, sizeOfDB);
			}
		} while (true);
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
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

				break;
			}
		}
	}
}