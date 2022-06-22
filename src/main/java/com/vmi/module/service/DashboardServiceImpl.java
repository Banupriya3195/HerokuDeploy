package com.vmi.module.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.vmi.module.model.ItemMaster;
import com.vmi.module.model.ItemZone;
import com.vmi.module.model.SearchProductRequestValues;
import com.vmi.module.model.Users;
import com.vmi.module.model.WareHouseMapping;
import com.vmi.module.repo.CompanyDetailsRepo;
import com.vmi.module.repo.IndentRepo;
import com.vmi.module.repo.ItemMasterRepo;
import com.vmi.module.repo.WareHouseMappingRepo;
import com.vmi.module.repo.WareHouseRepo;

@Transactional
@Service("DashboardService")
public class DashboardServiceImpl implements DashboardService {
	@Autowired
	private WareHouseMappingRepo wareHouseMappingRepo;
	@Autowired
	private CompanyDetailsRepo companyDetailsRepo;
	@Autowired
	private WareHouseRepo wareHouseRepo;
	@Autowired
	private ItemMasterRepo itemMasterRepo;
	@Autowired
	private IndentRepo indentRepo;

	public Map<String, Object> getCurrentItemZone(Users user, String wareHouseId) {
		Map<String, Object> responseDataHashMap = new HashMap();

		List<ItemZone> itemZone = new ArrayList();
		List<ItemMaster> itemmaster = new ArrayList();

		long green = 0L;
		long yellow = 0L;
		long red = 0L;
		long blue = 0L;
		long grey = 0L;
		List<WareHouseMapping> wareHouseMapping;
		if (StringUtils.isNotBlank(wareHouseId)) {
			wareHouseMapping = this.wareHouseMappingRepo.findByWareHouse(wareHouseId);
		} else {
			wareHouseMapping = this.wareHouseMappingRepo.findByWareHouseIn(user.getWareHouse());
		}
		for (WareHouseMapping warehouse : wareHouseMapping) {
			if (warehouse.getGreenZone() < warehouse.getCurrentStock()) {
				warehouse.getItemMaster().setItemZone("#398bf7");
				itemmaster.add(warehouse.getItemMaster());
				blue += 1L;
			} else if ((warehouse.getGreenZone() >= warehouse.getCurrentStock())
					&& (warehouse.getCurrentStock() > warehouse.getYellowZone())) {
				warehouse.getItemMaster().setItemZone("#06d79c");
				itemmaster.add(warehouse.getItemMaster());
				green += 1L;
			} else if ((warehouse.getYellowZone() >= warehouse.getCurrentStock())
					&& (warehouse.getCurrentStock() > warehouse.getRedZone())) {
				warehouse.getItemMaster().setItemZone("#ffb22b");
				itemmaster.add(warehouse.getItemMaster());
				yellow += 1L;
			} else if (warehouse.getCurrentStock() == 0L) {
				warehouse.getItemMaster().setItemZone("#777777");
				itemmaster.add(warehouse.getItemMaster());
				red += 1L;
			} else if (warehouse.getRedZone() >= warehouse.getCurrentStock()) {
				warehouse.getItemMaster().setItemZone("#ef5350");
				itemmaster.add(warehouse.getItemMaster());
				grey += 1L;
			}
		}
		itemZone.add(new ItemZone(blue, "#398bf7"));
		itemZone.add(new ItemZone(green, "#06d79c"));
		itemZone.add(new ItemZone(yellow, "#ffb22b"));
		itemZone.add(new ItemZone(red, "#ef5350"));
		itemZone.add(new ItemZone(grey, "#777777"));
		responseDataHashMap.put("itemMaster", itemmaster);
		responseDataHashMap.put("itemZone", itemZone);
		return responseDataHashMap;
	}

	public Map<String, Object> getDashboardCount(Users loginUser) {
		Map<String, Object> responseDataHashMap = new HashMap();
		if ("ADMIN".equals(loginUser.getRole().getRole())) {
			responseDataHashMap.put("companyCount",
					Long.valueOf(this.companyDetailsRepo.countByCreateUser(loginUser.getEmpCode())));
			responseDataHashMap.put("wareHouseCount",
					Long.valueOf(this.wareHouseRepo.countByCreateBy(loginUser.getEmpCode())));
			responseDataHashMap.put("itemMasterCount",
					Long.valueOf(this.itemMasterRepo.countByCreateBy(loginUser.getEmpCode())));
			responseDataHashMap.put("indentCount", Long.valueOf(this.itemMasterRepo.count()));
		} else {
			responseDataHashMap.put("companyCount",
					Long.valueOf(this.companyDetailsRepo.countByCreateUser(loginUser.getCreateBy())));
			responseDataHashMap.put("wareHouseCount",
					Long.valueOf(this.wareHouseRepo.countByCreateBy(loginUser.getCreateBy())));
			responseDataHashMap.put("itemMasterCount",
					Long.valueOf(this.itemMasterRepo.countByCreateBy(loginUser.getCreateBy())));
			responseDataHashMap.put("indentCount",
					Long.valueOf(this.indentRepo.countByCreateUser(loginUser.getEmpCode())));
		}
		return responseDataHashMap;
	}

	@Override
	public String getSearch(String query,int start, int count) {
		RestTemplate rs = new RestTemplate();
		String result= "";
		SearchProductRequestValues searchRequest = new SearchProductRequestValues();
		searchRequest.setCount(count);
		searchRequest.setDisabled(true);
		searchRequest.setQuery(query);
		searchRequest.setStart(start);
		searchRequest.setStrQuery("vmiminimalsuggest");

		ResponseEntity<String> coreRespObj = rs.postForEntity("https://v-marketplace.net/beta/vmilist?lang=en&store=DEFAULT",
				searchRequest, String.class);
		if (coreRespObj.getStatusCode() == HttpStatus.OK)
			result = coreRespObj.getBody();
		return result;
	}
}
