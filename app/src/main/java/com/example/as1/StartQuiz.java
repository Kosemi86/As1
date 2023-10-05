package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        Button Dash = findViewById(R.id.btnDashboard);
        Button button = findViewById(R.id.btnStart);

        String username = getIntent().getStringExtra("username");

        // Display the username in a TextView
        TextView txtUsername = findViewById(R.id.txtUsername);
        txtUsername.setText("Logged in as " + username);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartQuiz.this, Quizquestions.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
                startActivity(intent);


            }


        });


        Dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartQuiz.this, DashBoard.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
                Toast.makeText(StartQuiz.this, "welcome to the Calculator Activity", Toast.LENGTH_SHORT).show();
            }
        });


    }


}