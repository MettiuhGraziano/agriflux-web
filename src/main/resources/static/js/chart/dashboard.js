let dashboardLoaded = false;

document.addEventListener('DOMContentLoaded', function() {
	const dashboardTab = document.getElementById('dashboard-tab');
	dashboardTab.addEventListener('click', function() {
		if (!dashboardLoaded) {
			fetch('/dashboard')
				.then(response => response.text())
				.then(html => {
					document.getElementById('tab-dashboard').innerHTML = html;
				})
				.catch(error => {
					console.error('Errore nel caricamento della dashboard:', error);
				});
		}
	});
});