package cai.base.src.com.basetest.test.mvp.news;

/**
 * Created by Administrator on 2017/12/6.
 */

public class NewsBean {

    //标题
    private String title;
    //内容
    private String value;

    public NewsBean(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
