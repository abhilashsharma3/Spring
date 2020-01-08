package hw.fileupload.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hw.fileupload.dao.FileUploadDao;
import hw.fileupload.model.File;
import hw.fileupload.model.Version;

@Repository
public class FileUploadImplementation implements FileUploadDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public File deleteFile(File file) {
		return null;
	}
	
	@Override
	public List<File> getAllDocuments() {
		return entityManager.createQuery("from File",File.class).getResultList();
	}

	@Override
	public File getDocument(String title) {
		// TODO Auto-generated method stub
		List<File> file=entityManager.createQuery("from File where title =?1").setParameter(1, title).getResultList();
		for(File f:file) {
			if(f.getTitle()==title) {
				return f;
			}
		}
		return file.get(0);
	}

	@Override
	@Transactional
	public File addFile(File file,Version version) {
		// TODO Auto-generated method stub
					List<Version> versionList=new ArrayList();
					version.setRev(1);
					version.setDownloadUri(ServletUriComponentsBuilder
							.fromCurrentContextPath()
							.path("/documents/getdocument/")
							.path(file.getTitle()+"/")
							.path(version.getRev().toString()+"/")
							.path(version.getFilename())
							.toUriString());
					versionList.add(version);
					file.setVersion(versionList);
					entityManager.persist(file);
					entityManager.persist(version);
					return file;
	}

	@Override
	@Transactional
	public File addNewRev(File file,Version version) {
				List<Version> ver=file.getVersion();
				Integer size=ver.size();
				++size; 
				version.setRev(size);
				version.setDownloadUri(ServletUriComponentsBuilder
						.fromCurrentContextPath()
						.path("/documents/getdocument/")
						.path(file.getTitle()+"/")
						.path(version.getRev().toString()+"/")
						.path(version.getFilename())
						.toUriString());
				entityManager.persist(version);
		return file;
	}

	@Override
	@Transactional
	public File editRev(File file) {
		entityManager.merge(file);
		return file;
	}

}
