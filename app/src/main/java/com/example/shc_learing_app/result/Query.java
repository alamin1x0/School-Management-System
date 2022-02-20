package com.example.shc_learing_app.result;

import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.shc_learing_app.admission.AdmissionData;
import com.example.shc_learing_app.studentlist.StudentListData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Query {

    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference reference = db.getReference("Admission");


    public static void query(String categoryClass, String categoryYear, String roll, AlertDialog.Builder builder) {
        reference.child(categoryClass).child(categoryYear)
                .orderByChild("roll")
                .startAt(roll)
                .endAt(roll + "\uf8ff")

                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            AdmissionData admissionData = snap.getValue(AdmissionData.class);
                            builder.setMessage(admissionData.getAddress().toString());
                            builder.create();
                            builder.show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}


//    public static void query(String cls, String name, AlertDialog.Builder builder) {
//        reference.child(cls)
//                .orderByChild("name")
//                .startAt(name)
//                .endAt(name + "\uf8ff")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot std : snapshot.getChildren()) {
//                            StudentListData st = std.getValue(StudentListData.class);
//                            builder.setMessage(st.getPhone().toString());
//                            builder.setMessage(st.getAddress().toString());
//                            builder.create();
//                            builder.show();
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }
//}
