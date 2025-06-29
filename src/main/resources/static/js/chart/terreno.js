let terrenoLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const terrenoTab = document.getElementById('terreno-tab');
	terrenoTab.addEventListener('click', function() {
		if (!terrenoLoaded) {
			fetch('/terreno')
				.then(response => response.text())
				.then(html => {
					document.getElementById('tab-terreno').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento del terreno:', error);
				});
		}
	});
});

document.getElementById('terreno-tab').addEventListener('click', function() {
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
	document.getElementById('terreno-tab').addEventListener("click", function() {
		setTimeout(() => { 
			
			rotazioneColtureBarChart();
			
		}, 1000);
	});
});

function generaColoreRandom() {
	var r = Math.floor(Math.random() * 255);
	var g = Math.floor(Math.random() * 255);
	var b = Math.floor(Math.random() * 255);
	return "rgb(" + r + "," + g + "," + b + ")";
}

//ROTAZIONE COLTURE BARCHART
function rotazioneColtureBarChart() {
	let barChartInstance;

	fetch("/findParticellaJoinColturaTerreno")
		.then(res => res.json())
		.then(data => {

			const terreniDatalistOptions = document.getElementById("particelleDatalistOptions");
			Object.keys(data).forEach(prodotto => {
				const option = document.createElement("option");
				option.value = prodotto;
				option.textContent = prodotto;
				terreniDatalistOptions.appendChild(option);
			});

			document.getElementById("particelleDataList").addEventListener("change", function() {

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

					const ctxLine = document.getElementById("rotazioneColtureBarChart").getContext("2d");

					var dateMin = dtoList[dtoList.length - 1].dateRilevazione[0];
					var dateMax = dtoList[0].dateRilevazione[dtoList[0].dateRilevazione.length - 1];

					const prodottiColtivati = [];

					const datasetMap = new Map(); // Mappa per aggregare i dati per prodotto

					for (let dto of dtoList) {
					    const range = {
					        y: dto.prodottoColtivato,
					        x: [dto.dateRilevazione[0], dto.dateRilevazione[dto.dateRilevazione.length - 1]]
					    };

					    if (datasetMap.has(dto.prodottoColtivato)) {
					        datasetMap.get(dto.prodottoColtivato).data.push(range);
					    } else {
					        datasetMap.set(dto.prodottoColtivato, {
					            label: 'Coltura ' + dto.idColtura,
					            data: [range],
					            borderColor: generaColoreRandom()
					        });
					    }
					}
					
					const datasetDinamico = Array.from(datasetMap.values());
					
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
									stacked: true,
									beginAtZero: false,
									type: 'time',
									time: {
										unit: 'year',
										tooltipFormat: 'yyyy',
										displayFormats: {
											day: 'yyyy'
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
									barThickness: 100
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