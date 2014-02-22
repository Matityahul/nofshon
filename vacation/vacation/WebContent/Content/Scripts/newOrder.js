define(['text!html/newOrder.html', 'css!styles/orders.css', 'js/authentication', 'js/navigation', 'js/serverWrapper', 'knockoutjs'], function (template, _style, authentication, navigation, serverWrapper, ko) {
    return new function () {
        var container = this;

        function newOrderViewModel(order, callback) {
            var self = this;

            var priceForPassenger = (order.hotel().cost() * order.nightsCount()) + order.departFlight().cost() + order.returnFlight().cost(); 
            
            self.departAirline = ko.observable(order.departFlight().airline());
            self.firstDepartAirport = ko.observable(order.departFlight().departAirport());
            self.firstArrivalAirport = ko.observable(order.departFlight().arrivalAirport());
            self.departDate = ko.observable(order.departFlight().departDate());
            self.hotelName = ko.observable(order.hotel().name());
            self.nights = ko.observable(order.nightsCount());
            self.returnAirline = ko.observable(order.returnFlight().airline());
            self.secondDepartAirport = ko.observable(order.returnFlight().departAirport());
            self.secondArrivalAirport = ko.observable(order.returnFlight().arrivalAirport());
            self.returnDate = ko.observable(order.returnFlight().departDate());
            self.selectedMethod = ko.observable();
            
            self.totalPrice = ko.observable(priceForPassenger);
            
            self.paymentMethods = ko.observableArray();
            
            var allPaymentMethods = serverWrapper.getAllPaymentMethods();
            
            $.when(allPaymentMethods).then(function (paymentMethods) {
                if (!paymentMethods) return;
                for (var i = 0; i < paymentMethods.length; ++i) {
                    self.paymentMethods.push(paymentMethods[i]);
                }
            });

            self.passengers = ko.observableArray();
            self.passengers.push(new passengerViewModel());
            
            self.addPassenger = function () {
            	self.passengers.push(new passengerViewModel());
            	
            	self.totalPrice(self.passengers().length * priceForPassenger);
            };
            
            self.removePassenger = function () {
            	if (self.passengers().length > 1)
            	{
            		self.passengers.pop();
    			}
            	
            	self.totalPrice(self.passengers().length * priceForPassenger);
            };
            
            self.userId = ko.observable(authentication.getUserID());
            self.departFlightId = ko.observable(order.departFlight().flightId());
            self.returnFlightId = ko.observable(order.returnFlight().flightId());
            self.hotelId = ko.observable(order.hotel().hotelId());
            
            self.orderFailed = ko.observable(false);
            
            self.orderClick = function () {
            	serverWrapper
	                .order(self).success(function () {
	                    alert('Your order submited sucessfully!');
	                    location.reload();
	                }).error(function () {
	                    self.orderFailed(true);
	                });
	                
            };
            
            self.back = function (){
    			if (callback) {
    				callback();
    			}
            };
        };
        
        function passengerViewModel() {
            this.name = ko.observable();
            this.passport = ko.observable();
        };
        
        container.Order = function (order, callback) {
            navigation.load('newOrder', template, new newOrderViewModel(order, callback));
        };
    };
});