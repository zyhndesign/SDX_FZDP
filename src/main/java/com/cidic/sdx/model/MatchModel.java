package com.cidic.sdx.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="sdx_match")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatchModel {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="image") 
	private String image; //����ͼ
	
	@Column(name="quantity") 
	private int quantity; //����
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="clothes_hpbh")
	private String clothesHpbh; //�µĻ�Ʒ���
	
	@Column(name="clothes_name")
	private String clothesName; //�µ�����
	
	@Column(name="clothes_price")
	private float clothesPrice; //�µļ۸�
	
	@Column(name="clothes_size")
	private String clothesSize; //�µĴ�С
	
	@Column(name="pants_hpbh")
	private String pantsHpbh; //���ӵĻ�Ʒ���
	
	@Column(name="pants_name")
	private String pantsName; //���ӵ�����
	
	@Column(name="pants_price")
	private float pantsPrice; //���ӵļ۸�
	
	@Column(name="pants_size")
	private String pantsSize; //���ӵĴ�С

	@OneToMany(mappedBy = "matchModel",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<MatchFeedbackModel> matchFeedbackModelList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getClothesHpbh() {
		return clothesHpbh;
	}

	public void setClothesHpbh(String clothesHpbh) {
		this.clothesHpbh = clothesHpbh;
	}

	public String getClothesName() {
		return clothesName;
	}

	public void setClothesName(String clothesName) {
		this.clothesName = clothesName;
	}

	public float getClothesPrice() {
		return clothesPrice;
	}

	public void setClothesPrice(float clothesPrice) {
		this.clothesPrice = clothesPrice;
	}

	public String getClothesSize() {
		return clothesSize;
	}

	public void setClothesSize(String clothesSize) {
		this.clothesSize = clothesSize;
	}

	public String getPantsHpbh() {
		return pantsHpbh;
	}

	public void setPantsHpbh(String pantsHpbh) {
		this.pantsHpbh = pantsHpbh;
	}

	public String getPantsName() {
		return pantsName;
	}

	public void setPantsName(String pantsName) {
		this.pantsName = pantsName;
	}

	public float getPantsPrice() {
		return pantsPrice;
	}

	public void setPantsPrice(float pantsPrice) {
		this.pantsPrice = pantsPrice;
	}

	public String getPantsSize() {
		return pantsSize;
	}

	public void setPantsSize(String pantsSize) {
		this.pantsSize = pantsSize;
	}
	
	
}
