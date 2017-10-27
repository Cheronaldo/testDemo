// 百度地图API功能
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

//以下添加代码

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

    setPlace();
});

function setPlace(){

    $("#query_").attr('disabled',false);

    map.clearOverlays();    //清除地图上所有覆盖物
    function myFun(){
        var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
        map.centerAndZoom(pp, 18);
        map.addOverlay(new BMap.Marker(pp));    //添加标注
    }
    var local = new BMap.LocalSearch(map, { //智能搜索
        onSearchComplete: myFun
    });
    local.search(myValue);
}

function setDisabled() {

    $("#query_").attr('disabled',true);
}

//以上为添加代码

// 地址输入框id 改为 suggestId

$(function() {

    $("#query_").click(function (e) {

        map.clearOverlays();//清空原来的标注
        var keyword = document.getElementById("suggestId").value;
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            //document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat; //获取经度和纬度，将结果显示在文本框中
            map.centerAndZoom(poi.point, 13);

            var point = new BMap.Point(poi.point.lng, poi.point.lat);
//                var marker = new BMap.Marker(point);  // 创建标注，为要查询的地址对应的经纬度
//                map.addOverlay(marker);

            var url = "http://image.tupian114.com/20140419/09274112.png";
            //var url= "http://lbsyun.baidu.com/jsdemo/img/fox.gif";
            var myIcon = new BMap.Icon(url, new BMap.Size(40,30));
            var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
            map.addOverlay(marker);



            //显示经纬度信息
            var opts = {
                width:100,
                height:120,
                title:"设备信息",
            };

            var content = "地址："+document.getElementById("suggestId").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
            //var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
            var infoWindow = new BMap.InfoWindow(content,opts);
            marker.addEventListener("click", function () { map.openInfoWindow(infoWindow,point); });

            //将地址 及 经纬度 下发
            var deviceAddress = keyword;
            var deviceLng = poi.point.lng;
            var deviceLat = poi.point.lat;

            $.ajax({
                type : "POST",
                url : "/device/register",
                data : {
                    //"s123" :     $("#check").html(),
                    "deviceAddress" : deviceAddress,
                    "deviceLng" : deviceLng,
                    "deviceLat": deviceLat
                },
                dataType : "json",
                success : function(data) {
                    if (data.result == "0") {
                        alert('设备注册成功！');
                        $("#query_").attr('disabled',true);//避免重复注册
                        //window.location.href ="/index?userName="+$("#userName").val();
                        <!-- 传值成功 如何到index.html中接收？ -->
                    } else {
                        alert("提示",'设备注册失败！');
                    }
                }
            });

        });
        localSearch.search(keyword);

    });

});