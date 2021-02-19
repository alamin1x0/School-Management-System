package com.example.schoolapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.schoolapp.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;


public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_home, container, false);

       sliderLayout = view.findViewById(R.id.slider);
       sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
       sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
       sliderLayout.setScrollTimeInSec(1);

       setSliderViews();

       map = view.findViewById(R.id.map);
       map.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openMap();
           }
       });
       return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Shyampur High School Joypurhat");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderViews() {

        for (int i = 0; i< 5; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-school-3f4a0.appspot.com/o/slid-show%2Fshcbanner.png?alt=media&token=97dc1c09-aeed-4888-b1fd-02460164447d");
                    break;

                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-school-3f4a0.appspot.com/o/slid-show%2FFILE00016.jpg?alt=media&token=1fee6012-8ff5-48ce-8e29-12582040241b");
                    break;

                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-school-3f4a0.appspot.com/o/slid-show%2FFILE00010.jpg?alt=media&token=5ff537ec-b623-4b7c-b5b5-628d1f58d4a7");
                    break;

                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-school-3f4a0.appspot.com/o/slid-show%2FFILE00018.jpg?alt=media&token=881a6d4c-ba0f-4552-99b2-60a92b5eac8f");
                    break;

                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-school-3f4a0.appspot.com/o/slid-show%2FFILE02096.jpg?alt=media&token=7d20cd26-0f23-4579-8d7c-8c4afb9d12ab");
                    break;


            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

            sliderLayout.addSliderView(sliderView);
        }
    }
}