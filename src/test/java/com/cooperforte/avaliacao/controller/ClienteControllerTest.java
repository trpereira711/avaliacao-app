package com.cooperforte.avaliacao.controller;

import com.cooperforte.avaliacao.controller.form.ClienteForm;
import com.cooperforte.avaliacao.controller.form.EnderecoForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClienteController controller;


    @Test
    public void deveCarregarContexto() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void deveBuscarClientes() throws Exception {
        mockMvc.perform(get("/clientes")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    public void deveCadastrarCliente() throws Exception {
//        mockMvc.perform(post("/clientes")
//                .content(asJsonString(new ClienteForm("Thiago Roberto Pereira", "02370453117",
//                        new EnderecoForm("71900100", "Av. das Castanheiras 1370",
//                                "Águas Claras", "Brasília",
//                                "DF", "AP 804 BL. A"), )))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").exists());
//    }

    /** */
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
