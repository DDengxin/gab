package com.tengzhi.business.system.upload;

import java.io.InputStream;

public class UploadVo {
	private String chunks;
	private String chunk;
	private InputStream file;
	private String fileName;
	private String ext;

	public String getChunks() {
		return chunks;
	}

	public void setChunks(String chunks) {
		this.chunks = chunks;
	}

	public String getChunk() {
		return chunk;
	}

	public void setChunk(String chunk) {
		this.chunk = chunk;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public UploadVo(String chunks, String chunk, InputStream file, String fileName, String ext) {
		super();
		this.chunks = chunks;
		this.chunk = chunk;
		this.file = file;
		this.fileName = fileName;
		this.ext = ext;
	}

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}


}
