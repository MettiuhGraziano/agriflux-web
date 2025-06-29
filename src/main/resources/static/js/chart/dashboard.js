let dashboardLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	isJobExecuted();
	const dashboardTab = document.getElementById('homepage-tab');
	dashboardTab.addEventListener('click', function() {
		isJobExecuted();
		if (!dashboardLoaded) {
			fetch('/homepage')
				.then(response => response.text())
				.then(html => {
					document.getElementById('tab-homepage').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento della homepage:', error);
				});
		}
	});
});

function isJobExecuted() {
	fetch("/isJobExecuted")
		.then(response => response.json())
		.then(result => {
			if (result) {
				document.getElementById("colture-tab").disabled = false;
				document.getElementById("produzione-tab").disabled = false;
				document.getElementById("terreno-tab").disabled = false;
				document.getElementById("ambiente-tab").disabled = false;
				document.getElementById("fatturato-tab").disabled = false;
				document.getElementById("batchButton").disabled = true;

				const progressBar = document.getElementById("batchProgressBar");
				progressBar.style.width = "100%";
				progressBar.innerText = "100%";
				progressBar.classList.remove("progress-bar-animated");
				progressBar.classList.add("bg-success");
			}
		});
}

document.getElementById("batchButton").addEventListener("click", async () => {
	const progressBar = document.getElementById("batchProgressBar");
	let progress = 0;

	// Reset barra
	progressBar.style.width = "0%";
	progressBar.innerText = "0%";
	progressBar.classList.remove("bg-success");

	// Animazione fittizia durante il processo
	const progressAnimation = setInterval(() => {
		if (progress < 90) {
			progress += Math.floor(Math.random() * 5) + 1;
			progress = Math.min(progress, 90);
			progressBar.style.width = `${progress}%`;
			progressBar.innerText = `${progress}%`;
		}
	}, 300);

	// Avvia il batch (e aspetta la fine)
	const response = await fetch("/startSimulationJob", { method: "POST" });
	
	if (response.ok) {
		clearInterval(progressAnimation);

		const progressComplete = setInterval(() => {
			if (progress < 100) {
				progress += 2;
				progress = Math.min(progress, 100);
				progressBar.style.width = `${progress}%`;
				progressBar.innerText = `${progress}%`;
			} else {
				clearInterval(progressComplete);
				progressBar.classList.remove("progress-bar-animated");
				progressBar.classList.add("bg-success");
			}
		}, 50);
		
		//ABILITO I PULSANTI DELLA BARRA DI NAVIGAZIONE E DISABILITO IL PULSANTE DI SIMULAZIONE
		document.getElementById("colture-tab").disabled = false;
		document.getElementById("produzione-tab").disabled = false;
		document.getElementById("terreno-tab").disabled = false;
		document.getElementById("ambiente-tab").disabled = false;
		document.getElementById("fatturato-tab").disabled = false;
		
		document.getElementById("batchButton").disabled = true;
		
	} else {
		clearInterval(progressAnimation);
		alert("Errore durante l'esecuzione del processo di Simulazione");
	}
});