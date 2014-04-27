package com.example.fapapp;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FAPApp extends Activity {

   private EditText usernameField,passwordField;
   private TextView status,role;
   Button LoginButton;
   Button RegisterButton;
   Button passResetButton;
   @Override 
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.login);
      usernameField = (EditText)findViewById(R.id.userNameField);
      passwordField = (EditText)findViewById(R.id.passwordField);
      LoginButton = (Button) findViewById(R.id.loginbtn);
      RegisterButton = (Button) findViewById(R.id.registerbtn);
      passResetButton = (Button) findViewById(R.id.passresetbtn);
      
      
      status = (TextView)findViewById(R.id.loginStatusField);
      role = (TextView)findViewById(R.id.loginRoleField);
      
      
      
      
      RegisterButton.setOnClickListener(new View.OnClickListener() {
    	 
          public void onClick(View view) {
        	  System.out.println("TEST (***********) INSIDE CLICK LISTENER");
              Intent myIntent = new Intent(view.getContext(), RegisterActivity.class);
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
	   
	   
	   if (  ( !usernameField.getText().toString().equals("")) && ( !passwordField.getText().toString().equals("")) )
       {
		   String username = usernameField.getText().toString();
		      String password = passwordField.getText().toString();
		      new SignInActivity(this,status,role).execute(username,password);
       }
       else if ( ( !usernameField.getText().toString().equals("")) )
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

  
   
   
}
