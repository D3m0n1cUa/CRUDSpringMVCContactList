<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>  
	<title>Contacts</title>  
	
	<!-- My Style CSS -->
	<link  rel="stylesheet" href="<c:url value='/static/css/myStyles.css' />"></link>
	<!-- Bootstrap CSS -->
	<link  rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />"></link>
	
	<!-- jQuery library -->
	<script src="<c:url value='/static/js/jquery.min.js' /> "></script>
	
	<!-- Bootstrap JS -->
	<script src="<c:url value='/static/js/bootstrap.min.js' /> "></script>
	
	<!-- AngularJs library -->
	<script src="<c:url value='/static/js/angular.min.js' />"></script>

	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/contact_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/contact_controller.js' />"></script>
	<script src="<c:url value='/static/js/utility/resetForm.js' /> "></script>
	
</head>
<body ng-app="myApp" class="ng-cloak">
	<div id="controller" class="generic-container" ng-controller="ContactController as ctrl">
			
	
		<!-- CREATE MODAL WINDOW -->
		<div class="modal fade" id="addeditmodal" tabindex="-1" role="dialog"  aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
			
				<!-- CREATE FROM -->
				<form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
					<div class="modal-content">
					
						<!-- HEADER -->
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h2 class="modal-title" >{{!ctrl.contact.id ? 'Create contact' : 'Update'}}</h2>
						</div>
						
						<!-- BODY -->
						<div class="modal-body">
							
							<input id="contactId" type="hidden" ng-model="ctrl.contact.id" />
							
							<!-- FRIST NAME and LAST NAME -->
							<div class="form-goup row">
								<div class="col-xs-5">
									<label for="firstName"><h4>First Name</h4></label>
									<input type="text" ng-model="ctrl.contact.firstName" name="contactFirstName" class="requiredfields form-control" placeholder="First name" required="" ng-minlength="3"/>
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.contactFirstName.$error.required">This is a required field</span>
										<span ng-show="myForm.contactFirstName.$error.minlength">Minimum length required is 3</span>
									</div>
								</div>
								<div class="col-xs-5">
									<label  for="lastName"><h4>Last Name</h4></label>
									<input type="text" ng-model="ctrl.contact.lastName" name="contactLastName" class="requiredfields form-control" placeholder="Last name" required="" ng-minlength="3"/>
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.contactLastName.$error.required">This is a required field</span>
										<span ng-show="myForm.contactLastName.$error.minlength">Minimum length required is 3</span>
									</div>
								</div>
							</div> 
							
							
							<!-- NUMBER MOBILE and EMAIL -->
							<div class="form-group row">
								<div class="col-xs-5">
									<label for="mobileNumber"><h4>Mobile Number</h4></label>
									<input type="text" ng-model="ctrl.contact.mobileNumber" name="mobileNumber" class="requiredfields minlenght form-control" placeholder="Mobile number (must have 9 digits)" required="" ng-pattern="/^\d{9}$/"/>
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.mobileNumber.$error.required">This is a required field</span>
										<span ng-show="myForm.mobileNumber.$error.pattern">Example: 123456789</span>
									</div>
								</div>
								<div class="col-xs-5">
									<label for="email"><h4>Email</h4></label>
									<input type="email" ng-model="ctrl.contact.email" name="email" class="requiredfields form-control" placeholder="Email" required=""/>
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.email.$error.required">This is a required field</span>
										<span ng-show="myForm.email.$error.email">Email is not valid</span>
									</div>
								</div>
							</div>
							
							<hr />
							
							<!-- STREET -->
							<div class="form-group row">
								<div class="col-xs-10">
									<label for="street"><h4>Street</h4></label>
									<input type="text" ng-model="ctrl.contact.address.street" name="street" class="requiredfields form-control" placeholder="Street" required=""/>
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.street.$error.required">This is a required field</span>
									</div>
								</div>
							</div>
							
							
							
							<!-- CITY and ZIP-CODE -->
							<div class="form-group row">
								<div class="col-xs-6">
									<label for="street"><h4>City</h4></label>
									<input type="text" name="city" ng-model="ctrl.contact.address.city" id="city" class="requiredfields form-control" placeholder="City" required=""/>
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.city.$error.required">This is a required field</span>
									</div>
								</div>
								<div class="col-xs-4">
									<label for="street"><h4>Zip Code</h4></label>
									<input type="text" ng-model="ctrl.contact.address.zipCode" name="zipCode" class="requiredfields form-control" placeholder="Zip Code" required="" ng-pattern="/^\d{4}-\d{3}$/"/>
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.zipCode.$error.required">This is a required field</span>
										<span ng-show="myForm.zipCode.$error.pattern">Example: 1234-567</span>
									</div>
								</div>
							</div>

						</div>
						
						<!-- FOOTER -->
						<div class="modal-footer">
							<div class="row">
								<div class="form-actions floatRight">
									<input type="submit"  value="{{!ctrl.contact.id ? 'Add' : 'Update'}}" class="btn btn-success btn-sm" ng-disabled="myForm.$invalid"/>
									<button type="button" ng-click="ctrl.reset()" ng-show="ctrl.contact.id ? false : true" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
								</div>
							</div> 
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<!-- DEFAULT PANEL CONTENTS -->
		<div class="panel panel-default">
		
			<!-- HEADER -->
			<div class="panel panel-default">
				<div class="panel-heading"><h2>List of Contacts</h2>
					<input type="image" data-toggle="modal" data-target="#addeditmodal" src="<c:url value='/static/resources/add_contact.png'/>" align="right" />
				</div>
			</div>
			
			<!--  BODY -->	
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Mobile Number</th>
							<th>Email</th>
							<th width="20%"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="c in ctrl.contacts">
							
							<td><span ng-bind="c.firstName"></span></td>
							<td><span ng-bind="c.lastName"></span></td>
							<td><span ng-bind="c.mobileNumber"></span></td>
							<td><span ng-bind="c.email"></span></td>
							<td>
								<input type="image" src="<c:url value='/static/resources/view_contact.png'/>" data-toggle="modal" data-target="#viewmodal" ng-click="ctrl.showView(c.id)" /><input type="image" src="<c:url value='/static/resources/edit_contact.png'/>" ng-click="ctrl.edit(c.id)" data-toggle="modal" data-target="#addeditmodal"/><input type="image" src="<c:url value='/static/resources/delete_contact.png'/>" ng-click="ctrl.remove(c.id)" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- ModalView -->
		<div id="viewmodal" class="modal fade" role="dialog" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header" >
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title"><span>Information about:&nbsp;</span><span class="headModal" ng-bind="ctrl.contact.firstName" ></span>&nbsp;<span class="headModal" ng-bind="ctrl.contact.lastName" ></span></h4>
		      </div>
		      <div class="modal-body">
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td>Mobile number:</td>
                        <td><span ng-bind="ctrl.contact.mobileNumber"></span></td>
                      </tr>
                      <tr>
                        <td>Email:</td>
                        <td><span ng-bind="ctrl.contact.email"></span></td>
                      </tr>
                      <tr>
                        <td>Street:</td>
                        <td><span ng-bind="ctrl.contact.address.street"></span></td>
                      </tr>
                      <tr>
                        <td>City:</td>
                        <td><span ng-bind="ctrl.contact.address.city"></span></td>
                      </tr>
                      <tr>
                        <td>Zip code:</td>
                        <td><span ng-bind="ctrl.contact.address.zipCode"></span></td>
                      </tr>
                    </tbody>
                  </table>
		      </div>
		      
		    </div>
		
		  </div>
		</div>
	</div>
	
</body>
</html>