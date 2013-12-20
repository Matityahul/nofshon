define(['text!html/personalData.html', 'css!styles/personalData.css', 'js/navigation', 'js/authentication', /*'js/bookingsPresenter', 'js/serverWrapper',*/ 'knockoutjs'], function (template, _style, navigation, authentication, /*bookingsPresenter, serverWrapper,*/ ko) {
    return new function () {
        var container = this;

        function viewModel(personalData) {
            var self = this;

            /*serverWrapper
                .userTotalCost(authentication.getUserID())
                .success(function (result) {
                    self.totalCost(result.total_cost);
                });*/

            self.name = ko.observable(personalData.first_name + ' ' + personalData.last_name);
            self.email = ko.observable(personalData.email_address);
            self.registrationDate = ko.observable(personalData.registration_date);
            self.address = ko.observable(personalData.address);
            self.totalCost = ko.observable(0);
            self.failed = ko.observable(false);

            /*self.bookingsClick = function () {
                var onFailure = function () {
                    self.failed(true);
                };

                serverWrapper
                    .userBookings(authentication.getUserID())
                    .success(function (result) {
                        if (result) {
                            bookingsPresenter.bind(result);
                        }
                        else {
                            onFailure();
                        }
                    })
                    .error(onFailure);
            };*/
        }

        container.bind = function () {
            if (!authentication.isLoggedIn) {
                authentication.authenticate(container.bind);
            } else {
                navigation.load('personalData', template, new viewModel(authentication.user));
            }
        };
    };
});