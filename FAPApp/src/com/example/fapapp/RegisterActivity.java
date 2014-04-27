package com.example.fapapp;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	EditText inputFirstName;
    EditText inputLastName;
    EditText inputUsername;
    EditText inputEmail;
    EditText inputPassword;
    Button btnRegister;
    TextView registerErrorMsg;
	 
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       setContentView(R.layout.register);
	       inputFirstName = (EditText) findViewById(R.id.fname);
	        inputLastName = (EditText) findViewById(R.id.lname);
	        inputUsername = (EditText) findViewById(R.id.uname);
	        inputEmail = (EditText) findViewById(R.id.email);
	        inputPassword = (EditText) findViewById(R.id.pword);
	        btnRegister = (Button) findViewById(R.id.register);
	        registerErrorMsg = (TextView) findViewById(R.id.register_error);
	        
	        
	        Button login = (Button) findViewById(R.id.bktologin);
	        login.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                Intent myIntent = new Intent(view.getContext(), FAPApp.class);
	                startActivityForResult(myIntent, 0);
	                finish();
	            }
	        });
	        
	        btnRegister.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                if (  ( !inputUsername.getText().toString().equals("")) && ( !inputPassword.getText().toString().equals("")) && ( !inputFirstName.getText().toString().equals("")) && ( !inputLastName.getText().toString().equals("")) && ( !inputEmail.getText().toString().equals("")) )
	                {
	                    if ( inputUsername.getText().toString().length() > 4 ){
	                   //call function to add info to server datatbase
	                    }
	                    else
	                    {
	                        Toast.makeText(getApplicationContext(),
	                                "Username should be minimum 5 characters", Toast.LENGTH_SHORT).show();
	                    }
	                }
	                else
	                {
	                    Toast.makeText(getApplicationContext(),
	                            "One or more fields are empty", Toast.LENGTH_SHORT).show();
	                }
	            }
	        });
	        
	        
	        
	 }
}
