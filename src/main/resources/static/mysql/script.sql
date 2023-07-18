select * from file_board fb ;

select * from file_board_image2 fbi ;

select * from product p order by p.pno desc;

select * from product_images pi2 order by product_pno desc ;

select * from product_review pr ;

select product_pno, avg(score), count(rno)
from product_review pr 
group by pr.product_pno ;

delete from member where email = 'toquasimodo@naver.com';

select * from member;


select * from product2;

select * from product2_images pi2 ;

select * from product_review pr ;

select * from tbl_reply tr;

select * from tbl_board ;

delete from tbl_board where bno=2;


CREATE TABLE tbl_board (
  bno INT AUTO_INCREMENT,
  title VARCHAR(100),
  content VARCHAR(500),
  writer VARCHAR(50),
  dueDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  exist boolean default true,
  PRIMARY KEY (bno)
);


CREATE TABLE tbl_reply (
  rno INT AUTO_INCREMENT PRIMARY KEY,
  bno INT,
  reply VARCHAR(500),
  replyer VARCHAR(50),
  replyDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  gno INT DEFAULT 0,
  step INT,
  status BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (bno) REFERENCES tbl_board(bno)
);


insert into tbl_reply (bno, reply, replyer) values (2039, 'test', 'tester');


insert into tbl_board (title, content, writer)
(select title, content, writer from tbl_board);