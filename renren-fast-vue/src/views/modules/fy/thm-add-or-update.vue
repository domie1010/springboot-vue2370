<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm"  ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="id" prop="id">
        <el-input v-model="dataForm.id" placeholder="id"></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="联系方式"></el-input>
      </el-form-item>
      <el-form-item label="出行地点" prop="place">
        <el-input v-model="dataForm.place" placeholder="出行地点"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="bz">
        <el-input v-model="dataForm.bz" placeholder="备注"></el-input>
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
        username: '',
        place: '',
        mobile: '',
        bz: ''
      },
      tempKey: -666666 // 临时key, 用于解决tree半选中状态项不能传给后台接口问题. # 待优化
    }
  },
  methods: {
    init (id) {
      this.dataForm.id = id || 0
      this.$http({
        url: this.$http.adornUrl('/sys/thm/list'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        this.thmList = treeDataTranslate(data, 'id')
      }).then(() => {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      }).then(() => {
        if (this.dataForm.id) {
          alert(id)
          this.$http({
            url: this.$http.adornUrl(`/sys/thm/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            this.dataForm.username = data.thm.username
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/sys/thm/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'id': this.dataForm.id || undefined,
              'username': this.dataForm.username,
              'mobile': this.dataForm.mobile,
              // 'cxTime': this.dataForm.cxTime,
              'place': this.dataForm.place,
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
