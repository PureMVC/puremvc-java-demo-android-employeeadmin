//
//  UserVO.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.model.valueObject;

import android.os.Parcel;
import android.os.Parcelable;

import org.puremvc.java.demos.android.employeeadmin.model.enumerator.DeptEnum;

public class UserVO implements Parcelable {

    public String username;
    public String first;
    public String last;
    public String email;
    public String password;
    public DeptEnum department;

    public UserVO() {
    }

    public UserVO(String username, String first, String last, String email, String password, DeptEnum department) {
        this.username = username != null ? username : new String();
        this.first = first != null ? first : new String();
        this.last = last != null ? last : new String();
        this.email = email != null ? email : new String();
        this.password = password != null ? password : new String();
        this.department = (department != null) ? department : DeptEnum.NONE_SELECTED;
    }

    public Boolean isValid() {
        return !(first.equals("") || last.equals("") || username.equals("") || password.equals(""));
    }

    public String toString() {
        return last + ", " + first;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected UserVO(Parcel in) {
        username = in.readString();
        first = in.readString();
        last = in.readString();
        email = in.readString();
        password = in.readString();
        department = in.readParcelable(DeptEnum.class.getClassLoader());
    }

    public static final Creator<UserVO> CREATOR = new Creator<UserVO>() {
        @Override
        public UserVO createFromParcel(Parcel in) {
            return new UserVO(in);
        }

        @Override
        public UserVO[] newArray(int size) {
            return new UserVO[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(first);
        parcel.writeString(last);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeParcelable(department, i);
    }

}