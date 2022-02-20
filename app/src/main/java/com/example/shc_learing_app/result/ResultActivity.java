package com.example.shc_learing_app.result;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.shc_learing_app.R;

import es.dmoral.toasty.Toasty;

public class ResultActivity extends AppCompatActivity {

    private EditText inputRollId;
    private Button addResultBtn;

    private Spinner addAllClassName, addAllYears;
    private String categoryClass, categoryYear;

    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        inputRollId = findViewById(R.id.inputRollId);

        addResultBtn = findViewById(R.id.addResultBtn);

        addAllClassName = findViewById(R.id.addAllClassName);
        addAllYears = findViewById(R.id.addAllYears);

        alertDialog = new AlertDialog.Builder(this);


        //class spinner
        String[] items = new String[]{"ক্লাস নির্বাচন করুন", "ক্লাস ৬", "ক্লাস ৭", "ক্লাস ৮", "ক্লাস ৯"};
        addAllClassName.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));

        addAllClassName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                categoryClass = addAllClassName.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //class spinner
        String[] item = new String[]{"সাল", "২০২২", "২০২৪", "২০২৫", "২০২৬", "২০২৭"};
        addAllYears.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, item));

        addAllYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryYear = addAllYears.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addResultBtn.setOnClickListener(v -> {
            String roll = inputRollId.getText().toString();

            addAllClassName.getSelectedItem().toString();
            //addAllYears.getSelectedItem().toString();

//            if (categoryClass.equals("ক্লাস নির্বাচন করুন")) {
//                Toasty.warning(this, "দয়া করে আপনারা ক্লাস সিলেক্ট করুন", Toasty.LENGTH_SHORT).show();
//            } else if (categoryYear.equals("সাল")) {
//                Toasty.warning(this, "দয়া করে আপনারা পরীক্ষার সাল সিলেক্ট করুন", Toasty.LENGTH_SHORT).show();
//            } else if (roll.isEmpty()) {
//                Toasty.warning(this, "দয়া করে আপনারা পরীক্ষার রোল সিলেক্ট করুন", Toasty.LENGTH_SHORT).show();
//                inputRollId.setError("পরীক্ষার রোল");
//                inputRollId.requestFocus();
//                return;
//            } else {
//
//            }


            Query.query(categoryClass,categoryYear, roll, alertDialog);

        });


//
//        submit.setOnClickListener(v->{
//            String csl = name.getText().toString();
//            String phon = phone.getText().toString();
//
//            Query.query(csl,phon,alertDialog);
//
//        });

    }
}