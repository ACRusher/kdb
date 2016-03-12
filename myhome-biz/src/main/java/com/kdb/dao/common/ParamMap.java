package com.kdb.dao.common;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxiliang on 2015/12/7.
 */
public class ParamMap {

    private Map paramMap=new HashMap();
    private List orderMapList=new ArrayList();

    private static ThreadLocal<Integer> currentStrategyIndex=new ThreadLocal<Integer>();

    public static ParamMap instance(){
        return new ParamMap();
    }

    public void where(List<List<ColumnFilter>> where){
        if(where!=null && !where.isEmpty()){
            paramMap.put(MapperConstants.WHERE_KEY,where);
        }
    }

    public void desc(String columnName){
        Map order=new HashMap();
        order.put(MapperConstants.ORDER_TYPE_DESC,true);
        order.put(MapperConstants.COLUMN_NAME,columnName);
        orderMapList.add(order);
    }

    public void asc(String columnName){
        Map order=new HashMap();
        order.put(MapperConstants.ORDER_TYPE_ASC,true);
        order.put(MapperConstants.COLUMN_NAME,columnName);
        orderMapList.add(order);
    }

    public void limit(int offset,int total){
        if(offset>=0 && total >=0){
            paramMap.put(MapperConstants.LIMIT_OFFSET,offset);
            paramMap.put(MapperConstants.LIMIT_TOTAL,total);
        }
    }

    public static void strategyIndex(int index){
        if(index>=0) {
//            paramMap.put(MapperConstants.STRATEGY_INDEX, index);
            currentStrategyIndex.set(index);
        }
    }

    /**
     * 只能获取一次
     *
     * @return
     */
    public static Integer getStrategyIndex(){
        Integer index= currentStrategyIndex.get();
        currentStrategyIndex.remove();
        return index==null?0:index;
    }

    public void shardKey(Long key){
        if(key!=null) {
            paramMap.put(MapperConstants.SHARD_KEY, key);
        }
    }


    public Map build(){
        if(!orderMapList.isEmpty()){
            paramMap.put(MapperConstants.ORDER_MAP_LIST,orderMapList);
        }
        return paramMap;
    }
}
