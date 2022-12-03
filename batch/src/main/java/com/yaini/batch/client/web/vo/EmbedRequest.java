package com.yaini.batch.client.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmbedRequest {
  private String title;
  private String description;
  private String url;
  private ImageRequest image;
  private ImageRequest thumbnail;
}
