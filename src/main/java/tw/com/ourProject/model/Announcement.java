package tw.com.ourProject.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="announcement")

public class Announcement {
	@OneToMany(mappedBy = "announcements",cascade = CascadeType.ALL)
	private Set<Attach> attaches;
	
	@Id
	@Column(name = "announceid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer announceId;
	
	@Column(name = "type",columnDefinition="ENUM('最新公告')")
	private String type;
	
	@Column(name = "uploaddate")
	private java.util.Date uploadDate;
	
	@ManyToOne
	@JoinColumn(name="uploader",referencedColumnName = "empid")
	private Employee employees;
//  FK不需要，上面會產生
//	@Column(name = "uploader",columnDefinition="char(11)")
//	private String uploader;
	
	@Column(name = "removed")
	private java.util.Date removed;
	
	@Column(name = "content")
	private String content;
	
//	@Column(name = "updatedate")
//	private java.util.Date updateDate;
//	
//	@Column(name = "createdate")
//	private java.util.Date createDate;
//	
//	@Column(name = "createperson")
//	private String createPerson;
//	
//	@Column(name = "updateperson")
//	private String updatePerson;
	
}