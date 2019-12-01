<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="newAPI" value="/api/new" />
<c:url var="listURL" value="/quan-tri/bai-viet/danh-sach" />
<c:url var="editNewURL" value="/quan-tri/bai-viet/chinh-sua"/>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<!-- <li>
								<a href="#">Forms</a>
							</li> -->
							<li class="active">Form Elements</li>
						</ul><!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">
  								${message}
							</div>
						</c:if>
						<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
									<div class="form-group">
										  <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Thể loại:</label>
										  <div class="col-sm-9">
										  	 <form:select path="categoryCode" id="categoryCode">
										  	 	<form:option value="" label="-- Chọn thể loại --"/>
										  	 	<form:options items="${categories}"/>
										  	 </form:select>
										  </div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Tên bài viết </label>
										<div class="col-sm-9">
											<!-- <input type="text" id="title" name="title" class="col-xs-10 col-sm-5" /> -->
											<form:input path="title" type="text" id="title" cssClass="col-xs-10 col-sm-5"/>	
										</div>
									</div>

									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> Ảnh đại diện </label>
										<div class="col-sm-9">
											<!-- <input type="file" id="thumbnail" name="thumbnail" class="col-xs-10 col-sm-5" /> -->
											<form:input path="thumbnail" type="file" id="thumbnail" cssClass="col-xs-10 col-sm-5"/>										
										</div>
									</div>

									<div class="form-group">
									  	<label for="shortDescription" class="col-sm-3 control-label no-padding-right">Mô tả ngắn:</label>
									  	<div class="col-sm-9">
									  		<form:textarea path="shortDescription" rows="5" cols="10" cssClass="form-control" id="shortDescription"/>
								  		</div>
									</div>
									<div class="form-group">
									  	<label for="content" class="col-sm-3 control-label no-padding-right">Nội dung:</label>
									  	<div class="col-sm-9">
									  		<form:textarea path="content" rows="5" cols="10" cssClass="form-control" id="content"/>
									  	</div>
									</div>  
									
									<form:hidden path="id" id="id"/>
									
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
										<c:if test="${not empty model.id}">
											<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Cập nhật
											</button>
										</c:if>
										<c:if test="${empty model.id}">
											<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Thêm mới
											</button>
										</c:if>
											&nbsp; &nbsp; &nbsp;
                                                                                        <button class="btn" type="reset" onclick="gotoListItem()">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												Xem danh sách
											</button>
										</div>
									</div>

									<div class="hr hr-24"></div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#btnAddOrUpdateNew').click(function(e){
                   e.preventDefault();
                   var data = {};
                   var formData = $('#formSubmit').serializeArray();
                   $.each(formData,function (i,v){
                      data[""+v.name+""] = v.value; 
                   });
                   var id = $('id').val();
                   if(id == ""){
                       //= Thêm mới bài viết
                       insertData(data);
                   }else{
                       //= Cập nhật bài viết
                       updateData(data);
                   }
                });
                
                function insertData(data){
                    $.ajax({
                       url: '${newAPI}',
                       type: 'POST',
                       contentType: 'application/json',
                       data: JSON.stringify(data),
                       dataType: 'json',
                       success: function (success){
                           window.location.href = "${listURL}?id="+success.id+"&message=insert_success";
                       },
                       error: function (error) {
                    	   window.location.href = "${listURL}?page=1&limit=2&message=insert_error";
                       } 
                    });
                }
                
                function updateData(data){
                    $.ajax({
                       url: '${newAPI}',
                       type: 'PUT',
                       contentType: 'application/json',
                       data: JSON.stringify(data),
                       dataType: 'json',
                       success: function (success){
                           window.location.href = "${editNewURL}?id="+success.id+"&message=update_success";
                       },
                       error: function (error) {
                    	   window.location.href = "${editNewURL}?id="+success.id+"&message=update_error";
                       } 
                    });
                }
                
         function gotoListItem(){
            window.location.href = "${listURL}?page=1&limit=2";
         }
	</script>
	
</body>
</html>
