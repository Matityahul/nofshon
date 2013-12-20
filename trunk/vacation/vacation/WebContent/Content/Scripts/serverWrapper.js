//TODO : Change the functions for our needs!!
define(function () {
    return new function () {
        var wrapper = this;

        wrapper.authenticate = function (useName, password) {
            var data = {
                userName: userName,
                password: password
            };

            return wrapper.post('/AuthenticateServlet/auth', data);
        };

        wrapper.userBookings = function (userID) {
            var data = {
                uid: userID
            };

            return wrapper.get('/BookingServlet/getbooking', data);
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

            return wrapper.post('/BookingServlet/makebooking', JSON.stringify(data));
        };

        wrapper.bookingTickets = function (bookingId) {
            var data = {
                bid: bookingId
            };

            return wrapper.get('/BookingServlet/gettickets', data);
        };

        wrapper.bookingSeats = function (bookingId) {
            var data = {
                bid: bookingId
            };

            return wrapper.get('/BookingServlet/getseats', data);
        };

        wrapper.userTotalCost = function (userID) {
            var data = {
                uid: userID
            };

            return wrapper.get('/BookingServlet/getcost', data);
        };

        wrapper.search = function (source, destination, departure, maxCost) {
            var data = {
                start: departure == '' ? undefined : departure,
                depart: source,
                arrive: destination,
                maxcost: maxCost
            };

            return wrapper.get('/FlightServlet/qparams0', data);
        };

        wrapper.getAllDestinations = function () {
            return wrapper.get('/FlightServlet/dest');
        };

        wrapper.getSeatsForFlight = function (flightId) {
            var data = {
                fid: flightId
            };

            return wrapper.get('/FlightSeatServlet/get', data);
        },

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