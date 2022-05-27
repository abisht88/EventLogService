package com.log.assignment;

import com.log.assignment.service.EventService;
import com.log.assignment.template.DataMinerTemplate;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@Log
@Component
public class Application {

	@Autowired
	DataMinerTemplate dataMinerTemplate;

	@Autowired
	EventService eventService;


	public static void main(String[] args) throws IOException, URISyntaxException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.log.assignment");
		context.refresh();
		Application bean = context.getBean(Application.class);

		if (args.length != 0) {
			log.info("file path " + args[0]);
			bean.processLog(args[0]);
		} else {
			log.warning("file path not available ... reading the default path from resources folder");
			URI uri = ClassLoader.getSystemResource("logfile.json").toURI();
			String path = Paths.get(uri).toString();
			bean.processLog(path);
		}


		bean.fetchData();

		context.close();
	}

	private void fetchData() {
		System.out.println(eventService.findAllEvent());
	}

	private void processLog(String path) throws IOException {
		dataMinerTemplate.dataMining(path);
	}
}
