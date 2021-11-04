-- Adminer 4.7.7 PostgreSQL dump

DROP TABLE IF EXISTS "todo_item";
DROP SEQUENCE IF EXISTS todo_item_sequence;
CREATE SEQUENCE todo_item_sequence INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."todo_item"
(
    "id"      integer DEFAULT nextval('todo_item_sequence') NOT NULL,
    "text"    character varying                             NOT NULL,
    "resolve" boolean DEFAULT false                         NOT NULL,
    CONSTRAINT "todo_item_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


-- 2021-11-04 14:44:14.154187+00