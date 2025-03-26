<template>
  <el-dialog
    title
    :close-on-click-modal="false"
    :visible.sync="visible"
    center
    width="65%"
    :before-close="closedialog"
  >
    <iframe :src="previewUrl" width="100%" height="100%" frameborder="0" style="height: 600px;"></iframe>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      dataForm: {
        id: 0
      },
      done: true,
      previewUrl: ''
    }
  },
  methods: {
    inits (id) {
      this.dataForm.id = id || 0
      this.$nextTick(() => {
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl('/file/getFileDetail'),
            method: 'get',
            params: this.$http.adornParams({
              id: this.dataForm.id
            })
          }).then(({ data }) => {
            if (data.url && data.url !== 'null') {
              this.visible = true
              this.previewUrl =
                window.SITE_CONFIG.baseUrl + '/virtuel' + '/' + data.url
              console.log(this.previewUrl)
              this.opentime = new Date()
            } else {
              this.visible = false
            }
            this.dataListLoading = false
          })
        }
      })
    },
    closedialog () {
      if ((this.visible = true)) {
        this.closetime = new Date()
        this.visible = false
      } else {
        return false
      }
    }
  }
}
</script>
