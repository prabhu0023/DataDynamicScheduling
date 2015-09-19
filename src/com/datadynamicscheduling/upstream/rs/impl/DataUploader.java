package com.datadynamicscheduling.upstream.rs.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.datadynamicscheduling.common.util.DBUtil;
import com.datadynamicscheduling.upstream.bean.Product;

public class DataUploader {

	public DataUploader() {
	}

	public boolean blukUpload(List<Product> products) {
		Connection con = DBUtil.getDBConnection();
		PreparedStatement ps = null;
		try {
			String sql = "insert into input_system (item_id, merchant_id, marketplace_id, priority, data_type, payload) values(?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			final int batchSize = 1000;
			int count = 0;
			for (Product product : products) {
				ps.setString(1, String.valueOf(product.getItemID()));
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

}
