package com.lightning.demo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lightning.demo.R;
import com.lightning.demo.pojo.User;
import com.lightning.demo.utils.Utils;

import java.util.List;

/**
 * Created by Abhishek on 6/12/2016.
 */

public class UserAdapter extends BaseAdapter {
    private List<User> list;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<User> list) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public User getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_user, parent, false);
            holder = new Holder();
            holder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
            holder.txtDepartment = (TextView) convertView.findViewById(R.id.txt_dept);
            holder.txtSalary = (TextView) convertView.findViewById(R.id.txt_salary);
            holder.imgProfileImage = (ImageView) convertView.findViewById(R.id.img_profileImage);
        } else
            holder = (Holder) convertView.getTag();
        User user = list.get(position);
        holder.txtName.setText(user.getFirstName() + " " + user.getLastName());
        holder.txtDepartment.setText("Department: " + user.getDepartment());
        holder.txtSalary.setText("Salary: " + user.getSalary());
        holder.imgProfileImage.setImageBitmap(Utils.getPhoto(user.getImage()));
        return convertView;
    }

    static class Holder {
        ImageView imgProfileImage;
        TextView txtName;
        TextView txtDepartment;
        TextView txtSalary;
    }
}
