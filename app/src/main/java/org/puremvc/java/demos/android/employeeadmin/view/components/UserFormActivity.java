//
//  UserFormActivity.java
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
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.UserVO;

import java.util.ArrayList;

public class UserFormActivity extends AppCompatActivity implements UserFormFragment.IUserFormFragment {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_form_activity);
        getSupportActionBar().setTitle("User Form");

        if(savedInstanceState == null) {
            UserFormFragment fragment = (UserFormFragment) getSupportFragmentManager().findFragmentById(R.id.userFormFragment);

            fragment.setData(getIntent().getParcelableExtra(Application.BUNDLE_USER),
                    getIntent().getParcelableArrayListExtra(Application.BUNDLE_USER_ROLE));
        }
    }

    @Override
    public void save(UserVO user, ArrayList<RoleEnum> roles) { // Response to UserListActivity
        Intent intent = new Intent();
        intent.putExtra(Application.BUNDLE_USER, user);
        intent.putParcelableArrayListExtra(Application.BUNDLE_USER_ROLE, roles);
        setResult(RESULT_FIRST_USER, intent);
        finish();
    }

    @Override
    public void update(UserVO user, ArrayList<RoleEnum> roles) { // Response to UserListActivity
        Intent intent = new Intent();
        intent.putExtra(Application.BUNDLE_USER, user);
        intent.putParcelableArrayListExtra(Application.BUNDLE_USER_ROLE, roles);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void cancel() { // Response to UserListActivity
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void getRoles(UserVO user, ArrayList<RoleEnum> roles) { // UserRoleActivity Request
        Intent intent = new Intent(this, UserRoleActivity.class);
        intent.putParcelableArrayListExtra(Application.BUNDLE_USER_ROLE, roles);
        startActivityForResult(intent, Application.REQUEST_ROLE_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // UserRoleActivity Response
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Application.REQUEST_ROLE_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                UserFormFragment fragment = (UserFormFragment) getSupportFragmentManager().findFragmentById(R.id.userFormFragment);
                fragment.setRoles(data.getParcelableArrayListExtra(Application.BUNDLE_USER_ROLE));
            }
        }
    }

}