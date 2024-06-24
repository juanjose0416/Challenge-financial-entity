package com.financial.challenge.infraestructure.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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
import com.financial.challenge.app.dto.transaction.request.DepositRequest;
import com.financial.challenge.app.dto.transaction.request.TransactionRequest;
import com.financial.challenge.app.dto.transaction.response.TransactionResponse;
import com.financial.challenge.app.usecase.transaction.TransactionDeleterUseCase;
import com.financial.challenge.app.usecase.transaction.TransactionGetterUseCase;
import com.financial.challenge.app.usecase.transaction.TransactionUseCase;
import com.financial.challenge.infraestructure.in.handler.RestExceptionHandler;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

  @Mock private TransactionUseCase transactionUseCase;
  @Mock private TransactionGetterUseCase transactionGetterUseCase;
  @Mock private TransactionDeleterUseCase transactionDeleterUseCase;

  @InjectMocks private TransactionController transactionController;

  private MockMvc mockMvc;
  private ObjectMapper mapper;

  @BeforeEach
  public void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(transactionController)
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
  void shouldDeposit() throws Exception {
    DepositRequest depositRequest = new DepositRequest();
    depositRequest.setDestinationAccount("12344");
    depositRequest.setAmount(BigDecimal.ONE);
    when(transactionUseCase.deposit(any())).thenReturn(new TransactionResponse());
    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/transactions/deposit")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(depositRequest)))
            .andExpect(status().isOk())
            .andReturn();
    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    verify(transactionUseCase).deposit(depositRequest);
  }

  @Test
  void shouldWithdraw() throws Exception {
    DepositRequest withdrawRequest = new DepositRequest();
    withdrawRequest.setDestinationAccount("1234");
    withdrawRequest.setAmount(BigDecimal.ONE);
    when(transactionUseCase.withdraw(any())).thenReturn(new TransactionResponse());
    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/transactions/withdraw")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(withdrawRequest)))
            .andExpect(status().isOk())
            .andReturn();
    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    verify(transactionUseCase).withdraw(withdrawRequest);
  }

  @Test
  void shouldTransfer() throws Exception {
    TransactionRequest transferRequest = new TransactionRequest();
    transferRequest.setOriginAccount("1234");
    transferRequest.setDestinationAccount("12421423");
    transferRequest.setAmount(BigDecimal.TEN);
    when(transactionUseCase.transfer(any())).thenReturn(new TransactionResponse());
    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/transactions/transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(transferRequest)))
            .andExpect(status().isOk())
            .andReturn();
    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    verify(transactionUseCase).transfer(transferRequest);
  }

  @Test
  void shouldGetTransaction() throws Exception {
    TransactionResponse transactionResponse = new TransactionResponse();
    when(transactionGetterUseCase.getTransaction(anyLong())).thenReturn(transactionResponse);
    MvcResult mvcResult =
        mockMvc.perform(get("/transactions/1")).andExpect(status().isOk()).andReturn();
    verify(transactionGetterUseCase).getTransaction(1L);
    assertEquals(asJsonString(transactionResponse), mvcResult.getResponse().getContentAsString());
  }

  @Test
  void shouldGetAllTransactions() throws Exception {
    List<TransactionResponse> transactionResponseList = Collections.emptyList();
    when(transactionGetterUseCase.findAll()).thenReturn(transactionResponseList);
    MvcResult mvcResult =
        mockMvc.perform(get("/transactions/all")).andExpect(status().isOk()).andReturn();
    verify(transactionGetterUseCase).findAll();
  }

  @Test
  void shouldDeleteTransaction() throws Exception {
    MvcResult mvcResult =
        mockMvc.perform(delete("/transactions/1")).andExpect(status().isOk()).andReturn();
    verify(transactionDeleterUseCase).deleteTransaction(1L);
    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
  }

  public String asJsonString(final Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
