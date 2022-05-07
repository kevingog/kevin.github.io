
function signUp(){
	var email=$("#email").val();
	var name=$("#user").val();
	var password=$("#password").val();
	var confirmPassword=$("#confirmPassword").val();
	if(email == null){
		email="";
	}
	if(name == null){
		name="";
	}
	if(password == null){
		password="";
	}
	if(confirmPassword == null){
		confirmPassword="";
	}
	var paramStr="name="+name+"&email="+email+"&password="+password+"&confirmPassword="+confirmPassword;
	
	$.ajax({
		url:"http://localhost:8080/OnlineShop/signUp",
		data:paramStr,
		type:"POST",
		success:function(msg){
			var result = JSON.parse(msg);
			if(result.flag== "success"){
				showToast("注册成功", 3000);
				//页面跳转
				location.href='./goodsList.html';
				//注册成功会将邮箱存入缓存
				localStorage.setItem("userEmail", email);
			}else{
				showToast(result.data, 3000);
			}
		}		
	});
	
}
function login(){
	var email=$("#loginEmail").val();
	var password=$("#loginPassword").val();
	var paramStr="email="+email+"&password="+password;
		
	$.ajax({
		url:"http://localhost:8080/OnlineShop/login",
		data:paramStr,
		type:"POST",
		success:function(msg){
			var result = JSON.parse(msg);
			if(result.flag== "success"){
				showToast("登录成功", 3000);
				//页面跳转
				location.href='./goodsList.html';
				//注册成功会将邮箱存入缓存
				localStorage.setItem("userEmail", email);
			}else{
				showToast(result.data, 3000);
			}
		}		
	});
			

}

	