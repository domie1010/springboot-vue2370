<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm"  ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item label="体温" prop="tw">
        <el-input v-model="dataForm.tw" placeholder="体温"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="dataForm.phone" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="所在地" prop="szd">
        <el-input v-model="dataForm.szd" placeholder="所在地"></el-input>
      </el-form-item>
      <el-form-item label="身体状况" prop="stzk">
        <el-input v-model="dataForm.stzk" placeholder="身体状况"></el-input>
      </el-form-item>
      <el-form-item label="是否接触感染人员" prop="grjc">
        <el-input v-model="dataForm.grjc" placeholder="是否接触感染人员"></el-input>
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
          name: '',
          tw: '',
          szd: '',
          stzk: '',
          phone: '',
          grjc: '',
          bz: ''
        },
        tempKey: -666666 // 临时key, 用于解决tree半选中状态项不能传给后台接口问题. # 待优化
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.$http({
          url: this.$http.adornUrl('/sys/clock/list'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.clockList = treeDataTranslate(data, 'id')
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/sys/clock/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              this.dataForm.name = data.clock.name
              this.dataForm.tw = data.clock.tw
              this.dataForm.szd = data.clock.szd
              this.dataForm.phone = data.clock.phone
              this.dataForm.grjc = data.clock.grjc
              this.dataForm.stzk = data.clock.stzk
              this.dataForm.bz = data.clock.bz
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/clock/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'tw': this.dataForm.tw,
                'phone': this.dataForm.phone,
                'szd': this.dataForm.szd,
                'stzk': this.dataForm.stzk,
                'grjc': this.dataForm.grjc,
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
