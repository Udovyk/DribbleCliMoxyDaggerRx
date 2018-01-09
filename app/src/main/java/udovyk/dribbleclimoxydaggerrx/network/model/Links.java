package udovyk.dribbleclimoxydaggerrx.network.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Links{

	@SerializedName("twitter")
	private String twitter;

	@SerializedName("web")
	private String web;

	public void setTwitter(String twitter){
		this.twitter = twitter;
	}

	public String getTwitter(){
		return twitter;
	}

	public void setWeb(String web){
		this.web = web;
	}

	public String getWeb(){
		return web;
	}

	@Override
 	public String toString(){
		return 
			"Links{" + 
			"twitter = '" + twitter + '\'' + 
			",web = '" + web + '\'' + 
			"}";
		}
}