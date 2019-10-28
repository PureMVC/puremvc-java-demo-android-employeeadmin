//
//  DeleteUserCommand.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.controller;

import org.puremvc.java.demos.android.employeeadmin.model.RoleProxy;
import org.puremvc.java.demos.android.employeeadmin.model.UserProxy;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.UserVO;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class DeleteUserCommand extends SimpleCommand {

    @Override
    public void execute(INotification notification) {
        UserVO userVO = (UserVO) notification.getBody();
        UserProxy userProxy = (UserProxy) getFacade().retrieveProxy(UserProxy.NAME);
        RoleProxy roleProxy = (RoleProxy) getFacade().retrieveProxy(RoleProxy.NAME);
        userProxy.deleteItem(userVO);
        //roleProxy.deleteItem(userVO);
    }

}