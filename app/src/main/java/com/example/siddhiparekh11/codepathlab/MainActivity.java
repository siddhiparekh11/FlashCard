package com.example.siddhiparekh11.codepathlab;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

//Codepath lab
public class MainActivity extends AppCompatActivity {

    TextView txtQuestion,txtAnswer,txtChoice1,txtChoice2,txtChoice3;
    RelativeLayout relChoices;
    ImageView toggleChoices,addFlashCard,editFlashCard,btnNext,btnDelete,btnEdit;
    boolean isShowingAnswers;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        txtQuestion = findViewById(R.id.flashcard_question);
        txtAnswer = findViewById(R.id.flashcard_answer);
      /*  txtChoice1 =  findViewById(R.id.choice1);
        txtChoice2 = findViewById(R.id.choice2);
        txtChoice3 =  findViewById(R.id.choice3);
        relChoices = findViewById(R.id.relchoices);
        toggleChoices = findViewById(R.id.toggle_choices_visibility);*/
        addFlashCard = findViewById(R.id.addFlashCard);
        editFlashCard = findViewById(R.id.editFlashCard);
        btnNext = findViewById(R.id.btnNext);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);

     //   relChoices.setVisibility(View.INVISIBLE);


       /* txtQuestion.setBackgroundColor(getResources().getColor(R.color.blue,null));
        txtAnswer.setBackgroundColor(getResources().getColor(R.color.yellow,null)); */

        if (allFlashcards != null && allFlashcards.size() > 0) {

            txtQuestion.setText(allFlashcards.get(0).getQuestion());
            txtAnswer.setText(allFlashcards.get(0).getQuestion());
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(txtQuestion.getText().toString());
                allFlashcards.remove(currentCardDisplayedIndex);
                currentCardDisplayedIndex=0;

                    txtQuestion.setText("");
                    txtAnswer.setText("");

                if(allFlashcards.size()!=0) {
                    txtAnswer.setVisibility(View.INVISIBLE);
                    txtQuestion.setVisibility(View.VISIBLE);
                    txtQuestion.setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    txtAnswer.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                currentCardDisplayedIndex=getRandomNumber(0,allFlashcards.size()-1);
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                if(allFlashcards.size()!=0) {
                    txtAnswer.setVisibility(View.INVISIBLE);
                    txtQuestion.setVisibility(View.VISIBLE);
                    txtQuestion.setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    txtAnswer.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                }

            }
        });


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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddFlashCardActivity.class);
                intent.putExtra("Question",txtQuestion.getText());
                intent.putExtra("Answer",txtAnswer.getText());
                startActivityForResult(intent,101);
            }
        });
    }

    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            txtQuestion.setText(data.getExtras().getString("Question"));
            txtAnswer.setText(data.getExtras().getString("Answer"));
            Flashcard flashcard = new Flashcard(txtQuestion.getText().toString(),txtAnswer.getText().toString());
            flashcardDatabase.insertCard(flashcard);
            allFlashcards.add(flashcard);
            Snackbar.make(txtAnswer.getRootView(),"Card successfully created",Snackbar.LENGTH_SHORT).show();
        }else if(requestCode==101 && resultCode==RESULT_OK){
            txtQuestion.setText(data.getExtras().getString("Question"));
            txtAnswer.setText(data.getExtras().getString("Answer"));
            allFlashcards.get(currentCardDisplayedIndex).setAnswer(data.getExtras().getString("Answer"));
            allFlashcards.get(currentCardDisplayedIndex).setQuestion(data.getExtras().getString("Question"));
            flashcardDatabase.updateCard(allFlashcards.get(currentCardDisplayedIndex));
        }

    }


}
