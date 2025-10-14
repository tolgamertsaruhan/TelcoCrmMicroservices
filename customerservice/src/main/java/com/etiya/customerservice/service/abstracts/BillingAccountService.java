package com.etiya.customerservice.service.abstracts;



import com.etiya.customerservice.service.requests.billingaccount.CreateBillingAccountRequest;
import com.etiya.customerservice.service.requests.billingaccount.UpdateBillingAccountRequest;
import com.etiya.customerservice.service.responses.billingaccount.CreatedBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingaccount.GetListBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingaccount.UpdatedBillingAccountResponse;

import java.util.List;

public interface BillingAccountService {
    public CreatedBillingAccountResponse add(CreateBillingAccountRequest request);
    public UpdatedBillingAccountResponse update(UpdateBillingAccountRequest request);
    List<GetListBillingAccountResponse> getList();

    void delete(int id);

    void softDelete(int id);
}
