package udovyk.dribbleclimoxydaggerrx.network.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Images{

	@SerializedName("normal")
	private String normal;

	@SerializedName("hidpi")
	private Object hidpi;

	@SerializedName("teaser")
	private String teaser;

	public void setNormal(String normal){
		this.normal = normal;
	}

	public String getNormal(){
		return normal;
	}

	public void setHidpi(Object hidpi){
		this.hidpi = hidpi;
	}

	public Object getHidpi(){
		return hidpi;
	}

	public void setTeaser(String teaser){
		this.teaser = teaser;
	}

	public String getTeaser(){
		return teaser;
	}

	@Override
 	public String toString(){
		return 
			"Images{" + 
			"normal = '" + normal + '\'' + 
			",hidpi = '" + hidpi + '\'' + 
			",teaser = '" + teaser + '\'' + 
			"}";
		}
}