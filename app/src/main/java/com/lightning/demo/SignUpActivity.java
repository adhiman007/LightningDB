package com.lightning.demo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lightning.demo.pojo.User;
import com.lightning.demo.pojo.UserTable;
import com.lightning.demo.utils.Utils;

/**
 * Created by Abhishek on 6/12/2016.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editUserName;
    private EditText editPassword;
    private EditText editConfirmPassword;
    private EditText editSalary;
    private Spinner spinDepartment;
    private UserTable table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editFirstName = (EditText) findViewById(R.id.edit_firstName);
        editLastName = (EditText) findViewById(R.id.edit_lastName);
        editUserName = (EditText) findViewById(R.id.edit_userName);
        editPassword = (EditText) findViewById(R.id.edit_password);
        editConfirmPassword = (EditText) findViewById(R.id.edit_confirmPassword);
        editSalary = (EditText) findViewById(R.id.edit_salary);
        spinDepartment = (Spinner) findViewById(R.id.spin_dept);
        findViewById(R.id.btn_signUp).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        table = new UserTable(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.btn_signUp:
                if (TextUtils.isEmpty(editFirstName.getText().toString()))
                    editFirstName.setError(getResources().getString(R.string.empty_firstname));
                else if (TextUtils.isEmpty(editLastName.getText().toString()))
                    editLastName.setError(getResources().getString(R.string.empty_lastname));
                else if (TextUtils.isEmpty(editUserName.getText().toString()))
                    editUserName.setError(getResources().getString(R.string.empty_usernamae));
                else if (TextUtils.isEmpty(editPassword.getText().toString()))
                    editPassword.setError(getResources().getString(R.string.empty_password));
                else if (TextUtils.isEmpty(editConfirmPassword.getText().toString()))
                    editConfirmPassword.setError(getResources().getString(R.string.empty_confirmpassword));
                else if (!editPassword.getText().toString().equals(editConfirmPassword.getText().toString()))
                    Toast.makeText(this, getResources().getString(R.string.password_mismatch), Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(editSalary.getText().toString()))
                    editSalary.setError(getResources().getString(R.string.empty_salary));
                else {
                    User user = new User();
                    user.setFirstName(editFirstName.getText().toString());
                    user.setLastName(editLastName.getText().toString());
                    user.setUserName(editUserName.getText().toString());
                    user.setPassword(editPassword.getText().toString());
                    user.setDepartment(spinDepartment.getSelectedItem().toString());
                    user.setSalary(Float.parseFloat(editSalary.getText().toString()));
                    user.setImage(Utils.getBytes(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
                    table.insert(user);
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
        }
    }
}
