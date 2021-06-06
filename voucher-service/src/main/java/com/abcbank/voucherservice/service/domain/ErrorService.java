package com.abcbank.voucherservice.service.domain;

import com.abcbank.voucherservice.model.entity.ErrorEntity;
import com.abcbank.voucherservice.repository.mysql.ErrorRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
