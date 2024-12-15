class Rate {
    constructor(date, rate, ticker) {
        this.date = date;
        this.rate = rate;
        this.ticker = ticker;
    }
}

// Fonction pour remplir le sélecteur de devises
function loadTickers() {
    fetch('/api/tickers')
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur lors du chargement des tickers');
            }
            return response.json();
        })
        .then(data => {
            const select = document.getElementById('currency');
            data.forEach(ticker => {
                const option = document.createElement('option');
                option.value = ticker;
                option.textContent = ticker;
                select.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Erreur:', error);
            alert('Impossible de charger la liste des devises.');
        });
}

// Fonction pour mettre à jour le graphique
function updateChart(rates) {
    const labels = rates.map(rate => rate.date);
    const data = rates.map(rate => rate.rate);

    let ctx = document.getElementById('currencyChart').getContext('2d');

    // Check if a chart instance already exists and destroy it
    if (window.currencyChart instanceof Chart) {
        window.currencyChart.destroy();
    }

    window.currencyChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Historique des taux',
                data: data,
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderWidth: 2,
                tension: 0.3,
            }]
        },
        options: {
            responsive: true,
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Date'
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Taux'
                    }
                }
            }
        }
    });
}

// Fonction pour charger les taux d'une devise sélectionnée
function loadRates(ticker) {

    fetch(`/api/rates/`+ticker)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur lors du chargement des taux');
            }
            return response.json();
        })
        .then(data => {
            let valeurs = []
            console.log("ici");
            data.forEach(item => {
                valeurs.push(new Rate(item.date, item.rate, item.ticker));
            });
            console.log(valeurs);
            updateChart(valeurs); // Mettre à jour le graphi
        })
        .catch(error => {
            console.error('Erreur:', error);
            alert('Impossible de charger les taux pour cette devise.');
        });
}

// Écouter les changements dans le sélecteur de devises
document.getElementById('currency').addEventListener('change', (event) => {
    const selectedTicker = event.target.value; // Devise sélectionnée
    loadRates(selectedTicker); // Charger les taux pour la devise sélectionnée
});

// Gestion de l'importation de fichiers CSV
document.getElementById('uploadCsvBtn').addEventListener('click', () => {
    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.accept = '.csv';
    fileInput.addEventListener('change', () => {
        const file = fileInput.files[0];
        if (!file) {
            alert('Veuillez sélectionner un fichier.');
            return;
        }

        const formData = new FormData();
        formData.append('rate', file);

        fetch('/api/rates', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    alert('Fichier importé avec succès.');
                    loadTickers(); // Recharger les tickers après l'import
                } else {
                    throw new Error('Erreur lors de l\'importation du fichier.');
                }
            })
            .catch(error => {
                console.error('Erreur:', error);
                alert('Impossible d\'importer le fichier. Vérifiez son format.');
            });
    });
    fileInput.click(); // Ouvre la boîte de dialogue pour choisir un fichier
});

// Charger les tickers au chargement de la page
document.addEventListener('DOMContentLoaded', () => {
    loadTickers();
});
