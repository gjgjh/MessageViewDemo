package com.geosis.messageviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Message> messageList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMessages();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter adapter=new MessageAdapter(messageList);
        recyclerView.setAdapter(adapter);
    }

    private void initMessages(){
        for(int i=0;i<2;++i){
            Message message=new Message("发生地震3.2级，经度120，维度32","北京北京大学",2019,3,27);
            messageList.add(message);
            Message message2=new Message("发生地震3.2级，经度120，维度32","汶川xx村",2012,2,1);
            messageList.add(message2);
            Message message3=new Message("发生地震3.2级，经度120，维度32","新疆yy村",2013,12,7);
            messageList.add(message3);
            Message message4=new Message("发生地震3.2级，经度120，维度32","江西zz路",2019,3,27);
            messageList.add(message4);
            Message message5=new Message("发生地震3.2级，经度120，维度32","汶川xx村",2012,2,1);
            messageList.add(message5);
            Message message6=new Message("发生地震3.2级，经度120，维度32","yy村",2013,12,7);
            messageList.add(message6);
            Message message7=new Message("发生地震3.2级，经度120，维度32","北京大学",2019,3,27);
            messageList.add(message7);
            Message message8=new Message("发生地震3.2级，经度120，维度32","汶川xx村",2012,2,1);
            messageList.add(message8);
            Message message9=new Message("发生地震3.2级，经度120，维度32","yy村",2013,12,7);
            messageList.add(message9);
        }
    }
}
