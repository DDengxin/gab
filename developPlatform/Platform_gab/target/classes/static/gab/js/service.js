$(function () {
    'use strict';
    layui.use('layim', function (layim) {
        var layim = layui.layim;
        layim.config(userId, {
            init: {
                //配置客户信息
                mine: {
                    "username": "访客" //我的昵称
                    , "id": "100000123" //我的ID
                    , "status": "online" //在线状态 online：在线、hide：隐身
                    , "remark": "在深邃的编码世界，做一枚轻盈的纸飞机" //我的签名
                    , "avatar": "//res.layui.com/images/fly/avatar/00.jpg" //我的头像
                }
            }
            //开启客服模式
            , brief: true
        });
        //打开一个客服面板
        layim.chat({
            name: '在线客服一' //名称
            , type: 'kefu' //聊天类型
            , avatar: '//tp1.sinaimg.cn/5619439268/180/40030060651/1' //头像
            , id: 1111111 //定义唯一的id方便你处理信息
        }).chat({
            name: '在线客服二' //名称
            , type: 'kefu' //聊天类型
            , avatar: '//tp1.sinaimg.cn/5619439268/180/40030060651/1' //头像
            , id: 2222222 //定义唯一的id方便你处理信息
        });
        layim.setChatMin(); //收缩聊天面板
    });
});