define(['text!html/flightSearch.html', 'text!html/flightSearchResults.html', 'css!styles/search.css', 'js/navigation', 'js/authentication', 'js/serverWrapper', 'knockoutjs'], function (searchTemplate, searchResultsTemplate, _style, navigation, authentication, serverWrapper, ko) {
    return new function () {
        var presenter = this;
        var allDestinations = serverWrapper.getAllDestinations();
        var lastSearch = new searchViewModel();

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

                var d = undefined;
                var s = undefined;
                if(self.source() != undefined) 
                { 
                	d = self.source()._id;
                }
                if (self.destination() != undefined)
                {
                	s = self.destination()._id;
                }
                	
                
                serverWrapper
                    .search(d, s, self.departure(), self.maxCost())
                    .success(function (result) {
                        if (!result || result.length == 0) return onFailure();
                        navigation.load('searchResults', searchResultsTemplate, new resultsViewModel(result));
                    })
                    .error(onFailure);
            };
        };

        function resultsViewModel(results) {

            this.results = ko.observableArray();
            for (var i = 0; i < results.length; ++i) {
                var itemModel = new resultViewModel(results[i]);
                itemModel.emptyFlight.add(function (model) {
                    var index = this.results().indexOf(model);
                    this.results.splice(index, 1);
                });

                this.results.push(itemModel);
            }
            
            self.continueToHotel = function (){
            	$('.selectedFlight input')
            };
        };

        function resultViewModel(result) {
            var self = this;

            self.emptyFlight = $.Callbacks();
            self.flightId = ko.observable(result._id);
            self.airline = ko.observable(result._name);
            self.departAirport = ko.observable(result._depart_name);
            self.arrivalAirport = ko.observable(result._arrival_name);
            self.departDate = ko.observable(result._departure_time);
            self.cost = ko.observable(result._cost);
            self.isExpanded = ko.observable(false);
        };
        
        self.flightClick = function () {
            self.isExpanded(!self.isExpanded());
        };

        presenter.bind = function () {
            navigation.load('flightSearch', searchTemplate, lastSearch);
        };
    };
});