package udovyk.dribbleclimoxydaggerrx.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Shot{

	@SerializedName("buckets_url")
	private String bucketsUrl;

	@SerializedName("rebounds_url")
	private String reboundsUrl;

	@SerializedName("rebounds_count")
	private int reboundsCount;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("title")
	private String title;

	@SerializedName("attachments_url")
	private String attachmentsUrl;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("comments_url")
	private String commentsUrl;

	@SerializedName("id")
	private int id;

	@SerializedName("views_count")
	private int viewsCount;

	@SerializedName("height")
	private int height;

	@SerializedName("images")
	private Images images;

	@SerializedName("likes_url")
	private String likesUrl;

	@SerializedName("team")
	private Team team;

	@SerializedName("buckets_count")
	private int bucketsCount;

	@SerializedName("tags")
	private List<String> tags;

	@SerializedName("likes_count")
	private int likesCount;

	@SerializedName("comments_count")
	private int commentsCount;

	@SerializedName("html_url")
	private String htmlUrl;

	@SerializedName("width")
	private int width;

	@SerializedName("animated")
	private boolean animated;

	@SerializedName("attachments_count")
	private int attachmentsCount;

	@SerializedName("projects_url")
	private String projectsUrl;

	@SerializedName("user")
	private User user;

	public void setBucketsUrl(String bucketsUrl){
		this.bucketsUrl = bucketsUrl;
	}

	public String getBucketsUrl(){
		return bucketsUrl;
	}

	public void setReboundsUrl(String reboundsUrl){
		this.reboundsUrl = reboundsUrl;
	}

	public String getReboundsUrl(){
		return reboundsUrl;
	}

	public void setReboundsCount(int reboundsCount){
		this.reboundsCount = reboundsCount;
	}

	public int getReboundsCount(){
		return reboundsCount;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setAttachmentsUrl(String attachmentsUrl){
		this.attachmentsUrl = attachmentsUrl;
	}

	public String getAttachmentsUrl(){
		return attachmentsUrl;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCommentsUrl(String commentsUrl){
		this.commentsUrl = commentsUrl;
	}

	public String getCommentsUrl(){
		return commentsUrl;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setViewsCount(int viewsCount){
		this.viewsCount = viewsCount;
	}

	public int getViewsCount(){
		return viewsCount;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	public void setImages(Images images){
		this.images = images;
	}

	public Images getImages(){
		return images;
	}

	public void setLikesUrl(String likesUrl){
		this.likesUrl = likesUrl;
	}

	public String getLikesUrl(){
		return likesUrl;
	}

	public void setTeam(Team team){
		this.team = team;
	}

	public Team getTeam(){
		return team;
	}

	public void setBucketsCount(int bucketsCount){
		this.bucketsCount = bucketsCount;
	}

	public int getBucketsCount(){
		return bucketsCount;
	}

	public void setTags(List<String> tags){
		this.tags = tags;
	}

	public List<String> getTags(){
		return tags;
	}

	public void setLikesCount(int likesCount){
		this.likesCount = likesCount;
	}

	public int getLikesCount(){
		return likesCount;
	}

	public void setCommentsCount(int commentsCount){
		this.commentsCount = commentsCount;
	}

	public int getCommentsCount(){
		return commentsCount;
	}

	public void setHtmlUrl(String htmlUrl){
		this.htmlUrl = htmlUrl;
	}

	public String getHtmlUrl(){
		return htmlUrl;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setAnimated(boolean animated){
		this.animated = animated;
	}

	public boolean isAnimated(){
		return animated;
	}

	public void setAttachmentsCount(int attachmentsCount){
		this.attachmentsCount = attachmentsCount;
	}

	public int getAttachmentsCount(){
		return attachmentsCount;
	}

	public void setProjectsUrl(String projectsUrl){
		this.projectsUrl = projectsUrl;
	}

	public String getProjectsUrl(){
		return projectsUrl;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Shot{" + 
			"buckets_url = '" + bucketsUrl + '\'' + 
			",rebounds_url = '" + reboundsUrl + '\'' + 
			",rebounds_count = '" + reboundsCount + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",title = '" + title + '\'' + 
			",attachments_url = '" + attachmentsUrl + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",comments_url = '" + commentsUrl + '\'' + 
			",id = '" + id + '\'' + 
			",views_count = '" + viewsCount + '\'' + 
			",height = '" + height + '\'' + 
			",images = '" + images + '\'' + 
			",likes_url = '" + likesUrl + '\'' + 
			",team = '" + team + '\'' + 
			",buckets_count = '" + bucketsCount + '\'' + 
			",tags = '" + tags + '\'' + 
			",likes_count = '" + likesCount + '\'' + 
			",comments_count = '" + commentsCount + '\'' + 
			",html_url = '" + htmlUrl + '\'' + 
			",width = '" + width + '\'' + 
			",animated = '" + animated + '\'' + 
			",attachments_count = '" + attachmentsCount + '\'' + 
			",projects_url = '" + projectsUrl + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}