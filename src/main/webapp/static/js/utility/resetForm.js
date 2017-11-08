$(function () {
	$('#addeditmodal').on('hidden.bs.modal', function () {
		console.log("addedit");
    	angular.element($('#controller')).scope().resetFromOutside();
    	angular.element($('#controller')).scope().$apply();
    });
});
$(function () {
		$('#viewmodal').on('hidden.bs.modal', function () {
			console.log("viewmodal");
	    	angular.element($('#controller')).scope().resetFromOutside();
	    	angular.element($('#controller')).scope().$apply();
	    });
});