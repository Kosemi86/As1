package com.example.as1;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView userTextView;
    private EditText edtusername;
    private EditText edtpassword;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtPassword);
        btnlogin = findViewById(R.id.btnlogin);

        TextView txtLoggedInUser = findViewById(R.id.txtLoggedInUser);
        if (LoginDetails.loggedInUser != null) {
            txtLoggedInUser.setText("Logged in as: " + LoginDetails.loggedInUser);
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtusername.getText().toString();
                String password = edtpassword.getText().toString();
                boolean isValidLogin = LoginDetails.isValidLogin(username,password);

                if (isValidLogin)
                {
                    Intent intent = new Intent(MainActivity.this, DashBoard.class);
                    intent.putExtra("username", username); // Pass the username to the MainDash activity
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    protected void onStart()
    {
        Log.d(TAG, "Activity on onStart() stage ");
        super.onStart();
    }

    protected void onResume()
    {
        Log.d(TAG, "Activity on onResume() stage ");
        super.onResume();
    }

    protected void onStop()
    {
        Log.d(TAG, "Activity on onStop stage ");
        super.onStop();
    }

    protected void onDestroy()
    {
        Log.d(TAG, "Activity on onDestroy() stage ");
        super.onDestroy();
    }

    protected void onPause()
    {
        Log.d(TAG, "Activity on onPause(); stage ");
        super.onPause();
    }

}
