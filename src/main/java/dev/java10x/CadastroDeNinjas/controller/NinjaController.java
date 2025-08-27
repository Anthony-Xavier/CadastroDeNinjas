package dev.java10x.CadastroDeNinjas.controller;

import dev.java10x.CadastroDeNinjas.model.MissaoModel;
import dev.java10x.CadastroDeNinjas.model.NinjaModel;
import dev.java10x.CadastroDeNinjas.services.NinjaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping
    public List<NinjaModel> listar() {
        return ninjaService.listarNinjas();
    }

    @PostMapping
    public NinjaModel inserir(@RequestBody NinjaModel ninja) {
        return ninjaService.insert(ninja);
    }

    @PutMapping("/{id}")
    public NinjaModel atualizar(@PathVariable Long id, @RequestBody NinjaModel ninja) {
        return ninjaService.update(id, ninja);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ninjaService.delete(id);
    }

    @PostMapping("/{id}/missao")
    public ResponseEntity<NinjaModel> atribuirMissao(
            @PathVariable Long id,
            @RequestBody MissaoModel missao) {

        NinjaModel atualizado = ninjaService.atribuirMissao(id, missao);
        return ResponseEntity.ok(atualizado);
    }

}
