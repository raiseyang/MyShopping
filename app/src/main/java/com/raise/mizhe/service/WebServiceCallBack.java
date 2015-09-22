package com.raise.mizhe.service;

/**
 * Created by yu on 2015/9/2.
 */
public interface WebServiceCallBack<Type> {
    void on_success(Type ret);

    void on_failed(String error);
}
