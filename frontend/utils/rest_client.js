var RestClient = {
    get: function (url, callback, error_callback) {
        $.ajax({
            url: Constants.API_BASE_URL + url,
            type: "GET",
            beforeSend: function (xhr) {
                const user = Utils.get_from_localstorage("user");
                if (user && user.token) {
                    xhr.setRequestHeader("Authentication", user.token);
                }
            },
            success: function (response) {
                if (callback) callback(response);
            },
            error: function (jqXHR) {
                if (error_callback) error_callback(jqXHR);
            },
        });
    },
    request: function (url, method, data, callback, error_callback) {
        $.ajax({
            url: Constants.API_BASE_URL + url,
            type: method,
            data: data,
            beforeSend: function (xhr) {
                const user = Utils.get_from_localstorage("user");
                if (user && user.token) {
                    xhr.setRequestHeader("Authentication", user.token);
                }
            },
        })
            .done(function (response) {
                if (callback) callback(response);
            })
            .fail(function (jqXHR) {
                if (error_callback) {
                    error_callback(jqXHR);
                } else {
                    toastr.error(jqXHR.responseJSON?.message || "An error occurred");
                }
            });
    },
    post: function (url, data, callback, error_callback) {
        RestClient.request(url, "POST", data, callback, error_callback);
    },
    delete: function (url, data, callback, error_callback) {
        RestClient.request(url, "DELETE", data, callback, error_callback);
    },
    put: function (url, data, callback, error_callback) {
        RestClient.request(url, "PUT", data, callback, error_callback);
    },
};
