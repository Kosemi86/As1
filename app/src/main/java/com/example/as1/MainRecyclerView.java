    package com.example.as1;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Locale;

    public class MainRecyclerView extends AppCompatActivity {
   private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<String> arrayList;
    private EditText itemNameEditText, fileNameEditText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recycler);
            getSupportActionBar().hide();
            String username = getIntent().getStringExtra("username");
            recyclerView = findViewById(R.id.recycler_id);
            itemNameEditText = findViewById(R.id.edt_text_id);
            fileNameEditText = findViewById(R.id.file_name_edit_text);
            Button btnDash = findViewById(R.id.btnDash);
            // Display the username in a TextView
            TextView txtUsername = findViewById(R.id.txtUsername);
            txtUsername.setText("Logged in as " + username);

            btnDash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainRecyclerView.this, DashBoard.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                    startActivity(intent);
                }
            });


   arrayList = new ArrayList<String>();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
      recyclerView.setLayoutManager(linearLayoutManager);
            myAdapter = new MyAdapter(arrayList,this);
            recyclerView.setAdapter(myAdapter);
            myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    arrayList.remove(position);

                    myAdapter.notifyItemRemoved(position);
                }
            });
        }


        public void addItem(View view) {
            String itemName = itemNameEditText.getText().toString();
            String fileName = fileNameEditText.getText().toString();

            if (!itemName.isEmpty() && !fileName.isEmpty()) {
                String newItem = itemName + " (" + getCurrentDate() + ") - " + fileName;
                arrayList.add(newItem);
                myAdapter.notifyDataSetChanged();
                Toast.makeText(this, "You have saved an item", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter an item name and file name", Toast.LENGTH_SHORT).show();
            }
        }
        private String getCurrentDate() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            return dateFormat.format(calendar.getTime());
        }
    }
