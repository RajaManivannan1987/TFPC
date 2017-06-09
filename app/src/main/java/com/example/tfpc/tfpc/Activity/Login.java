package com.example.tfpc.tfpc.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tfpc.tfpc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Login extends AppCompatActivity {
    Button login;
    @BindView(R.id.memberIdEdittext)
    EditText memberId;
    @BindView(R.id.pesswordEditText)
    EditText pesswordEditText;
    @BindView(R.id.loginActivityLoginButton)
    Button loginActivityLoginButton;
    @BindView(R.id.forgotTextView)
    TextView forgotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginActivityLoginButton)
    public void onViewClicked() {
        startActivity(new Intent(Login.this, HomeActivity.class));
    }
}
