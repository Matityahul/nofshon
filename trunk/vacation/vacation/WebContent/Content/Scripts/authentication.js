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

            self.loginFailed = ko.observable(false);
            self.signupFailed = ko.observable(false);
            
            self.l_userName = ko.observable('Lior1989');
            self.s_userName = ko.observable('');
            self.l_password = ko.observable('123456');
            self.s_password = ko.observable('');
            self.firstName = ko.observable('');
            self.lastName = ko.observable('');
            self.email = ko.observable('');
            self.address = ko.observable('');
            self.phone = ko.observable('');
            
            self.submitLoginClick = function () {
                serverWrapper
                	.authenticate(self.l_userName(), self.l_password())
                	.success(self.onLoginSuccess)
                	.error(self.onLoginFailure);
            };
            
            self.submitSignupClick = function () {
                serverWrapper
                	.register(self.s_userName(), self.s_password(), self.firstName(), self.lastName(), self.email(), self.address(), self.phone())
                	.success(self.onSignupSuccess)
                	.error(self.onSignupFailure);
            };

            self.onLoginSuccess = function (result) {
                if (result && result.status == 1) {
                	container.isLoggedIn = true;
                	container.user = result.data;
                	$('#connectedUser span').text('Hello ' + container.user._userName);
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
                	$('#connectedUser span').text('Hello ' + container.user._userName);
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