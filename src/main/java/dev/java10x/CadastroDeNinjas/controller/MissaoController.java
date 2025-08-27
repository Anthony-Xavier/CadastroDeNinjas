package dev.java10x.CadastroDeNinjas.controller;

import dev.java10x.CadastroDeNinjas.model.MissaoModel;
import dev.java10x.CadastroDeNinjas.model.NinjaModel;
import dev.java10x.CadastroDeNinjas.services.MissaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    // Listar todas as missões
    @GetMapping
    public ResponseEntity<List<MissaoModel>> listarMissoes() {
        return ResponseEntity.ok(missaoService.listarMissoes());
    }

    // Buscar missão por ID
    @GetMapping("/{id}")
    public ResponseEntity<MissaoModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(missaoService.buscarPorId(id));
    }

    // Criar nova missão
    @PostMapping
    public ResponseEntity<MissaoModel> criar(@RequestBody MissaoModel missao) {
        return ResponseEntity.ok(missaoService.insert(missao));
    }

    // Atualizar missão existente
    @PutMapping("/{id}")
    public ResponseEntity<MissaoModel> atualizar(@PathVariable Long id, @RequestBody MissaoModel missao) {
        return ResponseEntity.ok(missaoService.update(id, missao));
    }

    // Deletar missão
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        missaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/ninja")
    public ResponseEntity<MissaoModel> adicionarNinjaNaMissao(
            @PathVariable Long id,
            @RequestBody NinjaModel ninja) {

        MissaoModel missao = missaoService.adicionarNinjaNaMissao(id, ninja);
        return ResponseEntity.ok(missao);
    }

}
