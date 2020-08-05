package com.pavlov.moappstest.screen.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pavlov.moappstest.R;
import com.pavlov.moappstest.pojo.User;
import com.pavlov.moappstest.screen.applist.ApplicationListActivity;
import com.pavlov.moappstest.screen.dialog.InfoDialog;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;
    private Button loginButton;
    private TextView info;
    private EditText emailText;
    private EditText passwordText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!sharedPreferences.getString("userToken",  "").equals("")) {
            Intent intent = new Intent(this, ApplicationListActivity.class);
            intent.putExtra("userToken", sharedPreferences.getString("userToken",  ""));
            startActivity(intent);
        }

        getSupportActionBar().hide();
        presenter = new LoginPresenter(this);
        loginButton = findViewById(R.id.loginBtn);
        emailText = findViewById(R.id.editTextEmail);
        passwordText = findViewById(R.id.editTextPassword);
        info = findViewById(R.id.infoBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateLoginData()){
                    presenter.authUser(emailText.getText().toString(), passwordText.getText().toString());
                }
                else{
                    emailText.setText("");
                    passwordText.setText("");
                }
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoDialog.display(((AppCompatActivity)v.getContext()).getSupportFragmentManager());
            }
        });
    }

    private boolean validateLoginData(){
        if(TextUtils.isEmpty(emailText.getText().toString())){
            Toast.makeText(this,  getResources().getString(R.string.enter_email), Toast.LENGTH_LONG).show();
            return false;
        }else  if(TextUtils.isEmpty(passwordText.getText().toString())){
            Toast.makeText(this, getResources().getString(R.string.enter_password), Toast.LENGTH_LONG).show();
            return false;
        }else
            return true;
    }

    @Override
    public void setUser(User user) {
        if(user != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userToken",  user.getData());
            Intent intent = new Intent(this, ApplicationListActivity.class);
            intent.putExtra("userToken", user.getData());
            editor.apply();
            startActivity(intent);
        }

    }

    @Override
    public void showError() {
        Toast.makeText(this, getResources().getString(R.string.sign_in_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.disposeDisposable();
    }

    public void deleteText(View view) {
        if(view.getId() == R.id.deleteEmail)
            emailText.setText("");
        else
            passwordText.setText("");
    }
}
