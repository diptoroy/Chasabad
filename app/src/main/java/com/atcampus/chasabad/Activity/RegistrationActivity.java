package com.atcampus.chasabad.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.atcampus.chasabad.Fragment.SignInFragment;
import com.atcampus.chasabad.Fragment.SignUpFragment;
import com.atcampus.chasabad.R;

public class RegistrationActivity extends AppCompatActivity {

    private TextView title;
    private FrameLayout frameLayout;
    private RadioButton signInBtn,signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        title = findViewById(R.id.title);
        frameLayout = findViewById(R.id.registerFrame);
        signInBtn = findViewById(R.id.signInBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        setDefaultFragment(new SignInFragment());

    }

    public void setDefaultFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    public void radioButtonChecked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.signInBtn:
                if (checked)
                    setDefaultFragment(new SignInFragment());
                break;
            case R.id.signUpBtn:
                if (checked)
                    setFragment(new SignUpFragment());
                    break;
        }
        }


}