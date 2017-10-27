
$(function() {

    initMap();
    //getData();
    $("#suggestId").mousedown(function (e) {
        setDisabled();
    });


});


function setDisabled() {

    $("#query_").attr('disabled',true);
}

function initMap() {


    //1.创建地图
    function G(id) {
        return document.getElementById(id);
    }

    var map = new BMap.Map("container");
    map.centerAndZoom("武汉",12);                   // 初始化地图,设置城市和地图级别。

    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
// map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
// map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开

    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
//以上部分代码已经测试通过

    var pp = null;
    //2.下拉框事件监测
    var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
        {"input" : "suggestId"
            ,"location" : map
        });

    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件

        $("#query_").attr('disabled',true);

        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
       ///
        //pp = local.getResults().getPoi(0).point;
        setPlace();
    });

    //以下为添加部分


    //
    var geoc = new BMap.Geocoder();

    map.addEventListener("click", function(e){
         pp = e.point;
        geoc.getLocation(pp, function(rs){
            var addComp = rs.addressComponents;

            //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);

            var str1 = addComp.province+addComp.city+addComp.district+addComp.street+addComp.streetNumber;
            document.getElementById("suggestId").value = str1;

            $("#query_").attr('disabled',false);
            //$("#searchResultPanel").style.visibility="hidden";;

            //setPlace();

            map.clearOverlays();    //清除地图上所有覆盖物
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMap.Marker(pp));    //添加标注

        });
    });


    //以上为添加部分

    //3.显示选择的设备注册的地址
    function setPlace(){

        $("#query_").attr('disabled',false);

        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun(){
           pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMap.Marker(pp));    //添加标注
        }
        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }

    // $("#suggestId").mousedown(function (e) {
    //     setDisabled();
    // });
    //
    // function setDisabled() {
    //
    //     $("#query_").attr('disabled',true);
    // }

    //4.获取设备列表信息
    $("#query_").click(function (e) {

        //4.1 获取已选择的地址信息
        map.clearOverlays();//清空原来的标注
        var keyword = document.getElementById("suggestId").value;
        //document.get
//         localSearch.setSearchCompleteCallback(function (searchResult) {
//             var poi = searchResult.getPoi(0);
//             // var poi = null;
//             // poi.point = pp;
//             //document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat; //获取经度和纬度，将结果显示在文本框中
//             map.centerAndZoom(poi.point, 13);
//
//             var point = new BMap.Point(poi.point.lng, poi.point.lat);
// //                var marker = new BMap.Marker(point);  // 创建标注，为要查询的地址对应的经纬度
// //                map.addOverlay(marker);
//
//             var url = "http://image.tupian114.com/20140419/09274112.png";
//             //var url= "http://lbsyun.baidu.com/jsdemo/img/fox.gif";
//             var myIcon = new BMap.Icon(url, new BMap.Size(40,30));
//             var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
//             map.addOverlay(marker);


        localSearch.setSearchCompleteCallback(function (searchResult) {
            //var poi = searchResult.getPoi(0);
            // var poi = null;
            // poi.point = pp;
            var point1 = pp;
            //document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat; //获取经度和纬度，将结果显示在文本框中
            map.centerAndZoom(point1, 13);

            var point = new BMap.Point(point1.lng, point1.lat);
//                var marker = new BMap.Marker(point);  // 创建标注，为要查询的地址对应的经纬度
//                map.addOverlay(marker);

            var url = "http://image.tupian114.com/20140419/09274112.png";
            //var url= "http://lbsyun.baidu.com/jsdemo/img/fox.gif";
            var myIcon = new BMap.Icon(url, new BMap.Size(40,30));
            var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
            map.addOverlay(marker);



            //4.2 显示经纬度信息
            var opts = {
                width:100,
                height:120,
                title:"设备信息",
            };

            var content = "地址："+document.getElementById("suggestId").value + "<br/><br/>经度：" + point1.lng + "<br/>纬度：" + point1.lat;
            //var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
            var infoWindow = new BMap.InfoWindow(content,opts);
            marker.addEventListener("click", function () { map.openInfoWindow(infoWindow,point); });

            //4.3 将sn码 校验码 地址 及 经纬度 下发
            var snCode = document.getElementById("snCode").value;
            var regCode = document.getElementById("jiaoyan").value;
            var deviceAddress = keyword;
            var deviceLng = point1.lng;
            var deviceLat = point1.lat;

            var sendFlag = false;
            if(snCode != "" && regCode != ""){
                sendFlag = true;
            }
            if(sendFlag){
                $.ajax({
                    type : "POST",
                    url : "/device/register",
                    data : {
                        //"s123" :     $("#check").html(),
                        "snCode" :  snCode,
                        "regCode" : regCode,
                        "deviceAddress" : deviceAddress,
                        "deviceLng" : deviceLng,
                        "deviceLat": deviceLat
                    },
                    dataType : "json",
                    success : function(data) {
                        if (data.result == "0") {
                            alert('设备注册成功！');
                            $("#query_").attr('disabled',true);//避免重复注册
                            document.getElementById("snCode").value = "";
                            document.getElementById("suggestId").value = "";
                            document.getElementById("jiaoyan").value = "";
                            //window.location.href ="/index?userName="+$("#userName").val();
                            <!-- 传值成功 如何到index.html中接收？ -->
                        } else if(data.result == "1") {
                            alert('设备不存在或未在设备上生成校验码！');
                        }else if(data.result == "2"){
                            alert('校验码错误！');
                        }else if(data.result == "3"){
                            alert('校验码过期！');
                        }else if(data.result == "4"){
                            alert('设备注册失败！');
                        }
                    }
                });
            }else {
                alert('SN码和校验码不为空！');
            }



        });
        localSearch.search(keyword);

    });

}



