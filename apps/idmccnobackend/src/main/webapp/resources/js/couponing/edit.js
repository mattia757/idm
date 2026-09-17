// Gestione click "Indietro"
document.getElementById("returnCoupon").addEventListener("click", function() {
    
    var url = contextPath + "/couponing/ricerca";
    window.location.href = url;
});

// Conserva il riferimento al file selezionato anche quando il plugin filestyle
// modifica la rappresentazione grafica dell'input.
let selectedVoucherImageFile = null;
document.addEventListener("change", function(event) {
  if (event.target && event.target.id === "voucherImage") {
    selectedVoucherImageFile = event.target.files && event.target.files.length
            ? event.target.files[0]
            : null;
    console.info("Selezione immagine voucher aggiornata. immaginePresente="
            + (selectedVoucherImageFile !== null));
  }
});

document.querySelector('form.needs-validation').addEventListener('submit', async function(e) {
  // lascia fare la validazione nativa HTML5
  if (!this.checkValidity()) {
    this.classList.add('was-validated');   // stile bootstrap
    e.preventDefault();
    e.stopPropagation();
    return; // esco: niente AJAX se non valido
  }

  // valido: procedo in AJAX
  e.preventDefault(); // blocco il submit tradizionale

  const imageInput = document.getElementById("voucherImage");
  const imageFile = selectedVoucherImageFile
          || (imageInput && imageInput.files && imageInput.files.length ? imageInput.files[0] : null);
  if (imageFile && !isPngFile(imageFile)) {
    showAlert("Selezionare un'immagine valida in formato PNG", "danger", "#alert-container");
    return;
  }

  // allinea switch -> hidden PRIMA di leggere i valori
  syncAttivoToHidden();

  blockScreen();

  try {
    const fileInput = imageFile ? await readFileAsBase64(imageFile) : null;

    const voucherDto = {
    idVoucher: getValue("idVoucher"),
    idStateFk: getValue("idStateFk"),
    idType: getValue("idType"),
    headerMsg: getValue("headerMsg"),
    bcdEanDiscCoup8: getValue("bcdEanDiscCoup8"),
    valueType: getValue("valueType"),
    voucherType: getValue("voucherType"),
    coupCounter: getValue("coupCounter"),
    value: getValue("value"),
    redStartDate: getValue("redStartDate"),
    redEndDate: getValue("redEndDate"),
    commDescr: getValue("commDescr"),
    commType: getValue("commType"),
    redPromoType: getValue("redPromoType"),
    codice: getValue("codice"),
    link: getValue("link"),
    fileInput: fileInput,
    descrizioneWeb: getValue("descrizioneWeb"),
    messaggioCarta: getValue("messaggioCarta"),
    termsAndConditions: getValue("termsAndConditions"),
    tag: getValue("tag"),
    descriptionCrm: getValue("descriptionCrm"),
    startDateCrm: getValue("startDateCrm"),
    endDateCrm: getValue("endDateCrm"),
    noteCrm: getValue("noteCrm"),
    attivo: getValue("attivo")
    };

    console.info("Invio modifyVoucher. voucherId=" + voucherDto.idVoucher
            + ", immaginePresente=" + (voucherDto.fileInput !== null)
            + ", lunghezzaBase64=" + (voucherDto.fileInput ? voucherDto.fileInput.length : 0));

    $.ajax({
      type: "POST",
      url: contextPath + "/rest/couponing/modifyVoucher",
      contentType: "application/json",
      data: JSON.stringify(voucherDto),
      success: function(response) {
        unblockScreen();
        if (response && response.header && response.header.result === "OK") {
          setNextPageSuccessMessage("Salvataggio riuscito per il Coupon Id " + voucherDto.idVoucher + " - Titolo: " + voucherDto.headerMsg);
          goToUrl();
        } else {
          const errorMessage = response && response.header && response.header.errorMessage
                  ? response.header.errorMessage
                  : "Errore durante il salvataggio del voucher";
          showAlert(errorMessage, "danger", "#alert-container");
        }
      },
      error: function() {
        unblockScreen();
        showAlert("Errore durante il salvataggio del voucher", "danger", "#alert-container");
      }
    });
  } catch (error) {
    unblockScreen();
    showAlert("Errore durante la lettura dell'immagine selezionata", "danger", "#alert-container");
  }
});

function isPngFile(file) {
  const hasPngExtension = /\.png$/i.test(file.name || "");
  const hasValidMimeType = !file.type || file.type.toLowerCase() === "image/png";
  return hasPngExtension && hasValidMimeType;
}

function readFileAsBase64(file) {
  return new Promise(function(resolve, reject) {
    const reader = new FileReader();
    reader.onload = function() {
      const result = reader.result || "";
      const separatorIndex = result.indexOf(",");
      if (separatorIndex < 0) {
        reject(new Error("Formato immagine non valido"));
        return;
      }
      resolve(result.substring(separatorIndex + 1));
    };
    reader.onerror = function() {
      reject(reader.error || new Error("Lettura immagine fallita"));
    };
    reader.readAsDataURL(file);
  });
}

//// Gestione click "Salva"
//document.getElementById("saveCoupon").addEventListener("click", function() {
//    blockScreen();
//
//// assicurati che il valore sia allineato prima di leggere dal form
//  syncAttivoToHidden();
//
//
//    const voucherDto = {
//        idVoucher: getValue("idVoucher"),
//        idStateFk: getValue("idStateFk"),
//        idType: getValue("idType"),
//        headerMsg: getValue("headerMsg"),
//        bcdEanDiscCoup8: getValue("bcdEanDiscCoup8"),
//        valueType: getValue("valueType"),
//        voucherType: getValue("voucherType"),
//        coupCounter: getValue("coupCounter"),
//        value: getValue("value"),
//        redStartDate: getValue("redStartDate"),
//        redEndDate: getValue("redEndDate"),
//        commDescr: getValue("commDescr"),
//        commType: getValue("commType"),
//        redPromoType: getValue("redPromoType"),
//        descrizioneWeb: getValue("descrizioneWeb"),
//        messaggioCarta: getValue("messaggioCarta"),
//        termsAndConditions: getValue("termsAndConditions"),
//        tag: getValue("tag"),
//        descriptionCrm: getValue("descriptionCrm"),
//        startDateCrm: getValue("startDateCrm"),
//        endDateCrm: getValue("endDateCrm"),
//        noteCrm: getValue("noteCrm"),
//        attivo: getValue("attivo")
//    };
//
//    $.ajax({
//        type: "POST",
//        url: contextPath + "/rest/couponing/modifyVoucher",
//        contentType: "application/json",
//        data: JSON.stringify(voucherDto),
//        success: function(response) {
//            unblockScreen();
//            showAlert("Voucher modificato con successo", "success", "#alert-container");
//            goToUrl();
//        },
//        error: function(xhr) {
//            unblockScreen();
//            showAlert("Errore durante il salvataggio del voucher", "danger", "#alert-container");
//        }
//    });
//});

function getValue(id) {
    const el = document.getElementById(id);
    return el ? el.value.trim() : "";
}

function validateForm() {
    let isValid = true;
    document.querySelectorAll("form .form-control[required]").forEach(function(input) {
        if (!input.value.trim()) {
            input.classList.add("is-invalid");
            isValid = false;
        } else {
            input.classList.remove("is-invalid");
        }
    });
    return isValid;
}

function goToUrl() {
    var url = contextPath + "/couponing/ricerca";
    window.location.href = url;
}

function syncAttivoToHidden() {
  var switchEl = document.getElementById('activeSwitch');
  var hiddenEl = document.getElementById('attivo');
  var badgeEl  = document.getElementById('activeBadge');
  if (!switchEl || !hiddenEl) return;

  var isOn = !!switchEl.checked;
  hiddenEl.value = isOn ? 'ATTIVO' : 'INATTIVO';

  if (badgeEl) {
    badgeEl.textContent = isOn ? 'Attivo' : 'Inattivo';
    badgeEl.classList.toggle('btn-success',  isOn);
    badgeEl.classList.toggle('btn-danger',  !isOn);
  }
};

document.addEventListener('DOMContentLoaded', function () {
  // inizializza badge/hidden in base allo stato corrente
  syncAttivoToHidden();

  // aggiorna hidden+badge al toggle
  var switchEl = document.getElementById('activeSwitch');
  if (switchEl) {
    switchEl.addEventListener('change', syncAttivoToHidden);
  }
});
