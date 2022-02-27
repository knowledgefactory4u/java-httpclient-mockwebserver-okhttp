package com.knf.dev.demo.javahttpclientokhttpunitestdemo.client;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class PostClientTest {
	
	private MockWebServer mockWebServer;
	private PostClient post;
	private static String RESPSONE_ALL;
	
	
	static {
		try {
			RESPSONE_ALL = new String(
					(PostClient.class.getClassLoader().
							getResourceAsStream
							  ("posts-all-success.json"))
							.readAllBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void init() {
		this.mockWebServer = new MockWebServer();
		this.post = new PostClient(mockWebServer.url("/").
				toString());
	}

	@Test
	public void fetchAllPosts() 
			throws InterruptedException, IOException {
		
		mockWebServer.enqueue(new MockResponse().
				addHeader("Content-Type", "application/json; "
						+ "charset=utf-8")
				.setBody(RESPSONE_ALL).setResponseCode(200));
		List<Post> result = post.fetchAllPosts();
		
		assertEquals(2, result.size());
		assertEquals("title 1", result.get(0).title());
		assertEquals("1", result.get(0).userId());
		
	}
}