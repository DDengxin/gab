$(function () {

    // 认证状态
    $('.notice-success').hide();
    $('.notice-fail').hide();
    $('.notice-loading').hide();

    var status = $('.status').text();
    var pass = $('.pass').text();

    _init();

    if (status == '确认') {
        $('.container').css('display', 'none');
        $('.notice-loading').show();
        _init();
    }
    if (status == '登记' && pass == '驳回') {
        $('.container').css('display', 'none');
        $('.notice-fail').show();
        _init();
        $('.tip').html('重新提交<b onclick="aginSubmit()">实名认证</b>');
        $('.submit').click(function () {
            aginSubmit();
            _init();
        })
    }
    if (status == '核准') {
        $('.container').css('display', 'none');
        $('.notice-success').show();
        _init();
        $('.tip').html('如果您需要变更账号认证信息，可以重新<b onclick="aginSubmit()">实名认证</b>');
        $.ajax({
            url: '/platform/personal/personById/' + TZ_SETTING.gabUserId,
            dataType: 'json',
            type: 'get',
            success: function (res) {
                var getData = res.data.personal;
                $('.notice-success ul').find('li:eq(1)>b').text(res.data.gUserInfo.userName);
                var cardNum = getData.idCard;
                cardNum = cardNum.substring(0,5)+cardNum.substring(5,10).replace(cardNum.substring(5,10),'****')+cardNum.substring(10,cardNum.length);
                $('.notice-success ul').find('li:eq(2)>b').text(cardNum);
            }
        })
    }


    layui.use(['form', 'laydate', 'layer'], function () {
        var form = layui.form;
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#effectiveDate' //指定元素
        });

        // 身份证验证
        form.verify({
            idCard: [
                /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                '身份证号码格式不正确'
            ]
        });

        //监听提交
        form.on('submit()', function (data) {
            var jsonData = {};

            jsonData.gUserInfo = { userName: data.field.userName };
            jsonData.expert = data.field;
            jsonData.selectType = 'complete';
            var index = layer.load(); //加载load提示
            jsonData.expert.expertType = '工艺专家';

            $.ajax({
                url: '/platform/expert/gab/authExpertSave',
                dataType: 'json',
                type: 'post',
                data: JSON.stringify(jsonData),
                contentType: "application/json",
                success: function (res) {
                    if (res.code == '200') {
                        layer.msg(res.msg, { icon: 1 });
                        window.setTimeout(function () {
                            $('.notice-loading').show();
                            $('.container').css('display', 'none');
                        }, 3000);
                    }
                },
                error: function () {
                    layer.msg('请求有错误', { icon: 5 })
                },
                complete: function () {
                    layer.close(index);
                }
            })
            return false;
        });
    });

})


function aginSubmit() {
    $('.container').css('display', 'block');
    $('.notice-fail').hide();
    $('.tip').html('请完善一下信息，方便我们更好的为您服务');
    _init();
}

function _init() {
    // 上传文件的控件初始化
    _init_upload(['#cardZImg', '#idZCard', '#cardFImg', '#idFCard']);
    $('.sidebar').css('height', $('.right').height() + 'px');
}