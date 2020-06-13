package com.atcampus.chasabad.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
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
    private TextView skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        title = findViewById(R.id.title);
        frameLayout = findViewById(R.id.registerFrame);
        signInBtn = findViewById(R.id.signInRadioBtn);
        signUpBtn = findViewById(R.id.signUpRadioBtn);
        skipBtn = findViewById(R.id.skipBtn);
        setDefaultFragment(new SignInFragment());

        checkBtn();
    }

    private void checkBtn() {
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setDefaultFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_left,R.anim.slide_out_from_right);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_right,R.anim.slide_out_from_left);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    public void radioButtonChecked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.signInRadioBtn:
                if (checked)
                    setDefaultFragment(new SignInFragment());
                break;
            case R.id.signUpRadioBtn:
                if (checked)
                    setFragment(new SignUpFragment());
                    break;
        }
        }


}