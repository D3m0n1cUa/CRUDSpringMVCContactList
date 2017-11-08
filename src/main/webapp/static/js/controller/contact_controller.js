'use strict';
 
angular.module('myApp').controller('ContactController', ['$scope', 'ContactService', function($scope, ContactService) {
    var self = this;
    self.contact={id:null,firstName:'',lastName:'', mobileNumber:'', email:'', address:{id:null, street:'', city:'', zipCode:''}};
    self.contacts=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.showView = showView;
 
 
    getAllContacts();
 
    function getAllContacts(){
    	ContactService.getAllContacts()
            .then(
            function(d) {
                self.contacts = d;
            },
            function(errResponse){
                console.error('Error while getting contacts');
            }
        );
    }
 
    function createContact(contact){
    	ContactService.createContact(contact)
            .then(
            getAllContacts,
            function(errResponse){
                console.error('Error while creating contact');
            }
        );
    }
 
    function updateContact(contact, id){
    	ContactService.updateContact(contact, id)
            .then(
            getAllContacts,
            function(errResponse){
                console.error('Error while updating contact');
            }
        );
    }
 
    function deleteContact(id){
    	ContactService.deleteContact(id)
            .then(
            getAllContacts,
            function(errResponse){
                console.error('Error while deleting contact');
            }
        );
    }
 
    function submit() {
        if(self.contact.id===null){
            console.log('Saving New Contact', self.contact);
            createContact(self.contact);
        }else{
            updateContact(self.contact, self.contact.id);
            console.log('Contact updated with id ', self.contact.id);
        }
        hideModalWindow();
        reset();
    }
 
    function edit(id){
        for(var i = 0; i < self.contacts.length; i++){
            if(self.contacts[i].id === id) {
                self.contact = angular.copy(self.contacts[i]);
                console.log("Edit contact with id", self.contact.id)
                break;
            }
        }
    }
    
    function showView(id){
        for(var i = 0; i < self.contacts.length; i++){
            if(self.contacts[i].id === id) {
                self.contact = angular.copy(self.contacts[i]);
                console.log("Show full contact information with id", self.contact.id)
                break;
            }
        }
    }
 
    function remove(id){
        console.log('Contact with id is deleted', id);
        if(self.contact.id === id) {	//clean form if the contact was deleted is shown there.
            reset();
        }
        deleteContact(id);
    }
 
    // function for hiding Modal Windows
    function hideModalWindow() {
    	$('#addeditmodal').modal('toggle');
    }
 
    // function for reset Form
    function reset(){
        self.contact={id:null,firstName:'',lastName:'', mobileNumber:'', email:'', address:{id:null, street:'', city:'', zipCode:''}};
        $scope.myForm.$setPristine(); 
    }
    
    //function for reset Form from jQuery
    $scope.resetFromOutside = function() {
    	self.contact={id:null,firstName:'',lastName:'', mobileNumber:'', email:'', address:{id:null, street:'', city:'', zipCode:''}};
        $scope.myForm.$setPristine(); 
    }
 
}]);