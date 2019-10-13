package com.dudes.wsdude.controller;


import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.dto.AddressDTO;
import com.dudes.wsdude.dto.CityDTO;
import com.dudes.wsdude.mapper.AddressMapper;
import com.dudes.wsdude.mapper.CityMapper;
import com.dudes.wsdude.service.AddressService;
import com.dudes.wsdude.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "City Controller")
@RestController
@RequestMapping(value = "/cities")
public class CityController implements GenericController<City, Long, CityDTO> {
    private final CityService service;
    private CityMapper mapper = null;

    public CityController(CityService service){
        this.service = service;
    }

    private CityMapper getMapper(){
        return mapper == null ? new CityMapper() : mapper;
    }

    @GetMapping
    @ApiOperation(value = "List all cities paginated")
    public Page<CityDTO> findAllPaginated(Pageable page){
        return getMapper().convertToPageDTO(service.getAllPaginated(page));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get city by id")
    public CityDTO getById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return getMapper().convertToDTO(service.get(id));
    }

    @PostMapping
    @ApiOperation(value = "Create new city")
    public CityDTO create(@ApiParam(value = "city", required = true) @RequestBody @Valid CityDTO cityDTO) {
        City city = getMapper().convertToEntity(cityDTO);
        return getMapper().convertToDTO(service.add(city));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update city")
    public CityDTO update(@ApiParam(value = "id", required = true) @PathVariable Long id,
                             @ApiParam(value = "city", required = true) @RequestBody @Valid CityDTO cityDTO) {
        City city = getMapper().convertToEntity(cityDTO);
        return getMapper().convertToDTO(service.update(id, city));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete city")
    public void remove(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        service.removeById(id);
    }

}
