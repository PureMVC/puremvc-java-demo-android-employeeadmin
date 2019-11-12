//
//  UserListMediator.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.view;

import androidx.appcompat.app.AppCompatActivity;

import org.puremvc.java.demos.android.employeeadmin.model.RoleProxy;
import org.puremvc.java.demos.android.employeeadmin.model.UserProxy;
import org.puremvc.java.demos.android.employeeadmin.model.enumerator.RoleEnum;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.RoleVO;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.UserVO;
import org.puremvc.java.demos.android.employeeadmin.view.components.UserListActivity;
import org.puremvc.java.demos.android.employeeadmin.view.interfaces.IUserListActivity;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import java.util.ArrayList;

public class UserListMediator extends Mediator implements IUserListActivity {

    public static final String NAME = "UserListMediator";

    private UserProxy userProxy;
    private RoleProxy roleProxy;

    public UserListMediator(AppCompatActivity viewComponent) {
        super(NAME, viewComponent);
    }

    @Override
    public void onRegister() {
        userProxy = (UserProxy) getFacade().retrieveProxy(UserProxy.NAME);
        roleProxy = (RoleProxy) getFacade().retrieveProxy(RoleProxy.NAME);

        switch (getViewComponent().getClass().getSimpleName()) {
            case "UserListActivity":
                ((UserListActivity) getViewComponent()).setDelegate(this);
                break;
        }
    }

    @Override
    public ArrayList<UserVO> getUsers() {
        return userProxy.getUsers();
    }

    @Override
    public void saveUser(UserVO user, ArrayList<RoleEnum> roles) {
        userProxy.addItem(user);
        roleProxy.addItem(new RoleVO(user.username, roles));
    }

    @Override
    public void updateUser(UserVO user) {
        userProxy.updateItem(user);
    }

    @Override
    public void deleteUser(UserVO user) {
        userProxy.deleteItem(user);
        roleProxy.deleteItem(user.username);
    }

    @Override
    public ArrayList<RoleEnum> getUserRoles(String username) {
        return roleProxy.getUserRoles(username);
    }

    @Override
    public void updateUserRoles(String username, ArrayList<RoleEnum> roles) {
        roleProxy.updateUserRoles(username, roles);
    }

}