package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Emergency_contact extends AppCompatActivity {


    Button Savecontacts;
    EditText contact1, contact2, contact3;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
    }

    public void TransferActivity(View v){

        Savecontacts = findViewById(R.id.save_button);
        contact1 = findViewById(R.id.editText_contact1);
        contact2 = findViewById(R.id.editText_contact2);
        contact3 = findViewById(R.id.editText_contact3);


        db = openOrCreateDatabase("ContactDB", Context.MODE_PRIVATE, null);
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS contact (contact1 VARCHAR, contact2 VARCHAR, contact3 VARCHAR);"
        );

        db.delete("contact", null, null);

        if (contact1.getText().toString().trim().length() == 0
                || contact2.getText().toString().trim().length() == 0
                || contact3.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Please enter all contacts", Toast.LENGTH_SHORT).show();
            return;
        }
        db.execSQL(
                "INSERT INTO contact VALUES('" + contact1.getText() + "','" + contact2.getText() + "','" + contact3.getText()+"');"
        );



        Toast.makeText(this, "contacts are saved!", Toast.LENGTH_SHORT).show();


        Intent intent= new Intent(this, DashboardActivity.class);
        startActivity(intent);




    }
}