<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm"  ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="外出人员" prop="username">
        <el-input v-model="dataForm.username" placeholder="外出人员"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="dataForm.phone" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="外出地点" prop="wcDd">
        <el-input v-model="dataForm.wcDd" placeholder="外出地点"></el-input>
      </el-form-item>
      <el-form-item label="外出原因" prop="wcYy">
        <el-input v-model="dataForm.wcYy" placeholder="外出原因"></el-input>
      </el-form-item>
      <el-form-item label="外出时间" prop="wcTime">
        <el-input type="date" v-model="dataForm.wcTime" placeholder="外出时间"></el-input>
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
          wcTime: '',
          wcYy: '',
          wcDd: '',
          username: '',
          phone: '',
          bz: ''
        },
        tempKey: -666666 // 临时key, 用于解决tree半选中状态项不能传给后台接口问题. # 待优化
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.$http({
          url: this.$http.adornUrl('/sys/goOut/list'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.gooutList = treeDataTranslate(data, 'id')
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/sys/goOut/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              this.dataForm.username = data.goOut.username
              this.dataForm.wcTime = data.goOut.wcTime
              this.dataForm.phone = data.goOut.phone
              this.dataForm.wcDd = data.goOut.wcDd
              this.dataForm.wcYy = data.goOut.wcYy
              this.dataForm.bz = data.goOut.bz
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/goOut/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'wcTime': this.dataForm.wcTime,
                'wcDd': this.dataForm.wcDd,
                'username': this.dataForm.username,
                'phone': this.dataForm.phone,
                'wcYy': this.dataForm.wcYy,
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
