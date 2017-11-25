package com.example.thevillain.mathforbaby.mainfunction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thevillain.mathforbaby.Objects.Units;
import com.example.thevillain.mathforbaby.R;
import com.example.thevillain.mathforbaby.SupportClass.MyFunctions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class UnitActivity extends AppCompatActivity {
    TextView textLesson1, textLesson2, textLesson3;
    ImageView imgLess1, imgLess2, imgLess3;
    String text;
    ProgressDialog pg_dialog;
    ArrayList<Units> arr_unit;
    int result;
    TextToSpeech toSpeech;
    Units unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        arr_unit = new ArrayList<Units>();

        //bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundlesend");
        String idreci = bundle.getString("send");

        new getDetail(idreci).execute();
        textLesson1 = (TextView) findViewById(R.id.textLesson1);
        textLesson2 = (TextView) findViewById(R.id.textLesson2);
        textLesson3 = (TextView) findViewById(R.id.textLesson3);

        //Image
        imgLess1 = (ImageView) findViewById(R.id.imageLesson1);
        imgLess2 = (ImageView) findViewById(R.id.imageLesson2);
        imgLess3 = (ImageView) findViewById(R.id.imageLesson3);

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

    class getDetail extends AsyncTask<Void, Void, Integer>

    {
        MyFunctions myfunctions;
        String id;

        public getDetail(String id) {
            this.id = id;
        }

        // Check for success tag
        @Override
        protected Integer doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            Integer thanhcong = 0;
            //getall
            try {
                myfunctions = new MyFunctions(getApplicationContext());
                JSONObject jsonobject = myfunctions.getDetail(id);
                thanhcong = jsonobject.getInt("thanhcong");
                //doc tat ca du lieu tu json bo vao ArrayList
                if (thanhcong == 1)//thanh cong
                {
                    //truy mang ten sanpham trong json
                    JSONArray jsonarray = jsonobject.getJSONArray("unit");
                    //duyet mang
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject item = jsonarray.getJSONObject(i);
                        String id = item.getString("id");
                        String unit_name = item.getString("unit_name");
                        String unit_img = item.getString("unit_img");
                        String lesson1_img = item.getString("lesson1_img");
                        String lesson1 = item.getString("lesson1");
                        String lesson2_img = item.getString("lesson2_img");
                        String lesson2 = item.getString("lesson2");
                        String lesson3_img = item.getString("lesson3_img");
                        String lesson3 = item.getString("lesson3");
                        unit = new Units(id, unit_name, unit_img, lesson1_img, lesson1, lesson2_img, lesson2, lesson3_img, lesson3);
                    }
                } else //that bai
                {
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return thanhcong;

        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pg_dialog = new ProgressDialog(UnitActivity.this);
            pg_dialog.setMessage("dang nap du lieu");
            pg_dialog.setIndeterminate(false);
            pg_dialog.setCancelable(false);//co the cancel bang phim back
            pg_dialog.show();
        }

        @Override
        protected void onPostExecute(Integer thanhcong) {
            // TODO Auto-generated method stub
            super.onPostExecute(thanhcong);


            pg_dialog.dismiss();

            textLesson1.setText(unit.getLesson1());
            textLesson2.setText(unit.getLesson2());
            textLesson3.setText(unit.getLesson3());

            Picasso.with(UnitActivity.this).load(unit.getLesson1_img()).into(imgLess1);
            Picasso.with(UnitActivity.this).load(unit.getLesson2_img()).into(imgLess2);
            Picasso.with(UnitActivity.this).load(unit.getLesson3_img()).into(imgLess3);
        }
    }


    //speak for lesson 1
    public void TIS(View view) {
        switch (view.getId()) {
            case R.id.imageVoice1:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "ABC", Toast.LENGTH_SHORT).show();
                    Log.d("ABC", text);
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
