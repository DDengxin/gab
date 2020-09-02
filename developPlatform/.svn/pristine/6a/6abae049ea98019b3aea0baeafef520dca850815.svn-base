package com.tengzhi.business.system.workflow.vo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import org.springframework.core.serializer.Serializer;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.CUSTOM)
public class Source implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Describe> describe;
	private List<Edges> edges;
	private List<Nodes> nodes;

	public List<Describe> getDescribe() {
		return describe;
	}

	public void setDescribe(List<Describe> describe) {
		this.describe = describe;
	}

	public List<Edges> getEdges() {
		return edges;
	}

	public void setEdges(List<Edges> edges) {
		this.edges = edges;
	}

	public List<Nodes> getNodes() {
		return nodes;
	}

	public void setNodes(List<Nodes> nodes) {
		this.nodes = nodes;
	}
}
