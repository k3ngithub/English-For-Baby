package com.example.thevillain.mathforbaby.mainfunction;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.thevillain.mathforbaby.Adapter.ListAdapter;
import com.example.thevillain.mathforbaby.Adapter.Units;
import com.example.thevillain.mathforbaby.R;
import com.example.thevillain.mathforbaby.SupportClass.MyFunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LearningActivity extends AppCompatActivity {
    ArrayList<Units> ds_unit;
    ListAdapter adapter;
    ProgressDialog pg_dialog;
    SwipeMenuListView lv_unit;
    //ListView lv_unit;
    EditText txtUnitName,txtUnitImg, txtLessonImg1,txtLesson1,txtLessonImg2,txtLesson2,txtLessonImg3,txtLesson3;
    final Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        ds_unit = new ArrayList<Units>();
        lv_unit=(SwipeMenuListView) findViewById(R.id.listunit);
        //lv_unit = (ListView)findViewById(R.id.listunit);
        new xulygettitleunitunits().execute();

    }
    class xulygettitleunitunits extends AsyncTask<Void,Void,Void>

    {
        MyFunctions myfunctions;

        public xulygettitleunitunits() {

        }

        // Check for success tag
        int success;
        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            String thanhcong=null;
            //getall
            try {
                myfunctions=new MyFunctions(getApplicationContext());
                JSONObject jsonobject=myfunctions.getAllUnit();
                thanhcong=jsonobject.getString("thanhcong");

                //doc tat ca du lieu tu json bo vao ArrayList
                if(Integer.parseInt(thanhcong)==1)//thanh cong
                {
                    //truy mang ten sanpham trong json
                    JSONArray jsonarray=jsonobject.getJSONArray("unit");
                    //duyet mang
                    for(int i=0;i<jsonarray.length();i++)
                    {
                        JSONObject item=jsonarray.getJSONObject(i);
                        String id=item.getString("id");
                        String unit_name=item.getString("unit_name");
                        String unit_img = item.getString("unit_img");
                        String lesson1_img = item.getString("lesson1_img");
                        String lesson1 = item.getString("lesson1");
                        String lesson2_img = item.getString("lesson2_img");
                        String lesson2 = item.getString("lesson2");
                        String lesson3_img = item.getString("lesson3_img");
                        String lesson3 = item.getString("lesson3");
                        Units unit = new Units(id, unit_name, unit_img, lesson1_img, lesson1, lesson2_img, lesson2, lesson3_img, lesson3);
                        ds_unit.add(unit);
                    }
                }
                else //that bai
                {

                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pg_dialog=new ProgressDialog(LearningActivity.this);
            pg_dialog.setMessage("dang nap du lieu");
            pg_dialog.setIndeterminate(false);
            pg_dialog.setCancelable(false);//co the cancel bang phim back
            pg_dialog.show();
        }
        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            pg_dialog.dismiss();
            adapter = new ListAdapter(LearningActivity.this, R.layout.list_itemunit, ds_unit);
            lv_unit.setAdapter(adapter);
            super.onPostExecute(result);
        }
    }
}
