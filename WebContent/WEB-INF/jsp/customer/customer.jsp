<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<style type="text/css">
  span.error{
    color: red;
  }
  span.go{
    color: green;
    float:right;
  }
  .panel{
  	float:right;
  	margin-right: 20px;
  	clear:both;
  }
</style>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
var errors = '';
$(document).ready(function() {	  
	  $('#newcard-form').submit(function(event) {
		  	$.ajax({
		        url: $("#newcard-form").attr( "action"),
		        data: $('form[name=newcard-form]').serialize(),
		        type: "POST",
		         
		        success: function(res) {
		        	$(".error").remove();
	                $(".go").remove();
		        	 if(res.validated)
		        	 {
		                $('#createBtn').after('<span class="go">Card added successfully!</span>');
		             }
		        	 else
		             {
		        	   $.each(res.errorMessages,function(key,value){			            	
		   	           $('input[name='+key+']').after('<span class="error">'+value+'</span>');
		               });
		             }        
		        }
		    });
		  	event.preventDefault();
		  });	      
	    
	  });	    
	
</script>
</head>
<body>
<div id="customer">
        <h3 class="text-info panel"></h3>
        <a href="<c:url value='/logout' />"><span class="panel">Logout</span></a> 
        <a href="<c:url value='/cards/viewCards' />"><span class="panel">Search Cards</span></a> 
        <h1 class="text-center text-white pt-5"></h1>
        <div class="container">
            
           <div id="newcard-row" class="row justify-content-center align-items-center">
                <div id="newcard-column" class="col-md-6">
                    <div id="newcard-box" class="col-md-12">
                        <form:form name="newcard-form" id="newcard-form" class="form" action="${pageContext.request.contextPath}/cards/newcard" method="post">
                            <h3 class="text-center text-info">Add Card</h3>
                            <div class="form-group">
                                <label for="cardHoldername" class="text-info">Cardholder Name:</label><br>
                                <input type="text" name="cardHoldername" id="cardHoldername" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="cardNumber" class="text-info">Card Number:</label><br>
                                <input type="text" name="cardNumber" id="cardNumber" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="expDate" class="text-info">Exp Date:</label><br>
                                <input type="text" name="expDate" id="expDate" class="form-control" placeholder="MM/YY">
                            </div>
                            <div class="form-group">
                                <input type="hidden" id="username" name="username" value="${message}">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Create" id="createBtn">                                         
                            </div>                             
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>