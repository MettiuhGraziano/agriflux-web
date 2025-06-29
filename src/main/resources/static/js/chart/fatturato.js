let fatturatoLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('fatturato-tab').addEventListener('click', function() {
		if (!fatturatoLoaded) {
			fetch('/fatturato')
				.then(response => response.text())
				.then(html => {
					document.getElementById('tab-fatturato').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento del fatturato:', error);
				});
		}
	});
});

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('fatturato-tab').addEventListener("click", function() {
		setTimeout(() => {
			new DataTable('#fatturato-datatable', {
				responsive: true,
				ordering: true,
				language: {
					entries: {
						_: 'dati fatturato',
						1: 'fatturato'
					}
				},
				pageLength: 5,
				lengthMenu: [ [5, 10, 15], [5, 10, 15] ]
			});
		}, 1000);
	});
});