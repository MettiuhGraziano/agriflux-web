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
				},
				pageLength: 5,
				lengthMenu: [[5, 10, 15], [5, 10, 15]]
			});
		}
	}, 50);
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById('pills-produzione-tab').addEventListener("click", function() {
		setTimeout(() => {
			
			//MULTIPLE LINECHART COLTURE
			function produzioneMultipleChartLineRadar() {
				let lineChartInstance;
				let radarChartInstance;

				fetch("/findColtureJoinProduzione")
					.then(res => res.json())
					.then(data => {
						
						const datalistOptions = document.getElementById("datalistOptions");
						Object.keys(data).forEach(prodotto => {
							const option = document.createElement("option");
							option.value = prodotto;
							option.textContent = prodotto;
							datalistOptions.appendChild(option);
						});
						
						//Se prodottiDataList è cambiato, sblocco il form della data dinamicamente
						document.getElementById("prodottiDataList").addEventListener("change", function() {
							const prodottoSelezionato = this.value;

							//Mappa da utilizzare nel grafico per mostrare i valori
							const mappaAnnoRiferimento = data[prodottoSelezionato];

							const pulsanteRicerca = document.getElementById("ricerca");

							//Se annoSelect è stato selezionato, sblocco il pulsante di visualizzazione del grafico
							pulsanteRicerca.removeAttribute("disabled");
							pulsanteRicerca.addEventListener("click", function() {
								
								const labelsAnnoRiferimento = Object.keys(mappaAnnoRiferimento);
								
								const quantitaRaccolto = [];
								const quantitaRaccoltoVenduto = [];
								const fatturatoColtura = [];
								
								Object.values(mappaAnnoRiferimento).forEach(dto => {
									quantitaRaccolto.push(dto.quantitaRaccolto);
									quantitaRaccoltoVenduto.push(dto.quantitaRaccoltoVenduto);
									fatturatoColtura.push(dto.fatturatoColtura);
								});
								
								const ctxLine = document.getElementById("produzioneMultipleLineChart").getContext("2d");

								// Se esiste già un grafico, viene distrutto prima di crearne uno nuovo
								if (lineChartInstance) {
									lineChartInstance.destroy();
								}
								
								//LINECHART MULTIPLO
								lineChartInstance = new Chart(ctxLine, {
									type: 'line',
									data: {
										labels: labelsAnnoRiferimento,
										datasets: [{
											label: `Quantita' di ${prodottoSelezionato} raccolto/a (KG)`,
											data: quantitaRaccolto,
											borderColor: 'rgb(0, 0, 128)',
											backgroundColor: 'rgb(0, 0, 128)',
											borderWidth: 2
										}, {
											label: `Quantita' di ${prodottoSelezionato} venduto/a (KG)`,
											data: quantitaRaccoltoVenduto,
											borderColor: 'rgb(0, 255, 0)',
											backgroundColor: 'rgb(0, 255, 0)',
											borderWidth: 2
										}]
									}, options: {
										responsive: true,
										maintainAspectRatio: false,
										plugins: {
											scales: {
												y: {
													beginAtZero: true
												}
											}
										}
									}
								});
								
								//RADARCHART
								const ctxRadar = document.getElementById("produzioneRadarChart").getContext("2d");

								// Se esiste già un grafico, viene distrutto prima di crearne uno nuovo
								if (radarChartInstance) {
									radarChartInstance.destroy();
								}
								
								radarChartInstance = new Chart(ctxRadar, {
									type: 'radar',
									data: {
										labels: labelsAnnoRiferimento,
										datasets: [{
											label: `Fatturato annuale ${prodottoSelezionato} (€)`,
											data: fatturatoColtura,
											borderColor: 'rgb(127, 255, 0)',
											backgroundColor: 'rgb(127, 255, 0)',
											borderWidth: 2
										}]
									}, options: {
										responsive: true,
										maintainAspectRatio: false
									}
								});
							});
						});
					})
			}
			produzioneMultipleChartLineRadar();
			
		}, 50)
	})
});
