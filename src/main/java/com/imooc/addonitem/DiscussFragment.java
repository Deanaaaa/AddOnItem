package com.imooc.addonitem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.bmob.v3.Bmob;

/**
 * Created by 文静 on 2017/3/24.
 */

public class DiscussFragment extends Fragment {

    Bmob bmob = new Bmob();
    private ListView orders;
    private talkItemAdapter mAdapter;
    private Vector<String> mVector;
    private Person person;
    private List<talkItemModel> mList ;
    private GatherPostTable mGatherPostTable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        Bmob.initialize(this.getContext(),"1d30b11d2f4bc8f7352ea055dcbb1230");
        orders = (ListView) view.findViewById(R.id.q_id_tab2_orders);
        person = Person.getCurrentUser();
        mVector = person.getOrderNumber();
        mList = new ArrayList<>();
        for(int i = 0; i < 5; i ++){
            mList.add(new talkItemModel(R.mipmap.ic_launcher, "name", "talk", "time", "noSeeNumbere"));
        }
        mAdapter = new talkItemAdapter(this.getContext(), mList);
        orders.setAdapter(mAdapter);
        Log.i("show", "adapter");
        return view;
    }
}
