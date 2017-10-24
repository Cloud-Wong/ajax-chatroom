function SendContent(text) {
	$.ajax({
		type:"POST",
		url:"chat",
		data:"action=SendContent&d="+new Date()+"&content="+text,
		success:function(data){
			if(data==1){
				$("#txtContent").val('');
				GetMessageList();
				$content.focus();
			}
			else{
				alert("请先登录！");
				window.location.href="index.html";
			}
		}
	});
}

function GetMessageList(){
	$.ajax({
		type:"POST",
		url:"chat",
		data:"action=ChatList",
		success:function(data){
			$("#divContent").html(data);
		}
	});
	AutoUpdContent();
}

function GetOnlineList(){
	$.ajax({
		type:"POST",
		url:"chat",
		data:"action=OnlineList",
		success:function(data){
			$("#online-user-List").html(data);
		}
	});
	AutoUpdContent();
}
function AutoUpdContent() {
    setTimeout(GetMessageList, 1000);
    setTimeout(GetOnlineList, 1000);
}


$(function(){
	
	//发送ajax
    $("#send").click(function(){
    	var $content = $("#txtContent");
    	if($content.val()==""){
    		alert("发送内容不能为空!");
    		$content.focus();
    	}
    	else{
    		SendContent($content.val());	
    	}
    })

});