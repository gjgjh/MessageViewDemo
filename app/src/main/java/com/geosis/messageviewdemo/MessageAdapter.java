package com.geosis.messageviewdemo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mMessageList;
    private DatabaseOpenHelper mdbHelper;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View messageView;
        TextView messageTime;
        TextView messageContent;
        TextView moreInfo;

        public ViewHolder(View view){
            super(view);
            messageView=view;
            messageTime=(TextView)view.findViewById(R.id.time);
            messageContent=(TextView)view.findViewById(R.id.content);
            moreInfo=(TextView) view.findViewById(R.id.moreInfo);
        }
    }

    public MessageAdapter(List<Message> messageList,DatabaseOpenHelper dbHelper){
        mMessageList=messageList;
        mdbHelper=dbHelper;
    }

    // 用于创建ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
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

        // 长按发送或删除某个子项
        holder.messageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Context context=view.getContext();

                // 把自定义的布局设置到dialog中
                final AlertDialog dialog = new AlertDialog.Builder(context).create();
                View view= LayoutInflater.from(context).inflate(R.layout.altert_dialog,null);

                // 设置dialog在屏幕底部，并自底向上弹出动画
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.DialogAnimation);
                dialog.setView(view);
                dialog.show();

                // 设置点击事件
                TextView send=(TextView)view.findViewById(R.id.send);
                TextView delete=(TextView) view.findViewById(R.id.delete);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Send isn't implemented yet",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position=holder.getAdapterPosition();
                        SQLiteDatabase db=mdbHelper.getWritableDatabase();
                        String uuid=mMessageList.get(position).getM_uuid();
                        db.delete("Earthquake","uuid=?",new String[]{uuid}); // 从数据库中删除
                        mMessageList.remove(position); // 从消息列表中删除
                        notifyItemRemoved(position); // 通知界面更新
                        notifyItemRangeChanged(position,mMessageList.size());

                        Toast.makeText(context,"Message Deleted",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                return true;
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
