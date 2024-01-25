package JpMorganApi.JpMorganBankApi.controllertest;

import JpMorganApi.JpMorganBankApi.controller.AccountController;
import JpMorganApi.JpMorganBankApi.model.Account;
import JpMorganApi.JpMorganBankApi.response.AccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountResponse accountResponse;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testCreateAccount() throws Exception {
        mockMvc.perform(post("/customers/1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountNumber\":\"123456\",\"accountType\":\"Savings\",\"balance\":1000.0}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/accounts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAccountById() throws Exception {
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAccount() throws Exception {
        mockMvc.perform(put("/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountNumber\":\"123456\",\"accountType\":\"Savings\",\"balance\":2000.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllAccountsForCustomer() throws Exception {
        mockMvc.perform(get("/customers/1/accounts"))
                .andExpect(status().isOk());
    }
}