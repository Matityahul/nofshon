define(['text!html/hotelSearch.html', 'text!html/hotelSearchResults.html', 'css!styles/search.css', 'js/navigation', 'js/authentication', 'js/serverWrapper', 'knockoutjs'], function (searchTemplate, searchResultsTemplate, _style, navigation, authentication, serverWrapper, ko) {
    return new function () {
        var presenter = this;
        var lastSearch = new hotelSearchViewModel();

        function hotelSearchViewModel() {
            var self = this;

            self.name = ko.observable();
            self.city = ko.observable();
            self.minCost = ko.observable();
            self.maxCost = ko.observable();
            self.failed = ko.observable(false);
            self.cities = ko.observableArray();

            self.search = function () {
                var onFailure = function () {
                    self.failed(true);
                };
    
                serverWrapper
                    .hotelSearch(self.name() , 1, self.minCost(), self.maxCost())
                    .success(function (result) {
                        if (!result || result.length == 0) return onFailure();
                        navigation.load('hotelSearchResults', searchResultsTemplate, new resultsViewModel(result));
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

           
            self.emptyHotel = $.Callbacks();
            self.hotelId = ko.observable(result._id);
            self.cityID = ko.observable(result._name);
            self.name = ko.observable(result._depart_name);
            self.address = ko.observable(result._arrival_name);
            self.phone = ko.observable(result._departure_time);
            self.cost = ko.observable(result._cost);
            self.isExpanded = ko.observable(false);
        };
        
        self.flightClick = function () {
        	//navigation.load('search', searchTemplate, lastSearch);
            self.isExpanded(!self.isExpanded());
        };

        presenter.bind = function () {
            navigation.load('hotelSearch', searchTemplate, lastSearch);
        };
    };
});