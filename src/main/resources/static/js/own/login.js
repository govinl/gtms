layui.use(['layer'],function () {
    var layer=layui.layer;
})
function login() {
    var $=layui.jquery;
    window.event.returnValue=false;
    if (!$('#id').val() || !$('#pwd').val()){
        layer.msg("请将信息输入完整！",{icon:2});
    } else {
        var data1 = {}
        data1["stuId"] = $('#id').val();
        data1["stuPwd"] = $('#pwd').val();
        $.ajax({
            url: "/student/login",
            type: 'post',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify(data1),
            async: true,
            cache: false,
            success: function (result1) {
                var json1 = eval(result1);
                if (true == (json1.res)) {
                    layer.msg(json1.msg,{icon:1});
                    setCookie('userName', json1.data.stuName, 7);
                    setCookie('userId', json1.data.stuId, 7);
                    window.location.href = json1.url;
                } else {
                    var data2 = {}
                    data2["teaId"] = $('#id').val();
                    data2["teaPwd"] = $('#pwd').val();
                    $.ajax({
                        url: "/teacher/login",
                        type: 'post',
                        contentType: 'application/json;charset=UTF-8',
                        dataType: 'json',
                        data: JSON.stringify(data2),
                        async: true,
                        cache: false,
                        success: function (result2) {
                            var json2 = eval(result2);
                            if (true == (json2.res)) {
                                layer.msg(json2.msg,{icon:1});
                                setCookie('userName', json2.data.teaName, 1);
                                setCookie('userId', json2.data.teaId, 1);
                                setCookie('userLevel', json2.data.plevel, 1);
                                window.location.href = json2.url;
                            } else {
                                layer.msg(json2.msg,{icon:2});
                            }
                        }
                    });
                }

            }
        })
    }
}


