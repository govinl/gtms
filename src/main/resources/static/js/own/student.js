layui.use(['layer'],function () {
    var layer=layui.layer;
    var $=layui.jquery;
})
//题目提交,包含修改题目的方法
function subject(id) {
    window.event.returnValue=false;
    // i 用于取出参数中的数字，来判断是第几个button 按钮
    var i=id.substring(13);
    if (!$('#subject'+i).val() || !$('#subject_teacher'+i).val() || !$('#subject_category'+i).val()){
        layer.msg("请将数据填写完整！", {icon: 5});
    } else {
        //status是该题目的状态
        var status=$('#subject_YN'+i).val();
        if (status=="未通过审核，请重新拟题！"){
            var dataDelete={}
            dataDelete['id']=getCookie("subject"+i);
            $.ajax({
                url:"/subject/deleteOld",
                type:"post",
                contentType:"application/json;charset=UTF-8",
                dataType:'json',
                data:JSON.stringify(dataDelete),
                async:true,
                cache:false,
                success:function (resulrDelete) {
                    var jsonDelete=eval(resulrDelete);
                    if (jsonDelete.res==true){
                        parent.layer.msg("原题目已删除", {icon: 1});
                        var data1 = {}
                        data1["subject"] = $('#subject'+i).val();
                        data1["stuId"] =getCookie("userId");
                        data1["twant"] = $('#subject_teacher'+i).val();
                        data1["cname"] = $('#subject_category'+i).val();
                        $.ajax({
                            url: "/subject/newsubject",
                            type: 'post',
                            contentType: 'application/json;charset=UTF-8',
                            dataType: 'json',
                            data: JSON.stringify(data1),
                            async: false,
                            cache: false,
                            success: function (resultNewSubject) {
                                var json2 = eval(resultNewSubject);
                                if (true == (json2.res)) {
                                    parent.layer.msg(json2.msg, {icon:1});
                                    window.location.reload();
                                }
                            }
                        });
                    } else {
                        parent.layer.msg("题目修改失败！", {icon:5});
                        window.location.reload();
                    }

                }
            });
        }else if (!status){
            var dataNew = {}
            dataNew["subject"] = $('#subject'+i).val();
            dataNew["stuId"] =getCookie("userId");
            dataNew["twant"] = $('#subject_teacher'+i).val();
            dataNew["cname"] = $('#subject_category'+i).val();
            $.ajax({
                url: "/subject/newsubject",
                type: 'post',
                contentType: 'application/json;charset=UTF-8',
                dataType: 'json',
                data: JSON.stringify(dataNew),
                async: false,
                cache: false,
                success: function (resultNew) {
                    var json3 = eval(resultNew);
                    if (true == (json3.res)) {
                        parent.layer.msg(json3.msg, {icon:1});
                        window.location.reload();
                    }
                }
            });
        }

    }    
}
//类别下拉列表
function cateFocus(id){
    //kind为哪个input的标识符
    var kind=id.substring(16);
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
                //i是list的长度
                var i = jsonAllCateGory.data.length;
                for (j = 0; j < i; j++) {
                    $("#cnamelist"+kind).append("<option>" + jsonAllCateGory.data[j].cname + "</option>");
                }
            }
        }
    });
}
//当类别失去焦点时，清除option
function cateBlur(id){
    //kind为哪个input的标识符
    var kind=id.substring(16);
    $("#cnamelist"+kind).empty();
}

//请求教师列表
function teaFocus(id){
    //kind为哪个input的标识符
    var kind=id.substring(16);
    if ($('#subject_category'+kind).val()) {
        var dataTea = {}
        dataTea["direction"] = $('#subject_category'+kind).val();
        $.ajax({
            url: "/teadirection/selectbydirection",
            type: 'post',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            data:JSON.stringify(dataTea),
            async: false,
            cache: false,
            success: function (resultAllTea) {
                var jsonAllTea = eval(resultAllTea);
                if (true == (jsonAllTea.res)) {
                    console.log(jsonAllTea.data);
                    //i是list的长度
                    var i = jsonAllTea.data.length;
                    for (j = 0; j < i; j++) {
                        $("#twantlist"+kind).append("<option>" + jsonAllTea.data[j].teaName + "</option>");
                    }
                }
            }
        });
    }else {
        layer.msg("请先选择类别！",{icon:5});
        $('#subject_category'+kind).focus();
    }
}
//当类别失去焦点时，清除option
function teaBlur(id){
    //kind为哪个input的标识符
    var kind=id.substring(16);
    $("#twantlist"+kind).empty();
}

