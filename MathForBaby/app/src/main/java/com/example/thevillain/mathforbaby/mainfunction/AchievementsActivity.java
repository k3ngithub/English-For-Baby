package com.example.thevillain.mathforbaby.mainfunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thevillain.mathforbaby.MainActivity;
import com.example.thevillain.mathforbaby.R;

public class AchievementsActivity extends AppCompatActivity {
    LinearLayout l1,l2;
    Button btnClose;
    Animation uptodown,downtoup;
<<<<<<< HEAD
    int score;

=======
>>>>>>> badc3c9853e1b6882fb328b059a624ab878dc47a
    TextView tvScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        tvScore = (TextView) findViewById(R.id.tvScore);
        btnClose = (Button)findViewById(R.id.btnOut);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        tvScore = (TextView) findViewById(R.id.tvScore);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
<<<<<<< HEAD

=======
>>>>>>> badc3c9853e1b6882fb328b059a624ab878dc47a
        Intent i = getIntent();

        String score = i.getStringExtra("Score");

        tvScore.setText(score);
<<<<<<< HEAD


        //String name = i.getStringExtra("name");

        //tvScore.setText("");





=======
>>>>>>> badc3c9853e1b6882fb328b059a624ab878dc47a
    }
}
