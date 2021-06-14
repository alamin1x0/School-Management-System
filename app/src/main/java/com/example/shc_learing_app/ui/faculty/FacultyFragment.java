package com.example.shc_learing_app.ui.faculty;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class FacultyFragment extends Fragment {

    private RecyclerView headTeacher,csDepartment, mechanicalDepartment, physicsDepartment, chmesityDepartment;
    private LinearLayout headNoData,csNoData, mecNoData, phyNoData, chNoData;
    private List<TeacherData> list, list1, list2, list3,list4;
    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);


        headTeacher = view.findViewById(R.id.headTeacher);
        csDepartment = view.findViewById(R.id.csDepartment);
        mechanicalDepartment = view.findViewById(R.id.mechanicalDepartment);
        physicsDepartment = view.findViewById(R.id.physicsDepartment);
        chmesityDepartment = view.findViewById(R.id.chmesityDepartment);

        headNoData = view.findViewById(R.id.headNoData);
        csNoData = view.findViewById(R.id.csNoData);
        mecNoData = view.findViewById(R.id.mecNoData);
        phyNoData = view.findViewById(R.id.phyNoData);
        chNoData = view.findViewById(R.id.chNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        reference.keepSynced(true);

        headTeacher();
        csDepartment();
        mechanicalDepartment();
        physicsDepartment();
        chmesityDepartment();

        return view;
    }

    private void headTeacher() {

        dbRef = reference.child("Head Teacher");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    headNoData.setVisibility(View.VISIBLE);
                    headTeacher.setVisibility(View.GONE);
                }else {
                    headNoData.setVisibility(View.GONE);
                    headTeacher.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list.add(data);
                    }
                    headTeacher.setHasFixedSize(true);
                    headTeacher.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list, getContext());
                    headTeacher.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });
    }

    private void csDepartment() {

        dbRef = reference.child("Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else {
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1, getContext());
                    csDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });
    }


    private void mechanicalDepartment() {

        dbRef = reference.child("Humanities");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mecNoData.setVisibility(View.VISIBLE);
                    mechanicalDepartment.setVisibility(View.GONE);
                }else {


                    mecNoData.setVisibility(View.GONE);
                    mechanicalDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    mechanicalDepartment.setHasFixedSize(true);
                    mechanicalDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2, getContext());
                    mechanicalDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();

            }
        });
    }


    private void physicsDepartment() {

        dbRef = reference.child("Commerce");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    phyNoData.setVisibility(View.VISIBLE);
                    physicsDepartment.setVisibility(View.GONE);
                }else {


                    phyNoData.setVisibility(View.GONE);
                    physicsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    physicsDepartment.setHasFixedSize(true);
                    physicsDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3, getContext());
                    physicsDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });
    }



    private void chmesityDepartment() {

        dbRef = reference.child("ICT");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    chNoData.setVisibility(View.VISIBLE);
                    chmesityDepartment.setVisibility(View.GONE);
                }else {
                    chNoData.setVisibility(View.GONE);
                    chmesityDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    chmesityDepartment.setHasFixedSize(true);
                    chmesityDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4, getContext());
                    chmesityDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toasty.error(getContext(), databaseError.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });
    }
}