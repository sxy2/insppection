package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysInsppectionRecordMapper;
import com.ruoyi.system.domain.SysInsppectionRecord;
import com.ruoyi.system.service.ISysInsppectionRecordService;

/**
 * 机房现场巡检记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
@Service
public class SysInsppectionRecordServiceImpl implements ISysInsppectionRecordService 
{
    @Autowired
    private SysInsppectionRecordMapper sysInsppectionRecordMapper;

    /**
     * 查询机房现场巡检记录
     * 
     * @param inspectionId 机房现场巡检记录主键
     * @return 机房现场巡检记录
     */
    @Override
    public SysInsppectionRecord selectSysInsppectionRecordByInspectionId(Long inspectionId)
    {
        return sysInsppectionRecordMapper.selectSysInsppectionRecordByInspectionId(inspectionId);
    }

    /**
     * 查询机房现场巡检记录列表
     * 
     * @param sysInsppectionRecord 机房现场巡检记录
     * @return 机房现场巡检记录
     */
    @Override
    public List<SysInsppectionRecord> selectSysInsppectionRecordList(SysInsppectionRecord sysInsppectionRecord)
    {
        return sysInsppectionRecordMapper.selectSysInsppectionRecordList(sysInsppectionRecord);
    }

    /**
     * 新增机房现场巡检记录
     * 
     * @param sysInsppectionRecord 机房现场巡检记录
     * @return 结果
     */
    @Override
    public int insertSysInsppectionRecord(SysInsppectionRecord sysInsppectionRecord)
    {
        sysInsppectionRecord.setCreateTime(DateUtils.getNowDate());
        return sysInsppectionRecordMapper.insertSysInsppectionRecord(sysInsppectionRecord);
    }

    /**
     * 修改机房现场巡检记录
     * 
     * @param sysInsppectionRecord 机房现场巡检记录
     * @return 结果
     */
    @Override
    public int updateSysInsppectionRecord(SysInsppectionRecord sysInsppectionRecord)
    {
        sysInsppectionRecord.setUpdateTime(DateUtils.getNowDate());
        return sysInsppectionRecordMapper.updateSysInsppectionRecord(sysInsppectionRecord);
    }

    /**
     * 批量删除机房现场巡检记录
     * 
     * @param inspectionIds 需要删除的机房现场巡检记录主键
     * @return 结果
     */
    @Override
    public int deleteSysInsppectionRecordByInspectionIds(Long[] inspectionIds)
    {
        return sysInsppectionRecordMapper.deleteSysInsppectionRecordByInspectionIds(inspectionIds);
    }

    /**
     * 删除机房现场巡检记录信息
     * 
     * @param inspectionId 机房现场巡检记录主键
     * @return 结果
     */
    @Override
    public int deleteSysInsppectionRecordByInspectionId(Long inspectionId)
    {
        return sysInsppectionRecordMapper.deleteSysInsppectionRecordByInspectionId(inspectionId);
    }
}
