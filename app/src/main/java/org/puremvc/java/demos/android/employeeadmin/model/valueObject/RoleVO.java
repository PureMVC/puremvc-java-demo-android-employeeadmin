//
//  RoleVO.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.model.valueObject;

import android.os.Parcel;
import android.os.Parcelable;

import org.puremvc.java.demos.android.employeeadmin.model.enumerator.RoleEnum;

import java.util.ArrayList;

public class RoleVO implements Parcelable {

    public String username;
    public ArrayList<RoleEnum> roles;

    public RoleVO(String username, ArrayList<RoleEnum> roles) {
        this.username = username != null ? username : new String();
        this.roles = roles != null ? roles : new ArrayList<>();
    }

    protected RoleVO(Parcel in) {
        username = in.readString();
        roles = in.createTypedArrayList(RoleEnum.CREATOR);
    }

    public static final Creator<RoleVO> CREATOR = new Creator<RoleVO>() {
        @Override
        public RoleVO createFromParcel(Parcel in) {
            return new RoleVO(in);
        }

        @Override
        public RoleVO[] newArray(int size) {
            return new RoleVO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeTypedList(roles);
    }
}