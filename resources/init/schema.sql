CREATE TABLE channels (
    channel_id  INTEGER  PRIMARY KEY,
    token  TEXT  NOT NULL,
    webhook_url  TEXT  NOT NULL
);

CREATE TABLE posts (
    post_id  INTEGER  PRIMARY KEY,
    channel_id  INTEGER  NOT NULL,
    submitter  TEXT  NOT NULL,
    FOREIGN KEY (channel_id) REFERENCES channel(channel_id)
);

CREATE TABLE votes (
    vote_id  INTEGER  PRIMARY KEY,
    submitter  TEXT  NOT NULL,
    post_id  INTEGER  NOT NULL,
    vote_type  INTEGER  NOT NULL  CHECK (vote_type = 1 or vote_type = 0),
    FOREIGN KEY (post_id) REFERENCES post(post_id)
);