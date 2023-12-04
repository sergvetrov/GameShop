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
INSERT INTO public.accounts (amount, type) VALUES (252.12, 'VISA');
INSERT INTO public.accounts (amount, type) VALUES (2132.70, 'MASTERCARD');

INSERT INTO public.users (name, nickname, birthday, password, account_id) VALUES ('John', 'spellsinger', '1990-01-24', 'makarena13', 1);
INSERT INTO public.users (name, nickname, birthday, password, account_id) VALUES ('Sandra', 'hawkey', '1985-03-15', 'omnomnom00', 2);

INSERT INTO public.games (name, release_date, rating, cost, description) VALUES ('Minecraft', '2011-01-01', 54, 22.5, 'shit for kids');
INSERT INTO public.games (name, release_date, rating, cost, description) VALUES ('Super Mario', '1982-04-03', 41, 2.5, 'old school game');
INSERT INTO public.games (name, release_date, rating, cost, description) VALUES ('Counter Strike', '2002-06-19', 61, 7.3, 'shooter');
INSERT INTO public.games (name, release_date, rating, cost, description) VALUES ('Lineage 2', '2006-07-21', 92, 71, 'MMO RPG');
INSERT INTO public.games (name, release_date, rating, cost, description) VALUES ('Dota2', '2016-10-25', 97, 100.7, 'arcada');

INSERT INTO public.users_games (user_id, game_id) VALUES (1, 1);
INSERT INTO public.users_games (user_id, game_id) VALUES (1, 3);
INSERT INTO public.users_games (user_id, game_id) VALUES (1, 5);
INSERT INTO public.users_games (user_id, game_id) VALUES (2, 1);
INSERT INTO public.users_games (user_id, game_id) VALUES (2, 2);
INSERT INTO public.users_games (user_id, game_id) VALUES (1, 4);