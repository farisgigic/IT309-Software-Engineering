// var Constants = {
//     API_BASE_URL: "http://localhost/SE/IT309-Software-Engineering/backend/",
// };

var Constants = {
    get_api_base_url: function () {
        if (location.hostname == "localhost") {
            return "http://localhost/SE/IT309-Software-Engineering/backend/";
        } else {
            return "https://se-backend-t36mu.ondigitalocean.app/";
        }
    },
};