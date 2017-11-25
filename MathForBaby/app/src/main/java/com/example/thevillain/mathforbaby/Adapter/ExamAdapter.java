package com.example.thevillain.mathforbaby.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.thevillain.mathforbaby.Objects.Exams;

import java.util.List;

/**
 * Created by sony on 11/05/2017.
 */

public class ExamAdapter extends ArrayAdapter<Exams> {

    public ExamAdapter(Context context, int resource, List<Exams> items) {
        super(context, resource, items);
    }

//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//////        View view = convertView;
//////        if (view == null) {
//////            LayoutInflater inflater = LayoutInflater.from(getContext());
//////            view =  inflater.inflate(R.layout.layoutmotdongkiemtra, null);
//////        }
//////        Exams e = getItem(position);
//////        if (e != null) {
//////            // Anh xa + Gan gia tri
//////            TextView txtid = (TextView) view.findViewById(R.id.textViewidkiemtra);
//////            txtid.setText(e.getId());
//////
//////            TextView txtquestion = (TextView) view.findViewById(R.id.textViewcauhoi);
//////            txtquestion.setText(e.getQuestions());
//////
//////            TextView txtanswer1 = (TextView) view.findViewById(R.id.textViewcautraloi1);
//////            txtanswer1.setText(e.getAnswer1());
//////
//////            TextView txtanswer2 = (TextView) view.findViewById(R.id.textViewcautraloi2);
//////            txtanswer2.setText(e.getAnswer2());
//////
//////            TextView txtResultans = (TextView) view.findViewById(R.id.textViewcautraloi3);
//////            txtResultans.setText(e.getResultans());
//////
////////            TextView txtDesc = (TextView) view.findViewById(R.id.productdescription);
////////            txtDesc.setText(p.description);
//////
//////            TextView txtScore = (TextView) view.findViewById(R.id.textViewhinhanhkt);
//////            txtScore.setText(e.getScore());
//////
//////            ImageView img = (ImageView) view.findViewById(R.id.imageViewkiemtra);
//////            Picasso.with(getContext()).load(e.getImage()).into(img);
//////
//////            RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.groupradidokiemtra);
//////
//////            RadioButton rb1 = (RadioButton) view.findViewById(R.id.radioButtoncautraloi1);
//////            rb1.setText(kt.getCautraloi1());
//////            RadioButton rb2 = (RadioButton) view.findViewById(R.id.radioButtoncautraloi2);
//////            rb2.setText(kt.getCautraloi2());
//////            RadioButton rb3 = (RadioButton) view.findViewById(R.id.radioButtoncautraloi3);
//////            rb3.setText(kt.getCautraloi3());
//////
//////
//////
//////        }
////        return view;
//    }
}
