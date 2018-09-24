package com.bermanfaat.formbuilder.model;

public class Response {
	private int inserted;
	private String row;

	public Response(int inserted, String row) {
		super();
		this.inserted = inserted;
		this.row = row;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getInserted() {
		return inserted;
	}

	public void setInserted(int inserted) {
		this.inserted = inserted;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "Response [inserted=" + inserted + ", row=" + row + "]";
	}

}
