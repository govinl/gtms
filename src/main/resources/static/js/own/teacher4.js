layui.use(['layer'],function () {
    var layer=layui.layer;
})
var $=layui.jquery;
//新的类别输入框
function newcate(){
    var $=layui.jquery;
    //获取最后一个类别输入框的id
    var divId=$('#cateform1').children("div:last-child").attr('id');
    var i=divId.substring(8);
    $("#category"+Number(i)).after("<div id=\"category"+(Number(i)+Number(1))+"\">\n" +
        "                                        <div class=\"input-group m-b-10 col-sm-8 col-md-8 col-lg-8\" style=\"float: left\">\n" +
        "                                            <span class=\"input-group-addon\">请输入类别：</span>\n" +
        "                                            <input type=\"text\" class=\"form-control\" placeholder=\"类别"+(Number(i)+Number(1))+"\"\n" +
        "                                                   aria-describedby=\"basic-addon1\" id=\"cate"+(Number(i)+Number(1))+"\">\n" +
        "                                        </div>\n" +
        "                                        <div id=\"catebtn"+(Number(i)+Number(1))+"\" class=\"col-sm-4 col-md-4 col-lg-4\" style=\"float: right;margin-bottom: 10px;\">\n" +
        "                                            <button class=\"btn btn-label btn-primary col-sm-5 col-md-5 col-lg-5\"  id=\"catesubbtn"+(Number(i)+Number(1))+"\" onclick=\"newCategory(this)\" style=\"float: left\"><label><i class=\"mdi mdi-checkbox-marked-circle-outline\"></i></label> 确认提交</button>\n" +
        "                                            <button class=\"btn btn-label btn-warning col-sm-5 col-md-5 col-lg-5\" id=\"catechangebtn"+(Number(i)+Number(1))+"\" onclick=\"changeCategory(this)\" style=\"display: none;float: left;margin-right: 10px;\"><label><i class=\"mdi mdi-delete-empty\"></i></label> 修改数据</button>\n" +
        "                                            <button class=\"btn btn-label btn-danger col-sm-5 col-md-5 col-lg-5\" id=\"cateoverchangebtn"+(Number(i)+Number(1))+"\" onclick=\"javascript:window.location.reload()\" style=\"display: none;float: left;margin-right: 10px;\"><label><i class=\"mdi mdi-close\"></i></label> 修改数据</button>\n" +
        "                                            <button class=\"btn btn-label btn-primary col-sm-5 col-md-5 col-lg-5\"  id=\"catechangesubbtn"+(Number(i)+Number(1))+"\" onclick=\"changeCategorySub(this)\" style=\"display: none;float: left;margin-right: 10px;\"><label><i class=\"mdi mdi-checkbox-marked-circle-outline\"></i></label> 提交修改</button>\n" +
        "                                            <button class=\"btn btn-label btn-danger col-sm-5 col-md-5 col-lg-5\" id=\"catedeletebtn"+(Number(i)+Number(1))+"\" onclick=\"deleteCategory(this)\" style=\"display: none;float: left;\"><label><i class=\"mdi mdi-close\"></i></label> 删除数据</button>\n" +
        "                                        </div>\n" +
        "                                    </div>");
}
//将数据导出为excal
function tableToExcel(jsonData){
    //列标题，逗号隔开，每一个逗号就是隔开一个单元格
    let str = `id,类别\n`;
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
    if (Number(month)<Number(9)) {
        link.download = ""+year+"届毕业生论文题目类别表.csv";
    }else if (Number(month)>Number(9)) {
        link.download = (Number(year)+Number(1))+"届毕业生论文题目类别表.csv";
    }
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}
//下载类别的excal
function dowloadcate() {
    $.ajax({
        url: "/category/allCateGory",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        async: true,
        cache: false,
        success: function (resultCate) {
            console.log(resultCate);
            var resultcate=eval(resultCate);
            if(resultcate.res) {
                tableToExcel(resultCate.data);
            }else {
                layer.msg("数据请求失败，请稍后再试！",{icon:5})
            }
        }
    });
}


//提交新的类别
function newCategory(element){
    //被点击的提交按钮的id
    var id=element.id;
    var i=id.substring(10);
    var dataCate={}
    dataCate["cname"]=$('#cate'+i).val();
    if ($('#cate'+i).val()) {
        $.ajax({
            url: "/category/newcategory",
            type: 'post',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify(dataCate),
            async: true,
            cache: false,
            success: function (result) {
                var json = eval(result);
                if (true == (json.res)) {
                    parent.layer.msg(json.msg, {icon:1});
                    window.location.reload();
                } else {
                   parent.layer.msg(json.msg, {icon:5});
                    window.location.reload();
                }
            }
        });
    }else {
        layer.msg("请填写类别后再提交！",{icon:2});
    }
}

//删除该类别，根据类别的内容，所以类别不可重复
function deleteCategory(element) {
    //被点击的提交按钮的id
    var id=element.id;
    var i=id.substring(13);
    var dataCate={}
    dataCate["cname"]=$('#cate'+i).val();
    $.ajax({
        url: "/category/deletecategory",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(dataCate),
        async: true,
        cache: false,
        success: function (result) {
            var json = eval(result);
            if (true == (json.res)) {
                parent.layer.msg(json.msg,{icon:1});
                window.location.reload();
            }else {
                parent.layer.msg(json.msg,{icon:5});
                window.location.reload();
            }
        }
    });
}

//修改类别,解锁输入框
function changeCategory(element) {
    //获取类别框的个数
    var divId=$('#cateform1').children("div:last-child").attr('id');
    var count=divId.substring(8);
    var flag=false;
    for (i=1;i<count;i++){
        if( $("#catechangesubbtn"+i).css("display")=='block'){
            flag=true;
            break;
        }
    }
    if(!flag) {
        //被点击的提交按钮的id
        var id = element.id;
        var i = id.substring(13);
        setCookie('oldcname',$('#cate'+i).val());
        //隐藏修改按钮
        $('#catechangebtn' + i).hide();
        //显示取消修改按钮
        $('#cateoverchangebtn' + i).show();
        //隐藏删除按钮
        $('#catedeletebtn' + i).hide();
        //解锁输入框
        $('#cate' + i).attr("disabled", false);
        //显示提交按钮
        $('#catechangesubbtn' + i).show();
    }else{
        layer.msg("请先完成正在进行的修改操作！",{icon:6});
    }
}
//点击提交修改按钮后
function changeCategorySub(element) {
    //被点击的提交按钮的id
    var id=element.id;
    var i=id.substring(16);
    var data={}
    data["oldcname"]=getCookie('oldcname');
    data["newcname"]=$('#cate'+i).val();
    $.ajax({
        url: "/category/updatecategory",
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(data),
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
