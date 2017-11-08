package com.example.thevillain.mathforbaby.GetStarted;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.thevillain.mathforbaby.Objects.User;
import com.example.thevillain.mathforbaby.R;
import com.example.thevillain.mathforbaby.SupportClass.MyFunctions;
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
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;


public class BeginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    TextView txtSignip, txtH, txtM, txtWarning;
    EditText edtUser, edtPass;
    Button btnGo, btnSignout;
    LoginButton btnFb;
    SignInButton signInButton;
    CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 007;
    String id, avatar, fullname, username, password, highscore, account_type;
    int currentHour, currentMinute;
    private ProgressDialog mProgressDialog;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_begin);
        getSupportActionBar().hide();

        init();
        setTime();
        warning();
        actionOnclick();
        setLoginFB();
        setLoginGoogle();
    }

    private void actionOnclick() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new executeLogin(BeginActivity.this).execute();
            }
        });
        txtSignip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
//        btnSignout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signOut();
//                LoginManager.getInstance().logOut();
//            }
//        });
        btnFb.setReadPermissions(Arrays.asList("public_profile", "email"));
    }

    private void setLoginGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Toast.makeText(BeginActivity.this, "Log out!", Toast.LENGTH_SHORT).show();
                    }
                });
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
                    String id = object.getString("id");
                    avatar = "https://graph.facebook.com/" + id + "/picture?type=large";
                    fullname = object.getString("name");
                    username = object.getString("email");
                    password = "none";
                    highscore = "0";
                    account_type = "facebook";
                    User user = new User(avatar, fullname, username, password, highscore, account_type);
                    new executeSignUp(user).execute();
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

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Uri uri = acct.getPhotoUrl();
            String imgUrl;
            imgUrl = uri.toString();
            avatar = imgUrl;
            fullname = acct.getDisplayName().toString();
            username = acct.getEmail().toString();
            password = "none";
            highscore = "0";
            account_type = "google";
            User user = new User(avatar, fullname, username, password, highscore, account_type);
            new executeSignUp(user).execute();

        } else {
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    private void warning() {
        if (6 > currentHour | currentHour > 22) {
            txtWarning.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        callbackManager = CallbackManager.Factory.create();
        txtH = (TextView) findViewById(R.id.txtH);
        txtM = (TextView) findViewById(R.id.txtM);
        txtWarning = (TextView) findViewById(R.id.txtWarning);
        btnGo = (Button) findViewById(R.id.buttonGo);
        btnFb = (LoginButton) findViewById(R.id.btnFb);
        txtSignip = (TextView) findViewById(R.id.tvSignUp);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        btnSignout = (Button) findViewById(R.id.signout);
    }

    private void setTime() {
        Calendar rightNow = Calendar.getInstance();
        currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        currentMinute = rightNow.get(Calendar.MINUTE);
        if (currentHour < 10) {
            if (currentMinute < 10) {
                txtH.setText("0" + currentHour);
                txtM.setText("0" + currentMinute);
            } else {
                txtH.setText("0" + currentHour);
                txtM.setText("" + currentMinute);
            }
        } else {
            txtH.setText("" + currentHour);
            txtM.setText("" + currentMinute);
        }

    }

    class executeLogin extends AsyncTask<Void, Void, String> {
        Context c;
        MyFunctions myfunctions;
        String username;
        String password;

        public executeLogin(Context c) {
            this.c = c;
            username = edtUser.getText().toString();
            password = edtPass.getText().toString();
        }

        @Override
        protected String doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            String successful = null;

            try {
                myfunctions = new MyFunctions(getApplicationContext());
                JSONObject jsonobject = myfunctions.loginUser(username, password);
                successful = jsonobject.getString("successful");
            } catch (Exception e) {
            }
            return successful;
        }

        @Override
        protected void onPostExecute(String successful) {
            // TODO Auto-generated method stub
            super.onPostExecute(successful);
            if (Integer.parseInt(successful) == 1) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("KEYCODE","normal");
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(getApplicationContext(),
                        "Incorrect passwork!!!",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    class executeSignUp extends AsyncTask<Void, Void, String> {
        String avt, fname, uname, pword, hscore, acc_type;
        MyFunctions myfunctions;
        User user;

        public executeSignUp(User user) {
            this.user = user;
        }

            @Override
            protected String doInBackground (Void...params){
                // TODO Auto-generated method stub

                String successful = null;
                try {
                    avt = user.getAvatar();
                    fname = user.getFullname();
                    uname = user.getUsername();
                    pword = user.getPassword();
                    hscore = user.getHighscore();
                    acc_type = user.getAccount_type();

                    myfunctions = new MyFunctions(getApplicationContext());
                    JSONObject jsonobject = myfunctions.registerUser(avt, fname, uname, pword, hscore, acc_type);
                    successful = jsonobject.getString("successful");

                } catch (Exception e) {
                }
                return successful;
            }

            @Override
            protected void onPostExecute (String successful){
                // TODO Auto-generated method stub
                super.onPostExecute(successful);
                if (Integer.parseInt(successful) == 1) {
                    Intent intent = new Intent(BeginActivity.this, MainActivity.class);
                    intent.putExtra("KEYCODE", acc_type);
                    intent.putExtra("avtS", avt);
                    intent.putExtra("fnameS", fname);
                    intent.putExtra("unameS", fname);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(BeginActivity.this, MainActivity.class);
                    intent.putExtra("KEYCODE", acc_type);
                    intent.putExtra("avtS", avt);
                    intent.putExtra("fnameS", fname);
                    intent.putExtra("unameS", fname);
                    startActivity(intent);
                }
            }
        }

    class executeRegister extends AsyncTask<Void, Void, String> {
        String avt, fname, uname, pword, hscore, acc_type;
        MyFunctions myfunctions;
        User user;

        public executeRegister(User user) {
            this.user = user;
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub

            String successful = null;
            try {
                avt = user.getAvatar();
                fname = user.getFullname();
                uname = user.getUsername();
                pword = user.getPassword();
                hscore = user.getHighscore();
                acc_type = user.getAccount_type();

                myfunctions = new MyFunctions(getApplicationContext());
                JSONObject jsonobject = myfunctions.registerUser(avt, fname, uname, pword, hscore, acc_type);
                successful = jsonobject.getString("successful");

            } catch (Exception e) {
            }
            return successful;
        }

        @Override
        protected void onPostExecute(String successful) {
            // TODO Auto-generated method stub
            super.onPostExecute(successful);
            if (Integer.parseInt(successful) == 1) {
                Intent intent = new Intent(BeginActivity.this, MainActivity.class);
                intent.putExtra("avtS", avt);
                intent.putExtra("fnameS", fname);
                intent.putExtra("unameS", fname);
                //startActivity(intent);

            } else {
                Intent intent = new Intent(BeginActivity.this, MainActivity.class);
                intent.putExtra("avtS", avt);
                intent.putExtra("fnameS", fname);
                intent.putExtra("unameS", fname);
                //startActivity(intent);
            }
        }

}
}
