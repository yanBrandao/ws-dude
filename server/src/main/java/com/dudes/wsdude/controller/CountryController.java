package com.dudes.wsdude.controller;

import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.dto.CountryDTO;
import com.dudes.wsdude.mapper.CountryMapper;
import com.dudes.wsdude.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Country Controller")
@RestController
@RequestMapping(value = "/countries")
public class CountryController implements GenericController<Country, Long, CountryDTO> {
    private final CountryService service;
    private CountryMapper mapper = null;

    public CountryController(CountryService service){
        this.service = service;
    }

    private CountryMapper getMapper(){
        return mapper == null ? new CountryMapper() : mapper;
    }

    @GetMapping
    @ApiOperation(value = "List all countries paginated")
    public Page<CountryDTO> findAllPaginated(Pageable page){
        return getMapper().convertToPageDTO(service.getAllPaginated(page));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get country by id")
    public CountryDTO getById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return getMapper().convertToDTO(service.get(id));
    }

    @PostMapping
    @ApiOperation(value = "Create new country")
    public CountryDTO create(@ApiParam(value = "city", required = true) @RequestBody @Valid CountryDTO countryDTO) {
        Country country = getMapper().convertToEntity(countryDTO);
        return getMapper().convertToDTO(service.add(country));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update country")
    public CountryDTO update(@ApiParam(value = "id", required = true) @PathVariable Long id,
                          @ApiParam(value = "city", required = true) @RequestBody @Valid CountryDTO countryDTO) {
        Country country = getMapper().convertToEntity(countryDTO);
        return getMapper().convertToDTO(service.update(id, country));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete country")
    public void remove(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        service.removeById(id);
    }

}