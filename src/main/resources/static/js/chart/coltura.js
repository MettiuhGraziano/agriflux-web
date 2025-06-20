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
				},
				pageLength: 5,
				lengthMenu: [ [5, 10, 15], [5, 10, 15] ]
			});
		}, 1000);
	});
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById('pills-colture-tab').addEventListener("click", function() {
		setTimeout(() => {
			
			colturaLineChartDinamica();
			colturaBarChart();
			colturaPieChart();
			
		}, 1000);
	});
});

function generaColoreRandom() {
	var r = Math.floor(Math.random() * 255);
	var g = Math.floor(Math.random() * 255);
	var b = Math.floor(Math.random() * 255);
	return "rgb(" + r + "," + g + "," + b + ")";
}

//BARCHART COLTURE
function colturaBarChart() {

	const ctx = document.getElementById('colturaBarChart').getContext('2d');

	fetch('/countOrtaggioColtura')
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
					maintainAspectRatio: false,
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			})
		});
}

//PIECHART FAMIGLIE COLTURE
function colturaPieChart() {
	const ctx = document.getElementById('colturaPieChart').getContext('2d');

	fetch('/countFamigliaOrtaggioColtura')
		.then(response => response.json())
		.then(data => {

			const colorArray = [];
			//GENERO COLORI RANDOM X OGNI COLTURA
			Object.keys(data).forEach(prodotto => {
				colorArray.push(generaColoreRandom());
			});

			new Chart(ctx, {
				type: 'pie',
				data: {
					labels: Object.keys(data),
					datasets: [{
						label: 'Colture Totali',
						data: Object.values(data),
						hoverOffset: 5,
						backgroundColor: colorArray
					}]
				},
				options: {
					responsive: true,
					maintainAspectRatio: false,
					animateRotate: true,
					animateScale: true
				}
			})
		});
}

//LINECHART COLTURE
function colturaLineChartDinamica() {

	let lineChartInstance;

	fetch("/findPrezziAndDateRaccoltoColtura")
		.then(res => res.json())
		.then(data => {
			const select = document.getElementById("productSelect");

			// Popola la Select con le chiavi della mappa
			Object.keys(data).forEach(prodotto => {
				const option = document.createElement("option");
				option.value = prodotto;
				option.textContent = prodotto;
				select.appendChild(option);
			});

			// Evento cambio selezione
			select.addEventListener("change", function() {
				const prodottoSelezionato = this.value;
				const colturaData = data[prodottoSelezionato];

				const labels = colturaData.dataRaccoltoList;
				const values = colturaData.prezzoKgList;

				const ctx = document.getElementById("colturaLineChart").getContext("2d");

				// Se esiste già un grafico, viene distrutto prima di crearne uno nuovo
				if (lineChartInstance) {
					lineChartInstance.destroy();
				}

				lineChartInstance = new Chart(ctx, {
					type: "line",
					data: {
						labels: labels,
						datasets: [{
							label: `Prezzo ${prodottoSelezionato}`,
							data: values,
							fill: false,
							borderColor: generaColoreRandom(),
							tension: 0.2
						}]
					},
					options: {
						responsive: true,
						maintainAspectRatio: false,
						scales: {
							y: {
								beginAtZero: false,
								title: { display: true, text: 'Prezzo €/kg' }
							},
							x: {
								title: { display: true, text: 'Data Raccolto' },
								beginAtZero: false
							}
						}
					}
				});
			});
		});
}
