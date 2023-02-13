drop table if exists _user;
drop table if exists recruit_post;
drop table if exists recruit_post_tag;
drop table if exists tag;
drop table if exists job_group;

create table _user (
   id bigint not null auto_increment,
   email varchar(255) not null,
   social varchar(255) not null,
   social_provider_key varchar(255) not null,
   user_role varchar(255) not null,
   username varchar(255) not null,
   primary key (id)
);


create table profile (
    id bigint not null auto_increment,
    available_period varchar(255) DEFAULT '',
    available_time varchar(255) DEFAULT '',
    career_period varchar(255) DEFAULT '',
    face_to_face bit,
    image_url varchar(255) DEFAULT '',
    phone_number varchar(255) DEFAULT '',
    portfolio_url varchar(255) DEFAULT '',
    position varchar(255) DEFAULT '',
    residence varchar(255) DEFAULT '',
    job_group varchar(255) DEFAULT  '',
    skills varchar(255) DEFAULT  '',
    user_id bigint,
    primary key (id)
);

create table recruit_post (
    id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    body longtext not null,
    image_url varchar(255) DEFAULT '',
    is_opened bit,
    project_end_time date,
    project_start_time date,
    recruit_due_time date,
    required_designers integer DEFAULT 0,
    required_developers integer DEFAULT 0,
    required_project_managers integer DEFAULT 0,
    title varchar(255) not null,
    user_id bigint,
    primary key (id)
);

create table recruit_post_tag (
    id bigint not null auto_increment,
    recruit_post_id bigint,
    tag_id bigint,
    primary key (id)
);

create table tag (
    id bigint not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);


alter table tag
    add constraint UK_1wdpsed5kna2y38hnbgrnhi5b unique (name);

alter table profile
    add constraint FKp08s82jrgy459anpd60iik67f
    foreign key (user_id)
    references _user (id);

alter table recruit_post
    add constraint FKq1l5p3mnn5re7f7loym2t6b0j
    foreign key (user_id)
    references _user (id);

alter table recruit_post_tag
    add constraint FKmo139hucabs7l16u9mrnm951e
    foreign key (recruit_post_id)
    references recruit_post (id);

alter table recruit_post_tag
    add constraint FK4jpvk82mmqchv29k49vriixxp
    foreign key (tag_id)
    references tag (id);

