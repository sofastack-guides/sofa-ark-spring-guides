package com.alipay.sofa.sofaarkspringguides.impl;

import com.alipay.sofa.facade.SampleService;
import org.springframework.stereotype.Service;

/**
 * @author: yuanyuan
 * @date: 2023/9/26 11:53 上午
 */
@Service
public class SampleServiceImplNew implements SampleService {

    @Override
    public String service() {
        return "New Sample Service";
    }
}
