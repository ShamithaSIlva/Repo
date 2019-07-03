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
	  $('#resultsTableDiv').hide();
	  $('#searchcard-form').submit(function(event) {
		  	$.ajax({
		        url: $("#searchcard-form").attr( "action"),
		        data: $('form[name=searchcard-form]').serialize(),
		        type: "POST",
		         
		        success: function(res) {
		        	 if(res.validated)
		        	 {
		                $(".error").remove();
		                $("#resultsTable").find("tr:gt(0)").remove();
		                var trHTML = '';
		                $.each(res.searchedCards, function (i, item) {
		                    trHTML += '<tr><td>' + (i+1) + '</td><td>' + item.cardHoldername + '</td><td>' + item.cardNumber +'</td><td>'
		                    +'<form name="row_'+i+'" id="row_'+i+'" action="${pageContext.request.contextPath}/cards/updateCard" method="post"><input name="expDate" type="text" value="'+ item.expDate +'"/>'
		                    +'<input type="hidden"  name="cardHoldername" value="'+ item.cardHoldername +'">'
		                    +'<input type="hidden"  name="cardNumber" value="'+ item.cardNumber +'">'		                    
		                    +'<input type="button" name="submit_'+i+'" id="submit_'+i+'"class="btn btn-info btn-md" value="Update">'
		                    +'</form></td></tr>';		                    
		                });
		                $('#resultsTable').append(trHTML);
		                $('#resultsTableDiv').show();	
		                
		                $.each(res.searchedCards, function (i, item) {
		                	var individualBidForm = $('#row_'+i);
		                	individualBidForm.submit(function(event) {
		                    	$.ajax({
		            		        url: individualBidForm.attr( "action"),
		            		        data: individualBidForm.serialize(),
		            		        type: "POST",
		            		         
		            		        success: function(res) {
		            		        	if(res.validated)
		           		        	    {
		            		        		alert('Card updated successfully');
		           		        	    }
		            		        	else
		            		        	{
		            		        		$.each(res.errorMessages,function(key,value){			            	
		            		        			alert(value);
		            				               });
		            		        	}
		            		        }});
		                    	event.preventDefault();
		                       });
		                	$( "#submit_"+i).click(function() {
						    	individualBidForm.submit();
						    });
		                });   
		                
		                	               
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
        <h3 class="text-info panel"></h3>
        <a href="<c:url value='/logout' />"><span class="panel">Logout</span></a> 
        <a href="<c:url value='/customer' />"><span class="panel">Add Card</span></a> 
        <h1 class="text-center text-white pt-5"></h1>
        <div class="container">
            
           <div id="searchcard-row" class="row justify-content-center align-items-center">
                <div id="searchcard-column" class="col-md-6">
                    <div id="searchcard-box" class="col-md-12">
                        <form:form name="searchcard-form" id="searchcard-form" class="form" action="${pageContext.request.contextPath}/cards/searchcard" method="post">
                            <h3 class="text-center text-info">Search Cards</h3>                            
                            <div class="form-group">
                                <label for="cardNumber" class="text-info">Card Number:</label><br>
                                <input type="text" name="cardNumber" id="cardNumber" class="form-control">
                            </div>                            
                            <div class="form-group">
                                <input type="hidden" id="username" name="username" value="${message}">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Search" >                                         
                            </div>                             
                        </form:form>
                    </div>
                </div>
            </div>
            <div id="resultsTableDiv" class="row justify-content-center align-items-center">
            	<table class="table" id="resultsTable">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">Card Holder Name</th>
				      <th scope="col">Card Number</th>
				      <th scope="col">Exp Date</th>			
				    </tr>
				  </thead>
				  <tbody>
				    
				  </tbody>
				</table>
            </div>
        </div>
    </div>
</body>

</html>