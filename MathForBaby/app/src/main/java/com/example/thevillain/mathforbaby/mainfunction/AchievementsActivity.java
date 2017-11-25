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
    int score;

    TextView tvScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        btnClose = (Button)findViewById(R.id.buttonClose);
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
                Intent intent = new Intent(AchievementsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();

        String score = i.getStringExtra("Score");

        tvScore.setText(score);


        //String name = i.getStringExtra("name");

        //tvScore.setText("");





    }
}
