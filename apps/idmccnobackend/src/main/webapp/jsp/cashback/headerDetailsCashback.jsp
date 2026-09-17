<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<div class="card mb-3">  
    <div class="card-header">Anagrafica Cashback</div>
    <div class="card-body">
        <div class="col-sm">              
            <div class="form-group row">
                <label class="col-sm-2 col-form-label form-label-bg" for="id">Cashback Id</label>
                <div class="col-sm">
                    <input type="text" class="form-control-plaintext" readonly id="id" value="${cashback.cashbackid}">
                </div>
            </div>    
                
            <div class="form-group row">
                <label class="col-sm-2 col-form-label form-label-bg" for="cashbackTitle">Titolo</label>
                <div class="col-sm">
                    <input type="text" class="form-control" id="cashbackTitle" name="cashbackTitle" value="${cashback.cashbacktitle}" readonly>
                </div>
                <label class="col-sm-2 col-form-label form-label-bg" for="cashbackDescription">Descrizione</label>
                <div class="col-sm">
                    <input type="text" class="form-control" id="cashbackDescription" name="cashbackDescription" value="${cashback.cashbackdescription}" readonly>
                </div>
            </div>
        </div>
    </div>
</div>    


