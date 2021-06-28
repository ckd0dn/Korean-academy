package com.pch.ex_1223;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.gun0912.tedpermission.TedPermission;

public class PermissionActivity extends AppCompatActivity {

    TedPermission tedPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            tedPermission.
        }
    };
}