var goodsId = 0;
$(document).ready(function() {
	var email = localStorage.getItem("userEmail");
	if(email == null || email == undefined){
		email ="";
	}
	//获取账户信息【TODO】，此处ajax使用同步查询 async:false
	$(".email").text(email);
	showBalance(email);
	
	//充值按钮绑定事件
	$(".recharge").on('click',function(){
		$('#rechargeItemTip').modal('show');
	})
	//充值确认按钮
	$('.rechargeSure').on('click', function() {
		$('#rechargeItemTip').modal('hide');
		var amount = $("#amount").val();
		if(amount == ""){
			alert("请输入金额!");
			return;
		}
		var paramStr ="email="+email+"&amount="+amount;
		$.ajax({
			url:"http://localhost:8080/OnlineShop/recharge",
			data:paramStr,
			type:"POST",
			success:function(msg){
				var result = JSON.parse(msg);
				if(result.flag== "success"){
					showToast("充值成功,以太币稍后到账，请注意查收", 3000);
				}else{
					showToast(result.data, 3000);
				}
			}
		});
		
	})
	
	$(".logout").on('click',function(){
		localStorage.setItem("userEmail", "");
		location.href='./login.html';
	})
	
	//显示商品列表
	showGoodsList(email);
		
	//点击buy按钮
    $(".row").on('click', '.add-to-cart', function() {
        goodsId = $(this).parents('.product-grid').find('.id').val();
		$('#buyItemTip').modal('show');
    })
	$('.buySure').on('click', function() {
		$('#buyItemTip').modal('hide');
		/*
		ajax在此处完成购买接口的调用:1.提示信息；2.如果付款成功则延时3秒跳转订单页【TODO】
		
		//提示信息调用示例
		showToast("登录成功！", 3000);
		//延时三秒跳转示例（保证提示能够有充足时间看到）
		setTimeout(function(){
			window.location.href="./itemList.html";
		},3000);
		*/
		
	})

});



function showGoodsList(email){
	//此处编写ajax在成功后的回调函数success中将后端查结果存入goodsList变量并调用loadGoodsList()函数即可
	var paramStr ="email="+email;
	$.ajax({
		url:"http://localhost:8080/OnlineShop/goodsList",
		data:paramStr,
		type:"POST",
		success:function(msg){
			var result = JSON.parse(msg);
			if(result.flag== "success"){
				goodsList = result.data;
				loadGoodsList(goodsList);
			}else{
				showToast(result.data, 3000);
			}
		}
		
	});
}




