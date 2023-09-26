package com.alipay.sofa.base.impl;

import com.alipay.sofa.base.facade.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
    private static Logger LOGGER = LoggerFactory.getLogger(SampleServiceImpl.class);

    @Override
    public String service() {
        LOGGER.info("web test: into a service");

        return "A Sample Service";
    }
}
