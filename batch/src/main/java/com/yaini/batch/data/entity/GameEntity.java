package com.yaini.batch.data.entity;

import com.yaini.batch.data.entity.enumerated.GameMode;
import com.yaini.batch.data.entity.enumerated.GameType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game")
public class GameEntity {
  @Id private Long gameId;

  @Column(nullable = false)
  private Long mapId;

  @Column(nullable = false)
  private GameMode gameMode;

  @Column(nullable = false)
  private GameType gameType;

  @Column(nullable = false)
  private Long gameQueueConfigId;

  @Column(nullable = false)
  private Long gameStartTime;

  @Column(nullable = false)
  private Long gameLength;

  @Column(nullable = false)
  private String participants;
  /*
  {
      "gameStartTime": 1669436771110,
      "gameLength": -80
      "gameId": 6233254897,
      "mapId": 11,
      "gameMode": "CLASSIC",
      "gameType": "MATCHED_GAME",
      "gameQueueConfigId": 420,
      "participants": [
          {
              "teamId": 100,
              "spell1Id": 14,
              "spell2Id": 4,
              "championId": 412,
              "profileIconId": 5316,
              "summonerName": "스나킹",
              "bot": false,
              "summonerId": "R5t7l9VzcPl1wSas2Czyj8M-surA6HLCWTqKVWHDN35vrrZX_MoGYCwKoA",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8351,
                      8313,
                      8345,
                      8352,
                      8473,
                      8242,
                      5008,
                      5008,
                      5002
                  ],
                  "perkStyle": 8300,
                  "perkSubStyle": 8400
              }
          },
          {
              "teamId": 100,
              "spell1Id": 11,
              "spell2Id": 4,
              "championId": 131,
              "profileIconId": 1591,
              "summonerName": "1su xiao han w",
              "bot": false,
              "summonerId": "ik8B95OHHuWOIekgfsMcf8N9srbLGB5lGtmGyDHAZ1oogk4u3AKK6_FD8g",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8010,
                      9111,
                      9105,
                      8299,
                      8304,
                      8347,
                      5005,
                      5008,
                      5002
                  ],
                  "perkStyle": 8000,
                  "perkSubStyle": 8300
              }
          },
          {
              "teamId": 100,
              "spell1Id": 12,
              "spell2Id": 4,
              "championId": 897,
              "profileIconId": 5631,
              "summonerName": "바론맛우유",
              "bot": false,
              "summonerId": "VaAU2nG20zU7B-i-1bqQfDde8pMHcwypx6KR54tnvX4jd_k",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8021,
                      9111,
                      9105,
                      8299,
                      8451,
                      8429,
                      5005,
                      5002,
                      5002
                  ],
                  "perkStyle": 8000,
                  "perkSubStyle": 8400
              }
          },
          {
              "teamId": 100,
              "spell1Id": 7,
              "spell2Id": 4,
              "championId": 236,
              "profileIconId": 4763,
              "summonerName": "우 제",
              "bot": false,
              "summonerId": "5_nYoHcRhvqGnQNufBz3dv0ZDPH8QfSUtsLfsGLXRdYfI_Q",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8005,
                      8009,
                      9103,
                      8299,
                      8304,
                      8345,
                      5005,
                      5008,
                      5002
                  ],
                  "perkStyle": 8000,
                  "perkSubStyle": 8300
              }
          },
          {
              "teamId": 100,
              "spell1Id": 12,
              "spell2Id": 4,
              "championId": 13,
              "profileIconId": 10,
              "summonerName": "우리라인은게복치",
              "bot": false,
              "summonerId": "bjfoxlSrB3CY8kPOvMA5muv1WceG_FPHBuZJ99rWxsh4KJg",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8230,
                      8226,
                      8234,
                      8237,
                      8345,
                      8347,
                      5005,
                      5008,
                      5003
                  ],
                  "perkStyle": 8200,
                  "perkSubStyle": 8300
              }
          },
          {
              "teamId": 200,
              "spell1Id": 6,
              "spell2Id": 4,
              "championId": 67,
              "profileIconId": 4861,
              "summonerName": "YoushouldKnowme",
              "bot": false,
              "summonerId": "26Yfb3WqDvwplTm9MND4FX2GBVdzq2Y0l5IAjnurDt5kXu8",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8369,
                      8313,
                      8345,
                      8347,
                      9104,
                      8017,
                      5005,
                      5008,
                      5002
                  ],
                  "perkStyle": 8300,
                  "perkSubStyle": 8000
              }
          },
          {
              "teamId": 200,
              "spell1Id": 12,
              "spell2Id": 4,
              "championId": 517,
              "profileIconId": 5454,
              "summonerName": "return paradox",
              "bot": false,
              "summonerId": "SanWC8_8-QDLF6Q691AF9LQX_DRqvbIpJs9kAffaFDqqs2VJgGXoqdQaSw",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8010,
                      8009,
                      9105,
                      8299,
                      8401,
                      8444,
                      5008,
                      5008,
                      5003
                  ],
                  "perkStyle": 8000,
                  "perkSubStyle": 8400
              }
          },
          {
              "teamId": 200,
              "spell1Id": 11,
              "spell2Id": 4,
              "championId": 234,
              "profileIconId": 7,
              "summonerName": "920104",
              "bot": false,
              "summonerId": "YQTpCnq-CbqO-MWc74EWXh1lzdxa_MY3yolGZvAYqgrc_r8",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8010,
                      9111,
                      9104,
                      8014,
                      8304,
                      8347,
                      5005,
                      5008,
                      5003
                  ],
                  "perkStyle": 8000,
                  "perkSubStyle": 8300
              }
          },
          {
              "teamId": 200,
              "spell1Id": 3,
              "spell2Id": 4,
              "championId": 267,
              "profileIconId": 3225,
              "summonerName": "생앙추",
              "bot": false,
              "summonerId": "A4mBt7poL3MvVe2bngSrMWmXlylX8f_Fxu2O8pHRoFYl9A",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8214,
                      8226,
                      8210,
                      8237,
                      8304,
                      8345,
                      5008,
                      5008,
                      5002
                  ],
                  "perkStyle": 8200,
                  "perkSubStyle": 8300
              }
          },
          {
              "teamId": 200,
              "spell1Id": 6,
              "spell2Id": 4,
              "championId": 50,
              "profileIconId": 2088,
              "summonerName": "팀못하면합류없음",
              "bot": false,
              "summonerId": "tv40uXwDv6qATZ9qwBJmIwucnqDJNgE9GfiEsT3uvlJxcA",
              "gameCustomizationObjects": [],
              "perks": {
                  "perkIds": [
                      8010,
                      8009,
                      9105,
                      8299,
                      8106,
                      8126,
                      5005,
                      5008,
                      5002
                  ],
                  "perkStyle": 8000,
                  "perkSubStyle": 8100
              }
          }
      ],
      "observers": {
          "encryptionKey": "8/JPMXVeUGPQ8bjHRKtcYS1Hzs5/ZE7d"
      },
      "platformId": "KR",
      "bannedChampions": [
          {
              "championId": -1,
              "teamId": 100,
              "pickTurn": 1
          },
          {
              "championId": 122,
              "teamId": 100,
              "pickTurn": 2
          },
          {
              "championId": 350,
              "teamId": 100,
              "pickTurn": 3
          },
          {
              "championId": 145,
              "teamId": 100,
              "pickTurn": 4
          },
          {
              "championId": 114,
              "teamId": 100,
              "pickTurn": 5
          },
          {
              "championId": 238,
              "teamId": 200,
              "pickTurn": 6
          },
          {
              "championId": 51,
              "teamId": 200,
              "pickTurn": 7
          },
          {
              "championId": 24,
              "teamId": 200,
              "pickTurn": 8
          },
          {
              "championId": 53,
              "teamId": 200,
              "pickTurn": 9
          },
          {
              "championId": 33,
              "teamId": 200,
              "pickTurn": 10
          }
      ],
      "gameStartTime": 1669436771110,
      "gameLength": -80
  }
   */
}
