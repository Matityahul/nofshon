define(['text!html/home.html', 'js/navigation', 'knockoutjs'], function (template, navigation, ko) {
    return new function () {
        var container = this;
        var searchContainerPromise = curl('js/search');
        var personalDataContainerPromise = curl('js/personalData');
        var aboutContainerPromise = curl('js/about');

        function viewModel() {
            
    		this.search = function () {
                searchContainerPromise.then(function (searchContainer) {
                    searchContainer.bind();
                });
            };

            this.personalData = function () {
            	personalDataContainerPromise.then(function (personalDataContainer) {
            		personalDataContainer.bind();
                });
            };

            this.about = function () {
                aboutContainerPromise.then(function (aboutContainer) {
                    aboutContainer.bind();
                });
            };
        }

        container.bind = function () {
            navigation.init(template);
            ko.applyBindings(new viewModel(), $('#menu').get(0));
            $('body').fadeIn();
        };
    };
});