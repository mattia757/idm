package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value = {"/refreshCache"}, method = RequestMethod.GET)
    @ResponseBody
    public String clearParametersCache(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile) {
        if (userProfile == null || !"ADMIN".equalsIgnoreCase(userProfile.getRole())) {
            return "Operazione non autorizzata";
        }
        int cleared = 0;
        for (String name : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(name);
            if (cache != null) {
                cache.clear();
                cleared++;
            }
        }
        return "Cleared " + cleared + " caches: " + String.join(", ", cacheManager.getCacheNames());
    }
}
