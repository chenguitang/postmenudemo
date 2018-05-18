package com.posin.postmenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.posin.menudevices.command.MenuManager;
import com.posin.menudevices.command.ConnectCallback;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private ArrayList<com.posin.menudevices.constant.Dishes> listDishes;
    private boolean is_connect_successful = false;
    private double sum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        try {
            listDishes = new ArrayList<>();
            MenuManager.init(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_add_1, R.id.btn_add_2, R.id.btn_add_3, R.id.btn_add_4, R.id.btn_add_5,
            R.id.btn_add_6, R.id.btn_add_7, R.id.btn_sub_1, R.id.btn_sub_2, R.id.btn_sub_3,
            R.id.btn_sub_4, R.id.btn_sub_5, R.id.btn_sub_6, R.id.btn_sub_7, R.id.btn_pay,
            R.id.btn_clear, R.id.btn_init})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_1:
                addFood("黄焖鸡");
                break;
            case R.id.btn_add_2:
                addFood("黄焖牛");
                break;
            case R.id.btn_add_3:
                addFood("黄焖猪");
                break;
            case R.id.btn_add_4:
                addFood("黄焖马");
                break;
            case R.id.btn_add_5:
                addFood("黄焖兔");
                break;
            case R.id.btn_add_6:
                addFood("黄焖龙");
                break;
            case R.id.btn_add_7:
                addFood("黄焖123");
                break;
            case R.id.btn_sub_1:
                addFood("黄焖鸭");
                break;
            case R.id.btn_sub_2:
                addFood("黄焖羊");
                break;
            case R.id.btn_sub_3:
                addFood("黄焖狗");
                break;
            case R.id.btn_sub_4:
                addFood("黄焖蛇");
                break;
            case R.id.btn_sub_5:
                addFood("黄焖鼠");
                break;
            case R.id.btn_sub_6:
                addFood("黄焖猴");
                break;
            case R.id.btn_sub_7:
                addFood("黄焖456");
                break;
            case R.id.btn_pay:
                try {
                    if (!is_connect_successful) {
                        Toast.makeText(this, "连接失败，请重新初化连接", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    MenuManager.getInstance(this).pay(78, 1545, 1228.00, 250.0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_clear:
                try {
                    if (!is_connect_successful) {
                        Toast.makeText(this, "连接失败，请重新初化连接", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listDishes.clear();
                    MenuManager.getInstance(this).clearMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
            case R.id.btn_init:
                try {
                    listDishes = new ArrayList<>();
                    MenuManager.getInstance(this).initConnect(10, true, new ConnectCallback() {
                        @Override
                        public void success() {
                            is_connect_successful = true;
                            Log.e(TAG, "*************************************");
                            Log.e(TAG, "****   连接成功   ****");
                            Log.e(TAG, "*************************************");
                        }

                        @Override
                        public void failure() {
                            is_connect_successful = false;
                            Log.e(TAG, "*************************************");
                            Log.e(TAG, "****   连接失败   ****");
                            Log.e(TAG, "*************************************");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 增加菜品
     * @param name 菜品
     */
    public void addFood(String name) {
        try {
            if (!is_connect_successful) {
                Toast.makeText(this, "连接失败，请重新初化连接", Toast.LENGTH_SHORT).show();
                return;
            }
            listDishes.add(new com.posin.menudevices.constant.Dishes(name, 1, 20, 25));
            MenuManager.getInstance(this).sendMenu(listDishes, 587);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
