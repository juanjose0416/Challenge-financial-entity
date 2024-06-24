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

import java.time.LocalDate;
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
import com.financial.challenge.app.dto.client.request.CreateClientRequest;
import com.financial.challenge.app.dto.client.request.UpdateClientRequest;
import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.app.usecase.client.ClientCreatorUseCase;
import com.financial.challenge.app.usecase.client.ClientDeleterUseCase;
import com.financial.challenge.app.usecase.client.ClientSelectorUseCase;
import com.financial.challenge.app.usecase.client.ClientUpdaterUseCase;
import com.financial.challenge.domain.util.enums.DocumentTypeEnum;
import com.financial.challenge.infraestructure.in.handler.RestExceptionHandler;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

  @Mock private ClientCreatorUseCase clientCreatorUseCase;
  @Mock private ClientUpdaterUseCase clientUpdaterUseCase;
  @Mock private ClientDeleterUseCase clientDeleterUseCase;
  @Mock private ClientSelectorUseCase clientSelectorUseCase;

  @InjectMocks private ClientController clientController;

  private MockMvc mockMvc;
  private ObjectMapper mapper;

  @BeforeEach
  public void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(clientController)
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
  void shouldCreateClient() throws Exception {
    CreateClientRequest createClientRequest = new CreateClientRequest();
    createClientRequest.setLastName("Doe");
    createClientRequest.setEmail("john.doe@example.com");
    createClientRequest.setName("Juan");
    createClientRequest.setLastName("Lastname");
    createClientRequest.setBirthDate(LocalDate.MIN);
    createClientRequest.setDocumentType(DocumentTypeEnum.CC);
    createClientRequest.setDocumentNumber("12345");
    when(clientCreatorUseCase.createClient(any())).thenReturn(new ClientResponse());
    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/clients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(createClientRequest)))
            .andExpect(status().isCreated())
            .andReturn();
    assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    verify(clientCreatorUseCase).createClient(createClientRequest);
  }

  @Test
  void shouldUpdateClient() throws Exception {
    UpdateClientRequest updateClientRequest = new UpdateClientRequest();
    updateClientRequest.setName("Jane");
    updateClientRequest.setLastName("Doe");
    updateClientRequest.setEmail("jane.doe@example.com");
    MvcResult mvcResult =
        mockMvc
            .perform(
                put("/clients/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(updateClientRequest)))
            .andExpect(status().isOk())
            .andReturn();
    verify(clientUpdaterUseCase).updateClient(updateClientRequest, 1L);
    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
  }

  @Test
  void shouldDeleteClient() throws Exception {
    MvcResult mvcResult =
        mockMvc.perform(delete("/clients/1")).andExpect(status().isOk()).andReturn();
    verify(clientDeleterUseCase).deleteClient(1L);
    assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
  }

  @Test
  void shouldGetClient() throws Exception {
    ClientResponse clientResponse = new ClientResponse();
    clientResponse.setId(1L);
    clientResponse.setName("John");
    clientResponse.setLastName("Doe");
    clientResponse.setEmail("john.doe@example.com");
    when(clientSelectorUseCase.getClient(anyLong())).thenReturn(clientResponse);
    MvcResult mvcResult = mockMvc.perform(get("/clients/1")).andExpect(status().isOk()).andReturn();
    verify(clientSelectorUseCase).getClient(1L);
    assertEquals(asJsonString(clientResponse), mvcResult.getResponse().getContentAsString());
  }

  @Test
  void shouldGetAllClients() throws Exception {
    List<ClientResponse> clientResponseList = Collections.emptyList();
    when(clientSelectorUseCase.getAll()).thenReturn(clientResponseList);
    MvcResult mvcResult =
        mockMvc.perform(get("/clients/all")).andExpect(status().isOk()).andReturn();
    verify(clientSelectorUseCase).getAll();
  }

  public String asJsonString(final Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
