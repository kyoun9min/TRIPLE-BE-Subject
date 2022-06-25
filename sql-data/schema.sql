create table photo
(
    id varchar(255) not null,
    review_id varchar(255),
    primary key (id)
) engine = InnoDB;

create table place
(
    id varchar(255) not null,
    primary key (id)
) engine = InnoDB;

create table point_history
(
    id bigint not null auto_increment,
    reason varchar(255),
    status varchar(255),
    value varchar(255),
    user_id varchar(255),
    primary key (id)
) engine = InnoDB;

create table review
(
    id varchar(255) not null,
    content varchar(255),
    photo_number integer,
    point integer,
    place_id varchar(255),
    user_id varchar(255),
    primary key (id)
) engine = InnoDB;

create table user
(
    id varchar(255) not null,
    point integer,
    primary key (id)
) engine = InnoDB;

create index idx_user on review (user_id, place_id);

alter table photo
    add constraint photo_review
        foreign key (review_id) references review (id);

alter table point_history
    add constraint point_history_user
        foreign key (user_id) references user (id);

alter table review
    add constraint review_place
        foreign key (place_id) references place (id);

alter table review
    add constraint review_user
        foreign key (user_id) references user (id);