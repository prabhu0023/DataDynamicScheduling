package com.datadynamicscheduling.upstream.rs;

import java.util.List;

import com.datadynamicscheduling.upstream.bean.Product;

public interface IDataUploader {

	public boolean bulkUpload(List<Product> products);

}
