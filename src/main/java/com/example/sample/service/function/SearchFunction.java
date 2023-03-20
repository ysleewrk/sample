package com.example.sample.service.function;

import com.example.sample.dto.HelloDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class SearchFunction implements Supplier<Object>  {


    public SearchFunction(HelloDto helloDto) {
    }

    @Override
    public Object get() {
        try {

            return null;

        } catch (Exception e) {
            log.info("IPayBillKeyStlmReq request error {}", e);
            return e;
        }
    }
}
