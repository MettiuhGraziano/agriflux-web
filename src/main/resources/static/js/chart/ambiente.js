let ambienteLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const ambienteTab = document.getElementById('ambiente-tab');
	ambienteTab.addEventListener('click', function() {
		if (!ambienteLoaded) {
			fetch('/ambiente')
				.then(response => response.text())
				.then(html => {
					document.getElementById('tab-ambiente').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento dei dati ambientali:', error);
				});
		}
	});
});

document.getElementById('ambiente-tab').addEventListener('click', function() {
	setTimeout(() => {
		if (!$.fn.DataTable.isDataTable('#ambiente-datatable')) {
			new DataTable('#ambiente-datatable', {
				responsive: true,
				language: {
					entries: {
						_: 'dati ambientali',
						1: 'ambiente'
					}
				},
				pageLength: 5,
				lengthMenu: [[5, 10, 15], [5, 10, 15]]
			});
		}
	}, 1000);
});