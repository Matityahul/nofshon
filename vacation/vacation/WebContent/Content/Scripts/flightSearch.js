define(['text!html/search.html', 'text!html/searchResults.html', 'css!styles/search.css', 'js/navigation', 'js/authentication', 'js/serverWrapper', 'knockoutjs'], function (searchTemplate, searchResultsTemplate, _style, navigation, authentication, serverWrapper, ko) {
    return new function () {
        var presenter = this;
        var destinationsPromise = serverWrapper.getAllDestinations();
        var lastSearch = new searchViewModel();

        function searchViewModel() {
            var self = this;

            self.source = ko.observable();
            self.destination = ko.observable();
            self.departure = ko.observable();
            self.maxCost = ko.observable();
            self.failed = ko.observable(false);
            self.destinations = ko.observableArray();

            $.when(destinationsPromise).then(function (desinations) {
                if (!desinations) return;
                for (var i = 0; i < desinations.length; ++i) {
                    self.destinations.push(desinations[i]);
                }
            });

            self.search = function () {
                var onFailure = function () {
                    self.failed(true);
                };

                serverWrapper
                    .search(self.source(), self.destination(), self.departure(), self.maxCost())
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
        };

        function resultViewModel(result) {
            var self = this;

            self.emptyFlight = $.Callbacks();
            self.flightId = ko.observable(result._id);
            self.airline = ko.observable(result._name);
            //self.flightNumber = ko.observable(result.flight_number);
            self.departAirport = ko.observable(result._depart_name);
            self.arrivalAirport = ko.observable(result._arrival_name);
            self.departDate = ko.observable(result._departure_time);
            self.isExpanded = ko.observable(false);
        };
        
        self.flightClick = function () {
            self.isExpanded(!self.isExpanded());
        };

        presenter.bind = function () {
            navigation.load('search', searchTemplate, lastSearch);
        };
    };
});