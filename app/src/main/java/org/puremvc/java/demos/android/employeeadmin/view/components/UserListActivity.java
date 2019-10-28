//
//  UserListActivity.java
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
import org.puremvc.java.demos.android.employeeadmin.view.interfaces.IUserListActivity;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity implements UserListFragment.IUserListFragment, UserFormFragment.IUserFormFragment {

    private IUserListActivity delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_activity);
        getSupportActionBar().setTitle("User List");

        ((Application) getApplication()).registerActivity(this);

        findViewById(R.id.fab).setOnClickListener(event -> getDetails(null));

        UserListFragment fragment = (UserListFragment) getSupportFragmentManager().findFragmentById(R.id.userListFragment);
        fragment.setUsers(delegate.getUsers());
    }

    @Override
    public void getDetails(UserVO user) { // UserFormActivity Request
        if (findViewById(R.id.userFormFragment) != null) {
            UserFormFragment fragment = (UserFormFragment) getSupportFragmentManager().findFragmentById(R.id.userFormFragment);
            if (user != null) {
                fragment.setData(user, delegate.getUserRoles(user.username));
            } else {
                fragment.setData(user, null);
            }
        } else {
            Intent intent = new Intent(this, UserFormActivity.class);
            if (user != null) {
                intent.putExtra(Application.BUNDLE_USER, user);
                intent.putParcelableArrayListExtra(Application.BUNDLE_USER_ROLE, delegate.getUserRoles(user.username));
            }
            startActivityForResult(intent, Application.REQUEST_USER_FORM_ACTIVITY);
        }
    }

    @Override
    public void getRoles(UserVO user, ArrayList<RoleEnum> roles) { // UserRoleActivity Request
        Intent intent = new Intent(this, UserRoleActivity.class);
        intent.putParcelableArrayListExtra(Application.BUNDLE_USER_ROLE, delegate.getUserRoles(user.username));
        startActivityForResult(intent, Application.REQUEST_ROLE_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // UserFormActivity, UserRoleActivity Response
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Application.REQUEST_USER_FORM_ACTIVITY) {
            if (resultCode == RESULT_FIRST_USER) { // add
                UserVO user = data.getParcelableExtra(Application.BUNDLE_USER);
                this.save(user);
            } else if (resultCode == RESULT_OK) { // update
                UserVO user = data.getParcelableExtra(Application.BUNDLE_USER);
                ArrayList<RoleEnum> roles = data.getParcelableArrayListExtra(Application.BUNDLE_USER_ROLE);
                this.update(user, roles);
            } else { // cancel
                this.cancel();
            }
        } else if (requestCode == Application.REQUEST_ROLE_ACTIVITY) {
            if (resultCode == RESULT_OK) { // update roles
                UserFormFragment fragment = (UserFormFragment) getSupportFragmentManager().findFragmentById(R.id.userFormFragment);
                fragment.setRoles(data.getParcelableArrayListExtra(Application.BUNDLE_USER_ROLE));
            }
        }
    }

    @Override
    public void save(UserVO user) {
        ((UserListFragment) getSupportFragmentManager().findFragmentById(R.id.userListFragment)).addUser(user);
        delegate.saveUser(user);
    }

    @Override
    public void update(UserVO user, ArrayList<RoleEnum> roles) {
        UserListFragment fragment = (UserListFragment) getSupportFragmentManager().findFragmentById(R.id.userListFragment);
        fragment.updateUser(user);

        delegate.updateUser(user);
        delegate.updateUserRoles(user.username, roles);
    }

    @Override
    public void cancel() {
    }

    public void delete(UserVO user) {
        if (findViewById(R.id.userFormFragment) != null) {
            ((UserFormFragment) getSupportFragmentManager().findFragmentById(R.id.userFormFragment)).reset();
        }
        delegate.deleteUser(user);
    }

    public void setDelegate(IUserListActivity delegate) {
        this.delegate = delegate;
    }

}