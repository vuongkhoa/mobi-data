package com.abcbank.thirdpartyservice.repository;

import com.abcbank.thirdpartyservice.model.entity.ErrorEntity;
import java.util.Optional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//TODO: turn on when running with cache
//@CacheConfig(cacheNames = {"ErrorRepository"}, cacheManager = "redisCacheManager")
@CacheConfig(cacheNames = {"ErrorRepository"})
public interface ErrorRepository extends JpaRepository<ErrorEntity, String> {
  @Cacheable
  Optional<ErrorEntity> findByErrorCodeAndLanguage(String errorCode, String language);
}
