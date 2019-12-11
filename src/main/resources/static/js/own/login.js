layui.use(['layer'],function () {
    var layer=layui.layer;
})
function login() {
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
///设置cookie
function setCookie(NameOfCookie, value, expiredays) {
    var ExpireDate = new Date();
    ExpireDate.setTime(ExpireDate.getTime() + (expiredays * 24 * 3600 * 1000));
    document.cookie = NameOfCookie + "=" + escape(value) + ((expiredays == null) ? "" : "; expires=" + ExpireDate.toGMTString());
}
//获取cookie的值
var allcookies = document.cookie;
function getCookie(cookie_name)
{
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookie_name);   //索引的长度

    // 如果找到了索引，就代表cookie存在，
    // 反之，就说明不存在。
    if (cookie_pos != -1)
    {
        // 把cookie_pos放在值的开始，只要给值加1即可。
        cookie_pos += cookie_name.length + 1;
        var cookie_end = allcookies.indexOf(";", cookie_pos);

        if (cookie_end == -1)
        {
            cookie_end = allcookies.length;
        }

        var value = unescape(allcookies.substring(cookie_pos, cookie_end));
    }
    return value;
}
//清除网站cookie
function clearcookie() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
        for (var i = keys.length; i--;) {
            document.cookie = keys[i] + '=0;path=/;expires=' + new Date(0).toUTCString();//清除当前域名下的,例如：m.kevis.com
            document.cookie = keys[i] + '=0;path=/;domain=' + document.domain + ';expires=' + new Date(0).toUTCString();//清除当前域名下的，例如 .m.kevis.com
            document.cookie = keys[i] + '=0;path=/;domain=localhost;expires=' + new Date(0).toUTCString();//清除一级域名下的或指定的，例如 .kevis.com
            document.cookie = keys[i] + "= 0 " + "; expires=" + new Date(0).toUTCString()+";path="+"/page";
            document.cookie = keys[i] + "= 0 " + "; expires=" + new Date(0).toUTCString()+";path="+"/page";
            document.cookie = keys[i] + "= 0 " + "; expires=" + new Date(0).toUTCString()+";path="+"/page/student";
            document.cookie = name + "= ' ' " + "; expires=" + new Date(0).toUTCString()+";path="+"/";
        }
    }
    $("#divcookie").html(document.cookie);
    alert('Cookie已清除');
}

