let produzioneLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const produzioneTab = document.getElementById('produzione-tab');
	produzioneTab.addEventListener('click', function() {
		if (!produzioneLoaded) {
			fetch('/produzione')
				.then(response => response.text())
				.then(html => {
					document.getElementById('tab-produzione').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento dei dati di produzione:', error);
				});
		}
	});
});

document.getElementById('produzione-tab').addEventListener('click', function() {
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
	}, 1000);
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById('produzione-tab').addEventListener("click", function() {
		setTimeout(() => {

			produzioneMultipleChartLineRadar();
			downloadProduzioneAnnualeRaccoltoChart();
			
			produzioneBarChartHorizontal();
			downloadVariazioneTempi();
			
			produzioneScatterChart();
			downloadDistribuzioneProduzione();

		}, 1000);
	})
});

function generaColoreRandom() {
	var r = Math.floor(Math.random() * 255);
	var g = Math.floor(Math.random() * 255);
	var b = Math.floor(Math.random() * 255);
	return "rgb(" + r + "," + g + "," + b + ")";
}

//MULTIPLE LINECHART COLTURE
function produzioneMultipleChartLineRadar() {
	let lineChartInstance;
	let radarChartInstance;

	fetch("/findProduzioneQuantitaJoinColtura")
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

				const pulsanteRicerca = document.getElementById("ricercaAnno");

				//Se annoSelect è stato selezionato, sblocco il pulsante di visualizzazione del grafico
				pulsanteRicerca.removeAttribute("disabled");
				pulsanteRicerca.addEventListener("click", function() {

					const labelsAnnoRiferimento = Object.keys(mappaAnnoRiferimento);

					const quantitaRaccolto = [];
					const quantitaRaccoltoVenduto = [];
					const quantitaRaccoltoMarcio = [];
					const quantitaRaccoltoTerzi = [];
					const fatturatoRaccolto = [];

					Object.values(mappaAnnoRiferimento).forEach(dto => {
						quantitaRaccolto.push(dto.quantitaRaccolto);
						quantitaRaccoltoVenduto.push(dto.quantitaRaccoltoVenduto);
						quantitaRaccoltoMarcio.push(dto.quantitaRaccoltoMarcio);
						quantitaRaccoltoTerzi.push(dto.quantitaRaccoltoTerzi);
						fatturatoRaccolto.push(dto.fatturatoRaccolto);
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
							},
							{
								label: `Quantita' di ${prodottoSelezionato} marcio/a (KG)`,
								data: quantitaRaccoltoMarcio,
								borderColor: 'rgb(210,105,30)',
								backgroundColor: 'rgb(210,105,30)',
								borderWidth: 2
							}, {
								label: `Quantita' di ${prodottoSelezionato} utilizzato per scopi terzi (KG)`,
								data: quantitaRaccoltoTerzi,
								borderColor: 'rgb(139,0,139)',
								backgroundColor: 'rgb(139,0,139)',
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
								data: fatturatoRaccolto,
								borderColor: 'rgb(233,150,122)',
								backgroundColor: 'rgb(233,150,122, 0.4)',
								borderWidth: 3
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

//HORIZONTAL BARCHART
function produzioneBarChartHorizontal() {
	let horizontalBarChartInstance;

	fetch("/findProduzioneJoinColturaTempi")
		.then(res => res.json())
		.then(data => {

			const annoRiferimentoSelect = document.getElementById("annoRiferimentoSelect");

			Object.keys(data).forEach(annoRiferimento => {
				const option = document.createElement("option");
				option.value = annoRiferimento;
				option.textContent = annoRiferimento;
				annoRiferimentoSelect.appendChild(option);
			});

			annoRiferimentoSelect.addEventListener("change", function() {

				const ctxBar = document.getElementById("produzioneBarChartHorizontal").getContext("2d");

				// Se esiste già un grafico, viene distrutto prima di crearne uno nuovo
				if (horizontalBarChartInstance) {
					horizontalBarChartInstance.destroy();
				}

				const annoSelectList = data[this.value];
				const prodottiColtivati = [];

				const medieSeminaList = [];
				const medieGerminazioneList = [];
				const medieTrapiantoList = [];
				const medieMaturazioneList = [];
				const medieRaccoltaList = [];

				annoSelectList.forEach(dto => {
					prodottiColtivati.push(dto.prodottoColtivato);
					medieSeminaList.push(dto.listMedieTempi[0]);
					medieGerminazioneList.push(dto.listMedieTempi[1]);
					medieTrapiantoList.push(dto.listMedieTempi[2]);
					medieMaturazioneList.push(dto.listMedieTempi[3]);
					medieRaccoltaList.push(dto.listMedieTempi[4]);
				});
				
				horizontalBarChartInstance = new Chart(ctxBar, {
					type: 'bar',
					data: {
						labels: prodottiColtivati,
						datasets: [{
							label: 'Tempo Semina',
							data: medieSeminaList,
							borderColor: 'rgb(33, 102, 172)',
							backgroundColor: 'rgb(33, 102, 172, 0.2)',
							borderWidth: 2
						}, {
							label: 'Tempo Germinazione',
							data: medieGerminazioneList,
							borderColor: 'rgb(67, 147, 195)',
							backgroundColor: 'rgb(67, 147, 195, 0.2)',
							borderWidth: 2
						}, {
							label: 'Tempo Trapianto',
							data: medieTrapiantoList,
							borderColor: 'rgb(146, 197, 222)',
							backgroundColor: 'rgb(146, 197, 222, 0.2)',
							borderWidth: 2
						}, {
							label: 'Tempo Maturazione',
							data: medieMaturazioneList,
							borderColor: 'rgb(244, 165, 130)',
							backgroundColor: 'rgb(244, 165, 130, 0.2)',
							borderWidth: 2
						}, {
							label: 'Tempo Raccolta',
							data: medieRaccoltaList,
							borderColor: 'rgb(215, 48, 39)',
							backgroundColor: 'rgb(215, 48, 39, 0.2)',
							borderWidth: 2
						}]
					}, options: {
						indexAxis: 'y',
						responsive: true,
						maintainAspectRatio: false,
						scales: {
							x: {
								stacked: true,
								beginAtZero: true,
								title: { display: true, text: 'Giorni' }
							},
							y: {
								stacked: true,
								title: { display: true, text: 'Prodotti' }
							}
						}, plugins: {
							legend: {
								position: 'right',
							}
						}
					}
				});
			});
		})
}

//SCATTER CHART
function produzioneScatterChart() {
	let produzioneScatterChart;

	fetch("/findProduzioneParticellaColturaOrtaggio")
		.then(res => res.json())
		.then(data => {

			const startDate = document.getElementById("startDate");
			const endDate = document.getElementById("endDate");

			const produzioni = Object.keys(data);

			//Setto gli attributi min e max con il range massimo di date e i value
			startDate.setAttribute("min", data[produzioni[0]].dataRaccolto);
			startDate.setAttribute("max", data[produzioni[produzioni.length - 1]].dataRaccolto);
			startDate.setAttribute("value", data[produzioni[0]].dataRaccolto);
			
			endDate.setAttribute("min", data[produzioni[0]].dataRaccolto);
			endDate.setAttribute("max", data[produzioni[produzioni.length - 1]].dataRaccolto);
			endDate.setAttribute("value", data[produzioni[produzioni.length - 1]].dataRaccolto);

			startDate.addEventListener("change", function() {
				startDate.setAttribute("value", this.value);
			});

			endDate.addEventListener("change", function() {
				endDate.setAttribute("value", this.value);
			});

			document.getElementById("invio").addEventListener("click", function() {

				const produzioniFiltrate = [];

				const startDateSelected = document.getElementById("startDate");
				const endDateSelected = document.getElementById("endDate");

				var dateMax = new Date(new Date(endDateSelected.value).setYear(new Date(endDateSelected.value).getFullYear() + 1));
				var dateMin = new Date(new Date(startDateSelected.value).setYear(new Date(startDateSelected.value).getFullYear() - 1));
				
				var rangeMin = new Date(startDateSelected.value);
				var rangeMax = new Date(endDateSelected.value);
				
				//Filtro le produzioni in base al range di date inserito
				produzioni.forEach(produzione => {
					const dataRaccolto = new Date(data[produzione].dataRaccolto);
					if ((dataRaccolto <= rangeMin && dataRaccolto >= rangeMax) ||
						(dataRaccolto >= rangeMin && dataRaccolto <= rangeMax)) {
						produzioniFiltrate.push(produzione);
					}
				});

				//Recupero la lista di prodotti coltivati
				fetch("/findListaProdottiColtivati")
					.then(res => res.json())
					.then(prodottiColtivati => {

						const mapColore = new Map();

						prodottiColtivati.forEach(prodotto => {
							mapColore.set(prodotto, { colore: generaColoreRandom() });
						});

						const datasetDinamico = [];
						//Mappo dinamicamente il dataset del grafico
						for (let prodotto of mapColore.keys()) {
							const dataDinamico = [];
							produzioni.forEach(produzione => {
								if (data[produzione].prodottoColtivato == prodotto) {
									//Converto la data in formato yyyy-MM-dd
									const isoString = new Date(data[produzione].dataRaccolto).toISOString();
									const dataFormattata = isoString.split("T")[0];
									dataDinamico.push({
										x: dataFormattata,
										y: Number(produzione)
									})
								}
							});
							datasetDinamico.push({
								label: prodotto,
								data: dataDinamico,
								backgroundColor: mapColore.get(prodotto).colore,
								pointStyle: 'circle',
								pointRadius: 5,
							})
						}

						let ctxScatter = document.getElementById("produzioneScatterChart").getContext("2d");

						// Se esiste già un grafico, viene distrutto prima di crearne uno nuovo
						if (produzioneScatterChart) {
							produzioneScatterChart.destroy();
						}

						produzioneScatterChart = new Chart(ctxScatter, {
							type: "scatter",
							data: {
								datasets: datasetDinamico
							}, options: {
								responsive: true,
								//EVENTO ONCLICK
								onClick: (event, pointer) => {

									//Inserisco l'evento dentro un IF per evitare errori quando non si preme sui puntini
									if (pointer.length > 0) {
										//Estraggo l'indice relativo alla posizione del dato del punto premuto nel dataset
										const elementIndex = pointer[0].index;
										//Estraggo l'indice relativo alla posizione del dataset
										const datasetIndex = pointer[0].datasetIndex;
										//Estraggo il valore dell'oggetto puntato
										const point = produzioneScatterChart.data.datasets[datasetIndex].data[elementIndex];

										aggiornaTabella(data[point.y]);
									}
								},
								maintainAspectRatio: false,
								scales: {
									x: {
										type: 'time',
										time: {
											unit: 'month',
											tooltipFormat: 'yyyy-MM-dd',
											displayFormats: {
												day: 'yyyy-MM-dd'
											}
										},
										min: dateMin.toISOString(),
										max: dateMax.toISOString(),
										title: {
											display: true,
											text: 'Data Raccolto'
										}
									},
									y: {
										beginAtZero: true,
										title: {
											display: true,
											text: 'Codice Produzione'
										}
									}
								}, plugins: {
									legend: {
										position: 'top',
									}
								}
							}
						});
					})
			});
		})
}

function aggiornaTabella(dto) {
	const tabellaUpdate = document.getElementById("produzione-chart-datatable");
	const bodyTabella = tabellaUpdate.querySelector("tbody");
	
	bodyTabella.innerHTML = '';

	const prodottoRow = document.createElement("tr");
	prodottoRow.innerHTML = `<td class="text-center">Prodotto Coltivato</td>
			    <td class="text-center">${dto.prodottoColtivato}</td>`;
	bodyTabella.appendChild(prodottoRow);

	const morfologiaRow = document.createElement("tr");
	morfologiaRow.innerHTML = `<td class="text-center">Codice Particella</td>
						    <td class="text-center">${dto.idParticella}</td>`;
	bodyTabella.appendChild(morfologiaRow);

	const estensioneRow = document.createElement("tr");
	estensioneRow.innerHTML = `<td class="text-center">Estensione Terreno</td>
						    <td class="text-center">${dto.estensioneTerreno} m²</td>`;
	bodyTabella.appendChild(estensioneRow);

	const pendenzaRow = document.createElement("tr");
	pendenzaRow.innerHTML = `<td class="text-center">Pendenza</td>
						    <td class="text-center">${dto.pendenza} %</td>`;
	bodyTabella.appendChild(pendenzaRow);

	const esposizione = document.createElement("tr");
	esposizione.innerHTML = `<td class="text-center">Esposizione</td>
						    <td class="text-center">${dto.esposizione}</td>`;
	bodyTabella.appendChild(esposizione);

	const litologiaRow = document.createElement("tr");
	litologiaRow.innerHTML = `<td class="text-center">Litologia</td>
						    <td class="text-center">${dto.litologia}</td>`;
	bodyTabella.appendChild(litologiaRow);
}

function downloadProduzioneAnnualeRaccoltoChart() {
	document.getElementById("downloadProduzioneAnnualeRaccolto").addEventListener("click", async () => {
		const { jsPDF } = window.jspdf;

		const pdf = new jsPDF({
			orientation: 'landscape',
			unit: 'px',
			format: 'a4'
		});

		const canvas1 = document.getElementById("produzioneMultipleLineChart");
		const imageData1 = canvas1.toDataURL("image/png", 1.0);

		const width1 = pdf.internal.pageSize.getWidth();
		const height1 = canvas1.height * (width1 / canvas1.width) / 2;

		pdf.text("Produzione Annuale", 40, 30);
		pdf.addImage(imageData1, 'PNG', 40, 50, width1 - 80, height1);
		
		const canvas2 = document.getElementById("produzioneRadarChart");
		const imageData2 = canvas2.toDataURL("image/png", 1.0);

		const width2 = pdf.internal.pageSize.getWidth();
		const height2 = canvas2.height * (width2 / canvas2.width) / 2;
	
		pdf.addPage();
		pdf.text("Fatturato Annuale", 40, 30);
		pdf.addImage(imageData2, 'PNG', 40, 50, width2 - 80, height2);

		pdf.save("produzione_annuale_raccolto.pdf");
	});
}

function downloadDistribuzioneProduzione() {
	document.getElementById("downloadDistribuzioneProduzione").addEventListener("click", async () => {
		const { jsPDF } = window.jspdf;

		const pdf = new jsPDF({
			orientation: 'landscape',
			unit: 'px',
			format: 'a4'
		});

		const canvas = document.getElementById("produzioneScatterChart");
		const imageData = canvas.toDataURL("image/png", 1.0);

		const width = pdf.internal.pageSize.getWidth();
		const height = canvas.height * (width / canvas.width) / 2;

		pdf.text("Distribuzione Produzione Prodotti", 40, 30);
		pdf.addImage(imageData, 'PNG', 40, 50, width - 80, height);

		pdf.save("distribuzione_produzione.pdf");
	});
}

function downloadVariazioneTempi() {
	document.getElementById("downloadVariazioneTempi").addEventListener("click", async () => {
		const { jsPDF } = window.jspdf;

		const pdf = new jsPDF({
			orientation: 'landscape',
			unit: 'px',
			format: 'a4'
		});

		const canvas = document.getElementById("produzioneBarChartHorizontal");
		const imageData = canvas.toDataURL("image/png", 1.0);

		const width = pdf.internal.pageSize.getWidth();
		const height = canvas.height * (width / canvas.width);

		pdf.text("Variazioni tempi di coltura", 40, 30);
		pdf.addImage(imageData, 'PNG', 40, 50, width - 80, height);

		pdf.save("variazione_tempi.pdf");
	});
}
