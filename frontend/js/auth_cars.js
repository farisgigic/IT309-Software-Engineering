$(document).ready(function () {
    const user = Utils.get_from_localstorage("user");

    // Only target the My Cars view if it's visible
    if (window.location.hash === "#mycars" || $("#tbl_cars").length > 0) {
        if (!user) {
            $("#layoutSidenav_content").html(`
                <div class="container d-flex justify-content-center align-items-center" style="min-height: 70vh;">
                    <div class="card shadow-lg p-4 text-center" style="max-width: 500px; width: 100%; border-radius: 1rem;">
                        <img src="img/locked.png" alt="Locked" class="mx-auto mb-3" style="max-width: 150px;">
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
