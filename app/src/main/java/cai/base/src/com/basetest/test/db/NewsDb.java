package cai.base.src.com.basetest.test.db;

import android.text.TextUtils;

import java.util.List;

import cai.test.com.base.annotation.Column;
import cai.test.com.base.annotation.Table;
import cai.test.com.base.db.sqlite.SqlInfo;
import cai.test.com.base.db.table.DbModel;
import cai.test.com.base.ex.DbException;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/12/4.
 */
@Table(name = "Base_News_List")
public class NewsDb {


    @Column(name = "_id", isId = true)
    private long id;//用户主键

    /**
     * uniquekey : 62ce9d81897228576929ac92280724d5
     * title : 外交部驻港特派员公署助力驻港外商共享新时代中国发展新机遇
     * date : 2017-11-08 16:33
     * category : 头条
     * author_name : 中国日报网
     * url : http://mini.eastday.com/mobile/171108163353486.html
     * thumbnail_pic_s : http://05.imgmini.eastday.com/mobile/20171108/20171108163353_1494c731255e5e94325549bab768659d_3_mwpm_03200403.jpg
     * thumbnail_pic_s02 : http://05.imgmini.eastday.com/mobile/20171108/20171108163353_1494c731255e5e94325549bab768659d_8_mwpm_03200403.jpg
     * thumbnail_pic_s03 : http://05.imgmini.eastday.com/mobile/20171108/20171108163353_1494c731255e5e94325549bab768659d_6_mwpm_03200403.jpg
     */
    @Column(name = "news_key")
    private String uniquekey;
    @Column(name = "news_title")
    private String title;
    @Column(name = "news_time")
    private String date;
    @Column(name = "news_category")
    private String category;
    @Column(name = "news_name")
    private String author_name;
    @Column(name = "news_url")
    private String url;
    @Column(name = "news_thumbnail_1")
    private String thumbnail_pic_s;
    @Column(name = "news_thumbnail_2")
    private String thumbnail_pic_s02;
    @Column(name = "news_thumbnail_3")
    private String thumbnail_pic_s03;


    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }


    /**去重数据保存*/
    public static void save(List<NewsDb> list){
        for (NewsDb bean : list) {
            try {
                //查询是否存在该数据
                NewsDb data = x.db().selector(NewsDb.class)
                        .where("news_key", "=", bean.getUniquekey())
                        .and("news_title", "=", bean.getTitle())
                        .and("news_category","=",bean.getCategory())
                        .and("news_time", "=", bean.getDate())
                        .findFirst();

                if (data == null) {//如果不存在，则保存该数据
                    x.db().save(bean);
                } else {//如果存在，则更新数据
                    x.db().saveOrUpdate(bean);
                }

            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 分页查询数据
     * @param page
     *      当前页数
     * @param size
     *      每页个数
     * @return
     */
    public static List<NewsDb> getData(String category,int page,int size){
        try {
            List<NewsDb>  data = null;
            if (!TextUtils.isEmpty(category)) {
                data = x.db().selector(NewsDb.class).where("news_category", "=", category).limit(size).offset(size * page).findAll();
            }else{
                data = x.db().selector(NewsDb.class).limit(size).offset(size * page).findAll();
            }
            return data;
        } catch (DbException e) {
            e.printStackTrace();
            return null;
        }



    }
}
