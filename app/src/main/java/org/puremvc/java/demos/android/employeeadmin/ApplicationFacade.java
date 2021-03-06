//
//  ApplicationFacade.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin;

import androidx.appcompat.app.AppCompatActivity;

import org.puremvc.java.demos.android.employeeadmin.controller.RegisterCommand;
import org.puremvc.java.demos.android.employeeadmin.controller.StartupCommand;
import org.puremvc.java.multicore.patterns.facade.Facade;

public class ApplicationFacade extends Facade {

    public static final String STARTUP = "startup";

    public static final String REGISTER = "register";

    private ApplicationFacade(String key) {
        super(key);
    }

    @Override
    protected void initializeController() {
        super.initializeController();
        registerCommand(STARTUP, () -> new StartupCommand());
        registerCommand(REGISTER, () -> new RegisterCommand());
    }

    public static ApplicationFacade getInstance(String key) {
        return (ApplicationFacade) Facade.getInstance(key, k -> new ApplicationFacade(k));
    }

    public void registerActivity(AppCompatActivity activity) {
        sendNotification(REGISTER, activity);
    }

    public void startup(Application application) {
        sendNotification(STARTUP, application);
    }

}