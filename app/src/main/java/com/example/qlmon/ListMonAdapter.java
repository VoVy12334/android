package com.example.qlmon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ListMonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Mon> listMon;

    public ListMonAdapter(Context context, int layout, List<Mon> listMon){
        this.context = context;
        this.layout = layout;
        this.listMon = listMon;
    }
    @Override
    public int getCount() {
        return listMon.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTenMon;
        TextView txtGia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //tải layout từ resource file và ánh xạ các thành phần
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtTenMon = convertView.findViewById(R.id.textViewTenMon);
            holder.txtGia = convertView.findViewById(R.id.textViewGia);


            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        //gán giá trị cho các thành phần trên view
        Mon mon = listMon.get(position);
        holder.txtTenMon.setText(mon.getTenMon());
        holder.txtGia.setText(String.valueOf(mon.getGia()));
        return convertView;
    }
}


