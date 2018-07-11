package cn.droidlover.xdroid.expand.db;

import android.content.Context;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * Created by yuanlu on 2016-10-27.
 */
public class DBManager {
    private static String sDbName = "xutils.db";
    private static String sDbDirName = "xutils";
    private static int sDbVersion = 2;

    private static Context sContext;
    private static DbManager mManager;

    private DBManager() {

    }

    public static void init(Context context) {
        sContext = context;
        initDbManager();
    }

    public static void init(Context context, String dbName) {
        sContext = context;
        sDbName = dbName;
        initDbManager();
    }

    public static void init(Context context, String dbName, String dbDirName) {
        sContext = context;
        sDbName = dbName;
        sDbDirName = dbDirName;
        initDbManager();
    }

    public static void init(Context context, String dbName, String dbDirName, int dbVersion) {
        sContext = context;
        sDbName = dbName;
        sDbDirName = dbDirName;
        sDbVersion = dbVersion;
        initDbManager();
    }

    private static void initDbManager() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(sDbName)    //设置数据库名称
                .setDbDir(sContext.getDatabasePath(sDbDirName))  //数据库路径
                .setDbVersion(sDbVersion)  //数据库版本
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
        mManager = x.getDb(daoConfig);
    }

    /**
     * 保存实体类或实体类的List到数据库,
     * 如果该类型的id是自动生成的, 则保存完后会给id赋值.
     *
     * @param entity
     * @return
     * @throws DbException
     */
    public static boolean saveBindingId(Object entity) {
        try {
            return mManager.saveBindingId(entity);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 保存或更新实体类或实体类的List到数据库, 根据id对应的数据是否存在.
     *
     * @param entity
     * @throws DbException
     */
    public static void saveOrUpdate(Object entity) {
        try {
            mManager.saveOrUpdate(entity);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存实体类或实体类的List到数据库
     *
     * @param entity
     * @throws DbException
     */
    public static void save(Object entity) {
        try {
            mManager.save(entity);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存或更新实体类或实体类的List到数据库, 根据id和其他唯一索引判断数据是否存在.
     *
     * @param entity
     * @throws DbException
     */
    public static void replace(Object entity) {
        try {
            mManager.replace(entity);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    ///////////// delete
    public static void deleteById(Class<?> entityType, Object idValue) {
        try {
            mManager.deleteById(entityType, idValue);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Object entity) {
        try {
            mManager.delete(entity);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Class<?> entityType) {
        try {
            mManager.delete(entityType);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static int delete(Class<?> entityType, WhereBuilder whereBuilder) {
        try {
            mManager.delete(entityType, whereBuilder);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return -1;
    }

    ///////////// update
    public static void update(Object entity, String... updateColumnNames) {
        try {
            mManager.update(entity, updateColumnNames);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static int update(Class<?> entityType, WhereBuilder whereBuilder, KeyValue... nameValuePairs) {
        try {
            return mManager.update(entityType, whereBuilder, nameValuePairs);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return -1;
    }

    ///////////// find
    public static <T> T findById(Class<T> entityType, Object idValue) {
        try {
            return mManager.findById(entityType, idValue);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T findFirst(Class<T> entityType) {
        try {
            return mManager.findFirst(entityType);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> findAll(Class<T> entityType) {
        try {
            return mManager.findAll(entityType);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    ///////////// table

    /**
     * 删除表
     *
     * @param entityType
     * @throws DbException
     */
    public static void dropTable(Class<?> entityType) {
        try {
            mManager.dropTable(entityType);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一列,
     * 新的entityType中必须定义了这个列的属性.
     *
     * @param entityType
     * @param column
     * @throws DbException
     */
    public static void addColumn(Class<?> entityType, String column) {
        try {
            mManager.addColumn(entityType, column);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    ///////////// db

    /**
     * 删除库
     *
     * @throws DbException
     */
    public static void dropDb() {
        try {
            mManager.dropDb();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
