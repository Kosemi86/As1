package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Quizquestions extends AppCompatActivity {
    private TextView tvquestion, tvScore, tvQuestionNo, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private Button btnNext;


    int totalQuestion;
    int QCounter = 0;

    int score;


    ColorStateList dfbColor;
    boolean answered;

    CountDownTimer countDownTimer;

    private QuizSetters currentQuestion;

    private List<QuizSetters> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizquestions);

        questionsList = new ArrayList<>();
        tvquestion = findViewById(R.id.textQuestion);
        tvScore = findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.TextQuestionsno);
        tvTimer = findViewById(R.id.textTimer);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);
        dfbColor = rb1.getTextColors();

        String username = getIntent().getStringExtra("username");
        // Display the username in a TextView
        TextView txtUsername = findViewById(R.id.txtUsername);
        txtUsername.setText("Logged in as " + username);

        addQuestion();
        totalQuestion = questionsList.size();
        showNextQuestion();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answered == false)
                {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked())
                    {
                        checkAnswer();
                        countDownTimer.cancel();
                    }else
                    {
                        Toast.makeText(Quizquestions.this, "Please Select an option", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    showNextQuestion();
                }
            }
        });
    }

    private void checkAnswer() {

        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) + 1;

        if(answerNo == currentQuestion.getCorrectAnsNo())
        {
            score++;
            tvScore.setText("score:"+score);
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch(currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }
        if(QCounter < totalQuestion)
        {
            btnNext.setText("Next");

        }else
        {
            btnNext.setText("finish");
        }
    }

    private void showNextQuestion() {
        radioGroup.clearCheck();
        rb1.setTextColor(dfbColor);
        rb2.setTextColor(dfbColor);
        rb3.setTextColor(dfbColor);

        if(QCounter < totalQuestion){
            timer();
            currentQuestion = questionsList.get(QCounter);
            tvquestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            QCounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question:"+QCounter+"/"+totalQuestion);

            answered = false;
        }else {
            Intent intent = new Intent(Quizquestions.this, QuizScore.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            intent.putExtra("score", score);
            startActivity(intent);
            finish();

        }

    }



    private void timer() {
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText("00:" + 1/1000);
            }

            @Override
            public void onFinish() {
                showNextQuestion();
            }
        }.start();
    }

    private void addQuestion() {
        questionsList.add(new QuizSetters("What is the capital of France?", "Paris", "Rome", "Madrid", 1));
        questionsList.add(new QuizSetters("Which country is known as the Land of the Rising Sun?", "China","Japan","South Korea",2));
        questionsList.add(new QuizSetters("What is the smallest planet in our solar system?", "Mercury","Venus","Mars",1));
        questionsList.add(new QuizSetters("Who wrote the novel To Kill a Mockingbird", "Harper Lee"," John Steinbeck","Ernest Hemingway",1));
        questionsList.add(new QuizSetters("Who was the first person to step on the moon?", "Michael Collins"," Buzz Aldrin"," Neil Armstrong",3));
        questionsList.add(new QuizSetters("In which city is the famous Leaning Tower located?", " Florence"," Pisa ","Venice",2));
        questionsList.add(new QuizSetters("What is the name of the highest mountain in Africa?", "Mount Everest","  Denali"," Kilimanjaro",3));
        questionsList.add(new QuizSetters("What is the smallest country in the world by land area?", "Liechtenstein"," Vatican Cityn"," Monaco",2));
        questionsList.add(new QuizSetters("Which chemical element has the highest melting point at room temperature?", "Carbon"," Tungsten"," Titanium",3));
        questionsList.add(new QuizSetters("What is the name of the nearest star to our Sun?", "Sirius"," Proxima Centauri"," Betelgeuse",2));

    }
}