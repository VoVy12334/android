package com.example.qlmon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    ListView lvMon;
    ListMonAdapter adapter;
    List<Mon> listMon = new ArrayList<>();
    DatabaseHelper databaseHelper;
    int currentIndex = -1;

    Button btnThemMon, btnXoaMon, btnCapNhat;
    EditText txtTenMon, txtGia;
    ImageButton imageButtonFood;
    int maMonChon = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Ánh xạ
        btnThemMon = findViewById(R.id.buttonThemMon);
        btnXoaMon = findViewById(R.id.buttonXoa);
        btnCapNhat = findViewById(R.id.buttonCapNhat);
        txtTenMon = findViewById(R.id.editTextTenMon);
        txtGia = findViewById(R.id.editTextGia);
        lvMon = findViewById(R.id.listViewMon);
        databaseHelper = new DatabaseHelper(this);
        //đọc dữ liệu
        List<Mon> list = databaseHelper.layDanhSachMon();
        listMon.addAll(list);
        // Đổ dữ liệu lên listView
        adapter = new ListMonAdapter(this, R.layout.list_mon, listMon);
        lvMon.setAdapter(adapter);
        //xử lý các nút thêm/cập nhật
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMon = txtTenMon.getText().toString();
                double gia = Double.parseDouble(txtGia.getText().toString());
                databaseHelper.themMon(tenMon, gia);
                capNhatListView();
                clearView();
                Toast.makeText(MainActivity3.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        lvMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mon mon = listMon.get(position);
                txtTenMon.setText(mon.getTenMon());
                txtGia.setText(String.valueOf(mon.getGia()));
                maMonChon = mon.getMaMon();
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maMonChon == -1) return;

                String tenMon = txtTenMon.getText().toString();
                double gia = Double.parseDouble(txtGia.getText().toString());

                databaseHelper.capNhatMon(maMonChon, tenMon, gia);
                capNhatListView();
                clearView();

                Toast.makeText(MainActivity3.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

            }
        });

        btnXoaMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maMonChon == -1) return;
                databaseHelper.xoaMon(maMonChon);

                capNhatListView();
                clearView();

                Toast.makeText(MainActivity3.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearView () {
        txtTenMon.setText("");
        txtGia.setText("");
        maMonChon = -1;
    }

    private void capNhatListView () {
        listMon.clear();
        listMon.addAll(databaseHelper.layDanhSachMon());
        adapter.notifyDataSetChanged();
    }
}

