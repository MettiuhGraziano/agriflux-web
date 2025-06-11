let produzioneLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const produzioneTab = document.getElementById('pills-produzione-tab');
	produzioneTab.addEventListener('click', function() {
		if (!produzioneLoaded) {
			fetch('/produzione')
				.then(response => response.text())
				.then(html => {
					document.getElementById('pills-produzione').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento dei dati di produzione:', error);
				});
		}
	});
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
	}, 50); // aspetta che il fragment sia stato inserito nel DOM
});