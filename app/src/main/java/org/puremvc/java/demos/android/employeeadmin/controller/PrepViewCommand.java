package org.puremvc.java.demos.android.employeeadmin.controller;

import org.puremvc.java.demos.android.employeeadmin.view.ApplicationMediator;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class PrepViewCommand extends SimpleCommand {

    @Override
    public void execute(INotification notification) {
        getFacade().registerMediator(new ApplicationMediator(notification.getBody()));
    }
}
