//
//  DeptEnum.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.model.enumerator;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public enum DeptEnum implements Parcelable {

    NONE_SELECTED("--None Selected--", 0),
    ACCT("Accounting", 1),
    SALES("Sales", 2),
    PLANT("Plant", 3),
    SHIPPING("Shipping", 4),
    QC("Quality Control", 5);

    public String name;
    public int ordinal;

    DeptEnum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public static ArrayList<DeptEnum> list() {
        return new ArrayList<DeptEnum>(){
            {
                add(ACCT);
                add(SALES);
                add(PLANT);
                add(SHIPPING);
                add(QC);
            }
        };
    }

    public static ArrayList<DeptEnum> comboList() {
        ArrayList<DeptEnum> depts = list();
        depts.add(0, NONE_SELECTED);
        return depts;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final Creator<DeptEnum> CREATOR = new Creator<DeptEnum>() {
        @Override
        public DeptEnum createFromParcel(Parcel in) {
            return DeptEnum.values()[in.readInt()];
        }

        @Override
        public DeptEnum[] newArray(int size) {
            return new DeptEnum[size];
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