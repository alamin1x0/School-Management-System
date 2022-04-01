package com.example.shc_learing_app.phoneAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class UpdateProfile extends AppCompatActivity {


    private EditText mnewusername;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    private FirebaseFirestore firebaseFirestore;

    private ImageView mgetnewimageimageView;

    private StorageReference storageReference;

    private String ImageURIacessToken;

    private androidx.appcompat.widget.Toolbar mtoolbarofupdateprofile;
    private ImageButton mbackbuttonupdateprofile;

    private FirebaseStorage firebaseStorage;


    ProgressBar mprogressBarofupdateprofile;

    private Uri imagepath;

    Intent intent;

    private static int PICK_IMAGE=123;

    android.widget.Button mupdateprofilebutton;

    String newname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
//        getSupportActionBar().hide();

        mtoolbarofupdateprofile = findViewById(R.id.toolbarofupdateprofile);
        mbackbuttonupdateprofile = findViewById(R.id.backbuttonofupdateprofile);
        mgetnewimageimageView = findViewById(R.id.getnewuserimageimageView);
        mprogressBarofupdateprofile = findViewById(R.id.progressbarofupdateprofile);
        mnewusername = findViewById(R.id.getnewusername);
        mupdateprofilebutton = findViewById(R.id.updateprofilebutton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        intent = getIntent();

        //setSupportActionBar(mtoolbarofupdateprofile);


        mbackbuttonupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mnewusername.setText(intent.getStringExtra("nameofuser"));


        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        mupdateprofilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newname = mnewusername.getText().toString();

                if (newname.isEmpty())
                {
                    Toasty.warning(getApplicationContext(), "Name is Empty", Toasty.LENGTH_SHORT).show();
                }else if (imagepath!=null){

                    mprogressBarofupdateprofile.setVisibility(View.VISIBLE);
                    UserProfile muserProfile = new UserProfile(newname, firebaseAuth.getUid());
                    databaseReference.setValue(muserProfile);

                    updateimagetostorage();

                    Toasty.success(getApplicationContext(), "Updated", Toasty.LENGTH_SHORT).show();
                    mprogressBarofupdateprofile.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    mprogressBarofupdateprofile.setVisibility(View.VISIBLE);
                    UserProfile muserProfile = new UserProfile(newname, firebaseAuth.getUid());
                    databaseReference.setValue(muserProfile);
                    updatenameoncloudfirestore();
                    Toasty.success(getApplicationContext(),"Updated", Toasty.LENGTH_SHORT).show();

                    mprogressBarofupdateprofile.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


        mgetnewimageimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,PICK_IMAGE);
            }
        });

        storageReference = firebaseStorage.getReference();
        storageReference.child("Images").child(firebaseAuth.getUid()).child("Profile Photo").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                ImageURIacessToken = uri.toString();
                Picasso.get().load(uri).into(mgetnewimageimageView);
            }
        });

    }

    private void updatenameoncloudfirestore() {

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(firebaseAuth.getUid());
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("name", newname);
        userdata.put("image", ImageURIacessToken);
        userdata.put("uid",firebaseAuth.getUid());
        userdata.put("status","Online");


        documentReference.set(userdata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toasty.success(getApplicationContext(), "Profile Update Successfully", Toasty.LENGTH_SHORT).show();
            }
        });



    }

    private void updateimagetostorage() {


        StorageReference imageref = storageReference.child("Images").child(firebaseAuth.getUid()).child("Profile Photo");

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
        }catch (IOException e){
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
                        ImageURIacessToken = uri.toString();
                        Toasty.success(getApplicationContext(), "URI get Sucess", Toasty.LENGTH_SHORT).show();
                        updatenameoncloudfirestore();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toasty.error(getApplicationContext(), "URI get Failed", Toasty.LENGTH_SHORT).show();
                    }
                });

                Toasty.success(getApplicationContext(), "Image is Updated", Toasty.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(getApplicationContext(), "Image is Not Updated", Toasty.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==PICK_IMAGE && resultCode== RESULT_OK){
            imagepath = data.getData();
            mgetnewimageimageView.setImageURI(imagepath);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



}