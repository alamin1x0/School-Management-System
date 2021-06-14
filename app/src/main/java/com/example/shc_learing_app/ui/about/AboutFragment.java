package com.example.shc_learing_app.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.shc_learing_app.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BanchAdapter adapter;
    private List<BranchModel> list;

    private ImageView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_computer, " আইসিটি","আইসিটি  ২০১৭ সালে শুরু হয়েছিল। বর্তমানে 100 জন শিক্ষার্থী."));
        list.add(new BranchModel(R.drawable.ic_settings, "বিজ্ঞান"," ১৯৭০ সালে বিজ্ঞান শুরু হয়েছিল। বর্তমানে 100 জন শিক্ষার্থী "));

        adapter = new BanchAdapter(getContext(),list);
        viewPager = view.findViewById(R.id.viewPage);
        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.collage_image);

        map = view.findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=Shyampur High School Joypurhat"));
                Intent chooser = Intent.createChooser(intent,"Lauch Maps");
                startActivity(chooser);
            }
        });

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/admin-dash-42c58.appspot.com/o/Slid-Show%2Fupdate-school.jpg?alt=media&token=83214c12-a4a3-4061-8254-684739f7fc1f")
                .into(imageView);

        return  view;
    }
}