package com.cooperativa.desafiobackend.service;

import com.cooperativa.desafiobackend.model.Cliente;
import com.cooperativa.desafiobackend.model.Endereco;
import com.cooperativa.desafiobackend.model.Emails;
import com.cooperativa.desafiobackend.model.Telefone;
import com.cooperativa.desafiobackend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooperativa.desafiobackend.exception.ClienteNaoEncontrado;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private boolean cpfJaExiste(String cpf) {
        return clienteRepository.findByCpf(cpf).isPresent();
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

//    public Cliente buscarPorId(Long id) {
//        Optional<Cliente> cliente = clienteRepository.findById(id);
//        return cliente.orElse(null);
//    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontrado("Cliente com ID " + id + " não encontrado."));
    }

    public Cliente salvar(Cliente cliente) {

        if (cpfJaExiste(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        vincularClienteNosRelacionamentos(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = buscarPorId(id);
        if (clienteExistente == null) {
            return null;
        }

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setCpf(clienteAtualizado.getCpf());

        clienteExistente.getTelefones().clear();
        if (clienteAtualizado.getTelefones() != null) {
            for (Telefone tel : clienteAtualizado.getTelefones()) {
                tel.setCliente(clienteExistente);
                clienteExistente.getTelefones().add(tel);
            }
        }

        clienteExistente.getEmails().clear();
        if (clienteAtualizado.getEmails() != null) {
            for (Emails em : clienteAtualizado.getEmails()) {
                em.setCliente(clienteExistente);
                clienteExistente.getEmails().add(em);
            }
        }

        clienteExistente.getEnderecos().clear();
        if (clienteAtualizado.getEnderecos() != null) {
            for (Endereco end : clienteAtualizado.getEnderecos()) {
                end.setCliente(clienteExistente);
                clienteExistente.getEnderecos().add(end);
            }
        }

        return clienteRepository.save(clienteExistente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void vincularClienteNosRelacionamentos(Cliente cliente) {
        if (cliente.getTelefones() != null) {
            for (Telefone tel : cliente.getTelefones()) {
                tel.setCliente(cliente);
            }
        }
        if (cliente.getEmails() != null) {
            for (Emails em : cliente.getEmails()) {
                em.setCliente(cliente);
            }
        }
        if (cliente.getEnderecos() != null) {
            for (Endereco end : cliente.getEnderecos()) {
                end.setCliente(cliente);
            }
        }
    }
}
