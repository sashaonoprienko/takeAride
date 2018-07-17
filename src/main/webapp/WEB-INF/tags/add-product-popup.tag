<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:if test="${language  == null }">
<div id="addProductPopup" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title">Ordering</h4>
                  <h5 class="modal-title" style="color:#FF0000">To make final order,please go to myOrders</h5>
                </div>
                <div class="modal-body row">
                  <div class="col-xs-12 col-sm-6">
                    <div class="thumbnail">
                      <img class="product-image" src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=" alt="Product image">
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-6">
                    <h4 class="name text-center">Name</h4>
                    <div class="list-group hidden-xs adv-chars">
                      <span class="list-group-item"> <small>Category:</small> <span class="category">?</span></span> 
                      <span class="list-group-item"> <small>Producer:</small> <span class="producer">?</span></span>
                    </div>
                    <div class="list-group">
                      <span class="list-group-item"> <small>Price:</small> <span class="price">0</span></span> 
                      <span class="list-group-item"> <small>Number</small> <input type="number" class="count" value="1" min="1" max="10"></span>
                      <span class="list-group-item"> <small>Cost:</small> <span class="cost">0</span></span>
                    </div>
                  </div>
                </div>
                <div class="modal-footer">
                  <button id="addToCart" type="button" class="btn btn-primary">Add to Cart</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
          </div>
          </c:if>
          
          <c:if test="${language  == 'Russ' }">
          <div id="addProductPopup" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title">Ordering</h4>
                  <h5 class="modal-title" style="color:#FF0000">Чтобы оформить заказ,перейдите в Мои заказы!</h4>
                </div>
                <div class="modal-body row">
                  <div class="col-xs-12 col-sm-6">
                    <div class="thumbnail">
                      <img class="product-image" src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=" alt="Product image">
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-6">
                    <h4 class="name text-center">Название</h4>
                    <div class="list-group hidden-xs adv-chars">
                      <span class="list-group-item"> <small>Класс:</small> <span class="category">?</span></span> 
                      <span class="list-group-item"> <small>Марка:</small> <span class="producer">?</span></span>
                    </div>
                    <div class="list-group">
                      <span class="list-group-item"> <small>Цена:</small> <span class="price">0</span></span> 
                      <span class="list-group-item"> <small>Кол-во суток:</small> <input type="number" class="count" value="1" min="1" max="10"></span>
                      <span class="list-group-item"> <small>Ваше полное имя:</small><input type="text" class="passName" size="30"> </span>
                      <span class="list-group-item"> <small>Номер и серия пспорта:</small><input type="text" class="passport" size="28"> </span>
                        <span class="list-group-item"><small>Мне нужен водитель</small> <input id="needDriver" type="radio"></span>
                      <span class="list-group-item"> <small>Общая стоимость:</small> <span class="cost">0</span></span>
                    </div>
                  </div>
                </div>
                <div class="modal-footer">
                  <button id="addToCart" type="button" class="btn btn-primary">Добавить</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </div>
              </div>
            </div>
          </div>
          
          </c:if>.