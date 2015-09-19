package com.datadynamicscheduling.upstream.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/api")
public interface IDataUploader {

	// http://localhost:8080/sds/api/bulkUpload
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/bulkUpload")
	public boolean bulkUpload(String data);

}
