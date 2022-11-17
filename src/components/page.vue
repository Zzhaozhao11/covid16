<!-- 封装的分页器 -->
<template>
    <div>
        <el-pagination
        background layout="prev, pager, next"
        @current-change="ChangePage" 
        :page-size="pageSize" :total="total"></el-pagination>
    </div>
</template>
<script>
export default {
    name:'page',
    props:{
        curpage:{   //当前页数
            type:Number,
        },
        pageSize:{  //一页最大有多少条数据
            type:Number,
            required:true,
        },
        total:{  //总共有多少条数据
            type:Number,
            required:true,
        },
        ChangePage:{   //改变当前页的回调方法
           type:Function,
           required:true,
        }
    }
}
</script>
<style lang="css">
    
</style>