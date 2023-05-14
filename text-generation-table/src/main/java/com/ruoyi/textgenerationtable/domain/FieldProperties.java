package com.ruoyi.textgenerationtable.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 字段管理对象 field_properties
 * 
 * @author ruoyi
 * @date 2023-05-14
 */
public class FieldProperties extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Long id;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String name;

    /** 字段类型 */
    @Excel(name = "字段类型")
    private String type;

    /** 表ID */
    @Excel(name = "表ID")
    private Long tableId;

    /** 表名 */
    @Excel(name = "表名")
    private String tableName;

    /** 字段长度 */
    @Excel(name = "字段长度")
    private Long length;

    /** 是否可以为空 */
    @Excel(name = "是否可以为空")
    private Long isEmpty;

    /** 外键 */
    @Excel(name = "外键")
    private String fieldForeignKey;

    /** 注释 */
    @Excel(name = "注释")
    private String desc;

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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setTableId(Long tableId) 
    {
        this.tableId = tableId;
    }

    public Long getTableId() 
    {
        return tableId;
    }
    public void setTableName(String tableName) 
    {
        this.tableName = tableName;
    }

    public String getTableName() 
    {
        return tableName;
    }
    public void setLength(Long length) 
    {
        this.length = length;
    }

    public Long getLength() 
    {
        return length;
    }
    public void setIsEmpty(Long isEmpty) 
    {
        this.isEmpty = isEmpty;
    }

    public Long getIsEmpty() 
    {
        return isEmpty;
    }
    public void setFieldForeignKey(String fieldForeignKey) 
    {
        this.fieldForeignKey = fieldForeignKey;
    }

    public String getFieldForeignKey() 
    {
        return fieldForeignKey;
    }
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
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
            .append("type", getType())
            .append("tableId", getTableId())
            .append("tableName", getTableName())
            .append("length", getLength())
            .append("isEmpty", getIsEmpty())
            .append("fieldForeignKey", getFieldForeignKey())
            .append("desc", getDesc())
            .append("crtTime", getCrtTime())
            .append("updTime", getUpdTime())
            .toString();
    }
}
