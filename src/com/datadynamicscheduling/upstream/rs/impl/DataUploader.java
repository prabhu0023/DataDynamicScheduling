package com.datadynamicscheduling.upstream.rs.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.datadynamicscheduling.common.util.DBUtil;
import com.datadynamicscheduling.upstream.bean.Product;
import com.datadynamicscheduling.upstream.rs.IDataUploader;

@Path("/api")
public class DataUploader implements IDataUploader {

	public DataUploader() {
	}

	@Override
	// http://localhost:8080/sds/api/bulkUpload
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/bulkUpload")
	public boolean bulkUpload(String data) {
		List<Product> products = convertJsonDataToPojo(data);

		Connection con = DBUtil.getDBConnection();
		PreparedStatement ps = null;
		try {
			String sql = "insert into input_system (item_id, merchant_id, marketplace_id, priority, data_type, payload) values(?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			final int batchSize = 1000;
			int count = 0;
			for (Product product : products) {
				ps.setString(1, String.valueOf(product.getItemID()));
				ps.setString(1, String.valueOf(product.getMerchantID()));
				ps.setString(1, String.valueOf(product.getMarketPlaceID()));
				ps.setString(1, product.getPriority());
				ps.setString(1, product.getDataType());
				ps.setString(1, product.getPayload());
				ps.addBatch();
				if (++count % batchSize == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private List<Product> convertJsonDataToPojo(String data) {
		List<Product> product = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			product = objectMapper.readValue(data, new TypeReference<List<Product>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return product;
	}

	public static void main(String[] args) {
		List<Product> p = new ArrayList<>();
		p.add(new Product());
		p.get(0).setDataType("Item");
		p.get(0).setItemID(100);
		p.get(0).setMarketPlaceID(2000);
		p.get(0).setMerchantID(4300);
		p.get(0).setPriority("LOW");
		p.get(0).setPayload("item 1 data");
		System.out.println(p);
	}

}
