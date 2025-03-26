<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm"  ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="申请标题" prop="title">
        <el-input v-model="dataForm.title" placeholder="申请标题"></el-input>
      </el-form-item>
      <el-form-item label="申请内容" prop="content">
        <el-input v-model="dataForm.content" placeholder="申请内容"></el-input>
      </el-form-item>
      <el-form-item label="申请人" prop="applyUser">
        <el-input  v-model="dataForm.applyUser" placeholder="复工申请人"></el-input>
      </el-form-item>
      <el-form-item label="申请时间" prop="applyTime">
        <el-input type="date" v-model="dataForm.applyTime" placeholder="申请时间"></el-input>
      </el-form-item>
          <el-form-item label="备注说明" prop="bz">
         <el-input v-model="dataForm.bz" placeholder="备注说明"></el-input>
       </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { treeDataTranslate } from '@/utils'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          title: '',
          content: '',
          applyUser: '',
          applyTime: '',
          bz: ''
        },
        tempKey: -666666 // 临时key, 用于解决tree半选中状态项不能传给后台接口问题. # 待优化
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.$http({
          url: this.$http.adornUrl('/sys/toworkApply/list'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.toworkApplyList = treeDataTranslate(data, 'id')
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/sys/toworkApply/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/toworkApply/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'title': this.dataForm.title,
                'content': this.dataForm.content,
                'applyUser': this.dataForm.applyUser,
                'applyTime': this.dataForm.applyTime,
                'bz': this.dataForm.bz
              })
            }).then(({data}) => {
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
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
