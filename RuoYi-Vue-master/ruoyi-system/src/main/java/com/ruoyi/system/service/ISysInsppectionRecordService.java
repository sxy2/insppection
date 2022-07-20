package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysInsppectionRecord;

/**
 * 机房现场巡检记录Service接口
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
public interface ISysInsppectionRecordService 
{
    /**
     * 查询机房现场巡检记录
     * 
     * @param inspectionId 机房现场巡检记录主键
     * @return 机房现场巡检记录
     */
    public SysInsppectionRecord selectSysInsppectionRecordByInspectionId(Long inspectionId);

    /**
     * 查询机房现场巡检记录列表
     * 
     * @param sysInsppectionRecord 机房现场巡检记录
     * @return 机房现场巡检记录集合
     */
    public List<SysInsppectionRecord> selectSysInsppectionRecordList(SysInsppectionRecord sysInsppectionRecord);

    /**
     * 新增机房现场巡检记录
     * 
     * @param sysInsppectionRecord 机房现场巡检记录
     * @return 结果
     */
    public int insertSysInsppectionRecord(SysInsppectionRecord sysInsppectionRecord);

    /**
     * 修改机房现场巡检记录
     * 
     * @param sysInsppectionRecord 机房现场巡检记录
     * @return 结果
     */
    public int updateSysInsppectionRecord(SysInsppectionRecord sysInsppectionRecord);

    /**
     * 批量删除机房现场巡检记录
     * 
     * @param inspectionIds 需要删除的机房现场巡检记录主键集合
     * @return 结果
     */
    public int deleteSysInsppectionRecordByInspectionIds(Long[] inspectionIds);

    /**
     * 删除机房现场巡检记录信息
     * 
     * @param inspectionId 机房现场巡检记录主键
     * @return 结果
     */
    public int deleteSysInsppectionRecordByInspectionId(Long inspectionId);
}
