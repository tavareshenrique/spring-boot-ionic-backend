package com.henriquetavares.cursomc.services.validation.utils;

import com.henriquetavares.cursomc.domain.Cliente;
import com.henriquetavares.cursomc.domain.enums.TipoCliente;
import com.henriquetavares.cursomc.dto.ClienteDTO;
import com.henriquetavares.cursomc.repositories.ClienteRepository;
import com.henriquetavares.cursomc.resources.exception.FieldMessage;
import com.henriquetavares.cursomc.services.validation.ClienteUpdate;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.server.HandlerMapper;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "O email "+ '"'+objDto.getEmail()+'"' +" já existe!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}