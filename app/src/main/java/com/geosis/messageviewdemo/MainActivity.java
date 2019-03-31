package com.geosis.messageviewdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amap.api.maps.model.LatLng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private List<Message> messageList=new ArrayList<>();
    private DatabaseOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DatabaseOpenHelper(this,"Earthquake.db",null,1);

        if(!getDatabasePath("Earthquake.db").exists())
            addFakeMessages(); // FIXME:如果数据库不存在，向数据库添加测试消息

        initMessages();

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter adapter=new MessageAdapter(messageList,dbHelper);
        recyclerView.setAdapter(adapter);
    }

    // FIXME:向数据库添加测试消息，最新的消息放在消息顶部
    private void addFakeMessages() {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2019-1900)+",2,26,23,59,59","威远县","29.46","104.55","7","1","0","无备12312312312314如4天4访问法国4提高4覆盖4烦非法4非法" +
                        "3人人日日3日3日3方钢管224高为人父无若翁绕弯儿无若翁热热无若翁绕弯儿翁3非注"});
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2018-1900)+",2,26,23,59,59","汶川市","28","110","8","2","1","备注xxx"});
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2008-1900)+",2,26,23,59,59","北京","32","118","4","1","0","空"});
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2011-1900)+",2,26,23,59,59","xx","29.46","104.55","7","1","0","无备注"});
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2014-1900)+",2,26,23,59,59","yy","29.46","104.55","6","1","0","无备注"});
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2012-1900)+",2,26,23,59,59","zz","29.46","104.55","5","2","0","无备注"});
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2008-1900)+",2,26,23,59,59","dd","29.46","104.55","7","4","0","无备注"});
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2011-1900)+",2,26,23,59,59","eee","29.46","104.55","7","1","0","无备注"});
        db.execSQL("insert into Earthquake (uuid,time, adminregion, latitude, longitude, rank, desciption, infosource, remark) values(?,?,?,?,?,?,?,?,?)",
                new String[]{UUID.randomUUID().toString(),""+(2000-1900)+",2,26,23,59,59","ttt","29.46","104.55","7","2","0","无备注"});
    }

    // 如果数据库不存在，创建数据库；如果数据库存在，则查询数据库初始化消息列表
    private void initMessages(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("Earthquake",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String uuid=cursor.getString(cursor.getColumnIndex("uuid"));
                String temp=cursor.getString(cursor.getColumnIndex("time"));
                String[] temp2 = temp.split(",");
                Date time=new Date(Integer.valueOf(temp2[0]),Integer.valueOf(temp2[1]),Integer.valueOf(temp2[2]),
                        Integer.valueOf(temp2[3]),Integer.valueOf(temp2[4]),Integer.valueOf(temp2[5]));
                String admin_region=cursor.getString(cursor.getColumnIndex("adminregion"));
                double latitude=cursor.getDouble(cursor.getColumnIndex("latitude"));
                double longitude=cursor.getDouble(cursor.getColumnIndex("longitude"));
                int rank=cursor.getInt(cursor.getColumnIndex("rank"));
                int desciption=cursor.getInt(cursor.getColumnIndex("desciption"));
                int infosource=cursor.getInt(cursor.getColumnIndex("infosource"));
                String remark=cursor.getString(cursor.getColumnIndex("remark"));

                Message message=new Message(uuid,time,admin_region,new LatLng(latitude,longitude),rank,desciption,infosource,remark);
                messageList.add(message);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

}
