CREATE TABLE game
(
	`game_id`				BIGINT			NOT NULL,
	`map_id`				BIGINT			NOT NULL,
	`game_mode`				VARCHAR(50)		NOT NULL,
	`game_type`				VARCHAR(50)		NOT NULL,
	`game_queue_id`			BIGINT			NOT NULL,
	`game_start_time`		BIGINT			NOT NULL,
	`game_length`			BIGINT			NOT NULL,
	`participants`			VARCHAR(255)	NOT NULL,
	`create_date`			DATETIME(6)		NOT NULL,
	`update_date`			DATETIME(6)		NOT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE game_queue
(
	`game_queue_id`			BIGINT			NOT NULL,
	`map`					VARCHAR(255)	NOT NULL,
	`description`			VARCHAR(255),
	`notes`					VARCHAR(255),
	`create_date`			DATETIME(6)		NOT NULL,
	`update_date`			DATETIME(6)		NOT NULL,
    PRIMARY KEY (game_queue_id)
);

CREATE TABLE summoner
(
	`summoner_id`			VARCHAR(70)		NOT NULL,
	`account_id`			VARCHAR(70)		NOT NULL,
	`puuid`					VARCHAR(100)	NOT NULL,
	`name`					VARCHAR(200)	NOT NULL,
	`profile_icon_id`		BIGINT			NOT NULL,
	`revision_date`			BIGINT			NOT NULL,
	`summoner_level`		INT				NOT NULL,
	`create_date`			DATETIME(6)		NOT NULL,
	`update_date`			DATETIME(6)		NOT NULL,
    PRIMARY KEY (summoner_id)
);

CREATE TABLE summoner_game_relation
(
	`summoner_game_relation_id`	BIGINT			NOT NULL,
	`summoner_account_id`		VARCHAR(70)		NOT NULL,
	`game_id`					BIGINT			NOT NULL,
	`first_spell`				BIGINT			NOT NULL,
	`second_spell`				BIGINT			NOT NULL,
	`champion_id`				BIGINT			NOT NULL,
	`create_date`				DATETIME(6)		NOT NULL,
	`update_date`				DATETIME(6)		NOT NULL,
    PRIMARY KEY (summoner_game_relation_id)
);
