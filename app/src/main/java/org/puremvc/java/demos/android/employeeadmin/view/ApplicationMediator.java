//
//  ApplicationMediator.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.view;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

public class ApplicationMediator extends Mediator {

    public static final String NAME = "ApplicationMediator";

    public ApplicationMediator(Object viewComponent) {
        super(NAME, viewComponent);
    }

}