package com.dudes.wsdude.controller;


import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.dto.CountryDTO;
import com.dudes.wsdude.dto.StateDTO;
import com.dudes.wsdude.mapper.CountryMapper;
import com.dudes.wsdude.mapper.StateMapper;
import com.dudes.wsdude.service.CountryService;
import com.dudes.wsdude.service.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Country Controller")
@RestController
@RequestMapping(value = "/states")
public class StateController implements GenericController<State, Long, StateDTO> {
    private final StateService service;
    private StateMapper mapper = null;

    public StateController(StateService service){
        this.service = service;
    }

    private StateMapper getMapper(){
        return mapper == null ? new StateMapper() : mapper;
    }

    @GetMapping
    @ApiOperation(value = "List all states paginated")
    public Page<StateDTO> findAllPaginated(Pageable page){
        return getMapper().convertToPageDTO(service.getAllPaginated(page));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get state by id")
    public StateDTO getById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return getMapper().convertToDTO(service.get(id));
    }

    @PostMapping
    @ApiOperation(value = "Create new state")
    public StateDTO create(@ApiParam(value = "city", required = true) @RequestBody @Valid StateDTO stateDTO) {
        State state = getMapper().convertToEntity(stateDTO);
        return getMapper().convertToDTO(service.add(state));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update state")
    public StateDTO update(@ApiParam(value = "id", required = true) @PathVariable Long id,
                             @ApiParam(value = "city", required = true) @RequestBody @Valid StateDTO stateDTO) {
        State state = getMapper().convertToEntity(stateDTO);
        return getMapper().convertToDTO(service.update(id, state));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete state")
    public void remove(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        service.removeById(id);
    }

}