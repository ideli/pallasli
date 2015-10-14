package com.pallas.bpm.direct;

import java.io.IOException;

import org.restlet.data.ChallengeScheme;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class DatabaseManagerDirect {

	@DirectMethod
	public JsonNode getTables(String url) {

		ClientResource client = new ClientResource(url);
		client.setChallengeResponse(ChallengeScheme.HTTP_BASIC, "jbarrez",
				"password");
		Representation resp = client.get();
		ObjectMapper om = new ObjectMapper();
		JsonNode node = null;
		try {
			node = om.readTree(resp.getStream());
			System.out.println(node);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	public static void main(String[] a) {
		new DatabaseManagerDirect()
				.getTables("http://localhost:8080/management/tables");
	}
}
