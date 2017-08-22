package com.imooc.addonitem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

//import cn.bmob.v3.BmobSMS;

/**
 * Created by 文静 on 2017/6/29.
 */

public class Register extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private Button back;
    private Button Save;
    private EditText Name;
    private EditText Password;
    private Person person;
    private TextView checkName;
    private EditText school;
    private ImageView see;
    private boolean nameIsOk = false;
    private boolean canSee = false;
    private int permission;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SysApplication.getInstance().addActivity(this);

        Log.i(">>>>>>>>>>>>>", "in");
        Bmob bmob = new Bmob();

        Bmob.initialize(this, "1d30b11d2f4bc8f7352ea055dcbb1230");
        BmobSMS.initialize(this, "1d30b11d2f4bc8f7352ea055dcbb1230");




        Save = (Button) findViewById(R.id.test_btn);
        Name = (EditText) findViewById(R.id.test_name);
        Password = (EditText) findViewById(R.id.test_mima);
        checkName = (TextView) findViewById(R.id.q_id_register_checkName);
        back = (Button) findViewById(R.id.q_id_register_back);
        school = (EditText) findViewById(R.id.q_id_register_school);
        see = (ImageView) findViewById(R.id.q_id_register_see);

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canSee == false){
                    see.setImageResource(R.drawable.registe_show_password_pressed_icon);
                    canSee = true;
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
                else{
                    see.setImageResource(R.drawable.registe_show_password_icon);
                    canSee = false;
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Enter.class));
            }
        });

        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(">>>>>>>>>>>>", "is changing!!!");
                BmobQuery<Person> query = new BmobQuery<Person>();
                query.addWhereEqualTo("username", Name.getText().toString());
                query.findObjects(new FindListener<Person>() {
                    @Override
                    public void done(List<Person> object, BmobException e) {
                        if (e == null && object.size() != 0) {
                            Log.i("hhh", "e == null !!!!!!!!!!");
                            checkName.setText("用户名已存在");
                            nameIsOk = false;
                            checkName.setVisibility(View.VISIBLE);
                        }
                        else {
                            if(Name.getText().toString() != null) {
                                Log.i("<<<<<<<<<<<<<<<<<<<", "im here");
                                nameIsOk = true;
                                checkName.setText("");
                            }
                            else{
                                checkName.setText("");
                                nameIsOk = false;

                            }

                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("》》》》》》》》》》》》》》", "in it");

                if(nameIsOk == true){
                    Intent intent = new Intent(Register.this, PhoneCheck.class);
                    intent.putExtra("userName", Name.getText().toString());
                    intent.putExtra("password", Password.getText().toString());
                    Log.i("name is" + Name.getText().toString(), "password is" + Password.getText().toString());
                    Name.setText("");
                    Password.setText("");
                    startActivity(intent);
                }
           //     else if(phoneNumberIsOk == false){
           //         Toast.makeText(Register.this, "电话已注册", Toast.LENGTH_SHORT).show();
           //     }
                else if(Name.getText().toString().equals(null) || Password.getText().toString().equals(null)){
                    Toast.makeText(Register.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Register.this, "用户名已注册或为空", Toast.LENGTH_SHORT).show();
                /*BmobUser person = new BmobUser();
                person.setUsername(Name.getText().toString());
                person.setMobilePhoneNumber(Number.getText().toString());
                person.setPassword(Password.getText().toString());
                person.setMobilePhoneNumberVerified(false);
                person.signUp(new SaveListener<Person>() {
                    @Override
                    public void done(Person s, BmobException e) {
                        if(e==null){
                            Toast.makeText(Register.this, "注册成功:", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, PhoneCheck.class);
                            intent.putExtra("userName", Name.getText().toString());
                            intent.putExtra("password", Password.getText().toString());
                            intent.putExtra("phoneNumber", Number.getText().toString());
                            startActivity(intent);
                            Log.i("注册成功了", "yeah");

                        }
                        else{
                            Toast.makeText(Register.this,"注册失败" + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });*/


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
