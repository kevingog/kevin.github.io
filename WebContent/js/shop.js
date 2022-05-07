function showToast(msg, duration) {
    duration = isNaN(duration) ? 3000 : duration;
    var m = document.createElement('div');
    m.innerHTML = msg;
    m.style.cssText = "width:60%; min-width:180px; background:#6633CC ; opacity:0.6; height:auto;min-height: 30px; color:#fff; line-height:30px; text-align:center; border-radius:4px; position:fixed; top:60%; left:20%; z-index:999999;";
    document.body.appendChild(m);
    setTimeout(function() {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(m) }, d * 1000);
    }, duration);
}

function cambiar_login() {
//    showToast("恭喜您！请求数据成功！请求数据成功！", 3000);
    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
    document.querySelector('.cont_form_login').style.display = "block";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";

    setTimeout(function() { document.querySelector('.cont_form_login').style.opacity = "1"; }, 400);

    setTimeout(function() {
        document.querySelector('.cont_form_sign_up').style.display = "none";
    }, 200);
}

function cambiar_sign_up(at) {
    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
    document.querySelector('.cont_form_sign_up').style.display = "block";
    document.querySelector('.cont_form_login').style.opacity = "0";

    setTimeout(function() {
        document.querySelector('.cont_form_sign_up').style.opacity = "1";
    }, 100);

    setTimeout(function() {
        document.querySelector('.cont_form_login').style.display = "none";
    }, 400);


}



function ocultar_login_sign_up() {

    document.querySelector('.cont_forms').className = "cont_forms";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";
    document.querySelector('.cont_form_login').style.opacity = "0";

    setTimeout(function() {
        document.querySelector('.cont_form_sign_up').style.display = "none";
        document.querySelector('.cont_form_login').style.display = "none";
    }, 500);

}


function loadGoodsList(goodsList) {
    $.each(goodsList, function(i, item) {
        var goodsHtml = '<div class="col-md-3 col-sm-6">' +
            '<div class="product-grid">' +
            '<input type="hidden" class="id" value="' + item.id + '">' +
            '<div class="product-image">' +
            '<a href="#" class="image">' +
            '<img class="pic-1" src="' + item.imgUrl + '">' +
            '</a>' +
            '<span class="product-new-label">new</span>' +
            '</div>' +
            '<div class="product-content">' +
            '<h3 class="title"><a href="#">' + item.name + '</a></h3>' +
            '<div class="price">' + item.price + '</div>' +
            '<ul class="rating">';
        for (var i = 0; i < item.starNum; i++) {
            goodsHtml += '<li class="fas fa-star"></li>';
        }
        for (var i = 0; i < 5 - item.starNum; i++) {
            goodsHtml += '<li class="far fa-star"></li>';
        }
        goodsHtml = goodsHtml + '</ul><a class="add-to-cart" href="#">' +
            '<span class="addCar" style="width:100%;background:red;color:white">Buy</span>' +
            '</a>' +
            '</div>' +
            '</div>' +
            '</div>';
        $('.row').append(goodsHtml);
    })
}


function loadOrders(list) {
	$.each(list, function(i, order) {	
		var ordersHtml = 
		'<div class="goods-item">'+
			'<div class="panel panel-default">'+
				'<div class="panel-body">'+
					'<div class="col-md-2 car-goods-info goods-count">'+
						'<span>'+order.id+'</span>'+
					'</div>'+
					'<div class="col-md-3 car-goods-info goods-image-column">'+
						'<img class="goods-image" src="'+order.imgUrl+'" style="width: 100px; height: 100px;margin-left: 30px;">'+
						'<span id="goods-info" style="width: calc(100% - 135px);">'+order.goodsName+'</span>'+
					'</div>'+
					'<div class="col-md-2 car-goods-info goods-price">'+
						'<span>￥</span><span class="single-price">'+order.orderPrice+'</span>'+
					'</div>'+
					'<div class="col-md-2 car-goods-info goods-money-count">'+
						'<span>';
						
					if(order.status==0){						
						var timeHtmlStr=getTimeHtml(order.buyTime);
						ordersHtml+='待付款</span>'+
							'</div>'+
							'<div class="col-md-2 car-goods-info goods-money-count">'+
								'<div style="text-align:center">'+
								timeHtmlStr+
								'</div>'+
							'</div>'+
							'<div class="col-md-2 car-goods-info goods-operate">'+
								'<button type="button" class="btn btn-warning item-pay" style="margin: 0 5px;">付款</button>'+
							'</div>'+
							'</div></div></div>';
					}else if(order.status==1){
						var timeHtmlStr=getTimeHtml(order.payTime);
						ordersHtml+='已完成</span>'+
							'</div>'+
							'<div class="col-md-2 car-goods-info goods-money-count">'+
								'<div style="text-align:center">'+
								 timeHtmlStr+
								'</div>'+
							'</div>'+
							'<div class="col-md-2 car-goods-info goods-operate">'+
								'<button type="button" class="btn btn-info item-detail">详情</button>'+
							'</div>'+
							'</div></div></div>';
						
					}
						
			$('.goods-content').append(ordersHtml)
	})
}

function getTimeHtml(timeStr){
	var htmlStr = '<div>'+timeStr+'</div>';
	if(timeStr != null && timeStr != undefined && timeStr != ""){
		try {		
			var timeArr = timeStr.split(/\s+/);
			htmlStr = '<div>'+timeArr[0]+'</div>'+
					  '<div>'+timeArr[1]+'</div>';
		}
		catch(err){
			 console.log(err);
		}
	}
	return htmlStr;
}

function showDetail(detail){
	var orderDetailHtml=
	'<div>'+
	'订单编号：'+detail.id+
	'</div>'+
	'<div>'+
	'商品名称：'+detail.goodsName+
	'</div>'+
	'<div>'+
	'商品单价：￥'+detail.goodsPrice+
	'</div>'+
	'<div>'+
	'商品数目：'+detail.goodsNum+
	'</div>'+
	'<div>'+
	'订单金额：￥'+detail.orderPrice+
	'</div>'+
	'<div>'+
	'买家地址：'+detail.buyerAddress+
	'</div>'+
	'<div>'+
	'卖家地址：'+detail.sellerAddress+
	'</div>'+
	'<div>'+
	'下单时间：'+detail.buyTime+
	'</div>'+
	'<div>'+
	'付款时间：'+detail.payTime+
	'</div>'+
	'<div>';
	if(detail.status==0){
		orderDetailHtml+='订单状态：待付款</div>';
	}else if(detail.status==1){
		orderDetailHtml+='订单状态：已完成</div>';
	}else{
		orderDetailHtml+='订单状态：已取消</div>';
	}
	$('.orderDetail').html(" ");
	$('.orderDetail').append(orderDetailHtml);
	$('#detailItemTip').modal('show');
}

function showBalance(email){
	var paramStr = "email="+email;
	$.ajax({
		url:"http://localhost:8080/OnlineShop/getBalance",
		data:paramStr,
		type:"POST",
		async:false,
		success:function(msg){
			var result = JSON.parse(msg);
			if(result.flag== "success"){
				$(".balance span").text(result.data);
			}
		}
		
	});
}
