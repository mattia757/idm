// Variabile che indica se almeno un valore è stato modificato
var isValueChanged = false;

// Funzione per gestire il cambiamento di un valore
function handleValueChange() {
    isValueChanged = true;
}

// Aggiungo un listener per ogni elemento che potrebbe cambiare
var elementsToCheck = [
    'allSelectedNovacoop', 'allSelectedLiguria', 'allSelectedLombardia'
];

elementsToCheck.forEach(function (elementId) {
    var element = document.getElementById(elementId);
    if (element) {
        element.addEventListener('input', handleValueChange);
    }
});

var pdvSelectedForNovacoopCheckboxes = document.querySelectorAll('input[name="pdvSelectedForNovacoop"]');
pdvSelectedForNovacoopCheckboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', handleValueChange);
});

var pdvSelectedForLiguriaCheckboxes = document.querySelectorAll('input[name="pdvSelectedForLiguria"]');
pdvSelectedForLiguriaCheckboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', handleValueChange);
});

var pdvSelectedForLombardiaCheckboxes = document.querySelectorAll('input[name="pdvSelectedForLombardia"]');
pdvSelectedForLombardiaCheckboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', handleValueChange);
});

// Funzione per abilitare/disabilitare la dropdown in base allo stato del checkbox
function toggleDropdownNovacoop() {
    var checkbox = document.getElementById('allSelectedNovacoop');
    var dropdownButton = document.getElementById('pdvSelectedForNovacoopDropdown');

    // Disabilita/Abilita la dropdown in base allo stato del checkbox
    dropdownButton.disabled = checkbox.checked;
}

function toggleDropdownLiguria() {
    var checkbox = document.getElementById('allSelectedLiguria');
    var dropdownButton = document.getElementById('pdvSelectedForLiguriaDropdown');

    // Disabilita/Abilita la dropdown in base allo stato del checkbox
    dropdownButton.disabled = checkbox.checked;
}

function toggleDropdownLombardia() {
    var checkbox = document.getElementById('allSelectedLombardia');
    var dropdownButton = document.getElementById('pdvSelectedForLombardiaDropdown');

    // Disabilita/Abilita la dropdown in base allo stato del checkbox
    dropdownButton.disabled = checkbox.checked;
}

// Funzione per visualizzare i valori selezionati all'apertura della pagina
function showSelectedValues() {
    var listPdvIdForNovacoop = document.getElementById('listPdvIdForNovacoop');
    var pdvIdsNovacoop = [];

    document.querySelectorAll('input[name="pdvSelectedForNovacoop"]:checked').forEach(function (checkbox) {
        pdvIdsNovacoop.push(checkbox.id);
    });
    listPdvIdForNovacoop.innerText = pdvIdsNovacoop.length > 0 ? pdvIdsNovacoop.join(',') : '';
    
    var listPdvIdForLiguria = document.getElementById('listPdvIdForLiguria');
    var pdvIdsLiguria = [];

    document.querySelectorAll('input[name="pdvSelectedForLiguria"]:checked').forEach(function (checkbox) {
        pdvIdsLiguria.push(checkbox.id);
    });
    listPdvIdForLiguria.innerText = pdvIdsLiguria.length > 0 ? pdvIdsLiguria.join(',') : '';
    
    var listPdvIdForLombardia = document.getElementById('listPdvIdForLombardia');
    var pdvIdsLombardia = [];

    document.querySelectorAll('input[name="pdvSelectedForLombardia"]:checked').forEach(function (checkbox) {
        pdvIdsLombardia.push(checkbox.id);
    });
    listPdvIdForLombardia.innerText = pdvIdsLombardia.length > 0 ? pdvIdsLombardia.join(',') : '';
}

window.onload = function () {
    showSelectedValues();
    
    toggleDropdownNovacoop();
    toggleDropdownLiguria();
    toggleDropdownLombardia();

    // Aggiungi gli event listener per gestire il cambiamento dello stato del checkbox
    document.getElementById('allSelectedNovacoop').addEventListener('change', toggleDropdownNovacoop);
    document.getElementById('allSelectedLiguria').addEventListener('change', toggleDropdownLiguria);
    document.getElementById('allSelectedLombardia').addEventListener('change', toggleDropdownLombardia);
};

document.querySelectorAll('input[name="pdvSelectedForNovacoop"]').forEach(function (checkbox) {
    checkbox.addEventListener('change', showSelectedValues);
});

document.querySelectorAll('input[name="pdvSelectedForLiguria"]').forEach(function (checkbox) {
    checkbox.addEventListener('change', showSelectedValues);
});

document.querySelectorAll('input[name="pdvSelectedForLombardia"]').forEach(function (checkbox) {

    checkbox.addEventListener('change', showSelectedValues);
});

$(document).ready(function() {
    $('.select2').select2();
});

$("#saveCashbackPdvIds").click(function() {
    blockScreen();
    savePdvData();
});

function goToUrl() {
    
    var baseUrl = contextPath + "/cashback/ricerca";
    var url = baseUrl;
    window.location.href = url;
}

function savePdvData() {
    
    // Se nessun valore è stato modificato, esci dalla funzione
    if (!isValueChanged) {
        unblockScreen();
        $(".alert").alert('close');
        showAlert('Nessun dato cashback modificato', 'warning', '#alert-container');
        return;
    }
    
    var cashbackId = document.getElementById('cashbackId').value;
    
    // Novacoop
    var allSelectedNovacoop = getAllSelectedNovacoop();
    var listPdvIdForNovacoopDiv = document.getElementById('listPdvIdForNovacoop');
    var selectedPdvIdsNovacoop = listPdvIdForNovacoopDiv.innerText;  
//    if(allSelectedNovacoop == null && selectedPdvIdsNovacoop == null){
//        unblockScreen();
//        $(".alert").alert('close');
//        showAlert('Selezionare almeno un punto vendita per Novacoop', 'warning', '#alert-container');
//        return;
//    }
    // Liguria
    var allSelectedLiguria = getAllSelectedLiguria();
    var listPdvIdForLiguriaDiv = document.getElementById('listPdvIdForLiguria');
    var selectedPdvIdsLiguria = listPdvIdForLiguriaDiv.innerText;
//    if(allSelectedLiguria == null && selectedPdvIdsLiguria == null){
//        unblockScreen();
//        $(".alert").alert('close');
//        showAlert('Selezionare almeno un punto vendita per Liguria', 'warning', '#alert-container');
//        return;
//    }
    // Lombardia
    var allSelectedLombardia = getAllSelectedLombardia();
    var listPdvIdForLombardiaDiv = document.getElementById('listPdvIdForLombardia');
    var selectedPdvIdsLombardia = listPdvIdForLombardiaDiv.innerText;
//    if(allSelectedLombardia == null && selectedPdvIdsLombardia == null){
//        unblockScreen();
//        $(".alert").alert('close');
//        showAlert('Selezionare almeno un punto vendita per Lombardia', 'warning', '#alert-container');
//        return;
//    }
    
    // Effettua la chiamata Ajax
    $.ajax({
        type: "POST",
        url: contextPath + "/rest/cashback/updateCashbackPdv",
        data: {
            cashbackId : cashbackId,
            allSelectedNovacoop : allSelectedNovacoop,
            selectedPdvIdsNovacoop: selectedPdvIdsNovacoop,
            allSelectedLiguria: allSelectedLiguria,
            selectedPdvIdsLiguria: selectedPdvIdsLiguria,
            allSelectedLombardia: allSelectedLombardia,
            selectedPdvIdsLombardia: selectedPdvIdsLombardia,
        },
        success: function(data, message, response) {
            unblockScreen();
            if (response.responseText.includes("OK")) {
                $(".alert").alert('close');
                $("#returnPdv").click();
            } else {
                $(".alert").alert('close');
                showAlert('Aggiornamento dati PDV fallito', 'danger', '#alert-container');
            }
        },
        error: function(xhr, textStatus, errorThrown) {
            // Operazioni da eseguire in caso di errore
            unblockScreen();
            $(".alert").alert('close');
            showAlert('Aggiornamento dati PDV fallito', 'danger', '#alert-container');
        }
    });
}

function getAllSelectedNovacoop() {
    var allSelectedNovacoopCheckbox = document.getElementById('allSelectedNovacoop');
    return allSelectedNovacoopCheckbox.checked ? '1' : '0';
}

function getAllSelectedLiguria() {
    var allSelectedLiguriaCheckbox = document.getElementById('allSelectedLiguria');
    return allSelectedLiguriaCheckbox.checked ? '1' : '0';
}

function getAllSelectedLombardia() {
    var allSelectedLombardiaCheckbox = document.getElementById('allSelectedLombardia');
    return allSelectedLombardiaCheckbox.checked ? '1' : '0';
}
