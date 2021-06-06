package com.abcbank.voucherservice.common.factory.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingMetaData {
    @JsonProperty("size")
    private Long size;
    @JsonProperty("totalElement")
    private Integer totalElement;
    @JsonProperty("totalPage")
    private Long totalPage;
    @JsonProperty("currentPage")
    private Long currentPage;
    @JsonProperty("nextPageURL")
    private String nextPageURL;
    @JsonProperty("previousPageURL")
    private String previousPageURL;
}
