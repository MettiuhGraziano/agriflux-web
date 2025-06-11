let terrenoLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const terrenoTab = document.getElementById('pills-terreno-tab');
	terrenoTab.addEventListener('click', function() {
		if (!terrenoLoaded) {
			fetch('/terreno')
				.then(response => response.text())
				.then(html => {
					document.getElementById('pills-terreno').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento del terreno:', error);
				});
		}
	});
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
	}, 50); // aspetta che il fragment sia stato inserito nel DOM
});