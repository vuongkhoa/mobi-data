package com.abcbank.voucherservice.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<F, S> {

    protected F factoryResponse;
    protected S service;

    @Autowired
    public BaseController(F factoryResponse, S service) {
        this.factoryResponse = factoryResponse;
        this.service = service;
    }

}
