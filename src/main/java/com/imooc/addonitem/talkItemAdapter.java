package com.imooc.addonitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 文静 on 2017/8/21.
 */

public class talkItemAdapter extends BaseAdapter {

    private List<talkItemModel> mList;
    private LayoutInflater mInfalter;
    private Context context;

    public talkItemAdapter(Context context, List<talkItemModel> list){
        mList = list;
        this.context = context;
        mInfalter = LayoutInflater.from(context);
    }
    class ViewHolder{
        public ImageView photo;
        public TextView name;
        public TextView talk;
        public TextView time;
        public TextView noSeeNumber;

    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /* View view = mInfalter.inflate(R.layout.every_talk_item, null);
        ImageView photo = (ImageView) view.findViewById(R.id.q_id_every_talk_item_photo);
        TextView name = (TextView) view.findViewById(R.id.q_id_every_talk_item_name);
        TextView talk = (TextView) view.findViewById(R.id.q_id_every_talk_item_talk);
        TextView time = (TextView) view.findViewById(R.id.q_id_every_talk_item_time);
        TextView noSeeNumber = (TextView) view.findViewById(R.id.q_id_every_talk_item_noSeeNumber);
        talkItemModel model = mList.get(position);
        photo.setImageResource(model.getPhoto());
        name.setText(model.getName());
        talk.setText(model.getTalk());
        time.setText(model.getTime());
        noSeeNumber.setText(model.getNoSeeNumber());
        return view;*/
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInfalter.inflate(R.layout.every_talk_item, null);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.q_id_every_talk_item_photo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.q_id_every_talk_item_name);
            viewHolder.talk = (TextView) convertView.findViewById(R.id.q_id_every_talk_item_talk);
            viewHolder.time = (TextView) convertView.findViewById(R.id.q_id_every_talk_item_time);
            viewHolder.noSeeNumber = (TextView) convertView.findViewById(R.id.q_id_every_talk_item_noSeeNumber);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        talkItemModel model = mList.get(position);
        viewHolder.photo.setImageResource(model.getPhoto());
        viewHolder.name.setText(model.getName());
        viewHolder.talk.setText(model.getTalk());
        viewHolder.time.setText(model.getTime());
        viewHolder.noSeeNumber.setText(model.getNoSeeNumber());
        return convertView;
    }
}
