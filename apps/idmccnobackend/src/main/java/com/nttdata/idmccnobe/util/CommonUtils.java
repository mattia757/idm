package com.nttdata.idmccnobe.util;

import com.nttdata.idmccnobe.dto.CommonDto;
import com.nttdata.idmccnobe.enumeration.ErrorCode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author smartinenghi
 */
public class CommonUtils {
    
    public static final String DATE_FORMAT_ITA = "dd/MM/yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT_ITA);
    
    public static Date parseDate(String date) throws ParseException {
        return DATE_FORMATTER.parse(date);
    }

    public static String formatDate(Date date) {
        return DATE_FORMATTER.format(date);

    }
    
    public static String nullToEmpty(String param) {
        return (param == null ? "" : param);
    }
    
    //capitalize the first letter of the field name for retriving value of the field later
    public static String capitalize(String s) {
        if (s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static boolean isNullOrEmpty(String str) {
        return nullToEmpty(str).trim().isEmpty();
    }
    
    public static CommonDto setHeaderResultKO(ErrorCode errorCode){
        CommonDto headerDto=new CommonDto();
        headerDto.setErrorCode(errorCode.errorCode());
        headerDto.setErrorMessage(errorCode.errorMessage());
        headerDto.setResult(CommonDto.ACK_KO);
        return headerDto;
    }
    
    public static CommonDto setHeaderResultOK(){
        CommonDto headerDto=new CommonDto();
        headerDto.setErrorCode(null);
        headerDto.setErrorMessage("");
        headerDto.setResult(CommonDto.ACK_OK);
        return headerDto;
    }
}
