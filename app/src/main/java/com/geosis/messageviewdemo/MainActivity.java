package com.geosis.messageviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
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
            Message message=new Message(
                    new Date(2019-1900,2,26,23,59,59),
                    "威远县",
                    new LatLng(29.46,104.55),
                    7,1,0,"");
            messageList.add(message);
            Message message2=new Message(
                    new Date(2012-1900,5,12,12,28,20),
                    "汶川市",
                    new LatLng(29.46,105),
                    7,1,0,"");
            messageList.add(message2);
            Message message3=new Message(
                    new Date(2019-1900,2,26,23,59,59),
                    "北京市",
                    new LatLng(29.46,104.55),
                    7,1,0,"");
            messageList.add(message3);
            Message message4=new Message(
                    new Date(2019-1900,2,26,23,59,59),
                    "xxx",
                    new LatLng(29.46,104.55),
                    7,1,0,"");
            messageList.add(message4);
            Message message5=new Message(
                    new Date(2019-1900,2,26,23,59,59),
                    "yyy",
                    new LatLng(29.46,104.55),
                    7,1,0,"");
            messageList.add(message5);
            Message message6=new Message(
                    new Date(2019-1900,2,26,23,59,59),
                    "zzz",
                    new LatLng(29.46,104.55),
                    7,1,0,"");
            messageList.add(message6);
            Message message7=new Message(
                    new Date(2019-1900,2,26,23,59,59),
                    "威远县",
                    new LatLng(29.46,104.55),
                    7,1,0,"");
            messageList.add(message7);
            Message message8=new Message(
                    new Date(2019-1900,2,26,23,59,59),
                    "威远县",
                    new LatLng(29.46,104.55),
                    7,1,0,"");
            messageList.add(message8);
        }
    }
}
