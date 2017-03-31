package com.xiaozan.common.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobTest {
	
	private static final Logger logger = LoggerFactory.getLogger(JobTest.class);

	@Scheduled(cron = "0/1 * * * * ?")
	public void run() {
		logger.info("Job Start");
		for (int i = 0; i < 1000000000; i++) {
			System.out.println(i);
		}
		logger.info("Job ENd");
	}
}
