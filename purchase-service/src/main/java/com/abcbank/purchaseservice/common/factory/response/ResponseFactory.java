package com.abcbank.purchaseservice.common.factory.response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.abcbank.purchaseservice.common.constant.ResponseStatusCodeEnum;
import com.abcbank.purchaseservice.config.MessageResponseConfig;
import com.abcbank.purchaseservice.locale.Translator;
import com.abcbank.purchaseservice.service.domain.ErrorService;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ResponseFactory {

//    @Autowired
//    private SimpleSecurityService simpleSecurityService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private MessageResponseConfig messageResponseConfig;

    /**
     * Replace params in message
     *
     * @param message
     * @param params
     * @return
     */
    public String replaceParams(String message, Map<String, String> params) {
        // replace params in message
        if (!CollectionUtils.isEmpty(params)) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                message = Pattern.compile("%%" + param.getKey() + "%%").matcher(message).replaceAll(param.getValue());
            }
        }

        // replace general params
        if (messageResponseConfig != null && !CollectionUtils.isEmpty(messageResponseConfig.getParams())) {
            for (Map.Entry<String, String> param : messageResponseConfig.getParams().entrySet()) {
                message = message.replaceAll("%%" + param.getKey() + "%%", param.getValue());
            }
        }
        return message;
    }

    /**
     * Parsing status code & message to response object
     */
    public ResponseStatus parseResponseStatus(ResponseStatusCodeEnum code,
                                               Map<String, String> params) {
        ResponseStatus responseStatus = new ResponseStatus(code.getCode(), true);
        responseStatus.setMessage(replaceParams(responseStatus.getMessage(), params));

        if(errorService != null) {
            String errorDetail = errorService
                    .getErrorDetail(code.getCode(), LocaleContextHolder.getLocale().getLanguage());
            if (StringUtils.isNotBlank(errorDetail)) {
                // replace params to display message
                responseStatus.setDisplayMessage(replaceParams(errorDetail, params));
            } else {
                responseStatus.setDisplayMessage(responseStatus.getMessage());
            }
        }

        log.debug(responseStatus.toString());

        return responseStatus;
    }

    /**
     * Response status code SUCCESS
     */
    public ResponseEntity success(Object data, Class<?> clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setData(clazz.cast(data));
        return success(responseObject);
    }

    public ResponseEntity success(GeneralResponse responseObject) {
        ResponseStatus responseStatus = parseResponseStatus(ResponseStatusCodeEnum.SUCCESS, null);
        responseObject.setStatus(responseStatus);
        return ResponseEntity.ok().body(responseObject);
    }

    /**
     * Response SUCCESS with header
     */
    public ResponseEntity successWithHeader(MultiValueMap<String, String> header, Object data,
                                            Class<?> clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setData(clazz.cast(data));

        ResponseStatus responseStatus = parseResponseStatus(ResponseStatusCodeEnum.SUCCESS, null);
        responseObject.setStatus(responseStatus);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.addAll(header);
        return ResponseEntity.ok().headers(responseHeaders).body(responseObject);
    }

    /**
     * Response with status code not equals SUCCESS
     */
    public ResponseEntity fail(Object data, Class<?> clazz, ResponseStatusCodeEnum code) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setData(clazz.cast(data));
        return fail(responseObject, code, null);
    }

    public ResponseEntity fail(GeneralResponse responseObject, ResponseStatusCodeEnum code) {
        return fail(responseObject, code, null);
    }

    public ResponseEntity fail(GeneralResponse responseObject, ResponseStatusCodeEnum code,
                               Map<String, String> params) {
        ResponseStatus responseStatus = parseResponseStatus(code, params);
        responseObject.setStatus(responseStatus);
        return ResponseEntity.status(code.getHttpCode()).body(responseObject);
    }

    /**
     * Response on filter
     */
    public void httpServletResponseToClient(HttpServletResponse httpServletResponse, Object data,
                                            ResponseStatusCodeEnum statusCode) throws IOException {
        httpServletResponseToClient(httpServletResponse, data, statusCode, null);
    }

    public void httpServletResponseToClient(HttpServletResponse httpServletResponse, Object data,
                                            ResponseStatusCodeEnum statusCode, Map<String, String> params) throws IOException {
        GeneralResponse<Object> response = new GeneralResponse<>();
        response.setData(data);
        ResponseStatus responseStatus = parseResponseStatus(statusCode, params);
        response.setStatus(responseStatus);
        writeToHttpServletResponse(httpServletResponse, response, statusCode);
    }

    public void writeToHttpServletResponse(HttpServletResponse httpServletResponse, Object response,
                                           ResponseStatusCodeEnum statusCode) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String responseString = mapper.writeValueAsString(response);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setStatus(statusCode.getHttpCode());
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE,
                MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(responseString);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }

    public ResponseEntity success(Object data, Class<?> clazz, PagingMetaData pageInfo) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus(ResponseStatusCodeEnum.SUCCESS.getCode(), true);
        responseObject.setStatus(responseStatus);
        responseObject.setData(clazz.cast(data));
        responseObject.setPagingMetadata(pageInfo);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity failCustomMessage(String messageCode, Object arrayParam) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus(messageCode, false);
        String message = Translator.toLocale(messageCode, arrayParam);
        responseStatus.setMessage(message);
        responseObject.setStatus(responseStatus);
        return ResponseEntity.status(ResponseStatusCodeEnum.BUSINESS_ERROR.getHttpCode()).body(responseObject);
    }

    public ResponseEntity successCustomMessageAndHttpStatus(String code, String massageDisplay) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = parseResponseStatus(ResponseStatusCodeEnum.SUCCESS, null);
        responseStatus.editCode(code);
        responseStatus.setMessage(massageDisplay);
        responseStatus.setDisplayMessage(massageDisplay);
        responseObject.setStatus(responseStatus);
        responseObject.setData(new ArrayList<>());
        responseObject.setPagingMetadata(new PagingMetaData());
        return ResponseEntity.ok().body(responseObject);
    }
    
	public ResponseEntity responseMessage(String messageCode, String sttMessage, Object... arrayParam) {
		ResponseStatus responseStatus = new ResponseStatus(messageCode, false);
		String message = Translator.toLocale(messageCode, arrayParam);
		responseStatus.setMessage(sttMessage);
		responseStatus.setDisplayMessage(message);
		return ResponseEntity.ok().body(responseStatus);
	}
	
	/**
	 * Response status code SUCCESS
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity responseStatus(Object data, Class<?> clazz, String messageCode,String sttMessage, Object... arrayParam) {
		GeneralResponse<Object> responseObject = new GeneralResponse<>();
		responseObject.setData(clazz.cast(data));
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.editCode(messageCode);
		responseStatus.setMessage(sttMessage);
		String message = Translator.toLocale(messageCode, arrayParam);
		responseStatus.setDisplayMessage(message);
		responseObject.setStatus(responseStatus);
		responseObject.setPagingMetadata(new PagingMetaData());
		return ResponseEntity.ok().body(responseObject);
	}
}
