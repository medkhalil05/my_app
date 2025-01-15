AOS.init();

// Fonction pour afficher/masquer les sous-catégories
function toggleSubMenu(menuId, arrowId) {
    const subMenu = document.getElementById(menuId);
    const arrow = document.getElementById(arrowId);
    subMenu.classList.toggle("hidden");
    arrow.classList.toggle("rotate-180");
}

document.getElementById("toggleChambres").addEventListener("click", function() {
    toggleSubMenu("chambresSubMenu", "arrowChambres");
});

document.getElementById("toggleResidents").addEventListener("click", function() {
    toggleSubMenu("residentsSubMenu", "arrowResidents");
});

document.getElementById("toggleRequetes").addEventListener("click", function() {
    toggleSubMenu("requetesSubMenu", "arrowRequetes");
});



function fetchChambres() {
    fetch('http://localhost:8080/api/chambres', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'),
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erreur lors de la récupération des chambres : ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        const chambresTableBody = document.querySelector('#chambres tbody');
        if (!chambresTableBody) {
            console.error('Élément tbody introuvable dans le DOM.');
            return;
        }
        chambresTableBody.innerHTML = ''; // Effacer les anciennes données

        if (data && Array.isArray(data) && data.length > 0) {
            data.forEach(chambre => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td class="px-6 py-3">${chambre.numero}</td>
                    <td class="px-6 py-3">${chambre.taille}</td>
                    <td class="px-6 py-3">${chambre.equipements}</td>
                    <td class="px-6 py-3">${chambre.etat}</td>
                    <td class="px-6 py-3">${chambre.prix}</td> 
                    <td>
                        ${chambre.etat === 'DISPONIBLE' ? `
                            <button 
                                class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-700"
                                onclick="showAssignForm(${chambre.id})">
                                Assigner
                            </button>
                        ` : `
                            <button 
                                class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700"
                                onclick="unassignChambre(${chambre.id})">
                                Libérer
                            </button>
                        `}
                    </td>
                `;
                chambresTableBody.appendChild(row);
            });
        } else {
            chambresTableBody.innerHTML = '<tr><td colspan="6" class="text-center">Aucune chambre disponible</td></tr>';
        }
    })
    .catch(error => {
        console.error('Erreur lors de la récupération des chambres :', error);
    });
}

function unassignChambre(chambreId) {
    fetch(`http://localhost:8080/api/chambres/desassign-chambre/${chambreId}`, {
        method: 'put',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'),
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erreur lors de la désattribution de la chambre : ' + response.statusText);
        }
        alert('Chambre désattribuée avec succès');
        fetchChambres(); // Recharger la liste
    })
    .catch(error => {
        console.error('Erreur lors de la désattribution de la chambre :', error);
    });
}




function showAssignForm(chambreId) {
    // Affiche la modale pour attribuer une chambre
    const modal = document.getElementById('assignModal');
    modal.classList.remove('hidden');
    modal.querySelector('#chambreId').value = chambreId;
}

function assignChambre(event) {
    event.preventDefault();
    const chambreId = document.getElementById('chambreId').value;
    const residentEmail = document.getElementById('residentEmail').value;

    fetch(`http://localhost:8080/api/chambres/${residentEmail}/assign-chambre/${chambreId}`, {
        method: 'put',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: residentEmail })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erreur lors de l\'attribution de la chambre : ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        alert('Chambre attribuée avec succès');
        document.getElementById('assignModal').classList.add('hidden');
        fetchChambres(); // Rafraîchir la liste des chambres
    })
    .catch(error => {
        console.error('Erreur lors de l\'attribution de la chambre :', error);
    });
}

function closeAssignForm() {
    document.getElementById('assignModal').classList.add('hidden');
}


function fetchResidents() {
    fetch('http://localhost:8080/api/residents', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'),
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erreur lors de la récupération des résidents : ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        const residentsTableBody = document.querySelector('#residents tbody');
        residentsTableBody.innerHTML = ''; // Effacer les anciennes données

        if (data && Array.isArray(data) && data.length > 0) {
            data.forEach(resident => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td class="px-6 py-3">${resident.nom}</td>
                    <td class="px-6 py-3">${resident.prenom}</td>
                    <td class="px-6 py-3">${resident.email}</td>
                    <td class="px-6 py-3">${resident.chambre ? resident.chambre.numero : 'Non affecté'}</td>
                `;
                residentsTableBody.appendChild(row);
            });
        } else {
            residentsTableBody.innerHTML = '<tr><td colspan="3" class="text-center">Aucun résident disponible</td></tr>';
        }
    })
    .catch(error => {
        console.error('Erreur lors de la récupération des résidents :', error);
    });
}


function deleteChambre(id) {
fetch(`http://localhost:8080/api/chambres/${id}`, {
    method: 'DELETE',
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'),
        'Content-Type': 'application/json'
    }
})
.then(response => {
    if (!response.ok) {
        throw new Error('Erreur lors de la suppression de la chambre');
    }
    alert('Chambre supprimée avec succès');
    fetchChambres(); // Actualiser la liste après suppression
})
.catch(error => {
    console.error('Erreur lors de la suppression de la chambre :', error);
});
}

function addChambre() {
    const numChambre = document.getElementById('numChambre').value;
    const tailleChambre = document.getElementById('tailleChambre').value;
    const equipements = document.getElementById('equipements').value;
    const prixChambre = document.getElementById('prixChambre').value;
    
    // Vérifier que tous les champs sont remplis
    if (!numChambre || !tailleChambre || !equipements || !prixChambre) {
        alert('Veuillez remplir tous les champs.');
        return;
    }

    // Créer l'objet de la nouvelle chambre
    const chambreData = {
        numero: numChambre,
        taille: tailleChambre,
        equipements: equipements,
        disponibilite: true,
        prix: prixChambre,
        etat: "DISPONIBLE" // Tu peux ajuster l'état selon tes besoins
    };

    // Envoyer la requête POST pour ajouter la chambre
    fetch('http://localhost:8080/api/chambres', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(chambreData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erreur lors de l\'ajout de la chambre');
        }
        return response.json();
    })
    .then(data => {
        alert('Chambre ajoutée avec succès');
        fetchChambres(); // Actualiser la liste après l'ajout
        document.getElementById('modalForm').classList.add('hidden'); // Fermer le formulaire modal
    })
    .catch(error => {
        console.error('Erreur lors de l\'ajout de la chambre :', error);
    });
}


document.addEventListener('DOMContentLoaded', () => {
fetchChambres();
fetchResidents();
});
// Ouvrir le formulaire modale
document.getElementById('openForm').addEventListener('click', function() {
    document.getElementById('modalForm').classList.remove('hidden');
});

// Fermer le formulaire modale
document.getElementById('closeForm').addEventListener('click', function() {
    document.getElementById('modalForm').classList.add('hidden');
});

// Soumettre le formulaire
document.getElementById('addChambreForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Empêche la soumission du formulaire

    // Appeler la fonction addChambre() pour ajouter la chambre
    addChambre();
});

