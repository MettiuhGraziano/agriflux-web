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
			
			//BARCHART COLTURE
			const ctx = document.getElementById('colturaBarChart').getContext('2d');

			function colturaBarChart() {
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
			
			colturaBarChart(ctx);
			
			//LINECHART COLTURE
			let lineChartInstance;
			
			function colturaLineChartDinamica() {
			  fetch("/findPrezziAndDateRaccoltoColtura")
			    .then(res => res.json())
			    .then(data => {
			      const select = document.getElementById("productSelect");

			      // Popola la select con le chiavi della mappa
			      Object.keys(data).forEach(prodotto => {
			        const option = document.createElement("option");
			        option.value = prodotto;
			        option.textContent = prodotto;
			        select.appendChild(option);
			      });

			      // Evento cambio selezione
			      select.addEventListener("change", function () {
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
			              borderColor: 'rgb(75, 192, 192)',
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
			                title: { display: true, text: 'Data Raccolto' }
			              }
			            }
			          }
			        });
			      });
			    });
			}
			
			colturaLineChartDinamica();
			
		}, 50);
	});
});
