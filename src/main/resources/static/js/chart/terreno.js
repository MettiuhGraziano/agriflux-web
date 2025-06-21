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
		if (!$.fn.DataTable.isDataTable('#particella-datatable')) {
			new DataTable('#particella-datatable', {
				responsive: true,
				language: {
					entries: {
						_: 'dati particella',
						1: 'particella'
					}
				},
				pageLength: 5,
				lengthMenu: [[5, 10, 15], [5, 10, 15]]
			});
		}

		if (!$.fn.DataTable.isDataTable('#rilevazione-datatable')) {
			new DataTable('#rilevazione-datatable', {
				responsive: true,
				language: {
					entries: {
						_: 'dati rilevazioni morfologiche',
						1: 'rilevazione'
					}
				},
				pageLength: 5,
				lengthMenu: [[5, 10, 15], [5, 10, 15]]
			});
		}
	}, 1000);
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById('pills-terreno-tab').addEventListener("click", function() {
		setTimeout(() => { 
			
			//terrenoHorizontalBarChart();
			
		}, 100);
	});
});

function generaColoreRandom() {
	var r = Math.floor(Math.random() * 255);
	var g = Math.floor(Math.random() * 255);
	var b = Math.floor(Math.random() * 255);
	return "rgb(" + r + "," + g + "," + b + ")";
}

//BARCHART HORIZONTAL
function terrenoHorizontalBarChart() {
	let barChartInstance;

	fetch("/findTerrenoJoinColturaMorfologia")
		.then(res => res.json())
		.then(data => {

			const terreniDatalistOptions = document.getElementById("terreniDatalistOptions");
			Object.keys(data).forEach(prodotto => {
				const option = document.createElement("option");
				option.value = prodotto;
				option.textContent = prodotto;
				terreniDatalistOptions.appendChild(option);
			});

			document.getElementById("terreniDataList").addEventListener("change", function() {

				const terrenoSelezionato = this.value;
				//Inverto l'ordine della lista per avere la lista di date già ordinate
				const dtoList = data[terrenoSelezionato].slice().reverse();

				const pulsanteRicerca = document.getElementById("ricercaTerreno");

				//Se terreniDataList è stato selezionato, sblocco il pulsante di visualizzazione del grafico
				pulsanteRicerca.removeAttribute("disabled");
				pulsanteRicerca.addEventListener("click", function() {

					// Se esiste già un grafico, viene distrutto prima di crearne uno nuovo
					if (barChartInstance) {
						barChartInstance.destroy();
					}

					const ctxLine = document.getElementById("terrenoHorizontalBarChart").getContext("2d");

					var dateMin = dtoList[dtoList.length - 1].dateRilevazione[0];
					var dateMax = dtoList[0].dateRilevazione[dtoList[0].dateRilevazione.length - 1];

					const datasetDinamico = [];
					const prodottiColtivati = [];

					for (let dto of dtoList) {
						const dataDinamica = { y: dto.prodottoColtivato, x: [dto.dateRilevazione[0], dto.dateRilevazione[dto.dateRilevazione.length - 1]] }

						datasetDinamico.push({
							label: 'Coltura ' + dto.idColtura,
							data: [dataDinamica],
							borderColor: generaColoreRandom()
						})

						if (!prodottiColtivati.includes(dto.prodottoColtivato)) {
							prodottiColtivati.push(dto.prodottoColtivato);
						}
					}

					console.log(datasetDinamico);

					barChartInstance = new Chart(ctxLine, {
						type: 'bar',
						data: {
							labels: prodottiColtivati,
							datasets: datasetDinamico,
							parsing: {
								xAxisKey: 'x'
							}
						}, options: {
							responsive: true,
							maintainAspectRatio: false,
							indexAxis: 'y',
							scales: {
								x: {
									beginAtZero: false,
									type: 'time',
									time: {
										unit: 'day',
										tooltipFormat: 'yyyy-MM-dd',
										displayFormats: {
											day: 'yyyy-MM-dd'
										}
									},
									min: dateMin,
									max: dateMax,
									title: {
										display: true,
										text: 'Data Rilevazione Terreno'
									}
								},
								y: {
									title: {
										display: true,
										text: 'Prodotto Coltivato'
									}
								}
							},
							elements: {
								bar: {
									borderWidth: 2,
								}
							},
							plugins: {
								legend: {
									position: 'right'
								}, tooltip: {
									callbacks: {
										label: function(context) {
											const label = context.dataset.label || '';
											const [start, end] = context.raw.x;
											return [
												`${label}`,
												`Periodo rilevazione: dal ${start} al ${end}`
											];
										}
									}
								}
							}
						}
					})
				});
			});
		})
}