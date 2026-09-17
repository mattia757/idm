package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.PartecipazioneInteresseDao;
import com.nttdata.idmccnobe.dto.PartecipazioneInteresseDto;
import com.nttdata.idmccnobe.dto.PartecipazioneInteresseSearchRequest;
import com.nttdata.idmccnobe.dto.TipologiaEventoInteresseDto;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.model.CcnoPartecipazioneInteresse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("PartecipazioneInteresseService")
@Transactional
public class PartecipazioneInteresseService extends AbstractService {

    @Autowired
    private PartecipazioneInteresseDao partecipazioneInteresseDao;

    @Autowired
    private TipologieEventiInteresseService tipologieEventiInteresseService;

    public List<PartecipazioneInteresseDto> searchPartecipazioni(PartecipazioneInteresseSearchRequest request, UserProfile userProfile) {
        normalizeCoopFilter(request, userProfile);
        List<CcnoPartecipazioneInteresse> partecipazioni = hasSearchFilter(request)
                ? partecipazioneInteresseDao.searchPartecipazioni(request)
                : partecipazioneInteresseDao.searchAllPartecipazioni();
        return toDtoList(partecipazioni);
    }

    public List<TipologiaEventoInteresseDto> findAllTipologieEvento() {
        return tipologieEventiInteresseService.findAll();
    }

    public void deletePartecipazione(Long id) {
        partecipazioneInteresseDao.deleteById(id);
    }

    public int deletePartecipazioniByUserId(String userId) {
        return partecipazioneInteresseDao.deleteByUserId(userId);
    }

    public boolean hasSearchFilter(PartecipazioneInteresseSearchRequest request) {
        return request != null
                && (StringUtils.hasText(request.getNome())
                || StringUtils.hasText(request.getCognome())
                || StringUtils.hasText(request.getEmail())
                || StringUtils.hasText(request.getCoopId())
                || StringUtils.hasText(request.getDataEventoFrom())
                || StringUtils.hasText(request.getDataEventoTo())
                || StringUtils.hasText(request.getTipologiaEvento()));
    }

    private void normalizeCoopFilter(PartecipazioneInteresseSearchRequest request, UserProfile userProfile) {
        if (request == null) {
            return;
        }
        if (userProfile == null || userProfile.getIdCooperativa() == null) {
            return;
        }
        Integer idCooperativa = userProfile.getIdCooperativa();
        if (idCooperativa.intValue() != 0) {
            request.setCoopId(String.valueOf(idCooperativa));
        }
    }

    private List<PartecipazioneInteresseDto> toDtoList(List<CcnoPartecipazioneInteresse> partecipazioni) {
        List<PartecipazioneInteresseDto> result = new ArrayList<>();
        Map<String, String> tipologieMap = tipologieEventiInteresseService.getDescriptionMap();
        if (partecipazioni != null) {
            for (CcnoPartecipazioneInteresse partecipazione : partecipazioni) {
                String tipologiaDescription = tipologieMap.containsKey(partecipazione.getTipologiaEvento())
                        ? tipologieMap.get(partecipazione.getTipologiaEvento())
                        : partecipazione.getTipologiaEvento();
                result.add(partecipazione.toDto(tipologiaDescription));
            }
        }
        return result;
    }
}
