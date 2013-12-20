define(['text!html/authentication.html', 'css!styles/authentication.css', 'js/navigation', 'js/serverWrapper', 'knockoutjs'], function (template, _style, navigation, serverWrapper, ko) {
    return new function () {
        var container = this;

        container.user = null;
        container.isLoggedIn = false;

        container.getUserID = function () {
            return container.user._id;
        };

        function viewModel(callback) {
            var self = this;

            self.failed = ko.observable(false);
            
            self.l_userName = '';
            self.s_userName = '';
            self.l_password = '';
            self.s_password = '';
            self.firstName = '';
            self.lastName = '';
            self.email = '';
            self.address = '';
            self.phone = '';
            
            self.submitLoginClick = function () {
                serverWrapper
                	.authenticate(self.l_userName, self.l_password)
                	.success(self.onLoginSuccess)
                	.error(self.onLoginFailure);
            };
            
            self.submitSignupClick = function () {
                serverWrapper
                	.register(self.s_userName, self.s_password, self.firstName, self.lastName, self.email, self.address, self.phone)
                	.success(self.onSuccess)
                	.error(self.onFailure);
            };

            self.onLoginSuccess = function (result) {
                if (result && result.status == 1) {
                	container.isLoggedIn = true;
                	container.user = result.data;
                    callback();
                }
                else {
                    self.onLoginFailure();
                }
            };

            self.onLoginFailure = function () {
                self.loginFailed(true);
            };
            
            self.onSignupSuccess = function (result) {
                if (result && result.status == 1) {
                	container.isLoggedIn = true;
                	container.user = result.data;
                    callback();
                }
                else {
                    self.onSignupFailure();
                }
            };

            self.onSignupFailure = function () {
                self.signupFailed(true);
            };
        }

        container.authenticate = function (callback) {
            navigation.load('authentication', template, new viewModel(callback));
        };
    };
});