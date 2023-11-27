CREATE TABLE IF NOT EXISTS Accounts
(
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	amount numeric NOT NULL,
	type varchar(255) NOT NULL,
	PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS Users
(
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	name varchar(255) NOT NULL,
	nickname varchar(255) NOT NULL,
	birthday date NOT NULL,
	password varchar(255) NOT NULL,
	account_id bigint NOT NULL REFERENCES Accounts(id) UNIQUE,
	PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS Games
(
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	name varchar(255) NOT NULL,
	release_date date NOT NULL,
	rating bigint NOT NULL,
	cost numeric NOT NULL,
	description varchar(255) NOT NULL,
	PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS Users_games
(
	user_id bigint NOT NULL REFERENCES Users(id),
	game_id bigint NOT NULL REFERENCES Games(id)
);