define(['text!html/about.html', 'css!styles/about.css', 'js/navigation'], function (template, _style, navigation) {
    return new function () {
        var container = this;

        container.bind = function () {
            navigation.load('about', template);
        };
    };
});