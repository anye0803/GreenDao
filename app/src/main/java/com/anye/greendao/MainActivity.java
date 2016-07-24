package com.anye.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anye.greendao.entity.User;
import com.anye.greendao.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAdd,mDelete,mUpdate,mFind;
    private TextView mContext;
    private User mUser;
    private UserDao mUserDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();
    }

    private void initEvent() {
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFind.setOnClickListener(this);
    }

    private void initView() {
        mContext = (TextView) findViewById(R.id.textView);
        mAdd = (Button) findViewById(R.id.button);
        mDelete = (Button) findViewById(R.id.button2);
        mUpdate = (Button) findViewById(R.id.button3);
        mFind = (Button) findViewById(R.id.button4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                addDate();
                break;
            case R.id.button2:
                deleteDate();
                break;
            case R.id.button3:
                updateDate();
                break;
            case R.id.button4:
                findDate();
                break;
        }
    }

    /**
     * 增加数据
     */
    private void addDate() {
        mUser = new User((long)3,"anye3");
        mUserDao.insert(mUser);//添加一个
        mContext.setText(mUser.getName());
    }

    /**
     * 删除数据
     */
    private void deleteDate() {
        deleteUserById(2);
    }

    /**
     * 根据主键删除User
     *
     * @param id User的主键Id
     */
    public void deleteUserById(long id) {
        mUserDao.deleteByKey(id);
    }

    /**
     * 更改数据
     */
    private void updateDate() {
        mUser = new User((long)2,"anye0803");
        mUserDao.update(mUser);
    }

    /**
     * 查找数据
     */
    private void findDate() {
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName()+",";
        }
        mContext.setText("查询全部数据==>"+userName);
    }
}
