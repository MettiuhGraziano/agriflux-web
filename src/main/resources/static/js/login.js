const images = [
	'/images/test1.jpg',
	'/images/test2.jpg',
	'/images/test3.jpg'
];

let index = 0;
const bgDiv = document.querySelector('.bg-carousel');

function changeBg() {
	bgDiv.style.backgroundImage = `url('${images[index]}')`;
	index = (index + 1) % images.length;
}

changeBg(); // Mostra il primo sfondo
setInterval(changeBg, 5000); // Cambia ogni 5 secondi