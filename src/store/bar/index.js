export default {
    namespaced:true,
    actions:{

    },
    mutations:{
        FOLDLEFTBAR(state){  //展开收起侧边栏
          state.IsFoldLeftBar=!state.IsFoldLeftBar;
        },
        SHOWLOG(state){   //显示隐藏登录界面
            state.IsShowLog=!state.IsShowLog;
        },
        CHANGEREG(state){  //切换注册界面
            state.Isreg=!state.Isreg;
        }
    },
    state:{
        IsFoldLeftBar:false,  //是否展开侧边栏
        IsShowLog:false,  //是否显示注册登录界面
        Isreg:false, //是否显示注册界面
    },
    getters:{

    }
}