package com.helloworld.liulian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.helloworld.liulian.Bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    void onBtnRegister(View view)
    {
        User user = new User();
        user.setUsername(findViewById(R.id.id_register_username).toString());
        user.setPassword(findViewById(R.id.id_register_secret).toString());
        /*user.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if(e==null){
                    toast("注册成功:" +s.toString());
                }else{
                    loge(e);
                }
            }
        });*/
    }
}
