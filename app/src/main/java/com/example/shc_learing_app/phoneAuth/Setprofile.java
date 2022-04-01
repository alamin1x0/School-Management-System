package com.example.shc_learing_app.phoneAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.shc_learing_app.MainActivity;
import com.example.shc_learing_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Setprofile extends AppCompatActivity {


    private CardView mgetuserimage;
    private ImageView mgetuserimageview;
    private static int PICK_IMAGE = 123;
    private Uri imagepath;

    private EditText mgetusername;

    private android.widget.Button msaveprofle;

    private FirebaseAuth firebaseAuth;
    private String name;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private String ImageUriAccessToken;

    private FirebaseFirestore firebaseFirestore;

    ProgressBar mprogressBarSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setprofile);

//        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();


        mgetusername = findViewById(R.id.getusername);
        mgetuserimage = findViewById(R.id.getuserimage);
        mgetuserimageview = findViewById(R.id.getuserimageimageView);
        msaveprofle = findViewById(R.id.saveProfile);
        mprogressBarSaveProfile = findViewById(R.id.progressBarSaveProfile);

        mgetuserimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        msaveprofle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mgetusername.getText().toString();
                if (name.isEmpty()) {
                    Toasty.warning(getApplicationContext(), "আপনার নাম লিখুন", Toasty.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Name is Empty", Toast.LENGTH_SHORT).show();
                } else if (imagepath == null) {
                    Toasty.warning(getApplicationContext(), "আপনার ছবি আপলোড করুন", Toasty.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Image is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    mprogressBarSaveProfile.setVisibility(View.VISIBLE);
                    sendDataForNewUser();
                    mprogressBarSaveProfile.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(Setprofile.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void sendDataForNewUser() {
        sendDataToRealTimeDatabase();

    }

    private void sendDataToRealTimeDatabase() {


        name = mgetusername.getText().toString().trim();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        UserProfile muserProfile = new UserProfile(name, firebaseAuth.getUid());
        databaseReference.setValue(muserProfile);
        Toasty.success(getApplicationContext(), "ব্যবহারকারীর প্রোফাইল সাফল্যের সাথে যুক্ত হয়েছে", Toasty.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "User Profile Added Successfully", Toast.LENGTH_SHORT).show();
        sendImagetoStorage();


    }

    private void sendImagetoStorage() {
        StorageReference imageref = storageReference.child("Images").child(firebaseAuth.getUid()).child("Profile Photo");

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();

        //puting image to storage

        UploadTask uploadTask = imageref.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ImageUriAccessToken = uri.toString();
                        Toasty.success(getApplicationContext(), "ইউআরআই সাফল্য পেতে", Toasty.LENGTH_SHORT).show();
                        sendDataToCloudFirestore();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toasty.warning(getApplicationContext(), "ইউআরআই ব্যর্থ হয়", Toasty.LENGTH_SHORT).show();
                    }
                });

                Toasty.success(getApplicationContext(), "ছবি আপলোড করা হয়", Toasty.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(getApplicationContext(), "ছবি আপলোড করা হয়নি ", Toasty.LENGTH_SHORT).show();

            }
        });

    }

    private void sendDataToCloudFirestore() {

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(firebaseAuth.getUid());
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("name", name);
        userdata.put("image", ImageUriAccessToken);
        userdata.put("uid", firebaseAuth.getUid());
        userdata.put("status", "Online");

        documentReference.set(userdata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toasty.success(getApplicationContext(), "ক্লাউড ফায়ারস্টোরের ডেটা সাফল্য প্রেরণ করুন", Toasty.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imagepath = data.getData();
            mgetuserimageview.setImageURI(imagepath);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}