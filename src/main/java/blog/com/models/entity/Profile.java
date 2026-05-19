package blog.com.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@Column(name = "profile_id", nullable = false)
	private Long profileId;

	@Column(name = "profile_image", nullable = false, length = 255)
	private String profileImage;

	@Column(name = "profile_message", length = 255)
	private String profileMessage;

	@Column(name = "account_id", nullable = false)
	private Long accountId;

	public Profile() {
	}

	public Profile(Long profileId, String profileImage, String profileMessage, Long accountId) {
		this.profileId = profileId;
		this.profileImage = profileImage;
		this.profileMessage = profileMessage;
		this.accountId = accountId;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileMessage() {
		return profileMessage;
	}

	public void setProfileMessage(String profileMessage) {
		this.profileMessage = profileMessage;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
