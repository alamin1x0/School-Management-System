package com.example.shc_learing_app.ui.student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shc_learing_app.R;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shc_learing_app.R;
import com.example.shc_learing_app.ui.faculty.TeacherAdapter;
import com.example.shc_learing_app.ui.faculty.TeacherData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class StudentFragment extends Fragment {

    private RecyclerView class6, class7, class8, class9, class10;
    private LinearLayout class6NoData, class7NoData, class8NoData, class9NoData, class10NoData;
    private List<StudentData> list, list1, list2, list3, list4;
    private StudentAdapter adapter;

    private DatabaseReference reference, dbRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student, container, false);


        class6 = view.findViewById(R.id.class6);
        class7 = view.findViewById(R.id.class7);
        class8 = view.findViewById(R.id.class8);
        class9 = view.findViewById(R.id.class9);
        class10 = view.findViewById(R.id.class10);

        class6NoData = view.findViewById(R.id.class6NoData);
        class7NoData = view.findViewById(R.id.class7NoData);
        class8NoData = view.findViewById(R.id.class8NoData);
        class9NoData = view.findViewById(R.id.class9NoData);
        class10NoData = view.findViewById(R.id.class10NoData);
        reference = FirebaseDatabase.getInstance().getReference().child("student");

//        reference.keepSynced(true);

        class6();
        class7();
        class8();
        class9();
        class10();

        return view;
    }




    private void class6() {
        dbRef = reference.child("Class 6");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    class6NoData.setVisibility(View.VISIBLE);
                    class6.setVisibility(View.GONE);
                }else {


                    class6NoData.setVisibility(View.GONE);
                    class6.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StudentData data = snapshot.getValue(StudentData.class);
                        list.add(data);
                    }
                    class6.setHasFixedSize(true);
                    class6.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentAdapter(list, getContext());
                    class6.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    private void class7() {
        dbRef = reference.child("Class 7");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    class7NoData.setVisibility(View.VISIBLE);
                    class7.setVisibility(View.GONE);
                }else {


                    class7NoData.setVisibility(View.GONE);
                    class7.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StudentData data = snapshot.getValue(StudentData.class);
                        list1.add(data);
                    }
                    class7.setHasFixedSize(true);
                    class7.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentAdapter(list1, getContext());
                    class7.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    private void class8() {
        dbRef = reference.child("Class 8");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    class8NoData.setVisibility(View.VISIBLE);
                    class8.setVisibility(View.GONE);
                }else {


                    class8NoData.setVisibility(View.GONE);
                    class8.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StudentData data = snapshot.getValue(StudentData.class);
                        list2.add(data);
                    }
                    class8.setHasFixedSize(true);
                    class8.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentAdapter(list2, getContext());
                    class8.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    private void class9() {
        dbRef = reference.child("Class 9");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    class9NoData.setVisibility(View.VISIBLE);
                    class9.setVisibility(View.GONE);
                }else {
                    class9NoData.setVisibility(View.GONE);
                    class9.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StudentData data = snapshot.getValue(StudentData.class);
                        list3.add(data);
                    }
                    class9.setHasFixedSize(true);
                    class9.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentAdapter(list3, getContext());
                    class9.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }

    private void class10() {
        dbRef = reference.child("Class 10");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    class10NoData.setVisibility(View.VISIBLE);
                    class10.setVisibility(View.GONE);
                }else {
                    class10NoData.setVisibility(View.GONE);
                    class10.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StudentData data = snapshot.getValue(StudentData.class);
                        list4.add(data);
                    }
                    class10.setHasFixedSize(true);
                    class10.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentAdapter(list4, getContext());
                    class10.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });

    }


}