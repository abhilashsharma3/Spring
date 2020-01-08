package hw.fileupload.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import hw.fileupload.dao.FileUploadDao;
import hw.fileupload.model.File;
import hw.fileupload.model.GetAllDocuments;
import hw.fileupload.model.Version;

@RestController
@RequestMapping("/documents")
public class FileController {
	
	@Autowired
	private FileUploadDao fileUploadDao;
	
	@PostMapping("/upload")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<HttpStatus> add(@RequestParam String name,@RequestParam String desc,@RequestParam("file") MultipartFile file) throws IOException {
		File documents=new File();
		Version ver=new Version();
		ver.setFilename(org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename()));
		documents.setTitle(name);
		ver.setDescription(desc);
		documents.setTimestamp(java.util.Calendar.getInstance().getTime());
		ver.setFile(documents);
		ver.setDocument(file.getBytes());
		fileUploadDao.addFile(documents,ver);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}
	
	@PutMapping("/upload/{name}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<HttpStatus> update(@PathVariable String name,@RequestParam("file") MultipartFile file,@RequestParam String desc) throws IOException {
		File document=fileUploadDao.getDocument(name);
		Version version=new Version();
		version.setFilename(org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename()));
		version.setDescription(desc);
		version.setFile(document);
		version.setDocument(file.getBytes());
		fileUploadDao.addNewRev(document,version);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@GetMapping("/getdocuments")
	public @ResponseBody List<GetAllDocuments> getAllDocuments() {
		List<File> file=fileUploadDao.getAllDocuments();
		List<GetAllDocuments> result2=new ArrayList<GetAllDocuments>();
		if (file == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File does not exist");
		for(File f:file) {
			GetAllDocuments g=new GetAllDocuments();
			g.setTitle(f.getTitle());
			g.setRevNum(f.getVersion().size());
			g.setTimeStamp(f.getTimestamp());
			result2.add(g);
		}
		return result2;
	}
	
	@GetMapping("/getdocuments/{name}")
	public File getDocument(@PathVariable String name) {
		File file=fileUploadDao.getDocument(name);
		//List<Version> ver=file.getVersion();
		return file;
	}
	
	@GetMapping("/getdocuments/{name}/{revNum}/{filename}")
	public ResponseEntity<?> getDocument(@PathVariable String name,@PathVariable Integer revNum,@PathVariable String filename) {
		File file=fileUploadDao.getDocument(name);
		if(revNum<1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is no revision less than 1, please provide correct revision number");
		}
		List<Version> ver=file.getVersion();
		revNum--;
		Version v=new Version();
				v.setDownloadUri(ver.get(revNum).getDownloadUri());
				v.setDocument(ver.get(revNum).getDocument());
				v.setFilename(ver.get(revNum).getFilename());
				v.setFile(ver.get(revNum).getFile());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + v.getFilename()  + "\"")
				.body(v.getDocument());
	}
	
	@PatchMapping("/edit/{name}/{revNum}")
	public ResponseEntity<HttpStatus> updateRev(@PathVariable String name,@PathVariable Integer revNum,@RequestBody Map<String,Object> desc) {
		File file=fileUploadDao.getDocument(name);
		List<Version> ver=file.getVersion();
		if(desc.size()>1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Expected only 1 description but too many description passed");
		}
		if(revNum<1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is no revision less than 1, please provide correct revision number");
		}
		revNum--;
		for(String key:desc.keySet()) {
			switch(key) {
			case "desc":
				ver.get(revNum).setDescription(desc.get(key).toString());
			}
		}
		file.setVersion(ver);
		fileUploadDao.editRev(file);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
