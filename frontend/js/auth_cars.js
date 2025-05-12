$(document).ready(function () {
    const user = Utils.get_from_localstorage("user");

    // Only target the My Cars view if it's visible
    if (window.location.hash === "#mycars" || $("#tbl_cars").length > 0) {
        if (!user) {
            $("#layoutSidenav_content").html(`
                <div class="container d-flex justify-content-center align-items-center" style="min-height: 70vh;">
                    <div class="card shadow-lg p-4 text-center" style="max-width: 500px; width: 100%; border-radius: 1rem;">
                        <h3 class="mb-3 text-danger">You are not logged in</h3>
                        <p class="mb-4">Please <a href="login/index.html" class="text-decoration-none fw-bold">log in</a> to access this page.</p>
                        <a href="login/index.html" class="btn btn-primary px-4">Go to Login</a>
                    </div>
                </div>
            `);
        } else {
            CarService.reload_cars_datatable();
        }
    }
});

// Bind edit form submission (works even when modal is loaded dynamically)
$(document).on("submit", "#edit-car-form", function (e) {
    e.preventDefault();

    const data = {
        id: $("#edit-car-form input[name='id']").val(),
        manufacturer: $("#edit-car-form input[name='manufacturer']").val(),
        model: $("#edit-car-form input[name='model']").val(),
        year: $("#edit-car-form input[name='year']").val(),
        mileage: $("#edit-car-form input[name='mileage']").val(),
        engine: $("#edit-car-form input[name='engine']").val()
    };

    RestClient.put("cars/edit_car/" + data.id, data, function () {
        $("#edit-car-modal").modal("hide");
        CarService.reload_cars_datatable();
    });
});

// Handle delete 
$(document).on("click", ".btn-delete-car", function () {
    const carId = $(this).data("id");

    if (confirm("Do you want to delete this car?")) {
        RestClient.delete("cars/delete_car/" + carId, {}, function () {
            CarService.reload_cars_datatable();
        });
    }
});


