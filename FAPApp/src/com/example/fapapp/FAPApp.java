package com.example.fapapp;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FAPApp extends Activity {

   private EditText usernameField,passwordField;
   private TextView status,role;

   @Override 
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_fapapp);
      usernameField = (EditText)findViewById(R.id.userNameField);
      passwordField = (EditText)findViewById(R.id.passwordField);
      status = (TextView)findViewById(R.id.loginStatusField);
      role = (TextView)findViewById(R.id.loginRoleField);
     
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.fapapp, menu);
      return true;
   }
  
   public void login(View view){
      String username = usernameField.getText().toString();
      String password = passwordField.getText().toString();
      new SignInActivity(this,status,role,1).execute(username,password);

   }

}
