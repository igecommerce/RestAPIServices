package com.gaia.web.rest;

import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.common.ErrorCodes;
import com.gaia.common.GaiaException;
import com.gaia.domain.AdminUserRolesEntity;
import com.gaia.service.AdminUserRolesService;

@RestController
@RequestMapping(path = "/api/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUsersRolesController {

	@Autowired
	private AdminUserRolesService adminUsrRolServ;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@GetMapping("adminuserroles/{id}")
	public ResponseEntity<AdminUserRolesEntity> getAdminUserRoles(@PathVariable long id) throws GaiaException {
		return new ResponseEntity<AdminUserRolesEntity>(adminUsrRolServ.getAdminUserDetails(id), HttpStatus.OK);
	}

	@GetMapping("adminuserroles")
	public ResponseEntity<PagedResources<Resource<AdminUserRolesEntity>>> getAdminUserRoles(
			PagedResourcesAssembler<AdminUserRolesEntity> assembler, @RequestParam Map<String, String> map,
			Pageable pageable) throws GaiaException {

		AdminUserRolesEntity adminUsrRolEntity = dozerBeanMapper.map(map, AdminUserRolesEntity.class);
		Map<String, Long> request = dozerBeanMapper.map(adminUsrRolEntity, Map.class);
		Page<AdminUserRolesEntity> response = adminUsrRolServ.getAdminUserDetails(request, pageable);
		if (response.getContent().isEmpty()) {
			throw new GaiaException(ErrorCodes.CODE_NO_DATA, ErrorCodes.MSG_NO_DATA);
		}

		return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
	}

}
