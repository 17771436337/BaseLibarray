package cai.base.src.com.basetest.test.mvp.chat;

import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import cai.base.src.com.basetest.R;
import cai.test.com.base.annotation.ViewInject;
import cai.test.com.base.view.fragments.BaseSpaceFragment;
import cai.test.com.base.view.widget.toolbar.TitleToolbar;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ChatMainFragment extends BaseSpaceFragment {

    @ViewInject(R.id.tabView)
    private TabView tabView;

    List<TabViewChild> list ;
    @Override
    public void init() {

        list = new ArrayList<>();
        list.add(new TabViewChild(R.mipmap.ic_launcher,R.mipmap.ic_launcher,"会话",new ConversationListFragment()));
        list.add(new TabViewChild(R.mipmap.ic_launcher,R.mipmap.ic_launcher,"联系人",new ContactListFragment()));
        list.add(new TabViewChild(R.mipmap.ic_launcher,R.mipmap.ic_launcher,"设置",new EaseContactListFragment()));

        tabView.setTabViewDefaultPosition(1);
        tabView.setTabViewChild(list,getChildFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {

            }
        });
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_mian_chat;
    }
}
