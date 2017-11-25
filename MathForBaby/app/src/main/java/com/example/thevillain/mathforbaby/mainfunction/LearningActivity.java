package com.example.thevillain.mathforbaby.mainfunction;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.thevillain.mathforbaby.Adapter.ListAdapter;
import com.example.thevillain.mathforbaby.Objects.Units;
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

            SwipeMenuCreator creator = new SwipeMenuCreator() {

                @Override
                public void create(SwipeMenu menu) {
                    // create "open" item
                    SwipeMenuItem openItem = new SwipeMenuItem(
                            getApplicationContext());
                    // set item background
                    openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                            0xCE)));
                    // set item width
                    openItem.setWidth((90));
                    // set item title
                    openItem.setIcon(R.drawable.dp2px);
                    // set item title fontsize
                    openItem.setTitleSize(18);
                    // set item title font color
                    openItem.setTitleColor(Color.WHITE);
                    // add to menu
                    menu.addMenuItem(openItem);

                    // create "delete" item
                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                            getApplicationContext());
                    // set item background
                    deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                            0x3F, 0x25)));
                    // set item width
                    deleteItem.setWidth((90));
                    // set a icon
                    deleteItem.setIcon(R.drawable.ic_delete);
                    // add to menu
                    menu.addMenuItem(deleteItem);
                }
            };
            // set creator
            lv_unit.setMenuCreator(creator);
            lv_unit.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                    //ApplicationInfo item = ds.get(position);
                    switch (index) {
                        case 0:
                            // open
                            Dialog dialog1=new Dialog(LearningActivity.this);
                            dialog1.setTitle("Edit Units");
                            dialog1.setContentView(R.layout.editunit_layout);
                            ((ViewGroup)dialog1.getWindow().getDecorView()).getChildAt(0).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.slide_in_left));
                            txtUnitName = (EditText)dialog1.findViewById(R.id.editUnitname);
                            txtUnitImg = (EditText)dialog1.findViewById(R.id.editUnitImg);
                            txtLessonImg1 = (EditText)dialog1.findViewById(R.id.editLessonImg1);
                            txtLesson1 = (EditText)dialog1.findViewById(R.id.editLesson1);
                            txtLessonImg2 = (EditText)dialog1.findViewById(R.id.editLessonImg2);
                            txtLesson2 = (EditText)dialog1.findViewById(R.id.editLesson2);
                            txtLessonImg3 = (EditText)dialog1.findViewById(R.id.editLessonImg3);
                            txtLesson3 = (EditText)dialog1.findViewById(R.id.editLesson3);


                            txtUnitName.setText(ds_unit.get(position).getUnit_name());
                            txtUnitImg.setText(ds_unit.get(position).getUnit_img());
                            txtLessonImg1.setText(ds_unit.get(position).getLesson1_img());
                            txtLesson1.setText(ds_unit.get(position).getLesson1());
                            txtLessonImg2.setText(ds_unit.get(position).getLesson2_img());
                            txtLesson2.setText(ds_unit.get(position).getLesson2_img());
                            txtLessonImg3.setText(ds_unit.get(position).getLesson3_img());
                            txtLesson3.setText(ds_unit.get(position).getLesson3());

                            dialog1.show();
                            dialog1.findViewById(R.id.btOkEdit).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String newUnitName = txtUnitName.getText().toString();
                                    String newUnitImage = txtUnitImg.getText().toString();
                                    String newLessonImg1 = txtLessonImg1.getText().toString();
                                    String newLesson1 = txtLesson1.getText().toString();
                                    String newLessonImg2 = txtLessonImg2.getText().toString();
                                    String newLesson2 = txtLesson2.getText().toString();
                                    String newLessonImg3 = txtLessonImg3.getText().toString();
                                    String newLesson3 = txtLesson3.getText().toString();
                                    Units units = new Units(ds_unit.get(position).getId(), newUnitName, newUnitImage, newLessonImg1,newLesson1,newLessonImg2,newLesson2,newLessonImg3,newLesson3);
                                    new updateunits(units).execute();
                                }
                            });
                            dialog1.findViewById(R.id.btCancelEdit).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(LearningActivity.this,LearningActivity.class);
                                    startActivity(intent);
                                }
                            });
                            break;
                        case 1:
                            // delete
//					delete(item);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                            // set title
                            alertDialogBuilder.setTitle("Do you want to delete it?");

                            // set dialog message
                            alertDialogBuilder
                                    .setMessage("Your choice")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            new deleteunits(ds_unit.get(position).getId()).execute();
                                            //Xoa

                                        }
                                    })
                                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    });

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();

                    }
                    return false;
                }
            });

            // set SwipeListener
            lv_unit.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

                @Override
                public void onSwipeStart(int position) {
                    // swipe start
                }

                @Override
                public void onSwipeEnd(int position) {
                    // swipe end
                }
            });

            // set MenuStateChangeListener
            lv_unit.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
                @Override
                public void onMenuOpen(int position) {
                }

                @Override
                public void onMenuClose(int position) {
                }
            });

            // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());

            // test item long click
            lv_unit.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view,
                                               int position, long id) {
                    Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            lv_unit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   String value = ds_unit.get(position).toString();

                   Bundle bundle = new Bundle();
                   String idsend = ds_unit.get(position).getId();
                   //Toast.makeText(context, "A"+idsend, Toast.LENGTH_SHORT).show();
                   bundle.putString("send",idsend);
                   Intent intent = new Intent(LearningActivity.this, UnitActivity.class);
                   intent.putExtra("bundlesend",bundle);
                   startActivity(intent);
               }
           });
            super.onPostExecute(result);
        }
    }
    // sua san pham
    class updateunits extends AsyncTask<Void,Void,Integer> {

        String id, nameunit,imgunit,imgless1,less1,imgless2,less2,imgless3,less3;
        MyFunctions myfunctions;
        Units unit;

        public updateunits(Units unit) {
            this.unit = unit;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            // TODO Auto-generated method stub

            Integer thanhcong = new Integer(0);
            try {
                id = unit.getId();
                nameunit = unit.getUnit_name();
                imgunit = unit.getUnit_img();
                imgless1 = unit.getLesson1_img();
                less1 = unit.getLesson1();
                imgless2 = unit.getLesson2_img();
                less2 = unit.getLesson2();
                imgless3 = unit.getLesson3_img();
                less3 = unit.getLesson3();

                myfunctions = new MyFunctions(getApplicationContext());
                JSONObject jsonobject = myfunctions.updateUnit(id, nameunit, imgunit, imgless1,less1,imgless2,less2,imgless3,less3);
                thanhcong = jsonobject.getInt("thanhcong");

            } catch (Exception e) {

            }

            return thanhcong;
        }

        //tien xu ly
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pg_dialog = new ProgressDialog(LearningActivity.this);
            pg_dialog.setMessage("Loading...");
            pg_dialog.setIndeterminate(false);
            pg_dialog.setCancelable(false);//co the cancel bang phim back
            pg_dialog.show();
        }

        //hau xu ly
        @Override
        protected void onPostExecute(Integer thanhcong) {
            // TODO Auto-generated method stub
            super.onPostExecute(thanhcong);
            pg_dialog.dismiss();

            if (thanhcong == 1) {
                Intent i = new Intent(getApplicationContext(), LearningActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Can not create", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //DELETE SANPham
    class deleteunits extends AsyncTask<Void,Void,Integer> {

        String id;
        MyFunctions myfunctions;

        public deleteunits(String id) {
            this.id= id;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            // TODO Auto-generated method stub

            Integer thanhcong = new Integer(0);
            try {
                id = id;

                myfunctions = new MyFunctions(getApplicationContext());
                JSONObject jsonobject = myfunctions.deleteUnit(id);
                thanhcong = jsonobject.getInt("thanhcong");

            } catch (Exception e) {

            }

            return thanhcong;
        }

        //tien xu ly
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pg_dialog = new ProgressDialog(LearningActivity.this);
            pg_dialog.setMessage("Loading...");
            pg_dialog.setIndeterminate(false);
            pg_dialog.setCancelable(false);//co the cancel bang phim back
            pg_dialog.show();
        }

        //hau xu ly
        @Override
        protected void onPostExecute(Integer thanhcong) {
            // TODO Auto-generated method stub
            super.onPostExecute(thanhcong);
            pg_dialog.dismiss();

            if (thanhcong == 1) {
                Intent i = new Intent(getApplicationContext(), LearningActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Can delete", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
