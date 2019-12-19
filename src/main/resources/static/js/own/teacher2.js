layui.use(['layer'],function () {
    var layer=layui.layer;
})
function newtopicbody() {
    var $=layui.jquery;
    //获取最后一个类别输入框的id
    var divId=$('#topictbody').children("tr:last-child").attr('id');
    var i=divId.substring(9);
    var newid=Number(i)+Number(1);
    $("#topicbody"+Number(i)).after("<tr id=\"topicbody"+newid+"\">\n" +
        "                                            <td scope=\"row\" class=\"col-sm-1 col-md-1 col-lg-1\" id=\"topicnum"+newid+"\">1</td>\n" +
        "                                            <td>\n" +
        "                                                <input type=\"text\" class=\"form-control col-sm-4 col-md-4 col-lg-4\"\n" +
        "                                                       placeholder=\"题目"+newid+"\"\n" +
        "                                                       aria-describedby=\"basic-addon1\" id=\"topic"+newid+"\">\n" +
        "                                            </td>\n" +
        "                                            <td>\n" +
        "                                                <input type=\"text\" class=\"form-control col-sm-2 col-md-2 col-lg-2\"\n" +
        "                                                       placeholder=\"题目类别\"\n" +
        "                                                       aria-describedby=\"basic-addon1\"  id=\"topiccate"+newid+"\" \n" +
        "                                                       onfocus=\"cnameFocus('topiccate"+newid+"')\" onblur=\"cnameBlur('topiccate"+newid+"')\" list=\"catelist"+newid+"\">\n" +
        "                                                <datalist id=\"catelist"+newid+"\"></datalist>\n" +
        "                                            </td>\n" +
        "                                            <td id=\"topicYn"+newid+"\">\n" +
        "                                                无\n" +
        "                                            </td>\n" +
        "                                            <td>\n" +
        "                                                <button class=\"btn btn-label btn-primary\" id=\"topicsubbtn"+newid+"\"\n" +
        "                                                        onclick=\"newTopic(this)\" style=\"float: left\"><label><i\n" +
        "                                                        class=\"mdi mdi-checkbox-marked-circle-outline\"></i></label> 确认提交\n" +
        "                                                </button>\n" +
        "                                                <button class=\"btn btn-label btn-warning col-sm-5 col-md-5 col-lg-5\"\n" +
        "                                                        id=\"topicchangebtn"+newid+"\" onclick=\"changeTopic(this)\"\n" +
        "                                                        style=\"display: none;float: left;margin-right: 10px;\"><label><i\n" +
        "                                                        class=\"mdi mdi-delete-empty\"></i></label> 修改数据\n" +
        "                                                </button>\n" +
        "                                                <button class=\"btn btn-label btn-warning col-sm-5 col-md-5 col-lg-5\"\n" +
        "                                                        id=\"overchangebtn"+newid+"\" onclick=\"javascript:window.location.reload()\"\n" +
        "                                                        style=\"display: none;float: left;margin-right: 10px;\"><label><i\n" +
        "                                                        class=\"mdi mdi-delete-empty\"></i></label> 取消修改\n" +
        "                                                </button>\n" +
        "                                                <button class=\"btn btn-label btn-primary col-sm-5 col-md-5 col-lg-5\"\n" +
        "                                                        id=\"topicchangesubbtn"+newid+"\" onclick=\"changeTopicSub(this)\"\n" +
        "                                                        style=\"display: none;float: left;margin-right: 10px;\"><label><i\n" +
        "                                                        class=\"mdi mdi-checkbox-marked-circle-outline\"></i></label> 提交修改\n" +
        "                                                </button>\n" +
        "                                                <button class=\"btn btn-label btn-danger col-sm-5 col-md-5 col-lg-5\"\n" +
        "                                                        id=\"topicdeletebtn"+newid+"\" onclick=\"deleteTopic(this)\"\n" +
        "                                                        style=\"display: none;float: left;\"><label><i\n" +
        "                                                        class=\"mdi mdi-close\"></i></label> 删除数据\n" +
        "                                                </button>\n" +
        "                                            </td>\n" +
        "                                        </tr>");
    $('#topicnum' + newid).html(newid);
}

//提交题目
function newTopic(element) {
    //被点击的提交按钮的id
    var id=element.id;
    var i=id.substring(11);
    if (!$('#topic'+i).val() || !$('#topiccate'+i).val()){
     lightyear.notify("请将数据填写完整!","danger");
    } else {
        //获取发送的数据
        var datetopic = {}
        datetopic["topic"] = $('#topic' + i).val();
        datetopic['teaId'] = getCookie("userId");
        datetopic['teaName'] = getCookie("userName");
        datetopic['cname'] = $('#topiccate' + i).val();
        $.ajax({
            url: "/topic/insertTopic",
            type: 'post',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify(datetopic),
            async: false,
            cache: false,
            success: function (result) {
                var json = eval(result);
                if (true == (json.res)) {
                    lightyear.notify(json.msg, "success");
                    window.location.reload();
                } else {
                    lightyear.notify(json.msg, "danger");
                    window.location.reload();
                }
            }
        });
    }
}

//类别下拉列表
function cnameFocus(eleid){
    //kind为哪个input的标识符
    var kind=eleid.substring(9);
    $.ajax({
        url: "/category/allCateGory",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        async: false,
        cache: false,
        success: function (resultAllCateGory) {
            var jsonAllCateGory = eval(resultAllCateGory);
            if (true == (jsonAllCateGory.res)) {
                console.log(jsonAllCateGory.data);
                //i是list的长度
                var i = jsonAllCateGory.data.length;
                for (j = 0; j < i; j++) {
                    $("#catelist"+kind).append("<option>" + jsonAllCateGory.data[j].cname + "</option>");
                }
            }
        }
    });
}
//当类别失去焦点时，清除option
function cnameBlur(eleid){
    //kind为哪个input的标识符
    var kind=eleid.substring(9);
    $("#catelist"+kind).empty();
}

//题目修改的事件
function changeTopic(element) {
    //获取类别框的个数
    var divId=$('#topictbody').children("tr:last-child").attr('id');
    var count=divId.substring(9);
    var flag=false;
    for (let j=1;j<count;j++){
        if( $("#topicchangesubbtn"+j).css("display")=='block'){
            flag=true;
            break;
        }
    }
    if(!flag) {
        //被点击的提交按钮的id
        var id=element.id;
        var i=id.substring(14);
        getCookie("topic"+i);
        //隐藏修改按钮
        $('#topicchangebtn' + i).hide();
        //显示取消修改按钮
        $('#overchangebtn'+i).show();
        //隐藏删除按钮
        $('#topicdeletebtn' + i).hide();
        //解锁输入框
        $('#topic' + i).attr("disabled", false);
        $('#topiccate' + i).attr("disabled", false);
        //显示提交按钮
        $('#topicchangesubbtn' + i).show();
    }else{
        layer.msg("请先完成正在进行的修改操作！",{icon:5});
    }
}
//点击提交修改按钮后
function changeTopicSub(element) {
    //被点击的提交按钮的id
    var id=element.id;
    var i=id.substring(17);
    var datatopic={}
    datatopic["id"]=getCookie('topic'+i);
    datatopic["topic"]=$('#topic'+i).val();
    datatopic["cname"]=$('#topiccate'+i).val();
    $.ajax({
        url: "/topic/changetopic",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(datatopic),
        async: true,
        cache: false,
        success: function (result) {
            var json = eval(result);
            if (true == (json.res)) {
                parent.layer.msg(json.msg,{icon:1});
                window.location.reload();
            }else {
                parent.layer.msg(json.msg,{icon:1});
                window.location.reload();
            }
        }
    });
}
//点击删除数据按钮的事件
function deleteTopic(element) {
    //被点击的提交按钮的id
    var id=element.id;
    var i=id.substring(14);
    var datatopic={}
    datatopic["id"]=getCookie('topic'+i);
    $.ajax({
        url: "/topic/deletetopic",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(datatopic),
        async: true,
        cache: false,
        success: function (result) {
            var json = eval(result);
            if (true == (json.res)) {
                parent.layer.msg(json.msg,{icon:1});
                window.location.reload();
            }else {
                parent.layer.msg(json.msg,{icon:2});
                window.location.reload();
            }
        }
    });
}
//将教师自拟题目的数据导出为excal
function teaowntopicToExcel(jsonData){
    //列标题，逗号隔开，每一个逗号就是隔开一个单元格
    let str = `id,题目,教师编号,教师姓名,题目类别,题目状态\n`;
    //增加\t为了不让表格显示科学计数法或者其他格式
    for(let i = 0 ; i < jsonData.length ; i++ ){
        for(let item in jsonData[i]){
            str+=`${jsonData[i][item] + '\t'},`;
        }
        str+='\n';
    }
    //encodeURIComponent解决中文乱码
    let uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str);
    //通过创建a标签实现
    let link = document.createElement("a");
    link.href = uri;
    //对下载的文件命名
    var now = new Date();
    var month=now.getMonth()+1;
    var year=now.getFullYear();
    var userName=getCookie('userName');
    if (Number(month)<Number(9)) {
        link.download = userName+"老师"+year+"届毕业生论文题目教师自拟表.csv";
    }else if (Number(month)>Number(9)) {
        link.download = userName+"老师"+(Number(year)+Number(1))+"届毕业生论文题目类别表.csv";
    }
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}
//下载教师自拟题目的excal
function dowloadteaowntopic() {
    var teachertopicdata={}
    teachertopicdata["teaId"]=getCookie('userId');
    $.ajax({
        url: "/topic/selectowntopic",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data:JSON.stringify(teachertopicdata),
        dataType:'json',
        async: true,
        cache: false,
        success: function (resultCate) {
            console.log(resultCate);
            var resultcate=eval(resultCate);
            console.log(resultcate);
            if(resultcate.res) {
                teaowntopicToExcel(resultCate.data);
            }else {
                layer.msg("数据请求失败，请稍后再试！",{icon:6})
            }
        }
    });
}
/*
* 因项目重构，下列被注释方法不再使用
* */
/*
//左侧菜单生成方法
function newmeau(powername) {
    var divId=$('#meau').children("li:last-child").attr('id');
    var i=divId.substring(4);
    var newid=Number(i)+Number(1);
    $("#meau"+i).after("<li class=\"nav-item active\" id=\"meau"+newid+"\">\n" +
        "                            <a href=\"#\" rel=\"external nofollow\" onclick=\"checkOnOff('"+powername+"')\">\n" +
        "                                <i class=\"mdi mdi-home\"></i>\n" +
        "                                "+powername+"\n" +
        "                            </a>\n" +
        "                        </li>");
}
//动态生成左侧菜单
function mainmeau(funlevel) {
    var datalevel={}
    datalevel["funlevel"]=funlevel;
    $.ajax({
        url: "/power/ownpower",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data:JSON.stringify(datalevel),
        dataType:'json',
        async: true,
        cache: false,
        success: function (resuletpower) {
            var resuletpower = eval(resuletpower);
            console.log(resuletpower);
            if (resuletpower.res) {
                for (let i=0;i<resuletpower.data.length;i++){
                    newmeau(resuletpower.data[i].power);
                }
            }
        }
    });

}*/
