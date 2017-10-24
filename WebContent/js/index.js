function UserLogin(name,pass){
    $.ajax({
        type:"POST",
        url:"chat",  
        data:"action=Login&d="+new Date()+"&name="+name+"&pass="+pass,
        success:function(msg){
            if(msg=="yes"){
                window.location.href="chat.html";
            }else{
                alert("Information Wrong!");
                return false;
            }
        }
    });
}


$(function(){
	
	//登陆ajax传入数据验证
    $("#Login").click(function(){
        var $name=$("#txtName");
        var $pass=$("#txtPass");
        if($name.val()!="" && $pass.val()!=""){

            UserLogin($name.val(),$pass.val());

        }else{
            if($name.val()==""){
                alert("username is empty");
                $name.focus();
                return false;
            }else{
                alert("password is empty");
                $pass.focus();
                return false;
            }
        }
    })
    
});

