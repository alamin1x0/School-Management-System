package com.example.shc_learing_app.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shc_learing_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import es.dmoral.toasty.Toasty;

public class ContactForm extends AppCompatActivity {

    private EditText Name,  Contact; //Email,
    private Button addBtn;
    private final int REQ = 1;
    private ProgressDialog pd;

    private String name, email, contact ="";


    private StorageReference storageReference;
    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        getSupportActionBar().hide();

        Name = findViewById(R.id.Name);
        //Email = findViewById(R.id.Email);
        Contact = findViewById(R.id.Contact);
        addBtn = findViewById(R.id.addBtn);

        pd = new ProgressDialog(this);

        reference = FirebaseDatabase.getInstance().getReference().child("CotactForm");
        storageReference = FirebaseStorage.getInstance().getReference();


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chackContactForm();
            }
        });


    }

    private void chackContactForm() {
        name = Name.getText().toString();
        //email = Email.getText().toString();
        contact = Contact.getText().toString();

        if (name.isEmpty()) {
            Name.setError("Please Inter Your Name");
        }
//        }else if (email.isEmpty()){
//            Email.setError("Please Enter Your Email");
//        }
      else if (contact.isEmpty()){
            Contact.setError("Please Enter Your Message");
        }else {
            insertData();
        }


    }

    private void insertData() {
        dbRef = reference.child("CotactForm");
        final  String uniquekey = dbRef.push().getKey();

//        ContactData contactData = new ContactData(name,email,contact,uniquekey);

        ContactData contactData = new ContactData(name,contact,uniquekey);

        dbRef.child(uniquekey).setValue(contactData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toasty.success(ContactForm.this, "Successfuly", Toasty.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toasty.error(ContactForm.this, "Something Worng", Toasty.LENGTH_SHORT).show();
            }
        });
    }
}