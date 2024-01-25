package JpMorganApi.JpMorganBankApi.controller;

import JpMorganApi.JpMorganBankApi.model.Bill;
import JpMorganApi.JpMorganBankApi.response.BillResponse;
import JpMorganApi.JpMorganBankApi.service.BillService;
import JpMorganApi.JpMorganBankApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BillResponse billResponse;

    /**
     * gets bill by account
     * @param accountId
     * @return
     */
    @GetMapping(value = "/accounts/{accountId}/bills")// get all bills for specific account
    public ResponseEntity<?> getBillByAccount(@PathVariable Long accountId) throws Exception {
        return new ResponseEntity<>(billResponse.getBillByAccount(accountId), HttpStatus.OK);
    }

    /**
     * gets bill by bill id
     * @param billId
     * @return
     */
    @GetMapping(value = "/bills/{billId}")//get bill by id
    public ResponseEntity<?> getBillById(@PathVariable Long billId) throws Exception {
        return new ResponseEntity<>(billResponse.getBillById(billId),HttpStatus.OK);

    }

    /**
     * get bill by customer id
     * @param customerId
     * @return
     */
    @GetMapping(value = "/customers/{customerId}/bills")// get bill by id
    public ResponseEntity<?> getBillByCustomerId(@PathVariable Long customerId) throws Exception {
        return new ResponseEntity<>(billResponse.getBillByCustomerId(customerId),HttpStatus.OK);

    }

    /**
     * gets all bills for a customer
     * @param customerId
     * @return
     */
    @GetMapping(value = "/customers/{customerId}/allbills")//Action: Get all bills for customer
    public ResponseEntity<?> getAllBillsForCustomer(@PathVariable Long customerId) throws Exception {
        return new ResponseEntity<>(billResponse.getAllBills(customerId),HttpStatus.OK);
    }

    /**
     * creates a bill
     * @param bill
     * @param
     * @return
     */
    @PostMapping(value = "/accounts/{accountId}/bills")// create a bill
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable Long accountId) throws Exception {

        return billResponse.createBill(accountId, bill);
    }

    /**
     * updates a bill
     * @param bill
     * @param billId
     * @return
     */
    @PutMapping(value = "bills/{billId}") //update A specific existing bill
    public ResponseEntity<?>updateBill(@RequestBody Bill bill, @PathVariable Long billId) throws Exception {
        billService.editBill(billId,bill);
        return new ResponseEntity<>(billResponse.updateBill(bill,billId),HttpStatus.OK);
    }

    /**
     * deletes a bill by id
     * @param billId
     * @return
     */
    @DeleteMapping(value = "/bills/{billId}")// delete a specific existing bill
    public ResponseEntity<?> removeBillById(@PathVariable Long billId) throws Exception {

        return billResponse.removeBillById(billId);
    }
}

