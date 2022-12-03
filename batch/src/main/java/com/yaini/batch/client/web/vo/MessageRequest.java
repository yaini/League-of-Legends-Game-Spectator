package com.yaini.batch.client.web.vo;

import com.yaini.batch.job.tasklet.Embeds;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
  private String username;
  private String content;
  private List<Embeds> embeds;
}
