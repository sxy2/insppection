package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysInsppectionRecord;
import com.ruoyi.system.service.ISysInsppectionRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 机房现场巡检记录Controller
 * 
 * @author ruoyi
 * @date 2022-07-20
 */
@RestController
@RequestMapping("/system/record")
public class SysInsppectionRecordController extends BaseController
{
    @Autowired
    private ISysInsppectionRecordService sysInsppectionRecordService;

    /**
     * 查询机房现场巡检记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysInsppectionRecord sysInsppectionRecord)
    {
        startPage();
        List<SysInsppectionRecord> list = sysInsppectionRecordService.selectSysInsppectionRecordList(sysInsppectionRecord);
        return getDataTable(list);
    }

    /**
     * 导出机房现场巡检记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "机房现场巡检记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysInsppectionRecord sysInsppectionRecord)
    {
        List<SysInsppectionRecord> list = sysInsppectionRecordService.selectSysInsppectionRecordList(sysInsppectionRecord);
        ExcelUtil<SysInsppectionRecord> util = new ExcelUtil<SysInsppectionRecord>(SysInsppectionRecord.class);
        util.exportExcel(response, list, "机房现场巡检记录数据");
    }

    /**
     * 获取机房现场巡检记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{inspectionId}")
    public AjaxResult getInfo(@PathVariable("inspectionId") Long inspectionId)
    {
        return AjaxResult.success(sysInsppectionRecordService.selectSysInsppectionRecordByInspectionId(inspectionId));
    }

    /**
     * 新增机房现场巡检记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "机房现场巡检记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysInsppectionRecord sysInsppectionRecord)
    {
        return toAjax(sysInsppectionRecordService.insertSysInsppectionRecord(sysInsppectionRecord));
    }

    /**
     * 修改机房现场巡检记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "机房现场巡检记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInsppectionRecord sysInsppectionRecord)
    {
        return toAjax(sysInsppectionRecordService.updateSysInsppectionRecord(sysInsppectionRecord));
    }

    /**
     * 删除机房现场巡检记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "机房现场巡检记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inspectionIds}")
    public AjaxResult remove(@PathVariable Long[] inspectionIds)
    {
        return toAjax(sysInsppectionRecordService.deleteSysInsppectionRecordByInspectionIds(inspectionIds));
    }
}
