package com.example.dvd24.DangKyvsDangNhap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dvd24.CacBanCty.KeToanTruong;
import com.example.dvd24.DocBao.DocBao;

import com.example.dvd24.CacBanCty.QuanTriVien;
import com.example.dvd24.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DangNhap extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences_admin;
    EditText sdt_dang_nhapC,mat_khau_dang_nhapC;
    Button button_dangNhapC,button_chua_co_tai_khoan_dang_kyC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        //an StatusBar System
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sdt_dang_nhapC = findViewById(R.id.sdt_dang_nhap);
        mat_khau_dang_nhapC = findViewById(R.id.mat_khau_dang_nhap);
        button_dangNhapC = findViewById(R.id.button_dangNhap);
        button_chua_co_tai_khoan_dang_kyC = findViewById(R.id.chua_co_tai_khoan_dang_ky);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("tennguoidung", MODE_PRIVATE);
        sharedPreferences_admin = getSharedPreferences("tennguoidung_admin", MODE_PRIVATE);
        // button
        button_dangNhapC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!XacThucSoDienThoai() | !XacThucMatKhau()){

                }else {
                    KiemTraNguoiDung();
                }
            }
        });
        button_chua_co_tai_khoan_dang_kyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
    }
    public Boolean XacThucSoDienThoai(){
        String val = sdt_dang_nhapC.getText().toString();
        if (val.isEmpty()) {
            sdt_dang_nhapC.setError("Số điện thoại không được để trống");
            return false;
        } else {
            sdt_dang_nhapC.setError(null);
            return true;
        }
    }
    public Boolean XacThucMatKhau(){
        String val = mat_khau_dang_nhapC.getText().toString();
        if (val.isEmpty()) {
            mat_khau_dang_nhapC.setError("Mật khẩu không được để trống");
            return false;
        } else {
            mat_khau_dang_nhapC.setError(null);
            return true;
        }
    }
    public void KiemTraNguoiDung(){
        String sdt = sdt_dang_nhapC.getText().toString().trim();
        String matKhau = mat_khau_dang_nhapC.getText().toString().trim();
        //nguoidung
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Nguoi_Dung");
        Query kiemtra = reference.orderByChild("so_dien_thoai").equalTo(sdt);
        //quanly
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Quan_Ly");
        Query kiemtra2 = reference2.orderByChild("so_dien_thoai").equalTo(sdt);
        //ban ke toan
        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference("Ban_Ke_Toan");
        Query kiemtra3 = reference3.orderByChild("so_dien_thoai").equalTo(sdt);



        kiemtra.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    sdt_dang_nhapC.setError(null);
                    String mat_khau_from_DB = snapshot.child(sdt).child("mat_khau").getValue(String.class);

                    if (mat_khau_from_DB.equals(matKhau)){
                        sdt_dang_nhapC.setError(null);
                        Toast.makeText(DangNhap.this, "Chúc mừng bạn đã đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        // Lưu tên người dùng vào SharedPreferences
                        String tenNguoidung = snapshot.child(sdt).child("ten").getValue(String.class);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("tennguoidung", tenNguoidung);
                        editor.putBoolean("loggedIn",true);
                        editor.apply();
                        finishAffinity();
                        // cho can doi
                        Intent intent = new Intent(DangNhap.this, DocBao.class);
                        startActivity(intent);
                    }
                    else {
                        mat_khau_dang_nhapC.setError("Sai mật khẩu hoặc tài khoản không đúng");
                        mat_khau_dang_nhapC.requestFocus();
                    }
                }else {
                    kiemtra2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                sdt_dang_nhapC.setError(null);
                                String mat_khau_from_DB = snapshot.child(sdt).child("mat_khau").getValue(String.class);

                                if (mat_khau_from_DB.equals(matKhau)){
                                    sdt_dang_nhapC.setError(null);
                                    Toast.makeText(DangNhap.this, "Chúc mừng bạn đã đăng nhập thành công", Toast.LENGTH_SHORT).show();

                                    // Lưu tên người dùng vào SharedPreferences, sau khi đăng nhập, không thể quay về trang đăng nhập
                                    String tenNguoidung = snapshot.child(sdt).child("ten").getValue(String.class);
                                    SharedPreferences.Editor editor = sharedPreferences_admin.edit();
                                    editor.putString("tennguoidung_admin", tenNguoidung);
                                    editor.putBoolean("loggedIn_admin",true);
                                    editor.apply();
                                    //lam mat stack
                                    finishAffinity();
                                    // cho can doi
                                    Intent intent = new Intent(DangNhap.this, QuanTriVien.class);
                                    startActivity(intent);
                                }else {
                                    mat_khau_dang_nhapC.setError("Sai mật khẩu hoặc tài khoản không đúng");
                                    mat_khau_dang_nhapC.requestFocus();
                                }
                            }else {
                                kiemtra3.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()){
                                            sdt_dang_nhapC.setError(null);
                                            String mat_khau_from_DB = snapshot.child(sdt).child("mat_khau").getValue(String.class);

                                            if (mat_khau_from_DB.equals(matKhau)){
                                                sdt_dang_nhapC.setError(null);
                                                Toast.makeText(DangNhap.this, "Chúc mừng bạn đã đăng nhập thành công", Toast.LENGTH_SHORT).show();

                                                // Lưu tên người dùng vào SharedPreferences, sau khi đăng nhập, không thể quay về trang đăng nhập
                                                String tenNguoidung = snapshot.child(sdt).child("ten").getValue(String.class);
                                                SharedPreferences.Editor editor = sharedPreferences_admin.edit();
                                                editor.putString("tennguoidung_admin", tenNguoidung);
                                                editor.putBoolean("loggedIn_admin",true);
                                                editor.apply();
                                                //lam mat stack
                                                finishAffinity();
                                                // cho can doi
                                                Intent intent = new Intent(DangNhap.this, KeToanTruong.class);
                                                startActivity(intent);
                                            }else {
                                                mat_khau_dang_nhapC.setError("Sai mật khẩu hoặc tài khoản không đúng");
                                                mat_khau_dang_nhapC.requestFocus();
                                            }
                                        }else {
                                            sdt_dang_nhapC.setError("Số điện thoại không tồn tại");
                                            sdt_dang_nhapC.requestFocus();

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}