package com.example.shc_learing_app.result;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shc_learing_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        String[] items = new String[]{"ক্লাস নির্বাচন করুন", "ক্লাস ৬", "ক্লাস ৭", "ক্লাস ৮", "ক্লাস ৯", "ক্লাস ১০"};
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


        addResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validator()) {
                    query();
                }
            }
        });

    }


    void query() {

        String cls = addAllClassName.getSelectedItem().toString();

        FirebaseDatabase.getInstance().getReference("result").child(cls).child(addAllYears.getSelectedItem().toString())
                .orderByChild("roll")
                .equalTo(inputRollId.getText().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {

                            ResultModel resultModel = child.getValue(ResultModel.class);

                            Toast.makeText(ResultActivity.this, resultModel.getGpa(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    boolean validator() {
        return true;
    }
}