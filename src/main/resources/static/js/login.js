const images = [
	'/images/carosello1.png',
	'/images/carosello2.png',
	'/images/carosello3.png',
	'/images/carosello4.jpg',
	'/images/carosello5.jpg',
	'/images/carosello6.jpg'
];

let index = 0;
const bgDiv = document.querySelector('.bg-carousel');

function changeBg() {
	bgDiv.style.backgroundImage = `url('${images[index]}')`;
	index = (index + 1) % images.length;
}

changeBg(); // Mostra il primo sfondo
setInterval(changeBg, 4500); // Cambia ogni 4.5 secondi