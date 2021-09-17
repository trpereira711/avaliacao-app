package com.cooperforte.avaliacao.controller;

import com.cooperforte.avaliacao.controller.form.AtualizaClienteForm;
import com.cooperforte.avaliacao.controller.form.ClienteForm;
import com.cooperforte.avaliacao.model.Cliente;
import com.cooperforte.avaliacao.model.Perfil;
import com.cooperforte.avaliacao.model.Usuario;
import com.cooperforte.avaliacao.model.dto.ClienteDto;
import com.cooperforte.avaliacao.model.enums.TipoOperacao;
import com.cooperforte.avaliacao.model.enums.TipoPerfil;
import com.cooperforte.avaliacao.repository.ClienteRepository;
import com.cooperforte.avaliacao.repository.EmailClienteRepository;
import com.cooperforte.avaliacao.repository.OperacaoRepository;
import com.cooperforte.avaliacao.repository.TelefoneRepository;
import com.cooperforte.avaliacao.service.AtualizaClienteService;
import com.cooperforte.avaliacao.service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AtualizaClienteService atualizaService;
    @Autowired
    private OperacaoService operacaoService;


    @GetMapping
    public ResponseEntity<Collection<ClienteDto>> buscar(@AuthenticationPrincipal Usuario usuario) {
        List<Cliente> clientes = clienteRepository.findAll();

        operacaoService.salvarOperacao(usuario, TipoOperacao.SELECT_ALL, null);

        return ResponseEntity.ok(ClienteDto.converte(clientes));
    }

    @PostMapping @Transactional
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder,
                                                @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getPerfis().contains(new Perfil(TipoPerfil.ADMIN))) {
            Cliente cliente = form.converte();
            Cliente clienteSalvo = clienteRepository.save(cliente);

            operacaoService.salvarOperacao(usuario, TipoOperacao.INSERT, cliente);

            URI uri = uriBuilder.path("/clientes/{id}").build(clienteSalvo.getId());
            return ResponseEntity.created(uri).body(new ClienteDto(clienteSalvo));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{id}") @Transactional
    public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaClienteForm form,
                                                @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getPerfis().contains(new Perfil(TipoPerfil.ADMIN))) {
             return clienteRepository.findById(id)
                    .map(cliente -> {
                        operacaoService.salvarOperacao(usuario, TipoOperacao.UPDATE, cliente);
                        return ResponseEntity.ok(atualizaService.atualizar(cliente, form));
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    operacaoService.salvarOperacao(usuario, TipoOperacao.SELECT, cliente);
                    return ResponseEntity.ok(new ClienteDto(cliente));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getPerfis().contains(new Perfil(TipoPerfil.ADMIN))) {
            return clienteRepository.findById(id).map(cliente -> {
                clienteRepository.deleteById(id);
                operacaoService.salvarOperacao(usuario, TipoOperacao.DELETE, cliente);

                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

