/**
 * Module for displaying confirm dialog using Bootstrap
 *
 * @author smartinenghi
 */

(function (root, factory) {
	'use strict';

	if (typeof define === 'function' && define.amd) {
		define(['jquery'], function ($) {
			return (root.confirmDialog = factory($));
		});
	}
	else {
		root.confirmDialog = root.confirmDialog || factory(root.jQuery);
	}

}(this, function ($) {
	'use strict';

	/**
	 * Dialog DOM constructor
	 */
	function constructDialog($dialogConfirmParam, htmlContent) {
		// Deleting previous instance of the dialog
		if ($dialogConfirmParam) {
			$dialogConfirmParam.remove();
		}
		return $(htmlContent);
	}

	var $dialogConfirm; // Dialog object
        var $htmlContent = '<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'+
                            '<div class="modal-dialog" role="document">'+
                                 '<div class="modal-content">'+
                                     '<div class="modal-header">'+
                                         '<h5 class="modal-title"><i class="fas fa-exclamation-triangle mr-2"></i> {dialogTitle}</h5>'+
                                         '<button class="close" type="button" data-dismiss="modal" aria-label="Close">'+
                                             '<span aria-hidden="true">&times;</span>'+
                                         '</button>'+
                                     '</div>'+
                                     '<div class="modal-body">{dialogBody}</div>'+
                                     '<div class="modal-footer">'+
                                         '<button class="btn btn-secondary" type="button" data-dismiss="modal">{dialogNo}</button>'+
                                         '<a class="btn btn-primary" href="javascript:confirmDialog.hide();{dialogHref}">{dialogYes}</a>'+
                                     '</div>'+
                                '</div>'+
                             '</div>'+
                         '</div>';

	return {
		/**
		 * Opens our dialog
		 */
		show: function (dialogTitle, dialogBody, dialogHref, dialogYes, dialogNo) {
			// Assigning defaults
                        if (typeof dialogTitle === 'undefined') {
				dialogTitle = 'Confirm';
			}
                        if (typeof dialogBody === 'undefined') {
				dialogBody = 'Yes or no?';
			}
                        if (typeof dialogHref === 'undefined') {
				dialogHref = '#';
			}
                        if (typeof dialogYes === 'undefined') {
				dialogYes = 'Si';
			}
                        if (typeof dialogNo === 'undefined') {
				dialogNo = 'No';
			}
                        
                        var htmlContentTemp = $htmlContent.fmt({  dialogTitle: dialogTitle, 
                                                                  dialogBody: dialogBody, 
                                                                  dialogYes: dialogYes, 
                                                                  dialogNo: dialogNo,
                                                                  dialogHref: dialogHref});
                                                              

                        
			$dialogConfirm = constructDialog($dialogConfirm, htmlContentTemp);
                        
			// Opening dialog
			$dialogConfirm.modal();
		},
		/**
		 * Closes dialog
		 */
		hide: function () {
			if (typeof $dialogConfirm !== 'undefined') {
				$dialogConfirm.modal('hide');
			}
		}
	};

}));
