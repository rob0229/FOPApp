package com.example.fapapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Trivia extends Activity{
	String question = "";
	Button backToETF;
	Button nextQuestion;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trivia);
		
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
					
					question = getNextQuestion();
					
				}});
		
		
	}
	//Querys the server for the next question
	public String getNextQuestion(){
		String queryRawResult;
		String queryResult = "";
		
		//creates query string with a random number to pick a question
		String url = "http://fapapp.bugs3.com/trivia.php?num="+randInt(1,1);
		
		
		System.out.println("Query string is : "+ url);
		
		try{
			
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();         
            request.setURI(new URI(url));
            //Gets Stuck RIGHT HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            HttpResponse response = client.execute(request);
            System.out.println("**********HERE   ******");
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            
           StringBuffer sb = new StringBuffer("");
           String line="";
           while ((line = in.readLine()) != null) {
              sb.append(line);
              break;
            }
            in.close();
            System.out.println("Get Method returned: " +sb.toString());
            queryRawResult = sb.toString();
            
            //Serversfree returns an object with an advertisement string appended to the result, 
            //this loop looks for the appending first '<' which starts the added content and stops
            //adding characters to the resultQuery string.
            for (int i = 0; i < queryRawResult.length(); i++){
            	if (queryRawResult.charAt(i) == '<')
            		break;
            	else 
            		queryResult += queryRawResult.charAt(i);
            	
            }
            
            System.out.println("Parsed queryResult is now: " +queryResult);
  
            return queryResult;
            
      }catch(Exception e){
         return new String("Exception: " + e.getMessage());
      }
	
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
