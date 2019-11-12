//
//  UserFormFragment.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.view.components;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import org.puremvc.java.demos.android.employeeadmin.R;
import org.puremvc.java.demos.android.employeeadmin.model.enumerator.DeptEnum;
import org.puremvc.java.demos.android.employeeadmin.model.enumerator.RoleEnum;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.UserVO;

import java.util.ArrayList;

public class UserFormFragment extends Fragment {

    private UserVO user;

    private ArrayList<RoleEnum> roles = new ArrayList();

    private Spinner spinner;

    private IUserFormFragment activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_form_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, DeptEnum.comboList()));

        view.findViewById(R.id.roles).setOnClickListener(event -> activity.getRoles(this.user, roles));

        view.findViewById(R.id.save).setOnClickListener(event -> { // save or update
            UserVO user = new UserVO();
            user.first = ((TextView) getView().findViewById(R.id.first)).getText().toString();
            user.last = ((TextView) getView().findViewById(R.id.last)).getText().toString();
            user.email = ((TextView) getView().findViewById(R.id.email)).getText().toString();
            user.username = ((TextView) getView().findViewById(R.id.username)).getText().toString();
            user.password = ((TextView) getView().findViewById(R.id.password)).getText().toString();
            user.department = (DeptEnum) spinner.getSelectedItem();

            if (!user.password.equals(((TextView) getView().findViewById(R.id.confirm)).getText().toString())) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Error")
                        .setMessage("Your password and confirmation password do not match.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, null)
                        .create().show();
                return;
            }

            if (!user.isValid()) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Error")
                        .setMessage("Invalid Form Data.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, null)
                        .create().show();
                return;
            }

            if (!getView().findViewById(R.id.username).isEnabled()) {
                activity.update(user, roles);
                reset();
                return;
            }

            activity.save(user, roles);
            reset();
        });

        view.findViewById(R.id.cancel).setOnClickListener(event -> {
            activity.cancel();
            reset();
        });
    }

    public void setData(UserVO user, ArrayList<RoleEnum> roles) {
        if (user == null) { // new
            return;
        }

        this.user = user;
        this.roles = roles;

        ((TextView) getView().findViewById(R.id.first)).setText(user.first);
        ((TextView) getView().findViewById(R.id.last)).setText(user.last);
        ((TextView) getView().findViewById(R.id.email)).setText(user.email);
        ((TextView) getView().findViewById(R.id.username)).setText(user.username);
        ((TextView) getView().findViewById(R.id.password)).setText(user.password);

        getView().findViewById(R.id.username).setEnabled(false);
        ((TextView) getView().findViewById(R.id.confirm)).setText(user.password);
        ((TextView) getView().findViewById(R.id.save)).setText(R.string.update);

        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0, count = adapter.getCount(); i < count; i++) {
            if(adapter.getItem(i) == user.department) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    public void setRoles(ArrayList<RoleEnum> roles) {
        this.roles = roles;
    }

    public void reset() {
        this.user = null;
        this.roles = null;

        ((TextView) getView().findViewById(R.id.first)).setText("");
        ((TextView) getView().findViewById(R.id.last)).setText("");
        ((TextView) getView().findViewById(R.id.email)).setText("");
        ((TextView) getView().findViewById(R.id.username)).setText("");
        ((TextView) getView().findViewById(R.id.password)).setText("");
        ((TextView) getView().findViewById(R.id.confirm)).setText("");

        getView().findViewById(R.id.username).setEnabled(true);
        ((TextView) getView().findViewById(R.id.confirm)).setText("");
        ((TextView) getView().findViewById(R.id.save)).setText(R.string.save);

        spinner.setSelection(0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (IUserFormFragment) getActivity();
    }

    public interface IUserFormFragment {
        void save(UserVO user, ArrayList<RoleEnum> roles);
        void update(UserVO user, ArrayList<RoleEnum> roles);
        void getRoles(UserVO user, ArrayList<RoleEnum> roles);
        void cancel();
    }

}