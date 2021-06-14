package com.example.shc_learing_app.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.shc_learing_app.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.Locale;


public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_home, container, false);

       sliderLayout = view.findViewById(R.id.slider);
       sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
       sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
       sliderLayout.setScrollTimeInSec(1);

       setSliderViews();

       map = view.findViewById(R.id.mapp);
       map.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("geo:0,0?q=Shyampur High School Joypurhat"));
               Intent chooser = Intent.createChooser(intent,"Lauch Maps");
               startActivity(chooser);
           }
       });

       return view;
    }

    private void setSliderViews() {

        for (int i = 0; i< 5; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-dash-42c58.appspot.com/o/Slid-Show%2Fupdate-school.jpg?alt=media&token=83214c12-a4a3-4061-8254-684739f7fc1f");
                    break;

                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-dash-42c58.appspot.com/o/Slid-Show%2FUpdate%2F4.jpg?alt=media&token=70d9390d-46ef-46bb-8ed1-27f5a3013fca");
                    break;

                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-dash-42c58.appspot.com/o/Slid-Show%2FUpdate%2F2.jpg?alt=media&token=d61ee8f9-b543-4f9e-9d0c-0f808947baad");
                    break;

                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-dash-42c58.appspot.com/o/Slid-Show%2FUpdate%2F3.jpg?alt=media&token=9820dfe2-9ab6-48c0-8e4b-2031e843699b");
                    break;

                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/admin-dash-42c58.appspot.com/o/gallery%2F20210513_181329.jpg?alt=media&token=8f065dae-ffb8-4f80-9cf3-d1fbf4de02b3");
                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);

        }
    }
}