//TODO : Change the functions for our needs!!
define(function () {
    return new function () {
        var wrapper = this;

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
        
       /* wrapper.userTotalCost = function (userID) {
            var data = {
                uid: userID
            };

            return wrapper.get('/vacation/BookingServlet/getcost', data);
        };

        wrapper.bookFlight = function (booking) {
            var data = {
                flight_id: booking.flightId(),
                user_id: booking.userId(),
                seats_class: booking.seatClass(),
                totalCost: booking.totalPrice(),
                seats_count: booking.desiredSeatsCount(),
                users: []
            };

            for (var i = 0; i < booking.users().length; ++i) {
                data.users.push({
                    first_name: booking.users()[i].firstName(),
                    last_name: booking.users()[i].lastName(),
                    passport_number: booking.users()[i].passport(),
                    credit_card: booking.users()[i].creditCard(),
                    credit_expt: booking.users()[i].creditExpiration()
                });
            }

            return wrapper.post('/vacation/BookingServlet/makebooking', JSON.stringify(data));
        };
        
        */

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


        /*
        
        wrapper.getSeatsForFlight = function (flightId) {
            var data = {
                fid: flightId
            };

            return wrapper.get('/vacation/FlightSeatServlet/get', data);
        };

		*/
        
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