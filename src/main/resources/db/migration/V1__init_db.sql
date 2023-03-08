create sequence images_seq start with 1 increment by 50;
create sequence notes_seq start with 1 increment by 50;

create table images (
    id             bigserial primary key,
    filename_image varchar(255)
    );

create table notes (
    id                bigserial primary key,
    subject_notes     varchar(255),
    full_text_notes   varchar(2048),
    is_important      boolean not null,
    date_create       timestamp not null,
    date_modification timestamp not null
    );

create table notes_images (
    note_id int8 references notes on delete cascade,
    images_id int8 references images
    );
