-- 회원
CREATE TABLE `member` (
	`memKey`   INTEGER      NOT NULL COMMENT 'PK', -- PK
	`memEmail` VARCHAR(40)  NOT NULL COMMENT 'e-mail', -- e-mail
	`memName`  VARCHAR(100) NOT NULL COMMENT '이름', -- 이름
	`memPw`    VARCHAR(50)  NOT NULL COMMENT '비밀번호', -- 비밀번호
	`memBirth` VARCHAR(20)  NOT NULL COMMENT '생일', -- 생일
	`memLevel` VARCHAR(100) NOT NULL DEFAULT 'MEMBER' COMMENT '권한' -- 권한
)
COMMENT '회원';

-- 회원
ALTER TABLE `member`
	ADD CONSTRAINT `PK_member` -- 회원 기본키
		PRIMARY KEY (
			`memKey` -- PK
		);

ALTER TABLE `member`
	MODIFY COLUMN `memKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 영화
CREATE TABLE `movie` (
	`mKey`      INTEGER       NOT NULL COMMENT 'api의 PK사용', -- PK
	`mTitle`    VARCHAR(200)  NOT NULL COMMENT '제목', -- 제목
	`mSummary`  VARCHAR(8000) NOT NULL COMMENT 'textarea입력', -- 줄거리
	`mTrailer1` VARCHAR(50)   NULL     COMMENT 'text입력 구분자'',''', -- 예고편1
	`mTrailer2` VARCHAR(50)   NULL     COMMENT '예고편2', -- 예고편2
	`mTrailer3` VARCHAR(50)   NULL     COMMENT '예고편3' -- 예고편3
)
COMMENT '영화';

-- 영화
ALTER TABLE `movie`
	ADD CONSTRAINT `PK_movie` -- 영화 기본키
		PRIMARY KEY (
			`mKey` -- PK
		);

-- 찜영화
CREATE TABLE `zzim` (
	`zKey`   INTEGER NOT NULL COMMENT 'PK', -- PK
	`memKey` INTEGER NOT NULL COMMENT '회원PK', -- 회원PK
	`mKey`   INTEGER NOT NULL COMMENT '영화PK' -- 영화PK
)
COMMENT '찜영화';

-- 찜영화
ALTER TABLE `zzim`
	ADD CONSTRAINT `PK_zzim` -- 찜영화 기본키
		PRIMARY KEY (
			`zKey` -- PK
		);

ALTER TABLE `zzim`
	MODIFY COLUMN `zKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 평가한 영화
CREATE TABLE `star` (
	`sKey`   INTEGER NOT NULL COMMENT 'PK', -- PK
	`memKey` INTEGER NOT NULL COMMENT '회원PK', -- 회원PK
	`mKey`   INTEGER NOT NULL COMMENT '영화PK', -- 영화PK
	`star`   INTEGER NOT NULL COMMENT '별점' -- 별점
)
COMMENT '평가한 영화';

-- 평가한 영화
ALTER TABLE `star`
	ADD CONSTRAINT `PK_star` -- 평가한 영화 기본키
		PRIMARY KEY (
			`sKey` -- PK
		);

ALTER TABLE `star`
	MODIFY COLUMN `sKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 무파
CREATE TABLE `movie_Fight` (
	`mfKey`     INTEGER       NOT NULL COMMENT 'PK', -- PK
	`mKey1`     INTEGER       NOT NULL COMMENT '영화1', -- 영화1
	`mKey2`     INTEGER       NOT NULL COMMENT '영화2', -- 영화2
	`mfTitle`   VARCHAR(100)  NULL     COMMENT '제목', -- 제목
	`mfVDate`   VARCHAR(20)   NULL     COMMENT 'yyyy/mm/dd~yyyy/mm/dd', -- 투표기간
	`mfComment` VARCHAR(8000) NULL     COMMENT 'textarea입력', -- 내용
	`mfDate`    TIMESTAMP     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '작성일', -- 작성일
	`mfHit`     INTEGER       NULL     DEFAULT 0 COMMENT '조회수' -- 조회수
)
COMMENT '무파';

-- 무파
ALTER TABLE `movie_Fight`
	ADD CONSTRAINT `PK_movie_Fight` -- 무파 기본키
		PRIMARY KEY (
			`mfKey` -- PK
		);

ALTER TABLE `movie_Fight`
	MODIFY COLUMN `mfKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 매거진
CREATE TABLE `magazine` (
	`magKey`      INTEGER       NOT NULL COMMENT 'PK', -- PK
	`magTitle`    VARCHAR(100)  NULL     COMMENT '제목', -- 제목
	`magSubTitle` VARCHAR(100)  NULL     COMMENT '서브타이틀', -- 서브타이틀
	`magContent`  VARCHAR(8000) NULL     COMMENT '내용', -- 내용
	`magImg`      VARCHAR(2000) NULL     COMMENT '게시글 이미지', -- 게시글 이미지
	`magDate`     TIMESTAMP     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '작성일', -- 작성일
	`magHit`      INTEGER       NULL     DEFAULT 0 COMMENT '조회수', -- 조회수
	`magGood`     INTEGER       NULL     DEFAULT 0 COMMENT '좋아요 총 갯수' -- 좋아요 총 갯수
)
COMMENT '매거진';

-- 매거진
ALTER TABLE `magazine`
	ADD CONSTRAINT `PK_magazine` -- 매거진 기본키
		PRIMARY KEY (
			`magKey` -- PK
		);

ALTER TABLE `magazine`
	MODIFY COLUMN `magKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 포스터 첨부파일
CREATE TABLE `file` (
	`fileKey`    INTEGER      NOT NULL COMMENT 'PK', -- PK
	`mKey`       INTEGER      NOT NULL COMMENT '영화PK', -- 영화PK
	`fileModify` VARCHAR(100) NOT NULL COMMENT '수정파일명' -- 수정파일명
)
COMMENT '포스터 첨부파일';

-- 포스터 첨부파일
ALTER TABLE `file`
	ADD CONSTRAINT `PK_file` -- 포스터 첨부파일 기본키
		PRIMARY KEY (
			`fileKey`, -- PK
			`mKey`     -- 영화PK
		);

ALTER TABLE `file`
	MODIFY COLUMN `fileKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 투표
CREATE TABLE `vote` (
	`vKey`   INTEGER NOT NULL COMMENT 'PK', -- PK
	`memKey` INTEGER NOT NULL COMMENT '회원PK', -- 회원PK
	`mfKey`  INTEGER NOT NULL COMMENT '무파PK', -- 무파PK
	`mKey`   INTEGER NOT NULL COMMENT '투표한 영화' -- 영화PK
)
COMMENT '투표';

-- 투표
ALTER TABLE `vote`
	ADD CONSTRAINT `PK_vote` -- 투표 기본키
		PRIMARY KEY (
			`vKey` -- PK
		);

ALTER TABLE `vote`
	MODIFY COLUMN `vKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 무파-댓글
CREATE TABLE `mf_Reply` (
	`repKey`     INTEGER       NOT NULL COMMENT 'PK', -- PK
	`memKey`     INTEGER       NOT NULL COMMENT '회원PK', -- 회원PK
	`mfKey`      INTEGER       NOT NULL COMMENT '무파PK', -- 무파PK
	`mKey`       INTEGER       NOT NULL COMMENT '댓글쓴 영화', -- 영화PK
	`repName`    VARCHAR(100)  NULL     COMMENT '이름', -- 이름
	`repContent` VARCHAR(8000) NULL     COMMENT '내용', -- 내용
	`repDate`    TIMESTAMP     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '작성일', -- 작성일
	`repGood`    INTEGER       NULL     DEFAULT 0 COMMENT '좋아요' -- 좋아요
)
COMMENT '무파-댓글';

-- 무파-댓글
ALTER TABLE `mf_Reply`
	ADD CONSTRAINT `PK_mf_Reply` -- 무파-댓글 기본키
		PRIMARY KEY (
			`repKey` -- PK
		);

ALTER TABLE `mf_Reply`
	MODIFY COLUMN `repKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 영화-댓글
CREATE TABLE `m_Reply` (
	`reKey`     INTEGER       NOT NULL COMMENT 'PK', -- PK
	`memKey`    INTEGER       NOT NULL COMMENT '회원PK', -- 회원PK
	`mKey`      INTEGER       NOT NULL COMMENT '영화PK', -- 영화PK
	`memName`   VARCHAR(100)  NOT NULL COMMENT '이름', -- 이름
	`reContent` VARCHAR(2000) NULL     COMMENT '내용', -- 내용
	`reDate`    TIMESTAMP     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '작성일', -- 작성일
	`reGood`    INTEGER       NULL     COMMENT '좋아요' -- 좋아요
)
COMMENT '영화-댓글';

-- 영화-댓글
ALTER TABLE `m_Reply`
	ADD CONSTRAINT `PK_m_Reply` -- 영화-댓글 기본키
		PRIMARY KEY (
			`reKey` -- PK
		);

ALTER TABLE `m_Reply`
	MODIFY COLUMN `reKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 매거진-좋아요
CREATE TABLE `good` (
	`GKey`   INTEGER NOT NULL COMMENT 'PK', -- PK
	`magKey` INTEGER NOT NULL COMMENT '매거진PK', -- 매거진PK
	`memKey` INTEGER NOT NULL COMMENT '회원PK' -- 회원PK
)
COMMENT '매거진-좋아요';

-- 매거진-좋아요
ALTER TABLE `good`
	ADD CONSTRAINT `PK_good` -- 매거진-좋아요 기본키
		PRIMARY KEY (
			`GKey` -- PK
		);

ALTER TABLE `good`
	MODIFY COLUMN `GKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 스틸샷 첨부파일
CREATE TABLE `stillShot` (
	`fileKey`    INTEGER      NOT NULL COMMENT 'PK', -- PK
	`mKey`       INTEGER      NOT NULL COMMENT '영화PK', -- 영화PK
	`fileModify` VARCHAR(100) NOT NULL COMMENT '수정파일명' -- 수정파일명
)
COMMENT '스틸샷 첨부파일';

-- 스틸샷 첨부파일
ALTER TABLE `stillShot`
	ADD CONSTRAINT `PK_stillShot` -- 스틸샷 첨부파일 기본키
		PRIMARY KEY (
			`fileKey` -- PK
		);

ALTER TABLE `stillShot`
	MODIFY COLUMN `fileKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 무파-댓글-좋아요
CREATE TABLE `mf_Good` (
	`GKey`   INTEGER NOT NULL COMMENT 'PK', -- PK
	`repKey` INTEGER NOT NULL COMMENT '무파-댓글PK', -- 무파-댓글PK
	`memKey` INTEGER NOT NULL COMMENT '회원PK' -- 회원PK
)
COMMENT '무파-댓글-좋아요';

-- 무파-댓글-좋아요
ALTER TABLE `mf_Good`
	ADD CONSTRAINT `PK_mf_Good` -- 무파-댓글-좋아요 기본키
		PRIMARY KEY (
			`GKey` -- PK
		);

ALTER TABLE `mf_Good`
	MODIFY COLUMN `GKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 매거진 이미지
CREATE TABLE `uploadImage` (
	`imageKey`      INTEGER      NOT NULL COMMENT 'PK', -- PK
	`magKey`        INTEGER      NOT NULL COMMENT 'PK2', -- PK2
	`fileOriginal`  VARCHAR(100) NOT NULL COMMENT '원본파일명', -- 원본파일명
	`fileModify`    VARCHAR(100) NOT NULL COMMENT '수정파일명', -- 수정파일명
	`fileDirectory` VARCHAR(100) NOT NULL COMMENT '파일 디렉토리' -- 파일 디렉토리
)
COMMENT '매거진 이미지';

-- 매거진 이미지
ALTER TABLE `uploadImage`
	ADD CONSTRAINT `PK_uploadImage` -- 매거진 이미지 기본키
		PRIMARY KEY (
			`imageKey` -- PK
		);

ALTER TABLE `uploadImage`
	MODIFY COLUMN `imageKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PK';

-- 영화-댓글-좋아요
CREATE TABLE `m_Good` (
	`GKey`   INTEGER NOT NULL COMMENT '새 컬럼', -- 새 컬럼
	`reKey`  INTEGER NOT NULL COMMENT '영화-댓글PK', -- 영화-댓글PK
	`memKey` INTEGER NOT NULL COMMENT '회원PK' -- 회원PK
)
COMMENT '영화-댓글-좋아요';

-- 영화-댓글-좋아요
ALTER TABLE `m_Good`
	ADD CONSTRAINT `PK_m_Good` -- 영화-댓글-좋아요 기본키
		PRIMARY KEY (
			`GKey` -- 새 컬럼
		);

ALTER TABLE `m_Good`
	MODIFY COLUMN `GKey` INTEGER NOT NULL AUTO_INCREMENT COMMENT '새 컬럼';

-- 찜영화
ALTER TABLE `zzim`
	ADD CONSTRAINT `FK_member_TO_zzim` -- 회원 -> 찜영화
		FOREIGN KEY (
			`memKey` -- 회원PK
		)
		REFERENCES `member` ( -- 회원
			`memKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 찜영화
ALTER TABLE `zzim`
	ADD CONSTRAINT `FK_movie_TO_zzim` -- 영화 -> 찜영화
		FOREIGN KEY (
			`mKey` -- 영화PK
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 평가한 영화
ALTER TABLE `star`
	ADD CONSTRAINT `FK_member_TO_star` -- 회원 -> 평가한 영화
		FOREIGN KEY (
			`memKey` -- 회원PK
		)
		REFERENCES `member` ( -- 회원
			`memKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 평가한 영화
ALTER TABLE `star`
	ADD CONSTRAINT `FK_movie_TO_star` -- 영화 -> 평가한 영화
		FOREIGN KEY (
			`mKey` -- 영화PK
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 무파
ALTER TABLE `movie_Fight`
	ADD CONSTRAINT `FK_movie_TO_movie_Fight` -- 영화 -> 무파
		FOREIGN KEY (
			`mKey1` -- 영화1
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 무파
ALTER TABLE `movie_Fight`
	ADD CONSTRAINT `FK_movie_TO_movie_Fight2` -- 영화 -> 무파2
		FOREIGN KEY (
			`mKey2` -- 영화2
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 포스터 첨부파일
ALTER TABLE `file`
	ADD CONSTRAINT `FK_movie_TO_file` -- 영화 -> 포스터 첨부파일
		FOREIGN KEY (
			`mKey` -- 영화PK
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 투표
ALTER TABLE `vote`
	ADD CONSTRAINT `FK_movie_Fight_TO_vote` -- 무파 -> 투표
		FOREIGN KEY (
			`mfKey` -- 무파PK
		)
		REFERENCES `movie_Fight` ( -- 무파
			`mfKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 투표
ALTER TABLE `vote`
	ADD CONSTRAINT `FK_member_TO_vote` -- 회원 -> 투표
		FOREIGN KEY (
			`memKey` -- 회원PK
		)
		REFERENCES `member` ( -- 회원
			`memKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 투표
ALTER TABLE `vote`
	ADD CONSTRAINT `FK_movie_TO_vote` -- 영화 -> 투표
		FOREIGN KEY (
			`mKey` -- 영화PK
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 무파-댓글
ALTER TABLE `mf_Reply`
	ADD CONSTRAINT `FK_movie_Fight_TO_mf_Reply` -- 무파 -> 무파-댓글
		FOREIGN KEY (
			`mfKey` -- 무파PK
		)
		REFERENCES `movie_Fight` ( -- 무파
			`mfKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 무파-댓글
ALTER TABLE `mf_Reply`
	ADD CONSTRAINT `FK_member_TO_mf_Reply` -- 회원 -> 무파-댓글
		FOREIGN KEY (
			`memKey` -- 회원PK
		)
		REFERENCES `member` ( -- 회원
			`memKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 무파-댓글
ALTER TABLE `mf_Reply`
	ADD CONSTRAINT `FK_movie_TO_mf_Reply` -- 영화 -> 무파-댓글
		FOREIGN KEY (
			`mKey` -- 영화PK
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 영화-댓글
ALTER TABLE `m_Reply`
	ADD CONSTRAINT `FK_movie_TO_m_Reply` -- 영화 -> 영화-댓글
		FOREIGN KEY (
			`mKey` -- 영화PK
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 영화-댓글
ALTER TABLE `m_Reply`
	ADD CONSTRAINT `FK_member_TO_m_Reply` -- 회원 -> 영화-댓글
		FOREIGN KEY (
			`memKey` -- 회원PK
		)
		REFERENCES `member` ( -- 회원
			`memKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 매거진-좋아요
ALTER TABLE `good`
	ADD CONSTRAINT `FK_magazine_TO_good` -- 매거진 -> 매거진-좋아요
		FOREIGN KEY (
			`magKey` -- 매거진PK
		)
		REFERENCES `magazine` ( -- 매거진
			`magKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 매거진-좋아요
ALTER TABLE `good`
	ADD CONSTRAINT `FK_member_TO_good` -- 회원 -> 매거진-좋아요
		FOREIGN KEY (
			`memKey` -- 회원PK
		)
		REFERENCES `member` ( -- 회원
			`memKey` -- PK
		);

-- 스틸샷 첨부파일
ALTER TABLE `stillShot`
	ADD CONSTRAINT `FK_movie_TO_stillShot` -- 영화 -> 스틸샷 첨부파일
		FOREIGN KEY (
			`mKey` -- 영화PK
		)
		REFERENCES `movie` ( -- 영화
			`mKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 무파-댓글-좋아요
ALTER TABLE `mf_Good`
	ADD CONSTRAINT `FK_mf_Reply_TO_mf_Good` -- 무파-댓글 -> 무파-댓글-좋아요
		FOREIGN KEY (
			`repKey` -- 무파-댓글PK
		)
		REFERENCES `mf_Reply` ( -- 무파-댓글
			`repKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 무파-댓글-좋아요
ALTER TABLE `mf_Good`
	ADD CONSTRAINT `FK_member_TO_mf_Good` -- 회원 -> 무파-댓글-좋아요
		FOREIGN KEY (
			`memKey` -- 회원PK
		)
		REFERENCES `member` ( -- 회원
			`memKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 매거진 이미지
ALTER TABLE `uploadImage`
	ADD CONSTRAINT `FK_magazine_TO_uploadImage` -- 매거진 -> 매거진 이미지
		FOREIGN KEY (
			`magKey` -- PK2
		)
		REFERENCES `magazine` ( -- 매거진
			`magKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 영화-댓글-좋아요
ALTER TABLE `m_Good`
	ADD CONSTRAINT `FK_m_Reply_TO_m_Good` -- 영화-댓글 -> 영화-댓글-좋아요
		FOREIGN KEY (
			`reKey` -- 영화-댓글PK
		)
		REFERENCES `m_Reply` ( -- 영화-댓글
			`reKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 영화-댓글-좋아요
ALTER TABLE `m_Good`
	ADD CONSTRAINT `FK_member_TO_m_Good` -- 회원 -> 영화-댓글-좋아요
		FOREIGN KEY (
			`memKey` -- 회원PK
		)
		REFERENCES `member` ( -- 회원
			`memKey` -- PK
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;