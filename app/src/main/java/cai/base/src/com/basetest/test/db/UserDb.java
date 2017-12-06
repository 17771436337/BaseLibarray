package cai.base.src.com.basetest.test.db;

import cai.base.src.com.basetest.test.App;
import cai.test.com.base.annotation.Column;
import cai.test.com.base.annotation.Table;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/11/24.
 */
@Table(name = "Base_User")
public class UserDb {

    @Column(name = "_id", isId = true)
    private long id;//用户主键

    @Column(name = "_name")
    private String account;//用户账号

    @Column(name = "_password")
    private String password;//用户密码

    @Column(name = "_TimeCreation")
    private long timeCreation;//创建时间


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(long timeCreation) {
        this.timeCreation = timeCreation;
    }

    /**注册相关代码进行代码数据库查询和保存*/
    public static boolean  register(UserDb bean){

        try {
            UserDb first = x.db().selector(UserDb.class).where("_name", "=", bean.account).or("_password", "=", bean.password).findFirst();
            if (first != null){
                return false;
            }else{
               return x.db().saveBindingId(bean);
            }
        }catch (Exception e){
            return false;
        }
    }

    /**登陆，查询数据库数据，进行判断*/
    public static long  login(String account,String password){
        try {
            UserDb first = x.db().selector(UserDb.class).where("_name", "=", account).and("_password", "=", password).findFirst();
            if (first != null){
                return first.getId();
            }else{
                return -1;
            }
        }catch (Exception e){
            return -1;
        }

    }




}
