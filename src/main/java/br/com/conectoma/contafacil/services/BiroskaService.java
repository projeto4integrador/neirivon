package br.com.conectoma.contafacil.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.conectoma.contafacil.domain.Biroska;
import br.com.conectoma.contafacil.dto.BiroskaDTO;
import br.com.conectoma.contafacil.repositories.BiroskaRepository;
import br.com.conectoma.contafacil.security.UserSS;
import br.com.conectoma.contafacil.services.exceptions.AuthorizationException;
import br.com.conectoma.contafacil.services.exceptions.DataIntegrityException;
import br.com.conectoma.contafacil.services.exceptions.ObjectNotFoundException;

@Service
public class BiroskaService {

	@Autowired
	private BiroskaRepository repo;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.biroska.fachada}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;
	
	private Long pId;

	public Biroska find(Long id) {
		Biroska obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Biroska.class.getName());
		}
		this.pId = obj.getId();
		return obj;
	}

	public Biroska insert(Biroska obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Biroska update(Biroska obj) {
		Biroska newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {

		find(id); // Caso o id não exista dispara uma exceção

		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Biroska!");
		}

	}

	public List<Biroska> findAll() {

		return repo.findAll();
	}

	public Page<Biroska> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Biroska fromDTO(BiroskaDTO objDto) {

		return new Biroska(objDto.getId(), objDto.getNome(), objDto.getLatitude(), objDto.getLongitude(),
				objDto.getImg_url());

	}

	private void updateData(Biroska newObj, Biroska obj) {
		newObj.setNome(obj.getNome());

	}

	public URI uploadFachadaPicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		if (this.pId == null) {
			throw new AuthorizationException("Id não pode ser null");
		}
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefix + this.pId + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
