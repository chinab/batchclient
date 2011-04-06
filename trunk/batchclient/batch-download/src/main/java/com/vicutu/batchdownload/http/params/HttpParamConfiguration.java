package com.vicutu.batchdownload.http.params;

import org.apache.http.params.HttpParams;

public interface HttpParamConfiguration {

	HttpParams getHttpParam();

	int getMaxConnectionsPerRoute();

	int getMaxTotalConnections();
}
