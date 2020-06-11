package com.atcampus.chasabad.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.atcampus.chasabad.R;

public class SignInFragment extends Fragment {

    public SignInFragment() {
        // Required empty public constructor
    }

    private FrameLayout parentFrameLayout;
    private EditText email,password;
    private TextView forgotPassword;
    private Button signInBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        parentFrameLayout = getActivity().findViewById(R.id.registerFrame);
        email = view.findViewById(R.id.mailEditText);
        password = view.findViewById(R.id.passwordEditText);
        forgotPassword = view.findViewById(R.id.fogotPasswordtextView);
        signInBtn = view.findViewById(R.id.signInBtn);

        return view;
    }
}