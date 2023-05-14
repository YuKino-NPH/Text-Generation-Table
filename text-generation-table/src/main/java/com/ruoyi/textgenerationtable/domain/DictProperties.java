package com.ruoyi.textgenerationtable.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 字典管理对象 dict_properties
 * 
 * @author ruoyi
 * @date 2023-05-14
 */
public class DictProperties extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Long id;

    /** 字典值 */
    @Excel(name = "字典值")
    private String dictValue;

    /** 字段名 */
    @Excel(name = "字段名")
    private String dictName;

    /** 默认类型 */
    @Excel(name = "默认类型")
    private String type;

    /** 默认长度 */
    @Excel(name = "默认长度")
    private Long length;

    /** 是否为空 */
    @Excel(name = "是否为空")
    private Long isEmpty;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crtTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDictValue(String dictValue) 
    {
        this.dictValue = dictValue;
    }

    public String getDictValue() 
    {
        return dictValue;
    }
    public void setDictName(String dictName) 
    {
        this.dictName = dictName;
    }

    public String getDictName() 
    {
        return dictName;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
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
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dictValue", getDictValue())
            .append("dictName", getDictName())
            .append("type", getType())
            .append("length", getLength())
            .append("isEmpty", getIsEmpty())
            .append("crtTime", getCrtTime())
            .append("updTime", getUpdTime())
            .append("status", getStatus())
            .toString();
    }
}
