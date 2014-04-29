package com.example.fapapp;



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_MENTOR = "mentor";
    private static String KEY_FIRSTNAME = "fName";
    private static String KEY_LASTNAME = "lName";
    private static String KEY_EMAIL = "email";
    

   private EditText emailField,passwordField;
   private TextView status, role, loginErrorMsg;
   Button LoginButton;
   Button RegisterButton;
   Button passResetButton;
   
   @Override 
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.login);
      emailField = (EditText)findViewById(R.id.emailField);
      passwordField = (EditText)findViewById(R.id.passwordField);
      LoginButton = (Button) findViewById(R.id.loginbtn);
      RegisterButton = (Button) findViewById(R.id.registerbtn);
      passResetButton = (Button) findViewById(R.id.passresetbtn);
      loginErrorMsg = (TextView) findViewById(R.id.loginErrorMsg);
      
      
      status = (TextView)findViewById(R.id.loginStatusField);
      role = (TextView)findViewById(R.id.loginRoleField);
      
      
      
      
      RegisterButton.setOnClickListener(new View.OnClickListener() {   	 
          public void onClick(View view) {
        	  
              Intent myIntent = new Intent(view.getContext(), Register.class);
              startActivityForResult(myIntent, 0);
              finish();
           }});
      
      passResetButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
          Intent myIntent = new Intent(view.getContext(), PasswordReset.class);
          startActivityForResult(myIntent, 0);
          finish();
          }});
      
      LoginButton.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			login(v);
			
		}});
      
      
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.fapapp, menu);
      return true;
   }
  
   public void login(View view){
	   
	   
	   if (  ( !emailField.getText().toString().equals("")) && ( !passwordField.getText().toString().equals("")) )
       {
		  // NetAsync(view);
		   new ProcessLogin().execute();
		   
       }
       else if ( ( !emailField.getText().toString().equals("")) )
       {
           Toast.makeText(getApplicationContext(),
                   "Password field empty", Toast.LENGTH_SHORT).show();
       }
       else if ( ( !passwordField.getText().toString().equals("")) )
       {
           Toast.makeText(getApplicationContext(),
                   "Email field empty", Toast.LENGTH_SHORT).show();
       }
       else
       {
           Toast.makeText(getApplicationContext(),
                   "Email and Password field are empty", Toast.LENGTH_SHORT).show();
       }

   }
   /**
    * Async Task to check whether internet connection is working.
    **/
   
   /*
      private class NetCheck extends AsyncTask
      {
          private ProgressDialog nDialog;
          @Override
          protected void onPreExecute(){
              super.onPreExecute();
              nDialog = new ProgressDialog(Login.this);
              nDialog.setTitle("Checking Network");
              nDialog.setMessage("Loading..");
              nDialog.setIndeterminate(false);
              nDialog.setCancelable(true);
              nDialog.show();
          }
          @Override
          protected Object doInBackground(Object... args){
  /**
   * Gets current device state and checks for working internet connection by trying Google.
  **/
   /*
              ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
              NetworkInfo netInfo = cm.getActiveNetworkInfo();
              if (netInfo != null && netInfo.isConnected()) {
                  try {
                      URL url = new URL("http://www.google.com");
                      HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                      urlc.setConnectTimeout(3000);
                      urlc.connect();
                      if (urlc.getResponseCode() == 200) {
                          return true;
                      }
                  } catch (MalformedURLException e1) {
                      // TODO Auto-generated catch block
                      e1.printStackTrace();
                  } catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  }
              }
              return false;
          }
          
          protected void onPostExecute(Boolean th){
              if(th == true){
                  nDialog.dismiss();
                  new ProcessLogin().execute();
              }
              else{
                  nDialog.dismiss();
                  loginErrorMsg.setText("Error in Network Connection");
              }
          }
      }
      /**
       * Async Task to get and send data to My Sql database through JSON respone.
       **/
      private class ProcessLogin extends AsyncTask {
          private ProgressDialog pDialog;
          String email,password;
          @Override
          protected void onPreExecute() {
              super.onPreExecute();
              emailField = (EditText) findViewById(R.id.emailField);
              passwordField = (EditText) findViewById(R.id.passwordField);
              email = emailField.getText().toString();
              password = passwordField.getText().toString();
              pDialog = new ProgressDialog(Login.this);
              pDialog.setTitle("Contacting Servers");
              pDialog.setMessage("Logging in ...");
              pDialog.setIndeterminate(false);
              pDialog.setCancelable(true);
              pDialog.show();
          }
          @Override
          protected String doInBackground(Object... args) {
              UserFunctions userFunction = new UserFunctions();
              String query = userFunction.loginUser(email, password);
             // return json;
        	  return query;
          }
          //@Override
          protected void onPostExecute(JSONObject json) {
              try {
                 if (json.getString(KEY_SUCCESS) != null) {
                      String res = json.getString(KEY_SUCCESS);
                      if(Integer.parseInt(res) == 1){
                          pDialog.setMessage("Loading User Space");
                          pDialog.setTitle("Getting Data");
                          DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                          JSONObject json_user = json.getJSONObject("user");
                          /**
                           * Clear all previous data in SQlite database.
                           **/
                          UserFunctions logout = new UserFunctions();
                          logout.logoutUser(getApplicationContext());
                          db.addUser(json_user.getString(KEY_FIRSTNAME),json_user.getString(KEY_LASTNAME),json_user.getString(KEY_EMAIL),json_user.getString(KEY_MENTOR),json_user.getString(KEY_UID));
                         /**
                          *If JSON array details are stored in SQlite it launches the User Panel.
                          **/
                          Intent upanel = new Intent(getApplicationContext(), Main.class);
                          upanel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                          pDialog.dismiss();
                          startActivity(upanel);
                          /**
                           * Close Login Screen
                           **/
                          finish();
                      }else{
                          pDialog.dismiss();
                          loginErrorMsg.setText("Incorrect username/password");
                      }
                  }
              } catch (JSONException e) {
                  e.printStackTrace();
              }
         }
      }
      
      /*
      public void NetAsync(View view){
          new NetCheck().execute();
      }
      */
   
}
