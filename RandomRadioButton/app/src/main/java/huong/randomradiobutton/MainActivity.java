package huong.randomradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton radio, radio1,radio2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radio = (RadioButton) findViewById(R.id.radioButton);
        radio1 = (RadioButton) findViewById(R.id.radioButton2);
        radio2 = (RadioButton) findViewById(R.id.radioButton3);

        int Min = 1;
        int Max = 3;
        int opmin = 1;
        int opmax = 3;

        int answer, choice1, choice2;

        int randomoption  = opmin + (int)(Math.random() * ((opmax - opmin) + 1));
        int randomoption1  = opmin + (int)(Math.random() * ((opmax - opmin) + 1));
        int randomoption2 = opmin + (int)(Math.random() * ((opmax - opmin) + 1));






        int[] options = new int[4];
        int choice = 1;
        //choice = numberText + numberText2;
        answer = choice;
        options[0] = answer;

        options[3] = Min + (int)(Math.random() * ((Max - Min) + 1));
        radio.setText(String.valueOf(options[0]));
        choice1 = Min + (int)(Math.random() * ((Max - Min) + 1));

        radio1.setText(String.valueOf(options[1]));
        choice2 = Min + (int)(Math.random() * ((Max - Min) + 1));

        options[2] = choice2;
        radio2.setText(String.valueOf(options[2]));
    }
}
