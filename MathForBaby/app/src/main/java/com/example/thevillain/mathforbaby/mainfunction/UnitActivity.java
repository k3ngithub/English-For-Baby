package com.example.thevillain.mathforbaby.mainfunction;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thevillain.mathforbaby.R;

import java.util.Locale;

public class UnitActivity extends AppCompatActivity {
    TextView textLesson1,textLesson2,textLesson3,textTVlesson1,textTVlesson2,textTVlesson3;
    ImageView imgLess1,imgLess2,imgLess3;
    String text;
    int result;
    TextToSpeech toSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        //Textview T.Anh
        textLesson1 = (TextView)findViewById(R.id.textLesson1);
        textLesson2 = (TextView)findViewById(R.id.textLesson2);
        textLesson3 = (TextView)findViewById(R.id.textLesson3);
        //TextView T.Việt
        textTVlesson1 = (TextView)findViewById(R.id.textTiengvietLesson1);
        textTVlesson2 = (TextView)findViewById(R.id.textTiengvietLesson2);
        textTVlesson3 = (TextView)findViewById(R.id.textTiengvietLesson3);
        //Image
        imgLess1 = (ImageView)findViewById(R.id.imageLesson1);
        imgLess2 = (ImageView)findViewById(R.id.imageLesson2);
        imgLess3 = (ImageView)findViewById(R.id.imageLesson3);
        toSpeech = new TextToSpeech(UnitActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = toSpeech.setLanguage(Locale.ENGLISH);
                } else {
                    Toast.makeText(getApplicationContext(), "ABC", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //speak for lesson 1
    public void TIS(View view) {
        switch (view.getId()) {
            case R.id.imageVoice1:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "ABC", Toast.LENGTH_SHORT).show();
                    Log.d("ABC",text);
                } else {
                    text = textLesson1.getText().toString();
                    toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
        }
    }
    //speak for lesson 2
    public void TIS2(View view) {
        switch (view.getId()) {
            case R.id.imageVoice2:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "ABC", Toast.LENGTH_SHORT).show();
                } else {
                    text = textLesson2.getText().toString();
                    toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
        }
    }
    //speak for lesson 3
    public void TIS3(View view) {
        switch (view.getId()) {
            case R.id.imageVoice3:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "ABC", Toast.LENGTH_SHORT).show();
                } else {
                    text = textLesson3.getText().toString();
                    toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
        }
    }
    protected void onDeastroy() {
        super.onDestroy();
        if (toSpeech != null) {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }
}
