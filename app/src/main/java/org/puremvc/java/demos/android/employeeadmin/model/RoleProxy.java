//
//  RoleProxy.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.model;

import org.puremvc.java.demos.android.employeeadmin.model.enumerator.RoleEnum;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.RoleVO;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.util.ArrayList;

public class RoleProxy extends Proxy {

    public static final String NAME = "RoleProxy";

    public RoleProxy() {
        super(NAME, new ArrayList<RoleVO>());
    }

    public void addItem(RoleVO roleVO) {
        getRoles().add(roleVO);
    }

    public ArrayList<RoleEnum> getUserRoles(final String username) {
        ArrayList<RoleEnum> roles = new ArrayList<>();
        for(int i = 0, size = getRoles().size(); i < size; i++) {
            if(getRoles().get(i).username.equals(username)) {
                roles = getRoles().get(i).roles;
                break;
            }
        }
        return roles;
    }

    public void updateUserRoles(final String username, ArrayList<RoleEnum> roles) {
        for(int i = 0, size = getRoles().size(); i < size; i++) {
            if (getRoles().get(i).username.equals(username)) {
                getRoles().get(i).roles = roles;
                break;
            }
        }
    }

    public void deleteItem(final String username) {
        for(int i = 0, size = getRoles().size(); i < size; i++) {
            if(getRoles().get(i).username.equals(username)) {
                getRoles().remove(i);
                break;
            }
        }
    }

    public ArrayList<RoleVO> getRoles() {
        return (ArrayList<RoleVO>) data;
    }

}