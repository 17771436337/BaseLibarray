package cai.test.com.base.presenter;

/**
 * Created by Administrator on 2017/11/27.
 */

public class Presenter<View> {
    View view;
    public View getView(){
        return view;
    }
    public void setView(View view){
        this.view = view;
    }
}
