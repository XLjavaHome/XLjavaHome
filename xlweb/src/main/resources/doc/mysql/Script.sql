use spring;
create table account (
  id int not null auto_increment,
  name varchar(45),
  money decimal(10,0),
  primary key (id)
)
  engine = InnoDB
;


use sping2;