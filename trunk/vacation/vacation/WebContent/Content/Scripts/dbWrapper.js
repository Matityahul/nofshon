define(function () {
    return new function () {
        var wrapper = this;

        var databaseOptions = {
            fileName: "sqlite_nofshon",
            version: "1.0",
            displayName: "Nofshon",
            maxSize: 1024
        };

        var database = openDatabase(
			databaseOptions.fileName,
			databaseOptions.version,
			databaseOptions.displayName,
			databaseOptions.maxSize
		);

        var getToday = function () {
            var today = new Date();
            var day = today.getDate();
            var month = today.getMonth() + 1;
            var year = today.getFullYear();
            var hours = today.getHours();
            var minutes = today.getMinutes();
            var seconds = today.getSeconds();

            if (day < 10) day = '0' + day;
            if (month < 10) month = '0' + month;
            if (hours < 10) hours = '0' + hours;
            if (minutes < 10) minutes = '0' + minutes;
            if (seconds < 10) seconds = '0' + seconds;

            today = year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;
            return today;
        };

        database.transaction(
			function (transaction) {
			    transaction.executeSql(
					"CREATE TABLE IF NOT EXISTS searches (" +
						"id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "creationTime DATETIME," +
                        "source TEXT," +
                        "destination TEXT," +
                        "departure TEXT," +
                        "maxCost INTEGER" +
					");"
				);
			}
		);

        wrapper.saveSearch = function (source, destination, departure, maxCost) {

            var today = getToday();
            database.transaction(
				function (transaction) {
				    transaction.executeSql(
                        "DELETE FROM searches WHERE source = ? AND destination = ? AND departure = ? AND maxCost = ?;",
						[source, destination, departure, maxCost],
						function (transaction, results) {
						    transaction.executeSql(
						        "INSERT INTO searches (creationTime,source,destination,departure,maxCost) VALUES (?,?,?,?,?);",
						        [today, source, destination, departure, maxCost],
						        function (transaction, results) { });
						});
				}
			);
        };

        wrapper.getSearches = function (callback) {
            database.transaction(
				function (transaction) {
				    transaction.executeSql(
						"SELECT * FROM searches",
						[],
						function (transaction, results) {
						    var arr = [];

						    for (var i = 0; i < results.rows.length; ++i) {
						        var row = results.rows.item(i);
						        arr.push(row);
						    };

						    callback(arr);
						}
					);
				}
			);
        };
    };
});