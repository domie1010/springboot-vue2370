<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="80px"
    >
      <el-form-item label="资料类型" prop="classify">
        <el-input v-model="dataForm.classify" placeholder="资料类型"></el-input>
      </el-form-item>
      <el-form-item label="上传文件" style="margin-left: 7%;" prop="File">
        <el-upload
          class="avatar-uploader"
          ref="upload"
          :action="url"
          :on-preview="handlePreview"
          :auto-upload="false"
          :before-upload="onBeforeUpload"
          :on-change="handleChange"
          :data="dataForm"
        >
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      hidden: true,
      visible: false,
      fileList: [],
      option: {},
      url: "",
      file: [],
      sujetNames: "",
      dataForm: {
        id: 0,
        classify:'',
        File: ""
      },

    }
  },
  methods: {
    init(id) {
      this.dataForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id !== 0) {
          this.$http({
            url: this.$http.adornUrl("/sys/pxworker/update"),
            method: "get"
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            }
          });
        }
      });
    },

    onBeforeUpload(file){
      console.log(this.dataForm.classify+"s");
      console.log(this.classify+"s");
      let fd = new FormData();
      fd.append('file',file);//传文件
      fd.append('classify',this.dataForm.classify);//传数据
      this.$http({
        url: this.$http.adornUrl("/uFile/uploadFile"),
        method: "post",
        data:fd
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.visible = false
              this.$emit('refreshDataList')
            }
          })
        }
      });

    },
    onProvincesChange() {
      this.sujetNames=this.$refs['myCascader'].currentLabels;
    },


    // 表单提交
    dataFormSubmit() {
      this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
    },
    handlePreview(file) {
    },
    handleChange(file, fileList) {
      this.dataForm.file = URL.createObjectURL(file.raw);
    },

  }
};
</script>
