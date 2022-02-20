package com.example.shc_learing_app.studentlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.shc_learing_app.R;
import com.example.shc_learing_app.ebook.EbookData;
import com.example.shc_learing_app.ui.student.StudentAdapter;
import com.example.shc_learing_app.ui.student.StudentData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class StudentList extends AppCompatActivity {

    private RecyclerView class6, class7, class8, class9, class10;
    private LinearLayout class6NoData, class7NoData, class8NoData, class9NoData, class10NoData;
    private List<StudentListData> list, list1, list2, list3, list4;
    private StudentListAdapter adapter;
    //private EditText searchText;

    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Students List");
        class6 = findViewById(R.id.class6);
        class7 = findViewById(R.id.class7);
        class8 = findViewById(R.id.class8);
        class9 = findViewById(R.id.class9);
        class10 = findViewById(R.id.class10);

        //searchText = findViewById(R.id.searchText);

        class6NoData = findViewById(R.id.class6NoData);
        class7NoData = findViewById(R.id.class7NoData);
        class8NoData = findViewById(R.id.class8NoData);
        class9NoData = findViewById(R.id.class9NoData);
        class10NoData = findViewById(R.id.class10NoData);
        reference = FirebaseDatabase.getInstance().getReference().child("student");

        class6();
        class7();
        class8();
        class9();
        class10();

    }


    private void class6() {
        dbRef = reference.child("Class 6");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    class6NoData.setVisibility(View.VISIBLE);
                    class6.setVisibility(View.GONE);
                } else {


                    class6NoData.setVisibility(View.GONE);
                    class6.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StudentListData data = snapshot.getValue(StudentListData.class);
                        list.add(data);
                    }
                    class6.setHasFixedSize(true);
                    class6.setLayoutManager(new LinearLayoutManager(getApplication()));
                    adapter = new StudentListAdapter(list, getApplication());
                    class6.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getApplicationContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

//        searchText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                filter(editable.toString());
//            }
//        });

    }

//    private void filter(String text) {
//        ArrayList<StudentListData> filterlist = new ArrayList<>();
//        for (StudentListData item : list){
//            if (item.getName().toLowerCase().contains(text.toString())){
//                filterlist.add(item);
//            }
//        }
//
//        adapter.Filteredlist(filterlist);
//    }

    private void class7() {
        dbRef = reference.child("Class 7");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    class7NoData.setVisibility(View.VISIBLE);
                    class7.setVisibility(View.GONE);
                } else {


                    class7NoData.setVisibility(View.GONE);
                    class7.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StudentListData data = snapshot.getValue(StudentListData.class);
                        list1.add(data);
                    }
                    class7.setHasFixedSize(true);
                    class7.setLayoutManager(new LinearLayoutManager(getApplication()));
                    adapter = new StudentListAdapter(list1, getApplication());
                    class7.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getApplicationContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    private void class8() {
        dbRef = reference.child("Class 8");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    class8NoData.setVisibility(View.VISIBLE);
                    class8.setVisibility(View.GONE);
                } else {


                    class8NoData.setVisibility(View.GONE);
                    class8.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StudentListData data = snapshot.getValue(StudentListData.class);
                        list2.add(data);
                    }
                    class8.setHasFixedSize(true);
                    class8.setLayoutManager(new LinearLayoutManager(getApplication()));
                    adapter = new StudentListAdapter(list2, getApplication());
                    class8.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getApplicationContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    private void class9() {
        dbRef = reference.child("Class 9");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    class9NoData.setVisibility(View.VISIBLE);
                    class9.setVisibility(View.GONE);
                } else {
                    class9NoData.setVisibility(View.GONE);
                    class9.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StudentListData data = snapshot.getValue(StudentListData.class);
                        list3.add(data);
                    }
                    class9.setHasFixedSize(true);
                    class9.setLayoutManager(new LinearLayoutManager(getApplication()));
                    adapter = new StudentListAdapter(list3, getApplication());
                    class9.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getApplicationContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    private void class10() {
        dbRef = reference.child("Class 10");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    class10NoData.setVisibility(View.VISIBLE);
                    class10.setVisibility(View.GONE);
                } else {
                    class10NoData.setVisibility(View.GONE);
                    class10.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StudentListData data = snapshot.getValue(StudentListData.class);
                        list4.add(data);
                    }
                    class10.setHasFixedSize(true);
                    class10.setLayoutManager(new LinearLayoutManager(getApplication()));
                    adapter = new StudentListAdapter(list4, getApplication());
                    class10.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getApplicationContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

}