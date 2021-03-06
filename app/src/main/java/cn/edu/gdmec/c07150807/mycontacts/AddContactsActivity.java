package cn.edu.gdmec.c07150807.mycontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactsActivity extends AppCompatActivity {
    //声明界面控制属性
    private EditText nameEditText;
    private EditText mobileEditText;
    private EditText qqEditText;
    private EditText danweiEditText;
    private EditText  addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        setTitle("添加联系人");
        //获取界面控制实例
        nameEditText=(EditText)findViewById(R.id.name);
        danweiEditText= (EditText) findViewById(R.id.danwei);
        mobileEditText= (EditText) findViewById(R.id.mobile);
        qqEditText= (EditText) findViewById(R.id.qq);
        addressEditText= (EditText) findViewById(R.id.address);
    }
    //创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,menu.NONE,"保存");
        menu.add(Menu.NONE,2,menu.NONE,"返回");

        return super.onCreateOptionsMenu(menu);
    }
    //选项菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                if (!nameEditText.getText().toString().equals("")){
                    //新建User对象
                    User user=new User();
                    //User对象赋值
                    user.setName(nameEditText.getText().toString());
                    user.setMobile(mobileEditText.getText().toString());
                    user.setDanwei(danweiEditText.getText().toString());
                    user.setQq(qqEditText.getText().toString());
                    user.setAddress(addressEditText.getText().toString());
                    //创建数据表对象
                    ContactsTable ct=new ContactsTable(AddContactsActivity.this);
                    //数据表增加数据
                    if (ct.addData(user)){
                        Toast.makeText(AddContactsActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                       finish();
                    }else {
                        Toast.makeText(AddContactsActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(AddContactsActivity.this,"请先输入数值",Toast.LENGTH_SHORT).show();

                }break;
        }
        return super.onOptionsItemSelected(item);
    }
}
