//
//  Activity.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.controller;

import androidx.appcompat.app.AppCompatActivity;

import org.puremvc.java.demos.android.employeeadmin.view.UserListMediator;
import org.puremvc.java.demos.android.employeeadmin.view.components.UserListActivity;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class RegisterCommand extends SimpleCommand {

    @Override
    public void execute(INotification notification) {
        AppCompatActivity activity = (AppCompatActivity) notification.getBody();

        if (activity instanceof UserListActivity) {
            getFacade().registerMediator(new UserListMediator(activity));
        }
    }
}