package com.abcbank.thirdpartyservice.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(
    name = "errors",
    indexes = {
        @Index(name = "i_error_code_and_language", columnList = "error_code, language")
    }
)
@Entity
public class ErrorEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "error_code", nullable = false)
  private String errorCode;

  @Column(name = "error_detail")
  private String errorDetail;

  @Column(name = "language", nullable = false, columnDefinition = "varchar(2) default 'vi'")
  private String language;
}
