var CarService = {
    reload_cars_datatable: function () {
        Utils.get_datatable(
            "tbl_cars", Constants.API_BASE_URL + "cars/",
            [
                { data: "number" },
                { data: "manufacturer" },
                { data: "model" },
                { data: "year" },
                { data: "mileage" },
                { data: "engine" },
                {
                    data: "actions",
                    orderable: false,
                    searchable: false,
                    render: function (data, type, row) {
                        return data;
                    }
                }
                // { data: "actions" },

            ], null, function () {
                console.log("Datatable works");

            }
        );
    },
    delete_car: function (car_id) {
        if (
            confirm(
                "Do you want to delete car?"
            ) == true
        ) {
            RestClient.delete(
                "cars/delete_car/" + car_id,
                {},
                function (data) {
                    CarService.reload_cars_datatable();
                }
            );
        }
    },
    open_edit_car_modal: function (car_id) {
        RestClient.get("cars/car/" + car_id, function (data) {
            $("#edit-car-modal").modal("toggle");
            $("#edit-car-form input[name='id']").val(data.id);
            $("#edit-car-form input[name='manufacturer']").val(data.manufacturer);
            $("#edit-car-form input[name='model']").val(data.model);
            $("#edit-car-form input[name='year']").val(data.year);
            $("#edit-car-form input[name='mileage']").val(data.mileage);
            $("#edit-car-form input[name='engine']").val(data.engine);
        });
    }
}