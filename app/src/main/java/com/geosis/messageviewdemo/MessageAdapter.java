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
        TextView messageTime;
        TextView messageContent;
        TextView moreInfo;

        public ViewHolder(View view){
            super(view);
            messageTime=(TextView)view.findViewById(R.id.time);
            messageContent=(TextView)view.findViewById(R.id.content);
            moreInfo=(TextView) view.findViewById(R.id.moreInfo);
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
        final ViewHolder holder=new ViewHolder(view);

        // 点击子项启动活动，并传递Message对象
        holder.moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=view.getContext();
                Intent intent=new Intent(context,MapActivity.class);

                int position=holder.getAdapterPosition();
                Message message=mMessageList.get(position);
                intent.putExtra("message_item",message);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    // 用于对RecyclerView子项数据赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=mMessageList.get(position);
        holder.messageTime.setText(message.getM_time());
        holder.messageContent.setText(message.getM_admin_region()+"发生"+
                message.getM_rank()+"级地震，震源经度"+
                message.getM_location().longitude+"，纬度"+
                message.getM_location().latitude);
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
