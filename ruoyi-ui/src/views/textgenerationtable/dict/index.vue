<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="字典值" prop="dictValue">
        <el-input
          v-model="queryParams.dictValue"
          placeholder="请输入字典值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="字段名" prop="dictName">
        <el-input
          v-model="queryParams.dictName"
          placeholder="请输入字段名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="默认类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择默认类型" clearable>
          <el-option
            v-for="dict in dict.type.field_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="默认长度" prop="length">
        <el-input
          v-model="queryParams.length"
          placeholder="请输入默认长度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为空" prop="isEmpty">
        <el-select v-model="queryParams.isEmpty" placeholder="请选择是否为空" clearable>
          <el-option
            v-for="dict in dict.type.field_is_empty"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCrtTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeUpdTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_notice_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
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
          v-hasPermi="['textgenerationtable:dict:add']"
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
          v-hasPermi="['textgenerationtable:dict:edit']"
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
          v-hasPermi="['textgenerationtable:dict:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['textgenerationtable:dict:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dictList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="字典值" align="center" prop="dictValue" />
      <el-table-column label="字段名" align="center" prop="dictName" />
      <el-table-column label="默认类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.field_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="默认长度" align="center" prop="length" />
      <el-table-column label="是否为空" align="center" prop="isEmpty">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.field_is_empty" :value="scope.row.isEmpty"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="crtTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.crtTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_notice_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['textgenerationtable:dict:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['textgenerationtable:dict:remove']"
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

    <!-- 添加或修改字典管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="字典值" prop="dictValue">
          <el-input v-model="form.dictValue" placeholder="请输入字典值" />
        </el-form-item>
        <el-form-item label="字段名" prop="dictName">
          <el-input v-model="form.dictName" placeholder="请输入字段名" />
        </el-form-item>
        <el-form-item label="默认类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择默认类型">
            <el-option
              v-for="dict in dict.type.field_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="默认长度" prop="length">
          <el-input v-model="form.length" placeholder="请输入默认长度" />
        </el-form-item>
        <el-form-item label="是否为空" prop="isEmpty">
          <el-select v-model="form.isEmpty" placeholder="请选择是否为空">
            <el-option
              v-for="dict in dict.type.field_is_empty"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listDict, getDict, delDict, addDict, updateDict } from "@/api/textgenerationtable/dict";

export default {
  name: "Dict",
  dicts: ['sys_notice_status', 'field_type', 'field_is_empty'],
  data() {
    return {
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
      // 字典管理表格数据
      dictList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否为空时间范围
      daterangeCrtTime: [],
      // 是否为空时间范围
      daterangeUpdTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        dictValue: null,
        dictName: null,
        type: null,
        length: null,
        isEmpty: null,
        crtTime: null,
        updTime: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        dictValue: [
          { required: true, message: "字典值不能为空", trigger: "blur" }
        ],
        isEmpty: [
          { required: true, message: "是否为空不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询字典管理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCrtTime && '' != this.daterangeCrtTime) {
        this.queryParams.params["beginCrtTime"] = this.daterangeCrtTime[0];
        this.queryParams.params["endCrtTime"] = this.daterangeCrtTime[1];
      }
      if (null != this.daterangeUpdTime && '' != this.daterangeUpdTime) {
        this.queryParams.params["beginUpdTime"] = this.daterangeUpdTime[0];
        this.queryParams.params["endUpdTime"] = this.daterangeUpdTime[1];
      }
      listDict(this.queryParams).then(response => {
        this.dictList = response.rows;
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
        id: null,
        dictValue: null,
        dictName: null,
        type: null,
        length: null,
        isEmpty: null,
        crtTime: null,
        updTime: null,
        status: null
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
      this.daterangeCrtTime = [];
      this.daterangeUpdTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加字典管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDict(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改字典管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDict(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDict(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除字典管理编号为"' + ids + '"的数据项？').then(function() {
        return delDict(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('textgenerationtable/dict/export', {
        ...this.queryParams
      }, `dict_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>