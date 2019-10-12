package com.dudes.wsdude.controller;

import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.dto.GenderDTO;
import com.dudes.wsdude.mapper.GenderMapper;
import com.dudes.wsdude.service.GenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Gender Controller")
@RestController
@RequestMapping(value = "/genders")
public class GenderController implements GenericController<Gender, Long, GenderDTO> {
    private final GenderService service;
    GenderMapper mapper;

    public GenderController(GenderService service){
        this.service = service;
    }

    GenderMapper getMapper(){
        return mapper == null ? new GenderMapper() : mapper;
    }

    @Override
    @GetMapping
    @ApiOperation(value = "List all genders paginated")
    public Page<GenderDTO> findAllPaginated(Pageable page) {
        return getMapper().convertToPageDTO(service.getAllPaginated(page));
    }

    @Override
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get gender by id")
    public GenderDTO getById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return getMapper().convertToDTO(service.get(id));
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create new gender")
    public GenderDTO create(@ApiParam(value = "dude", required = true) @RequestBody @Valid GenderDTO dto) {
        Gender gender = getMapper().convertToEntity(dto);
        return getMapper().convertToDTO(service.add(gender));
    }

    @Override
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update gender")
    public GenderDTO update(@ApiParam(value = "id", required = true) @PathVariable Long id,
                            @ApiParam(value = "gender", required = true) @RequestBody @Valid GenderDTO dto) {
        Gender gender = getMapper().convertToEntity(dto);
        return getMapper().convertToDTO(service.update(gender));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete gender")
    public void remove(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        service.removeById(id);
    }
}
