package com.chen.late_eec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DataBaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DataBaseManager(){

    }

    public DataBaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder{
        private static final DataBaseManager INSTANCE = new DataBaseManager();

    }

    public static DataBaseManager getIsntance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper =  new ReleaseOpenHelper(context,"fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }


    public final UserProfileDao getDao(){
         return mDao;
    }
}
