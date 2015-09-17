package com.barabashkastuff.urldownloader.rest;

import com.barabashkastuff.urldownloader.domain.Request;
import com.barabashkastuff.urldownloader.domain.status.RequestStatus;
import com.barabashkastuff.urldownloader.domain.rest.UrlRestRequest;
import com.barabashkastuff.urldownloader.domain.rest.UrlRestResponse;
import com.barabashkastuff.urldownloader.service.IRequestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * ExampleRest Class
 *
 * @author a.slepakurov
 * @version 9/17/15
 */
@RestController
@RequestMapping("/api")
public class UrlDownloaderRest {
    private static final Logger LOGGER = Logger.getLogger(UrlDownloaderRest.class);
    private static final Pattern URL_PATTERN = Pattern.compile("^(https?://)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([/\\w .-]*)*/?$");

    @Autowired
    private IRequestService requestService;
    @Autowired
    private ResourceBundle messages;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UrlRestResponse> submit(@RequestBody UrlRestRequest request) {
        String requestUrl = request.getUrl();
        if (!StringUtils.hasText(requestUrl)) {
            LOGGER.warn(messages.getString("no.url.error.log"));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new UrlRestResponse(null, null, RequestStatus.ERROR, messages.getString("no.url.error.message")));
        }
        if (!URL_PATTERN.matcher(requestUrl).matches()) {
            LOGGER.warn(String.format(messages.getString("incorrect.url.error.log"), requestUrl));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new UrlRestResponse(null, null, RequestStatus.ERROR, messages.getString("incorrect.url.error.message")));
        }
        String id = requestService.create(new Request(requestUrl));
        LOGGER.info(String.format(messages.getString("submitted.url.log"), id));
        return ResponseEntity
                .ok(new UrlRestResponse(id, requestUrl, RequestStatus.SUBMITTED, messages.getString("submitted.url.message")));
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UrlRestResponse> status(@PathVariable("id") String id) {
        Request request = requestService.get(id);
        if (request == null) {
            return ResponseEntity
                    .ok(new UrlRestResponse(id, null, RequestStatus.ERROR, messages.getString("no.request.message")));
        }
        return ResponseEntity.status(request.getRequestStatus().equals(RequestStatus.ERROR) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK)
                .body(new UrlRestResponse(id, request.getUrl(), request.getRequestStatus(),
                        messages.getString(String.format("request.status.%s.message", request.getRequestStatus().getTitle()))));
    }

}