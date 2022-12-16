package com.example.clinicscheduler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewAll extends AppCompatActivity {

    ClinicDatabase clinicDatabase;
    Button viewAll, searchByID;
    EditText searchID, searchLName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        clinicDatabase = new ClinicDatabase(this);

        searchLName = findViewById(R.id.searchLName);
        searchByID = findViewById(R.id.searchById);
        searchID = findViewById(R.id.searchID);
        viewAll = findViewById(R.id.viewAll);

        showAll();
        showDataByID();
    }

    //Honestly dont know if this show is a good one
    public void showAll() {
        viewAll.setOnClickListener(new View.OnClickListener() {
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
                    buffer.append("Appoint Date: " + res.getString(8) + "\n\n");
                    buffer.append("Appoint Time: " + res.getString(9) + "\n\n");
                }

                if(res.getCount() == 0) {
                    Toast.makeText(ViewAll.this, "Table is empty", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    showMessage("Data",buffer.toString());
                }

            }
        });
    }

    public void showDataByID() {
        searchByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = searchID.getText().toString();
                String lname = searchLName.getText().toString();
                Cursor res = clinicDatabase.getDataByID(id,lname);
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id: " + res.getString(0) + "\n");
                    buffer.append("Firstname: " + res.getString(1) + "\n");
                    buffer.append("Lastname: " + res.getString(2) + "\n");
                    buffer.append("Points: " + res.getString(3) + "\n\n");
                }
                if(res.getCount() == 0) {
                    Toast.makeText(ViewAll.this, "Table is empty", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    showMessage("Data",buffer.toString());
                    return;
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


}