'use strict';
 
angular.module('myApp').factory('ContactService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://95.85.57.79:8080/CRUDContactList/contact/';
 
    var factory = {
        getAllContacts: getAllContacts,
        createContact: createContact,
        updateContact: updateContact,
        deleteContact: deleteContact
    };
 
    return factory;
 
    function getAllContacts() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while getting contacts');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createContact(contact) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, contact)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating contact');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateContact(contact, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, contact)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating contact');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteContact(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting contact');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);