package com.yaini.batch.client.web.config;

import static feign.FeignException.errorStatus;
import static feign.Util.RETRY_AFTER;
import static java.util.concurrent.TimeUnit.SECONDS;

import com.yaini.batch.client.web.support.exception.ResourceNotFoundException;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class WebClientErrorDecoder implements ErrorDecoder {

  static final DateFormat RFC822_FORMAT =
      new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.getDefault());

  @Override
  public Exception decode(final String methodKey, final Response response) {
    FeignException exception = errorStatus(methodKey, response);
    Date retryAfter = this.retryAfter(firstOrNull(response.headers(), RETRY_AFTER));
    int httpStatus = response.status();

    if (HttpStatus.valueOf(httpStatus).is5xxServerError()) {

      return new RetryableException(
          response.status(),
          exception.getMessage(),
          response.request().httpMethod(),
          exception,
          retryAfter,
          response.request());
    } else if (HttpStatus.valueOf(httpStatus).is4xxClientError()) {
      if (HttpStatus.NOT_FOUND.value() == httpStatus) {

        return new ResourceNotFoundException();
      }

      return new RetryableException(
          response.status(),
          exception.getMessage(),
          response.request().httpMethod(),
          exception,
          retryAfter,
          response.request());
    }

    return new ErrorDecoder.Default().decode(methodKey, response);
  }

  private <T> T firstOrNull(Map<String, Collection<T>> map, String key) {
    if (map.containsKey(key) && !map.get(key).isEmpty()) {
      return map.get(key).iterator().next();
    }
    return null;
  }

  private Date retryAfter(String retryAfter) {
    if (retryAfter == null) {
      return null;
    }
    if (retryAfter.matches("^[0-9]+\\.?0*$")) {
      retryAfter = retryAfter.replaceAll("\\.0*$", "");
      long deltaMillis = SECONDS.toMillis(Long.parseLong(retryAfter));
      return new Date(System.currentTimeMillis() + deltaMillis);
    }
    synchronized (RFC822_FORMAT) {
      try {
        return RFC822_FORMAT.parse(retryAfter);
      } catch (ParseException ignored) {
        return null;
      }
    }
  }
}
