package com.example.fapapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.widget.TextView;

public class SigninActivity  extends AsyncTask<String,Void,String>{

	   private TextView questionField;

	   public SigninActivity(TextView v) {
	     
	      this.questionField = v;
	      
	     
	   }

	   protected void onPreExecute(){

	   }
	   @Override
	   protected String doInBackground(String... arg0) {
	     
	         try{
	            String questionNumber = (String)arg0[0];
	            System.out.println("QuestionNumber is: " + questionNumber);
	           
	            String link="http://rkclose.com/trivia.php";
	            String data  = URLEncoder.encode("NUM", "UTF-8") + "=" + URLEncoder.encode(questionNumber, "UTF-8");
	            
	            URL url = new URL(link);
	            URLConnection conn = url.openConnection(); 
	            conn.setDoOutput(true); 
	           
	            OutputStreamWriter wr = new OutputStreamWriter
	            (conn.getOutputStream()); 
	           
	            wr.write( data ); 
	            wr.flush(); 
	            BufferedReader reader = new BufferedReader
	            (new InputStreamReader(conn.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            // Read Server Response
	            while((line = reader.readLine()) != null)
	            {
	               sb.append(line);
	               break;
	            }
	            System.out.println("****************sb.toString is : "+ sb.toString());
	            return sb.toString();
	         }catch(Exception e){
	            return new String("**** Exception: " + e.getMessage());
	         }
	      
	   }
	   @Override
	   protected void onPostExecute(String result){
	     // this.questionField.setText("Login Successful");
	      this.questionField.setText(result);
	   }
	}