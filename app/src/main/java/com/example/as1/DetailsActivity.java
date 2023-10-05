package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
public class DetailsActivity extends AppCompatActivity {

    private TextView textView;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if (intent != null) {
            message = intent.getStringExtra("item");
        }

        textView = findViewById(R.id.Text_view_id);
        textView.setText(message);
    }
}
