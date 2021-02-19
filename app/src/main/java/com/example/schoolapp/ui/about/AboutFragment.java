package com.example.schoolapp.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.schoolapp.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BanchAdapter adapter;
    private List<BranchModel> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_computer, "ICT","ICT started in year 2017. At present  100 students."));
        list.add(new BranchModel(R.drawable.ic_settings, "Scicence","Science started in year 2017. At present  100 students "));

        adapter = new BanchAdapter(getContext(),list);

        viewPager = view.findViewById(R.id.viewPage);

        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.collage_image);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/admin-school-3f4a0.appspot.com/o/slid-show%2Fshcbanner.png?alt=media&token=97dc1c09-aeed-4888-b1fd-02460164447d")
                .into(imageView);

        return  view;
    }
}