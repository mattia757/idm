package com.nttdata.idmccnobe.controller;

import static com.nttdata.idmccnobe.controller.AbstractRestController.URI_REST;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(URI_REST)
public abstract class AbstractRestController {
    protected static final String URI_REST = "/rest";
    protected Logger logger = Logger.getLogger(this.getClass());
}
