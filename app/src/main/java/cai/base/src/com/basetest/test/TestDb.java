package cai.base.src.com.basetest.test;

import cai.test.com.base.annotation.Column;
import cai.test.com.base.annotation.Table;

/**
 * Created by Administrator on 2017/11/11.
 */
@Table(name = "deta")
public class TestDb {

    @Column(isId = true,name = "Id")
    private long id;

    @Column(name = "Date")
    private long date;

    @Column(name = "test")
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
