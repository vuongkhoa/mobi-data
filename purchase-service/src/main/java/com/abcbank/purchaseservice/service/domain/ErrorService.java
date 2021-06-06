package com.abcbank.purchaseservice.service.domain;

import com.abcbank.purchaseservice.model.entity.ErrorEntity;
import com.abcbank.purchaseservice.repository.mysql.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ErrorService {

  @Autowired
  ErrorRepository errorRepository;

  public String getErrorDetail(String errorCode, String language) {
    Optional<ErrorEntity> errorEntity = errorRepository
        .findByErrorCodeAndLanguage(errorCode, language);
    if (errorEntity.isPresent()) {
      return errorEntity.get().getErrorDetail();
    } else {
      return "";
    }
  }
}
