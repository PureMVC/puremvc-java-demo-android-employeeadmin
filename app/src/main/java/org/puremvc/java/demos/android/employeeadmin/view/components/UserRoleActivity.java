//
//  UserRoleActivity.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.view.components;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.puremvc.java.demos.android.employeeadmin.Application;
import org.puremvc.java.demos.android.employeeadmin.R;
import org.puremvc.java.demos.android.employeeadmin.model.enumerator.RoleEnum;

import java.util.ArrayList;

public class UserRoleActivity extends AppCompatActivity implements UserRoleFragment.IUserRoleFragment {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_role_activity);
        getSupportActionBar().setTitle("User Role");

        if (savedInstanceState == null) {
            UserRoleFragment fragment = (UserRoleFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.userRoleFragment);
            fragment.setRoles(this.getIntent().getParcelableArrayListExtra(Application.BUNDLE_USER_ROLE));
        }
    }

    @Override
    public void save(ArrayList<RoleEnum> roles) { // Response to Activity
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(Application.BUNDLE_USER_ROLE, roles);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void cancel() { // Response to Activity
        setResult(RESULT_CANCELED);
        finish();
    }

}