
$(function() {

    $.ajax({
        type : "POST",
        url : "/test/list1",
        data : {
            "deviceOwner" : "亿维自动化"
        },
        dataType : "json",
        success : function(data) {

            if(data.result != null){

                var html = null;

                $.each(data.result,function (index,device) {
                    html += "<tr>";
                    html += "<td>" + device.deviceType + "</td>";
                    html += "<td>" + device.deviceStatus + "</td>";
                    html += "<td>" + device.deviceAddress + "</td>";
                    html += "<td>" + device.deviceLng + "</td>";
                    html += "<td>" + device.deviceLat + "</td>";
                    html += "</tr>";
                });


                $("#tableList").append(html);

            }

        }
    });


});










