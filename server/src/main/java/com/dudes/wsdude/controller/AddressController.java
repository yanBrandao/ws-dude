package com.dudes.wsdude.controller;

import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.dto.AddressDTO;
import com.dudes.wsdude.mapper.AddressMapper;
import com.dudes.wsdude.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Address Controller")
@RestController
@RequestMapping(value = "/addresses")
public class AddressController implements GenericController<Address, Long, AddressDTO> {
    private final AddressService service;
    private AddressMapper mapper = null;

    public AddressController(AddressService service){
        this.service = service;
    }

    private AddressMapper getMapper(){
        return mapper == null ? new AddressMapper() : mapper;
    }

    @GetMapping
    @ApiOperation(value = "List all address paginated")
    public Page<AddressDTO> findAllPaginated(Pageable page){
        return getMapper().convertToPageDTO(service.getAllPaginated(page));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get address by id")
    public AddressDTO getById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        return getMapper().convertToDTO(service.get(id));
    }

    @PostMapping
    @ApiOperation(value = "Create new address")
    public AddressDTO create(@ApiParam(value = "dude", required = true) @RequestBody @Valid AddressDTO addressDTO) {
        Address address = getMapper().convertToEntity(addressDTO);
        return getMapper().convertToDTO(service.add(address));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update address")
    public AddressDTO update(@ApiParam(value = "id", required = true) @PathVariable Long id,
                          @ApiParam(value = "dude", required = true) @RequestBody @Valid AddressDTO addressDTO) {
        Address address = getMapper().convertToEntity(addressDTO);
        return getMapper().convertToDTO(service.update(id, address));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete address")
    public void remove(@ApiParam(value = "id", required = true) @PathVariable Long id) {
        service.removeById(id);
    }

}
