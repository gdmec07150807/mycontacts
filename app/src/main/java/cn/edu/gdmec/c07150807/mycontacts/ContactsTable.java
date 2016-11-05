package cn.edu.gdmec.c07150807.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by asus-c on 2016/10/24.
 */
public class ContactsTable {
    //数据表名常量
    private final static String TABLENAME="contactsTable";
    //声明数据库对象
    private  MyDB db;
    //构造方法
    public ContactsTable(Context context){
        //创建MyDB对象 context很重要
        db=new MyDB(context);
        //如果数据表不存在就新建数据表
        if (!db.isTableExits(TABLENAME)){
            String createTableSql="CREATE TABLE IF NOT EXISTS"+TABLENAME+"(id_DB integer"+
                    "primary key AUTOINCREMENT,"+
                    User.Name+"VARCHAR,"+
                    User.MOBILE+"VARCHAR,"+
                    User.QQ+"VARCHAR,"+
                    User.DANWEI+"VARCHAR,"+
                    User.ADDRESS+"VARCHAR)";
            db.createTable(createTableSql);
        }
    }
    //增加数据
    public boolean addData(User user) {
        //创建ContentValue对象用于保存数据
        ContentValues values=new ContentValues();
        //contenvalue赋值
        values.put(User.Name,user.getName());
        values.put(User.MOBILE,user.getMobile());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.QQ,user.getQq());
        values.put(User.ADDRESS,user.getAddress());
        //保存数据
        return db.save(TABLENAME,values);
    }
    //step2
    public User getUserByID(int id){
        Cursor cursor=null;
        User temp=new User();
        try{
            cursor=db.find("select * from "+TABLENAME+" where "+"  id_DB=?",
                    new  String[]{id+""});
            //游标开始时指向-1，movToNext方法将游标移动到了下一行。即第一行。
            cursor.moveToNext();
            temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
            temp.setName(cursor.getString(cursor.getColumnIndex(User.Name)));
            temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
            temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
            temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
            temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
            Log.d("aa",temp.getName());
            return temp;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        return null;
    }
    public boolean updateUser(User user){
        //创建ContentValues对象用于保存数据
        ContentValues values=new ContentValues();
        //contentvalue赋值
        values.put(User.Name,user.getName());
        values.put(User.MOBILE,user.getMobile());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.QQ,user.getQq());
        values.put(User.ADDRESS,user.getAddress());
        //执行数据库更新
        return db.update(TABLENAME,values," id_DB=?", new String[]{user.getId_DB()+""});


    }

}
