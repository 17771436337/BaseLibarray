package cai.base.src.com.basetest.test.mvp.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import cai.base.src.com.basetest.R;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ContactListFragment extends EaseContactListFragment implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        super.initView();
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_chat_contacts_header, null);

        header.findViewById(R.id.add_contact_item).setOnClickListener(this);
        header.findViewById(R.id.application_item).setOnClickListener(this);
        listView.addHeaderView(header);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EaseUser user = (EaseUser)listView.getItemAtPosition(position);
                if (user != null) {
                    String username = user.getUsername();
                    // demo中直接进入聊天页面，实际一般是进入用户详情页
                    ChatActivity.startChat(getActivity(),username);

                }
            }
        });


    }

    @Override
    protected void setUpView() {
        super.setUpView();
        x.task().run(new Runnable() {
                @Override
                public void run() {
                    try {
                        Map<String, EaseUser> contactsMap = new HashMap<>();
                        List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();

                        for (String userName : usernames){
                            contactsMap.put(userName,new EaseUser(userName));
                        }
                        setContactsMap(contactsMap);
                        refresh();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_contact_item://添加朋友
                Intent it = new Intent(getActivity(),AddContactActivity.class);
                startActivity(it);
                break;
            case R.id.application_item://添加朋友
                Intent it1 = new Intent(getActivity(),NewFriendsMsgActivity.class);
                startActivity(it1);
                break;
        }
    }
}
