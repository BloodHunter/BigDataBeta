/**
 * Created by Simple_love on 2015/10/30.
 *
 */


function search(){
        var dataName = $(".todo-search-field").val();
        $("button").unbind();
        if(dataName.length>0){
                $.getJSON("prov/queryProvByName?dataName=" + dataName,function(response){
                        if(response.result == "SUCCESS"){
                                var queryID = response.queryID;
                                provShow(0);
                                $("input[name=pageNum]:visible").val("");
                                $("input[name=totalPage]:visible").val(response.totalNum);
                                page(1,queryID);
                                $("button").unbind().bind("click",function(){
                                        var currentPage = parseInt($("input[name=currentPage]:visible").val());
                                        console.log(this.value);
                                        switch (this.value){
                                                case "first":
                                                        page(1,queryID);
                                                        break;
                                                case "previous":
                                                        page(currentPage-1,queryID);
                                                        break;
                                                case "next":
                                                        page(currentPage+1,queryID);
                                                        break;
                                                case "last":
                                                        page(response.totalNum,queryID);
                                                        break;
                                                case "goto":
                                                        var gotoPage = parseInt( $("input[name=pageNum]:visible").val());
                                                        console.log(gotoPage);
                                                        if(gotoPage <= 0 || gotoPage > response.totalNum){
                                                                alert("请输入正确的页数");
                                                        }else{
                                                                page(gotoPage,queryID);
                                                        }
                                                        break;
                                        }
                                })
                        }
                })
        }else{
                console.log("name can't be empty");
        }

        function page(currentPage,queryID){
                var totalNum = parseInt($("input[name=totalPage]:visible").val());
                if(currentPage <= 0){
                        alert("已经是最前一页了");
                }else if(currentPage > totalNum){
                        alert("已经是最后一页了");
                }else{
                        $("input[name=currentPage]:visible").val(currentPage);
                        $("#provImage").attr("src","resources/provImage/" + queryID + "-"+ (currentPage - 1)+".jpg");
                }
        }
}

function searchForUser(){
        page(1);
        $("input[name=pageNum]:visible").val("");
        $("button").unbind().bind("click",function(){
                var currentPage = parseInt($("input[name=currentPage]:visible").val());
                switch (this.value){
                        case "first":
                                console.log(this.value);
                                page(1);
                                break;
                        case "previous":
                                page(currentPage - 1);
                                console.log(this.value);
                                break;
                        case "next":
                                page(currentPage + 1);
                                console.log(this.value);
                                break;
                        case "goto":
                                var gotoPage = parseInt( $("input[name=pageNum]:visible").val());
                                if(gotoPage <=0){
                                        alert("请输入正确的页数");
                                }else{
                                        page(gotoPage);
                                }
                                break;
                }
        });

        function page(currentPage){
                if(currentPage <= 0){
                        alert("已经是最前一页了");
                }else{
                        $("input[name=currentPage]:visible").val(currentPage);
                        $('.table').empty();
                        var startRow = currentPage > 0 ? (currentPage - 1) * 10 :0;
                        var endRow = startRow + 10 * (currentPage > 0 ? 1:0);
                        $.getJSON("prov/queryProvDetail?startRow=" + startRow + "&&endRow=" + endRow,function(response){
                                if(response.result == "SUCCESS"){
                                        list = response.provs;
                                        for(var i = 0; i < list.length;i++){
                                                $(".table").append("<tr><td>" + list[i].agent +"</td><td>" + list[i].activity + "</td></tr>")
                                        }
                                        provShow(1);
                                }
                        })
                }
        }
        console.log("searchForUser");
}


function searchForOperate(){
        provShow(1);
        console.log("searchForOperate");
}

function provShow(index){
        $("div[class='container prov']").hide();
        $($("div[class='container prov']")[index]).show();
}