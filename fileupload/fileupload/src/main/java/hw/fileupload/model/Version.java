package hw.fileupload.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="versions")
public class Version implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String downloadUri;
	
	@Column(nullable=false)
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDownloadUri() {
		return downloadUri;
	}

	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}

	@Column
	@Lob
	private byte[] document;

	@Column(nullable=false)
	private Integer rev;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
	@ManyToOne
	private File file;

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}
}
