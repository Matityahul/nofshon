define(['knockoutjs'], function (ko) {
    return new function () {
        var manager = this;
        var container = $('#content');
        var currentPage = null;

        manager.init = function (template) {
            currentPage = 'home';
            container.html(template);
        };

        manager.load = function (page, template, viewModel) {
            if (currentPage == page) return;
            currentPage = page;
            container.fadeOut(function () {
                container.html(template).fadeIn();
                if (viewModel) {
                    ko.applyBindings(viewModel, $('#' + page).get(0));
                }
            });
        };
    };
});