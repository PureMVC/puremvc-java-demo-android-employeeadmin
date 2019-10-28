//
//  IUserListActivity.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.view.interfaces;

import org.puremvc.java.demos.android.employeeadmin.model.enumerator.RoleEnum;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.UserVO;

import java.util.ArrayList;

public interface IUserListActivity {

    ArrayList<UserVO> getUsers();

    void saveUser(UserVO user);

    void updateUser(UserVO user);

    void deleteUser(UserVO user);

    ArrayList<RoleEnum> getUserRoles(String username);

    void updateUserRoles(String username, ArrayList<RoleEnum> roles);

}