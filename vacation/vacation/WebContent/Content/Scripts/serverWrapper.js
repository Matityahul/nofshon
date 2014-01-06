define(function () {
    return new function () {
        var wrapper = this;
        
        wrapper.getLoggedInUser = function () {
            return wrapper.get('/vacation/CookieServlet');
        };

        wrapper.authenticate = function (userName, password) {
            var data = {
            	requestType: "login",
                userName: userName,
                password: password
            };

            return wrapper.post('/vacation/AuthenticateServlet', data);
        };

        wrapper.register = function (userName, password, firstName, lastName, email, address, phone) {
            var data = {
            	requestType: "register",
                userName:  	userName,
                password:  	password,
                firstName: 	firstName, 
                lastName:  	lastName,
                email: 		email,
                address: 	address,
                phone: 		phone
            };

            return wrapper.post('/vacation/AuthenticateServlet', data);
        };
        
        wrapper.getAllPaymentMethods = function() {
        	var data = {
                	requestType: "getPaymentMethods",
                };

                return wrapper.get('/vacation/OrderServlet', data);
        };
        
        wrapper.order = function (order) {
            var data = {
            	requestType: "newOrder",
            	userID: order.userId(),
                departFlightId: order.departFlightId(),
                returnFlightId: order.returnFlightId(),
                nights: order.nights(),
                paymentMethodId: order.selectedMethod(),
                hotelId: order.hotelId(),
                passengers: []
            };

            for (var i = 0; i < order.passengers().length; i++) {
                data.passengers.push({
                    name: order.passengers()[i].name(),
                    passport: order.passengers()[i].passport()
                });
            }

            return wrapper.post('/vacation/OrderServlet', JSON.stringify(data));
        };

        wrapper.userOrders = function (userID) {
            var data = {
            	requestType: "getOrders",
                userId: userID
            };

            return wrapper.get('/vacation/OrderServlet', data);
        };
        
        wrapper.orderBookings = function (orderId) {
            var data = {
            	requestType: "getBookings",
                orderId: orderId
            };

            return wrapper.get('/vacation/OrderServlet', data);
        };

        wrapper.orderFlights = function (orderId) {
            var data = {
            	requestType: "getFlights",
                orderId: orderId
            };

            return wrapper.get('/vacation/OrderServlet', data);
        };
        
        wrapper.orderHotels = function (orderId) {
            var data = {
            	requestType: "getHotels",
                orderId: orderId
            };

            return wrapper.get('/vacation/OrderServlet', data);
        };

        
        
        wrapper.search = function (source, destination, departure, maxCost) {
            var data = {
            	searchtype: "flight",
                start: departure == '' ? undefined : departure,
                fromairport: source,
                toairport: destination,
                topcost: maxCost
            };

            return wrapper.get('/vacation/Search', data);
        };
        
        wrapper.hotelsByFlight = function (flightID) {
            var data = {
            	searchtype:"HotelsByFlight",
            	flightID : flightID
            };

            return wrapper.get('/vacation/Search', data);
        };
        
        wrapper.returnFlights = function (departFlightID, numberOfNights) {
            var data = {
            	searchtype:"ReturnFlights",
            	flightID : departFlightID,
            	nightsNumber:numberOfNights
            };

            return wrapper.get('/vacation/Search', data);
        };
        
        wrapper.hotelSearch = function (name, city, minCost, maxCost) {
            var data = {
            	searchtype: "hotel",
                name: name == '' ? undefined : name,
                city: city,
                bottomcost: minCost,
                topcost: maxCost
            };

            return wrapper.get('/vacation/Search', data);
        };
        
        wrapper.getAllDestinations = function () {
            var data = {
            	info: "AllDestinations",
            };

            return wrapper.get('/vacation/StaticInfoServlet', data);
        };
        
        wrapper.get = function (url, data) {
            return wrapper.ajax(url, data, 'GET');
        };

        wrapper.post = function (url, data) {
            return wrapper.ajax(url, data, 'POST');
        };

        wrapper.ajax = function (url, data, method) {
            return $.ajax({
                type: method,
                data: data,
                url: url,
                cache: false,
                dataType: "json"
            });
        };
    };
});