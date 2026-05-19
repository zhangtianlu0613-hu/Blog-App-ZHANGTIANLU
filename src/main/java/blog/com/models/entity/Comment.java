package blog.com.models.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	public static final Long GUEST_ACCOUNT_ID = 0L;

	@Id
	@Column(name = "comment_id", nullable = false)
	private Long commentId;

	@Column(name = "account_id", nullable = false)
	private Long accountId;

	@Column(name = "blog_id", nullable = false)
	private Long blogId;

	@Column(name = "comment_image", length = 255)
	private String commentImage;

	@Column(name = "comment_article", nullable = false, columnDefinition = "TEXT")
	private String commentArticle;

	@Column(name = "parent_comment_id")
	private Long parentCommentId;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	public Comment() {
	}

	public Comment(Long commentId, Long accountId, Long blogId, String commentImage, String commentArticle,
			Long parentCommentId) {
		this.commentId = commentId;
		this.accountId = accountId;
		this.blogId = blogId;
		this.commentImage = commentImage;
		this.commentArticle = commentArticle;
		this.parentCommentId = parentCommentId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getCommentImage() {
		return commentImage;
	}

	public void setCommentImage(String commentImage) {
		this.commentImage = commentImage;
	}

	public String getCommentArticle() {
		return commentArticle;
	}

	public void setCommentArticle(String commentArticle) {
		this.commentArticle = commentArticle;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public static boolean isGuestAccountId(Long accountId) {
		return GUEST_ACCOUNT_ID.equals(accountId);
	}
}
