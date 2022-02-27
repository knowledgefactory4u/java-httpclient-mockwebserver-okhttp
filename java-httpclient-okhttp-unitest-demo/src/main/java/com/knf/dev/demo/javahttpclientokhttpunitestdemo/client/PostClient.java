package com.knf.dev.demo.
      javahttpclientokhttpunitestdemo.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostClient {

	private final String baseUrl;
	HttpClient client;
	HttpRequest request;
	
	public PostClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	 
	public List<Post> fetchAllPosts() 
			throws InterruptedException, IOException {
		
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
				uri(URI.create(baseUrl + "/posts"))
				.header("Accept", "application/json")
				  .GET().build();
		HttpResponse<String> response = client.
				send(request, HttpResponse.BodyHandlers.
						ofString());
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.readValue(response.body(),
				objectMapper.getTypeFactory().
				constructCollectionType(List.class, Post.class));
	}
}
