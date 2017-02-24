package org.mf.project.cron;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mf.project.storage.BoxOffice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	// @Scheduled(cron = "*/20 * * * * *") //20초간격
	@Scheduled(cron = "0 */5 * * * *")//5분간격
	public void boxoffice_cron() {

		Date now = new Date();
		Date yesterday = new Date();

		// yesterday의 날짜를 현재시각에서 24시간 전으로 바꿔준다
		yesterday.setTime(now.getTime() - (1000 * 60 * 60 * 24));

		// 날짜형식을 정해준다
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(yesterday);
		String recently_Day = BoxOffice.getRecently_Day();

		if (!today.equals(recently_Day)) {
			// 최근이 아니면 update
			BoxOffice.updateBoxOffice();
			logger.info("업데이트 하였습니다.");
		}
		// 최근이면 pass
		logger.info("업데이트하지 않았습니다.");

	}

}
