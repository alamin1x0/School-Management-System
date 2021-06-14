package com.example.shc_learing_app.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.shc_learing_app.FullImageView;
import com.example.shc_learing_app.MainActivity;
import com.example.shc_learing_app.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class ManiPhone extends AppCompatActivity {

    EditText mgetphonenumber;
    android.widget.Button msendotp;
    CountryCodePicker mcountryCodePicker;
    String countrycode;
    String phonenumber;


    FirebaseAuth firebaseAuth;
    ProgressBar mprogressBarOTPmain;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String codesent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mani_phone);
        getSupportActionBar().hide();


        mcountryCodePicker = findViewById(R.id.countrycodepicker);
        msendotp = findViewById(R.id.sendotpbutton);
        mgetphonenumber = findViewById(R.id.getphoneNumber);
        mprogressBarOTPmain = findViewById(R.id.progressBarOTPmain);

        firebaseAuth = FirebaseAuth.getInstance();

        countrycode = mcountryCodePicker.getSelectedCountryCodeWithPlus();

        mcountryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countrycode = mcountryCodePicker.getSelectedCountryCodeWithPlus();
            }
        });


        msendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number;
                number = mgetphonenumber.getText().toString();
                if (number.isEmpty()) {
                    Toasty.warning(getApplicationContext(), "আপনার নম্বর লিখুন", Toasty.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Please Enter Your Number", Toast.LENGTH_SHORT).show();
                } else if (number.length() < 11) {

                    Toasty.warning(getApplicationContext(), "আপনার সঠিক নম্বর লিখুন ", Toasty.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Please Enter correct number",Toast.LENGTH_LONG).show();
                } else {
                    mprogressBarOTPmain.setVisibility(View.VISIBLE);
                    phonenumber = countrycode + number;

                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber(phonenumber)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(ManiPhone.this)
                            .setCallbacks(mCallbacks)
                            .build();

                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                //automatioc call back funcation
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Toasty.success(getApplicationContext(), "ওটিপি পাঠানো হয়েছে", Toasty.LENGTH_SHORT).show(); //ওটিপি পাঠানো হয়েছে
                //Toast.makeText(getApplicationContext(), "OTP is Sent", Toast.LENGTH_SHORT).show();
                mprogressBarOTPmain.setVisibility(View.VISIBLE);
                codesent = s;

                Intent intent = new Intent(ManiPhone.this, OtpAutheration.class);
                intent.putExtra("ওটিপি", codesent); //ওটিপি
                startActivity(intent);
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(ManiPhone.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}