package com.helloworld.liulian;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.helloworld.liulian.Bean.User;
import com.helloworld.liulian.model.UserModel;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends FragmentActivity {

    EditText login_username = null;
    EditText login_secret = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_username = (EditText) findViewById(R.id.id_login_username);
        login_secret = (EditText) findViewById(R.id.id_login_secret);
    }

    public void OnLoginClick(View view)
    {
        UserModel.getInstance().login(login_username.getText().toString(), login_secret.getText().toString(), new LogInListener() {

            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {
                    //User user =(User)o;
                    //BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
                    //startActivity(MainActivity.class, null, true);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //响应“还没有注册”跳转
    void goRegisterActivity(View view)
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
