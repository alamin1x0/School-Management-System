package com.example.shc_learing_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shc_learing_app.admission.AdmissionActivity;
import com.example.shc_learing_app.contact.ContactForm;
import com.example.shc_learing_app.developer.developer;
import com.example.shc_learing_app.ebook.EbookActivity;
import com.example.shc_learing_app.otp.ProfileActivity;
import com.example.shc_learing_app.quizresult.QuizresultActivity;
import com.example.shc_learing_app.result.ResultActivity;
import com.example.shc_learing_app.studentlist.StudentList;
import com.example.shc_learing_app.video.VideoLucture;
import com.example.shc_learing_app.website.WebSiteActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;

    private final String CHECKEDITEM = "checked_item";

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");


        FirebaseMessaging.getInstance().subscribeToTopic("notification");


        auth = FirebaseAuth.getInstance();

        if (!isConnected()) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet Connection Please")
                    .setMessage("Please Check your Internet Connection")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }

//        else {
//            Toast.makeText(MainActivity.this,"", Toast.LENGTH_LONG).show();
//        }


        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        switch (getCheckedItem()) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);


        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.profile:
//                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.setting:
//                Toasty.success(getApplicationContext(), "Setting is Clicked", Toasty.LENGTH_SHORT).show();
//        }
//
//        return true;
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
//        if (item.getItemId() == R.id.logout){
//            auth.signOut();
//            openLogin();
//        }
        return true;
    }

//    private void openLogin() {
//        startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        finish();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (auth.getCurrentUser() == null){
//            openLogin();
//        }
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){


            case R.id.navigation_profile:
                intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_quiz:
                intent = new Intent(MainActivity.this, QuizresultActivity.class);
                startActivity(intent);
                break;


            case R.id.navigation_video:
                intent = new Intent(MainActivity.this, VideoLucture.class);
                startActivity(intent);
                break;

            case R.id.navigation_ebook:
                startActivity(new Intent(this, EbookActivity.class));
                break;

            case R.id.navigation_studentlist:
                intent = new Intent(MainActivity.this,StudentList.class);
                startActivity(intent);
                break;

            case R.id.navigation_website:
                intent = new Intent(MainActivity.this, WebSiteActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_result:
                intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
                break;


            case R.id.navigation_admission:
                intent = new Intent(MainActivity.this, AdmissionActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_contact:
                intent = new Intent(MainActivity.this, ContactForm.class);
                startActivity(intent);
                break;

            case R.id.navigation_developer:
                intent = new Intent(MainActivity.this, developer.class);
                startActivity(intent);
                break;







//            case R.id.logout:
//                editor.putString("isLogin", "false");
//                editor.commit();
//                openLogin();
//                break;





//            case R.id.navigation_theme:
//                showDialog();
//                break;






            case R.id.navigation_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "SHS_LEARING_APP");
                    i.putExtra(Intent.EXTRA_TEXT,"https://firebasestorage.googleapis.com/v0/b/admin-dash-42c58.appspot.com/o/App%2FSHS_Testing.apk?alt=media&token=75da9ae6-dd5d-435f-9f4c-68ebb8300327"+getApplicationContext().getPackageName());
                    startActivity(Intent.createChooser(i,"Share With"));
                }catch (Exception e){
                    Toast.makeText(this, "Unable to share this app.", Toast.LENGTH_SHORT).show();
                }

                break;


//            case R.id.navigation_rate:
//                Uri uri = Uri.parse("https://drive.google.com/file/d/1IH8mFbndgmL45p4agVJvCMjFWwKNXw7N/view?usp=sharing"+getApplicationContext().getPackageName());
//                Intent i  = new Intent(Intent.ACTION_VIEW,uri);
//                try {
//                    startActivity(i);
//                }catch (Exception e){
//                    Toasty.success(this,"Unable to open\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//                break;
        }
        return true;
    }

    private void showDialog() {

        String[] themes = this.getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                selected = themes[i];
                checkedItem = i;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (selected == null){
                    selected = themes[i];
                    checkedItem = i;
                }
                switch (selected){
                    case "System Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//                       AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                setCheckedItem(checkedItem);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }

    private void setCheckedItem(int i){
        editor.putInt(CHECKEDITEM, i);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }



    private boolean isConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}