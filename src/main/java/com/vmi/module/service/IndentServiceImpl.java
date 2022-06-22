package com.vmi.module.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmi.module.model.Applicationproperties;
import com.vmi.module.model.CompanyProfile;
import com.vmi.module.model.IndentData;
import com.vmi.module.model.IndentDetails;
import com.vmi.module.model.IndentStatus;
import com.vmi.module.model.IndentStatusTrack;
import com.vmi.module.model.ItemMaster;
import com.vmi.module.model.Sch;
import com.vmi.module.model.Users;
import com.vmi.module.model.WareHouseDetails;
import com.vmi.module.model.WareHouseMapping;
import com.vmi.module.repo.ApplicationPropertiesRepo;
import com.vmi.module.repo.CompanyDetailsRepo;
import com.vmi.module.repo.IndentRepo;
import com.vmi.module.repo.IndentStatusRepo;
import com.vmi.module.repo.IndentStatusTrackRepo;
import com.vmi.module.repo.ItemMasterRepo;
import com.vmi.module.repo.SupplyChainRepo;
import com.vmi.module.repo.WareHouseMappingRepo;
import com.vmi.module.repo.WareHouseRepo;

@Transactional
@Service("IndentService")
public class IndentServiceImpl implements IndentService {
	@Autowired
	private IndentRepo indentRepo;
	@Autowired
	private CompanyDetailsRepo companyRepo;
	@Autowired
	private WareHouseRepo wareHouseRepo;
	@Autowired
	private ItemMasterRepo itemMasterRepo;
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	@Autowired
	private WareHouseMappingRepo wareHouseMappingRepo;
	@Autowired
	private SupplyChainRepo supplyChainRepo;
	@Autowired
	private IndentStatusRepo indentStatusRepo;
	@Autowired
	private IndentStatusTrackRepo indentStatusTrackRepo;
	@Autowired
	private ApplicationPropertiesRepo applicationPropertiesRepo;
	ObjectMapper objMapper = new ObjectMapper();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public Map<String, Object> saveDetails(IndentDetails reqObj, HttpSession paramHttpSession) {
		Map<String, Object> responseHashMap = new HashMap();
		IndentDetails indentDetailsObj = null;
		WareHouseMapping wareHouseMappingdata = null;
		IndentStatus indentStatus = new IndentStatus();
		List<IndentStatusTrack> indentStatusTrack = new ArrayList();
		try {
			this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			if (reqObj.getId() != null) {
				indentDetailsObj = this.indentRepo.findById(reqObj.getId());
			}
			if (indentDetailsObj != null) {
				if (reqObj.getIndentsTrack() != null) {
					indentStatusTrack = reqObj.getIndentsTrack();
					for (IndentStatusTrack indentStatusObj : indentStatusTrack) {
						if (indentStatusObj.getId() == null) {
							wareHouseMappingdata = this.wareHouseMappingRepo.findByWareHouseAndItemMaster(
									indentDetailsObj.getWareHouse().getId(), indentDetailsObj.getItemMaster().getId());
							wareHouseMappingdata.setCurrentStock(
									wareHouseMappingdata.getCurrentStock() + indentStatusObj.getIndentRecQuantity());
							indentStatusObj.setIndentId(reqObj.getId());
							this.wareHouseMappingRepo.save(wareHouseMappingdata);
							this.indentStatusTrackRepo.save(indentStatusObj);
						}
					}
				}
				if (reqObj.getIndentStatus() != null) {
					indentDetailsObj.setIndentStatus(reqObj.getIndentStatus());
				}
				responseHashMap.put("message", "data updated !!");
			} else {
				Applicationproperties properties = this.applicationPropertiesRepo.findByValId(2L);
				reqObj.setId(null);
				indentDetailsObj = (IndentDetails) this.objMapper.convertValue(reqObj, IndentDetails.class);
				wareHouseMappingdata = this.wareHouseMappingRepo.findByWareHouseAndItemMaster(
						indentDetailsObj.getWareHouse().getId(), indentDetailsObj.getItemMaster().getId());
				indentDetailsObj.setIndentNo(this.sequenceGenerator.generateSequence("indents_sequence"));
				wareHouseMappingdata
						.setCurrentStock(wareHouseMappingdata.getCurrentStock() - reqObj.getIndentQuantity());
				if (wareHouseMappingdata.getCurrentStock() >= wareHouseMappingdata.getGreenZone()) {
					indentDetailsObj.setIndentPriority("#06d79c");
				} else if ((wareHouseMappingdata.getGreenZone() >= wareHouseMappingdata.getCurrentStock())
						&& (wareHouseMappingdata.getCurrentStock() >= wareHouseMappingdata.getYellowZone())) {
					indentDetailsObj.setIndentPriority("#ffb22b");
				} else if (wareHouseMappingdata.getCurrentStock() <= wareHouseMappingdata.getYellowZone()) {
					indentDetailsObj.setIndentPriority("#ef5350");
				}
				indentStatus.setId(properties.getPropertyValue());
				indentDetailsObj.setIndentStatus(indentStatus);
				this.wareHouseMappingRepo.save(wareHouseMappingdata);
				responseHashMap.put("message", "data inserted !!");
			}
			indentDetailsObj = (IndentDetails) this.indentRepo.save(indentDetailsObj);
			responseHashMap.put("indents", indentDetailsObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseHashMap;
	}

	public Date stringtoDate(String date) {
		Date convertDate = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			convertDate = formatter.parse(formatter.format(initDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertDate;
	}

	public Map<String, Object> deleteById(String id) {
		Map<String, Object> responseDataHashMap = new HashMap();
		IndentDetails indentDetailsObj = this.indentRepo.findById(id.trim());
		if (indentDetailsObj != null) {
			this.indentRepo.delete(indentDetailsObj);
			responseDataHashMap.put("message", "data deleted");
		} else {
			responseDataHashMap.put("message", "No data found");
		}
		return responseDataHashMap;
	}

	public Map<String, Object> getListIndents(Users loginUser, String type) {
		Map<String, Object> responseDataHashMap = new HashMap();
		if ("parent".equals(type)) {
			List<IndentDetails> indentDetailsObj = this.indentRepo.findByWareHouseIn(loginUser.getWareHouse());
			if (indentDetailsObj != null) {
				countOfColours(indentDetailsObj, responseDataHashMap);
				responseDataHashMap.put("indents", indentDetailsObj);
			} else {
				responseDataHashMap.put("message", "No data found");
			}
		} else {
			List<String> wareHouseId = new ArrayList();
			for (WareHouseDetails warehouse_element : loginUser.getWareHouse()) {
				wareHouseId.add(warehouse_element.getId());
			}
			List<WareHouseDetails> wareHouseDetails = new ArrayList();
			List<Sch> supplyChainObj = this.supplyChainRepo.findByParentWarehouseIdIn(wareHouseId);
			for (Sch sch : supplyChainObj) {
				wareHouseDetails.add(sch.getWareHouse());
			}
			List<IndentDetails> indentDetailsObj = this.indentRepo.findByWareHouseIn(wareHouseDetails);
			if (indentDetailsObj != null) {
				countOfColours(indentDetailsObj, responseDataHashMap);
				responseDataHashMap.put("indents", indentDetailsObj);
			} else {
				responseDataHashMap.put("message", "No data found");
			}
		}
		return responseDataHashMap;
	}

	public Map<String, Object> getColourListIndents(String id, String colour) {
		Map<String, Object> responseDataHashMap = new HashMap();
		List<IndentDetails> indentDetailsObj = this.indentRepo.findByWareHouseAndIndentPriority(id, colour);
		if (indentDetailsObj != null) {
			responseDataHashMap.put("indents", indentDetailsObj);
		} else {
			responseDataHashMap.put("message", "No data found");
		}
		return responseDataHashMap;
	}

	public Map<String, Object> getDateRangeListIndents(String id, String fromdate, String todate) {
		Date fromDate = null;
		Date toDate = null;
		try {
			toDate = new SimpleDateFormat("yyyy-MM-dd").parse(todate);
			fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, Object> responseDataHashMap = new HashMap();
		List<IndentDetails> indentDetailsObj = null;
		indentDetailsObj = this.indentRepo.findByWareHouseAndIndentDateBetween(id, fromDate, toDate);
		if (indentDetailsObj != null) {
			responseDataHashMap.put("indents", indentDetailsObj);
		} else {
			responseDataHashMap.put("message", "No data found");
		}
		return responseDataHashMap;
	}

	public Map<String, Object> getIndentFile(MultipartFile file) throws ParseException {
		Map<String, Object> responseDataHashMap = new HashMap();
		try {
			InputStream inStream = file.getInputStream();

			Workbook wb = WorkbookFactory.create(inStream);
			Sheet sheet = wb.getSheetAt(0);
			List<IndentData> indentData = new ArrayList();
			IndentData indentObj = null;
			for (Row row : sheet) {
				if (row.getRowNum() != 0) {
					indentObj = new IndentData();
					if ((row.getCell(0) != null) && (row.getCell(0).toString() != "") && (row.getCell(1) != null)
							&& (row.getCell(1).toString() != "") && (row.getCell(2) != null)
							&& (row.getCell(2).toString() != "") && (row.getCell(3) != null)
							&& (row.getCell(3).toString() != "") && (row.getCell(4) != null)
							&& (row.getCell(4).toString() != "")) {
						indentObj.setCompanyCode(row.getCell(0).toString().trim());
						indentObj.setWarehouseCode(row.getCell(1).toString().trim());
						indentObj.setItemCode(row.getCell(2).toString().trim());
						indentObj.setQuantityConsumed(Long.valueOf(row.getCell(3).toString().trim()).longValue());
						indentObj.setIndentDate(row.getCell(4).toString().trim());
						indentData.add(indentObj);
					}
				}
			}
			IndentDetails indentDetails = null;
			ItemMaster itemMaster = null;
			CompanyProfile companyProfile = null;
			WareHouseDetails wareHouseDetails = null;
			WareHouseMapping wareHouseMappingdata = null;
			IndentStatus indentStatus = new IndentStatus();
			List<IndentData> incompleteRecords = new ArrayList();
			Applicationproperties properties = this.applicationPropertiesRepo.findByValId(2L);
			for (IndentData indent : indentData) {
				indentDetails = new IndentDetails();
				itemMaster = this.itemMasterRepo.findByItemCode(indent.getItemCode());
				companyProfile = this.companyRepo.findByCode(indent.getCompanyCode());
				wareHouseDetails = this.wareHouseRepo.findByWarehouseCode(indent.getWarehouseCode());
				if ((itemMaster != null) && (wareHouseDetails != null)) {
					wareHouseMappingdata = this.wareHouseMappingRepo
							.findByWareHouseAndItemMaster(wareHouseDetails.getId(), itemMaster.getId());
				}
				if ((itemMaster != null) && (companyProfile != null) && (wareHouseDetails != null)
						&& (wareHouseMappingdata != null)) {
					Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(indent.getIndentDate());
					indent.setIndentDate(this.formatter.format(initDate));
					this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					indentDetails = (IndentDetails) this.objMapper.convertValue(indent, IndentDetails.class);
					indentDetails.setCompany(companyProfile);
					indentDetails.setItemMaster(itemMaster);
					indentDetails.setWareHouse(wareHouseDetails);
					indentDetails.setIndentNo(this.sequenceGenerator.generateSequence("indents_sequence"));
					indentDetails.setIndentQuantity(indent.getQuantityConsumed());
					wareHouseMappingdata
							.setCurrentStock(wareHouseMappingdata.getCurrentStock() - indent.getQuantityConsumed());
					if (wareHouseMappingdata.getCurrentStock() >= wareHouseMappingdata.getGreenZone()) {
						indentDetails.setIndentPriority("#06d79c");
					} else if ((wareHouseMappingdata.getGreenZone() >= wareHouseMappingdata.getCurrentStock())
							&& (wareHouseMappingdata.getCurrentStock() >= wareHouseMappingdata.getYellowZone())) {
						indentDetails.setIndentPriority("#ffb22b");
					} else if (wareHouseMappingdata.getCurrentStock() <= wareHouseMappingdata.getYellowZone()) {
						indentDetails.setIndentPriority("#ef5350");
					}
					indentStatus.setId(properties.getPropertyValue());
					indentDetails.setIndentStatus(indentStatus);
					indentDetails = (IndentDetails) this.indentRepo.save(indentDetails);
					this.wareHouseMappingRepo.save(wareHouseMappingdata);
				} else {
					incompleteRecords.add(indent);
				}
			}
			if (incompleteRecords.isEmpty()) {
				responseDataHashMap.put("message", "all data Sucessfully Updated");
			} else {
				responseDataHashMap.put("incompleteRecords", incompleteRecords);
				responseDataHashMap.put("message", "all data Sucessfully Updated");
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return responseDataHashMap;
	}

	public Map<String, Object> countOfColours(List<IndentDetails> indentDetailsObj,
			Map<String, Object> responseDataHashMap) {
		long green = 0L;
		long yellow = 0L;
		long red = 0L;
		long blue = 0L;
		long grey = 0L;
		for (IndentDetails indentDetails : indentDetailsObj) {
			WareHouseMapping wareHouseMappingdata = null;
			if ("#06d79c".equals(indentDetails.getIndentPriority())) {
				green += 1L;
			}
			if ("#ffb22b".equals(indentDetails.getIndentPriority())) {
				yellow += 1L;
			}
			if ("#ef5350".equals(indentDetails.getIndentPriority())) {
				red += 1L;
			}
			if ((indentDetails.getItemMaster().getId() != null) && (indentDetails.getWareHouse().getId() != null)) {
				wareHouseMappingdata = this.wareHouseMappingRepo.findByWareHouseAndItemMaster(
						indentDetails.getWareHouse().getId(), indentDetails.getItemMaster().getId());
				if (wareHouseMappingdata.getCurrentStock() > wareHouseMappingdata.getGreenZone()) {
					blue += 1L;
				}
				if (wareHouseMappingdata.getCurrentStock() == 0L) {
					grey += 1L;
				}
			}
		}
		responseDataHashMap.put("green", Long.valueOf(green));
		responseDataHashMap.put("yellow", Long.valueOf(yellow));
		responseDataHashMap.put("red", Long.valueOf(red));
		responseDataHashMap.put("blue", Long.valueOf(blue));
		responseDataHashMap.put("grey", Long.valueOf(grey));
		return responseDataHashMap;
	}

	public Date convertDate(String date) {
		Date dt = null;
		try {
			dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(5, 1);
		dt = c.getTime();

		return dt;
	}

	public Map<String, Object> saveDetails(IndentStatus reqObj, HttpSession session) {
		Map<String, Object> responseHashMap = new HashMap();

		this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		IndentStatus indentstatus = this.indentStatusRepo.findById(reqObj.getId());
		if (indentstatus != null) {
			reqObj.setCreateDate(indentstatus.getCreateDate());
			reqObj.setCreateBy(indentstatus.getCreateBy());
			indentstatus = (IndentStatus) this.objMapper.convertValue(reqObj, IndentStatus.class);
			responseHashMap.put("message", "data updated !!");
		} else {
			reqObj.setId(null);
			indentstatus = (IndentStatus) this.objMapper.convertValue(reqObj, IndentStatus.class);
			responseHashMap.put("message", "data inserted !!");
		}
		indentstatus = (IndentStatus) this.indentStatusRepo.save(indentstatus);
		responseHashMap.put("indentstatus", indentstatus);
		return responseHashMap;
	}

	public Map<String, Object> getIndentStatus() {
		Map<String, Object> responseDataHashMap = new HashMap();
		List<IndentStatus> indentStatus = this.indentStatusRepo.findAll();
		if (indentStatus != null) {
			responseDataHashMap.put("indentStatus", indentStatus);
		} else {
			responseDataHashMap.put("message", "No data found");
		}
		return responseDataHashMap;
	}

	public Map<String, Object> getLatestListIndents(Users loginUser, String statusId) {
		Map<String, Object> responseDataHashMap = new HashMap();
		List<IndentDetails> indents = null;
		if ("ADMIN".equals(loginUser.getRole().getRole())) {
			indents = this.indentRepo.findByIndentStatusOrderByCreateDateDesc(statusId, new PageRequest(0, 15));
		} else {
			indents = this.indentRepo.findByIndentStatusAndCreateUserOrderByCreateDateDesc(statusId,
					loginUser.getEmpCode(), new PageRequest(0, 15));
		}
		responseDataHashMap.put("indents", indents);
		return responseDataHashMap;
	}

	public Map<String, Object> getListIndentsTrack(String indentId) {
		Map<String, Object> responseDataHashMap = new HashMap();
		List<IndentStatusTrack> indentStatusTrack = this.indentStatusTrackRepo.findByIndentId(indentId.trim());
		if (indentStatusTrack != null) {
			responseDataHashMap.put("indentStatusTrack", indentStatusTrack);
		} else {
			responseDataHashMap.put("message", "No data found");
		}
		return responseDataHashMap;
	}
}
