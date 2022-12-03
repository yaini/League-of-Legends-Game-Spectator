package com.yaini.batch.job.model;

import lombok.Getter;

@Getter
public class DataFormat {
  private String type;
  private String format;
  private String version;
  private Champion data;
}
