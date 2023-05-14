package com.ruoyi.textgenerationtable.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 表结构管理对象 table_properties
 * 
 * @author ruoyi
 * @date 2023-05-14
 */
public class TableProperties extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Long id;

    /** 表名 */
    @Excel(name = "表名")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String desc;

    /** 主键 */
    @Excel(name = "主键")
    private String tablePrimaryKey;

    /** 索引 */
    @Excel(name = "索引")
    private String tableIndex;

    /** 外键 */
    @Excel(name = "外键")
    private String tableForeign;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crtTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
    }
    public void setTablePrimaryKey(String tablePrimaryKey) 
    {
        this.tablePrimaryKey = tablePrimaryKey;
    }

    public String getTablePrimaryKey() 
    {
        return tablePrimaryKey;
    }
    public void setTableIndex(String tableIndex) 
    {
        this.tableIndex = tableIndex;
    }

    public String getTableIndex() 
    {
        return tableIndex;
    }
    public void setTableForeign(String tableForeign) 
    {
        this.tableForeign = tableForeign;
    }

    public String getTableForeign() 
    {
        return tableForeign;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCrtTime(Date crtTime) 
    {
        this.crtTime = crtTime;
    }

    public Date getCrtTime() 
    {
        return crtTime;
    }
    public void setUpdTime(Date updTime) 
    {
        this.updTime = updTime;
    }

    public Date getUpdTime() 
    {
        return updTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("desc", getDesc())
            .append("tablePrimaryKey", getTablePrimaryKey())
            .append("tableIndex", getTableIndex())
            .append("tableForeign", getTableForeign())
            .append("status", getStatus())
            .append("crtTime", getCrtTime())
            .append("updTime", getUpdTime())
            .toString();
    }
}
