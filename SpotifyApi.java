package com.bridgelabz.spotify;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class SpotifyApi {
	public String token = "Bearer BQCNRyJEoGJjUVhY_8NwSZBoCCMYX7EMrnus_6m7KIRvZSsh2T-QRZLns2_Xn_n_2-HwkotZA1bsORFDVwVbC17xQdSfKuO77W_pm3ztpB8Vpzn7C6QwVv43VZzMtijwXzsaE31zfLzAIFd-oLlJ9JA0wfyW-6iOz45KyIs197Ta-dE6tWm8y3VW2QAoQnSCLddszFqUhiUYUBom50K5mbr8_qXPGDMC-_JYNoV4-RardlIvECbDE2HM0iSy2ipQH0XliE9t0qslFxAnSNX7KHG2ym3CXZzYZqjWM9Ly5BzO2opjp-hzjIgR1KNQ6FeVtGgBn593ova4";
	public String user_id = "314ofjcldbfucp3krrbglzkq65ca";
	public String trackid ="6zyBA0diqQ5DsWUZdJWWfa";
	public String playlistid ="7uOW9vZ8IMtxDtC1TVBPwB";
//    public String uris1 ="spotify:track:6zyBA0diqQ5DsWUZdJWWfa";
//    public String uris2 ="spotify:track:0XMgDXVvDen04Rl0iB1YNr";
//    public String uris3 ="spotify:track:6cZER0XaxSRdwBxebDINsk";
//    public String uris4 ="spotify:track:5Gy67YHzPZTk9Q0D73LAGH";

//==================================================================================  
//	Users Profile
//==================================================================================		
		@Test
	public void testGet_CurrentUsersProfile() {
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization", token)
					.when()
					.get("	https://api.spotify.com/v1/me");
			response.prettyPrint();
			response.then().assertThat().statusCode(200);
	}
	
	
	@Test
public void testGet_Users_Profile () {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+user_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
}
	
//==================================================================================  
//	Search
//==================================================================================  	
	
	@Test 
	public void testGetSearch_for_Item() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("q","Arijit singh")
				.queryParam("type","track")
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

//==================================================================================  
//	Playlists
//==================================================================================  
	@Test 
	public void testGet_Users_Playlists () {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+user_id+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test 
	public void testGet_Current_Users_Playlists () {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	

	@Test 
	public void testGet_Playlist_Cover_Image() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistid+"/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	

//	@Test 
//	public void testPost_Create_Playlist1(){
//		Response response = RestAssured.given().contentType(ContentType.JSON)
//				.accept(ContentType.JSON)
//				.header("Authorization", token)
//				.body("{\n" +
//				" \"name\": \"Automation playlist\",\n" +
//				" \"description\": \"Automation description\",\n" +
//				" \"public\": false\n" +
//				"}")
//				.when()
//				.post("https://api.spotify.com/v1/users/"+user_id+"/playlists");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(201);
//
//	}
	
	@Test 
	public void addItemstoPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
//				.queryParam("uris",uris1)
//				.queryParam("uris",uris2)
//				.queryParams("uris",uris3)
				.queryParams("uris","spotify:track:6zyBA0diqQ5DsWUZdJWWfa,spotify:track:0XMgDXVvDen04Rl0iB1YNr,spotify:track:6cZER0XaxSRdwBxebDINsk,spotify:track:5Gy67YHzPZTk9Q0D73LAGH")
				.when()
				.post("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
	}
	
	@Test 
	public void getPlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	@Test 
	public void getPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistid+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test 
	public void updatePlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"range_start\": 0,\r\n"
						+ "  \"insert_before\": 2,\r\n"
						+ "  \"range_length\": 1\r\n"
						+ "}")
				.when()
				.put("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
//	@Test 
//	public void addCustomPlaylistCoverImage() {
//		Response response = RestAssured.given().contentType(ContentType.JSON)
//				.accept(ContentType.JSON)
//				.header("Authorization", token)
//				.when()
//				.put("https://api.spotify.com/v1/playlists/"+playlistid+"/images");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
	
	@Test 
	public void changePlaylistDetails() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"Automation Playlist songs\",\r\n"
						+ "  \"description\": \"Updated playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")				
				.when()
				.put("https://api.spotify.com/v1/playlists/"+playlistid+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test 
	public void removePlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{ \"tracks\": [{ \"uri\": \"spotify:track:5Gy67YHzPZTk9Q0D73LAGH\" }]} ")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}


//==================================================================================  
//Tracks
//==================================================================================  
	
	@Test 
	public void testGet_Get_Track() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks/"+trackid+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test 
	public void testGet_Several_Tracks() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.params("ids",trackid)
				.when()
				.get("https://api.spotify.com/v1/tracks");
	response.prettyPrint();
	response.then().assertThat().statusCode(200);
	}
	
	@Test 
	public void testGet_Tracks_Audio_Features() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/"+trackid+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test 
	public void testGet_Tracks_Audio_Features1() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.params("ids",trackid)
				.when()
				.get("	https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	} 
	
	@Test 
	public void testGet_Tracks_Audio_Analysis() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-analysis/"+trackid+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
}
