package by.logonuk.controller.converter;

import by.logonuk.controller.response.DealResponse;
import by.logonuk.domain.Deal;
import by.logonuk.util.DealValidator;
import com.google.gson.Gson;

import java.sql.Timestamp;

public class DealConverter implements ConverterInterface<Deal, DealResponse>{

    private DealValidator validator;

    public DealConverter() {
        super();
        this.validator = new DealValidator();
    }

    public Deal createRequestToEntity(StringBuilder sb) {
        Gson gson = new Gson();
        Deal deal = gson.fromJson(String.valueOf(sb), Deal.class);
        deal.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        deal.setModificationDate(new Timestamp(new java.util.Date().getTime()));
        deal.setIsDeleted(false);
        deal.setPrice(validator.validate(deal) * deal.getQuantity());
        return deal;
    }

    public Deal updateRequestToEntity(StringBuilder sb) {
        Gson gson = new Gson();
        Deal deal = gson.fromJson(String.valueOf(sb), Deal.class);
        deal.setModificationDate(new Timestamp(new java.util.Date().getTime()));
        deal.setPrice(validator.validate(deal) * deal.getQuantity());
        return deal;
    }

    public DealResponse entityToResponse(Deal deal) {
        DealResponse dealResponse = new DealResponse();
        dealResponse.setId(deal.getId());
        dealResponse.setUserId(deal.getUserId());
        dealResponse.setProductId(deal.getProductId());
        dealResponse.setQuantity(deal.getQuantity());
        dealResponse.setPrice(deal.getPrice());
        dealResponse.setCreationDate(deal.getCreationDate());
        dealResponse.setModificationDate(deal.getModificationDate());
        dealResponse.setIsDeleted(deal.getIsDeleted());
        return dealResponse;
    }
}
