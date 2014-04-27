package com.example.fapapp;

import android.app.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
	 }
}
