package com.lightning.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lightning.demo.pojo.UserTable;

/**
 * Created by Abhishek on 6/12/2016.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editUserName;
    private EditText editPassword;
    private UserTable table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUserName = (EditText) findViewById(R.id.edit_userName);
        editPassword = (EditText) findViewById(R.id.edit_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_signUp).setOnClickListener(this);
        table = new UserTable(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty(editUserName.getText().toString()))
                    editUserName.setError(getResources().getString(R.string.empty_usernamae));
                else if (TextUtils.isEmpty(editPassword.getText().toString()))
                    editPassword.setError(getResources().getString(R.string.empty_password));
                else {
                    if (table.exist(UserTable.COLUMN_USERNAME + " = '" + editUserName.getText().toString() + "' AND " + UserTable.COLUMN_PASSWORD + " = '" + editPassword.getText().toString() + "'")) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else
                        Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_signUp:
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;
        }
    }
}
