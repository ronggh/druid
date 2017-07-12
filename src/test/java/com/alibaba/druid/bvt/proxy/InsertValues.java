package com.alibaba.druid.bvt.proxy;

import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl;
import junit.framework.TestCase;

import java.lang.reflect.Field;

/**
 * Created by wenshao on 12/07/2017.
 */
public class InsertValues extends TestCase {
    public void test_insert_values() throws Exception {
        String sql = "insert into t (f0, f1, f2, f3, f4) values ";
        for (int i = 0; i < 1000; ++i) {
            if (i != 0) {
                sql += ", (?, ?, ?, ?, ?)";
            } else {
                sql += "(?, ?, ?, ?, ?)";
            }
        }

        PreparedStatementProxyImpl proxy = new PreparedStatementProxyImpl(null, null, sql, 0);

        Field field = PreparedStatementProxyImpl.class.getDeclaredField("parameters");
        field.setAccessible(true);
        JdbcParameter[] params = (JdbcParameter[]) field.get(proxy);
        assertEquals(5000, params.length);
    }
}
