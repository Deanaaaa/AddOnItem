package com.imooc.addonitem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by 文静 on 2017/8/8.
 */

public class PhoneCheck extends AppCompatActivity implements View.OnClickListener{

    private EditText Number;
    private Button sendNumber;
    private EditText checkNumber;
    private Button sure;
    private int permission;
    private Intent intent;
    private Button back;
    private TextView textCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_check);
        SysApplication.getInstance().addActivity(this);

        intent = getIntent();

        Bmob.initialize(this, "1d30b11d2f4bc8f7352ea055dcbb1230");
        BmobSMS.initialize(this, "1d30b11d2f4bc8f7352ea055dcbb1230");

        ActivityCompat.requestPermissions(PhoneCheck.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission  = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        }
        permission = ContextCompat.checkSelfPermission(PhoneCheck.this,
                Manifest.permission.READ_PHONE_STATE);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Log.i("已经授权啦", "hhh");
        }
        else{
            Log.i("没有授权", "emmmm");
        }





        Number = (EditText) findViewById(R.id.q_id_phoneCheck_number);
        sendNumber = (Button) findViewById(R.id.q_id_phoneCheck_send);
        checkNumber = (EditText) findViewById(R.id.q_id_phoneCheck_check);
        sure = (Button) findViewById(R.id.q_id_phoneCheck_sure);
        back = (Button) findViewById(R.id.q_id_phoneCheck_back);
        textCheck = (TextView) findViewById(R.id.q_id_phoneCheck_checkFalse);



  //      BmobSMS.requestSMSCode(this, Integer.valueOf(intent.getStringExtra("phoneNumber")).intValue(), );


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneCheck.this, Register.class));
            }
        });


        sendNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Person> query = new BmobQuery<Person>();
                query.addWhereEqualTo("mobilePhoneNumber", Number.getText().toString());
                query.findObjects(new FindListener<Person>() {
                    @Override
                    public void done(List<Person> object, cn.bmob.v3.exception.BmobException e) {
                        if (e == null && object.size() != 0) {
                            Log.i("电话已注册:", Number.getText().toString());
                            Toast.makeText(PhoneCheck.this, "电话已注册", Toast.LENGTH_SHORT);
                        }
                        else {
                            sendNumber.setEnabled(false);
                            BmobSMS.requestSMSCode(PhoneCheck.this,Number.getText().toString() , "check_num", new RequestSMSCodeListener() {
                                @Override
                                public void done(Integer integer, BmobException e) {
                                    Log.i("电话未注册：", "可用");

                                    if(e == null){
                                        Toast.makeText(PhoneCheck.this, "短信已发送", Toast.LENGTH_SHORT).show();
                                        Log.i("bmob", "短信id" + integer);
                                    }
                                    else
                                        Log.i(">>>>>>>>>>>>>>>>>>", e.getMessage().toString());
                                }
                            });
                        }
                    }
                });

            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobSMS.verifySmsCode(PhoneCheck.this, Number.getText().toString(), checkNumber.getText().toString(), new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null){//短信验证码已验证成功
                            textCheck.setText("");
                            Person person = new Person();
                            person.setUsername(intent.getStringExtra("userName"));
                            person.setMobilePhoneNumber(Number.getText().toString());
                            person.setPassword(intent.getStringExtra("password"));
                            //////////////////////////////////////////

                            ////////////////////////////////////////////////
                            Log.i("name is" + intent.getStringExtra("username"), "password is" + intent.getStringExtra("password"));

                            person.signUp(new SaveListener<Person>() {
                                @Override
                                public void done(Person person, cn.bmob.v3.exception.BmobException e) {
                                    if(e == null){
                                        Toast.makeText(PhoneCheck.this, "注册成功:", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(PhoneCheck.this, Enter.class);
                                        checkNumber.setText("");
                                        startActivity(i);
                                    }
                                    else{
                                        Toast.makeText(PhoneCheck.this,"注册失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                        else{
                            Log.i("bmob", "验证失败：code =" + e.getErrorCode()+",msg = " + e.getLocalizedMessage());
                            Toast.makeText(PhoneCheck.this, "验证码错误", Toast.LENGTH_SHORT);
                            sendNumber.setEnabled(true);
                            textCheck.setText("验证码错误！");
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
