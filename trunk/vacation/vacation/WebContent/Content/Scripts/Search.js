define(['text!html/flightSearch.html', 'text!html/flightSearchResults.html', 'text!html/hotelSearchResults.html', 'css!styles/search.css', 'js/navigation', 'js/authentication', 'js/serverWrapper', 'js/newOrder', 'knockoutjs'], function (searchTemplate, searchResultsTemplate, hotelResultsTemplate, _style, navigation, authentication, serverWrapper, newOrder, ko) {
    return new function () {
        var container = this;
        var allDestinations = serverWrapper.getAllDestinations();
        var lastSearch = new searchViewModel();
        var _departFlight;
        var _returnFlight;
        var _hotel;
        var _nightsCount;
        

        function searchViewModel() {
            var self = this;

            self.source = ko.observable();
            self.destination = ko.observable();
            self.departure = ko.observable();
            self.maxCost = ko.observable();
            self.failed = ko.observable(false);
            self.destinations = ko.observableArray();

            $.when(allDestinations).then(function (desinations) {
                if (!desinations) return;
                for (var i = 0; i < desinations.length; ++i) {
                    self.destinations.push(desinations[i]);
                }
            });

            self.search = function () {
                var onFailure = function () {
                    self.failed(true);
                };

                /*
                var d = undefined;
                var s = undefined;
                if(self.source() != undefined) 
                { 
                	d = self.source()._id;
                }
                if (self.destination() != undefined)
                {
                	s = self.destination()._id;
                }*/
                	
                
                serverWrapper
                    .search(self.source(), self.destination(), self.departure(), self.maxCost())
                    .success(function (result) {
                        if (!result || result.length == 0) return onFailure();
                        navigation.load('flightSearchResults', searchResultsTemplate, new flightResultsViewModel(result));
                    })
                    .error(onFailure);
            };
        };

        function flightResultsViewModel(results, isReturnFlight) {
        	
        	this.results = ko.observableArray();
            for (var i = 0; i < results.length; ++i) {
                var itemModel = new flightResultViewModel(results[i], isReturnFlight);
                itemModel.emptyFlight.add(function (model) {
                    var index = this.results().indexOf(model);
                    this.results.splice(index, 1);
                });

                this.results.push(itemModel);
            }
            
        	if (isReturnFlight)
        	{
        		self.title = ko.observable('Choose return flight:');
        		
				self.nextStep = function (){
				                	
					if (!authentication.isLoggedIn) 
					{
						authentication.authenticate(self.nextStep);
	                } 
					else 
	                {
						self.departFlight = ko.observable(_departFlight);
						self.returnFlight = ko.observable(_returnFlight);
						self.hotel = ko.observable(_hotel);
						self.nightsCount = ko.observable(_nightsCount);
	                    newOrder.Order(self);
	                }
	            };
        	}
        	else
        	{
        		self.title = ko.observable('Choose departure flight:');
        		
        		self.nextStep = function (){
                	
                	var onHotelsFailure = function () {
                        //self.hotelsFailed(true);
                    };
                	
                	serverWrapper
                		.hotelsByFlight(_departFlight.flightId())
	                    .success(function (result) {
	                        if (!result || result.length == 0) return onHotelsFailure();
	                        navigation.load('hotelSearchResults', hotelResultsTemplate, new hotelResultsViewModel(result));
	                    })
	                    .error(onHotelsFailure);
                };
        	}
        };
        
        function flightResultViewModel(result, isReturnFlight) {
            var self = this;

            self.emptyFlight = $.Callbacks();
            self.flightId = ko.observable(result._id);
            self.airline = ko.observable(result._name);
            self.departAirport = ko.observable(result._depart_name);
            self.arrivalAirport = ko.observable(result._arrival_name);
            self.departDate = ko.observable(result._departure_time);
            self.departString = ko.observable(result._long_format_time);
            self.cost = ko.observable(result._cost);
            
            if (isReturnFlight)
            {
            	self.isReturnFlight = ko.observable(isReturnFlight);
            }
        };
        
        self.flightClick = function (data, event) {
        	if (data.isReturnFlight){
        		_returnFlight = data;
        	}
        	else{
        		_departFlight = data;
        	}
        	
            $('.searchOption').removeClass('selectedSearchItem');
        	$(event.currentTarget).addClass('selectedSearchItem');
        	$('#nextStep:hidden').fadeIn();
        };
        
        function hotelResultsViewModel(results) {

            this.results = ko.observableArray();
            for (var i = 0; i < results.length; ++i) {
                var itemModel = new hotelResultViewModel(results[i]);
                itemModel.emptyHotel.add(function (model) {
                    var index = this.results().indexOf(model);
                    this.results.splice(index, 1);
                });

                this.results.push(itemModel);
            }
            
            self.continueToFlight = function (){
            	
            	var onFlightsFailure = function () {
                    //self.hotelsFailed(true);
                };
            	
            	serverWrapper
	                .returnFlights(_departFlight.flightId(), _nightsCount)
	                .success(function (result) {
	                    if (!result || result.length == 0) return onFlightsFailure();
	                    navigation.load('flightSearchResults', searchResultsTemplate, new flightResultsViewModel(result, true));
	                })
	                .error(onFlightsFailure);
            };
        };

        function hotelResultViewModel(result) {
            var self = this;

            self.emptyHotel = $.Callbacks();
            self.hotelId = ko.observable(result._id);
            self.name = ko.observable(result._name);
            self.cityName = ko.observable(result._city_name);
            self.address = ko.observable(result._address);
            self.phone = ko.observable(result._phone);
            self.cost = ko.observable(result._cost);
            self.isExpanded = ko.observable(false);
            
            self.nightsValues = ko.observableArray();

            for (var i = 2; i <= 14; ++i) {
                self.nightsValues.push(i);
            }
        };
        
        self.hotelClick = function (data, event) {
        	var currentSelect = $(event.currentTarget).find('select');
        	_nightsCount = currentSelect.val();
        	_hotel = data;
        	
            $('.searchOption').removeClass('selectedSearchItem');
        	$(event.currentTarget).addClass('selectedSearchItem');
        	
        	$('.nightsNum').attr('disabled', 'disabled');
        	currentSelect.removeAttr('disabled');
        	currentSelect.change(function() {
        		_nightsCount = $(this).val();
        	});
        	
        	$('#continueToFlight:hidden').fadeIn();
        };
        

        container.bind = function () {
            navigation.load('flightSearch', searchTemplate, lastSearch);
        };
    };
});