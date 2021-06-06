package com.abcbank.purchaseservice.common.factory.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Format of response returned to client
 *
 * @author Ironman
 * @param <T>
 */

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse<T>  {
    @JsonProperty("status")
    private ResponseStatus status;

    @JsonProperty("data")
    private T data;

    @JsonProperty("pagingMetadata")
    private PagingMetaData pagingMetadata;

    public GeneralResponse(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" + "status=" + status +
                ", data=" + data +
                '}';
    }
}
