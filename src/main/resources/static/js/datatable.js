document.addEventListener("DOMContentLoaded", function() {
	// Inizializza tutti i dropdown nella sidebar
	document.querySelectorAll('.dropdown-toggle').forEach(function(dropdownToggleEl) {
		new bootstrap.Dropdown(dropdownToggleEl);
	});

	// Inizializza i tab pill manualmente (facoltativo ma utile se sono dinamici)
	document.querySelectorAll('[data-bs-toggle="pill"]').forEach(function(pillBtn) {
		pillBtn.addEventListener("click", function() {
			let tab = new bootstrap.Tab(pillBtn);
			tab.show();
		});
	});
});

document.getElementById('pills-colture-tab').addEventListener('click', function() {
	setTimeout(() => {
		if (!$.fn.DataTable.isDataTable('#coltura-datatable')) {
			new DataTable('#coltura-datatable', {
				responsive: true,
				language: {
					entries: {
						_: 'colture',
						1: 'coltura'
					}
				}
			});
		}
	}, 100); // aspetta che il fragment sia stato inserito nel DOM
});

document.getElementById('pills-produzione-tab').addEventListener('click', function() {
	setTimeout(() => {
		if (!$.fn.DataTable.isDataTable('#produzione-datatable')) {
			new DataTable('#produzione-datatable', {
				responsive: true,
				language: {
					entries: {
						_: 'dati produzione',
						1: 'produzione'
					}
				}
			});
		}
	}, 100); // aspetta che il fragment sia stato inserito nel DOM
});

document.getElementById('pills-ambiente-tab').addEventListener('click', function() {
	setTimeout(() => {
		if (!$.fn.DataTable.isDataTable('#ambiente-datatable')) {
			new DataTable('#ambiente-datatable', {
				responsive: true,
				language: {
					entries: {
						_: 'dati ambientali',
						1: 'ambiente'
					}
				}
			});
		}
	}, 100); // aspetta che il fragment sia stato inserito nel DOM
});

document.getElementById('pills-terreno-tab').addEventListener('click', function() {
	setTimeout(() => {
		if (!$.fn.DataTable.isDataTable('#terreno-datatable')) {
			new DataTable('#terreno-datatable', {
				responsive: true,
				language: {
					entries: {
						_: 'dati terreno',
						1: 'terreno'
					}
				}
			});
		}

		if (!$.fn.DataTable.isDataTable('#morfologia-datatable')) {
			new DataTable('#morfologia-datatable', {
				responsive: true,
				language: {
					entries: {
						_: 'dati morfologici',
						1: 'morfologia'
					}
				}
			});
		}
	}, 100); // aspetta che il fragment sia stato inserito nel DOM
});