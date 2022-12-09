<template>
  <div class="app-container">
    <div style="margin-bottom: 20px">
      <!-- 添加与批量添加按钮 -->
      <el-button type="primary" @click="showAddUser">添 加</el-button>
    </div>

    <!-- table表格：展示用户信息的地方 -->
    <el-table border stripe v-loading="loading" :data="users" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="50" />
      <el-table-column type="name" prop="name" label="姓名" width="90" align="center" />

      <el-table-column prop="personId" label="身份证号" />
      <el-table-column prop="gender" label="性别" width="50" />
      <el-table-column prop="divideAddress" label="隔离地址"  />

      <el-table-column prop="divideDate" label="隔离开始时间" />
      <el-table-column label="详情" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="showMes(scope.row)">查看详情</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="300" align="center">
        
        <template slot-scope="scope">
          <el-button size="mini" type="success" round  @click="openAddRnd(scope.row)">隔离完成</el-button>
          <el-button size="mini" type="danger" round @click="openAddpatient(scope.row)">转为确诊</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页器 -->
    <el-pagination :current-page="page" :page-size="limit" :page-sizes="[7, 3, 5]" style="padding: 20px 0;"
      layout="prev, pager, next, jumper, ->, sizes, total" @current-change="getUsers" @size-change="handleSizeChange" />
    <!-- 对话框的结构 -->
    <el-dialog :title="user.id ? '修改用户' : '添加用户'" :visible.sync="dialogUserVisible">
      <!-- 添加用户 -->
      <el-form :rules="adduserRules" label-width="80px" ref="adduserForm" :model="adduser" class="elform">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="adduser.name" style="width:30%;"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age" >
          <el-input v-model="adduser.age" style="width:15%;"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="adduser.gender">       
            <el-radio label="男" ></el-radio>
            <el-radio label="女" style="margin-left: 50px;"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="身份证号" prop="personId" style="width:50%;">
          <el-input v-model="adduser.personId"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone" style="width:50%;">
          <el-input v-model="adduser.phone"></el-input>
        </el-form-item>
        <el-form-item label="住宅" prop="houseAddress">
          <el-input v-model="adduser.houseAddress"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button :loading="loading" type="primary" @click="addUserNext">确 定</el-button>
      </div>
      <el-dialog width="30%" title="密接" :visible.sync="innerVisible" append-to-body>
        <!-- 密接管理 -->

        <el-form :rules="addmijieRules" label-width="94px" ref="addmijieForm" :model="mijie">
          <el-form-item label="接触来源" prop="source">
            <el-input v-model="mijie.source"></el-input>
          </el-form-item>
          <el-form-item label="住院时间" prop="divideDate">
              <el-date-picker 
              type="date" placeholder="选择日期" 
              v-model="mijie.divideDate"
              value-format="yyyy-MM-dd"></el-date-picker>
          </el-form-item>
          <el-form-item label="隔离地址" prop="divideAddress">
            <el-input v-model="mijie.divideAddress"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="addmijie">确 定</el-button>
        </div>
      </el-dialog>
    </el-dialog>

  <!-- 查看详情 -->
    <el-dialog title="详细信息" :visible.sync="MesVisible" width="30%" >
      <el-descriptions direction="vertical" :column="4" border>
    <el-descriptions-item label="ID">{{mes.basicId||"未知"}}</el-descriptions-item>
    <el-descriptions-item label="隔离地址">{{mes.divideAddress}}</el-descriptions-item>
    <el-descriptions-item label="隔离日期">{{mes.divideDate}}</el-descriptions-item>
    <el-descriptions-item label="接触来源">{{mes.source}}</el-descriptions-item>

</el-descriptions>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="MesVisible = false">关闭</el-button>
      </span>
    </el-dialog>


    <!-- 添加确诊展示 -->
    <el-dialog width="30%" title="确诊信息" :visible.sync="innerVisible" append-to-body>
        <!-- 病患管理 -->
        <el-form :rules="addbinghuanRules" label-width="94px" ref="addbinghuanForm" :model="binghuan">
          <el-form-item label="感染源" prop="source">
            <el-input v-model="binghuan.source"></el-input>
          </el-form-item>
          <el-form-item label="住院时间" prop="inDate">
              <el-date-picker 
              type="date" placeholder="选择日期" 
              v-model="binghuan.inDate"
              value-format="yyyy-MM-dd"></el-date-picker>
          </el-form-item>
          <el-form-item label="症状" prop="symptoms">
            <el-input v-model="binghuan.symptoms"></el-input>
          </el-form-item>
          <el-form-item label="诊治医院" prop="hospital">
            <el-input v-model="binghuan.hospital"></el-input>
          </el-form-item>
          <el-form-item label="是否为重症" prop="condition">
            <el-radio-group v-model="binghuan.condition">
              <el-radio label="是"></el-radio>
              <el-radio label="否"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注" prop="comments">
            <el-input v-model="binghuan.comments"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="IsLegalbinghuan">确 定</el-button>
        </div>
      </el-dialog>
  </div>
</template>
  
<script lang="js">
import cloneDeep from 'lodash/cloneDeep'

export default {
  name: 'AclUserList',

  data() {
    return {
      mes: {  //详细信息
      },
      adduser: {  //添加用户的表单
        name: '',
        personId: '',
        age: '',
        gender: '',
        houseAddress: '',
        phone: ''
      },
      binghuan: {   //添加病患下一步表单
        source: '',
        inDate: '',
        symptoms: '',
        hospital: '',
        condition: '',
        comments: '',
      },
      mijie: {   //添加密接下一步表单
        source: '',
        divideDate: '',
        divideAddress: '',
      },
      innerVisible: false,  //内部显示
      selectedIds: [], // 所有选择的user的id的数组
      users: [{
      }], // 当前页的用户列表
      page: 1, // 当前页码
      limit: 3, // 每页数量
      user: {}, // 当前要操作的user
      nowrowid:-1, //当前的点击行
      dialogUserVisible: false, // 是否显示用户添加/修改的dialog
      MesVisible:false, //是否显示查看详情的dialog
      loading: false, // 是否正在提交请求中
      adduserRules: {  //添加用户的验证规则
        name: [{ required: true, message: '请填写姓名', trigger: 'blur' }],
        personId: [{ required: true, message: '请填写身份证号', trigger: 'blur' }],
        age: [{ required: true, message: '请填写年龄', trigger: 'blur' }],
        gender: [{ required: true, message: '请填写性别', trigger: 'blur' }],
        houseAddress: [{ required: true, message: '请填写住宅', trigger: 'blur' }],
        phone: [{ required: true, message: '请填写手机号', trigger: 'blur' }],
      },
      addmijieRules: {
        source: [{ required: true, message: '请填写接触来源', trigger: 'blur' }],
        divideDate: [{ required: true, message: '请填写隔离开始时间', trigger: 'blur' }],
        divideAddress: [{ required: true, message: '请填写隔离地址', trigger: 'blur' }],
      },
      addbinghuanRules: {
        source: [{ required: true, message: '请填写感染源', trigger: 'blur' }],
        inDate: [{ required: true, message: '请填写住院时间', trigger: 'blur' }],
        symptoms: [{ required: true, message: '请填写症状', trigger: 'blur' }],
        hospital: [{ required: true, message: '请填写诊治医院', trigger: 'blur' }],
        condition: [{ required: true, message: '请填写是否为重症', trigger: 'blur' }],
        comments: [{ required: false, message: '请填写备注', trigger: 'blur' }],
      }
    }
  },

  //发请求一般情况下，我们都是在mounted去发，但是也可以在created内部去发
  created() {
    this.getUsers()
  },
  
  methods: {
   async addbinghuan(){
       let res=await this.$http.admin.ReqAddManage(this.nowrowid,this.binghuan);
       if(res.data.code!=200){
        this.$message.error('失败');
        return;
       }
       this.$message({type:'success',message:'成功！'});
       this.innerVisible=false;
       this.getUsers();
    },
   IsLegalbinghuan(){  //判断病患提示框是否合法
    this.$refs.addbinghuanForm.validate((valid) => {
        if (valid) {
          this.addbinghuan();
        } else {
          return false;
        }
      });
    },
    openAddpatient(row){  //打开添加确诊提示框
      this.innerVisible=true;
      this.nowrowid=row.id;
    },
    openAddRnd(row) {  //打开隔离完毕弹窗
        this.$confirm('是否确认隔离完毕?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.AddEnd(row.id);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });          
        });
      },
    async AddEnd(id){   //隔离完毕
        let result=await this.$http.admin.ReqAddEnd(id);
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
    async showMes(row) {  //查看详细信息
      let res = await this.$http.admin.ReqGetInformationMessage(row.id);
      if(res.data.code!=200){
        this.$message.error('错误');
        return;
      }
      this.mes=res.data.data;
      this.MesVisible=true;
    },
      /* 
    列表选中状态发生改变的监听回调
    */
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id)
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
    显示更新用户的界面
    */
    showUpdateUser(user) {
      this.user = cloneDeep(user)
      this.dialogUserVisible = true
    },

    /* 
    获取分页列表
    */
    async getUsers(page = 1) {
      this.page = page
      const { limit } = this
      this.loading = true
      this.users = [];
      const result = await this.$http.admin.ReqSelectAllInformationService(limit, page);
      if (!result.data.data) {
        this.$message.error('错误');
        return;
      }
      for (const user of result.data.data) { //遍历数组
        let {id, name, personId, gender, divideAddress, divideDate} = user;
        let newUser = {id, name, personId, gender, divideAddress, divideDate};
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

    /* 
    取消用户的保存或更新
    */
    cancel() {
      this.dialogUserVisible = false
      this.innerVisible = false;
      this.user = {};
      this.adduser = {};
      this.mijie = {};
    },
    
    addUserNext() {  //添加用户的下一步
      this.$refs.adduserForm.validate((valid) => {
        if (valid) {
          this.innerVisible = true; //显示子级表单
        } else {
          return false;
        }
      });
    },
    addmijie() { //添加用户的下一步
      this.$refs.addmijieForm.validate((valid) => {
        if (valid) {
          this.AddUserAndmijie();
        } else {
          return false;
        }
      });
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