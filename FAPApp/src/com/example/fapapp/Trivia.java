package com.example.fapapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

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
		String bytesSent;
		//creates query string with a random number to pick a question
		
		String address = "http://fapapp.bugs3.com/trivia.php";
		System.out.println("Query string is : "+ address);
		try{
		HttpPost httppost;
        DefaultHttpClient httpclient;
        ResponseHandler <String> res=new BasicResponseHandler();  
        List<NameValuePair> nameValuePairs;
      

        httppost = new HttpPost(address);  
        HttpParams params = new BasicHttpParams();  

        HttpProtocolParams.setContentCharset(params, "UTF-8");
        System.out.println("**************GETS HERE ****************");
        httpclient = new DefaultHttpClient(params);
        System.out.println("**************2 ****************");
        nameValuePairs = new ArrayList<NameValuePair>(2);  
        System.out.println("************** 3****************");
        nameValuePairs.add(new BasicNameValuePair("num", "1"));
        System.out.println("************** 4****************");
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  
        System.out.println("**************5 ****************");
        bytesSent = httpclient.execute(httppost, res);
        System.out.println("**************6 ****************");
        
		}catch(Exception e){
			return new String("Exception: " + e.getMessage());
			
		}
		System.out.println("BytesSent : " + bytesSent);
		return bytesSent;
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
