package com.example.siddhiparekh11.codepathlab;

import android.animation.Animator;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.List;
import java.util.Random;

//Codepath lab
public class MainActivity extends AppCompatActivity {

    TextView txtQuestion,txtAnswer,txtChoice1,txtChoice2,txtChoice3,txtCounter;
    RelativeLayout relChoices;
    ImageView toggleChoices,addFlashCard,editFlashCard,btnNext,btnDelete,btnEdit;
    boolean isShowingAnswers;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = -1;
    CountDownTimer  countDownTimer;
    View answerSideView,questionSideView;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        txtQuestion = findViewById(R.id.flashcard_question);
        txtAnswer = findViewById(R.id.flashcard_answer);
        txtCounter = findViewById(R.id.txtTimer);
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
        answerSideView = findViewById(R.id.flashcard_answer);
        questionSideView = findViewById(R.id.flashcard_question);

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

        final Animation leftOutAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out);
        final Animation rightInAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            txtQuestion.startAnimation(leftOutAnim);
            startTimer();
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



        leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // this method is called when the animation first starts
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // this method is called when the animation is finished playing
                txtQuestion.startAnimation(rightInAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // we don't need to worry about this method
            }
        });



                txtQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* txtQuestion.setCameraDistance(25000);
                txtAnswer.setCameraDistance(25000);
                questionSideView.animate()
                        .rotationY(90)
                        .setDuration(200)
                        .withEndAction(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        questionSideView.setVisibility(View.INVISIBLE);
                                        txtAnswer.setVisibility(View.VISIBLE);
                                        // second quarter turn
                                        txtAnswer.setRotationY(-90);
                                        txtAnswer.animate()
                                                .rotationY(0)
                                                .setDuration(200)
                                                .start();
                                    }
                                }
                        ).start();*/



                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
               Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);
                txtQuestion.setVisibility(View.INVISIBLE);
                txtAnswer.setVisibility(View.VISIBLE);
                anim.setDuration(3000);
                anim.start();

            }
        });


        txtAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                        .setSpeedRange(0.2f, 0.5f)
                        .oneShot(txtAnswer, 100);

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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
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

        countDownTimer = new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                txtCounter.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
            }
        };


    }

    private void startTimer(){
        countDownTimer.cancel();
        countDownTimer.start();
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
