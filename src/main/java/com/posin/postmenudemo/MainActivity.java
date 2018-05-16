package com.posin.postmenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.posin.menumanager.pattern.MenuManager;
import com.posin.menumanager.pattern.model.Dishes;
import com.posin.menumanager.socket.listener.ConnectCallback;
import com.posin.menumanager.socket.listener.SendCallback;
import com.posin.menumanager.utils.DoubleUtils;

import java.util.LinkedHashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MenuManager mUIManager;

    private double sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        try {
            mUIManager = MenuManager.getInstance();
            Log.e(TAG, "init data ... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_init_ui, R.id.btn_add_menu, R.id.btn_add_menu2,
            R.id.btn_sub_menu1, R.id.btn_sub_menu2, R.id.btn_pay, R.id.btn_clear_layout,
            R.id.btn_send_all_menu, R.id.btn_send_all_menu2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_init_ui:
                try {

                    mUIManager.connect(new ConnectCallback() {
                        @Override
                        public void connectSuccess() throws Exception {
//                            mUIManager.initDefaultUI(6, true, new SendCallback() {
//                                @Override
//                                public void success() {
                            Log.e(TAG, "#########     发送成功    ########");
//                                }
//
//                                @Override
//                                public void failure(Exception e) {
//                                    Log.e(TAG, "## 发送失败 ## " + e.getMessage());
//                                }
//                            });
                        }

                        @Override
                        public void connectFailure(Exception e) throws Exception {
                            Log.e(TAG, "连接失败： " + e.getMessage());
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_add_menu:
                try {
//                    mUIManager.addMenu("蒸熊掌", 2, 40.25);
                    for (int i = 0; i < 15; i++) {
                        if (i % 2 == 0) {
                            sum = DoubleUtils.add(sum, 40.275);
                            mUIManager.addMenu("蒸熊掌" + i, 1, 40.275, sum, new SendCallback() {
                                @Override
                                public void success() {
                                    Log.e(TAG, "#########     发送成功    ########");
                                }

                                @Override
                                public void failure(Exception e) {
                                    Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                                }
                            });
                        } else {
                            sum = DoubleUtils.add(sum, 12.275);
                            mUIManager.addMenu("蒸熊掌" + i, 1, 12.275, sum, new SendCallback() {
                                @Override
                                public void success() {
                                    Log.e(TAG, "#########     发送成功    ########");
                                }

                                @Override
                                public void failure(Exception e) {
                                    Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                                }
                            });
                        }
                        Thread.sleep(1000);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_add_menu2:
                try {
                    sum = DoubleUtils.add(sum, 12.275);
                    mUIManager.addMenu("蒸熊掌" + 2, 1, 12.275, sum, new SendCallback() {
                        @Override
                        public void success() {
                            Log.e(TAG, "#########     发送成功    ########");
                        }

                        @Override
                        public void failure(Exception e) {
                            Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_sub_menu1:
                try {
                    for (int i = 14; i >= 0; i--) {
                        if (i % 2 == 0) {
                            sum = DoubleUtils.subtract(sum, 40.275);
                            mUIManager.subsideMenu("蒸熊掌" + i, 1, 40.275, sum, new SendCallback() {
                                @Override
                                public void success() {
                                    Log.e(TAG, "#########     发送成功    ########");
                                }

                                @Override
                                public void failure(Exception e) {
                                    Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                                }
                            });
                        } else {
                            sum = DoubleUtils.subtract(sum, 12.275);
                            mUIManager.subsideMenu("蒸熊掌" + i, 1, 12.275, sum, new SendCallback() {
                                @Override
                                public void success() {
                                    Log.e(TAG, "#########     发送成功    ########");
                                }

                                @Override
                                public void failure(Exception e) {
                                    Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                                }
                            });
                        }
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_sub_menu2:
                try {
                    sum = DoubleUtils.subtract(sum, 12.275);
                    mUIManager.subsideMenu("蒸熊掌" + 2, 1, 12.275, sum, new SendCallback() {
                        @Override
                        public void success() {
                            Log.e(TAG, "#########     发送成功    ########");
                        }

                        @Override
                        public void failure(Exception e) {
                            Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "error: " + e.getMessage());
                }
                break;
            case R.id.btn_pay:
                try {
                    MenuManager.pay(DoubleUtils.add(sum, 35), sum, new SendCallback() {
                        @Override
                        public void success() {
                            Log.e(TAG, "#########     发送成功    ########");
                        }

                        @Override
                        public void failure(Exception e) {
                            Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_clear_layout:
                try {
                    MenuManager.clearDishes(new SendCallback() {
                        @Override
                        public void success() {
                            Log.e(TAG, "#########     发送成功    ########");
                        }

                        @Override
                        public void failure(Exception e) {
                            Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn_send_all_menu:
                try {

                    mUIManager.initDefaultUI(6, true, new SendCallback() {
                        @Override
                        public void success() {
                            Log.e(TAG, "#########     发送成功    ########");
                        }

                        @Override
                        public void failure(Exception e) {
                            Log.e(TAG, "## 发送失败 ## " + e.getMessage());
                        }
                    });
                    MenuManager.clearDishes(new SendCallback() {
                        @Override
                        public void success() {
                            LinkedHashMap<String, Dishes> mMenuMap = new LinkedHashMap<>();

                            for (int i = 0; i < 5; i++) {
                                mMenuMap.put("清蒸鱼" + i, new Dishes("清蒸鱼" + i, 10, 19, 15, true));
                            }
                            try {
                                MenuManager.setMenu(mMenuMap, 900, 200, 1000, 700, 7, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failure(Exception e) {
                            Log.e(TAG, "出错了： " + e.getMessage());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_send_all_menu2:
                try {
                    MenuManager.clearDishes(new SendCallback() {
                        @Override
                        public void success() {
                            LinkedHashMap<String, Dishes> mMenuMap = new LinkedHashMap<>();

                            for (int i = 0; i < 5; i++) {
                                mMenuMap.put("红烧排骨" + i, new Dishes("红烧排骨" + i, 10, 19, 15, true));
                            }
                            try {
                                MenuManager.setMenu(mMenuMap, 900, 200, 1000, 700, 7, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failure(Exception e) {
                            Log.e(TAG, "出错了： " + e.getMessage());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public String[][] getCmd() {
        String[][] cmds = {
                {
                        "action", "clear",
                        "name", "post_view",
                },
                {
                        "action", "add",
                        "parent", "post_view",
                        "name", "layout_1",
                        "type", "linear_layout",
                        "orientation", "vertical",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "31.5",
                        "background", "0xFFCD010C",
                },
                {
                        "action", "add",
                        "parent", "post_view",
                        "name", "layout_2",
                        "type", "linear_layout",
                        "orientation", "horizontal",
                        "gravity", "center",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "4",
                        "padding_left", "10",
                        "padding_right", "10",
                        "background", "0xFFA52A2A",
                },
                {
                        "action", "add",
                        "parent", "post_view",
                        "name", "layout_3",
                        "type", "linear_layout",
                        "orientation", "vertical",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "3",
                },
                ////////////////////////////////////////////收银页面
                {
                        "action", "add",
                        "parent", "layout_1",
                        "name", "layout_1_1",
                        "type", "linear_layout",
                        "orientation", "vertical",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "3",
                },
                {
                        "action", "add",
                        "parent", "layout_1",
                        "name", "layout_1_2",
                        "type", "linear_layout",
                        "orientation", "vertical",
                        "width", "match_parent",
                        "padding_left", "10",
                        "padding_right", "10",
                        "height", "0",
                        "weight", "18",
                },
                {
                        "action", "add",
                        "parent", "layout_1",
                        "name", "layout_1_3",
                        "type", "linear_layout",
                        "orientation", "vertical",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "10.5",
                },
//////////////////////////////////////////////////物品的清单
                {
                        "action", "add",
                        "parent", "layout_1_1",
                        "name", "text1_title",
                        "type", "text",
                        "gravity", "center",
                        "text", "物品清单",
                        "size", "24",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "layout_1_1",
                        "name", "text1_line",
                        "type", "text",
                        "width", "match_parent",
                        "gravity", "bottom",
                        "text", "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -",
                        "size", "18",
                        "color", "0xFFFFFFFF",
                },

                {
                        "action", "add",
                        "parent", "layout_1_3",
                        "name", "layout_1_3_1",
                        "type", "linear_layout",
                        "orientation", "vertical",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "1.5",
                },
                {
                        "action", "add",
                        "parent", "layout_1_3_1",
                        "name", "text2_line",
                        "type", "text",
                        "width", "match_parent",
                        "gravity", "center",
                        "text", "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -",
                        "size", "18",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "layout_1_3",
                        "name", "layout_1_3_2",
                        "type", "linear_layout",
                        "orientation", "vertical",
                        "width", "match_parent",
                        "padding_left", "10",
                        "padding_right", "10",
                        "height", "0",
                        "weight", "9",
                },
/////////////////////////////////////优惠区
                {
                        "action", "add",
                        "parent", "layout_1_3_2",
                        "name", "layout_1_3_2_1",
                        "type", "linear_layout",
                        "orientation", "horizontal",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "1",
                },
                //////////优惠金额
                {
                        "action", "add",
                        "parent", "layout_1_3_2_1",
                        "name", "text_pre_title",
                        "type", "text",
                        "width", "0",
                        "height", "match_parent",
                        "weight", "1",
                        "gravity", "left",
                        "text", "优惠金额：",
                        "size", "24",

                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "layout_1_3_2_1",
                        "name", "text_pre",
                        "type", "text",
                        "width", "0",
                        "height", "match_parent",
                        "weight", "1",
                        "gravity", "right",
                        "text", "0.00元",
                        "size", "24",
                        "color", "0xFFFFFFFF",
                },
                ///////////////////////累计金额
                {
                        "action", "add",
                        "parent", "layout_1_3_2",
                        "name", "layout_1_3_2_2",
                        "type", "linear_layout",
                        "orientation", "horizontal",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "1",
                },

                {
                        "action", "add",
                        "parent", "layout_1_3_2_2",
                        "name", "text_cum_title",
                        "type", "text",
                        "width", "0",
                        "height", "match_parent",
                        "weight", "1",
                        "gravity", "left",
                        "text", "累计金额：",
                        "size", "24",

                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "layout_1_3_2_2",
                        "name", "text_cum",
                        "type", "text",
                        "width", "0",
                        "height", "match_parent",
                        "weight", "1",
                        "gravity", "right",
                        "text", "0.00元",
                        "size", "24",
                        "color", "0xFFFFFFFF",
                },
                ////////////////////已付金额
                {
                        "action", "add",
                        "parent", "layout_1_3_2",
                        "name", "layout_1_3_2_3",
                        "type", "linear_layout",
                        "orientation", "horizontal",
                        "width", "match_parent",
                        "height", "0",
                        "weight", "1",
                },

                {
                        "action", "add",
                        "parent", "layout_1_3_2_3",
                        "name", "text_paid_title",
                        "type", "text",
                        "width", "0",
                        "height", "match_parent",
                        "weight", "1",
                        "gravity", "left",
                        "text", "已付金额：",
                        "size", "24",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "layout_1_3_2_3",
                        "name", "text_paid",
                        "type", "text",
                        "width", "0",
                        "height", "match_parent",
                        "weight", "1",
                        "gravity", "right",
                        "text", "0.00元",
                        "size", "24",
                        "color", "0xFFFFFFFF",
                },


////////////////////////////////item_clum
                {
                        "action", "add",
                        "parent", "layout_1_2",
                        "name", "item_clum",
                        "type", "linear_layout",
                        "orientation", "horizontal",
                        "width", "match_parent",
                        "height", "wrap_content",
                },
                {
                        "action", "add",
                        "parent", "item_clum",
                        "name", "item1",
                        "type", "text",
                        "gravity", "left",
                        "width", "0",
                        "weight", "1",
                        "height", "wrap_content",
                        "text", "品名",
                        "size", "18",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "item_clum",
                        "name", "item2",
                        "type", "text",
                        "gravity", "center",
                        "width", "0",
                        "weight", "1",
                        "height", "wrap_content",
                        "text", "数量 * 单价",
                        "size", "18",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "item_clum",
                        "name", "item3",
                        "type", "text",
                        "gravity", "right",
                        "width", "0",
                        "weight", "1",
                        "height", "wrap_content",
                        "text", "小计",
                        "size", "18",
                        "color", "0xFFFFFFFF",
                },
///////////////////////////找零
                {
                        "action", "add",
                        "parent", "layout_2",
                        "name", "text_odd_title",
                        "type", "text",
                        "gravity", "center",
                        "width", "0",
                        "height", "match_parent",
                        "weight", "1",
                        "text", "找 零：",
                        "size", "28",
                        "color", "0xFFFFFFFF",
                },
                {
                        "action", "add",
                        "parent", "layout_2",
                        "name", "text_odd",
                        "type", "text",
                        "gravity", "center",
                        "width", "0",
                        "height", "match_parent",
                        "weight", "1",
                        "text", "0.00元",
                        "size", "28",
                        "color", "0xFFFFFFFF",
                },

                {
                        "action", "add",
                        "parent", "layout_3",
                        "name", "text_cue",
                        "type", "text",
                        "gravity", "center",
                        "width", "match_parent",
                        "height", "match_parent",
                        "text", "请仔细核对好自己购买的东西哦，亲！",
                        "size", "18",
                        "color", "0xFF000000",
                },
        };

        return cmds;
    }


}
