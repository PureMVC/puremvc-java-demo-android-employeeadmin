//
//  UserListFragment.java
//  PureMVC Android Demo - EmployeeAdmin
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.java.demos.android.employeeadmin.view.components;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import org.puremvc.java.demos.android.employeeadmin.R;
import org.puremvc.java.demos.android.employeeadmin.model.valueObject.UserVO;

import java.util.ArrayList;

public class UserListFragment extends Fragment implements GestureDetector.OnGestureListener {

    private ArrayList<UserVO> users;

    private UserListAdapter adapter;

    private ListView listView;

    public IUserListFragment activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.user_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.fab).setVisibility(View.VISIBLE);
        view.findViewById(R.id.fab).setOnClickListener(event -> activity.getDetails(null));
    }

    public void hideFab() {
        getView().findViewById(R.id.fab).setVisibility(View.INVISIBLE);
    }

    public void setUsers(ArrayList<UserVO> users) {
        this.users = users;
        adapter = new UserListAdapter(getActivity(), users);

        listView = getView().findViewById(R.id.listView);
        listView.setAdapter(adapter);

        GestureDetector detector = new GestureDetector(getContext(), this);
        listView.setOnTouchListener((view, event) -> {
            detector.onTouchEvent(event);
            return false;
        });
    }

    public void addUser(UserVO user) {
        adapter.notifyDataSetChanged();
    }

    public void updateUser(UserVO user) {
        for (int i = 0, size = users.size(); i < size; i++) {
            if (users.get(i).username.equals(user.username)) {
                users.set(i, user);
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }

    private class UserListAdapter extends ArrayAdapter<UserVO> {

        public UserListAdapter(Context context, ArrayList<UserVO> users) {
            super(context, 0, users);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, container, false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).toString());
            return convertView;
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float deltaX = e2.getX() - e1.getX();
        float deltaY = e2.getY() - e1.getY();

        try {
            int position = listView.pointToPosition(Math.round(e1.getX()), Math.round(e1.getY()));
            UserVO user = adapter.getItem(position);
            if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > 100 && Math.abs(velocityX) > 100) { // Distance, Velocity Threshold
                if (deltaX <= 0) { // swipe left
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete")
                            .setMessage(String.format("Are you sure you want to delete %s?", user))
                            .setIcon(android.R.drawable.ic_delete)
                            .setPositiveButton(android.R.string.yes, (dialog, button) -> {
                                users.remove(user);
                                adapter.notifyDataSetChanged();
                                activity.delete(user);
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .create().show();
                }
                return true;
            }
        } catch (Exception exception) {
        }
        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        try {
            UserVO user = adapter.getItem(listView.pointToPosition(Math.round(event.getX()), Math.round(event.getY())));
            activity.getDetails(user);
            return true;
        } catch (Exception exception) {
        }
        return false;
    }

    @Override public boolean onDown(MotionEvent event) { return false; }

    @Override public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return false; }

    @Override public void onShowPress(MotionEvent event) { }

    @Override public void onLongPress(MotionEvent event) { }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (IUserListFragment) getActivity();
    }

    public interface IUserListFragment {
        void getDetails(UserVO user);
        void delete(UserVO user);
    }

}