package com.kdb.dao.common;

/**
 * Created by zhouxiliang on 2015/12/4.
 */
public class MapperConstants {
    /**
     * 排序 mapper 参数key , 使用方法:
     * Map param= new HashMap();
     * List orderList=new ArrayList<Map>();
     * Map order=new HashMap();
     * order.put(MapperConstants.COLUMN_NAME,"id");
     * order.put(MapperConstants.ORDER_TYPE_ASC,true);
     * orderList.add(order);
     * param.put(MapperConstants.ORDER_MAP_LIST,orderList);
     */
    public static final String ORDER_MAP_LIST = "_order_map_list";
    public static final String COLUMN_NAME = "_column_name";
    public static final String ORDER_TYPE_ASC = "_asc_";
    public static final String ORDER_TYPE_DESC = "_desc_";
    /**
     * distinct  参数key
     * 使用方法:
     * Map param= new HashMap();
     * param.put(MapperConstants.DISTINCT,true);
     */
    public static final String DISTINCT="_distinct_";

    /**
     * 使用方法:
     * Map param= new HashMap();
     * param.put(MapperConstants.LIMIT_OFFSET,0);
     * param.put(MapperConstants.LIMIT_TOTAL,100);
     */
    public static final String LIMIT_OFFSET="_limit_offset";
    public static final String LIMIT_TOTAL="_limit_total";

    /**
     * where 参数key, 使用方法:
     * Map param= new HashMap();
     * param.put(MapperConstants.WHERE_KEY,new Where())
     */

    public static final String WHERE_KEY="_where_key";

    /**
     * 使用策略的索引 默认为0
     */
//    public static final String STRATEGY_INDEX="_strategy_index";

    /**
     * 分库分表字段
     */
    public static final String SHARD_KEY="_shard_key";
}
