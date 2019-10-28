//
//  Application.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin;

import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

public class Application extends android.app.Application {

    public static final int REQUEST_USER_FORM_ACTIVITY = 1;

    public static final int REQUEST_ROLE_ACTIVITY = 2;

    public static final String BUNDLE_USER = "BUNDLE_USER";

    public static final String BUNDLE_USER_ROLE = "BUNDLE_USER_ROLE";

    private static final ApplicationFacade facade = ApplicationFacade.getInstance("EmployeeAdmin");

    @Override
    public void onCreate() {
        super.onCreate();
        facade.startup(this);
    }

    public void registerActivity(AppCompatActivity activity) {
        facade.registerActivity(activity);
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}