package tw.com.ourProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.com.ourProject.model.RestExcelBean;
import tw.com.ourProject.model.Restaurant;
import tw.com.ourProject.repository.RestaurantRepo;


@Service
public class RestaurantService {
	@Autowired
	public RestaurantRepo restrepo;
	
	public String parseExcel(MultipartFile file) {
		List<tw.com.ourProject.model.RestExcelBean> list = null;
        try {
            list = tw.com.ourProject.utils.RestExcelUtils.excelToShopIdList(file.getInputStream());
            if (list == null || list.size() <= 0) {
                return "導入的數據為空";
            }
            //excel的數據存到資料庫
            try {
            		for (RestExcelBean excel : list) {
                    System.out.println(excel.toString());
                    
                    Restaurant rest = new Restaurant();
                    rest.setRestaurantName(excel.getRestNum());
                    rest.setTel(excel.getRestTel());
                    rest.setAddr(excel.getRestAddr());
                    restrepo.save(rest);
                   
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "保存成功";
    }
	
}
