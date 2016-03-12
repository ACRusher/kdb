package com.kdb.dao.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxiliang on 2015/12/7.
 */
public class Where {
    /**
     * 外层循环使用 or 聚合
     * 内层循环使用 and 聚合
     */
    public List<List<ColumnFilter>> conditions=new ArrayList<List<ColumnFilter>>();

    public static Where instance(){
        return new Where();
    }

    public static ListWrapper<ColumnFilter> andList(){
        return new ListWrapper<ColumnFilter>();
    }

    public Where or(List<ColumnFilter> columnFilters){
        if(columnFilters!=null){
            conditions.add(columnFilters);
        }
        return this;
    }

    public List<List<ColumnFilter>> build(){
        return conditions;
    }

    public static  class ListWrapper<T> {
        private ArrayList<T> arrayList=new ArrayList<T>();
        public ListWrapper<T> add(T t){
            arrayList.add(t);
            return this;
        }
        public ArrayList<T> build(){
            return arrayList;
        }
    }

    public static void main(String[] args) {
        Where.instance().or(Where.andList().add(null).add(null).build()).build();
    }
}
