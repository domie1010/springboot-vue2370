<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.fileName" placeholder="请输入查询文件名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button typr="primary"  icon="el-icon-search" @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('sys:file:delete')" type="primary" @click="addOrUpdateHandle()">资料上传<i class="el-icon-upload el-icon--right"></i></el-button>
        <el-button  v-if="isAuth('uFile:uploadFile')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;"
    >
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column prop="id" header-align="center" align="center" label="id"></el-table-column>
      <el-table-column prop="classify" header-align="center" align="center" label="资料分类"></el-table-column>
      <el-table-column prop="fileName" header-align="center" align="center" label="文件名称"></el-table-column>
      <el-table-column prop="fileType" header-align="center" align="center" label="文件类型"></el-table-column>

      <el-table-column fixed="right" header-align="center" align="center" width="200" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="saveHandle(scope.row.id)">查看附件</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    <savedate v-if="savedateVisible" ref="Savedate" @refreshDataList="getDataList"></savedate>
  </div>
</template>

<script>
import AddOrUpdate from './file-add-or-update'
import Savedate from './file_detail'

export default {
  data () {
    return {
      dataForm: {
        key: ''
      },
      option: {},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      savedateVisible: false
    }
  },
  components: {
    AddOrUpdate, Savedate
  },
  activated () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/file/list'),
        method: 'post',
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          fileName: this.dataForm.fileName
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle (val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    saveHandle (id) {
      // eslint-disable-next-line no-unused-vars
      var ids = id
      this.savedateVisible = true
      this.$nextTick(() => {
        this.$refs.Savedate.inits(id)
      })
    },
    // 删除
    deleteHandle (id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(
        `确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`,
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        this.$http({
          url: this.$http.adornUrl('/file/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      })
    }

  }
}
</script>
