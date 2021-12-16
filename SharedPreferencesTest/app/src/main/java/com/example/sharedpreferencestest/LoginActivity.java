package com.example.sharedpreferencestest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private CheckBox rememberPass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("data",MODE_PRIVATE);

        //pref = PreferenceManager.getDefaultSharedPreferences(this);

        usernameEdit = findViewById(R.id.username);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember_pass);

        boolean isRemember = pref.getBoolean("remember_password",false);

        if (isRemember) {
            //将账号和密码都设置到文本框中
            String username = pref.getString("username","");
            String password = pref.getString("password","");

            usernameEdit.setText(username);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
    }

    public void loginButton(View view) {

        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        //如果账号是admin@royole.com且密码是123456就认为登录成功
        if (username.equals("admin@royole.com") && password.equals("123456")) {
            editor = pref.edit();

            if (rememberPass.isChecked()) {
                //检查复选框是否被选中
                editor.putString("username",username);
                editor.putString("password",password);
                editor.putBoolean("remember_password",true);
            } else {
                editor.clear();
            }
            editor.apply();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this,"account or password is invalid",Toast.LENGTH_SHORT).show();
        }
    }
}