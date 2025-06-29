let dashboardLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const dashboardTab = document.getElementById('homepage-tab');
	dashboardTab.addEventListener('click', function() {
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