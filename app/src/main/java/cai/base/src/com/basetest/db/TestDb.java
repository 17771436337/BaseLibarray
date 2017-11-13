package cai.base.src.com.basetest.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

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
