/**
 * Module for displaying info dialog using Bootstrap
 *
 * @author smartinenghi
 */

(function (root, factory) {
	'use strict';

	if (typeof define === 'function' && define.amd) {
		define(['jquery'], function ($) {
			return (root.infoDialog = factory($));
		});
	}
	else {
		root.infoDialog = root.infoDialog || factory(root.jQuery);
	}

}(this, function ($) {
	'use strict';

	/**
	 * Dialog DOM constructor
	 */
	function constructDialog($dialogInfoParam, htmlContent) {
		// Deleting previous instance of the dialog
		if ($dialogInfoParam) {
			$dialogInfoParam.remove();
		}
		return $(htmlContent);
	}

	var $dialogInfo; // Dialog object
        var $htmlContent = '<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'+
                            '<div class="modal-dialog" role="document">'+
                                 '<div class="modal-content">'+
                                     '<div class="modal-header">'+
                                         '<h5 class="modal-title"><i class="fas fa-info-circle mr-2"></i> {dialogTitle}</h5>'+
                                         '<button class="close" type="button" data-dismiss="modal" aria-label="Close">'+
                                             '<span aria-hidden="true">&times;</span>'+
                                         '</button>'+
                                     '</div>'+
                                     '<div class="modal-body">{dialogBody}</div>'+
                                     '<div class="modal-footer">'+
                                         '<button class="btn btn-primary" type="button" data-dismiss="modal" onclick="{dialogButtonCallback}">{dialogButtonLabel}</button>'+
                                     '</div>'+
                                '</div>'+
                             '</div>'+
                         '</div>';

	return {
		/**
		 * Opens our dialog
		 */
		show: function (dialogTitle, dialogBody, dialogButtonLabel, dialogButtonCallback) {
			// Assigning defaults
                        if (typeof dialogTitle === 'undefined') {
				dialogTitle = 'Info';
			}
                        if (typeof dialogBody === 'undefined') {
				dialogBody = 'Info text';
			}
                        if (typeof dialogButtonLabel === 'undefined') {
				dialogButtonLabel = 'OK';
			}
                        if (typeof dialogButtonCallback === 'undefined') {
				dialogButtonCallback = '';
			}
                        
                        var htmlContentTemp = $htmlContent.fmt({  dialogTitle: dialogTitle, 
                                                                  dialogBody: dialogBody, 
                                                                  dialogButtonLabel: dialogButtonLabel,
                                                                  dialogButtonCallback: dialogButtonCallback});
                        
			$dialogInfo = constructDialog($dialogInfo, htmlContentTemp);
                        
			// Opening dialog
			$dialogInfo.modal();
		},
		/**
		 * Closes dialog
		 */
		hide: function () {
			if (typeof $dialogInfo !== 'undefined') {
				$dialogInfo.modal('hide');
			}
		}
	};

}));
