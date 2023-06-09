package com.example.springsimplerestservicedemo.controller;

import com.example.springsimplerestservicedemo.model.Request;
import com.example.springsimplerestservicedemo.model.Response;
import com.example.springsimplerestservicedemo.service.ModifyRequestService;
import com.example.springsimplerestservicedemo.service.MyModifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {

  private final MyModifyService myModifyService;
  private final ModifyRequestService modifyRequestService;

  @Autowired
  public MyController(@Qualifier("ModifyErrorMessage") MyModifyService myModifyService,
      ModifyRequestService modifyRequestService){
    this.myModifyService = myModifyService;
    this.modifyRequestService = modifyRequestService;
  }

  @PostMapping(value = "/feedback")
  public ResponseEntity<Response> feedback(@RequestBody Request request){

    log.info("Входящий request: " + String.valueOf(request));

    Response response = Response.builder()
        .uid(request.getUid())
        .operationUid(request.getOperationUid())
        .systemTime(request.getSystemTime())
        .code("Success")
        .errorCode("")
        .errorMessage("")
        .build();

    modifyRequestService.modifyRq(request);

    Response responseAfterModify = myModifyService.modify(response);
    log.info("Исходящий response: " + String.valueOf(response));

    return new ResponseEntity<>(responseAfterModify, HttpStatus.OK);
  }

}