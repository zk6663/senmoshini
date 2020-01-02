create table eve_user(
  id int PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(32),
  password VARCHAR(32),
  level int
);

CREATE TABLE eve_movie(
  movie_id int AUTO_INCREMENT PRIMARY KEY ,
  movie_name VARCHAR(20),
  movie_director VARCHAR(20),
  movie_actor VARCHAR(100),
  movie_type VARCHAR(100),
  movie_area VARCHAR(100),
  movie_language VARCHAR(50),
  movie_ontime VARCHAR(50),
  movie_pic VARCHAR(200),
  movie_operation int,
  movie_review int REFERENCES eve_review(id)
);

CREATE TABLE eve_review(
  id int AUTO_INCREMENT PRIMARY KEY ,
  content VARCHAR(350),
  username VARCHAR(32),
  movie_id int REFERENCES eve_movie(id)
);