package com.example.shc_learing_app.admission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.shc_learing_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class AdmissionActivity extends AppCompatActivity {


    private EditText studentnameBangla, studentnameEnglish, studentbirhtNumber,
            studentdateofBirht,studentGender, fatheNameBangla, fatheNameEnglish,fatheNID,
            fatheDateofBirth,fatherPhone,motherNameBangla,motherNameEnglish,motherNID,motherDateofBirth,
            Division,District,Upazila,PostCode,AddressVillage,Oldschoolname,
            OldExamname,PassYear,BoardName,Roll,ResultGPA,Parstentes;


    private String bangla, english, numberdate, dateofbirth,
            gendar, fahtername, fatherenglish, fathernid,fatherdateofbirht,fahterphone,
            mothername, motherenglish,mothernid,motherdateofbirth,
            divisin, district, upzila, postcode,address,
            oldschool, oldexam, examyear, board, roll, result, parsents, downloadUrl = "";



//    private String bname, ename, snumber, downloadUrl = "";


    private ImageView addStudentImage;
    private Spinner addAdmissionCategory;
    private Button addAdmissionBtn;
    private final int REQ = 1;
    private Bitmap bitmap = null;
    private String category;

    private ProgressDialog pd;
    private StorageReference storageReference;
    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ভর্তি ফরম");

        studentnameBangla = findViewById(R.id.studentnameBangla);
        studentnameEnglish = findViewById(R.id.studentnameEnglish);
        studentbirhtNumber = findViewById(R.id.studentbirhtNumber);
        studentdateofBirht = findViewById(R.id.studentdateofBirht);
        studentGender = findViewById(R.id.studentGender);

        fatheNameBangla = findViewById(R.id.fatheNameBangla);
        fatheNameEnglish = findViewById(R.id.fatheNameEnglish);
        fatheNID = findViewById(R.id.fatheNID);
        fatheDateofBirth = findViewById(R.id.fatheDateofBirth);
        fatherPhone = findViewById(R.id.fatherPhone);

        motherNameBangla = findViewById(R.id.motherNameBangla);
        motherNameEnglish = findViewById(R.id.motherNameEnglish);
        motherNID = findViewById(R.id.motherNID);
        motherDateofBirth = findViewById(R.id.motherDateofBirth);


        Division = findViewById(R.id.Division);
        District = findViewById(R.id.District);
        Upazila = findViewById(R.id.Upazila);


        PostCode = findViewById(R.id.PostCode);
        AddressVillage = findViewById(R.id.AddressVillage);

        Oldschoolname = findViewById(R.id.Oldschoolname);
        OldExamname = findViewById(R.id.OldExamname);
        PassYear = findViewById(R.id.PassYear);
        BoardName = findViewById(R.id.BoardName);
        Roll = findViewById(R.id.Roll);
        ResultGPA = findViewById(R.id.ResultGPA);
        Parstentes = findViewById(R.id.Parstentes);





        addAdmissionCategory = findViewById(R.id.addAdmissionCategory);
        addStudentImage = findViewById(R.id.addStudentImage);
        addAdmissionBtn = findViewById(R.id.addAdmissionBtn);
        pd = new ProgressDialog(this);



        reference = FirebaseDatabase.getInstance().getReference().child("Admission");
        storageReference = FirebaseStorage.getInstance().getReference();



        String[] items = new String[]{"ক্লাস নির্বাচন করুন","ক্লাস ৬","ক্লাস ৭", "ক্লাস ৮", "ক্লাস ৯"};
        addAdmissionCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items));

        addAdmissionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = addAdmissionCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addStudentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        addAdmissionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });

    }

    private void checkValidation() {

        bangla = studentnameBangla.getText().toString();
        english = studentnameEnglish.getText().toString();
        numberdate = studentbirhtNumber.getText().toString();
        dateofbirth = studentdateofBirht.getText().toString();
        gendar = studentGender.getText().toString();

        fahtername = fatheNameBangla.getText().toString();
        fatherenglish = fatheNameEnglish.getText().toString();
        fathernid = fatheNID.getText().toString();
        fatherdateofbirht = fatheDateofBirth.getText().toString();
        fahterphone = fatherPhone.getText().toString();

        mothername = motherNameBangla.getText().toString();
        motherenglish = motherNameEnglish.getText().toString();
        mothernid = motherNID.getText().toString();
        motherdateofbirth = motherDateofBirth.getText().toString();

        divisin = Division.getText().toString();
        district = District.getText().toString();
        upzila = Upazila.getText().toString();

        postcode = PostCode.getText().toString();
        address = AddressVillage.getText().toString();

        oldschool = Oldschoolname.getText().toString();
        oldexam = OldExamname.getText().toString();
        examyear = PassYear.getText().toString();
        board = BoardName.getText().toString();
        roll = Roll.getText().toString();
        result = ResultGPA.getText().toString();
        parsents = Parstentes.getText().toString();

        if (bangla.isEmpty()){
            studentnameBangla.setError("Empty");
            studentnameBangla.requestFocus();
        }else if(english.isEmpty()){
            studentnameEnglish.setError("Empty");
            studentnameEnglish.requestFocus();
        }else if (numberdate.isEmpty()){
            studentbirhtNumber.setError("Empty");
            studentbirhtNumber.requestFocus();
        }else if (dateofbirth.isEmpty()){
            studentdateofBirht.setError("Empty");
            studentdateofBirht.requestFocus();
        }else if (gendar.isEmpty()){
            studentGender.setError("Empty");
            studentGender.requestFocus();
        }else if (fahtername.isEmpty()){
            fatheNameBangla.setError("Empty");
            fatheNameBangla.requestFocus();
        }else if (fatherenglish.isEmpty()){
            fatheNameEnglish.setError("Empty");
            fatheNameEnglish.requestFocus();
        }else if (fathernid.isEmpty()){
            fatheNID.setError("Empty");
            fatheNID.requestFocus();
        }else if (fatherdateofbirht.isEmpty()){
            fatheDateofBirth.setError("Empty");
            fatheDateofBirth.requestFocus();
        }else if (fahterphone.isEmpty()){
        fatherPhone.setError("Empty");
        fatherPhone.requestFocus();
        }else if (mothername.isEmpty()){
            motherNameBangla.setError("Empty");
            motherNameBangla.requestFocus();
        }else if (motherenglish.isEmpty()){
            motherNameEnglish.setError("Empty");
            motherNameEnglish.requestFocus();
        }else if (mothernid.isEmpty()){
            motherNID.setError("Empty");
            motherNID.requestFocus();
        }else if (motherdateofbirth.isEmpty()){
            motherDateofBirth.setError("Empty");
            motherDateofBirth.requestFocus();
        }else if (divisin.isEmpty()){
            Division.setError("Empty");
            Division.requestFocus();
        }else if (district.isEmpty()){
            District.setError("Empty");
            District.requestFocus();
        }else if (upzila.isEmpty()){
            Upazila.setError("Empty");
            Upazila.requestFocus();
        }else if (postcode.isEmpty()){
            PostCode.setError("Empty");
            PostCode.requestFocus();
        }else if (address.isEmpty()){
            AddressVillage.setError("Empty");
            AddressVillage.requestFocus();
        }else if (oldschool.isEmpty()){
            Oldschoolname.setError("Empty");
            Oldschoolname.requestFocus();
        }else if (oldexam.isEmpty()){
            OldExamname.setError("Empty");
            OldExamname.requestFocus();
        }else if (examyear.isEmpty()){
            PassYear.setError("Empty");
            PassYear.requestFocus();
        }else if (board.isEmpty()){
            BoardName.setError("Empty");
            BoardName.requestFocus();
        }else if (roll.isEmpty()){
            Roll.setError("Empty");
            Roll.requestFocus();
        }else if (result.isEmpty()){
            ResultGPA.setError("Empty");
            ResultGPA.requestFocus();
        }else if (parsents.isEmpty()){
            Parstentes.setError("Empty");
            Parstentes.requestFocus();
        }



        else if (category.equals("ক্লাস নির্বাচন করুন")){
            Toasty.warning(this, "দয়া করে আপনারা ক্লাস সিলেক্ট করুন", Toasty.LENGTH_SHORT).show();
        }else if (bitmap == null){
            insertData();
        }else {
            uploadImage();
        }
    }

    private void uploadImage() {
        pd.setMessage("Uploading......");
        pd.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("Admission").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(AdmissionActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    insertData();

                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toasty.error(AdmissionActivity.this, "Something went wrong", Toasty.LENGTH_LONG).show();
                }

            }
        });


    }


    private void insertData() {

        dbRef = reference.child(category);
        final String uniqueKey = dbRef.push().getKey();


        AdmissionData admissionData = new AdmissionData(bangla, english, numberdate, dateofbirth,
                gendar, fahtername, fatherenglish, fathernid,fatherdateofbirht,fahterphone,
                mothername, motherenglish,mothernid,motherdateofbirth,
                divisin, district, upzila, postcode,address,
                oldschool, oldexam, examyear, board, roll, result, parsents, downloadUrl, uniqueKey);

        dbRef.child(uniqueKey).setValue(admissionData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toasty.success(AdmissionActivity.this, "ভর্তি ফরমটি জমা  হয়েছে", Toasty.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toasty.error(AdmissionActivity.this, "Something went wrong", Toasty.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStudentImage.setImageBitmap(bitmap);
        }
    }
}