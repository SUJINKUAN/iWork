package tw.com.ourProject.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Punch;
import tw.com.ourProject.service.PunchService;
import tw.com.ourProject.utils.JWTUtil;

@RestController
public class PunchController {

	
	@Autowired
	public PunchService punchService;
	@Autowired
	JWTUtil jwtUtil;
	
	 
	@PostMapping("/Punch/saveInfo")
	public void savePunchInfo(@RequestBody String punchData) {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
		JSONObject infoObj = JSONObject.parseArray(punchData).getJSONObject(0);
		System.out.println("存入的打卡資訊："+infoObj.toJSONString());
		System.out.println("存入的打卡人："+jwtUtil.getInfoFromJwtToken(infoObj.getString("person"), "empId"));
		punchService.savePunchInfo(infoObj.getString("status"),
									format.parse(infoObj.getString("time")),
									jwtUtil.getInfoFromJwtToken(infoObj.getString("person"), "empId"),
									infoObj.getString("locationLat"),
									infoObj.getString("locationLng")
									);
		
	    }catch(Exception e) {
	    	
	    }
	}
	
	@PostMapping("/Punch/getInfo")
	public List<Punch> getPunchInfo(@RequestBody String punchData) {
		List<Punch> getPunchInfo = punchService.getPunchInfo(jwtUtil.getInfoFromJwtToken((JSONObject.parseArray(punchData).getJSONObject(0).getString("person")),"empId"));
		return getPunchInfo;
	}
}
