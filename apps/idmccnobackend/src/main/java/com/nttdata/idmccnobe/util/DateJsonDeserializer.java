package com.nttdata.idmccnobe.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.log4j.Logger;

/**
 *
 * @author smartinenghi
 */

@Component
public class DateJsonDeserializer extends JsonDeserializer<Date> {
    
    protected Logger logger = Logger.getLogger(this.getClass());
    
    @Override
    public Date deserialize(JsonParser jpar, DeserializationContext descon) throws IOException, JsonProcessingException {
        String date = jpar.getText();
        Date dateSer = null;
        try {
            dateSer = CommonUtils.parseDate(date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return dateSer;
    }
    
}