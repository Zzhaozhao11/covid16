<template>
  <div class="app-container">
    <el-form inline>
      <!-- 表单元素 -->
      <el-form-item>
        <el-input v-model="tempSearchObj.username" placeholder="用户名" />
      </el-form-item>
      <!-- 查询与情况的按钮 -->
      <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
      <el-button type="default" @click="resetSearch">清空</el-button>
    </el-form>
    <div style="margin-bottom: 20px">
      <!-- table表格：展示用户信息的地方 -->
      <el-table border stripe v-loading="loading" :data="users" @selection-change="handleSelectionChange">

        <el-table-column type="selection" width="50" />

        <el-table-column type="name" prop="name" label="姓名" width="90" align="center" />

        <el-table-column prop="personId" label="身份证号" />
        <el-table-column prop="gender" label="性别" width="50" />
        <el-table-column prop="houseAddress" label="住宅" />

        <el-table-column prop="outDate" label="住院时间" />
        <el-table-column prop="status" label="现状" />

        <el-table-column label="操作" width="300" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="showMes(scope.row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页器 -->
      <el-pagination :current-page="page" :page-size="limit" :page-sizes="[7, 3, 5]" style="padding: 20px 0;"
        layout="prev, pager, next, jumper, ->, sizes, total" @current-change="getUsers"
        @size-change="handleSizeChange" />
    </div>


    <el-dialog title="详细信息" :visible.sync="dialogVisible" width="30%">
      <el-descriptions direction="vertical" :column="4" border>
    <el-descriptions-item label="治疗医院">{{mes.comments}}</el-descriptions-item>
    <el-descriptions-item label="是否重症">{{mes.hospital}}</el-descriptions-item>
    <el-descriptions-item label="入院日期">{{mes.inDate}}</el-descriptions-item>
    <el-descriptions-item label="出院日期">{{mes.outDate}}</el-descriptions-item>
    <el-descriptions-item label="感染源">{{mes.source}}</el-descriptions-item>
    <el-descriptions-item label="症状">{{mes.symptoms}}</el-descriptions-item>
    <el-descriptions-item label="备注" v-show="mes.condition">{{mes.condition}}</el-descriptions-item>

</el-descriptions>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
  
<script lang="js">

export default {
  name: 'AclUserList',

  data() {
    return {
      tempSearchObj: { // 收集搜索条件输入的对象
        username: ''
      },
      selectedIds: [], // 所有选择的user的id的数组
      users: [{

      }], // 当前页的用户列表
      page: 1, // 当前页码
      limit: 3, // 每页数量
      user: {}, // 当前要操作的user
      loading: false, // 是否正在提交请求中
      dialogVisible: false, //显示查看详情
      mes: {
        comments:"富士达",
        condition:"大苏打",
        deadDate:null,
        divideAddress:null,
        divideDate:null,
        gender:null,
        hospital:"是",
        houseAddress:null,
        id:44,
        inDate:"2022-11-29",
        name:null,
        outDate:"2022-11-11",
        personId:null,
        source:"问起我",
      }
    }
  },

  //发请求一般情况下，我们都是在mounted去发，但是也可以在created内部去发
  created() {
    this.getUsers()
  },

  methods: {



    /* 
列表选中状态发生改变的监听回调
*/
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id)
    },
    /* 
    根据输入进行搜索
    */
    async search() {
      // this.searchObj = { ...this.tempSearchObj }
      // this.getUsers()
      this.loading = true;
      this.users = [];
      if(this.tempSearchObj.username===undefined)
      this.tempSearchObj.username='';
      let result = await this.$http.admin.ReqSelectAllCureByName(this.tempSearchObj.username);
      if (result.status != 200) {
        this.$message.error('错误');
        return;
      } else if (result.data.code === 400) {
        this.$message('未查询到');
        this.loading = false;
        return;
      }
      for (const user of result.data.data) { //遍历数组
        let { id, name, personId, gender, houseAddress, outDate } = user;
        let newUser = { id, name, personId, gender, houseAddress, outDate, status: '无' };
        this.users.push(newUser);
      }
      this.loading = false;
    },

    /* 
    重置输入后搜索
    */
    resetSearch() {
      this.searchObj = {
        id: ''
      }
      this.tempSearchObj = {
        id: ''
      }
      this.getUsers()
    },
    /* 
    获取分页列表
    */
    async getUsers(page = 1) {
      this.page = page
      const { limit } = this
      this.loading = true
      this.users = [];
      const result = await this.$http.admin.ReqSelectAllCure(limit, page);
      if (!result.data.data) {
        this.$message.error('错误');
        return;
      }
      for (const user of result.data.data) { //遍历数组
        let { id, name, personId, gender, houseAddress, outDate } = user;
        let newUser = { id, name, personId, gender, houseAddress, outDate, status: '无' };
        this.users.push(newUser);
      }
      this.loading = false
      this.selectedIds = []

    },

    /* 
    处理pageSize发生改变的监听回调
    */
    handleSizeChange(pageSize) {
      this.limit = pageSize
      this.getUsers()
    },
    async showMes(row) {  //查看详细信息
      let res = await this.$http.admin.ReqGetCureMessage(row.id);
      if(res.data.code!=200){
        this.$message.error('错误');
        return;
      }
      this.mes=res.data.data;
      this.dialogVisible=true;
    }
  }
}
</script>
<style lang="css">
.app-container {
  margin: 1rem 2rem;
}

.elform {
  margin: 0 5rem;
}
</style>