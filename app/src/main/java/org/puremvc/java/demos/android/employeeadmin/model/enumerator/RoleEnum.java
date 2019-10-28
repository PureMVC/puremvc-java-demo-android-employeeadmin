//
//  RoleEnum.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.model.enumerator;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public enum RoleEnum implements Parcelable {

    NONE_SELECTED("--None Selected--", 0),
    ADMIN("Administrator", 1),
    ACCT_PAY("Accounts Payable", 2),
    ACCT_RCV("Accounts Receivable", 3),
    EMP_BENEFITS("Employee Benefits", 4),
    GEN_LEDGER("General Ledger", 5),
    PAYROLL("Payroll", 6),
    INVENTORY("Inventory", 7),
    PRODUCTION("Production", 8),
    QUALITY_CTL("Quality Control", 9),
    SALES("Sales", 10),
    ORDERS("Orders", 11),
    CUSTOMERS("Customers", 12),
    SHIPPING("Shipping", 13),
    RETURNS("Returns", 14);

    private final String name;
    private final int ordinal;

    RoleEnum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public static ArrayList<RoleEnum> list() {
        return new ArrayList<RoleEnum>(){
            {
                add(ADMIN);
                add(ACCT_PAY);
                add(ACCT_RCV);
                add(EMP_BENEFITS);
                add(GEN_LEDGER);
                add(PAYROLL);
                add(INVENTORY);
                add(PRODUCTION);
                add(QUALITY_CTL);
                add(SALES);
                add(ORDERS);
                add(CUSTOMERS);
                add(SHIPPING);
                add(RETURNS);
            }
        };
    }

    public static ArrayList<RoleEnum> comboList() {
        ArrayList<RoleEnum> roles = list();
        roles.add(0, NONE_SELECTED);
        return roles;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final Creator<RoleEnum> CREATOR = new Creator<RoleEnum>() {
        @Override
        public RoleEnum createFromParcel(Parcel in) {
            return RoleEnum.values()[in.readInt()];
        }

        @Override
        public RoleEnum[] newArray(int size) {
            return new RoleEnum[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal);
    }

}