package com.posin.postmenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.posin.menumanager.pattern.MenuManager;
import com.posin.menumanager.pattern.model.Dishes;
import com.posin.menumanager.socket.listener.Callback;
import com.posin.menumanager.socket.listener.SendCallback;
import com.posin.menumanager.utils.DoubleUtils;
import com.posin.menumanager.utils.LogUtils;

import java.util.LinkedHashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MenuManager mUIManager;

    private boolean connect_success = false;
    LinkedHashMap<String, Dishes> mMenuMaps;
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
            mMenuMaps = new LinkedHashMap<>();
            mUIManager = MenuManager.getInstance();
            mUIManager.init(11, true, new Callback() {
                @Override
                public void connectSuccess() {
                    connect_success = true;
                }

                @Override
                public void connectFailure(Exception e) {
                    connect_success = false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_add_1, R.id.btn_add_2, R.id.btn_add_3, R.id.btn_add_4, R.id.btn_add_5,
            R.id.btn_add_6, R.id.btn_add_7, R.id.btn_sub_1, R.id.btn_sub_2, R.id.btn_sub_3,
            R.id.btn_sub_4, R.id.btn_sub_5, R.id.btn_sub_6, R.id.btn_sub_7, R.id.btn_pay})
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
                    //Demo中收款金额等为固定值，方便测试
                    MenuManager.getInstance().setMenu(mMenuMaps, 1.0, sum,
                            DoubleUtils.add(sum, 201), 200, new SendCallback() {
                                @Override
                                public void success() {
                                    Log.e(TAG, "支付成功 。。。");
                                    mMenuMaps.clear();
                                }

                                @Override
                                public void failure(Exception e) {
                                    Log.e(TAG, "支付失败 。。。");
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

    public void addFood(String name) {
        try {

            if (!connect_success) {
                Toast.makeText(this, "没有连接广告系统，请重新初始化SDK", Toast.LENGTH_SHORT).show();
                return;
//                throw new Exception("connect failure ,please connect again ... ");
            }
            sum += 12;
            if (mMenuMaps.containsKey(name)) {


                Dishes dishes = mMenuMaps.get(name);
                dishes.setAmount(dishes.getAmount() + 1);
                dishes.setSubtotal(dishes.getSubtotal() + 12);
                mMenuMaps.remove(name);
                mMenuMaps.put(name, dishes);

            } else {
                mMenuMaps.put(name, new Dishes(name, 1, 12, 12, true));
            }

            Log.e(TAG, "menu size: " + mMenuMaps.size());
            try {
                MenuManager.getInstance().setMenu(mMenuMaps, 0.00, sum, 0.00, 0.00, new SendCallback() {
                    @Override
                    public void success() {
                    }

                    @Override
                    public void failure(Exception e) {
                        LogUtils.Error(TAG, "发送菜单失败： " + e.getMessage());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
