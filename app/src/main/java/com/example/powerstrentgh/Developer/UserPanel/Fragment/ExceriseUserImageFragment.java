package com.example.powerstrentgh.Developer.UserPanel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powerstrentgh.Developer.UserPanel.Adapter.ImageToUserAdapter;
import com.example.powerstrentgh.Developer.UserPanel.UserModelClass.ExceriseImageModelClass;
import com.example.powerstrentgh.R;

import java.util.ArrayList;

public class ExceriseUserImageFragment extends Fragment {


    RecyclerView recyclerView;
    ImageToUserAdapter adapter;
    ArrayList<ExceriseImageModelClass> datalist;
    public ExceriseUserImageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_excerise_user_image, container, false);
        datalist = new ArrayList<>();
        datalist.add(new ExceriseImageModelClass(R.drawable.header,"biceps"));
        datalist.add(new ExceriseImageModelClass(R.drawable.header,"back"));
        datalist.add(new ExceriseImageModelClass(R.drawable.header,"chest"));
        datalist.add(new ExceriseImageModelClass(R.drawable.header,"arms"));
        datalist.add(new ExceriseImageModelClass(R.drawable.header,"legs"));
        datalist.add(new ExceriseImageModelClass(R.drawable.header,"shoulders"));
        adapter= new ImageToUserAdapter(datalist,requireActivity());
        recyclerView = view.findViewById(R.id.rvimageID);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);
         return view;
    }
}