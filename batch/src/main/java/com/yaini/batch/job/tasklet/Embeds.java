package com.yaini.batch.job.tasklet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
"embeds": [{
    "title": "Hello, Embed!",
    "description": "This is an embedded message.",
    "thumbnail": {
      "url": "attachment://myfilename.png"
    },
    "image": {
      "url": "attachment://mygif.gif"
    }
  }
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Embeds {

  private String title;
  private String description;
  private Image image;
}
