layui.use(['layer'], function () {
    var layer = layui.layer;
})

///设置cookie
function setCookie(NameOfCookie, value, expiredays) {
    var ExpireDate = new Date();
    ExpireDate.setTime(ExpireDate.getTime() + (expiredays * 24 * 3600 * 1000));
    document.cookie = NameOfCookie + "=" + escape(value) + ((expiredays == null) ? "" : "; expires=" + ExpireDate.toGMTString());
}

//获取cookie的值
var allcookies = document.cookie;

function getCookie(cookie_name) {
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookie_name);   //索引的长度

    // 如果找到了索引，就代表cookie存在，
    // 反之，就说明不存在。
    if (cookie_pos != -1) {
        // 把cookie_pos放在值的开始，只要给值加1即可。
        cookie_pos += cookie_name.length + 1;
        var cookie_end = allcookies.indexOf(";", cookie_pos);

        if (cookie_end == -1) {
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
            document.cookie = keys[i] + "= 0 " + "; expires=" + new Date(0).toUTCString() + ";path=" + "/page";
            document.cookie = keys[i] + "= 0 " + "; expires=" + new Date(0).toUTCString() + ";path=" + "/page";
            document.cookie = keys[i] + "= 0 " + "; expires=" + new Date(0).toUTCString() + ";path=" + "/page/student";
            document.cookie = name + "= ' ' " + "; expires=" + new Date(0).toUTCString() + ";path=" + "/";
        }
    }
    $("#divcookie").html(document.cookie);
    alert('Cookie已清除');
}

var $ = layui.jquery;

//判断功能是否开启
function checkOnOff(fun) {
    window.event.returnValue = false;
    var datafun = {}
    datafun['power'] = fun;
    $.ajax({
        url: "/power/checkOnOff",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(datafun),
        async: false,
        cache: false,
        success: function (resultfun) {
            var jsonfun = eval(resultfun);
            console.log(jsonfun);
            if (true == (jsonfun.res)) {
                var src = $('#iframe1').prop("src").replace(/^http:\/\/[^/]+/, "");
                if (src == jsonfun.url) {
                    //不做任何事
                } else {
                    $('#iframe1').attr("src", jsonfun.url);
                    $('#iframe1').load(function () {
                        var lasturl = jsonfun.url.match('[^/]+(?!.*/)')[0];
                        //如何根据不同的跟链接，执行不同的js方法
                        if (lasturl == "teacher4_leibie.html") {
                            $("#iframe1")[0].contentWindow.allcate();
                        } else {
                            $("#iframe1")[0].contentWindow.teachertopic();
                        }
                    });
                }
            } else {
                lightyear.notify(jsonfun.msg, "danger");
            }
        }
    });
}


//修改密码
function ChangePassword(type) {
    //验证新密码的两次输入是否一致
    var oldpwd = $('#old-password').val();
    var newpwd = $('#new-password').val();
    var confirmpwd = $('#confirm-password').val();
    if (!oldpwd || !newpwd || !confirmpwd) {
        layer.msg("请输入完整信息！",{icon:5})
    } else if (newpwd != confirmpwd) {
        layer.msg("新密码两次输入不一致，请重新输入！",{icon:5});
    } else {
        window.event.returnValue = false;
        if (type=='student'){
            var url="/student/changepassword";
        } else if (type=='teacher'){
            var url="/teacher/changepassword";
        } else {
            layer.msg("异常请求，系统拒绝响应！",{icon:5});
        }
        var data = {}
        data["oldpwd"] = $('#old-password').val();
        data["newpwd"] = $('#new-password').val();
        data["confirmpwd"] = $('#confirm-password').val();
        data["userId"] = getCookie('userId');
        $.ajax({
            url: url,
            type: 'post',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify(data),
            async: true,
            cache: false,
            success: function (result) {
                var json1 = eval(result);
                if (true == (json1.res)) {
                    parent.layer.msg(json1.msg,{icon:1});
                    xadmin.close();
                } else {
                    layer.msg(json1.msg,{icon:5});
                }
            }
        });
    }
}

//注销登录
function logout() {
    window.location.href = "/page/login.html";
}

//获取当前时间
function getnow() {
    var myDate = new Date();

    var year = myDate.getFullYear();        //获取当前年
    var month = myDate.getMonth() + 1;   //获取当前月
    var date = myDate.getDate();            //获取当前日


    var h = myDate.getHours();              //获取当前小时数(0-23)
    var m = myDate.getMinutes();          //获取当前分钟数(0-59)
    var s = myDate.getSeconds();

    var now = year + '-' + month + "-" + date + " " + h + ':' + m + ":" + s;
    return now;

}





