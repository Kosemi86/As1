package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ColorChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_change);

        Button button = findViewById(R.id.btnColor);
        Button Dashboard = findViewById(R.id.btnDashboard);

        EditText editColor = findViewById(R.id.editColor);
        Button btnApply = findViewById(R.id.btnApply);
        View layout = findViewById(android.R.id.content);

        String username = getIntent().getStringExtra("username");

        // Display the username in a TextView
        TextView txtUsername = findViewById(R.id.txtUsername);
        txtUsername.setText("Logged in as " + username);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int color = Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                button.setBackgroundColor(color);
            }
        });



        Dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ColorChange.this, DashBoard.class);
                intent.putExtra("username", username); // Pass the username to the MainDash activity
                startActivity(intent);
                finish();
                Toast.makeText(ColorChange.this, "welcome to the dashboard", Toast.LENGTH_SHORT).show();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String colorString = editColor.getText().toString();
                try {
                    int color = Color.parseColor(colorString);
                    layout.setBackgroundColor(color);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "Invalid color", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }





}