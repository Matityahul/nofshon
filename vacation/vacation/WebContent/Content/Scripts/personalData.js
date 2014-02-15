define(['text!html/personalData.html', 'css!styles/personalData.css', 'js/navigation', 'js/authentication', 'js/orders', 'js/serverWrapper', 'knockoutjs'], function (template, _style, navigation, authentication, ordersContainer, serverWrapper, ko) {
    return new function () {
        var container = this;

        function viewModel(personalData) {
            var self = this;
        	
        	self.userName = ko.observable(personalData._userName);
            self.name = ko.observable(personalData._firstName + ' ' + personalData._lastName);
            self.email = ko.observable(personalData._email);
            self.address = ko.observable(personalData._address);
            self.phone = ko.observable(personalData._phone);
            self.regDate = ko.observable(personalData._reg_date);
            
            self.failed = ko.observable(false);

            self.ordersClick = function () {
                var onFailure = function () {
                    self.failed(true);
                };

                serverWrapper
                    .userOrders(authentication.getUserID())
                    .success(function (result) {
                        if (result) {
                            ordersContainer.bind(result);
                        }
                        else {
                            onFailure();
                        }
                    })
                    .error(onFailure);
            };
            
            self.logout = function () {
            	authentication.logout();
            };
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