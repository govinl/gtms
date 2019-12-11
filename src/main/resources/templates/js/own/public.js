//判断功能是否开启
function checkOnOff(fun){
    window.event.returnValue=false;
    var datafun={}
    datafun['power']=fun;
    $.ajax({
        url:"/power/checkOnOff",
        type:'post',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(datafun),
        async: false,
        cache: false,
        success: function (resultfun) {
            var jsonfun = eval(resultfun);
            console.log(jsonfun);
            if (true == (jsonfun.res)) {
                var src=$('#iframe1').prop("src").replace(/^http:\/\/[^/]+/, "");
                if (src==jsonfun.url) {
                    //不做任何事
                }else {
                    $('#iframe1').attr("src", jsonfun.url);
                    $('#iframe1').load(function () {
                        var lasturl=jsonfun.url.match('[^/]+(?!.*/)')[0];
                        //如何根据不同的跟链接，执行不同的js方法
                        if (lasturl=="teacher4_leibie.html"){
                            $("#iframe1")[0].contentWindow.allcate();
                        }else {
                            $("#iframe1")[0].contentWindow.teachertopic();
                        }
                    });
                }
            } else {
                lightyear.notify(jsonfun.msg , "danger");
            }
        }
    });
}

//右上角用户名显示
$(function () {
    var userName=getCookie("userName");
    $('#userName-span').html(userName);
})

//修改密码
function ChangePassword() {
    //验证新密码的两次输入是否一致
    var newpwd = $('#new-password').val();
    var confirmpwd = $('#confirm-password').val();
    if (newpwd != confirmpwd) {
        alert("新密码两次输入不一致，请重新输入！")
    } else {
        window.event.returnValue = false;
        var data = {}
        data["oldpwd"] = $('#old-password').val();
        data["newpwd"] = $('#new-password').val();
        data["confirmpwd"] = $('#confirm-password').val();
        data["userId"] = getCookie('userId');
        $.ajax({
            url: "/teacher/changepassword",
            type: 'post',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify(data),
            async: true,
            cache: false,
            success: function (result) {
                var json1 = eval(result);
                if (true == (json1.res)) {
                    lightyear.notify(json1.msg , "success");
                } else {
                    lightyear.notify(json1.msg , "danger");
                }
            }
        });
    }
}
//注销登录
function logout() {
    window.location.href="/page/login.html";
}



