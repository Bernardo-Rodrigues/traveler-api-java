CREATE TABLE IF NOT EXISTS public.users
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.continents
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT continents_pkey PRIMARY KEY (id),
    CONSTRAINT uk_ekj4ujwv7pi6dcdsktqyrhrp7 UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS public.countries
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    continent_id integer,
    CONSTRAINT countries_pkey PRIMARY KEY (id),
    CONSTRAINT uk_1pyiwrqimi3hnl3vtgsypj5r UNIQUE (name),
    CONSTRAINT fk739g8l1ttj8jyjow661hv5kgw FOREIGN KEY (continent_id)
        REFERENCES public.continents (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.localizations
(
    id integer NOT NULL,
    lat character varying(255) COLLATE pg_catalog."default",
    lng character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT localizations_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.destinations
(
    image_link character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    localization_id integer NOT NULL,
    country_id integer,
    CONSTRAINT destinations_pkey PRIMARY KEY (localization_id),
    CONSTRAINT uk_420af7nwsa7i6e5k3pgw6r5cr UNIQUE (name),
    CONSTRAINT uk_dr9wdyac48y5qef1r7f325l5l UNIQUE (image_link),
    CONSTRAINT fkefbh5a8pav79svhn4w42j90xs FOREIGN KEY (localization_id)
        REFERENCES public.localizations (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkekpb47cct7wijcnqpb7fjhxma FOREIGN KEY (country_id)
        REFERENCES public.countries (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.descriptions
(
    id integer NOT NULL,
    text text COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    destination_id integer,
    CONSTRAINT descriptions_pkey PRIMARY KEY (id),
    CONSTRAINT fk2hru75orqjc8tbo5kqrqwo5f2 FOREIGN KEY (destination_id)
        REFERENCES public.destinations (localization_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.favorites
(
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    destination_id integer NOT NULL,
    CONSTRAINT favorites_pkey PRIMARY KEY (destination_id, user_id),
    CONSTRAINT fkc9hci4f44c3i90h4fbsesr26d FOREIGN KEY (destination_id)
        REFERENCES public.destinations (localization_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkk7du8b8ewipawnnpg76d55fus FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.reviews
(
    note integer,
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    destination_id integer NOT NULL,
    CONSTRAINT reviews_pkey PRIMARY KEY (destination_id, user_id),
    CONSTRAINT fkcgy7qjc1r99dp117y9en6lxye FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fko07xgps8spbcjhqpj559t835x FOREIGN KEY (destination_id)
        REFERENCES public.destinations (localization_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.tips
(
    id integer NOT NULL,
    description text COLLATE pg_catalog."default",
    destination_id integer,
    CONSTRAINT tips_pkey PRIMARY KEY (id),
    CONSTRAINT fkbpyobv40k0g6jrw8m6nk7cifo FOREIGN KEY (destination_id)
        REFERENCES public.destinations (localization_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS public.travels
(
    id integer NOT NULL,
    end_date timestamp(6) without time zone,
    start_date timestamp(6) without time zone,
    destination_id integer,
    user_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT travels_pkey PRIMARY KEY (id),
    CONSTRAINT fkrtjmw7ntheqchtl1b07a1qlfd FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fks45v3onea9ceiexlej2bg62tk FOREIGN KEY (destination_id)
        REFERENCES public.destinations (localization_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.achievements
(
    id integer NOT NULL,
    count integer,
    description character varying(255) COLLATE pg_catalog."default",
    image_link character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    destination_id integer,
    CONSTRAINT achievements_pkey PRIMARY KEY (id),
    CONSTRAINT uk_59nc2riw4oidiciqi9gskhjsq UNIQUE (count),
    CONSTRAINT uk_gocutjn7751o5vdq416k87fj8 UNIQUE (destination_id),
    CONSTRAINT fkk0vqy603xe7gau01b03ck8f3x FOREIGN KEY (destination_id)
        REFERENCES public.destinations (localization_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.achievements_users
(
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    achievement_id integer NOT NULL,
    CONSTRAINT achievements_users_pkey PRIMARY KEY (achievement_id, user_id),
    CONSTRAINT fkccdnwtrjxx5wogjgomepkjlbu FOREIGN KEY (achievement_id)
        REFERENCES public.achievements (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fknx079ng6cmkc1jxalh2y3j0ee FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);