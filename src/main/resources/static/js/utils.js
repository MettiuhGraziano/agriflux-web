let coltureLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const coltureTab = document.getElementById('pills-colture-tab');
	coltureTab.addEventListener('click', function() {
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

let ambienteLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const ambienteTab = document.getElementById('pills-ambiente-tab');
	ambienteTab.addEventListener('click', function() {
		if (!ambienteLoaded) {
			fetch('/ambiente')
				.then(response => response.text())
				.then(html => {
					document.getElementById('pills-ambiente').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento dei dati ambientali:', error);
				});
		}
	});
});

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