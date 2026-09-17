package com.nttdata.idmccnobe.enumeration;

import com.nttdata.idmccnobe.dao.ErrorCodeDao;

/**
 *
 * @author smartinenghi
 */
public enum ErrorCode {
    ALL_INPUT_REQUIRED(409, ErrorCodeDao.getErrorMessage("generic", 409)),
    EXISTING_USERNAME(408, ErrorCodeDao.getErrorMessage("generic", 408)),
    PASSWORD_WITH_BLANK_SPACES(407, ErrorCodeDao.getErrorMessage("generic", 407)),
    PASSWORD_NOT_SECURE(406, ErrorCodeDao.getErrorMessage("generic", 406)),
    WRONG_CURRENT_PASSWORD(405, ErrorCodeDao.getErrorMessage("generic", 405)),
    PASSWORD_DOESNT_MATCH(404, ErrorCodeDao.getErrorMessage("generic", 404)),
    FIELD_NOT_VALID_REPEAT_NEW_PASSWORD(403, ErrorCodeDao.getErrorMessage("generic", 403)),
    FIELD_NOT_VALID_NEW_PASSWORD(402, ErrorCodeDao.getErrorMessage("generic", 402)),
    FIELD_NOT_VALID_CURRENT_PASSWORD(401, ErrorCodeDao.getErrorMessage("generic", 401)),
    GENERIC_ERROR(17, ErrorCodeDao.getErrorMessage("generic", 17));

    private final int errorCode;
    private final String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    

    public int errorCode() {
        return errorCode;
    }
    
    public String errorMessage() {
        return errorMessage;
    }
    
    @Override
    public String toString() {
        return "ErrorCode{" + "errorCode=" + errorCode + ", errorMessage=" + errorMessage + "}";
    }
    
}
