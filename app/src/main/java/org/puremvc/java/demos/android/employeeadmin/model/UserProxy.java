//
//  UserProxy.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.model;

import org.puremvc.java.demos.android.employeeadmin.model.valueObject.UserVO;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.util.ArrayList;

public class UserProxy extends Proxy {

    public static final String NAME = "UserProxy";

    public UserProxy() {
        super(NAME, new ArrayList<UserVO>());
    }

    public void addItem(final UserVO userVO) {
        getUsers().add(userVO);
    }

    public void updateItem(final UserVO userVO) {
        for(int i = 0; i< getUsers().size(); i++) {
            if(getUsers().get(i).username.equals(userVO.username)) {
                getUsers().set(i, userVO);
            }
        }
    }

    public void deleteItem(final UserVO userVO) {
        for(int i = 0; i< getUsers().size(); i++) {
            if(getUsers().get(i).username.equals(userVO.username)) {
                getUsers().remove(i);
            }
        }
    }

    public ArrayList<UserVO> getUsers() {
        return (ArrayList<UserVO>) data;
    }

}