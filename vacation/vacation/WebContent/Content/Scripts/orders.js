define(['text!html/orders.html', 'css!styles/orders.css', 'js/authentication', 'js/navigation', 'js/serverWrapper', 'knockoutjs'], function (template, _style, authentication, navigation, serverWrapper, ko) {
    return new function () {
        var container = this;

        function orderViewModel(order) {
            var self = this;
            
            self.id = ko.observable(order._id);
            self.date = ko.observable(order._order_time);
            self.expanded = ko.observable(false);
            self.bookings = ko.observableArray();
            self.flights = ko.observableArray();
            self.hotels = ko.observableArray();

            var hotelsDeffered = $.Deferred();
            var flightsDeffered = $.Deferred();
            
            serverWrapper
	            .orderHotels(self.id())
	            .success(function (result) {
	                if (!result) return;
	                for (var i = 0; i < result.length; ++i) {
	                    var hotel = {
	                        id: ko.observable(result[i]._id),
	                        hotelName: ko.observable(result[i]._name),
	                        hotelCost: ko.observable(result[i]._cost)
	                    };
	
	                    self.hotels.push(hotel);
	                }
	
	                hotelsDeffered.resolve();
	            });
            
            serverWrapper
	            .orderFlights(self.id())
	            .success(function (result) {
	                if (!result) return;
	                for (var i = 0; i < result.length; ++i) {
	                    var flight = {
	                        id: ko.observable(result[i]._id),
	                        flightTime: ko.observable(result[i]._departure_time),
	                        flightTimeString: ko.observable(result[i]._long_format_time),
	                        flightCost: ko.observable(result[i]._cost)
	                    };
	
	                    self.flights.push(flight);
	                }
	
	                flightsDeffered.resolve();
	            });
            
            serverWrapper
                .orderBookings(self.id())
                .success(function (result) {
                    if (!result) return;
                    $.when(flightsDeffered.promise(), hotelsDeffered.promise()).then(function () {
                        for (var i = 0; i < result.length; ++i) {
                            var booking = {
                                id: ko.observable(result[i]._id),
                                passport: ko.observable(result[i]._passport_id),
                                name: ko.observable(result[i]._name),
                                nights: ko.observable(result[i]._number_of_nights),
                                departFlight: ko.observable(),
                                departString: ko.observable(),
                                returnFlight: ko.observable(),
                                hotel: ko.observable(),
                                totalCost: ko.observable()
                            };
                            
                            for (var j = 0; j < self.flights().length; ++j) 
                            {
                                if (self.flights()[j].id() == result[i]._depart_flight_id) 
                                {
                                    booking.departFlight(self.flights()[j]);
                                    booking.departString(self.flights()[j]._long_format_time);
                                }
                                else if (self.flights()[j].id() == result[i]._return_flight_id)
                                {
                                	booking.returnFlight(self.flights()[j]);
                                }
                            }
                            
                            for (var j = 0; j < self.hotels().length; ++j) 
                            {
                                if (self.hotels()[j].id() == result[i]._hotel_id) 
                                {
                                    booking.hotel(self.hotels()[j]);
                                }
                            }
                            
                            var totalCost =  booking.departFlight().flightCost() + booking.returnFlight().flightCost() + 
                            				booking.hotel().hotelCost() * booking.nights();
                            
                            booking.totalCost(totalCost);
                            
                            self.bookings.push(booking);
                        }
                    });
                });

            self.orderClick = function () {
                self.expanded(!self.expanded());
            };
        };

        function ordersViewModel(orders) {

            this.orders = ko.observableArray();

            for (var i = 0; i < orders.length; ++i) {
                this.orders.push(new orderViewModel(orders[i]));
            }
        };
        container.bind = function (orders) {
            navigation.load('orders', template, new ordersViewModel(orders));
        };
    };
});