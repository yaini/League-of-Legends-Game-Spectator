package com.yaini.batch.job.model;

public class DataResponse<T> {
  private String type;
  private String format;
  private String version;
  private T data;
}
