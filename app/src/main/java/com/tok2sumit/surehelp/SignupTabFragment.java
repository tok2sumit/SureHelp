package com.tok2sumit.surehelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class SignupTabFragment extends Fragment {

    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        button = root.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),signup_tab_activity1.class);
            startActivity(intent);
        });

        return root;
    }
}
