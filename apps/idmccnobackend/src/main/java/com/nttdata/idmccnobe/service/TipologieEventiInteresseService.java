package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.TipologieEventiInteresseDao;
import com.nttdata.idmccnobe.dto.TipologiaEventoInteresseDto;
import com.nttdata.idmccnobe.model.CcnoTipologieEventiInteresse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TipologieEventiInteresseService")
@Transactional
public class TipologieEventiInteresseService extends AbstractService {

    @Autowired
    private TipologieEventiInteresseDao tipologieEventiInteresseDao;

    public List<TipologiaEventoInteresseDto> findAll() {
        List<CcnoTipologieEventiInteresse> entities = tipologieEventiInteresseDao.findAllOrdered();
        List<TipologiaEventoInteresseDto> result = new ArrayList<>();
        for (CcnoTipologieEventiInteresse entity : entities) {
            result.add(new TipologiaEventoInteresseDto(entity.getIdInteresse(), entity.getDescription()));
        }
        return result;
    }

    public Map<String, String> getDescriptionMap() {
        Map<String, String> result = new LinkedHashMap<>();
        for (TipologiaEventoInteresseDto item : findAll()) {
            result.put(item.getIdInteresse(), item.getDescription());
        }
        return result;
    }
}
