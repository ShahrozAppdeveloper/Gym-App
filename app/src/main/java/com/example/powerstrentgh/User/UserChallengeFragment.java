package com.example.powerstrentgh.User;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.powerstrentgh.ClassAdapter.ChallengeAdapter;
import com.example.powerstrentgh.ModelCLass.UserCreateChallengeModelClass;
import com.example.powerstrentgh.R;

import java.util.ArrayList;


public class UserChallengeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<UserCreateChallengeModelClass> list;
    ChallengeAdapter adapter;



    public UserChallengeFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_challenge, container, false);
        recyclerView=view.findViewById(R.id.challengerv);
        list=new ArrayList<>();
        list.add(new UserCreateChallengeModelClass("dumbbellcurl","https://youtu.be/hIkeJVV-Djk?si=3L9TPMM7iGEYoiMN",R.drawable.bicep_dumbell));
        list.add(new UserCreateChallengeModelClass("hammercurls","https://youtube.com/shorts/eDVE75EisoY?si=gSrwgi0ppNvXFskO",R.drawable.hammercurls));
        list.add(new UserCreateChallengeModelClass("rodcurl","https://youtube.com/shorts/cyqfTrNrN6M?si=aZ_2Tb2XfaK_0yCx",R.drawable.curlbarexercise));
        list.add(new UserCreateChallengeModelClass("reardelt","https://youtube.com/shorts/SLOkdLLWj8A?si=QcpWeFHaqigImq-F",R.drawable.reardelt));
        list.add(new UserCreateChallengeModelClass("dumbbelfront","https://youtube.com/shorts/SLOkdLLWj8A?si=QcpWeFHaqigImq-F",R.drawable.seareddumbell));
        list.add(new UserCreateChallengeModelClass("shoulderpress","https://youtube.com/shorts/SLOkdLLWj8A?si=QcpWeFHaqigImq-F",R.drawable.shoulderpress));
        list.add(new UserCreateChallengeModelClass("backsquats","https://youtube.com/shorts/SLOkdLLWj8A?si=QcpWeFHaqigImq-F",R.drawable.backsquats));
        list.add(new UserCreateChallengeModelClass("sumosquats","https://youtube.com/shorts/SLOkdLLWj8A?si=QcpWeFHaqigImq-F",R.drawable.sumosquat));
        list.add(new UserCreateChallengeModelClass("inclinebenchpress","https://youtube.com/shorts/SLOkdLLWj8A?si=QcpWeFHaqigImq-F",R.drawable.inclinebench));
        list.add(new UserCreateChallengeModelClass("inclinedumbbellpress","https://youtube.com/shorts/SLOkdLLWj8A?si=QcpWeFHaqigImq-F",R.drawable.inclinedumbellpress));
        list.add(new UserCreateChallengeModelClass("cablechestcross","https://youtube.com/shorts/SLOkdLLWj8A?si=QcpWeFHaqigImq-F",R.drawable.cablechestcross));

        adapter=new ChallengeAdapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        return view;
    }
    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Bundle bundle=getArguments();
            Fragment fragment=new Home();
            fragment.setArguments(bundle);
            ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    };

    // Register the callback in the onCreate() method
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    // Unregister the callback in the onDestroy() method
    @Override
    public void onDestroy() {
        super.onDestroy();
        onBackPressedCallback.remove();
    }
}