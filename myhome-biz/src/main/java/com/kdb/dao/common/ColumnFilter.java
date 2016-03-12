package com.kdb.dao.common;

/**
 * Created by zhouxiliang on 2015/12/7.
 */
public class ColumnFilter {
    public String column;   //列名
    public Object value;    //待比较的值
    public Type type;       //比较类型

    public static ColumnFilter instance(){
        return new ColumnFilter();
    }

    public ColumnFilter column(String column){
        this.column=column;
        return this;
    }

    public ColumnFilter value(Object value){
        this.value=value;
        return this;
    }

    public ColumnFilter type(Type type){
        this.type=type;
        return this;
    }

    public ColumnFilter build(){
        return this;
    }

    public boolean LT(){
        return Type.LT.equals(type);
    }
    public boolean LE(){
        return Type.LE.equals(type);
    }
    public boolean GT(){
        return Type.GT.equals(type);
    }
    public boolean GE(){
        return Type.GE.equals(type);
    }
    public boolean EQ(){
        return Type.EQ.equals(type);
    }
    public boolean NE(){
        return Type.NE.equals(type);
    }
    public boolean LIKE(){
        return Type.LIKE.equals(type);
    }

    public static enum Type {
        LT ,    // <
        LE ,    // <=
        GT ,    // >
        GE ,    // >=
        EQ ,    // =
        NE ,    // !=
        LIKE    // like
    }
}
