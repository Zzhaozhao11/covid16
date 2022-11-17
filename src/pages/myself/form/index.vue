<template>
    <div>
        <el-form label-position="top">
            <p class="title">每日健康打卡</p>
            <el-form-item label="姓名">
                <el-input></el-input>
            </el-form-item>
            <el-form-item label="现在地址">
                <el-input></el-input>
            </el-form-item>
            <el-form-item label="健康状况">
                <el-radio-group>
                    <el-radio label="健康"></el-radio>
                    <el-radio label="头晕感冒发热"></el-radio>
                    <el-radio label="死亡"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="体温">
                <el-input></el-input>
            </el-form-item>
            <el-form-item label="是否到达过高风险地区">
                <el-radio-group>
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="是否接触过疑似或者确诊病例">
                <el-radio-group>
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="备注">
                <el-input type="textarea"></el-input>
            </el-form-item>
            <el-form-item class="butf">
                <el-button type="primary" class="but">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
export default {
    data() {
        return {

        }
    },
    methods: {

    }
}
</script>
<style lang="css" scoped>
.title {
    width: 100%;
    text-align: center;
    font-size: 1.625rem;
    margin-bottom: 1rem;
}
.butf {
    margin-top: 3rem;
    display: flex;
    justify-content: center;
}
.but {
    width: 12rem;

}
</style>