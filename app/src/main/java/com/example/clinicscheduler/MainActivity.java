package com.example.clinicscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Login Credentials
        // Username: ClinicSecretary
        // Password: clinic1234

        String account_username = "ClinicSecretary";
        String account_password = "clinic1234";
        EditText userName = (EditText) findViewById(R.id.userTxt);
        EditText pass = (EditText) findViewById(R.id.passwordTxt);
        TextView forgot = (TextView) findViewById(R.id.forgotPassword);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String password = pass.getText().toString();
                if(!user.equals(account_username) && !password.equals(account_password)){
                    Log.i("info", "Incorrect password and username. User: " + user +"Pass: " + password);
                    Toast.makeText(getBaseContext(), "Incorrect password and username", Toast.LENGTH_LONG).show();
                }
                else if(!user.equals(account_username)&&password.equals(account_password)){
                    Log.i("info", "Incorrect username. User: " + user +"Pass: " + password);
                    Toast.makeText(getBaseContext(), "Incorrect username.", Toast.LENGTH_LONG).show();
                }
                else if(user.equals(account_username)&&!password.equals(account_password)){
                    Log.i("info", "Incorrect password. User: " + user +"Pass: " + password);
                    Toast.makeText(getBaseContext(), "Incorrect password.", Toast.LENGTH_LONG).show();
                }
                else{
                    accessAppointmentPage();
                    Log.i("info", "Correct Password");
                    Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void accessAppointmentPage(){
        Intent i = new Intent(this,AppointPage.class);
        startActivity(i);

    }

}