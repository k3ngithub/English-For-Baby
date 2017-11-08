package com.example.thevillain.mathforbaby.GetStarted;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thevillain.mathforbaby.R;
import com.example.thevillain.mathforbaby.SupportClass.MyFunctions;

import org.json.JSONObject;

import java.util.Calendar;


public class RegisterActivity extends AppCompatActivity {
    TextView txtHs, txtMs, txtWarns,tvBack;
    int currentHour, currentMinute;
    EditText edtFullname, edtUsername, edtPassword;
    public static String normalUserAvt = "https://www.atomix.com.au/media/2015/06/atomix_user31.png";
    Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        init();
        setTime();
        warning();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new executeRegister(RegisterActivity.this).execute();
            }
        });
        tvBack=(TextView)findViewById(R.id.textView6);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,BeginActivity.class);
                startActivity(intent);
            }
        });
    }

    class executeRegister extends AsyncTask<Void,Void,String>
    {
        String fullname, username, password, highscore, account_type;
        MyFunctions myfunctions;
        Context c;

        public executeRegister(Context c) {
            this.c = c;
            fullname = edtFullname.getText().toString();
            username = edtUsername.getText().toString();
            password = edtPassword.getText().toString();
            highscore = "0";
            account_type = "normal";
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub

            String successful = null;
            try
            {
                myfunctions=new MyFunctions(getApplicationContext());
                JSONObject jsonobject=myfunctions.registerUser(normalUserAvt, fullname, username, password, highscore, account_type);
                successful = jsonobject.getString("successful");

            }catch(Exception e)
            {
            }
            return successful;
        }

        @Override
        protected void onPostExecute(String successful) {
            // TODO Auto-generated method stub
            super.onPostExecute(successful);
            if(Integer.parseInt(successful)==1)
            {
                Toast.makeText(c, "Registration completely!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(c, "Registration failed!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void warning() {
        if (6 > currentHour | currentHour > 22){
            txtWarns.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        txtHs = (TextView) findViewById(R.id.txtHs);
        txtMs = (TextView) findViewById(R.id.txtMs);
        txtWarns = (TextView) findViewById(R.id.txtWarnings);
        edtFullname = (EditText) findViewById(R.id.edtFNReg);
        edtUsername = (EditText) findViewById(R.id.edtUSReg);
        edtPassword = (EditText) findViewById(R.id.edtPWReg);
        btnReg = (Button) findViewById(R.id.btnReg);
    }

    private void setTime() {
        Calendar rightNow = Calendar.getInstance();
        currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        currentMinute = rightNow.get(Calendar.MINUTE);
        if(currentHour < 10){
            if(currentMinute < 10){
                txtHs.setText("0"+currentHour);
                txtMs.setText("0"+currentMinute);
            }
            else {
                txtHs.setText("0"+currentHour);
                txtMs.setText(""+currentMinute);
            }
        }

        else {
            txtHs.setText("" + currentHour);
            txtMs.setText("" + currentMinute);
        }

    }
}
