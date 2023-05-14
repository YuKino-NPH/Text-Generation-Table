<template>
    <div class="app-container">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleCreate"
            v-hasPermi="['textgenerationtable:analysis:analysis']"
          >文本解析</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            size="mini"
            @click="createTable"
            v-hasPermi="['textgenerationtable:analysis:create']"
          >生成表</el-button>
        </el-col>
        <right-toolbar @queryTable="getList"></right-toolbar>
      </el-row>
      
  
      <el-table v-loading="loading" :data="dictList">
        <el-table-column label="字段名称" align="center" prop="dictName" />
        <el-table-column label="字段值" align="center" prop="dictValue" />
        <el-table-column label="字段类型" align="center" prop="type">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.field_type" :value="scope.row.type"/>
          </template>
        </el-table-column>
        <el-table-column label="字段长度" align="center" prop="length" />
        <el-table-column label="是否可以为空" align="center" prop="isEmpty">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.field_is_empty" :value="scope.row.isEmpty"/>
          </template>
        </el-table-column>
        <el-table-column label="注释" align="center" prop="desc" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['textgenerationtable:analysis:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <!-- <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      /> -->
  
      <!-- 修改字段对话框 -->
     <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="字段名称" prop="dictName">
            <el-input v-model="form.dictName" disabled placeholder="请输入字段名称" />
          </el-form-item>
          <el-form-item label="字段值" prop="dictValue">
            <el-input v-model="form.dictValue" placeholder="请输入字段值" />
          </el-form-item>
          <el-form-item label="字段类型" prop="type">
            <el-select v-model="form.type" placeholder="请选择字段类型">
              <el-option
                v-for="dict in dict.type.field_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="字段长度" prop="length">
            <el-input v-model="form.length" placeholder="请输入字段长度" />
          </el-form-item>
          <el-form-item label="是否可以为空" prop="isEmpty">
            <el-select v-model="form.isEmpty" placeholder="请选择是否可以为空">
              <el-option
                v-for="dict in dict.type.field_is_empty"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="注释" prop="desc">
                <el-input v-model="form.desc" placeholder="请输入注释" />
           </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
      <!-- 文本生成表结构对话框 -->
      <el-dialog :title="title" :visible.sync="textOpen" width="500px" append-to-body>
        <el-input v-model="text" :rows="2" type="textarea" placeholder="请输入文本"/>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitTextForm">解 析</el-button>
        </div>
      </el-dialog>
      <!-- 提交生成表对话框 -->
      <el-dialog :title="title" :visible.sync="tableOpen" width="500px" append-to-body>
        <el-form label-width="80px">
          <el-form-item label="表名">
          <el-input v-model="tableName" :rows="2"  placeholder="请输入表名"/>
          </el-form-item>
          <el-form-item label="主键" >
            <el-select v-model="primaryKey" placeholder="请选择主键">
              <el-option
                v-for="dic in this.dictList"
                :key="dic.dictValue"
                :label="dic.dictValue"
                :value="dic.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        <el-form-item label="描述">
            <el-input v-model="desc" :rows="2" placeholder="请输入表的描述"/>
        </el-form-item>
      </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitTableForm">生 成</el-button>
        </div>
      </el-dialog>

    </div>
  </template>
  
  <script>
  import {analysisText, doCreateTable } from "@/api/textgenerationtable/analysis";
  
  export default {
    name: "Analysis",
    dicts: ['field_type', 'field_is_empty'],
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
        // 字段管理表格数据
        dictList: [],
        // 弹出层标题
        title: "",
        text: "",
        textOpen: false,
        tableOpen: false,
        tableName: "",
        primaryKey: "",
        // 是否显示弹出层
        open: false,
        // 注释时间范围
        daterangeCrtTime: [],
        // 注释时间范围
        daterangeUpdTime: [],
        desc: "",
        // 表单参数
        form: {},
        tableForm: {},
        // 表单校验
        rules: {
          dictName: [
            { required: true, message: "字段名称不能为空", trigger: "blur" }
          ],
          dictValue: [
            { required: true, message: "字段值不能为空", trigger: "blur" }
          ],
          type: [
            { required: true, message: "字段类型不能为空", trigger: "change" }
          ],
          isEmpty: [
            { required: true, message: "是否可以为空不能为空", trigger: "change" }
          ],
        }
      };
    },
    created() {
      this.getList();
      this.reset();
    },
    methods: {
      /** 查询字段管理列表 */
      getList() {
        this.loading = true;
        this.dictList=JSON.parse(JSON.stringify(this.dictList));
        this.loading = false;
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          dictName: null,
          dictValue: null,
          type: null,
          length: null,
          isEmpty: null,
        //   fieldForeignKey: null,
          desc: null,
        //   crtTime: null,
        //   updTime: null
        };
        this.resetForm("form");
      },
      /** 生成按钮操作 */
      handleCreate() {
        this.textOpen = true;
        this.title = "文本解析";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.form = JSON.parse(JSON.stringify(row));
        this.open = true;
        this.title = "修改字段";
      },
      /** 提交按钮 */
      submitForm() {
            for(let i in this.dictList){
                if(this.dictList[i].dictName==this.form.dictName){
                  this.dictList[i]=JSON.parse(JSON.stringify(this.form));
                  break;
                }
            }
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$modal.confirm('是否确认删除字段名称为"' + row.dictName + '"的数据项？').then(function() {
            let index= this.dictList.findIndex(item =>{
                if(item.dictName == row.dictName){
                    return true;
                }
            })
            this.dictList.splice(index, 1);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      submitTextForm() {
        analysisText(this.text).then(response=>{
            this.textOpen=false;
            this.dictList=response.data;
            this.getList();
        });
        

      },
      createTable() {
        this.title="文本生成表结构";
        this.tableOpen=true;
      },
      submitTableForm() {
        doCreateTable(this.tableName,this.primaryKey,this.dictList,this.desc).then(response=>{
          this.tableOpen=false;
          this.$modal.msgSuccess("表生成成功");
        });
      }
    //   /** 导出按钮操作 */
    //   handleExport() {
    //     this.download('textgenerationtable/field/export', {
    //       ...this.queryParams
    //     }, `field_${new Date().getTime()}.xlsx`)
    //   }
    }
  };
  </script>