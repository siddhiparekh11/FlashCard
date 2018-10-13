package com.example.siddhiparekh11.codepathlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
//Codepath lab
public class MainActivity extends AppCompatActivity {

    TextView txtQuestion,txtAnswer,txtChoice1,txtChoice2,txtChoice3;
    RelativeLayout relChoices;
    ImageView toggleChoices;
    boolean isShowingAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtQuestion = findViewById(R.id.flashcard_question);
        txtAnswer = findViewById(R.id.flashcard_answer);
        txtChoice1 =  findViewById(R.id.choice1);
        txtChoice2 = findViewById(R.id.choice2);
        txtChoice3 =  findViewById(R.id.choice3);
        relChoices = findViewById(R.id.relchoices);
        toggleChoices = findViewById(R.id.toggle_choices_visibility);

        relChoices.setVisibility(View.INVISIBLE);


       /* txtQuestion.setBackgroundColor(getResources().getColor(R.color.blue,null));
        txtAnswer.setBackgroundColor(getResources().getColor(R.color.yellow,null)); */


        txtQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtQuestion.setVisibility(View.INVISIBLE);
                txtAnswer.setVisibility(View.VISIBLE);
            }
        });

        txtAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAnswer.setVisibility(View.INVISIBLE);
                txtQuestion.setVisibility(View.VISIBLE);
            }
        });

        txtChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(getResources().getColor(R.color.red,null));
            }
        });

        txtChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(getResources().getColor(R.color.red,null));
            }
        });
        txtChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(getResources().getColor(R.color.green,null));
            }
        });

        toggleChoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isShowingAnswers){
                    toggleChoices.setImageResource(R.drawable.ic_iconmonstr_eye_5);
                    relChoices.setVisibility(View.INVISIBLE);
                    isShowingAnswers=false;
                }else{
                    toggleChoices.setImageResource(R.drawable.ic_iconmonstr_eye_8);
                    relChoices.setVisibility(View.VISIBLE);
                    isShowingAnswers=true;
                }

            }
        });
    }
}
