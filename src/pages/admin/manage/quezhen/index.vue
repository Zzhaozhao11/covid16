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
      <!-- 添加与批量添加按钮 -->
      <el-button type="primary" @click="showAddUser">添 加</el-button>
      <el-button type="danger" @click="revomveUsers" :disabled="selectedIds.length === 0">批量删除</el-button>
    </div>

    <!-- table表格：展示用户信息的地方 -->
    <el-table border stripe v-loading="listLoading" :data="users" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="50" />

      <el-table-column type="name" prop="name" label="姓名" width="90" align="center" />

      <el-table-column prop="personId" label="身份证号"  />
      <el-table-column prop="gender" label="性别" width="50"/>
      <el-table-column prop="houseAddress" label="住宅" width="50"/>

      <el-table-column prop="indate" label="住院时间"  />
      <el-table-column prop="source" label="感染来源"  />

      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="{row}">
          <el-button size="mini" type="success" round @click="Istozhiyu = true">治愈</el-button>
          <el-button size="mini" type="danger" round>死亡</el-button>
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页器 -->
    <el-pagination :current-page="page" :total="total" :page-size="limit" :page-sizes="[3, 10, 20, 30, 40, 50, 100]"
      style="padding: 20px 0;" layout="prev, pager, next, jumper, ->, sizes, total" @current-change="getUsers"
      @size-change="handleSizeChange" />
    <!-- 对话框的结构 -->
    <el-dialog :title="user.id ? '修改用户' : '添加用户'" :visible.sync="dialogUserVisible" >
      <!-- 添加用户 -->
      <el-form :rules="adduserRules" label-width="80px" ref="adduserForm" :model="adduser" class="elform">
        <el-form-item  label="姓名" prop="name">
          <el-input v-model="adduser.name"></el-input>
        </el-form-item>
        <el-form-item  label="身份证号" prop="uid">
          <el-input v-model="adduser.personId"></el-input>
        </el-form-item>
        <el-form-item  label="年龄" prop="age">
          <el-input v-model="adduser.age"></el-input>
        </el-form-item>
        <el-form-item  label="性别" prop="gender">
          <el-input v-model="adduser.gender"></el-input>
        </el-form-item>
        <el-form-item  label="住宅" prop="houseaddress">
          <el-input v-model="adduser.houseAddress"></el-input>
        </el-form-item>
        <el-form-item  label="手机号" prop="phone">
          <el-input v-model="adduser.phone"></el-input>
        </el-form-item>
      </el-form>
      
      <div slot="footer" class="dialog-footer" >
        <el-button @click="cancel">取 消</el-button>
        <el-button :loading="loading" type="primary" @click="addUserNext">确 定</el-button>
      </div>
      <el-dialog
      width="30%"
      title="病患管理"
      :visible.sync="innerVisible"
      append-to-body>
     <!-- 病患管理 -->
         
     <el-form :rules="addbinghuanRules" label-width="94px" ref="addbinghuanForm" :model="binghuan">
        <el-form-item  label="感染源" prop="source">
          <el-input v-model="binghuan.source"></el-input>
        </el-form-item>
        <el-form-item  label="住院时间" prop="indate">
          <el-input v-model="binghuan.indate"></el-input>
        </el-form-item>
        <el-form-item  label="症状" prop="symptom">
          <el-input v-model="binghuan.symptoms"></el-input>
        </el-form-item>
        <el-form-item  label="诊治医院" prop="hospital">
          <el-input v-model="binghuan.hospital" ></el-input>
        </el-form-item>
        <el-form-item  label="是否为重症" prop="condition">
          <el-input v-model="binghuan.condition"></el-input>
        </el-form-item>
        <el-form-item  label="备注" prop="comments">
          <el-input v-model="binghuan.comments"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button  type="primary" @click="addbinghuan">确 定</el-button>
      </div>
    </el-dialog>
    </el-dialog>


  </div>
</template>
  
<script lang="js">
import cloneDeep from 'lodash/cloneDeep'

export default {
  name: 'AclUserList',

  data() {
    return {
      adduser:{  //添加用户的表单
         name:'',
         personId:'',
        age:'',
        gender:'',
        houseAddress:'',
        phone:''
      },
      binghuan:{   //添加病患下一步表单
        source:'',
        indate:'',
        symptoms:'', 
        hospital:'',
        condition:'',
        comments:'',
      },
      innerVisible:false,  //内部显示
      listLoading: false, // 是否显示列表加载的提示
      tempSearchObj: { // 收集搜索条件输入的对象
        id: ''
      },
      selectedIds: [], // 所有选择的user的id的数组
      users: [{
        name: '赵昱哲',
        personId: '123456',
        gender: '女',
        houseAddress: '厕所',
        indate: '2023-05-02',
        source: '吃屎',
      }], // 当前页的用户列表
      page: 1, // 当前页码
      limit: 3, // 每页数量
      total: 0, // 总数量
      user: {}, // 当前要操作的user
      dialogUserVisible: false, // 是否显示用户添加/修改的dialog
      loading: false, // 是否正在提交请求中
      allRoles: [], // 所有角色列表
      userRoleIds: [], // 用户的角色ID的列表
      isIndeterminate: false, // 是否是不确定的
      checkAll: false, // 是否全选
      adduserRules:{  //添加用户的验证规则
        name:[{required:true,message: '请填写姓名', trigger: 'blur'}],
        personId:[{required:true,message: '请填写身份证号', trigger: 'blur'}],
        age:[{required:true,message: '请填写年龄', trigger: 'blur'}],
        gender:[{required:true,message: '请填写性别', trigger: 'blur'}],
        houseAddress:[{required:true,message: '请填写住宅', trigger: 'blur'}],
        phone:[{required:true,message: '请填写手机号', trigger: 'blur'}],
      },
      addbinghuanRules:{
        source:[{required:true,message: '请填写感染源', trigger: 'blur'}],
        indate:[{required:true,message: '请填写感染源', trigger: 'blur'}],
        symptoms:[{required:true,message: '请填写感染源', trigger: 'blur'}],
        hospital:[{required:true,message: '请填写感染源', trigger: 'blur'}],
        condition:[{required:true,message: '请填写感染源', trigger: 'blur'}],
        comments:[{required:true,message: '请填写感染源', trigger: 'blur'}],
      }
    }
  },

  //发请求一般情况下，我们都是在mounted去发，但是也可以在created内部去发
  created() {
    this.getUsers()
  },

  methods: {
    /* 
    显示指定角色的界面
    */
    // showAssignRole(user) {
    //   this.user = user
    //   this.dialogRoleVisible = true
    //   this.getRoles()
    // },

    /* 
    全选勾选状态发生改变的监听
    */
    handleCheckAllChange(value) {// value 当前勾选状态true/false
      // 如果当前全选, userRoleIds就是所有角色id的数组, 否则是空数组
      this.userRoleIds = value ? this.allRoles.map(item => item.id) : []
      // 如果当前不是全选也不全不选时, 指定为false
      this.isIndeterminate = false
    },

    /* 
    异步获取用户的角色列表
    */
    async getRoles() {
      // const result = await this.$http.user.getRoles(this.user.id)
      // const { allRolesList, assignRoles } = result.data
      // this.allRoles = allRolesList
      // this.userRoleIds = assignRoles.map(item => item.id)

      // this.checkAll = allRolesList.length === assignRoles.length
      // this.isIndeterminate = assignRoles.length > 0 && assignRoles.length < allRolesList.length
    },

    /* 
    角色列表选中项发生改变的监听
    */
    handleCheckedChange(value) {
      const { userRoleIds, allRoles } = this
      this.checkAll = userRoleIds.length === allRoles.length && allRoles.length > 0
      this.isIndeterminate = userRoleIds.length > 0 && userRoleIds.length < allRoles.length
    },

    /* 
    请求给用户进行角色授权
    */
    // async assignRole() {
    //   const userId = this.user.id
    //   const roleIds = this.userRoleIds.join(',')
    //   this.loading = true
    //   const result = await this.$API.user.assignRoles(userId, roleIds)
    //   this.loading = false
    //   this.$message.success(result.message || '分配角色成功')
    //   this.resetRoleData()

    //   // console.log(this.$store.getters.name, this.user)
    //   if (this.$store.getters.name === this.user.username) {
    //     window.location.reload()
    //   }
    // },

    /* 
    重置用户角色的数据
    */
    // resetRoleData() {
    //   this.dialogRoleVisible = false
    //   this.allRoles = []
    //   this.userRoleIds = []
    //   this.isIndeterminate = false
    //   this.checkAll = false
    // },

    /* 
    自定义密码校验
    */
    validatePassword(rule, value, callback) {
      if (!value) {
        callback('密码必须输入')
      } else if (!value || value.length < 6) {
        callback('密码不能小于6位')
      } else {
        callback()
      }
    },
    /* 
    根据输入进行搜索
    */
    search() {
      // this.searchObj = { ...this.tempSearchObj }
      // this.getUsers()
      this.$http.default.admin.reqGetBasic(30).then(res=>{
          if(res.data.msg==='成功')
          console.log(res.data.data);
          else
          console.log(res.msg);
      }).catch(err=>{
        console.log(err);
          alert(err);
      })
      
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
    显示添加用户的界面
    */
    showAddUser() {
      this.user = {}
      this.dialogUserVisible = true

      // this.$nextTick(() => this.$refs.userForm.clearValidate())
    },

    /* 
    删除所有选中的用户
    */
    revomveUsers() {
      this.$confirm('确定删除吗?').then(async () => {
        await this.$API.user.removeUsers(this.selectedIds)
        this.$message.success('删除成功')
        this.getUsers()
      }).catch(() => {
        this.$message.info('取消删除')
      })
    },

    /* 
    列表选中状态发生改变的监听回调
    */
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id)
    },

    /* 
    显示更新用户的界面
    */
    showUpdateUser(user) {
      this.user = cloneDeep(user)
      this.dialogUserVisible = true
    },

    /* 
    删除某个用户
    */
    async removeUser(id) {
      await this.$API.user.removeById(id)
      this.$message.success('删除成功')
      this.getUsers(this.users.length === 1 ? this.page - 1 : this.page)
    },

    /* 
    获取分页列表
    */
    async getUsers(page = 1) {
       this.page = page
       const { limit } = this
       this.listLoading = true
       const result = await this.$http.admin.ReqSelectAllPatient(page,limit);
      this.listLoading = false
      console.log(result);
      // const { items, total } = result.data
      // this.users = items.filter(item => item.username !== 'admin')
      // this.total = total - 1
      // this.selectedIds = []
    },

    /* 
    处理pageSize发生改变的监听回调
    */
    handleSizeChange(pageSize) {
      this.limit = pageSize
      this.getUsers()
    },

    /* 
    取消用户的保存或更新
    */
    cancel() {
      this.dialogUserVisible = false
      this.innerVisible=false;
      this.user = {}
      this.adduser={};
      this.binghuan={};
    },

    /* 
    保存或者更新用户
    */
    addOrUpdate() {
      this.$refs.userForm.validate(valid => {
        if (valid) {
          const { user } = this
          this.loading = true
          this.$API.user[user.id ? 'update' : 'add'](user).then((result) => {
            this.loading = false
            this.$message.success('保存成功!')
            this.getUsers(user.id ? this.page : 1)
            this.user = {}
            this.dialogUserVisible = false
          })
        }
      })
    },
    addUserNext(){  //添加用户的下一步
      this.$refs.adduserForm.validate((valid) => {
          if (valid) {
            this.innerVisible=true; //显示子级表单
          } else {
            return false;
          }
        });
    },
    addbinghuan(){ //添加用户的下一步
       this.$refs.addbinghuanForm.validate((valid)=>{
        if (valid) {
          
          } else {
            return false;
          }
       });
    },
    async AddUserAndBinghuan(){
      await this.$http.default.admin.reqAddBasic(this.user);
      await this.$http.default.admin.reqAddBasic(this.binghuan);
    }
  }
}
</script>
<style lang="css">
.app-container {
  margin: 1rem 2rem;
}
.elform {
  margin:0 5rem;
}
</style>