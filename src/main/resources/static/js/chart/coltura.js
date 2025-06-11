let coltureLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('pills-colture-tab').addEventListener('click', function() {
		if (!coltureLoaded) {
			fetch('/coltura')
				.then(response => response.text())
				.then(html => {
					document.getElementById('pills-colture').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento delle colture:', error);
				});
		}
	});
});

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('pills-colture-tab').addEventListener("click", function() {
		setTimeout(() => {
			new DataTable('#coltura-datatable', {
				responsive: true,
				ordering: true,
				language: {
					entries: {
						_: 'colture',
						1: 'coltura'
					}
				}
			});
		}, 50);
	});
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById('pills-colture-tab').addEventListener("click", function() {
		setTimeout(() => {
			const ctx = document.getElementById('colturaBarChart').getContext('2d');

			function colturaBarChart(ctx) {
				fetch('/countColtureGroupByProdotto')
					.then(response => response.json())
					.then(data => {
						new Chart(ctx, {
							type: 'bar',
							data: {
								labels: Object.keys(data),
								datasets: [{
									label: 'Colture Totali',
									data: Object.values(data),
									borderWidth: 2
								}]
							},
							options: {
								responsive: true,
								scales: {
									y: {
										beginAtZero: true
									}
								}
							}
						})
					});
			}
			
			colturaBarChart(ctx);
		}, 50);
	});
});
