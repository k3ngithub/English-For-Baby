package com.example.thevillain.mathforbaby.GetStarted;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thevillain.mathforbaby.MainActivity;
import com.example.thevillain.mathforbaby.R;
import com.example.thevillain.mathforbaby.SupportClass.MyFunctions;
import com.example.thevillain.mathforbaby.mainfunction.UnitActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;


public class BeginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    Button btnGo, btnGg;
    LoginButton btnFb;
    TextView txtSignip, txtH, txtM, txtWarning;
    int currentHour, currentMinute;
    EditText edtUser, edtPass;
    CallbackManager callbackManager;
    String avatar, fullname, email_username, password, highscore, acc_type;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_begin);
        getSupportActionBar().hide();

        callbackManager = CallbackManager.Factory.create();
        init();
        setTime();
        warning();
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new executeLogin(BeginActivity.this).execute();
                Intent intent = new Intent(BeginActivity.this,UnitActivity.class);
                startActivity(intent);
            }
        });
        txtSignip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnFb.setReadPermissions(Arrays.asList("public_profile", "email"));
        setLoginFB();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

    }

    private void setLoginFB() {
        btnFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    fullname = object.getString("name");
                    email_username = object.getString("email");
                    String id = object.getString("id");
                    avatar = "https://graph.facebook.com/"+id+"/picture?type=large";
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    class executeLogin extends AsyncTask<Void,Void,String>
    {
        Context c;
        MyFunctions myfunctions;
        String user;
        String password;

        public executeLogin(Context c) {
            this.c = c;
            user = edtUser.getText().toString();
            password = edtPass.getText().toString();
        }

        @Override
        protected String doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            String successful = null;

            try{
                myfunctions = new MyFunctions(getApplicationContext());
                JSONObject jsonobject=myfunctions.loginUser(user, password);

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "Incorrect passwork!!!",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void warning() {
        if (6 > currentHour | currentHour > 22){
            txtWarning.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        txtH = (TextView) findViewById(R.id.txtH);
        txtM = (TextView) findViewById(R.id.txtM);
        txtWarning = (TextView) findViewById(R.id.txtWarning);
        btnGo = (Button)findViewById(R.id.buttonGo);
        btnFb = (LoginButton) findViewById(R.id.btnFb);
        btnGg = (Button) findViewById(R.id.btnGg);
        txtSignip = (TextView) findViewById(R.id.tvSignUp);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
    }

    private void setTime() {
        Calendar rightNow = Calendar.getInstance();
        currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        currentMinute = rightNow.get(Calendar.MINUTE);
        if(currentHour < 10){
            if(currentMinute < 10){
                txtH.setText("0"+currentHour);
                txtM.setText("0"+currentMinute);
            }
            else {
                txtH.setText("0"+currentHour);
                txtM.setText(""+currentMinute);
            }
        }
        else {
            txtH.setText("" + currentHour);
            txtM.setText("" + currentMinute);
        }

    }
}
