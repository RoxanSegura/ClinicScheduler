package com.example.clinicscheduler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AppointPage extends AppCompatActivity {

    ClinicDatabase clinicDatabase;
    DatePickerDialog datePickerDialog;
    TextView fname, lname, age, condition, diagnosis;
    Button bDayPicker, appointDatePicker, appointTimePicker, submitRecord, viewRecords;
    RadioGroup radioGrp;

    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_page);

        clinicDatabase =new ClinicDatabase(this);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        age = findViewById(R.id.age);
        condition = findViewById(R.id.condition);
        diagnosis = findViewById(R.id.diagnosis);
        appointDatePicker = findViewById(R.id.appointDatePicker);
        appointTimePicker = findViewById(R.id.appointTimePicker);
        submitRecord = findViewById(R.id.recordSubmit);
        viewRecords = findViewById(R.id.viewRecords);
        radioGrp = findViewById(R.id.radiogrp);

        initDatePicker(); //For the Appointment Date Button
        setTime();
        submitData();
        showAll();
    }

    //Honestly dont know if this show is a good one
    public void showAll() {
        viewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = clinicDatabase.getAllData();
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id: " + res.getString(0) + "\n\n");
                    buffer.append("Firstname: " + res.getString(1) + "\n\n");
                    buffer.append("Lastname: " + res.getString(2) + "\n\n");
                    buffer.append("Age: " + res.getString(3) + "\n\n");
                    buffer.append("Gender: " + res.getString(4) + "\n\n");
                    buffer.append("Birthday: " + res.getString(5) + "\n\n");
                    buffer.append("Condition: " + res.getString(6) + "\n\n");
                    buffer.append("Diagnosis: " + res.getString(7) + "\n\n");
                    buffer.append("Appopint Date: " + res.getString(8) + "\n\n");
                    buffer.append("Appoint Time: " + res.getString(9) + "\n\n");
                }

                if(res.getCount() == 0) {
                    Toast.makeText(AppointPage.this, "Table is empty", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    showMessage("Data",buffer.toString());
                }

            }
        });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

public boolean checkInputs(){
        boolean isEmpty = false;
        int select = radioGrp.getCheckedRadioButtonId();
        RadioButton radio = (RadioButton) findViewById(select);

        if(fname.getText().toString().matches("") || lname.getText().toString().matches("") || age.getText().toString().matches("") ||
        radio.getText().toString().matches("") || bDayPicker.getText().toString().matches("") || condition.getText().toString().matches("") ||
            diagnosis.getText().toString().matches("") || appointDatePicker.getText().toString().matches("APPOINTMENT DATE") ||
                appointTimePicker.getText().toString().matches("APPOINTMENT TIME")) {
        isEmpty = true;
     }
        return isEmpty;
}

    /*Insert Data to the database*/

public void submitData() {
        submitRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!checkInputs()){

                    int select = radioGrp.getCheckedRadioButtonId();
                    RadioButton radio = (RadioButton) findViewById(select);

                    boolean res = clinicDatabase.insertData(fname.getText().toString(),lname.getText().toString(),age.getText().toString(),radio.getText().toString(),
                            bDayPicker.getText().toString(), condition.getText().toString(), diagnosis.getText().toString(), appointDatePicker.getText().toString(), appointTimePicker.getText().toString());

                    if(res == false)
                        Toast.makeText(AppointPage.this, "Data Insertion Failed", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AppointPage.this, "Data Insertion Successful", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(AppointPage.this, "Please Fill in the boxes", Toast.LENGTH_LONG).show();
                }

        }
    });
 }

    /*Appointment Date*/

public void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day,month,year);
                appointDatePicker.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int Year = cal.get(Calendar.YEAR);
        int Month = cal.get(Calendar.MONTH);
        int Day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, Year, Month, Day);
    }

private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

private String getMonthFormat(int month) {
        if(month == 1)
            return "Jan";
        if(month == 2)
            return "Feb";
        if(month == 3)
            return "Mar";
        if(month == 4)
            return "Apr";
        if(month == 5)
            return "May";
        if(month == 6)
            return "Jun";
        if(month == 7)
            return "Jul";
        if(month == 8)
            return "Aug";
        if(month == 9)
            return "Sep";
        if(month == 10)
            return "Oct";
        if(month == 11)
            return "Nov";
        if(month == 12)
            return "Dec";
        //Default
        return "Jan";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void setTime(){
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                appointTimePicker.setText(hour + ":" + minute);
            }
        }, mHour, mMinute, false);

        appointTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        Log.i("info","yes " + appointTimePicker);
    }

}

/*Appointment Time*/

