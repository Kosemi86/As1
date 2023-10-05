package com.example.as1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizScore extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView txtUsername;
    private TextView savedScoreTextView;
    private Button btnRetry;
    private Button btnSave;
    private int score;
    private TextView oldScoreTextView;
    private String username;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        username = intent.getStringExtra("username");

        sharedPref = this.getSharedPreferences("com.example.as1", Context.MODE_PRIVATE);

        int oldScore = sharedPref.getInt("score", 0);
        oldScoreTextView = findViewById(R.id.oldScoreTextView);
        oldScoreTextView.setText(username + "Old score: " + oldScore);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(username+ "score", score);
        editor.apply();

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(username+ "Your score is: " + score);

        txtUsername = findViewById(R.id.txtUsername);
        txtUsername.setText("Logged in as " + username);

        savedScoreTextView = findViewById(R.id.savedScoreTextView);
        int savedScore = sharedPref.getInt("score", 0);
        savedScoreTextView.setText("Saved score: " + savedScore);

        btnRetry = findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizScore.this, StartQuiz.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

        btnSave = findViewById(R.id.btnsave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("score", score);
                editor.apply();
                int savedScore = sharedPref.getInt("score", 0);
                savedScoreTextView.setText("Saved score: " + savedScore);
            }
        });
    }
}
