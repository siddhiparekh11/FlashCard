package com.example.siddhiparekh11.codepathlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddFlashCardActivity extends AppCompatActivity {

    private ImageView imgCancel;
    private ImageView imgImport;
    private EditText edtQuestion, edtAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        imgCancel = findViewById(R.id.imgCancelView);
        imgImport = findViewById(R.id.imgImportView);
        edtQuestion = findViewById(R.id.edtQuestion);
        edtAnswer = findViewById(R.id.edtAnswer);

        if(getIntent().hasExtra("Question"))
            edtQuestion.setText(getIntent().getExtras().getString("Question"));
        if(getIntent().hasExtra("Answer"))
            edtAnswer.setText(getIntent().getExtras().getString("Answer"));
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("QuestionText",edtQuestion.getText().toString());
                if((!edtQuestion.getText().toString().equals("")) && (!edtAnswer.getText().equals(""))) {
                    Intent intent = new Intent(AddFlashCardActivity.this, MainActivity.class);
                    intent.putExtra("Question", edtQuestion.getText().toString());
                    intent.putExtra("Answer", edtAnswer.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Please enter question and answer",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            }
        });

    }
}
