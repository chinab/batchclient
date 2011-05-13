package com.vicutu.batchdownload.engine.io;

import java.io.IOException;
import java.io.InputStream;

import com.vicutu.batchdownload.domain.AccessDetail;

public interface FileHandler {

	boolean exists(String folder, String name);

	long save(AccessDetail accessDetail, InputStream input, String folder, String name) throws IOException;

}
