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
      <el-button type="danger" :disabled="selectedIds.length === 0">批量死亡</el-button>
    </div>

    <!-- table表格：展示用户信息的地方 -->
    <el-table border stripe v-loading="listLoading" :data="users" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="50" />

      <el-table-column type="name" prop="name" label="姓名" width="90" align="center" />

      <el-table-column prop="personId" label="身份证号" align="center"/>
      <el-table-column prop="gender" label="性别" width="50" />
      <el-table-column prop="houseAddress" label="住宅"  align="center"/>

      <el-table-column prop="inDate" label="住院时间" align="center"/>
      <el-table-column prop="source" label="感染来源" align="center"/>
      <el-table-column label="操作" align="center" width="350px">
        <template slot-scope="scope">
          <el-button size="mini" type="info" plain @click="showMes(scope.row)">查看详情</el-button>
          <el-button size="mini"  @click="showjiancelishi(scope.row)">检测历史</el-button>
          <el-button size="mini" type="primary" plain  @click="showjiance(scope.row)">添加检测信息</el-button>
        </template>
      </el-table-column>
      <el-table-column label="变动"  align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="success" round  @click="showMsBox(scope.row,1)">治愈</el-button>
          <el-button size="mini" type="danger" round @click="showMsBox(scope.row,2)">死亡</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页器 -->
    <el-pagination :current-page="page" :page-size="limit" :page-sizes="[5,3,7]" style="padding: 20px 0;"
      layout="prev, pager, next, jumper, ->, sizes, total" @current-change="getUsers" @size-change="handleSizeChange" />
    <!-- 添加用户的对话框 -->
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
      <el-dialog width="30%" title="病患管理" :visible.sync="innerVisible" append-to-body>
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
          <el-button type="primary" @click="addbinghuan">确 定</el-button>
        </div>
      </el-dialog>
    </el-dialog>
   
    <!-- 查看详细信息的对话框 -->
    <el-dialog title="详细信息" :visible.sync="dialogVisible" width="30%" >
      <el-descriptions direction="vertical" :column="3" border>
    <el-descriptions-item label="治疗医院">{{mes.comments}}</el-descriptions-item>
    <el-descriptions-item label="是否重症">{{mes.hospital}}</el-descriptions-item>
    <el-descriptions-item label="入院日期">{{mes.inDate}}</el-descriptions-item>
    <el-descriptions-item label="感染源">{{mes.source}}</el-descriptions-item>
    <el-descriptions-item label="症状">{{mes.symptoms}}</el-descriptions-item>
    <el-descriptions-item label="备注" v-show="mes.condition">{{mes.condition}}</el-descriptions-item>

</el-descriptions>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
    <!-- 添加检测信息的对话框 -->
    <el-dialog title="添加检测信息" :visible.sync="dialogJianCeVisible" width="30%"  >
      <el-form :rules="addjianceRules" label-width="150px" ref="addjianceForm" :model="jiance" class="jiance">
        <el-form-item label="检测时间" prop="detectionDate">
              <el-date-picker 
              type="date" placeholder="选择日期" 
              v-model="jiance.detectionDate"
              value-format="yyyy-MM-dd"></el-date-picker>
          </el-form-item>
          <el-form-item label="核酸检测结果" prop="nuclein">
            <el-radio-group v-model="jiance.nuclein">
              <el-radio label="阴性"></el-radio>
              <el-radio label="阳性"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="ct检测" prop="ct">
            <el-input v-model="jiance.ct"></el-input>
          </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="addjiance">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 检测历史 -->
    <el-dialog title="检测历史" :visible.sync="dialogjiancelishiVisible" width="30%" >
      <el-table :data="mes">
    <el-table-column property="detectionDate" label="检测日期" align="center"></el-table-column>
    <el-table-column property="nuclein" label="核酸检测结果" align="center"></el-table-column>
    <el-table-column property="ct" label="ct" align="center"></el-table-column>
  </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click=" dialogjianceVisible = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
  
<script lang="js">
import cloneDeep from 'lodash/cloneDeep'

export default {
  name: 'AclUserList',

  data() {
    return {
      jiance:{  //添加检测信息的表单
        detectionDate:'',
        nuclein:'',
        ct:'',
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
      mes:{  //详细信息

      },
      nowRowId:-1, //当前选中行的id
      dialogjiancelishiVisible: false, //显示检测信息
      dialogJianCeVisible:false, //添加检测信息是否展示
      innerVisible: false,  //内部显示
      listLoading: false, // 是否显示列表加载的提示
      dialogVisible:false, //详情
      dialogUserVisible: false, // 是否显示用户添加/修改的dialog
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
      adduserRules: {  //添加用户的验证规则
        name: [{ required: true, message: '请填写姓名', trigger: 'blur' }],
        personId: [{ required: true, message: '请填写身份证号', trigger: 'blur' }],
        age: [{ required: true, message: '请填写年龄', trigger: 'blur' }],
        gender: [{ required: true, message: '请填写性别', trigger: 'blur' }],
        houseAddress: [{ required: true, message: '请填写住宅', trigger: 'blur' }],
        phone: [{ required: true, message: '请填写手机号', trigger: 'blur' }],
      },
      addbinghuanRules: {
        source: [{ required: true, message: '请填写感染源', trigger: 'blur' }],
        inDate: [{ required: true, message: '请填写住院时间', trigger: 'blur' }],
        symptoms: [{ required: true, message: '请填写症状', trigger: 'blur' }],
        hospital: [{ required: true, message: '请填写诊治医院', trigger: 'blur' }],
        condition: [{ required: true, message: '请填写是否为重症', trigger: 'blur' }],
        comments: [{ required: false, message: '请填写备注', trigger: 'blur' }],
      },
      addjianceRules:{
        detectionDate: [{ required: true, message: '请填写检测时间', trigger: 'blur' }],
        nuclein: [{ required: true, message: '请填写核算检测结果', trigger: 'blur' }],
        ct: [{ required: true, message: '请填写ct检测结果', trigger: 'blur' }],
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
    async showjiancelishi(row) {  //查看检测信息
      let res = await this.$http.admin.ReqGetDetetion(row.id);
      console.log(res.data);
      if(res.data.code!=200){
        this.$message.error('错误');
        return;
      }else if(!res.data.data){
        this.$message.error('无数据');
        return;
      }
      this.mes=res.data.data;
      this. dialogjiancelishiVisible=true;
    },
    /* 
    根据输入进行搜索
    */
    async search() {
      // this.searchObj = { ...this.tempSearchObj }
      // this.getUsers()
      this.listLoading = true;
      this.users = [];
            if(this.tempSearchObj.username===undefined)
      this.tempSearchObj.username='';
      let result = await this.$http.admin.ReqSelectPatientByName(this.tempSearchObj.username);
      if (result.status!=200) {
        this.$message.error('错误');
        return;
      }else if(result.data.code===400){
        this.$message('未查询到');
        this.listLoading=false;
        return;
      }
      for (const user of result.data.data) { //遍历数组
        let {id, name, personId, gender, houseAddress, inDate, source } = user;
        let newUser = {id,name, personId, gender, houseAddress, inDate, source };
        this.users.push(newUser);
      }
      this.listLoading = false;
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
      this.listLoading = true
      this.users = [];
      const result = await this.$http.admin.ReqSelectAllPatient(limit, page);
      if (!result.data.data) {
        this.$message.error('错误');
        return;
      }
      for (const user of result.data.data) { //遍历数组
        let {id, name, personId, gender, houseAddress, inDate, source } = user;
        let newUser = {id, name, personId, gender, houseAddress, inDate, source };
        this.users.push(newUser);
      }
      this.listLoading = false
      this.selectedIds = []
      
    },
    async showMes(row) {  //查看详细信息
      let res = await this.$http.admin.ReqGetManage(row.id);
      if(res.data.code!=200){
        this.$message.error('错误');
        return;
      }
      this.mes=res.data.data;
      this.dialogVisible=true;
    },
    showjiance(row){
      this.dialogJianCeVisible=true;
      this.nowRowId=row.id;
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
      this.dialogJianCeVisible=false;
      this.user = {};
      this.adduser = {};
      this.binghuan = {};
      this.jiance={};
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
    addbinghuan() { //添加用户的下一步
      this.$refs.addbinghuanForm.validate((valid) => {
        if (valid) {
          this.AddUserAndBinghuan();
        } else {
          return false;
        }
      });
    },
    addjiance(){ //添加检测的下一步
      this.$refs.addjianceForm.validate((valid) => {
        if (valid) {
          this.AddJIANCE();
        } else {
          return false;
        }
      });
    },
    async AddJIANCE(){ //添加检测发送axios请求
      const res = await this.$http.admin.ReqInsertDetetion(this.nowRowId,this.jiance);
      if (res.data.code!=200) {  //错误情况
        this.$message.error('错误！');
        return;
      }
      this.$message({ message: '成功添加', type: 'success' });
      this.nowRowId=-1;
      this.cancel(); // 关闭添加界面

    },
    async AddUserAndBinghuan() {
      const res = await this.$http.admin.reqAddBasic(this.adduser);
      console.log(res);
      if (res.data.code!=200) {  //错误情况
        this.$message.error('错误！');
        return;
      }
      const id = res.data.data.id;
      const lsres = await this.$http.admin.ReqAddManage(id, this.binghuan);
      console.log(lsres);
      if (lsres.data.code!=200) {  //错误情况
        this.$message.error('错误！');
        return;
      }
      this.$message({ message: '成功添加', type: 'success' });
      this.cancel(); // 关闭添加界面
      this.getUsers(); //重新发请求
    },
    async ToDead(id,date){   //转为死亡
       let res=await this.$http.admin.ReqToDead(id,date);
       if(res.data&&res.data.code===200)
       this.$message({type:'success',message:'成功转为死亡'});
       else{
        this.$message.error('失败');
        return;
       }
       await this.getUsers(); 
    },
    async ToCure(id,date){   //转为治愈
       let res=await this.$http.admin.ReqToCare(id,date);
       if(res.data&&res.data.code===200)
       this.$message({type:'success',message:'成功转为治愈'});
       else{
        this.$message.error('失败');
        return;
       }
       await this.getUsers(); 
    },
    showMsBox({id},inf){  //展示死亡和治愈的弹窗
      let mes='';
      if(inf===1)
      mes='出院';
      else
      mes='死亡';
      this.$prompt(`请输入${mes}日期`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /\d{4}-\d{2}-\d{2}/,
          inputErrorMessage:'请输入日期',
          inputType:'date',
        }).then(({ value }) => {
          if(inf===2)
          this.ToDead(id,value);
          else
          this.ToCure(id,value);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消'
          });       
        });
    }
  }
}
</script>
<style lang="css" scoped>
.app-container {
  margin: 1rem 2rem;
}

.elform {
  margin: 0 5rem;
}
::v-deep .jiance {
  margin-right: 5rem !important;
}
</style>