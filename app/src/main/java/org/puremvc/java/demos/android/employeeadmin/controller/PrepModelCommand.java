package org.puremvc.java.demos.android.employeeadmin.controller;

import org.puremvc.java.demos.android.employeeadmin.model.RoleProxy;
import org.puremvc.java.demos.android.employeeadmin.model.UserProxy;
import org.puremvc.java.demos.android.employeeadmin.model.enumerator.DeptEnum;
import org.puremvc.java.demos.android.employeeadmin.model.enumerator.RoleEnum;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.RoleVO;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.UserVO;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import java.util.ArrayList;

public class PrepModelCommand extends SimpleCommand {

    /**
     * Prepare the Model.
     */
    @Override
    public void execute(INotification notification) {
        // Create User Proxy,
        UserProxy userProxy = new UserProxy();

        //Populate it with dummy data
        userProxy.addItem(new UserVO("lstooge", "Larry", "Stooge","larry@stooges.com", "ijk456", DeptEnum.ACCT));
        userProxy.addItem(new UserVO("cstooge", "Curly", "Stooge","curly@stooges.com", "xyz987", DeptEnum.SALES));
        userProxy.addItem(new UserVO("mstooge", "Moe", "Stooge","moe@stooges.com", "abc123", DeptEnum.PLANT));

        // register it
        getFacade().registerProxy(userProxy);

        // Create Role Proxy
        RoleProxy roleProxy = new RoleProxy();

        //Populate it with dummy data
        roleProxy.addItem(new RoleVO("lstooge", new ArrayList<RoleEnum>(){{
            add(RoleEnum.EMP_BENEFITS);
        }}));
        roleProxy.addItem(new RoleVO("cstooge", new ArrayList<RoleEnum>(){{
            add(RoleEnum.ACCT_RCV);
            add(RoleEnum.GEN_LEDGER);
        }}));
        roleProxy.addItem(new RoleVO("mstooge", new ArrayList<RoleEnum>(){{
            add(RoleEnum.PRODUCTION);
            add(RoleEnum.SALES);
            add(RoleEnum.SHIPPING);
        }}));

        // register it
        getFacade().registerProxy(roleProxy);
    }
}
