package com.imooc.addonitem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by 文静 on 2017/7/30.
 */

public class Enter extends AppCompatActivity implements View.OnClickListener {

    private Button q_enter_sure;
    private EditText q_enter_name;
    private EditText q_enter_password;
    private Button toRegister;
    private String password;
    private String see = "";
    private int length = 0;
    boolean test = false;
    private LinearLayout enter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);
        SysApplication.getInstance().addActivity(this);


        Bmob bmob = new Bmob();
        Bmob.initialize(this,"1d30b11d2f4bc8f7352ea055dcbb1230");

        GatherPostTable gatherPostTable = new GatherPostTable();

        Person user = Person.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(Enter.this, MainActivity.class));
            Log.i(user.getUsername(), "已经登录过啦");
        }

        q_enter_sure = (Button) findViewById(R.id.q_id_enter_sure);
        q_enter_name = (EditText) findViewById(R.id.q_id_enter_name);
        q_enter_password = (EditText) findViewById(R.id.q_id_enter_password);
        toRegister = (Button) findViewById(R.id.q_id_enter_toRegister);
        enter = (LinearLayout) findViewById(R.id.q_id_enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q_enter_sure.setBackgroundResource(R.drawable.background);
            }
        });


        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Enter.this, Register.class));
            }
        });

        q_enter_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                q_enter_sure.setBackgroundResource(R.drawable.background_);

                Person.loginByAccount(q_enter_name.getText().toString(), q_enter_password.getText().toString(), new LogInListener<Person>() {
                    @Override
                    public void done(Person user, BmobException e){
                        Log.i(q_enter_name.getText().toString(), q_enter_password.getText().toString());
                        if(user != null){
                            Log.i("hhh", "登录成功啦");
                            startActivity(new Intent(Enter.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(Enter.this, "密码错误或用户名不存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });







    }



    @Override
    public void onClick(View v) {

    }

}
