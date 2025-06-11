let ambienteLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const ambienteTab = document.getElementById('pills-ambiente-tab');
	ambienteTab.addEventListener('click', function() {
		if (!ambienteLoaded) {
			fetch('/ambiente')
				.then(response => response.text())
				.then(html => {
					document.getElementById('pills-ambiente').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento dei dati ambientali:', error);
				});
		}
	});
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
	}, 50); // aspetta che il fragment sia stato inserito nel DOM
});