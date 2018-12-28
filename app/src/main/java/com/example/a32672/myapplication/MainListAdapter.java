package com.example.a32672.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 32672 on 2018/12/26.
 */

public class MainListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<MainItemBean> data;
    private OnItemClickListener itemClickListener;
    public MainListAdapter(Context context, List data){
        this.context=context;
        this.data=data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_list_item,parent,false);
        return new MyViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageView imageView=((MyViewHolder)holder).v.findViewById(R.id.background);
        imageView.setImageResource(data.get(position).getImageId());
        TextView textView=((MyViewHolder)holder).v.findViewById(R.id.item_title);
        textView.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private OnItemClickListener onItemClickListener;
        private View v;
        public MyViewHolder(View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener=onItemClickListener;
            itemView.setOnClickListener(this);
            v=itemView;
        }

        @Override
        public void onClick(View view) {
            if(onItemClickListener!=null)
                onItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener=onItemClickListener;
    }

}
