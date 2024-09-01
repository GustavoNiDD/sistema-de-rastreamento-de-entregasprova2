package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;

@RestController
@RequestMapping("/pacotes")
public class PacoteController {

	@Autowired
	private PacoteService pacoteService;

	@GetMapping
	public List<Pacote> getAllPacotes() {
		return pacoteService.getAllPacotes();
	}

	@PostMapping
	public Pacote createPacote(@RequestBody Pacote pacote) {
		return pacoteService.createPacote(pacote);
	}

	 @GetMapping("/{id}")
    public ResponseEntity<Pacote> getPacoteById(@PathVariable Long id) {
        Pacote pacote = pacoteService.getPacoteById(id);
        if (pacote == null) {
            throw new NaoAcheiException("Pacote não encontrado com o ID: " + id);
        }
        return ResponseEntity.ok().body(pacote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacote> updatePacote(@PathVariable Long id, @RequestBody Pacote pacoteDetails) {
        Pacote pacote = pacoteService.getPacoteById(id);
        if (pacote == null) {
            throw new NaoAcheiException("Pacote não encontrado com o ID: " + id);
        }
        Pacote updatedPacote = pacoteService.updatePacote(id, pacoteDetails);
        return ResponseEntity.ok().body(updatedPacote);
    }

	@DeleteMapping("/{id}")
	public void deletePacote(@PathVariable Long id) {
		pacoteService.deletePacote(id);
	}

	// Endpoint para buscar pacotes por status
    @GetMapping("/status/{status}")
    public List<Pacote> getPacotesByStatus(@PathVariable String status) {
        return pacoteService.getPacotesByStatus(status);
    }

    // Endpoint para buscar pacotes por destinatário
    @GetMapping("/destinatario/{destinatario}")
    public List<Pacote> getPacotesByDestinatario(@PathVariable String destinatario) {
        return pacoteService.getPacotesByDestinatario(destinatario);
    }
}