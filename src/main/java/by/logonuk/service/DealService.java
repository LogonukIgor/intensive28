package by.logonuk.service;

import by.logonuk.controller.converter.ConverterInterface;
import by.logonuk.controller.converter.DealConverter;
import by.logonuk.controller.response.DealResponse;
import by.logonuk.domain.Deal;
import by.logonuk.repository.deal.DealRepository;
import by.logonuk.repository.deal.DealRepositoryInterface;
import by.logonuk.util.DealValidator;
import by.logonuk.util.RequestBodyReader;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class DealService implements ServiceInterface<Integer, DealResponse> {

    private DealRepositoryInterface repository;

    private ConverterInterface<Deal, DealResponse> converter;

    private DealValidator validator;

    public DealService() {
        super();
        this.repository = new DealRepository();
        this.converter = new DealConverter();
        this.validator = new DealValidator();
    }
    @Override
    public DealResponse create(HttpServletRequest request) {
        StringBuilder sb;
        try {
            sb = RequestBodyReader.readBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Deal deal = converter.createRequestToEntity(sb);
        Double price = validator.validate(deal);
        return converter.entityToResponse(repository.create(deal));
    }

    @Override
    public DealResponse update(HttpServletRequest request) {
        StringBuilder sb;
        try {
            sb = RequestBodyReader.readBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Deal deal = converter.updateRequestToEntity(sb);
        Double price = validator.validate(deal);
        return converter.entityToResponse(repository.update(deal));
    }

    @Override
    public Integer delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public DealResponse findById(Integer id) {
        Deal deal = repository.findById(id);
        return converter.entityToResponse(deal);
    }
}
