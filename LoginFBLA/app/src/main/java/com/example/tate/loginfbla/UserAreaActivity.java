package com.example.tate.loginfbla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //String email = intent.getStringExtra("email");
        int age = intent.getIntExtra("age", -1);

        String message = "Welcome to the FBLA Yard Sale!";
        welcomeMessage.setText(message);
        etName.setText(name);
        etAge.setText(age + "");

    }

    public void goToEventInformationActivity (View view) {
        Intent i = new Intent(UserAreaActivity.this, EventInformationActivity.class);
        startActivity(i);

    }

    public void goToSubmitListingActivity (View view) {
        Intent j = new Intent(UserAreaActivity.this, SubmitActivity.class);
        startActivity(j);
    }
}
