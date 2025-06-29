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

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById('ambiente-tab').addEventListener("click", function() {
		setTimeout(() => {

			ambienteLineChartDinamico();
			downloadAmbienteLineChart();
			
		}, 1000);
	});
});

//LINECHART AMBIENTE
function ambienteLineChartDinamico() {

	let lineChartInstance;

	fetch("/getListaParametriAmbientali")
		.then(res => res.json())
		.then(data => {
			const select = document.getElementById("parameterSelect");

			// Popola la Select con le chiavi della mappa
			data.forEach(parametro => {
				const option = document.createElement("option");
				option.value = parametro;
				option.textContent = parametro;
				select.appendChild(option);
			});

			// Evento cambio selezione
			select.addEventListener("change", function() {
				const parametroSelezionato = this.value;

				fetch("/getVariazioneValoriParametriAmbiente")
					.then(res => res.json())
					.then(dataMap => {
						const dtoList = dataMap[parametroSelezionato];
						
						const dataDinamico = [];
						
						dtoList.forEach(dto => {
							dataDinamico.push({
								x: dto.dataRilevazione, y: dto.valoreParametro, variazione: dto.variazionePercentuale
							})
						});
						
						const ctx = document.getElementById("ambienteLineChart").getContext("2d");

						// Se esiste già un grafico, viene distrutto prima di crearne uno nuovo
						if (lineChartInstance) {
							lineChartInstance.destroy();
						}

						lineChartInstance = new Chart(ctx, {
							type: "line",
							data: {
								datasets: [{
									label: `Andamento ${parametroSelezionato}`,
									data: dataDinamico,
									fill: false,
									borderColor: generaColoreRandom()
								}]
							},
							options: {
								responsive: true,
								maintainAspectRatio: false,
								scales: {
									y: {
										beginAtZero: false,
										title: { display: true, text: 'Valore' }
									},
									x: {
										type: 'time',
										time: {
											unit: 'day',
											tooltipFormat: 'yyyy-MM-dd',
											displayFormats: {
												day: 'yyyy-MM-dd'
											},
											beginAtZero: true,
											title: { display: true, text: 'Data Rilevazione' }
										}
									}
								}, plugins: {
									tooltip: {
										callbacks: {
											title: function(tooltipItems) {
												return `Andamento ${parametroSelezionato}`;
											},
											label: function(context) {
												const dataRilevazione = context.raw.x;
												const value = context.raw.y;
												const variazionePercentuale = context.raw.variazione;
												
												var unitaMisura;
												if (parametroSelezionato == 'TEMPERATURA') {
													unitaMisura = '°C';
												} else if (parametroSelezionato == 'UMIDITA') {
													unitaMisura = '%';
												} else if (parametroSelezionato == 'PRECIPITAZIONI') {
													unitaMisura = 'ml';
												} else if (parametroSelezionato == 'IRRAGGIAMENTO') {
													unitaMisura = 'kWh/m²';
												} else if (parametroSelezionato == 'OMBREGGIAMENTO') {
													unitaMisura = 'Fsh';
												}
												
												return [
													`Data rilevazione: ${dataRilevazione}`,
													`Valore: ${value} ${unitaMisura}`,
													`Variazione rispetto all'anno precedente: ${variazionePercentuale} %`
												];
											}
										}
									}
								}
							}
						});
					});
			});
		});
}

function downloadAmbienteLineChart() {
	document.getElementById("downloadAmbienteLineChart").addEventListener("click", async () => {
		const { jsPDF } = window.jspdf;

		const pdf = new jsPDF({
			orientation: 'landscape',
			unit: 'px',
			format: 'a4'
		});

		const canvas = document.getElementById("ambienteLineChart");
		const imageData = canvas.toDataURL("image/png", 1.0);

		const width = pdf.internal.pageSize.getWidth();
		const height = canvas.height * (width / canvas.width);

		pdf.text("Andamento Parametri Ambientali", 40, 30);
		pdf.addImage(imageData, 'PNG', 40, 50, width - 80, height);

		pdf.save("andamento_parametri_ambientali.pdf");
	});
}