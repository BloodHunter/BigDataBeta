package com.wbl.modal;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created with Simple_love
 * Date: 2016/4/26.
 * Time: 11:12
 */
public class MultipleDataSource extends AbstractRoutingDataSource{

        private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

        public static void setDataSourceKey(String dataSource) {
                dataSourceKey.set(dataSource);
        }

        @Override
        protected Object determineCurrentLookupKey() {
                return dataSourceKey.get();
        }
}
