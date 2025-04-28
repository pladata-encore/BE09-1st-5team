-- 2. 테이블 삭제 (존재할 경우)
DROP TABLE IF EXISTS tbl_pizza_order;
DROP TABLE IF EXISTS tbl_side_order;
DROP TABLE IF EXISTS tbl_order;
DROP TABLE IF EXISTS tbl_size;
DROP TABLE IF EXISTS tbl_dough;
DROP TABLE IF EXISTS tbl_side_menu;
DROP TABLE IF EXISTS tbl_pizza;
DROP TABLE IF EXISTS tbl_member;

-- 3. 테이블 생성
-- 회원 테이블
CREATE TABLE tbl_member (
                            memberId INT NOT NULL AUTO_INCREMENT COMMENT '회원번호',
                            username VARCHAR(50) UNIQUE COMMENT '회원아이디',
                            password VARCHAR(100) COMMENT '회원비밀번호',
                            name VARCHAR(50) COMMENT '회원명',
                            PRIMARY KEY (memberId)
) COMMENT='회원';

-- 피자 테이블
CREATE TABLE tbl_pizza (
                           pizzaId INT NOT NULL AUTO_INCREMENT COMMENT '파자번호',
                           pizzaName VARCHAR(50) COMMENT '피자명',
                           quantity INT DEFAULT 0 COMMENT '수량',
                           PRIMARY KEY (pizzaId)
) COMMENT='피자';

-- 도우 테이블
CREATE TABLE tbl_dough (
                           doughId INT NOT NULL AUTO_INCREMENT COMMENT '도우번호',
                           doughName VARCHAR(50) COMMENT '도우명',
                           doughPrice INT DEFAULT 0 COMMENT '도우가격',
                           PRIMARY KEY (doughId)
) COMMENT='도우';

-- 사이즈 테이블
CREATE TABLE tbl_size (
                          sizeId INT PRIMARY KEY AUTO_INCREMENT COMMENT '사이즈번호',
                          sizeName VARCHAR(50) COMMENT '사이즈명',
                          price INT DEFAULT 0 COMMENT '가격',
                          pizzaId INT NOT NULL COMMENT '파자번호',
                          FOREIGN KEY (pizzaId) REFERENCES tbl_pizza(pizzaId)
) COMMENT='사이즈';

-- 주문 테이블
CREATE TABLE tbl_order (
                           orderId INT NOT NULL AUTO_INCREMENT COMMENT '주문번호',
                           totalPrice INT DEFAULT 0 COMMENT '주문가격',
                           memberId INT NOT NULL COMMENT '회원번호',
                           orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '주문일시',
                           PRIMARY KEY (orderId),
                           FOREIGN KEY (memberId) REFERENCES tbl_member(memberId)
) COMMENT='주문';

-- 피자주문 테이블
CREATE TABLE tbl_pizza_order (
                                 orderDetailId INT NOT NULL AUTO_INCREMENT COMMENT '주문상세번호',
                                 quantity INT DEFAULT 1 COMMENT '수량',
                                 price INT DEFAULT 0 COMMENT '가격',
                                 sizeId INT NOT NULL COMMENT '사이즈번호',
                                 doughId INT NOT NULL COMMENT '도우번호',
                                 orderId INT NOT NULL COMMENT '주문번호',
                                 pizzaId INT NOT NULL COMMENT '파자번호',
                                 PRIMARY KEY (orderDetailId),
                                 FOREIGN KEY (doughId) REFERENCES tbl_dough(doughId),
                                 FOREIGN KEY (orderId) REFERENCES tbl_order(orderId),
                                 FOREIGN KEY (pizzaId, sizeId) REFERENCES tbl_size(pizzaId, sizeId)
) COMMENT='피자주문';

-- 사이드메뉴 테이블
CREATE TABLE tbl_side_menu (
                               sideId INT NOT NULL AUTO_INCREMENT COMMENT '사이드번호',
                               sideName VARCHAR(50) COMMENT '사이드명',
                               price INT DEFAULT 0 COMMENT '가격',
                               quantity INT DEFAULT 0 COMMENT '수량',
                               PRIMARY KEY (sideId)
) COMMENT='사이드메뉴';

-- 사이드주문 테이블 (복합키 사용)
CREATE TABLE tbl_side_order (
                                sideOrderId INT NOT NULL COMMENT '사이드주문번호',
                                quantity INT DEFAULT 1 COMMENT '수량',
                                orderId INT NOT NULL COMMENT '주문번호',
                                sideId INT NOT NULL COMMENT '사이드번호',
                                PRIMARY KEY (sideId, sideOrderId),
                                FOREIGN KEY (sideId) REFERENCES tbl_side_menu(sideId),
                                FOREIGN KEY (orderId) REFERENCES tbl_order(orderId)
) COMMENT='사이드주문';
