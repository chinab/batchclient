package com.vicutu.bw.service;

import com.vicutu.bw.vo.SearchStatus;

public interface SearchStatusService {
	
	SearchStatus findSearchStatusByName(String accessName);
}
