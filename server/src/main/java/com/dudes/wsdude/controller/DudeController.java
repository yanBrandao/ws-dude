package com.dudes.wsdude.controller;

import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.dto.DudeDTO;
import com.dudes.wsdude.mapper.DudeMapper;
import com.dudes.wsdude.service.DudeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Dude Controller1")
@RestController
@RequestMapping(value = "/dudes")
public class DudeController implements GenericController<Dude, Long, DudeDTO> {
    private final DudeService service;
    DudeMapper mapper;

    public DudeController(DudeService service){
        this.service = service;
    }

    DudeMapper getMapper(){
        return mapper == null ? new DudeMapper() : mapper;
    }

    @GetMapping
    @ApiOperation(value = "List all Dudes paginated")
    public Page<DudeDTO> findAllPaginated(Pageable page){
        return getMapper().convertToPageDTO(service.getAllPaginated(page));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get dude by id")
    public DudeDTO getById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return getMapper().convertToDTO(service.get(id));
    }

    @PostMapping
    @ApiOperation(value = "Create new dude")
    public DudeDTO create(@ApiParam(value = "dude", required = true) @RequestBody @Valid DudeDTO dudeDTO) {
        Dude dude = getMapper().convertToEntity(dudeDTO);
        return getMapper().convertToDTO(service.add(dude));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update dude")
    public DudeDTO update(@ApiParam(value = "id", required = true) @PathVariable Long id,
                          @ApiParam(value = "dude", required = true) @RequestBody @Valid DudeDTO dudeDTO) {
        Dude dude = getMapper().convertToEntity(dudeDTO);
        return getMapper().convertToDTO(service.update(dude));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete dude")
    public void remove(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        service.removeById(id);
    }

}
