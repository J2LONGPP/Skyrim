package com.coder.mybatis.config;

/**
 * 存储Mapper下所有的xml文件中的SQL语句（目前只实现select）
 */
public class MappedSatement {
    private String sourceId;
    private String resultType;
    private String namespace;
    private String sql;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
