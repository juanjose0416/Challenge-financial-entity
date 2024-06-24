package com.financial.challenge.infraestructure.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.financial.challenge.app.dto.account.request.CreateAccountRequest;
import com.financial.challenge.app.dto.account.request.UpdateAccountRequest;
import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.app.usecase.account.AccountCreatorUseCase;
import com.financial.challenge.app.usecase.account.AccountDeleterUseCase;
import com.financial.challenge.app.usecase.account.AccountSelectorUseCase;
import com.financial.challenge.app.usecase.account.AccountUpdaterUseCase;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;
import com.financial.challenge.infraestructure.in.handler.RestExceptionHandler;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

  @Mock private AccountCreatorUseCase accountCreatorUseCase;
  @Mock private AccountUpdaterUseCase accountUpdaterUseCase;
  @Mock private AccountDeleterUseCase accountDeleterUseCase;
  @Mock private AccountSelectorUseCase accountSelectorUseCase;

  @InjectMocks private AccountController accountController;

  private MockMvc mockMvc;
  private ObjectMapper mapper;

  @BeforeEach
  public void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(accountController)
            .setControllerAdvice(new RestExceptionHandler())
            .build();
    mapper =
        new ObjectMapper()
            .findAndRegisterModules()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  @Test
  void shouldCreateAccount() throws Exception {
    CreateAccountRequest createAccountRequest = new CreateAccountRequest();
    createAccountRequest.setAccountType(AccountTypeEnum.SAVING_ACCOUNT);
    createAccountRequest.setClientId(1L);
    createAccountRequest.setIsGMF(true);
    when(accountCreatorUseCase.createAccount(any())).thenReturn(new AccountResponse());
    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(createAccountRequest)))
            .andExpect(status().isCreated())
            .andReturn();
    assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    verify(accountCreatorUseCase).createAccount(createAccountRequest);
  }

  @Test
  void shouldUpdateAccount() throws Exception {
    UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
    updateAccountRequest.setStatus(StatusEnum.ACTIVE);
    MvcResult mvcResult =
        mockMvc
            .perform(
                put("/accounts/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(updateAccountRequest)))
            .andExpect(status().isOk())
            .andReturn();
    verify(accountUpdaterUseCase).updateAccount(updateAccountRequest, 1L);
    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
  }

  @Test
  void shouldDeleteAccount() throws Exception {
    MvcResult mvcResult =
        mockMvc.perform(delete("/accounts/1")).andExpect(status().isOk()).andReturn();
    verify(accountDeleterUseCase).deleteAccount(1L);
    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
  }

  @Test
  void shouldGetAccount() throws Exception {
    AccountResponse accountResponse = new AccountResponse();
    when(accountSelectorUseCase.getAccount(anyLong())).thenReturn(accountResponse);
    MvcResult mvcResult =
        mockMvc.perform(get("/accounts/1")).andExpect(status().isOk()).andReturn();
    verify(accountSelectorUseCase).getAccount(1L);
    assertEquals(asJsonString(accountResponse), mvcResult.getResponse().getContentAsString());
  }

  @Test
  void shouldGetAllAccounts() throws Exception {
    List<AccountResponse> accountResponse = Collections.emptyList();
    when(accountSelectorUseCase.getAll()).thenReturn(accountResponse);
    MvcResult mvcResult =
        mockMvc.perform(get("/accounts/all")).andExpect(status().isOk()).andReturn();
    verify(accountSelectorUseCase).getAll();
  }

  public String asJsonString(final Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
