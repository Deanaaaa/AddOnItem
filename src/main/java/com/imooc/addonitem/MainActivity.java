package com.imooc.addonitem;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Bmob bmob = new Bmob();

    private LinearLayout q_findLayout;
    private LinearLayout q_discussLayout;
    private LinearLayout q_addLayout;
    private LinearLayout q_orderLayout;
    private LinearLayout q_mineLayout;

    private View q_mineLayout_quit;

    private ImageView q_findImg;
    private ImageView q_discussImg;
    private ImageView q_addImg;
    private ImageView q_orderImg;
    private ImageView q_mineImg;

    private TextView q_findText;
    private TextView q_discussText;
    private TextView q_addText;
    private TextView q_orderText;
    private TextView q_mineText;


    private Fragment q_findFragment;
    private Fragment q_discussFragment;
    private Fragment q_addFragment;
    private Fragment q_orderFragment;
    private Fragment q_mineFragment;

    public LinearLayout q_register_layout;

    //  private LinearLayout q_mine_passwordLinearLayout;

    public Object QueryListener;

    private String [] topname = {"发现", "讨论", "发起凑单", "订单", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SysApplication.getInstance().addActivity(this);




        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.tabber_mine_quit,null);


        q_findLayout = (LinearLayout) findViewById(R.id.q_id_layout_find);
        q_discussLayout = (LinearLayout) findViewById(R.id.q_id_layout_discuss);
        q_addLayout = (LinearLayout) findViewById(R.id.q_id_layout_add);
        q_orderLayout = (LinearLayout) findViewById(R.id.q_id_layout_order);
        q_mineLayout = (LinearLayout) findViewById(R.id.q_id_layout_mine);

        q_register_layout = (LinearLayout) findViewById(R.id.q_register_layout);
        //     q_register_layout.show

        q_findImg = (ImageView) findViewById(R.id.q_id_img_find);
        q_discussImg = (ImageView) findViewById(R.id.q_id_img_discuss);
        q_addImg = (ImageView) findViewById(R.id.q_id_img_add);
        q_orderImg = (ImageView) findViewById(R.id.q_id_img_order);
        q_mineImg = (ImageView) findViewById(R.id.q_id_img_mine);

        q_mineLayout_quit = view. findViewById(R.id.q_id_linearLayout_mine_quit);

        q_findText = (TextView) findViewById(R.id.q_id_text_find);
        q_discussText = (TextView) findViewById(R.id.q_id_text_discuss);
        q_addText = (TextView) findViewById(R.id.q_id_text_add);
        q_orderText = (TextView) findViewById(R.id.q_id_text_order);
        q_mineText = (TextView) findViewById(R.id.q_id_text_mine);


        q_findLayout.setOnClickListener(this);
        q_discussLayout.setOnClickListener(this);
        q_addLayout.setOnClickListener(this);
        q_orderLayout.setOnClickListener(this);
        q_mineLayout.setOnClickListener(this);



        q_mineLayout_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"123",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        q_findImg.setImageDrawable(getResources().getDrawable(R.drawable.find_selected_icon));
        q_findText.setTextColor(Color.parseColor("#3b6967"));
        showFragment(1);


        Bmob.initialize(this,"1d30b11d2f4bc8f7352ea055dcbb1230");


        //setContentView(R.layout.activity_register);
//        startActivity(new Intent(MainActivity.this, Register.class));


    }
    private void showFragment(int i){
        FragmentManager q_fm= getSupportFragmentManager();
        FragmentTransaction q_ft = q_fm.beginTransaction();
        hideFragment(q_ft);

        switch (i){
            case 1:
                if(q_addFragment == null){
                    q_addFragment = new AddFragment();

                }
                break;
            case 2:
                if(q_discussFragment == null){
                    q_discussFragment = new DiscussFragment();
                    q_ft.add(R.id.q_id_frameLayout, q_discussFragment);
                }
                else
                    q_ft.show(q_discussFragment);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                if(q_mineFragment == null){
                    q_mineFragment = new MineFragment();
                    q_ft.add(R.id.q_id_frameLayout, q_mineFragment);
                }
                else
                    q_ft.show(q_mineFragment);
                break;
            default:
                break;
        }
        q_ft.commit();
    }

    private void hideFragment(FragmentTransaction q_ft) {
        if(q_findFragment != null)
            q_ft.hide(q_findFragment);
        if(q_discussFragment != null)
            q_ft.hide(q_discussFragment);
        if(q_addFragment != null)
            q_ft.hide(q_addFragment);
        if(q_orderFragment != null)
            q_ft.hide(q_orderFragment);
        if(q_mineFragment != null)
            q_ft.hide(q_mineFragment);
    }

    @Override
    public void onClick(View v) {
        resetImg();
        resetText();
        switch (v.getId()) {
            case R.id.q_id_layout_find:
                q_findImg.setImageDrawable(getResources().getDrawable(R.drawable.find_selected_icon));
                q_findText.setTextColor(Color.parseColor("#3b6967"));
                showFragment(1);
                break;
            case R.id.q_id_layout_discuss:
                q_discussImg.setImageDrawable(getResources().getDrawable(R.drawable.discuss_selected_icon));
                q_discussText.setTextColor(Color.parseColor("#3b6967"));
                showFragment(2);
                break;
            case R.id.q_id_layout_add:
                q_addImg.setImageDrawable(getResources().getDrawable(R.drawable.add_selected_icon));
                q_addText.setTextColor(Color.parseColor("#3b6967"));
                showFragment(3);
                break;
            case R.id.q_id_layout_order:
                q_orderImg.setImageDrawable(getResources().getDrawable(R.drawable.order_selected_icon));
                q_orderText.setTextColor(Color.parseColor("#3b6967"));
                showFragment(4);
                break;
            case R.id.q_id_layout_mine:
                q_mineImg.setImageDrawable(getResources().getDrawable(R.drawable.mine_selected_icon));
                q_mineText.setTextColor(Color.parseColor("#3b6967"));
                showFragment(5);
                break;
            default:
                break;
        }
    }

    private void resetText() {
        q_findText.setTextColor(Color.parseColor("#ffffff"));
        q_discussText.setTextColor(Color.parseColor("#ffffff"));
        q_addText.setTextColor(Color.parseColor("#ffffff"));
        q_orderText.setTextColor(Color.parseColor("#ffffff"));
        q_mineText.setTextColor(Color.parseColor("#ffffff"));

    }

    private void resetImg() {
        q_findImg.setImageDrawable(getResources().getDrawable(R.drawable.find_icon));
        q_discussImg.setImageDrawable(getResources().getDrawable(R.drawable.discuss_icon));
        q_addImg.setImageDrawable(getResources().getDrawable(R.drawable.add_icon));
        q_orderImg.setImageDrawable(getResources().getDrawable(R.drawable.order_icon));
        q_mineImg.setImageDrawable(getResources().getDrawable(R.drawable.mine_icon));
    }
}
