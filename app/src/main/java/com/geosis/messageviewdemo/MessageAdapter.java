package com.geosis.messageviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mMessageList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView messageContent;
        TextView messageLocation;
        TextView messageDate;
        ImageView moreInfo;

        public ViewHolder(View view){
            super(view);
            messageContent=(TextView)view.findViewById(R.id.content_of_markpoint);
            messageLocation=(TextView)view.findViewById(R.id.description_of_location);
            messageDate=(TextView)view.findViewById(R.id.time_of_pickup);
            moreInfo=(ImageView)view.findViewById(R.id.more_info);
        }
    }

    public MessageAdapter(List<Message> messageList){
        mMessageList=messageList;
    }

    // 用于创建ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_point_information,parent,false);
        ViewHolder holder=new ViewHolder(view);
        holder.moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=view.getContext();
                Intent intent=new Intent(context,MapActivity.class);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    // 用于对RecyclerView子项数据赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=mMessageList.get(position);
        holder.messageContent.setText(message.getContent());
        holder.messageLocation.setText(message.getLocation());
        holder.messageDate.setText(message.getYear()+"年"+message.getMonth()+"月"+message.getDay()+"日");
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
