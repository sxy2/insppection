<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto" >
      <el-form-item label="数据机房" prop="dataRoom">
        <el-select v-model="queryParams.dataRoom" placeholder="请选择" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="传输机房" prop="transferRoom">
        <el-select v-model="queryParams.transferRoom" placeholder="请选择" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="光缆配线" prop="cableRoom">
        <el-select v-model="queryParams.cableRoom" placeholder="请选择" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="配电间与电池间" prop="powerRoom">
        <el-select v-model="queryParams.powerRoom" placeholder="请选择" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="周边环境" prop="environment">
        <el-select v-model="queryParams.environment" placeholder="请选择" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="异常描述时间内容" prop="abnormal">
        <el-input
          v-model="queryParams.abnormal"
          placeholder="请输入异常描述时间内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange" :cell-style="getClass">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="巡检ID" align="center" prop="inspectionId" />
      <!-- <el-table-column label="数据机房设备及环境" align="center" prop="dataRoom"  :formatter="formatBoolean">
      </el-table-column> -->
      <el-table-column label="数据机房设备及环境" align="center" prop="dataRoom">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal" :value="scope.row.dataRoom"/>
        </template>
      </el-table-column>
      <el-table-column label="传输机房设备及环境" align="center" prop="transferRoom">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal" :value="scope.row.transferRoom"/>
        </template>
      </el-table-column>
      <el-table-column label="光缆配线机房环境" align="center" prop="cableRoom">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal" :value="scope.row.cableRoom"/>
        </template>
      </el-table-column>
      <el-table-column label="配电间与电池间设备及环境" align="center" prop="powerRoom">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal" :value="scope.row.powerRoom"/>
        </template>
      </el-table-column>
      <el-table-column label="周边环境" align="center" prop="environment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal" :value="scope.row.environment"/>
        </template>
      </el-table-column>
      <el-table-column label="异常描述时间内容" align="center" prop="abnormal" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-bulid"
            @click="handleEnter(scope.row)"
            v-hasPermi="['system:record:edit']"
          >进入</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:record:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改机房现场巡检记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="200px">
        <el-form-item label="数据机房设备及环境">
          <el-radio-group v-model="form.dataRoom">
            <el-radio
              v-for="dict in dict.type.sys_normal"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="传输机房设备及环境">
          <el-radio-group v-model="form.transferRoom">
            <el-radio
              v-for="dict in dict.type.sys_normal"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="光缆配线机房环境">
          <el-radio-group v-model="form.cableRoom">
            <el-radio
              v-for="dict in dict.type.sys_normal"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="配电间与电池间设备及环境">
          <el-radio-group v-model="form.powerRoom">
            <el-radio
              v-for="dict in dict.type.sys_normal"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="周边环境">
          <el-radio-group v-model="form.environment">
            <el-radio
              v-for="dict in dict.type.sys_normal"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="异常描述时间内容" prop="abnormal">
          <el-input v-model="form.abnormal" placeholder="请输入异常描述时间内容" type="textarea"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" type="textarea"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/system/record";

export default {
  name: "Record",
  dicts: ['sys_normal'],
  data() {
    return {
      //表格高亮颜色
      redClass:{
        "color":"red"
      },
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 机房现场巡检记录表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dataRoom: null,
        transferRoom: null,
        cableRoom: null,
        powerRoom: null,
        environment: null,
        abnormal: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        dataRoom: [
          { required: true, message: "数据机房设备及环境不能为空", trigger: "blur" }
        ],
        transferRoom: [
          { required: true, message: "传输机房设备及环境不能为空", trigger: "blur" }
        ],
        cableRoom: [
          { required: true, message: "光缆配线机房环境不能为空", trigger: "blur" }
        ],
        powerRoom: [
          { required: true, message: "配电间与电池间设备及环境不能为空", trigger: "blur" }
        ],
        environment: [
          { required: true, message: "周边环境不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 表单中的转换 */
    getClass(row){
      if(row.row[row.column.property]==1&&(row.columnIndex>=3&&row.columnIndex<=6))
      return this.redClass;
      else if(row.row[row.column.property]==0)
      return ;
    },
    /** 查询机房现场巡检记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        inspectionId: null,
        dataRoom: 0,
        transferRoom: 0,
        cableRoom: 0,
        powerRoom: 0,
        environment: 0,
        abnormal: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.inspectionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 进入按钮操作 */
    handleEnter() {
      
      // this.reset();
      // this.open = true;
      // this.title = "添加机房现场巡检记录";
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加机房现场巡检记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const inspectionId = row.inspectionId || this.ids
      getRecord(inspectionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改机房现场巡检记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.inspectionId != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const inspectionIds = row.inspectionId || this.ids;
      this.$modal.confirm('是否确认删除机房现场巡检记录编号为"' + inspectionIds + '"的数据项？').then(function() {
        return delRecord(inspectionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {

      console.log(this.queryParams)
      this.download('system/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
