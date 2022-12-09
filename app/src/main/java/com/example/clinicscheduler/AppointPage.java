package com.example.clinicscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AppointPage extends AppCompatActivity {

    ClinicDatabase clinicDatabase;
    DatePickerDialog datePickerDialog;
    TextView fname, lname, condition, diagnosis;
    Button birthdayBttn, appointDatePicker, appointTimePicker;
    RadioGroup radioGrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_page);

        clinicDatabase =new ClinicDatabase(this);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        condition = findViewById(R.id.condition);
        diagnosis = findViewById(R.id.diagnosis);
        birthdayBttn = findViewById(R.id.birthdayBttn);
        appointDatePicker = findViewById(R.id.appointDatePicker);
        appointTimePicker = findViewById(R.id.appointTimePicker);


    }
}