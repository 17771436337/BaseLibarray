package cai.base.src.com.basetest.test.mvp.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ConversationListFragment extends EaseConversationListFragment implements EaseConversationListFragment.EaseConversationListItemClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConversationListItemClickListener(this);
    }

    @Override
    public void onListItemClicked(EMConversation conversation) {
        ChatActivity.startChat(getActivity(),conversation.conversationId());
    }
}
