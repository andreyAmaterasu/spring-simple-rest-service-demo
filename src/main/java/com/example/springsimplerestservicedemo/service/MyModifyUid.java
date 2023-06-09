package com.example.springsimplerestservicedemo.service;

import com.example.springsimplerestservicedemo.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ModifyUid")
public class MyModifyUid implements MyModifyService {

  @Override
  public Response modify(Response response){
    response.setUid("New uid");
    return response;
  }
}
