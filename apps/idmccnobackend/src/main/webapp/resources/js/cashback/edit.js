// Variabile che indica se almeno un valore è stato modificato
var isValueChanged = false;

// Funzione per gestire il cambiamento di un valore
function handleValueChange() {
    isValueChanged = true;
}

// Aggiungo un listener per ogni elemento che potrebbe cambiare
var elementsToCheck = [
    'cashbackTitle', 'cashbackDescription', 'cashbackValue', 'status',
    'cashbackPublicationStartDate', 'cashbackPublicationEndDate',
    'conditionsDescription', 'conditionsValidityDescription',
    'accumulationDescription', 'accumulationType', 'accumulationStartDate',
    'accumulationEndDate', 'accumulationFlyerPdfLink',
    'accumulationFlyerPdfImageUrl', 'accumulationProductLinesDropdown',
    'fruitionDescription', 'fruitionType', 'fruitionStartDate',
    'fruitionEndDate', 'fruitionFlyerPdfLink', 'fruitionFlyerPdfImageUrl',
    'fruitionProductLinesDropdown'
];

elementsToCheck.forEach(function (elementId) {
    var element = document.getElementById(elementId);
    if (element) {
        element.addEventListener('input', handleValueChange);
    }
});

// Aggiungo un listener per ogni checkbox delle linee di prodotto per l'accumulo
var accumulationProductLinesCheckboxes = document.querySelectorAll('input[name="accumulationProductLines"]');
accumulationProductLinesCheckboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', handleValueChange);
});

// Aggiungo un listener per ogni checkbox delle linee di prodotto per l fruizione
var fruitionProductLinesCheckboxes = document.querySelectorAll('input[name="fruitionProductLines"]');
fruitionProductLinesCheckboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', handleValueChange);
});

// Funzione per visualizzare i valori selezionati all'apertura della pagina
function showSelectedValues() {
    var dropdownButton = document.getElementById('accumulationProductLinesDropdown');
    var accumulationSelectedLines = [];

    document.querySelectorAll('input[name="accumulationProductLines"]:checked').forEach(function (checkbox) {
        accumulationSelectedLines.push(checkbox.value);
    });

    dropdownButton.innerText = accumulationSelectedLines.length > 0 ? accumulationSelectedLines.join(',') : 'Seleziona le linee';

    var dropdownButton = document.getElementById('fruitionProductLinesDropdown');
    var fruitionSelectedLines = [];

    document.querySelectorAll('input[name="fruitionProductLines"]:checked').forEach(function (checkbox) {
        fruitionSelectedLines.push(checkbox.value);
    });

    dropdownButton.innerText = fruitionSelectedLines.length > 0 ? fruitionSelectedLines.join(',') : 'Seleziona le linee';
}

function handleAccumulationTypeChange() {
    var accumulationType = document.getElementById('accumulationType').value;
    var accumulationFlyerPdfLink = document.getElementById('accumulationFlyerPdfLink');
    var accumulationFlyerPdfImageUrl = document.getElementById('accumulationFlyerPdfImageUrl');
    var accumulationProductLinesDropdown = document.getElementById('accumulationProductLinesDropdown');

    if (accumulationType === '1') { // BUY
        // Disabilito i campi
        accumulationFlyerPdfLink.setAttribute('readonly', 'true');
        accumulationFlyerPdfImageUrl.setAttribute('readonly', 'true');
        accumulationProductLinesDropdown.setAttribute('disabled', 'true');
    } else if (accumulationType === '2' || accumulationType === '4'){ // PDF e FLYER
        // Abilito i campi
        accumulationFlyerPdfLink.removeAttribute('readonly');
        accumulationFlyerPdfImageUrl.removeAttribute('readonly');
        accumulationProductLinesDropdown.setAttribute('disabled', 'true');
    } else if (accumulationType === '3'){ // LINES
        // Abilito i campi
        accumulationFlyerPdfLink.removeAttribute('readonly');
        accumulationFlyerPdfImageUrl.removeAttribute('readonly');
        accumulationProductLinesDropdown.removeAttribute('disabled');
    }
}

function handleFruitionTypeChange() {
    var fruitionType = document.getElementById('fruitionType').value;
    var fruitionFlyerPdfLink = document.getElementById('fruitionFlyerPdfLink');
    var fruitionFlyerPdfImageUrl = document.getElementById('fruitionFlyerPdfImageUrl');
    var fruitionProductLinesDropdown = document.getElementById('fruitionProductLinesDropdown');

    if (fruitionType === '1') { // BUY
        // Disabilito i campi
        fruitionFlyerPdfLink.setAttribute('readonly', 'true');
        fruitionFlyerPdfImageUrl.setAttribute('readonly', 'true');
        fruitionProductLinesDropdown.setAttribute('disabled', 'true');
    } else if (fruitionType === '2' || fruitionType === '4'){ // PDF e FLYER
        // Abilito i campi
        fruitionFlyerPdfLink.removeAttribute('readonly');
        fruitionFlyerPdfImageUrl.removeAttribute('readonly');
        fruitionProductLinesDropdown.setAttribute('disabled', 'true');
    } else if (fruitionType === '3'){ // LINES
        // Abilito i campi
        fruitionFlyerPdfLink.removeAttribute('readonly');
        fruitionFlyerPdfImageUrl.removeAttribute('readonly');
        fruitionProductLinesDropdown.removeAttribute('disabled');
    }
}

// Chiamo la funzione all'apertura della pagina
window.onload = function () {
    showSelectedValues();
    handleAccumulationTypeChange();
    handleFruitionTypeChange();
    
    var cashbackId = document.getElementById('id').value;
    if(cashbackId !== "***"){
        var cashbackTitle = document.getElementById('cashbackTitleHidden').value;
        document.getElementById('cashbackTitle').value = cashbackTitle;
        var cashbackDescription = document.getElementById('cashbackDescriptionHidden').value;
        document.getElementById('cashbackDescription').value = cashbackDescription;
        var cashbackValue = document.getElementById('cashbackValueHidden').value;
        document.getElementById('cashbackValue').value = cashbackValue;
        var status = document.getElementById('statusHidden').value;
        var statusSelect = document.getElementById('status');
        if (status === "1.0") {
            statusSelect.querySelector('option[value="1.0"]').selected = true;
        } else {
            statusSelect.querySelector('option[value="0"]').selected = true;
        }
        var cashbackPublicationStartDate = document.getElementById('cashbackPublicationStartDateHidden').value;
        document.getElementById('cashbackPublicationStartDate').value = cashbackPublicationStartDate;
        var cashbackPublicationEndDate = document.getElementById('cashbackPublicationEndDateHidden').value;
        document.getElementById('cashbackPublicationEndDate').value = cashbackPublicationEndDate;
        var conditionsDescription = document.getElementById('conditionsDescriptionHidden').value;
        document.getElementById('conditionsDescription').value = conditionsDescription;
        var conditionsValidityDescription = document.getElementById('conditionsValidityDescriptionHidden').value;
        document.getElementById('conditionsValidityDescription').value = conditionsValidityDescription;
        var accumulationDescription = document.getElementById('accumulationDescriptionHidden').value;
        document.getElementById('accumulationDescription').value = accumulationDescription;
        var accumulationStartDate = document.getElementById('accumulationStartDateHidden').value;
        document.getElementById('accumulationStartDate').value = accumulationStartDate;
        var accumulationEndDate = document.getElementById('accumulationEndDateHidden').value;
        document.getElementById('accumulationEndDate').value = accumulationEndDate;
        var accumulationFlyerPdfLink = document.getElementById('accumulationFlyerPdfLinkHidden').value;
        document.getElementById('accumulationFlyerPdfLink').value = accumulationFlyerPdfLink;
        var accumulationFlyerPdfImageUrl = document.getElementById('accumulationFlyerPdfImageUrlHidden').value;
        document.getElementById('accumulationFlyerPdfImageUrl').value = accumulationFlyerPdfImageUrl;
        var fruitionDescription = document.getElementById('fruitionDescriptionHidden').value;
        document.getElementById('fruitionDescription').value = fruitionDescription;
        var fruitionStartDate = document.getElementById('fruitionStartDateHidden').value;
        document.getElementById('fruitionStartDate').value = fruitionStartDate;
        var fruitionEndDate = document.getElementById('fruitionEndDateHidden').value;
        document.getElementById('fruitionEndDate').value = fruitionEndDate;
        var fruitionFlyerPdfLink = document.getElementById('fruitionFlyerPdfLinkHidden').value;
        document.getElementById('fruitionFlyerPdfLink').value = fruitionFlyerPdfLink;
        var fruitionFlyerPdfImageUrl = document.getElementById('fruitionFlyerPdfImageUrlHidden').value;
        document.getElementById('fruitionFlyerPdfImageUrl').value = fruitionFlyerPdfImageUrl;
    }

};

document.querySelectorAll('input[name="accumulationProductLines"]').forEach(function (checkbox) {
    checkbox.addEventListener('change', showSelectedValues);
});

document.querySelectorAll('input[name="fruitionProductLines"]').forEach(function (checkbox) {
    checkbox.addEventListener('change', showSelectedValues);
});

document.getElementById('accumulationType').addEventListener('change', handleAccumulationTypeChange);
document.getElementById('fruitionType').addEventListener('change', handleFruitionTypeChange);

$("#saveCashback").click(function() {
    blockScreen();
    saveCashback();
});

function createDateObject(dateString) {
    var parts = dateString.split("/");
    // Il mese -1 perché i mesi in JavaScript vanno da 0 a 11
    return new Date(parts[2], parts[1] - 1, parts[0]);
}

function isDateValid() {
    // Valori dai campi date del cashback
    var cashbackPublicationStartDate = document.getElementById("cashbackPublicationStartDate").value;
    var cashbackPublicationEndDate = document.getElementById("cashbackPublicationEndDate").value;
    var accumulationStartDate = document.getElementById("accumulationStartDate").value;
    var accumulationEndDate = document.getElementById("accumulationEndDate").value;
    var fruitionStartDate = document.getElementById("fruitionStartDate").value;
    var fruitionEndDate = document.getElementById("fruitionEndDate").value;

    var dateStrings = [
        cashbackPublicationStartDate,
        cashbackPublicationEndDate,
        accumulationStartDate,
        accumulationEndDate,
        fruitionStartDate,
        fruitionEndDate
    ];

    // Utilizzo una regex per verificare il formato dd/mm/yyyy
    var dateRegex = /^\d{2}\/\d{2}\/\d{4}$/;

    for (var i = 0; i < dateStrings.length; i++) {
        if (!dateRegex.test(dateStrings[i])) {
            return false;
        }

        // Verifico la validità della data
        var parts = dateStrings[i].split("/");
        var day = parseInt(parts[0], 10);
        var month = parseInt(parts[1], 10);
        var year = parseInt(parts[2], 10);

        if (isNaN(day) || isNaN(month) || isNaN(year)) {
            return false;
        }

        var maxDaysInMonth = new Date(year, month, 0).getDate();

        if (day < 1 || day > maxDaysInMonth || month < 1 || month > 12) {
            return false;
        }
    }

    // Aggiungi i controlli aggiuntivi
    var cashbackPublicationStartDateObj = createDateObject(cashbackPublicationStartDate);
    var accumulationStartDateObj = createDateObject(accumulationStartDate);
    var accumulationEndDateObj = createDateObject(accumulationEndDate);
    var fruitionStartDateObj = createDateObject(fruitionStartDate);
    var fruitionEndDateObj = createDateObject(fruitionEndDate);
    var cashbackPublicationEndDateObj = createDateObject(cashbackPublicationEndDate);

    // cashbackPublicationStartDate deve essere minore di accumulationStartDate
    if (cashbackPublicationStartDateObj > accumulationStartDateObj) {
        return false;
    }

    // accumulationStartDate deve essere minore di accumulationEndDate
    if (accumulationStartDateObj > accumulationEndDateObj) {
        return false;
    }

    // accumulationEndDate deve essere minore di fruitionStartDate
    if (accumulationEndDateObj > fruitionStartDateObj) {
        return false;
    }

    // fruitionStartDate deve essere minore di fruitionEndDate
    if (fruitionStartDateObj > fruitionEndDateObj) {
        return false;
    }

    // fruitionEndDate deve essere maggiore di cashbackPublicationEndDate
    if (fruitionEndDateObj > cashbackPublicationEndDateObj) {
        return false;
    }

    return true;
}

function validateForm() {
    // Recupera tutti gli elementi del modulo
    var formElements = document.forms["form"].elements;

    // Flag per indicare se il modulo è valido
    var isValid = true;

    // Itera attraverso gli elementi del modulo
    for (var i = 0; i < formElements.length; i++) {
        // Verifica solo gli elementi richiesti
        if (formElements[i].hasAttribute("required")) {
            // Controlla se il campo richiesto è compilato
            if (!formElements[i].value.trim()) {
                isValid = false;
                // Puoi anche aggiungere logica per evidenziare il campo non valido, ad esempio cambiando il colore del bordo.
                formElements[i].style.borderColor = "red";
            } else {
                // Ripristina eventuali stili precedenti
                formElements[i].style.borderColor = "";
            }
        }
    }

    // Restituisci la validità del modulo
    return isValid;
}

function goToUrl() {
    
    var baseUrl = contextPath + "/cashback/ricerca";
    var url = baseUrl;
    window.location.href = url;
}

function saveCashback() {
    
    
    // Se nessun valore è stato modificato, esco dalla funzione
    if (!isValueChanged) {
        unblockScreen();
        $(".alert").alert('close');
        showAlert('Nessun dato cashback modificato', 'warning', '#alert-container');
        return;
    }
    
    var isFormValid = validateForm();
    if (!isFormValid) {
        unblockScreen();
        $(".alert").alert('close');
        showAlert('Compila i campi obbligatori', 'danger', '#alert-container');
        return;
    }
    
    // Controllo il formato delle date usando isDateValid
    var resultDate = isDateValid();
    if (!resultDate) {
        unblockScreen();
        $(".alert").alert('close');
        showAlert('Le date devono essere nel formato dd/mm/yyyy e valorizzate correttamente', 'danger', '#alert-container');
        return;
    }
    
    // Recupero i valori dei campi di input
    var id = document.getElementById('id').value;
    var cashbackTitle = document.getElementById('cashbackTitle').value ;
    var cashbackDescription = document.getElementById('cashbackDescription').value;
    var cashbackValue = document.getElementById('cashbackValue').value;
    var status = parseInt(document.getElementById('status').value, 10);
    var cashbackPublicationStartDate = document.getElementById('cashbackPublicationStartDate').value;
    var cashbackPublicationEndDate = document.getElementById('cashbackPublicationEndDate').value;
    var conditionsDescription = document.getElementById('conditionsDescription').value;
    var conditionsValidityDescription = document.getElementById('conditionsValidityDescription').value;
    // Accumulation
    var accumulationDescription = document.getElementById('accumulationDescription').value;
    var accumulationType = document.getElementById('accumulationType').value;
    var accumulationStartDate = document.getElementById('accumulationStartDate').value;
    var accumulationEndDate = document.getElementById('accumulationEndDate').value;
    var accumulationFlyerPdfLink = document.getElementById('accumulationFlyerPdfLink').value;
    var accumulationFlyerPdfImageUrl = document.getElementById('accumulationFlyerPdfImageUrl').value;
    var accumulationProductLines = document.getElementById('accumulationProductLinesDropdown').textContent;
    // Fruition
    var fruitionDescription = document.getElementById('fruitionDescription').value;
    var fruitionType = document.getElementById('fruitionType').value;
    var fruitionStartDate = document.getElementById('fruitionStartDate').value;
    var fruitionEndDate = document.getElementById('fruitionEndDate').value;
    var fruitionFlyerPdfLink = document.getElementById('fruitionFlyerPdfLink').value;
    var fruitionFlyerPdfImageUrl = document.getElementById('fruitionFlyerPdfImageUrl').value;
    var fruitionProductLines = document.getElementById('fruitionProductLinesDropdown').textContent;

    // Effettuo la chiamata Ajax
    $.ajax({
        type: "POST",
        url: contextPath + "/rest/cashback/updateCashback",
        data: {
            id : id,
            cashbackTitle : cashbackTitle,
            cashbackDescription : cashbackDescription,
            cashbackValue : cashbackValue,
            status : status,
            cashbackPublicationStartDate : cashbackPublicationStartDate,
            cashbackPublicationEndDate : cashbackPublicationEndDate,
            conditionsDescription : conditionsDescription,
            conditionsValidityDescription : conditionsValidityDescription,
            accumulationDescription : accumulationDescription,
            accumulationType : accumulationType,
            accumulationStartDate : accumulationStartDate,
            accumulationEndDate : accumulationEndDate,
            accumulationFlyerPdfLink : accumulationFlyerPdfLink,
            accumulationFlyerPdfImageUrl : accumulationFlyerPdfImageUrl,
            accumulationProductLines : accumulationProductLines,
            fruitionDescription : fruitionDescription,
            fruitionType : fruitionType,
            fruitionStartDate : fruitionStartDate,
            fruitionEndDate : fruitionEndDate,
            fruitionFlyerPdfLink : fruitionFlyerPdfLink,
            fruitionFlyerPdfImageUrl : fruitionFlyerPdfImageUrl,
            fruitionProductLines : fruitionProductLines
        },
        success: function(data, message, response) {
            unblockScreen();
            $(".alert").alert('close');
            if (data && data.header && data.header.result === "OK") {
                setNextPageSuccessMessage("Salvataggio riuscito per il Cashback Id " + id + " - Titolo: " + cashbackTitle);
                goToUrl();
            } else {
                showAlert(data && data.header && data.header.errorMessage ? data.header.errorMessage : "Aggiornamento cashback fallito", "danger", "#alert-container");
            }
        },
        error: function(xhr, textStatus, errorThrown) {
            // Operazioni da eseguire in caso di errore
            unblockScreen();
            $(".alert").alert('close');
            showAlert('Aggiornamento cashback fallito', 'danger', '#alert-container');
        }
    });
}

function resetForm() {
    // Pulisci campi di input
    var inputFields = document.querySelectorAll('input[type="text"], input[type="number"]');
    for (var i = 0; i < inputFields.length; i++) {
        inputFields[i].value = '';
    }

    // Deseleziona checkbox
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var j = 0; j < checkboxes.length; j++) {
        checkboxes[j].checked = false;
    }
    
    // Cancella testo button dropdown
    document.getElementById('accumulationProductLinesDropdown').innerText = 'Seleziona le linee';
    document.getElementById('fruitionProductLinesDropdown').innerText = 'Seleziona le linee';
}

$("#saveAnagCashback").click(function() {
    blockScreen();
    saveNewCashback();
});

function saveNewCashback() {
    
    
    var isFormValid = validateForm();
    if (!isFormValid) {
        unblockScreen();
        $(".alert").alert('close');
        showAlert('Compila i campi obbligatori', 'danger', '#alert-container');
        return;
    }
    
    // Controllo il formato delle date usando isDateValid
    var resultDate = isDateValid();
    if (!resultDate) {
        unblockScreen();
        $(".alert").alert('close');
        showAlert('Le date devono essere nel formato dd/mm/yyyy e valorizzate correttamente', 'danger', '#alert-container');
        return;
    }
    
    // Recupero i valori dei campi di input
    var id = document.getElementById('id').value;
    var cashbackTitle = document.getElementById('cashbackTitle').value;
    var cashbackDescription = document.getElementById('cashbackDescription').value;
    var cashbackValue = document.getElementById('cashbackValue').value;
    var status = parseInt(document.getElementById('status').value, 10);
    var cashbackPublicationStartDate = document.getElementById('cashbackPublicationStartDate').value;
    var cashbackPublicationEndDate = document.getElementById('cashbackPublicationEndDate').value;
    var conditionsDescription = document.getElementById('conditionsDescription').value;
    var conditionsValidityDescription = document.getElementById('conditionsValidityDescription').value;
    // Accumulation
    var accumulationDescription = document.getElementById('accumulationDescription').value;
    var accumulationType = document.getElementById('accumulationType').value;
    var accumulationStartDate = document.getElementById('accumulationStartDate').value;
    var accumulationEndDate = document.getElementById('accumulationEndDate').value;
    var accumulationFlyerPdfLink = document.getElementById('accumulationFlyerPdfLink').value;
    var accumulationFlyerPdfImageUrl = document.getElementById('accumulationFlyerPdfImageUrl').value;
    var accumulationProductLines = document.getElementById('accumulationProductLinesDropdown').textContent;
    // Fruition
    var fruitionDescription = document.getElementById('fruitionDescription').value;
    var fruitionType = document.getElementById('fruitionType').value;
    var fruitionStartDate = document.getElementById('fruitionStartDate').value;
    var fruitionEndDate = document.getElementById('fruitionEndDate').value;
    var fruitionFlyerPdfLink = document.getElementById('fruitionFlyerPdfLink').value;
    var fruitionFlyerPdfImageUrl = document.getElementById('fruitionFlyerPdfImageUrl').value;
    var fruitionProductLines = document.getElementById('fruitionProductLinesDropdown').textContent;

    // Effettuo la chiamata Ajax
    $.ajax({
        type: "POST",
        url: contextPath + "/rest/cashback/saveCashback",
        data: {
            id : null, 
            cashbackTitle : cashbackTitle,
            cashbackDescription : cashbackDescription,
            cashbackValue : cashbackValue,
            status : status,
            cashbackPublicationStartDate : cashbackPublicationStartDate,
            cashbackPublicationEndDate : cashbackPublicationEndDate,
            conditionsDescription : conditionsDescription,
            conditionsValidityDescription : conditionsValidityDescription,
            accumulationDescription : accumulationDescription,
            accumulationType : accumulationType,
            accumulationStartDate : accumulationStartDate,
            accumulationEndDate : accumulationEndDate,
            accumulationFlyerPdfLink : accumulationFlyerPdfLink,
            accumulationFlyerPdfImageUrl : accumulationFlyerPdfImageUrl,
            accumulationProductLines : accumulationProductLines,
            fruitionDescription : fruitionDescription,
            fruitionType : fruitionType,
            fruitionStartDate : fruitionStartDate,
            fruitionEndDate : fruitionEndDate,
            fruitionFlyerPdfLink : fruitionFlyerPdfLink,
            fruitionFlyerPdfImageUrl : fruitionFlyerPdfImageUrl,
            fruitionProductLines : fruitionProductLines
        },
        success: function(data, message, response) {
            unblockScreen();
            $(".alert").alert('close');
            if (data && data.header && data.header.result === "OK") {
                var cashbackIdNew = data.payload.cashbackIdNew;
                setNextPageSuccessMessage("Salvataggio riuscito per il Cashback Id " + cashbackIdNew + " - Titolo: " + cashbackTitle);
                goToUrl();
            } else {
                showAlert(data && data.header && data.header.errorMessage ? data.header.errorMessage : "Creazione cashback fallita", "danger", "#alert-container");
            }
        },
        error: function(xhr, textStatus, errorThrown) {
            // Operazioni da eseguire in caso di errore
            unblockScreen();
            $(".alert").alert('close');
            showAlert('Creazione cashback fallita', 'danger', '#alert-container');
        }
    });
}

