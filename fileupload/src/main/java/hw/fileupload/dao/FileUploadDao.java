package hw.fileupload.dao;

import java.util.List;

import hw.fileupload.model.File;
import hw.fileupload.model.Version;

public interface FileUploadDao {
	
	List<File> getAllDocuments();
	
	File getDocument(String fileName);
	
	File addFile(File file,Version ver);
	
	File addNewRev(File file,Version version);
	
	File deleteFile(File file);
	
	File editRev(File file);

}
