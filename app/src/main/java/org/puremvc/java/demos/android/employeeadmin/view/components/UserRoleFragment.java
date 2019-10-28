//
//  UserRoleFragment.java
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
import android.widget.CheckedTextView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.puremvc.java.demos.android.employeeadmin.R;
import org.puremvc.java.demos.android.employeeadmin.model.enumerator.RoleEnum;

import java.util.ArrayList;

public class UserRoleFragment extends Fragment {

    private ArrayList<RoleEnum> roles;

    private IUserRoleFragment activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.user_role_fragment, container, false);

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(new UserRoleAdapter(getActivity(), new ArrayList<>(RoleEnum.list())));

        View footer = inflater.inflate(R.layout.user_role_footer, null, false);
        listView.addFooterView(footer);

        footer.findViewById(R.id.ok).setOnClickListener(event -> activity.save(roles)); // Response to UserRoleActivity
        footer.findViewById(R.id.cancel).setOnClickListener(event -> activity.cancel());

        return view;
    }

    public void setRoles(ArrayList<RoleEnum> roles) {
        this.roles = roles;
    }

    private class UserRoleAdapter extends ArrayAdapter<RoleEnum> {

        public UserRoleAdapter(Context context, ArrayList<RoleEnum> roles) {
            super(context, 0, roles);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup container) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_multiple_choice, container, false);
            }

            CheckedTextView checkedTextView = convertView.findViewById(android.R.id.text1);
            checkedTextView.setText(getItem(position).name());
            checkedTextView.setChecked(roles.contains(getItem(position)));
            checkedTextView.setTag(getItem(position));

            checkedTextView.setOnClickListener((view) -> {
                if(checkedTextView.isChecked()) {
                    checkedTextView.setChecked(false);
                    roles.remove(view.getTag());
                } else {
                    checkedTextView.setChecked(true);
                    roles.add((RoleEnum) view.getTag());
                }
            });

            return convertView;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (IUserRoleFragment) getActivity();
    }

    public interface IUserRoleFragment {
        void save(ArrayList<RoleEnum> roles);
        void cancel();
    }

}