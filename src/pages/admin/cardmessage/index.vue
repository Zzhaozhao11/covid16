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
        <el-table border v-loading="loading" :data="users" @selection-change="handleSelectionChange" :row-class-name="tableRowClassName">
  
          <el-table-column type="selection" width="50" />
  
          <el-table-column type="name" prop="name" label="姓名" width="90" align="center" />
  
          <el-table-column prop="location" label="现在地址" align="center"/>
          <el-table-column prop="temperature" label="体温" align="center" width="50"/>
          <el-table-column prop="health" label="健康状况"  />
          <el-table-column prop="clockDate" label="打卡时间" align="center"/>
          <el-table-column prop="danger" label="是否到过高风险地区" align="center"/>
          <el-table-column prop="contact" label="是否接触过确诊病例" align="center"/>
          <el-table-column prop="comments" label="备注" align="center"/>
          <!-- <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="success" @click="showMes(scope.row)">治疗信息</el-button>
              <el-button size="mini"  @click="showMes(scope.row)">检测历史</el-button>
            </template>
          </el-table-column> -->
        </el-table>
        <!-- 分页器 -->
        <el-pagination :current-page="page" :page-size="limit" :page-sizes="[3, 5, 7,1]" style="padding: 20px 0;"
          layout="prev, pager, next, jumper, ->, sizes, total" @current-change="getUsers"
          @size-change="handleSizeChange" />
      </div>
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
  
        }
      }
    },
  
    //发请求一般情况下，我们都是在mounted去发，但是也可以在created内部去发
    created() {
      this.getUsers()
    },
  
    methods: {
      tableRowClassName({row, rowIndex}) {  //背景颜色
        if (row.danger==='是'||row.contact==='是') {
          return 'warning-row';
        }
        return '';
      },
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
        let result = await this.$http.card.reqSearchCard(this.page,this.limit, this.tempSearchObj.username);
        if (result.status != 200) {
          this.$message.error('错误');
          return;
        } else if (result.data.code === 400) {
          this.$message('未查询到');
          this.loading = false;
          return;
        }
        for (const user of result.data.data) { //遍历数组
          let { id, name, location, health, temperature, clockDate, danger,contact,comments } = user;
          comments=comments||"无";
          let newUser = { id, name, location, health, temperature, clockDate, danger,contact,comments };
          this.users.push(newUser);
        }
        this.loading = false;
      },
  
      /* 
      重置输入后搜索
      */
      resetSearch() {
        this.tempSearchObj = {
          id: ''
        }
        this.getUsers()
      },
      /* 
      获取分页列表
      */
      async getUsers(page = 1) {
        if(this.tempSearchObj.id){
          this.search();
          return;
        }
        this.page = page
        const { limit } = this
        this.loading = true;
        this.users = [];
        const result = await this.$http.card.reqGetCard(limit, page);
        this.loading = false;
        if (!result.data.data) {
          this.$message.error('错误');
          this.loading=false;
          return;
        }
        for (const user of result.data.data) { //遍历数组
          let { id, name, location, health, temperature, clockDate, danger,contact,comments } = user;
          comments=comments||"无";
          let newUser = { id, name, location, health, temperature, clockDate, danger,contact,comments };
          this.users.push(newUser);
        };
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
  <style scoped>
  .app-container {
    margin: 1rem 2rem;
  }
  
  .elform {
    margin: 0 5rem;
  }
 ::v-deep .warning-row {
  background: oldlace;
  }
  </style>