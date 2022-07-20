package in.santiniketan.myquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class QuizActivity extends AppCompatActivity {

    private Button quizbtn1;
    private Button quizbtn2;
    private Button quizbtn3;
    private Button quizbtn4;
    private Button quizbtn5;
    private Button quizbtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


       quizbtn1 = findViewById(R.id.quizbtn1);

        quizbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent quizintent = new Intent(QuizActivity.this,QuizActivity1.class);
                startActivity(quizintent);
                finish();

            }
        });

        quizbtn2 = findViewById(R.id.quizbtn2);

        quizbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent quizintent = new Intent(QuizActivity.this,QuizActivity2.class);
                startActivity(quizintent);
                finish();

            }
        });
        quizbtn3 = findViewById(R.id.quizbtn3);

        quizbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent quizintent = new Intent(QuizActivity.this,QuizActivity3.class);
                startActivity(quizintent);
                finish();

            }
        });

        quizbtn4 = findViewById(R.id.quizbtn4);

        quizbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent quizintent = new Intent(QuizActivity.this,QuizActivity4.class);
                startActivity(quizintent);
                finish();

            }
        });

        quizbtn5 = findViewById(R.id.quizbtn5);

        quizbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent quizintent = new Intent(QuizActivity.this,QuizActivity5.class);
                startActivity(quizintent);
                finish();

            }
        });

        quizbtn6 = findViewById(R.id.quizbtn6);

        quizbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent quizintent = new Intent(QuizActivity.this,QuizActivity6.class);
                startActivity(quizintent);
                finish();

            }
        });
    }







    @Override
    public void onBackPressed() {


        Intent intent = new Intent(QuizActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){

            Intent intent = new Intent(QuizActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
