package com.imooc.addonitem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by 文静 on 2017/3/21.
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.q_id_img_tab5_back_icon)
    ImageView qIdImgTab5BackIcon;
    @BindView(R.id.q_id_text_tab5_mine)
    TextView qIdTextTab5Mine;
    @BindView(R.id.q_id_layout_mine)
    LinearLayout qIdLayoutMine;
    @BindView(R.id.q_id_img_tab5_mine_head)
    ImageView qIdImgTab5MineHead;
    @BindView(R.id.q_id_text_tab5_name)
    TextView qIdTextTab5Name;
    @BindView(R.id.q_id_img_tab5_mine_phone)
    ImageView qIdImgTab5MinePhone;
    @BindView(R.id.q_id_text_tab5_mine_phone)
    TextView qIdTextTab5MinePhone;
    @BindView(R.id.q_id_img_tab5_mine_phone_more)
    ImageView qIdImgTab5MinePhoneMore;
    @BindView(R.id.q_id_img_tab5_mine_password)
    ImageView qIdImgTab5MinePassword;
    @BindView(R.id.q_id_text_tab5_mine_password)
    TextView qIdTextTab5MinePassword;
    @BindView(R.id.q_id_img_tab5_mine_password_more)
    ImageView qIdImgTab5MinePasswordMore;
    @BindView(R.id.q_id_linearLayout_mine_password)
    LinearLayout qIdLinearLayoutMinePassword;
    @BindView(R.id.q_id_img_tab5_mine_address)
    ImageView qIdImgTab5MineAddress;
    @BindView(R.id.q_id_text_tab5_mine_address)
    TextView qIdTextTab5MineAddress;
    @BindView(R.id.q_id_img_tab5_mine_address_more)
    ImageView qIdImgTab5MineAddressMore;
    @BindView(R.id.q_id_img_tab5_mine_pass_order)
    ImageView qIdImgTab5MinePassOrder;
    @BindView(R.id.q_id_text_tab5_mine_pass_order)
    TextView qIdTextTab5MinePassOrder;
    @BindView(R.id.q_id_img_tab5_mine_pass_order_more)
    ImageView qIdImgTab5MinePassOrderMore;
    @BindView(R.id.q_id_img_tab5_mine_information)
    ImageView qIdImgTab5MineInformation;
    @BindView(R.id.q_id_text_tab5_mine_information)
    TextView qIdTextTab5MineInformation;
    @BindView(R.id.q_id_img_tab5_mine_information_more)
    ImageView qIdImgTab5MineInformationMore;
    @BindView(R.id.q_id_img_tab5_mine_us)
    ImageView qIdImgTab5MineUs;
    @BindView(R.id.q_id_text_tab5_mine_us)
    TextView qIdTextTab5MineUs;
    @BindView(R.id.q_id_img_tab5_mine_us_more)
    ImageView qIdImgTab5MineUsMore;
    @BindView(R.id.q_id_img_tab5_mine_quit)
    ImageView qIdImgTab5MineQuit;
    @BindView(R.id.q_id_text_tab5_mine_quit)
    TextView qIdTextTab5MineQuit;
    @BindView(R.id.q_id_img_tab5_mine_quit_more)
    ImageView qIdImgTab5MineQuitMore;
    @BindView(R.id.q_id_linearLayout_mine_quit)
    LinearLayout qxtIdLinearLayoutMineQuit;
    Unbinder unbinder;


    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab5, null);
        unbinder = ButterKnife.bind(this, view);
        Bmob.initialize(this.getContext(),"1d30b11d2f4bc8f7352ea055dcbb1230");
        Person p = Person.getCurrentUser();
        qIdTextTab5Name.setText(p.getUsername());
        return view;


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.q_id_layout_change, R.id.q_id_layout_mine, R.id.q_id_linearLayout_mine_password, R.id.q_id_linearLayout_mine_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.q_id_layout_change:
                BmobUser.logOut();   //清除缓存用户对象
                BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                System.exit(0);
                break;
            case R.id.q_id_layout_mine:
                break;
            case R.id.q_id_linearLayout_mine_password:
                Person person = Person.getCurrentUser();
                Log.i( person.getUsername(), ">>>>>>>>>>>>>>.");

                person.addOrderNumber("i am ordernumber");
                Log.i("ordernumber is", person.getOrderNumber().get(person.orderNumber.size() - 1));
                break;
            case R.id.q_id_linearLayout_mine_quit:
                SysApplication.getInstance().exit();
                break;
            case R.id.q_id_linearLayout_mine_address:

                break;


        }
    }
}
