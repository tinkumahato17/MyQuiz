package in.santiniketan.myquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity1 extends AppCompatActivity {

    private TextView question, no_counter;
    private LinearLayout option_layout;
    private Button next_btn;
    private int count = 0;
    private List<QuestionModel> list;
    private  int position =0;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        question = findViewById(R.id.question);
        no_counter = findViewById(R.id.no_conter);
        option_layout = findViewById(R.id.option_layout);
        next_btn = findViewById(R.id.next_btn);

        list = new ArrayList<>();
        list.add(new QuestionModel("Microprocessors can be used to make", "Digital system", "Mobile Phone", "Computer", "All of this", "All of this"));
        list.add(new QuestionModel("Which memory is volatile in nature ?", "PROM", "Dynamic RAM", "Static RAM", "None of this", "Static RAM"));
        list.add(new QuestionModel("The user can enter the data through", "cpu", "keyboard", "plotter", "printer", "keyboard"));
        list.add(new QuestionModel("CPU is the ", "Heard of computer system", "Nervous system of computer", "Brain of computer system", "Cntral part of computer system", "Heard of computer system"));
        list.add(new QuestionModel("Which of the following require large computers memory", "imaging", "graphics", "video", "all the above", "all the above"));
        list.add(new QuestionModel("Which of the following is used as a primary storage devices ?","Magnetic drum","Floppy","DVD","PROM","PROM"));
        list.add(new QuestionModel("The keyboard consists of ","memory","cu","function key","none","function key"));
        list.add(new QuestionModel("We can get into ____ menu by pressing Alt+F.","file","edit","view","none","file"));
        list.add(new QuestionModel("ALU stands for","Access Logic unit","Application Logic Unit","Arithmetic Logic Unit","none","Arithmetic Logic Unit"));
        list.add(new QuestionModel("LAN stands for ?","last affordable network","leased area network","local area network","none","local area network"));
        for (int i = 0; i < 4; i++) {

            option_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer((Button)view);
                }
            });

        }


        playAnim(question,0,list.get(position).getQuistion());
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_btn.setEnabled(false);
                next_btn.setAlpha(0.07f);
                enableoption(true);
                position++;

                if (position ==list.size()){
                    Intent scoreintent = new Intent(QuizActivity1.this,ScoreActivity.class);
                    finish();

                    scoreintent.putExtra("score",score);
                    scoreintent.putExtra("total",list.size());
                    startActivity(scoreintent);
                    return;
                }

                count =0;
                playAnim(question,0,list.get(position).getQuistion());
            }
        });
    }

    private void enableoption(boolean enable) {

        for (int i = 0; i<4; i++){
            option_layout.getChildAt(i).setEnabled(enable);
            if (enable){
                option_layout.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#654321")));

            }
        }

    }

    private void playAnim(final View view,final int value,final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(1).setDuration(500).setStartDelay(50)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (value == 0 && count<4){
                    String option="";
                    if (count == 0){

                        option = list.get(position).getOptionA();
                    }

                    else if (count == 1){
                        option =list.get(position).getOptionB();
                    }
                    else if (count == 2){
                        option =list.get(position).getOptionC();
                    }
                    else if (count == 3){
                        option =list.get(position).getOptionD();

                    }
                    playAnim(option_layout.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                if (value == 0){

                    try {
                        ((TextView)view).setText(data);
                        no_counter.setText(position+1+"/"+list.size());
                    }
                    catch (ClassCastException ex){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);

                    playAnim(view,1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QuizActivity1.this,QuizActivity.class);
        startActivity(intent);
        finish();
    }

    private void checkAnswer(Button selectedoption) {

        enableoption(false);
        next_btn.setEnabled(true);
        next_btn.setAlpha(1);
        if (selectedoption.getText().toString().equals(list.get(position).getCorrectAns())) {
            score++;
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0e9913")));
            StyleableToast.makeText(getApplicationContext(), "Right Answer", R.style.rightToast).show();
        }
        else {
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctoption =  (Button) option_layout.findViewWithTag(list.get(position).getCorrectAns());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0e3636")));

            StyleableToast.makeText(getApplicationContext(),"Wrong Answer",R.style.wrongToast).show();
        }


    }

}
