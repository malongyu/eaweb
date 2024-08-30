package com.easy.util;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.easy.bean.Goods;
import com.easy.bean.Student;
import com.easy.bean.Type;
import com.easy.bean.User;
import com.mysql.cj.xdevapi.Table;
import com.sun.media.jfxmediaimpl.HostUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName JDBCUtil
 * @Description TODO
 * @Author MLY
 * @Date 2024/5/30 15:05
 * @Version 1.0
 */


public class JDBCUtil {

    private static DataSource ds = null;

    /**
     * 加载JDBC驱动
     * 加载配置文件
     * 创建数据库连接池
     */
    static {
        InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties pros = new Properties();
        try {
            pros.load(in);
            ds = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 预编译执行SQL语句
     */
    private static PreparedStatement setParameters(Connection con, String sql, Object... objs) throws SQLException {
        PreparedStatement presta = con.prepareStatement(sql);
        for (int i = 0; i < objs.length; i++) {
            presta.setObject(i + 1, objs[i]);
        }
        return presta;
    }

    /**
     * 关闭数据库连接
     */
    public static void close(Connection con, PreparedStatement presta, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (presta != null) {
            try {
                presta.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改数据
     */
    public static int update(String sql, Object... objs) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement presta = setParameters(con, sql, objs);
        int result = presta.executeUpdate();
        close(con, presta, null);
        return result;
    }

    public static int updateById(Object obj) throws Exception {
        Class clazz = obj.getClass();//获取表对象
        Class tableclass = Table.class;
        Annotation t = clazz.getAnnotation(tableclass);//获取该类上的注解
        Method m = tableclass.getDeclaredMethod("tablename");//获取注解的方法
        Object table_name = m.invoke(t);//调用该方法
        StringBuilder strb = new StringBuilder();
        strb.append("update ");
        strb.append(table_name);
        strb.append(" set ");
        String wherestr = "";
        Object id = null;
        Field[] f_arr = clazz.getDeclaredFields();
        List params = new ArrayList();
        for (Field f : f_arr) {
            f.setAccessible(true);
            String fname = f.getName();
            Object fval = f.get(obj);
            if (f.isAnnotationPresent(ID.class)) {
                wherestr = "where " + fname + "=?";
                id = fval;
            } else {
                strb.append(fname);
                strb.append("=? ,");
                params.add(fval);
            }
        }
        String sql = strb.substring(0, strb.length() - 1);
        sql = sql + wherestr;
        params.add(id);
        Connection con = ds.getConnection();
        PreparedStatement presta = setParameters(con, sql, params.toArray());
        int result = presta.executeUpdate();
        close(con, presta, null);
        return result;
    }

    /**
     * 查询数据
     *
     * @param sql
     * @param objs
     * @return
     * @throws SQLException
     */
    public static <E> List<E> query(Class<E> clazz, String sql, Object... objs) throws Exception {
        Connection con = ds.getConnection();
        PreparedStatement presta = setParameters(con, sql, objs);
        ResultSet rs = presta.executeQuery();
        //解析结果集
        List<E> list = new ArrayList<E>();
        //获取clazz中所有的字段
        Field[] f_arr = clazz.getDeclaredFields();
        for (Field f : f_arr) {
            //设置所有属性可访问
            f.setAccessible(true);
        }
        while (rs.next()) {
            E e = clazz.newInstance();//获取一个对象
            for (Field f : f_arr) {
                //属性的字段名和列明一样
                String f_name = f.getName();
                Annotation ann_one = f.getAnnotation(One.class);
                Annotation ann_lists = f.getAnnotation(Lists.class);
                if (ann_one != null) {
                    getOne(con, rs, e, f);
                } else if (ann_lists != null) {
                    getLists(con, rs, e, f);
                } else {
                    if (f.getType() == Integer.class) {
                        int val = rs.getInt(f_name);
                        f.set(e, val);
                    } else if (f.getType() == String.class) {
                        String val = rs.getString(f_name);
                        f.set(e, val);
                    } else if (f.getType() == Double.class) {
                        double val = rs.getDouble(f_name);
                        f.set(e, val);
                    } else {
                        Object val = rs.getObject(f_name);
                        f.set(e, val);
                    }
                }
            }
            list.add(e);
        }
        close(con, presta, rs);
        return list;
    }

    public static void getOne(Connection con, ResultSet rsp, Object obj, Field field) {
        //属性声明的了类型
        System.out.println("one");
        Class field_class = field.getType();
        Class one_class = One.class;
        //获取该属性上的注解
        Annotation ann_one = field.getAnnotation(one_class);//注解对象
        Method method_sql = null;
        Method method_column_name = null;
        Object sql = null;
        Object columnName = null;
        Object param = null;
        PreparedStatement presta = null;
        ResultSet rs = null;

        try {
            method_sql = one_class.getDeclaredMethod("sql");
            method_column_name = one_class.getDeclaredMethod("columnName");
            sql = method_sql.invoke(ann_one);
            columnName = method_column_name.invoke(ann_one);
            //获取columnName列中对应的数据
            param = rsp.getObject((String) columnName);
            presta = setParameters(con, sql.toString(), param);
            //执行sql语句
            rs = presta.executeQuery();
            //获取clazz中所有的字段
            Field[] f_arr = field_class.getDeclaredFields();
            //设置属性可访问性
            for (Field f : f_arr) {
                f.setAccessible(true);
            }
            //解析结果集
            rs.next();
            Object e = field_class.newInstance();
            for (Field f : f_arr) {
                //属性的字段名和列明一样
                String f_name = f.getName();
                System.out.println(f_name);
                try {
                    if (f.getType() == Integer.class) {
                        int val = rs.getInt(f_name);
                        f.set(e, val);
                    } else if (f.getType() == String.class) {
                        String val = rs.getString(f_name);
                        f.set(e, val);
                    } else if (f.getType() == Double.class) {
                        double val = rs.getDouble(f_name);
                        f.set(e, val);
                    } else {
                        Object val = rs.getObject(f_name);
                        System.out.println("nu");
                        f.set(e, val);
                    }
                } catch (Exception q) {
                    q.printStackTrace();
                }
                field.set(obj, e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getLists(Connection con, ResultSet rsp, Object obj, Field field) {
        //属性声明的了类型
        Class field_class = field.getType();
        Class list_class = Lists.class;
        //获取该属性上的注解
        Annotation ann_one = field.getAnnotation(list_class);//注解对象
        Method method_sql = null;
        Method method_column_name = null;
        Method method_class_name = null;
        Object sql = null;
        Object columnName = null;
        Object param = null;
        Object className = null;
        Class clazz = null;
        PreparedStatement presta = null;
        ResultSet rs = null;

        try {
            method_sql = list_class.getDeclaredMethod("sql");
            method_column_name = list_class.getDeclaredMethod("columnName");
            method_class_name = list_class.getDeclaredMethod("className");
            sql = method_sql.invoke(ann_one);
            columnName = method_column_name.invoke(ann_one);
            className = method_class_name.invoke(ann_one);
            clazz = Class.forName((className.toString()));
            //获取columnName列中对应的数据
            param = rsp.getObject((String) columnName);
            presta = setParameters(con, sql.toString(), param);
            //执行sql语句
            rs = presta.executeQuery();
            //获取clazz中所有的字段
            Field[] f_arr = clazz.getDeclaredFields();
            //设置属性可访问性
            for (Field f : f_arr) {
                f.setAccessible(true);
            }
            //解析结果集
            List list = new ArrayList();
            //获取clazz中所有的字段
            for (Field f : f_arr) {
                //设置所有属性可访问
                f.setAccessible(true);
            }
            while (rs.next()) {
                Object e = clazz.newInstance();//获取一个对象
                for (Field f : f_arr) {
                    //属性的字段名和列明一样
                    String f_name = f.getName();
                    try{
                        if (f.getType() == Integer.class) {
                            int val = rs.getInt(f_name);
                            f.set(e, val);
                        } else if (f.getType() == String.class) {
                            String val = rs.getString(f_name);
                            f.set(e, val);
                        } else if (f.getType() == Double.class) {
                            double val = rs.getDouble(f_name);
                            f.set(e, val);
                        } else {
                            Object val = rs.getObject(f_name);
                            f.set(e, val);
                        }
                    }catch (Exception q){
                        q.printStackTrace();
                    }
                }
                list.add(e);
            }
            field.set(obj, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object queryOne(String sql, Object... objs) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement presta = setParameters(con, sql, objs);
        ResultSet rs = presta.executeQuery();
        Object result = null;
        if (rs.next()) {
            result = rs.getObject(1);
        }
        close(con, presta, rs);
        return result;
    }

    /**
     * 事务处理
     */
    public static int update(Connection con, String sql, Object... objs) throws SQLException {
        PreparedStatement presta = setParameters(con, sql, objs);
        int result = presta.executeUpdate();
        close(null, presta, null);
        return result;
    }

    public static <E> List<E> query(Connection con, Class<E> clazz, String sql, Object... objs) throws Exception {
        PreparedStatement presta = setParameters(con, sql, objs);
        ResultSet rs = presta.executeQuery();
        //解析结果集
        List<E> list = new ArrayList<E>();
        //获取clazz中所有的字段
        Field[] f_arr = clazz.getDeclaredFields();
        for (Field f : f_arr) {
            //设置所有属性可访问
            f.setAccessible(true);
        }
        while (rs.next()) {
            E e = clazz.newInstance();//获取一个对象
            for (Field f : f_arr) {
                //属性的字段名和列明一样
                String f_name = f.getName();
                if (f.getType() == Integer.class) {
                    int val = rs.getInt(f_name);
                    f.set(e, val);
                } else if (f.getType() == String.class) {
                    String val = rs.getString(f_name);
                    f.set(e, val);
                } else if (f.getType() == Double.class) {
                    double val = rs.getDouble(f_name);
                    f.set(e, val);
                } else {
                    Object val = rs.getObject(f_name);
                    f.set(e, val);
                }
            }
            list.add(e);
        }
        close(null, presta, rs);
        return list;
    }

    public static Object queryOne(Connection con, String sql, Object... objs) throws SQLException {
        PreparedStatement presta = setParameters(con, sql, objs);
        ResultSet rs = presta.executeQuery();
        Object result = null;
        if (rs.next()) {
            result = rs.getObject(1);
        }
        close(null, presta, rs);
        return result;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}



