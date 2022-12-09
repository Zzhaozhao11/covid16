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
    <!-- table表格：展示用户信息的地方 -->
    <el-table border stripe v-loading="loading" :data="users" @selection-change="handleSelectionChange" >
      <el-table-column type="selection" width="50" />
      <el-table-column type="name" prop="username" label="姓名" width="90" align="center" />
      <el-table-column prop="password" label="密码" align="center"/>
      <el-table-column prop="phone" label="电话号码" align="center"/>
      <el-table-column prop="company" label="公司" align="center" />
      <el-table-column prop="power" label="权限" align="center"/>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="success" plain  @click="openMod(scope.row)">修改账户</el-button>
          <el-button size="mini" type="danger" plain  @click="openDel(scope.row)">删除账户</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页器 -->
    <el-pagination :current-page="page" :page-size="limit" :page-sizes="[3, 5, 7]" style="padding: 20px 0;"
      layout="prev, pager, next, jumper, ->, sizes, total" @current-change="getUsers" @size-change="handleSizeChange" />


    <!-- 添加确诊展示 -->
    <el-dialog width="30%" title="修改信息" :visible.sync="innerVisible" append-to-body>
        <!-- 病患管理 -->
        <el-form :rules="addModUserRules" label-width="94px" ref="addModUserForm" :model="ModUser">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="ModUser.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="ModUser.password"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="ModUser.phone"></el-input>
          </el-form-item>
          <el-form-item label="公司" prop="company">
            <el-input v-model="ModUser.company"></el-input>
          </el-form-item>
          <el-form-item label="权限" prop="power">
            <el-radio-group v-model="ModUser.power">
              <el-radio label="普通用户"></el-radio>
              <el-radio label="管理员"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="IsLegalModUser">确 定</el-button>
        </div>
      </el-dialog>
  </div>
</template>
  
<script>

export default {
  name: 'AclUserList',

  data() {
    return {
      ModUser: {   //添加病患下一步表单
        username: '',
        password: '',
        phone: '',
        power: '',
        company: '',
      },
      tempSearchObj: { // 收集搜索条件输入的对象
        username: ''
      },
      innerVisible: false,  //内部显示
      selectedIds: [], // 所有选择的user的id的数组
      users: [{
      }], // 当前页的用户列表
      page: 1, // 当前页码
      limit: 3, // 每页数量
      user: {}, // 当前要操作的user
      nowrowid:-1, //当前的点击行
      loading: false, // 是否正在提交请求中
      addModUserRules: {
        username: [{ required: true, message: '请填写用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请填写密码', trigger: 'blur' }],
        phone: [{ required: true, message: '请填写电话', trigger: 'blur' }],
        power: [{ required: true, message: '请填写权限', trigger: 'blur' }],
        company: [{ required: true, message: '请填写公司', trigger: 'blur' }],
      }
    }
  },

  //发请求一般情况下，我们都是在mounted去发，但是也可以在created内部去发
  created() {
    this.getUsers()
  },
  
  methods: {
   async addModUser(){
       let res=await this.$http.login.ReqMod(this.nowrowid,this.ModUser);
       if(res.data.code!=200){
        this.$message.error('失败');
        return;
       }
       this.$message({type:'success',message:'成功！'});
       this.cancel();
       this.getUsers();
    },
   IsLegalModUser(){  //判断修改是否合法
    this.$refs.addModUserForm.validate((valid) => {
        if (valid) {
          this.addModUser();
        } else {
          return false;
        }
      });
    },
        /* 
    取消用户的保存或更新
    */
    cancel() {
      this.innerVisible = false;
      this.ModUser={};
    },
    openMod(row){  //打开修改用户提示框
      this.innerVisible=true;
      this.nowrowid=row.id;
    },
    openDel(row) {  //打开删除用户弹窗
        this.$confirm('是否确认删除用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.DelUser(row.id);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });          
        });
      },
    async DelUser(id){   //axios请求删除用户
        let result=await this.$http.login.ReqDelete(id);
        if(result.data.code!=200){
          this.$message.error("操作失败！");
          this.getUsers();
          return;
        }
        this.$message({
          type:'success',
          message:'操作成功！'
        })
        this.getUsers();
    },
      /* 
    列表选中状态发生改变的监听回调
    */
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id)
    },
    /* 
    获取分页列表
    */
    async getUsers(page = 1) {
      this.page = page
      const { limit } = this
      this.loading = true
      this.users = [];
      const result = await this.$http.login.ReqSelectAllUser(limit, page);
      if (!result.data.data) {
        this.$message.error('错误');
        return;
      }
      for (const user of result.data.data) { //遍历数组
        let {id,username,password,phone,company,power} = user;
        let newUser = {id,username,password,phone,company,power};
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
    async AddUserAndmijie() {  
      const res = await this.$http.admin.reqAddBasic(this.adduser);
      if (!res.data.data.id) {  //错误情况
        this.$message.error('错误！');
        return;
      }
      const id = res.data.data.id;
      const lsres = await this.$http.admin.ReqAddInformation(id, this.mijie);
      if (lsres.data.code!=200) {  //错误情况
        this.$message.error('错误！');
        return;
      }
      this.$message({ message: '成功添加', type: 'success' });
      this.cancel(); // 关闭添加界面
      this.getUsers(); //重新发请求
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
      let result = await this.$http.login.ReqSelectByName(this.tempSearchObj.username);
      if (result.status != 200) {
        this.$message.error('错误');
        return;
      } else if (result.data.code === 400) {
        this.$message('未查询到');
        this.loading = false;
        return;
      }
      for (const user of result.data.data) { //遍历数组
        let {id,username,password,phone,company,power} = user;
        let newUser = {id,username,password,phone,company,power};
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