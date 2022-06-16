package tw.com.ourProject.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;

import tw.com.ourProject.model.Calendar;
import tw.com.ourProject.service.CalendarService;

@RestController
public class CalendarController {
	@Autowired
	public CalendarService calendarService;
	
	@GetMapping("/Calendar/allEvent")
	public List<Calendar> findAllevent(){
		List<Calendar> findevent = calendarService.findEvents();
		return findevent;
	}
	
	@PostMapping("/Calendar/saveEvent")
	public void insertCalendar(@RequestBody JSONArray calendarInfo) {
		try{
			String obj1 = calendarInfo.getJSONObject(0).get("calendarType").toString();
			String obj2 = calendarInfo.getJSONObject(0).get("calendarTitle").toString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date obj3 = format.parse(calendarInfo.getJSONObject(0).get("eventStart").toString());
			Date obj4 = format.parse(calendarInfo.getJSONObject(0).get("eventEnd").toString());
			String obj5 = calendarInfo.getJSONObject(0).get("allDay").toString();
			
			calendarService.saveEvent(obj1 , obj2 , obj3 , obj4 , obj5);
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}

}
