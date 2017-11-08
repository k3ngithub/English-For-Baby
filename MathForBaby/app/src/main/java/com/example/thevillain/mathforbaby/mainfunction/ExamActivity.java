package com.example.thevillain.mathforbaby.mainfunction;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thevillain.mathforbaby.Adapter.Exams;
import com.example.thevillain.mathforbaby.R;
import com.example.thevillain.mathforbaby.SupportClass.MyFunctions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {
    ImageView imageViewExam;
    RadioButton radioButton1, radioButton2, radioButtonResultans;
    Button btNext;
    ArrayList<Exams> arr_exam;
    String exam_img;
    String exam_question;
    String exam_answer1;
    String exam_answer2;
    String exam_resultans;
    String exam_score;
    Exams exam;
    ArrayList bbjec;
    ProgressDialog pg_dialog;
    String idrandom;
    int rand;
    int count;
    String s;
    int i1;
    String histr;
    int so;
    List<Integer> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        init();
        //new getDetail("1").execute();

        arr_exam = new ArrayList<Exams>();

        randomdata();


        final TextView tvss = (TextView) findViewById(R.id.textViewTitleExam);
        tvss.setText(list.toString());

        so = 0;

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bbjec.remove(i1);
                count = count +1;

                String sodata = String.valueOf(list.get(so));
                new getDetail(sodata).execute();

                Toast.makeText(ExamActivity.this, so+"", Toast.LENGTH_LONG).show();
                so = so + 1;

                if(so == 9){
//                    Intent i = new Intent(ExamActivity.this, )

                }


            }
        });

    }

    private void randomdata(){
//
        list = new ArrayList<Integer>();
        for(int i = 0; i < 10;)
        {
            rand = ((int)(Math.random() * 10)) + 1;

            if(!list.contains(rand))
            {
                list.add(rand);
                int hi = list.get(i);
                histr = String.valueOf(hi);
                i++;
            }
        }
        new getDetail(histr).execute();

    }



//    private void setdata() {
//        Picasso.with(ExamActivity.this).load(exam.getImage()).into(imageViewExam);
//
//        radioButton1.setText(exam.getAnswer1());
//        radioButton2.setText(exam.getAnswer2());
//        radioButtonResultans.setText(exam.getResultans());
//    }


    public void init(){
        imageViewExam = (ImageView) findViewById(R.id.imageViewExam);
        radioButton1 = (RadioButton) findViewById(R.id.radioQues1);
        radioButton2 = (RadioButton) findViewById(R.id.radioQues2);
        radioButtonResultans = (RadioButton) findViewById(R.id.radioQue3);
        btNext = (Button) findViewById(R.id.btNextQues);


    }

    class getDetail extends AsyncTask<Void,Void,Integer>

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
                JSONObject jsonobject = myfunctions.getExamDetail(id);
                thanhcong = jsonobject.getInt("thanhcong");
                //doc tat ca du lieu tu json bo vao ArrayList
                if (thanhcong == 1)//thanh cong
                {
                    //truy mang ten sanpham trong json
                    JSONArray jsonarray = jsonobject.getJSONArray("exam");
                    //duyet mang
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject item = jsonarray.getJSONObject(i);
                        final String id = item.getString("id");
                        exam_img = item.getString("image");
                        exam_question = item.getString("questions");
                        exam_answer1 = item.getString("answer1");
                        exam_answer2 = item.getString("answer2");
                        exam_resultans = item.getString("resultans");
                        exam_score = item.getString("score");

                        exam = new Exams(id, exam_img, exam_question, exam_answer1, exam_answer2, exam_resultans, exam_score);

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
            pg_dialog = new ProgressDialog(ExamActivity.this);
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
            //Toast.makeText(ExamActivity.this, exam_img + "", Toast.LENGTH_SHORT).show();
            Picasso.with(ExamActivity.this).load(exam_img).into(imageViewExam);

            radioButton1.setText(exam_answer1);
            radioButton2.setText(exam_answer2);
            radioButtonResultans.setText(exam_resultans);
        }
    }


}
