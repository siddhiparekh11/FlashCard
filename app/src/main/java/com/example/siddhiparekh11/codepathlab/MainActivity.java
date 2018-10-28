package com.example.siddhiparekh11.codepathlab;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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
    ImageView toggleChoices,addFlashCard,editFlashCard;
    boolean isShowingAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtQuestion = findViewById(R.id.flashcard_question);
        txtAnswer = findViewById(R.id.flashcard_answer);
      /*  txtChoice1 =  findViewById(R.id.choice1);
        txtChoice2 = findViewById(R.id.choice2);
        txtChoice3 =  findViewById(R.id.choice3);
        relChoices = findViewById(R.id.relchoices);
        toggleChoices = findViewById(R.id.toggle_choices_visibility);*/
        addFlashCard = findViewById(R.id.addFlashCard);
        editFlashCard = findViewById(R.id.editFlashCard);

     //   relChoices.setVisibility(View.INVISIBLE);


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

       /* txtChoice1.setOnClickListener(new View.OnClickListener() {
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
        });*/

        addFlashCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddFlashCardActivity.class);
                startActivityForResult(intent,100);
            }
        });

        editFlashCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,AddFlashCardActivity.class);
                intent.putExtra("Question",txtQuestion.getText());
                intent.putExtra("Answer",txtAnswer.getText());
                startActivityForResult(intent,100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            txtQuestion.setText(data.getExtras().getString("Question"));
            txtAnswer.setText(data.getExtras().getString("Answer"));
            Snackbar.make(txtAnswer.getRootView(),"Card successfully created",Snackbar.LENGTH_SHORT).show();
        }
    }


}
