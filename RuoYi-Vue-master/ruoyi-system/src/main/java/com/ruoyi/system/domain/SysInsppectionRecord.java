package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 机房现场巡检记录对象 sys_insppection_record
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public class SysInsppectionRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 巡检ID */
    private Long inspectionId;

    /** 数据机房设备及环境 */
    @Excel(name = "数据机房设备及环境")
    private Integer dataRoom;

    /** 传输机房设备及环境 */
    @Excel(name = "传输机房设备及环境")
    private Integer transferRoom;

    /** 光缆配线机房环境 */
    @Excel(name = "光缆配线机房环境")
    private Integer cableRoom;

    /** 配电间与电池间设备及环境 */
    @Excel(name = "配电间与电池间设备及环境")
    private Integer powerRoom;

    /** 周边环境 */
    @Excel(name = "周边环境")
    private Integer environment;

    /** 异常描述时间内容 */
    @Excel(name = "异常描述时间内容")
    private String abnormal;

    public void setInspectionId(Long inspectionId) 
    {
        this.inspectionId = inspectionId;
    }

    public Long getInspectionId() 
    {
        return inspectionId;
    }
    public void setDataRoom(Integer dataRoom) 
    {
        this.dataRoom = dataRoom;
    }

    public Integer getDataRoom() 
    {
        return dataRoom;
    }
    public void setTransferRoom(Integer transferRoom) 
    {
        this.transferRoom = transferRoom;
    }

    public Integer getTransferRoom() 
    {
        return transferRoom;
    }
    public void setCableRoom(Integer cableRoom) 
    {
        this.cableRoom = cableRoom;
    }

    public Integer getCableRoom() 
    {
        return cableRoom;
    }
    public void setPowerRoom(Integer powerRoom) 
    {
        this.powerRoom = powerRoom;
    }

    public Integer getPowerRoom() 
    {
        return powerRoom;
    }
    public void setEnvironment(Integer environment) 
    {
        this.environment = environment;
    }

    public Integer getEnvironment() 
    {
        return environment;
    }
    public void setAbnormal(String abnormal) 
    {
        this.abnormal = abnormal;
    }

    public String getAbnormal() 
    {
        return abnormal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inspectionId", getInspectionId())
            .append("dataRoom", getDataRoom())
            .append("transferRoom", getTransferRoom())
            .append("cableRoom", getCableRoom())
            .append("powerRoom", getPowerRoom())
            .append("environment", getEnvironment())
            .append("abnormal", getAbnormal())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
