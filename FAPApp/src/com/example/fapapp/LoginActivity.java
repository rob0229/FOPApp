package com.example.fapapp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

public class LoginActivity  extends AsyncTask<String,Void,String>{
	private String userNameQueryResult, queryResult = "";
   private TextView statusField,roleField;
   private Context context;
   
   //flag 0 means get and 1 means post.(By default it is get.)
   public LoginActivity(Context context, TextView statusField, TextView roleField) {
      this.context = context;
      this.statusField = statusField;
      this.roleField = roleField;
     
   }

   protected void onPreExecute(){

   }
   @Override
   protected String doInBackground(String... arg0) {
	   //This code was referenced from http://www.tutorialspoint.com/android/android_php_mysql.htm
	  
         try{
            String email = (String)arg0[0];
            String password = (String)arg0[1];
            String link="http://fapapp.bugs3.com/login.php";
            String data  = URLEncoder.encode("email", "UTF-8")+ "=" + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8")+ "=" + URLEncoder.encode(password, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection(); 
            conn.setDoOutput(true); 
            OutputStreamWriter wr = new OutputStreamWriter (conn.getOutputStream()); 
            wr.write( data ); 
            wr.flush(); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            
            //PLAY GROUND
           // JSONObject json = new JSONObject();
            //END PLAYGROUND
            
            while((line = reader.readLine()) != null)
            {
               sb.append(line);
               break;
            }
            System.out.println("*****Login method returned: " + sb.toString());
            userNameQueryResult = sb.toString();
            
            //Serversfree returns an object with an advertisement string appended to the result, 
            //this loop looks for the appending first '<' which starts the added content and stops
            //adding characters to the resultQuery string.
            for (int i = 0; i < userNameQueryResult.length(); i++){
            	if (userNameQueryResult.charAt(i) == '<')
            		break;
            	else 
            		queryResult += userNameQueryResult.charAt(i);  	
            }
            
            System.out.println("Parsed queryResult is now: " +queryResult);
            return queryResult;
         }catch(Exception e){
            return new String("Exception trying to access server: " + e.getMessage());
         }
      
   }
   @Override
   protected void onPostExecute(String result){
	   
	   //change screens here to the next activity
	   	   
	  //this is for testing to ensure we are getting the server database info 
      this.statusField.setText("Login Successful");
      this.roleField.setText(result);
   }
}