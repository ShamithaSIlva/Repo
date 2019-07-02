<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<style type="text/css">
  span.error{
    color: red;
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
		        	 if(res.validated)
		        	 {
		                $(".error").remove();
		             }
		        	 else
		             {
		        	   $(".error").remove();
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
        <h3 class="text-center text-white pt-5">New Card Form</h3>
        <div class="container">
           <h3 class="text-center text-info">Welcome ${message}</h3>
           <div id="newcard-row" class="row justify-content-center align-items-center">
                <div id="newcard-column" class="col-md-6">
                    <div id="newcard-box" class="col-md-12">
                        <form name="newcard-form" id="newcard-form" class="form" action="${pageContext.request.contextPath}/cards/newcard" method="post">
                            <h3 class="text-center text-info">New Card</h3>
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
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Create" >                                         
                            </div>                             
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>