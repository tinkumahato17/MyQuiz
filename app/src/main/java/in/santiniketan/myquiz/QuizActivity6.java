package in.santiniketan.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity6 extends AppCompatActivity {

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
        setContentView(R.layout.activity_quiz6);

        question = findViewById(R.id.question);
        no_counter = findViewById(R.id.no_conter);
        option_layout = findViewById(R.id.option_layout);
        next_btn = findViewById(R.id.next_btn);

        list = new ArrayList<>();
        list.add(new QuestionModel("Which of the following is the product of data processing ?", "information", "data", "software program", "system", "information"));
        list.add(new QuestionModel("Which one is not input device ?", "keyboard", "mouse", "speaker", "scanner", "speaker"));
        list.add(new QuestionModel("Portable program means", "independent from its authors", "independent of platform", "program with wheels", "none", "ndependent of platform"));
        list.add(new QuestionModel("Memories which can be read only are called ____.", "ram", "rom", "dram", "vertual memory", "rom"));
        list.add(new QuestionModel("CPU controls _____.", "all input, output and processing", "controls memory", "cotrolled by the input data", "none", "all input, output and processing"));
        list.add(new QuestionModel("UBS stands for _____.", "universal serial bus", "universal sequential bus", "unique serial bus", "none", "universal serial bus"));
        list.add(new QuestionModel("Wcich of the following holds the ROM,RAM,CPU ?", "hard disk", "alu", "mother board", "none ", "mother board\""));
        list.add(new QuestionModel("What technology of memory is Cache RAM usually ?", "dram", "flash", "sram", "prom", "sram"));
        list.add(new QuestionModel("Where is Computer's BIOS stored ?", "dram", "flash", "sram", "prom", "flash"));
        list.add(new QuestionModel("Which company is not a PC manufacturer ?", "intel", "gateway", "dell", "none", "intel"));

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
                    Intent scoreintent = new Intent(QuizActivity6.this,ScoreActivity.class);
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
        Intent intent = new Intent(QuizActivity6.this,QuizActivity.class);
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
