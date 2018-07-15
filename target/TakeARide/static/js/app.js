;$(function(){
	var init = function (){
		initBuyBtn();
		$('#addToCart').click(addProductToCart);
		$('#addByadmin').click(addProductByAdmin);
		$('#setManByadmin').click(setManagerByAdmin);
		$('#DelByadmin').click(DelProductByAdmin);
		$('#UpByadmin').click(updateProductByAdmin);
		$('#blockByadmin').click(blockUserByAdmin);
		$('#UnblockByadmin').click(UnblockUserByAdmin);
		$('#doneBtn').click(doneOrder);
		$('#rejectBtn').click(rejectOrder);
		$('#showHistory').click(orderHistory);
		$('#hideHistory').click(orderHistory);
		$('#checked').click(setChecked);
		$('#toEng').click(toEnglish);
		$('#toRus').click(toRussian);
		$('#addProductPopup .count').change(calculateCost);
		$('#loadMore').click(loadMoreProducts);
		$('#loadMoreMyOrders').click(loadMoreMyOrders);
		initSearchForm();
		$('#goSearch').click(goSearch);
		$('.remove-product').click(removeProductFromCart);
		$('.post-request').click(function(){
			postRequest($(this).attr('data-url'));
		});
	};

	var showAddProductPopup = function (){
		var idProduct = $(this).attr('data-id-product');
		var product = $('#product'+idProduct);
		$('#addProductPopup').attr('data-id-product', idProduct);
		$('#addProductPopup .product-image').attr('src', product.find('.thumbnail img').attr('src'));
		$('#addProductPopup .name').text(product.find('.name').text());
		var price = product.find('.price').text();
		$('#addProductPopup .price').text(price);
		$('#addProductPopup .category').text(product.find('.category').text());
		$('#addProductPopup .producer').text(product.find('.producer').text());
		$('#addProductPopup .count').val(1);
		$('#addProductPopup .cost').text(price);
		$('#addToCart').removeClass('hidden');
		$('#addToCartIndicator').addClass('hidden');
		$('#addProductPopup').modal({
			show:true
		});
	};
	var initBuyBtn = function(){
		$('.buy-btn').click(showAddProductPopup);
	};
	var postRequest = function(url){
		var form = '<form id="postRequestForm" action="'+url+'" method="post"></form>';
		$('body').append(form);
		$('#postRequestForm').submit();
	};
	var addProductToCart = function (){
		var idProduct = $('#addProductPopup').attr('data-id-product');
		var count = $('#addProductPopup .count').val();
		var passport = document.querySelector(".passport").value
		var name = $('#addProductPopup .passName').val();
		var btn = $('#addToCart');
		var driver = 0;
		if(document.getElementById('needDriver').checked){
			driver = 1;
		}
		 if(passport == '')  
	       {
	           alert('Please input Passport.');
	           return false;
	       }
		 if(name == '')  
	       {
	           alert('Please input Name.');
	           return false;
	       }        
		convertButtonToLoader(btn, 'btn-primary');
		$.ajax({
			url : '/ajax/json/product/add' +'?passport='+ passport+'&name='+name+'&driver='+driver,
			method : 'POST',
			data : {
				idProduct : idProduct,
				count : count,
				passport : passport,
				name : name,
				driver : driver
			},
			success : function(data) {
				$('#currentShoppingCart .total-count').text(data.totalCount);
				$('#currentShoppingCart .total-cost').text(data.totalCost);
				$('#currentShoppingCart').removeClass('hidden');
				convertLoaderToButton(btn, 'btn-primary', addProductToCart);
				$('#addProductPopup').modal('hide');
			},
			error : function(xhr) {
				convertLoaderToButton(btn, 'btn-primary', addProductToCart);
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var addProductByAdmin = function (){
		var fullName = document.querySelector(".fullname").value
		var producer = document.querySelector(".producer").value
		var category = document.querySelector(".category").value
		var price = document.querySelector(".price").value
		var description = document.querySelector(".description").value
		var idProduct = document.querySelector(".id").value
		var filePath = document.querySelector(".pic").value.replace(/C:\\fakepath\\/i, '')
		var butn = $('#addByadmin');
		          if(fullName == '')  
			       {
			           alert('Please input Name.');
			           return false;
			       }        
			       if(producer == '')  
			       {
			           alert('Please input producer.');
			           return false;
			       }   			       
			       if(category == '')  
			       {
			           alert('Please input category.');
			           return false;
			       }   			       
			       if(price == '')  
			       {
			           alert('Please input price.');
			           return false;
			       }   
			
			       if(description == '')  
			       {
			           alert('Please input description.');
			           return false;
			       }   
			     
			       if(idProduct == '')  
			       {
			           alert('Please input idProduct.');
			           return false;
			       }   
			     
			       if(filePath == '')  
			       {
			           alert('Please insert File.');
			           return false;
			       }   
			     
		
		$.ajax({
			url : 'ajax/json/product/addByAdmin' +'?name='+ fullName+'&producer='+producer+'&category='+category+'&price='+price+'&description='+description+'&idProduct='+idProduct+'&file='+filePath,
			method : 'POST',
			data : {
				fullName : fullName,
				producer : producer,
				category : category,
				price : price,
				description : description,
				idProduct : idProduct,
				filePath : filePath
			},
			success : function(data) {
				
				location.reload(true)
			},
			error : function(xhr) {
				convertLoaderToButton(btn, 'btn-primary', addProductByAdmin);
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var DelProductByAdmin = function (){
		var fullName = document.querySelector(".fullnameforDel").value
		var butn = $('#DelByadmin');
		
		   if(fullName == '')  
	       {
	           alert('Please input Name.');
	           return false;
	       }   
	     
		$.ajax({
			url : '/ajax/json/product/deleteByAdmin' +'?fullName' + fullName,
			method : 'POST',
			data : {
				fullName : fullName
			},
			success : function(data) {
				
				location.reload(true)
			},
			error : function(xhr) {
		
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var updateProductByAdmin = function (){
		var fullName = document.querySelector(".fullnameUp").value
		var price = document.querySelector(".priceUp").value
		var description = document.querySelector(".descriptionUp").value
		var idProduct = document.querySelector(".idUp").value
		var butn = $('#UpByadmin');
	
		   if(fullName == '')  
	       {
	           alert('Please input Name.');
	           return false;
	       }   
	      
	       if(price == '')  
	       {
	           alert('Please input price.');
	           return false;
	       }   
	     
	       if(description == '')  
	       {
	           alert('Please input description.');
	           return false;
	       }   
	    
	       if(idProduct == '')  
	       {
	           alert('Please input idProduct.');
	           return false;
	       }   
	      
		$.ajax({
			url : '/ajax/json/product/upByAdmin' +'?name='+ fullName+'&price='+price+'&description='+description+'&idProduct='+idProduct,
			method : 'POST',
			data : {
				fullName : fullName,
				price : price,
				description : description,
				idProduct : idProduct
			},
			success : function(data) {
				
				location.reload(true)
			},
			error : function(xhr) {
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var blockUserByAdmin = function (){
		var userEmail = document.querySelector(".email").value
		var butn = $('#blockByadmin');
		   if(userEmail == '')  
	       {
	           alert('Please input Email.');
	           return false;
	       }   
		$.ajax({
			url : '/ajax/json/product/blockByadmin' +'?useremail='+ userEmail,
			method : 'POST',
			data : {
				userEmail : userEmail,
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var UnblockUserByAdmin = function (){
		var userEmail = document.querySelector(".emailUn").value
		   if(userEmail == '')  
	       {
	           alert('Please input Email.');
	           return false;
	       }   
		$.ajax({
			url : '/ajax/json/product/UnblockByadmin' +'?useremail='+ userEmail,
			method : 'POST',
			data : {
				userEmail : userEmail,
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var setManagerByAdmin = function (){
		var userEmail = document.querySelector(".emailM").value
		   if(userEmail == '')  
	       {
	           alert('Please input Email.');
	           return false;
	       }   
		$.ajax({
			url : '/ajax/json/product/setManager' +'?useremail='+ userEmail,
			method : 'POST',
			data : {
				userEmail : userEmail,
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var rejectOrder = function (){
		var message = document.querySelector(".reason").value
		var idOrder = document.querySelector(".idorder").value
		   if(message == '')  
	       {
	           alert('Please input Reason.');
	           return false;
	       }   
		$.ajax({
			url : '/ajax/json/product/rejectByManager' +'?message='+ message +'&idorder='+ idOrder,
			method : 'POST',
			data : {
				idOrder : idOrder,
				message : message
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var doneOrder = function (){
		var message = document.querySelector(".returnMessage").value
		var idOrder = document.querySelector(".idDoneorder").value
		  
		$.ajax({
			url : '/ajax/json/product/orderDone' +'?message='+ message + '&id=' + idOrder,
			method : 'POST',
			data : {
				message : message,
				idOrder : idOrder
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var toEnglish = function (){
	 
		$.ajax({
			url : '/ajax/json/toEng',
			method : 'POST',
			data : {
				
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var toRussian = function (){
		 
		$.ajax({
			url : '/ajax/json/toRus',
			method : 'POST',
			data : {
				
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var setChecked = function (){
	var id = getButtonValue($('#checked'))
		$.ajax({
			url : '/ajax/json/setChecked'+'?id='+ id,
			method : 'POST',
			data : {
				id: id
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var getButtonValue = function($button) {
	    var label = $button.text(); 
	    $button.text('');
	    var buttonValue = $button.val();
	    $button.text(label);
	    return buttonValue;
	};
	var orderHistory = function (){
		 
		$.ajax({
			url : '/ajax/json/hist',
			method : 'POST',
			data : {
				
			},
			success : function(data) {	
				location.reload(true)
			},
			error : function(xhr) {
				
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	var calculateCost = function(){
		var priceStr = $('#addProductPopup .price').text();
		var price = parseFloat(priceStr.replace('$',' '));
		var count = parseInt($('#addProductPopup .count').val());
		var min = parseInt($('#addProductPopup .count').attr('min'));
		var max = parseInt($('#addProductPopup .count').attr('max'));
		if(count >= min && count <= max) {
			var cost = price * count;
			$('#addProductPopup .cost').text('$ '+cost);
		} else {
			$('#addProductPopup .count').val(1);
			$('#addProductPopup .cost').text(priceStr);
		}
	};
	
	var convertButtonToLoader = function (btn, btnClass) {
		btn.removeClass(btnClass);
		btn.removeClass('btn');
		btn.addClass('load-indicator');
		var text = btn.text();
		btn.text('');
		btn.attr('data-btn-text', text);
		btn.off('click');
	};
	var convertLoaderToButton = function (btn, btnClass, actionClick) {
		btn.removeClass('load-indicator');
		btn.addClass('btn');
		btn.addClass(btnClass);
		btn.text(btn.attr('data-btn-text'));
		btn.removeAttr('data-btn-text');
		btn.click(actionClick);
	};
	
	var loadMoreProducts = function (){
		var btn = $('#loadMore');
		convertButtonToLoader(btn, 'btn-success');
		var pageNumber = parseInt($('#productList').attr('data-page-number'));
		var url = '/ajax/html/more' + location.pathname + '?page=' + (pageNumber + 1) + '&' + location.search.substring(1);
		$.ajax({
			url : url,
			success : function(html) {
				$('#productList .row').append(html);
				pageNumber++;
				var pageCount = parseInt($('#productList').attr('data-page-count'));
				$('#productList').attr('data-page-number', pageNumber);
				if(pageNumber < pageCount) {
					convertLoaderToButton(btn, 'btn-success', loadMoreProducts);
				} else {
					btn.remove();
				}
				initBuyBtn();
			},
			error : function(data) {
				convertLoaderToButton(btn, 'btn-success', loadMoreProducts);
				alert('Error');
			}
		});
	};
	var loadMoreMyOrders = function (){
		var btn = $('#loadMoreMyOrders');
		convertButtonToLoader(btn, 'btn-success');
		var pageNumber = parseInt($('#myOrders').attr('data-page-number'));
		var url = '/ajax/html/more/my-orders?page=' + (pageNumber + 1);
		$.ajax({
			url : url,
			success : function(html) {
				$('#myOrders tbody').append(html);
				pageNumber++;
				var pageCount = parseInt($('#myOrders').attr('data-page-count'));
				$('#myOrders').attr('data-page-number', pageNumber);
				if (pageNumber < pageCount) {
					convertLoaderToButton(btn, 'btn-success', loadMoreMyOrders);
				} else {
					btn.remove();
				}
			},
			error : function(xhr) {
				convertLoaderToButton(btn, 'btn-success', loadMoreMyOrders);
				if (xhr.status == 401) {
					window.location.href = '/sign-in';
				} else {
					alert('Error');
				}
			}
		});
	};
	
	/*var loadMore = function (btn, urlTemplate, attrContainer, appendContainer, onSuccess){
		convertButtonToLoader(btn, 'btn-success');
		var pageNumber = parseInt(attrContainer.attr('data-page-number'));
		var url = urlTemplate.replace('$PAGE_NUM',(pageNumber + 1));
		$.ajax({
			url : url,
			success : function(html) {
				appendContainer.append(html);
				pageNumber++;
				var pageCount = parseInt(attrContainer.attr('data-page-count'));
				attrContainer.attr('data-page-number', pageNumber);
				if (pageNumber < pageCount) {
					convertLoaderToButton(btn, 'btn-success', function(){
						loadMore(btn, urlTemplate, attrContainer, appendContainer, onSuccess);
					});
				} else {
					btn.remove();
				}
				if(onSuccess != undefined) {
					onSuccess();
				}
			},
			error : function(data) {
				convertLoaderToButton(btn, 'btn-success', function(){
					loadMore(btn, urlTemplate, attrContainer, appendContainer, onSuccess);
				});
				alert('Error');
			}
		});
	};
	
	var loadMoreProducts = function (){
		loadMore(
			$('#loadMore'), 
			'/ajax/html/more' + location.pathname + '?page=$PAGE_NUM&' + location.search.substring(1),
			$('#productList'),
			$('#productList .row'),
			function(){
				initBuyBtn();
			}
		);
	};
	
	var loadMoreMyOrders = function (){
		loadMore(
			$('#loadMoreMyOrders'), 
			'/ajax/html/more/my-orders?page=$PAGE_NUM',
			$('#myOrders'),
			$('#myOrders tbody')
		);
	};*/
	
	var initSearchForm = function (){
		$('#allCategories').click(function(){
			$('.categories .search-option').prop('checked', $(this).is(':checked'));
		});
		$('.categories .search-option').click(function(){
			$('#allCategories').prop('checked', false);
		});
		$('#allProducers').click(function(){
			$('.producers .search-option').prop('checked', $(this).is(':checked'));
		});
		$('.producers .search-option').click(function(){
			$('#allProducers').prop('checked', false);
		});
	};
	var goSearch = function(){
		var isAllSelected = function(selector) {
			var unchecked = 0;
			$(selector).each(function(index, value) {
				if(!$(value).is(':checked')) {
					unchecked ++;
				}
			});
			return unchecked === 0;
		};
		if(isAllSelected('.categories .search-option')) {
			$('.categories .search-option').prop('checked', false);
		}
		if(isAllSelected('.producers .search-option')) {
			$('.producers .search-option').prop('checked', false);
		}
		$('form.search').submit();
	};
	var confirm = function (msg, okFunction) {
		if(window.confirm(msg)) {
			okFunction();
		}
	};
	var removeProductFromCart = function (){
		var btn = $(this);
		confirm('Are you sure?', function(){
			executeRemoveProduct(btn);
		});
	};
	var refreshTotalCost = function () {
		var total = 0;
		$('#shoppingCart .item').each(function(index, value) {
			var count = parseInt($(value).find('.count').text());
			var price = parseFloat($(value).find('.price').text().replace('$', ' '));
			var val = price * count;
			total = total + val;
		});
		$('#shoppingCart .total').text('$'+total);
	};
	var executeRemoveProduct = function (btn) {
		var idProduct = btn.attr('data-id-product');
		var count = btn.attr('data-count');
		convertButtonToLoader(btn, 'btn-danger');
		
		$.ajax({
			url : '/ajax/json/product/remove',
			method : 'POST',
			data : {
				idProduct : idProduct,
				count : count
			},
			success : function(data) {
				if (data.totalCount == 0) {
					window.location.href = '/products';
				} else {
					var prevCount = parseInt($('#product' + idProduct + ' .count').text());
					var remCount = parseInt(count);
					if (remCount >= prevCount) {
						$('#product' + idProduct).remove();
					} else {
						convertLoaderToButton(btn, 'btn-danger', removeProductFromCart);
						$('#product' + idProduct + ' .count').text(prevCount - remCount);
						if(prevCount - remCount == 1) {
							$('#product' + idProduct + ' a.remove-all').remove();
						}
					}
					refreshTotalCost();
				}
			},
			error : function(data) {
				convertLoaderToButton(btn, 'btn-danger', removeProductFromCart);
				alert('Error');
			}
		});
	}

	init();
});