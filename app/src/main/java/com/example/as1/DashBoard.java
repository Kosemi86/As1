package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Button button = findViewById(R.id.btnActivity1);

        Button btnQuiz = findViewById(R.id.btnQuiz);

        Button btnStorage = findViewById(R.id.btnStorage);

        String username = getIntent().getStringExtra("username");

        // Display the username in a TextView
        TextView txtUsername = findViewById(R.id.txtUsername);
        txtUsername.setText("Logged in as " + username);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, ColorChange.class);
                intent.putExtra("username", username); // Pass the username to the MainDash activity
                startActivity(intent);
                finish();
                Toast.makeText(DashBoard.this, "welcome to the color change activity", Toast.LENGTH_SHORT).show();
            }
        });



        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, StartQuiz.class);
                intent.putExtra("username", username); // Pass the username to the MainDash activity
                startActivity(intent);
                finish();
                Toast.makeText(DashBoard.this, "welcome to this general knowledge quiz", Toast.LENGTH_SHORT).show();
            }
        });

        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, MainRecyclerView.class);
                intent.putExtra("username", username); // Pass the username to the MainDash activity
                startActivity(intent);
                finish();
                Toast.makeText(DashBoard.this, "welcome to this file Storage", Toast.LENGTH_SHORT).show();
            }
        });



    }
}