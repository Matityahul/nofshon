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
            
            self.userName = '';
            self.password = '';
            
            self.submitLoginClick = function () {
                serverWrapper
                	.authenticate(self.userName, self.password)
                	.success(self.onSuccess)
                	.error(self.onFailure);
            };
            
            self.submitSignupClick = function () {
                serverWrapper
                	.authenticate(self.userName, self.password)
                	.success(self.onSuccess)
                	.error(self.onFailure);
            };

            self.onSuccess = function (result) {
                if (result && result.status == 1) {
                	container.isLoggedIn = true;
                	container.user = result.data;
                    callback();
                }
                else {
                    self.onFailure();
                }
            };

            self.onFailure = function () {
                self.failed(true);
            };
        }

        container.authenticate = function (callback) {
            navigation.load('authentication', template, new viewModel(callback));
        };
    };
});