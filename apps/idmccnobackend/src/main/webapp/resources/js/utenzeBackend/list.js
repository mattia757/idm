var dataTableUtenzeBackend = $('#dataTableUtenzeBackend').DataTable({
    language: {
        url: contextPath+"/resources/vendor/datatables/italian.json"
    },
    searching: false,
    autoWidth: false,
    columnDefs: [
        {
            targets: 0, orderable: false, width: '5%'
        }
    ],
    fixedColumns: true
});

$("#buttonSearch").click(function() {
    blockScreen();
    search();
});

$("form#searchBeUsersForm :input").each(function(){
    var input = $(this);
    input.on('keypress', function(e){
        if (e.keyCode === 13){
            e.keyCode = 188;
            e.preventDefault();
            blockScreen();
            search();
        };
    });
});

function search() {
    
    var username = $("#username").val();
    var coop = $("#coop").val();
    var role = $("#userRole").val();
    var customerCancellation = $("#customerCancellation").val();
    
    $.ajax({
        type: "GET",
        url: contextPath+"/usersBe/search",
        data: {
            username: username,
            coop: coop,
            role: role,
            customerCancellation: customerCancellation
        },

        success: function(data, message, response) {
            unblockScreen();
            utenzeRenderTable(dataTableUtenzeBackend, data, message, response);
        }
    });
}

function utenzeRenderTable(dataTableUtenzeBackend, data, message, response) {
    dataTableUtenzeBackend.clear();
    if (data.header.result === 'OK') {
//        var userProfileUsername = data.payload.userProfileUsername;
        $.each(data.payload.beUsers, function(ind, obj){
            dataTableUtenzeBackend.row.add([
                obj.username !== data.payload.userProfileUsername ? getIconWithConfirmAndAction("Cancellazione utenza", "Sei sicuro di voler cancellare l'utente '"+obj.username+"'?", "deleteUserBe('"+obj.id+"','"+obj.username+"');", "trash", "Elimina") : "",
                obj.username === data.payload.userProfileUsername ? obj.username+" (tu)" : obj.username,
                obj.role,
                obj.coop,
                obj.customerCancellation
            ]);
        });
    } else {
        $(".alert").alert('close');
        showAlert("Errore: "+data.header.errorMessage, 'danger', '#alert-container');
    }
    dataTableUtenzeBackend.draw();
}

function deleteUserBe(id,username) {
    
    ajaxCall(contextPath+"/usersBe/delete",
        { 
            id: id,
            username: username,
        },
        function() {
            showAlert('Utenza eliminata con successo.', 'success', '#alert-container');
            search();
        }
    );
}