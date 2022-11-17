<template>
  <div>
    <el-table :header-cell-style="headStyle"
    :cell-style="headStyle"
     :data="tableData" 
     style="width: 100%" height="35rem">
      <el-table-column prop="name" label="姓名" width="90"></el-table-column>
      <el-table-column prop="uid" label="身份证号" width="180"></el-table-column>
      <el-table-column prop="gender" label="性别" width="90"></el-table-column>
      <el-table-column label="住院时间" width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.indate }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="source" label="感染来源" width="180"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData: [{
        name: '赵昱哲',
        uid: '123456',
        gender: '女',
        houseaddress: '厕所',
        indate: '2023-05-02',
        date: '2023-05-02',
        source: '吃屎',

      }]
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    headStyle() {
      return "text-align:center"
    }
  }
}
</script>
<style lang="css">
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>